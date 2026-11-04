package com.mist.carapp

import ai.pleos.playground.vehicle.Vehicle
import ai.pleos.playground.vehicle.constant.area.TurnSignalValue
import ai.pleos.playground.vehicle.constant.control.VehicleGear
import ai.pleos.playground.vehicle.listener.BrakePedalStatusListener
import ai.pleos.playground.vehicle.listener.GearSelectionListener
import ai.pleos.playground.vehicle.listener.SteeringWheelAngleListener
import ai.pleos.playground.vehicle.listener.TurnSignalStateListener
import android.content.Context
import android.util.Log
import org.json.JSONObject

/**
 * Pleos Vehicle SDK로 게임 입력을 읽는다.
 * - 핸들: SteeringWheel 조향각(도)
 * - 브레이크: Brake 페달 눌림 여부
 * - 변속 레버: Gear (P가 아니면 게임이 멈춤)
 * - 방향지시등: TurnSignal (위/아래 = 기어 업/다운)
 * 엑셀 페달 값은 SDK에 없어서 게임 쪽에서 자동 가속으로 처리한다.
 */
class VehicleInput(context: Context, private val onChange: (JSONObject) -> Unit) {

    private val vehicle = Vehicle(context).also { it.initialize() }

    private val steeringListener = object : SteeringWheelAngleListener {
        override fun onSteeringWheelAngleUpdated(value: Float?) {
            if (value != null) emit("steerDeg", value.toGameDeg())
        }
        override fun onFailed(e: Exception) = fail(e)
    }
    private val brakeListener = object : BrakePedalStatusListener {
        override fun onBrakePedalStatusUpdated(value: Boolean?) {
            if (value != null) emit("brake", value)
        }
        override fun onFailed(e: Exception) = fail(e)
    }
    private val gearListener = object : GearSelectionListener {
        override fun onSelected(value: VehicleGear?) = emit("gear", value.toCode())
        override fun onFailed(e: Exception) = fail(e)
    }
    private val signalListener = object : TurnSignalStateListener {
        override fun onTurnSignalStateUpdated(value: TurnSignalValue?) = emit("signal", value.toCode())
        override fun onFailed(e: Exception) = fail(e)
    }

    fun start() {
        // 현재값을 먼저 한 번 읽고, 이후 변경은 리스너로 받는다
        vehicle.getGear().getCurrentGearSelectionState({ emit("gear", it.toCode()) }, ::fail)
        vehicle.getSteeringWheel().getCurrentSteeringWheelAngle({ it?.let { d -> emit("steerDeg", d.toGameDeg()) } }, ::fail)
        vehicle.getBrake().getCurrentBrakePedalStatus({ it?.let { b -> emit("brake", b) } }, ::fail)

        vehicle.getGear().registerGearSelection(gearListener)
        vehicle.getSteeringWheel().registerSteeringWheelAngle(steeringListener)
        vehicle.getBrake().registerBrakePedalStatus(brakeListener)
        vehicle.getTurnSignal().registerTurnSignalState(signalListener)
    }

    fun stop() {
        vehicle.getGear().unregisterGearSelection(gearListener)
        vehicle.getSteeringWheel().unregisterSteeringWheelAngle(steeringListener)
        vehicle.getBrake().unregisterBrakePedalStatus(brakeListener)
        vehicle.getTurnSignal().unregisterTurnSignalState(signalListener)
    }

    fun release() = vehicle.release()

    private fun emit(key: String, value: Any) = onChange(JSONObject().put(key, value))

    private fun fail(e: Exception) {
        Log.w(TAG, "vehicle read failed", e)
    }

    // SDK는 오른쪽 조향을 음수로 준다(Virtual Console +90° → SDK -90°). 게임은 오른쪽이 +
    private fun Float.toGameDeg() = -toDouble()

    // 알 수 없는 값은 P가 아닌 것으로 본다 (안전 쪽으로)
    private fun VehicleGear?.toCode() = when (this) {
        VehicleGear.GEAR_PARK -> "P"
        VehicleGear.GEAR_REVERSE -> "R"
        VehicleGear.GEAR_NEUTRAL -> "N"
        null, VehicleGear.GEAR_UNKNOWN -> "?"
        else -> "D"
    }

    private fun TurnSignalValue?.toCode() = when (this) {
        TurnSignalValue.LEFT -> "LEFT"
        TurnSignalValue.RIGHT -> "RIGHT"
        else -> "OFF"
    }

    private companion object {
        const val TAG = "VehicleInput"
    }
}
