package com.mist.carapp

import ai.pleos.playground.navi.constants.NaviErrorCode
import ai.pleos.playground.navi.constants.NavigationStatus
import ai.pleos.playground.navi.constants.RouteOption
import ai.pleos.playground.navi.constants.WaypointIndex
import ai.pleos.playground.navi.data.CurrentLocationInfo
import ai.pleos.playground.navi.data.DestinationArrivedInfo
import ai.pleos.playground.navi.data.DestinationInfo
import ai.pleos.playground.navi.data.DrivingInfo
import ai.pleos.playground.navi.data.RequestWaypointInfo
import ai.pleos.playground.navi.data.RouteInfo
import ai.pleos.playground.navi.data.RouteStartInfo
import ai.pleos.playground.navi.data.RouteStateInfo
import ai.pleos.playground.navi.data.RouteWithWaypointsRequest
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
import android.car.drivingstate.CarUxRestrictionsManager
import android.content.Context
import android.os.Handler
import android.os.Looper
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

    private val doors = listOf(DoorValue.ROW_1_LEFT, DoorValue.ROW_1_RIGHT, DoorValue.ROW_2_LEFT, DoorValue.ROW_2_RIGHT)
    private val seats = listOf(SeatValue.ROW_1_LEFT, SeatValue.ROW_1_RIGHT, SeatValue.ROW_2_LEFT, SeatValue.ROW_2_RIGHT)

    private val naviListener = object : NaviHelperEventListener {
        override fun onRouteStarted(info: RouteStartInfo) {
            naviEvent("routeStarted", RouteStartInfo.serializer(), info)
            main.post { onProbeStarted(info) }
        }
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

    // ── 경로 확인: 카드 목적지마다 경로 요청 → onRouteStarted의 도로 거리·시간·통행료 → 안내 취소 → 다음 ──
    // 내비가 앞에 있는 동안 WebView 타이머가 멈추므로 순서 제어는 여기(메인 스레드)서 한다
    private val main = Handler(Looper.getMainLooper())
    private val probeQueue = ArrayDeque<JSONObject>()
    private var probing: JSONObject? = null
    private val probeTimeout = Runnable {
        emit(JSONObject().put("type", "navi").put("event", "probeFailed").put("data", probing?.optString("name")))
        runCatching { navi.cancelRoute() }
        probing = null
        main.postDelayed(::nextProbe, PROBE_GAP_MS)
    }

    /** places: [{lat, lng, name}] JSON 문자열. 결과는 navi 이벤트 probeRoute {name, route} / probeFailed / probeDone */
    @JavascriptInterface
    fun probeRoutes(places: String) {
        val arr = JSONArray(places)
        main.post {
            for (i in 0 until arr.length()) probeQueue.addLast(arr.getJSONObject(i))
            if (probing == null) nextProbe()
        }
    }

    private fun nextProbe() {
        val next = probeQueue.removeFirstOrNull()
        if (next == null) {
            naviEvent("probeDone")
            return
        }
        probing = next
        naviCall("probeRoute") { navi.requestRoute(routeInfo(next.getDouble("lat"), next.getDouble("lng"), next.getString("name"), "RECOMMENDED")) }
        main.postDelayed(probeTimeout, PROBE_TIMEOUT_MS)
    }

    private fun onProbeStarted(info: RouteStartInfo) {
        val p = probing ?: return
        main.removeCallbacks(probeTimeout)
        emit(JSONObject().put("type", "navi").put("event", "probeRoute")
            .put("data", JSONObject().put("name", p.getString("name")).put("route", JSONObject(json.encodeToString(RouteStartInfo.serializer(), info)))))
        probing = null
        // 안내가 막 시작된 직후의 취소는 내비가 무시한다 → 잠시 뒤 취소하고 다음 요청
        main.postDelayed({
            runCatching { navi.cancelRoute() }
            main.postDelayed(::nextProbe, PROBE_GAP_MS)
        }, CANCEL_DELAY_MS)
    }

    fun release() {
        main.removeCallbacksAndMessages(null)
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
        const val PROBE_TIMEOUT_MS = 20_000L
        const val PROBE_GAP_MS = 800L
        const val CANCEL_DELAY_MS = 2_000L
    }
}
