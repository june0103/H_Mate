package com.mist.carapp

import ai.pleos.playground.navi.constants.NaviErrorCode
import ai.pleos.playground.navi.constants.NavigationStatus
import ai.pleos.playground.navi.constants.RouteOption
import ai.pleos.playground.navi.constants.WaypointIndex
import ai.pleos.playground.navi.data.CurrentLocationInfo
import ai.pleos.playground.navi.data.DestinationArrivedInfo
import ai.pleos.playground.navi.data.DestinationInfo
import ai.pleos.playground.navi.data.DrivingInfo
import ai.pleos.playground.navi.data.RecentDestinationInfo
import ai.pleos.playground.navi.data.RequestWaypointInfo
import ai.pleos.playground.navi.data.RouteInfo
import ai.pleos.playground.navi.data.RouteStartInfo
import ai.pleos.playground.navi.data.RouteStateInfo
import ai.pleos.playground.navi.data.RouteWithWaypointsRequest
import ai.pleos.playground.navi.data.TripInfo
import ai.pleos.playground.navi.data.TripRequest
import ai.pleos.playground.navi.data.WaypointArrivedInfo
import ai.pleos.playground.navi.data.WaypointChangedInfo
import ai.pleos.playground.navi.data.WaypointInfo
import ai.pleos.playground.navi.helper.NaviHelper
import ai.pleos.playground.navi.helper.listener.NaviHelperEventListener
import ai.pleos.playground.vehicle.Vehicle
import ai.pleos.playground.vehicle.api.model.area.DoorArea
import ai.pleos.playground.vehicle.api.model.area.SeatArea
import ai.pleos.playground.vehicle.constant.area.DoorValue
import ai.pleos.playground.vehicle.constant.area.SeatValue
import android.car.Car
import android.car.VehiclePropertyIds
import android.car.drivingstate.CarUxRestrictionsManager
import android.car.hardware.property.CarPropertyManager
import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject

/**
 * 차곡 데이터 화면(assets/chagok.html)용 JS 브리지. 피그마 "구현 가능성 재검토 (11/2)" 표의 SDK 항목을 모두 읽고,
 * 순정 내비(NaviHelper)에 경로·경유지·경로 옵션을 요청하고 이벤트(도착 등)를 받는다.
 * 화면에서는 window.chagok.xxx()로 부르고, 결과는 onResult로 받은 JSON을 window.onChagok()에 넘긴다.
 * { type: "value", key, value } 읽은 값 / { type: "error", key, message } 실패 / { type: "navi", event, data } 내비 이벤트
 */
class ChagokBridge(context: Context, private val onResult: (JSONObject) -> Unit) {

    private val vehicle = Vehicle(context).also { it.initialize() }
    private val navi = NaviHelper(context)
    private val json = Json { encodeDefaults = true }
    private val car = runCatching { Car.createCar(context) }.onFailure { Log.w(TAG, "Car connect failed", it) }.getOrNull()
    private val uxr = car?.getCarManager(Car.CAR_UX_RESTRICTION_SERVICE) as? CarUxRestrictionsManager
    // PRD v1.2 탭: SDK의 getRangeRemainingDistance·getEvBatteryCapacity는 지원 중단 예정(@hide) → AAOS 표준 속성으로 읽는다
    private val props = runCatching { car?.getCarManager(Car.PROPERTY_SERVICE) as? CarPropertyManager }.getOrNull()

    private val doors = listOf(DoorValue.ROW_1_LEFT, DoorValue.ROW_1_RIGHT, DoorValue.ROW_2_LEFT, DoorValue.ROW_2_RIGHT)
    private val seats = listOf(SeatValue.ROW_1_LEFT, SeatValue.ROW_1_RIGHT, SeatValue.ROW_2_LEFT, SeatValue.ROW_2_RIGHT)

    private val naviListener = object : NaviHelperEventListener {
        override fun onRouteStarted(info: RouteStartInfo) = naviEvent("routeStarted", RouteStartInfo.serializer(), info)
        override fun onRouteEnded() = naviEvent("routeEnded")
        override fun onRoutingCompleted() = naviEvent("routingCompleted")
        override fun onReRouteEnded() = naviEvent("reRouteEnded")
        override fun onRouteOptionChanged(option: RouteOption) = naviEvent("routeOptionChanged", option.name)
        override fun onRouteCancelled() = naviEvent("routeCancelled")
        override fun onWaypointChanged(info: WaypointChangedInfo) = naviEvent("waypointChanged", WaypointChangedInfo.serializer(), info)
        override fun onDestinationArrived(info: DestinationArrivedInfo) = naviEvent("destinationArrived", DestinationArrivedInfo.serializer(), info)
        override fun onWayPointArrived(info: WaypointArrivedInfo) = naviEvent("waypointArrived", WaypointArrivedInfo.serializer(), info)
        override fun onDestinationChanged() = naviEvent("destinationChanged")
        override fun onError(code: NaviErrorCode) = naviEvent("error", code.name)
        override fun onCurrentLocationInfo(info: CurrentLocationInfo) = naviEvent("currentLocation", CurrentLocationInfo.serializer(), info)
        override fun onRouteStateInfo(info: RouteStateInfo) = naviEvent("routeState", RouteStateInfo.serializer(), info)
        override fun onDestinationInfo(info: DestinationInfo) = naviEvent("destination", DestinationInfo.serializer(), info)
        override fun onWayPointInfo(list: List<WaypointInfo>) =
            emit(JSONObject().put("type", "navi").put("event", "waypoints").put("data", JSONArray(list.map { JSONObject(json.encodeToString(WaypointInfo.serializer(), it)) })))
        override fun onCurrentRoadSpeedLimit(limit: Int) = naviEvent("speedLimit", limit)
        override fun onDrivingInfo(info: DrivingInfo) = naviEvent("drivingInfo", DrivingInfo.serializer(), info)
        override fun onNavigationStatus(status: NavigationStatus) = naviEvent("status", status.name)
        override fun onTripInfo(info: TripInfo) = naviEvent("tripInfo", TripInfo.serializer(), info)
        // 최근 목적지: 좌표·이름·주소·POI만 있고 시각·도착 여부는 없다 → "마지막 목적지"로만 쓴다
        override fun onRecentDestinationInfo(recentDestinationInfoList: List<RecentDestinationInfo>) =
            emit(JSONObject().put("type", "navi").put("event", "recentDestinations")
                .put("data", JSONArray(recentDestinationInfoList.map { JSONObject(json.encodeToString(RecentDestinationInfo.serializer(), it)) })))
    }

    init {
        navi.initialize(
            { navi.addListener(naviListener); naviEvent("ready") },
            { e -> Log.w(TAG, "NaviHelper init failed", e); emit(error("navi", e.message ?: e.javaClass.simpleName)) },
        )
        uxr?.registerListener { r -> value("uxRestricted", r.isRequiresDistractionOptimization) }
    }

    /** 차량 값을 모두 한 번 읽는다. 화면이 2초마다 부른다. */
    @JavascriptInterface
    fun readAll() {
        val fuel = vehicle.getFuel()
        fuel.getFuelPercent({ v -> value("fuelPercent", v) }, { e -> fail("fuelPercent", e) })
        fuel.getCurrentFuelEconomy({ v -> value("fuelEconomy", v) }, { e -> fail("fuelEconomy", e) })
        fuel.getInstantaneousFuelEconomy({ v -> value("fuelEconomyNow", v) }, { e -> fail("fuelEconomyNow", e) })
        fuel.getCumulativeFuelEconomy({ v -> value("fuelEconomyTotal", v) }, { e -> fail("fuelEconomyTotal", e) })

        val ev = vehicle.getEvBattery()
        ev.getEvBatteryStateOfCharge({ v -> value("evSoc", v) }, { e -> fail("evSoc", e) })
        ev.getEvBatteryLevel({ v -> value("evLevel", v) }, { e -> fail("evLevel", e) })
        ev.getEvBatteryCapacity({ v -> value("evCapacity", v) }, { e -> fail("evCapacity", e) })
        ev.getCurrentEvEfficiency({ v -> value("evEfficiency", v) }, { e -> fail("evEfficiency", e) })
        ev.getEvChargeSwitchState({ v -> value("evCharging", v) }, { e -> fail("evCharging", e) })

        vehicle.getCarInfo().getVINInfo({ v -> value("vin", v) }, { e -> fail("vin", e) })
        // 주행 규제: 정차 전용 화면을 숨길지 판단하는 값
        value("uxRestricted", uxr?.currentCarUxRestrictions?.isRequiresDistractionOptimization)

        val odo = vehicle.getOdometer()
        odo.getRangeRemainingDistance({ v -> value("range", v) }, { e -> fail("range", e) })
        odo.getCurrentDistanceDriven({ v -> value("odometer", v) }, { e -> fail("odometer", e) })
        odo.getTripCurrentDrivingDistance({ v -> value("tripDistance", v) }, { e -> fail("tripDistance", e) })
        odo.getTripCurrentDrivingTime({ v -> value("tripTime", v) }, { e -> fail("tripTime", e) })
        odo.getTripSinceRefillDrivingDistance({ v -> value("refillDistance", v) }, { e -> fail("refillDistance", e) })

        // AAOS 표준 속성 (android.car.permission.CAR_ENERGY·CAR_INFO). 주행가능거리 m, 배터리 용량 Wh
        prop("rangeAaos", VehiclePropertyIds.RANGE_REMAINING)
        prop("capacityAaos", VehiclePropertyIds.INFO_EV_BATTERY_CAPACITY)
        prop("fuelType", VehiclePropertyIds.INFO_FUEL_TYPE)
        prop("carMake", VehiclePropertyIds.INFO_MAKE)

        vehicle.getDrivingMode().getCurrentDrivingModeState({ v -> value("drivingMode", v?.name) }, { e -> fail("drivingMode", e) })
        vehicle.getGear().getCurrentGearSelectionState({ v -> value("gear", v?.name) }, { e -> fail("gear", e) })
        vehicle.getBrake().getCurrentBrakePedalStatus({ v -> value("brake", v) }, { e -> fail("brake", e) })

        for (d in doors) {
            vehicle.getDoor().getCurrentDoorPosition(DoorArea(d), { v -> value("door.${d.name}", v) }, { e -> fail("door.${d.name}", e) })
        }
        // SDK는 SEAT_OCCUPANCY 1을 "앉음"으로 읽지만, AAOS 표준(콘솔)은 1 = 빈 좌석, 2 = 앉음 → 뒤집어서 쓴다
        for (s in seats) {
            vehicle.getSeat().getCurrentSeatOccupancy(SeatArea(s), { v -> value("seat.${s.name}", v?.not()) }, { e -> fail("seat.${s.name}", e) })
        }
    }

    // ── 순정 내비 ──

    /** option: RouteOption 이름(RECOMMENDED, FREE, SHORT_TIME …) */
    @JavascriptInterface
    fun requestRoute(lat: Double, lng: Double, name: String, option: String) = naviCall("requestRoute") {
        navi.requestRoute(routeInfo(lat, lng, name, option))
    }

    /** waypoints: [{lat, lng, name}] JSON 문자열, 최대 2개(FIRST, SECOND) */
    @JavascriptInterface
    fun requestRouteWithWaypoints(lat: Double, lng: Double, name: String, option: String, waypoints: String) =
        naviCall("requestRouteWithWaypoints") {
            val arr = JSONArray(waypoints)
            val list = (0 until minOf(arr.length(), 2)).map { i ->
                val w = arr.getJSONObject(i)
                // (위도, 경도, 이름, 순서, poiId, 주소, poiSubId, poiProvider)
                RequestWaypointInfo(
                    w.getDouble("lat"), w.getDouble("lng"), w.optString("name"),
                    if (i == 0) WaypointIndex.FIRST else WaypointIndex.SECOND, "", "", "", "",
                )
            }
            navi.requestRouteWithWaypoints(RouteWithWaypointsRequest(routeInfo(lat, lng, name, option), list))
        }

    /** 안내 없이 경로 미리보기: 결과는 onTripInfo(거리·도착 예정·도착 시 SOC·에너지 소비) */
    @JavascriptInterface
    fun requestTrip(lat: Double, lng: Double, name: String) = naviCall("requestTrip") {
        // (위도, 경도, poiId, poiName, poiSubId)
        navi.requestTrip(TripRequest(lat, lng, "", name, ""))
    }

    @JavascriptInterface
    fun changeRouteOption(option: String) = naviCall("changeRouteOption") { navi.changeRouteOption(RouteOption.valueOf(option)) }

    @JavascriptInterface
    fun cancelRoute() = naviCall("cancelRoute") { navi.cancelRoute() }

    @JavascriptInterface
    fun reRoute() = naviCall("reRoute") { navi.requestReRoute() }

    /** 결과는 onDestinationInfo·onRouteStateInfo 등 리스너 이벤트로 온다 */
    @JavascriptInterface
    fun readNavi() = naviCall("readNavi") {
        navi.getDestinationInfo()
        navi.getRouteStateInfo()
        navi.getCurrentLocationInfo()
        navi.getWaypointInfo()
    }

    /** 최근 목적지 목록: 결과는 onRecentDestinationInfo → { event: "recentDestinations" } */
    @JavascriptInterface
    fun readRecent() = naviCall("readRecent") { navi.getRecentDestinationInfo() }

    private fun prop(key: String, id: Int) {
        val pm = props ?: return emit(error(key, "CarPropertyManager 없음"))
        try {
            val v = pm.getProperty<Any>(id, 0)?.value
            value(key, if (v is Array<*>) JSONArray(v.toList()) else v)
        } catch (e: Exception) {
            fail(key, e)
        }
    }

    fun release() {
        runCatching { navi.removeListener(naviListener); navi.release() }
        runCatching { uxr?.unregisterListener(); car?.disconnect() }
        vehicle.release()
    }

    // (위도, 경도, 표시 위도, 표시 경도, 이름, poiId, 주소, poiSubId, 경로 옵션, poiProvider, isMobileRequest)
    private fun routeInfo(lat: Double, lng: Double, name: String, option: String) =
        RouteInfo(lat, lng, null, null, name, "", "", "", RouteOption.valueOf(option), "", false)

    // 내비 앱이 없거나 아직 초기화 전이면 예외가 난다 → 화면 로그로
    private fun naviCall(key: String, block: () -> Unit) {
        try {
            block()
            emit(JSONObject().put("type", "navi").put("event", "sent").put("data", key))
        } catch (e: Exception) {
            fail("navi.$key", e)
        }
    }

    private fun <T> naviEvent(event: String, serializer: KSerializer<T>, data: T) =
        emit(JSONObject().put("type", "navi").put("event", event).put("data", JSONObject(json.encodeToString(serializer, data))))

    private fun naviEvent(event: String, data: Any? = null) =
        emit(JSONObject().put("type", "navi").put("event", event).put("data", data ?: JSONObject.NULL))

    private fun value(key: String, v: Any?) =
        emit(JSONObject().put("type", "value").put("key", key).put("value", v ?: JSONObject.NULL))

    private fun error(key: String, message: String) = JSONObject().put("type", "error").put("key", key).put("message", message)

    private fun fail(key: String, e: Exception) {
        Log.w(TAG, "$key failed", e)
        emit(error(key, e.message ?: e.javaClass.simpleName))
    }

    private fun emit(msg: JSONObject) = onResult(msg)

    private companion object {
        const val TAG = "ChagokBridge"
    }
}
