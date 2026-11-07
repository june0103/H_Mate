package com.mist.sdkcheck

import ai.pleos.playground.location.fused.FusedLocation
import ai.pleos.playground.location.fused.listener.FusedLocationListener
import ai.pleos.playground.location.fused.listener.ProximityAlertListener
import ai.pleos.playground.location.fused.model.LocationData
import ai.pleos.playground.location.fused.model.ProximityAlertEvent
import ai.pleos.playground.location.fused.model.ProximityAlertRequest
import ai.pleos.playground.location.fused.model.ProximityTransition
import ai.pleos.playground.navi.constants.NaviErrorCode
import ai.pleos.playground.navi.constants.RouteOption
import ai.pleos.playground.navi.data.DestinationInfo
import ai.pleos.playground.navi.data.RouteInfo
import ai.pleos.playground.navi.data.RouteStartInfo
import ai.pleos.playground.navi.helper.NaviHelper
import ai.pleos.playground.navi.helper.listener.NaviHelperEventListener
import ai.pleos.playground.stt.SpeechToText
import ai.pleos.playground.stt.listener.ResultListener
import ai.pleos.playground.tts.TextToSpeech
import ai.pleos.playground.tts.listener.EventListener
import ai.pleos.playground.vehicle.Vehicle
import ai.pleos.playground.vehicle.api.model.area.DoorArea
import ai.pleos.playground.vehicle.api.model.capability.AccessType
import ai.pleos.playground.vehicle.constant.area.DoorValue
import ai.pleos.playground.vehicle.constant.unit.TemperatureUnit
import ai.pleos.playground.vehicle.listener.DoorPositionListener
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ScrollView
import android.widget.TextView
import java.util.Locale
import ai.pleos.playground.stt.constant.ErrorType as SttErrorType
import ai.pleos.playground.stt.constant.Mode as SttMode
import ai.pleos.playground.stt.listener.OnInitializeListener as SttInitListener
import ai.pleos.playground.tts.constant.ErrorType as TtsErrorType
import ai.pleos.playground.tts.constant.Mode as TtsMode
import ai.pleos.playground.tts.listener.OnInitializeListener as TtsInitListener

/**
 * docs/pleos 문서(04~07)의 예제 코드를 그대로 옮겨 SDK 5종을 호출하고,
 * 결과를 화면과 logcat(태그 PLEOSCHECK)에 남긴다.
 * 컴파일이 되는 것 자체가 "문서의 시그니처가 실제 AAR과 맞다"는 1차 검증이다.
 */
class SdkCheckActivity : Activity() {

    private val main = Handler(Looper.getMainLooper())
    private lateinit var out: TextView

    private lateinit var vehicle: Vehicle
    private lateinit var naviHelper: NaviHelper
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechToText: SpeechToText
    private lateinit var fusedLocation: FusedLocation

    private fun log(sdk: String, msg: String) {
        Log.i(TAG, "[$sdk] $msg")
        main.post { out.append("[$sdk] $msg\n") }
    }

    /** 문서에 없는 예외로 앱이 죽지 않게, 단계마다 감싸서 결과만 기록한다. */
    private fun step(sdk: String, name: String, block: () -> Unit) {
        try {
            block()
            log(sdk, "$name: 호출됨")
        } catch (t: Throwable) {
            log(sdk, "$name: 예외 ${t.javaClass.simpleName}: ${t.message}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        out = TextView(this).apply { textSize = 14f; setPadding(32, 32, 32, 32) }
        setContentView(ScrollView(this).apply { addView(out) })
        log("ALL", "시작")

        checkVehicle()
        checkNavi()
        checkTts()
        checkStt()
        checkFusedLocation()
        // 비동기 콜백을 기다린 뒤 해제 (문서: 콜백 끝나기 전에 release 금지)
        main.postDelayed({ log("ALL", "25초 경과 — 결과 수집 끝") }, 25_000)
    }

    // ── 04 Vehicle SDK ─────────────────────────────────────────────
    private val doorListener = object : DoorPositionListener {
        override fun onPositionUpdated(pos: Int?) = log("Vehicle", "Callback onPositionUpdated pos=$pos")
        override fun onFailed(e: Exception) = log("Vehicle", "Callback onFailed ${describe(e)}")
    }

    // [문서 차이] 문서는 `e is VehicleException` / `e.errorCode`를 쓰지만, Vehicle 2.3.2 AAR에서
    // VehicleException은 난독화(callback.exception.a)되어 컴파일 시 참조할 수 없다. 리플렉션으로 확인.
    private fun describe(e: Exception): String {
        val code = e.javaClass.methods.firstOrNull { it.parameterCount == 0 && it.returnType.simpleName == "ErrorCode" }
            ?.let { runCatching { it.invoke(e) }.getOrNull() }
        return "${e.javaClass.name} errorCode=$code msg=${e.message}"
    }

    private fun checkVehicle() {
        step("Vehicle", "Vehicle(context).initialize()") {
            vehicle = Vehicle(this)
            vehicle.initialize()
        }
        if (!::vehicle.isInitialized) return
        val door = vehicle.getDoor()
        step("Vehicle", "checkDoorPositionCapabilities") {
            door.checkDoorPositionCapabilities(
                onComplete = { config, areaMap ->
                    val ok = config.accessType == AccessType.READ || config.accessType == AccessType.READ_WRITE
                    log("Vehicle", "Capability onComplete accessType=${config.accessType} readable=$ok areas=${areaMap.keys.size} " +
                        "ROW_1_LEFT bounds=${areaMap[DoorArea(DoorValue.ROW_1_LEFT)]?.let { "${it.minValue}..${it.maxValue}" }}")
                },
                onFailed = { e: Exception -> log("Vehicle", "Capability onFailed ${describe(e)}") }
            )
        }
        step("Vehicle", "getCurrentDoorPosition") {
            door.getCurrentDoorPosition(
                area = DoorArea(DoorValue.ROW_1_LEFT),
                onComplete = { pos: Int? -> log("Vehicle", "Get door onComplete pos=$pos") },
                onFailed = { e: Exception -> log("Vehicle", "Get door onFailed ${describe(e)}") }
            )
        }
        step("Vehicle", "getCurrentTemperatureDisplayUnit") {
            vehicle.getDisplay().getCurrentTemperatureDisplayUnit(
                onComplete = { unit: TemperatureUnit? -> log("Vehicle", "Get display unit onComplete unit=$unit") },
                onFailed = { e: Exception -> log("Vehicle", "Get display unit onFailed ${describe(e)}") }
            )
        }
        // [문서 차이] 04 Callback 예제는 registerDoorPosition(doorPositionListener = …)이지만 실제는 (area, listener)
        step("Vehicle", "registerDoorPosition") { door.registerDoorPosition(DoorArea(DoorValue.ROW_1_LEFT), doorListener) }
        step("Vehicle", "setDoorPosition(1)") {
            door.setDoorPosition(
                area = DoorArea(DoorValue.ROW_1_LEFT),
                value = 1,
                onFailed = { e: Exception -> log("Vehicle", "Set door onFailed ${describe(e)}") }
            )
        }
        // ── 문서(API Reference)에 없지만 AAR에 공개된 API ──
        step("Vehicle", "[미문서] getGear().getCurrentGearSelectionState") {
            vehicle.getGear().getCurrentGearSelectionState(
                { g -> log("Vehicle", "[미문서] gear=$g") }, { e -> log("Vehicle", "[미문서] gear onFailed ${describe(e)}") })
        }
        step("Vehicle", "[미문서] EvBattery.getEvBatteryLevel/getEvBatteryCapacity") {
            val ev = vehicle.getEvBattery()
            ev.getEvBatteryLevel({ v -> log("Vehicle", "[미문서] evBatteryLevel=$v") }, { e -> log("Vehicle", "[미문서] evBatteryLevel onFailed ${describe(e)}") })
            ev.getEvBatteryCapacity({ v -> log("Vehicle", "[미문서] evBatteryCapacity=$v") }, { e -> log("Vehicle", "[미문서] evBatteryCapacity onFailed ${describe(e)}") })
        }
        step("Vehicle", "[미문서] Odometer.getRangeRemainingDistance") {
            vehicle.getOdometer().getRangeRemainingDistance({ v -> log("Vehicle", "[미문서] rangeRemaining=$v") }, { e -> log("Vehicle", "[미문서] rangeRemaining onFailed ${describe(e)}") })
        }
        step("Vehicle", "[미문서] Tire.checkTirePressureCapabilities") {
            vehicle.getTire().checkTirePressureCapabilities({ c, areas -> log("Vehicle", "[미문서] tirePressure capability ${c.accessType} areas=$areas") }, { e -> log("Vehicle", "[미문서] tirePressure capability onFailed ${describe(e)}") })
        }
        main.postDelayed({
            step("Vehicle", "getCurrentDoorPosition (set 3초 후)") {
                door.getCurrentDoorPosition(
                    area = DoorArea(DoorValue.ROW_1_LEFT),
                    onComplete = { pos: Int? -> log("Vehicle", "Get after set pos=$pos") },
                    onFailed = { e: Exception -> log("Vehicle", "Get after set onFailed ${describe(e)}") }
                )
            }
            // 오류 경로 확인: 지원 영역이 6개뿐이라 3열은 미지원일 것으로 예상
            step("Vehicle", "getCurrentDoorPosition(ROW_3_LEFT)") {
                door.getCurrentDoorPosition(
                    area = DoorArea(DoorValue.ROW_3_LEFT),
                    onComplete = { pos: Int? -> log("Vehicle", "Get ROW_3_LEFT pos=$pos") },
                    onFailed = { e: Exception -> log("Vehicle", "Get ROW_3_LEFT onFailed ${describe(e)}") }
                )
            }
        }, 3_000)
    }

    // ── 05 NaviHelper SDK ──────────────────────────────────────────
    private val naviEventListener = object : NaviHelperEventListener {
        override fun onRouteStarted(routeStartInfo: RouteStartInfo) {
            log("Navi", "onRouteStarted $routeStartInfo")
            step("Navi", "getDestinationInfo (경로 시작 후)") { naviHelper.getDestinationInfo() }
            step("Navi", "changeRouteOption(HIGHWAY) (경로 시작 후)") { naviHelper.changeRouteOption(RouteOption.HIGHWAY) }
            main.postDelayed({ step("Navi", "cancelRoute") { naviHelper.cancelRoute() } }, 6_000)
        }
        override fun onRoutingCompleted() = log("Navi", "onRoutingCompleted")
        override fun onRouteEnded() = log("Navi", "[미문서] onRouteEnded")
        override fun onRouteCancelled() = log("Navi", "onRouteCancelled")
        override fun onError(errorCode: NaviErrorCode) = log("Navi", "onError $errorCode")
        override fun onDestinationInfo(destinationInfo: DestinationInfo) = log("Navi", "onDestinationInfo $destinationInfo")
        override fun onRouteOptionChanged(routeOption: RouteOption) = log("Navi", "onRouteOptionChanged $routeOption")
    }

    private fun checkNavi() {
        step("Navi", "NaviHelper(context).initialize(onConnected,onFailed)") {
            naviHelper = NaviHelper(applicationContext)
            naviHelper.initialize(
                onConnected = {
                    log("Navi", "onConnected")
                    naviHelper.addListener(naviEventListener)
                    step("Navi", "requestRoute") {
                        naviHelper.requestRoute(
                            RouteInfo(
                                latitude = 37.48544722,
                                longitude = 127.03636666,
                                displayLongitude = null,
                                displayLatitude = null,
                                poiName = "42dot",
                                poiId = "36khp37hh8gqcm5nqhe5",
                                poiSubId = "0",
                                address = "서울시 강남구 남부순환로 2621"
                            )
                        )
                    }
                },
                onFailed = { throwable -> log("Navi", "initialize onFailed ${throwable.javaClass.simpleName}: ${throwable.message}") }
            )
        }
    }

    // ── 06 TextToSpeech / SpeechToText SDK ─────────────────────────
    private val eventListener = object : EventListener {
        override fun onReady() = log("TTS", "onReady")
        override fun onStart() = log("TTS", "onStart")
        override fun onUpdatedRms(rms: Double) = Unit
        override fun onDone() = log("TTS", "onDone")
        override fun onStop() = log("TTS", "onStop")
        override fun onError(errorType: TtsErrorType) = log("TTS", "onError $errorType")
    }

    private fun checkTts() {
        step("TTS", "TextToSpeech(ctx, Mode.ON_DEVICE).initialize(listener)") {
            // 서버 모드는 Client ID/Secret 등록이 필요하므로 등록 없이 쓸 수 있는 ON_DEVICE로 확인
            textToSpeech = TextToSpeech(applicationContext, TtsMode.ON_DEVICE)
            textToSpeech.initialize(object : TtsInitListener {
                override fun onSuccess() {
                    log("TTS", "initialize onSuccess")
                    step("TTS", "setLocale(KOREA)") { textToSpeech.setLocale(Locale.KOREA) }
                    step("TTS", "speak") { textToSpeech.speak("안녕하세요. 무엇을 도와드릴까요?") }
                }

                override fun onFailure(errorType: TtsErrorType) = log("TTS", "initialize onFailure $errorType")
            })
        }
        // 문서 순서: initialize() → addEventListener()
        step("TTS", "addEventListener (initialize 직후)") { textToSpeech.addEventListener(eventListener) }
    }

    private val resultListener = object : ResultListener {
        override fun onReady() = log("STT", "onReady")
        override fun onStartedRecognition() = log("STT", "onStartedRecognition")
        override fun onUpdated(stt: String, completed: Boolean) = log("STT", "onUpdated '$stt' completed=$completed")
        override fun onUpdatedEpdData(on: Long, off: Long) = Unit
        override fun onUpdatedRms(rms: Double) = Unit
        override fun onEndedRecognition() = log("STT", "onEndedRecognition")
        override fun onError(errorType: SttErrorType) = log("STT", "onError $errorType")
    }

    private fun checkStt() {
        step("STT", "SpeechToText(ctx, Mode.ON_DEVICE).initialize(listener)") {
            speechToText = SpeechToText(applicationContext, SttMode.ON_DEVICE)
            speechToText.initialize(object : SttInitListener {
                override fun onSuccess() {
                    log("STT", "initialize onSuccess")
                    step("STT", "setLocale(KOREA)") { speechToText.setLocale(Locale.KOREA) }
                    step("STT", "addListener + request") {
                        speechToText.addListener(resultListener)
                        speechToText.request()
                    }
                    main.postDelayed({ step("STT", "stop") { speechToText.stop() } }, 5_000)
                }

                override fun onFailure(errorType: SttErrorType) = log("STT", "initialize onFailure $errorType")
            })
        }
    }

    // ── 07 FusedLocation SDK ───────────────────────────────────────
    private var locCount = 0
    private val locationListener = object : FusedLocationListener {
        override fun onChanged(location: LocationData?) {
            location ?: return log("FusedLocation", "onChanged null")
            if (++locCount > 2) return
            log("FusedLocation", "onChanged lat=${location.latitude} lon=${location.longitude} heading=${location.heading} conf=${location.confidence}")
        }

        override fun onError(exception: Exception) = log("FusedLocation", "onError ${exception.javaClass.simpleName}: ${exception.message}")
    }

    private fun checkFusedLocation() {
        step("FusedLocation", "FusedLocation(this)") { fusedLocation = FusedLocation(this) }
        if (!::fusedLocation.isInitialized) return
        val supported = try { fusedLocation.isSupported() } catch (t: Throwable) { log("FusedLocation", "isSupported 예외 $t"); false }
        log("FusedLocation", "isSupported=$supported")
        if (!supported) return
        step("FusedLocation", "initialize") { fusedLocation.initialize() }
        step("FusedLocation", "registerFusedLocationCallback") { fusedLocation.registerFusedLocationCallback(locationListener) }
        step("FusedLocation", "addProximityAlert") {
            fusedLocation.addProximityAlert(
                ProximityAlertRequest(37.48544722, 127.03636666, 500f, setOf(ProximityTransition.ENTER)),
                object : ProximityAlertListener {
                    override fun onRegistered(alertId: String) = log("FusedLocation", "proximity onRegistered $alertId")
                    override fun onTransition(event: ProximityAlertEvent) = log("FusedLocation", "proximity onTransition $event")
                    override fun onError(exception: Exception) = log("FusedLocation", "proximity onError ${exception.message}")
                }
            )
        }
    }

    override fun onDestroy() {
        if (::vehicle.isInitialized) runCatching { vehicle.getDoor().unregisterDoorPosition(DoorArea(DoorValue.ROW_1_LEFT), doorListener); vehicle.release() }
        if (::naviHelper.isInitialized) runCatching { naviHelper.removeListener(naviEventListener); naviHelper.release() }
        if (::textToSpeech.isInitialized) runCatching { textToSpeech.removeEventListener(eventListener); textToSpeech.release() }
        if (::speechToText.isInitialized) runCatching { speechToText.removeListener(resultListener); speechToText.release() }
        if (::fusedLocation.isInitialized) runCatching { fusedLocation.unregisterFusedLocationCallback(locationListener); fusedLocation.release() }
        super.onDestroy()
    }

    companion object { private const val TAG = "PLEOSCHECK" }
}
