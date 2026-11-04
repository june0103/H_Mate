package com.mist.carapp

import ai.pleos.playground.vehicle.Vehicle
import ai.pleos.playground.vehicle.api.model.area.SeatArea
import ai.pleos.playground.vehicle.api.model.area.WindowArea
import ai.pleos.playground.vehicle.api.model.capability.AreaBounds
import ai.pleos.playground.vehicle.constant.area.SeatValue
import ai.pleos.playground.vehicle.constant.area.WindowValue
import ai.pleos.playground.vehicle.constant.control.LightSwitch
import ai.pleos.playground.vehicle.constant.unit.TemperatureUnit
import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import org.json.JSONObject

/**
 * 차량 제어 화면(assets/control.html)용 JS 브리지. 공조, 앰비언트 조명, 창문을 읽고 바꾼다.
 * 화면에서는 window.carControl.xxx()로 부르고, 결과는 onResult로 받은 JSON을 window.onCarControl()에 넘긴다.
 * { type: "value", key, value } 읽은 값·바꾼 값 / { type: "error", key, message } 실패 / { type: "caps", ... } 지원 범위
 *
 * 차량마다 지원하는 좌석 영역이 달라서 check...Capabilities로 받은 첫 영역을 쓴다(못 받으면 운전석).
 */
class VehicleControl(context: Context, private val onResult: (JSONObject) -> Unit) {

    private val vehicle = Vehicle(context).also { it.initialize() }
    private val hvac get() = vehicle.getHVAC()
    private val light get() = vehicle.getLight()

    private val driver = SeatArea(SeatValue.ROW_1_LEFT)
    private var powerArea = driver
    private var tempArea = driver
    private var fanArea = driver
    private var ambientArea = driver
    private var colorArea = driver

    // 창문은 좌석 위치 이름(ROW_1_LEFT 등)으로 화면과 주고받는다
    private val windows = WINDOWS.associateWith { WindowArea(it) }.toMutableMap()

    /** 지원 영역·범위를 조회하고 나서 현재 값을 모두 읽는다. 화면이 처음 열릴 때 부른다. */
    @JavascriptInterface
    fun probe() {
        var pending = 6
        val done = { if (--pending == 0) readAll() }
        vehicle.getWindow().checkWindowPositionCapabilities({ bounds: Map<WindowArea, AreaBounds<Int>> ->
            for ((area, b) in bounds) {
                val w = area.values.firstOrNull { it in WINDOWS } ?: continue
                windows[w] = area
                emit(JSONObject().put("type", "caps").put("key", "window.${w.name}").put("min", b.minValue).put("max", b.maxValue))
            }
            done()
        }, { e -> fail("caps.window", e); done() })
        hvac.checkHvacPowerCapabilities({ areas: List<SeatArea> ->
            areas.firstOrNull()?.let { powerArea = it }
            done()
        }, { e -> fail("caps.hvacPower", e); done() })
        hvac.checkHvacTemperatureCapabilities({ bounds: Map<SeatArea, AreaBounds<Float>> ->
            bounds.entries.firstOrNull()?.let { (area, b) ->
                tempArea = area
                emit(JSONObject().put("type", "caps").put("key", "hvacTemp").put("min", b.minValue).put("max", b.maxValue))
            }
            done()
        }, { e -> fail("caps.hvacTemp", e); done() })
        hvac.checkHvacFanSpeedCapabilities({ bounds: Map<SeatArea, AreaBounds<Int>> ->
            bounds.entries.firstOrNull()?.let { (area, b) ->
                fanArea = area
                emit(JSONObject().put("type", "caps").put("key", "hvacFan").put("min", b.minValue).put("max", b.maxValue))
            }
            done()
        }, { e -> fail("caps.hvacFan", e); done() })
        light.checkAmbientLightBrightnessCapabilities({ _, bounds ->
            bounds.entries.firstOrNull()?.let { (area, b) ->
                ambientArea = area
                emit(JSONObject().put("type", "caps").put("key", "ambientBrightness").put("min", b.minValue).put("max", b.maxValue))
            }
            done()
        }, { e -> fail("caps.ambientBrightness", e); done() })
        light.checkAmbientLightBrightnessColorCapabilities({ _, areas ->
            areas.firstOrNull()?.let { colorArea = it }
            done()
        }, { e -> fail("caps.ambientColor", e); done() })
    }

    @JavascriptInterface
    fun readAll() {
        hvac.getHvacPowerState(powerArea, { v -> value("hvacPower", v) }, { e -> fail("hvacPower", e) })
        hvac.getHvacAcState(powerArea, { v -> value("hvacAc", v) }, { e -> fail("hvacAc", e) })
        hvac.getHvacTemperature(tempArea, TemperatureUnit.CELSIUS, { v -> value("hvacTemp", v) }, { e -> fail("hvacTemp", e) })
        hvac.getHvacFanSpeed(fanArea, { v -> value("hvacFan", v) }, { e -> fail("hvacFan", e) })
        light.getAmbientLightSwitch(ambientArea, { v -> value("ambientOn", v?.let { it == LightSwitch.ON }) }, { e -> fail("ambientOn", e) })
        light.getAmbientLightBrightness(ambientArea, { v -> value("ambientBrightness", v) }, { e -> fail("ambientBrightness", e) })
        light.getAmbientLightBrightnessColor(colorArea, { v -> value("ambientColor", v?.let(::hex)) }, { e -> fail("ambientColor", e) })
        for ((w, area) in windows) {
            vehicle.getWindow().getCurrentWindowPosition(area, { v -> value("window.${w.name}", v) }, { e -> fail("window.${w.name}", e) })
        }
    }

    /** window: "ROW_1_LEFT" 같은 WindowValue 이름. 위치는 SDK 범위 그대로(보통 작은 값이 닫힘). */
    @JavascriptInterface
    fun setWindowPosition(window: String, position: Int) {
        val w = WINDOWS.firstOrNull { it.name == window } ?: return fail("window.$window", IllegalArgumentException("알 수 없는 창문: $window"))
        vehicle.getWindow().setWindowPosition(windows.getValue(w), position,
            { _, v -> value("window.${w.name}", v) }, { e -> fail("window.${w.name}", e) })
    }

    @JavascriptInterface
    fun setHvacPower(on: Boolean) =
        hvac.setHvacPower(powerArea, on, { _, v -> value("hvacPower", v) }, { e -> fail("hvacPower", e) })

    @JavascriptInterface
    fun setHvacAc(on: Boolean) =
        hvac.setHvacAc(powerArea, on, { _, v -> value("hvacAc", v) }, { e -> fail("hvacAc", e) })

    @JavascriptInterface
    fun setHvacTemperature(celsius: Float) =
        hvac.setHvacTemperature(tempArea, celsius, TemperatureUnit.CELSIUS, { _, v -> value("hvacTemp", v) }, { e -> fail("hvacTemp", e) })

    @JavascriptInterface
    fun setHvacFanSpeed(level: Int) =
        hvac.setHvacFanSpeed(fanArea, level, { _, v -> value("hvacFan", v) }, { e -> fail("hvacFan", e) })

    @JavascriptInterface
    fun setAmbientOn(on: Boolean) =
        light.setAmbientLightSwitch(ambientArea, if (on) LightSwitch.ON else LightSwitch.OFF,
            { _, v -> value("ambientOn", v == LightSwitch.ON) }, { e -> fail("ambientOn", e) })

    @JavascriptInterface
    fun setAmbientBrightness(level: Int) =
        light.setAmbientLightBrightness(ambientArea, level, { _, v -> value("ambientBrightness", v) }, { e -> fail("ambientBrightness", e) })

    /** color: "#rrggbb" */
    @JavascriptInterface
    fun setAmbientColor(color: String) {
        val rgb = color.removePrefix("#").toIntOrNull(16) ?: return fail("ambientColor", IllegalArgumentException("색 형식 오류: $color"))
        light.setAmbientLightBrightnessColor(colorArea, rgb, { _, v -> value("ambientColor", v?.let(::hex)) }, { e -> fail("ambientColor", e) })
    }

    fun release() = vehicle.release()

    private fun hex(rgb: Int) = "#%06x".format(rgb and 0xffffff)

    private fun value(key: String, v: Any?) =
        emit(JSONObject().put("type", "value").put("key", key).put("value", v ?: JSONObject.NULL))

    private fun fail(key: String, e: Exception) {
        Log.w(TAG, "$key failed", e)
        emit(JSONObject().put("type", "error").put("key", key).put("message", e.message ?: e.javaClass.simpleName))
    }

    private fun emit(msg: JSONObject) = onResult(msg)

    private companion object {
        const val TAG = "VehicleControl"
        val WINDOWS = listOf(WindowValue.ROW_1_LEFT, WindowValue.ROW_1_RIGHT, WindowValue.ROW_2_LEFT, WindowValue.ROW_2_RIGHT)
    }
}
