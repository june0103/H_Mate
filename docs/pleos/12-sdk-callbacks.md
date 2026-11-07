# 12. SDK 콜백 목록 (실행·제어·조회)

Pleos Connect SDK 5종에서 **SDK 실행(초기화)·제어(Set/요청)·조회(Get/Capability)·구독(Callback)** 시 돌아오는 콜백을 모두 모았습니다.

- 출처: `api/*.md`(사이트 API Reference)의 시그니처. 사이트에 없는 API는 실제 AAR(`javap`)에서 보충했고 **[미문서]**로 표시했습니다.
- 실측 메모는 Pleos Connect Emulator v2.1.0에서 확인한 내용입니다([11-sdk-verification.md](11-sdk-verification.md)).
- 권한은 `pleos.car.permission.` 접두사를 뺀 이름입니다.

## 목차

- [1. Vehicle SDK](#1-vehicle-sdk)
- [2. NaviHelper SDK](#2-navihelper-sdk)
- [3. TextToSpeech SDK](#3-texttospeech-sdk)
- [4. SpeechToText SDK](#4-speechtotext-sdk)
- [5. FusedLocation SDK](#5-fusedlocation-sdk)
- [6. 오류 값 정리](#6-오류-값-정리)

---

## 1. Vehicle SDK

### 1-1. 실행·해제

| 호출 | 콜백 | 비고 |
|---|---|---|
| `Vehicle(context).initialize()` | 없음(반환값·콜백 없음) | 실측: 호출 직후 바로 도메인 API를 불러도 결과가 옴 |
| `vehicle.getXxx()` | 없음. 도메인 객체를 동기 반환 | `getDoor()`, `getHvac()` 등. [미문서] `getGear()` |
| `vehicle.release()` | 없음 | 비동기 콜백이 끝나기 전에 부르지 말 것 |

### 1-2. 콜백 규칙 (모든 도메인 공통)

| 종류 | 호출 형태 | 성공 시 | 실패 시 |
|---|---|---|---|
| Capability | `checkXxxCapabilities(onComplete, onFailed)` | `onComplete(CapabilityConfig[, 영역 목록/영역별 범위])` 1회 | `onFailed(Exception)` |
| Get | `getXxx([area,] onComplete, onFailed)` | `onComplete(값?)` 1회. 값은 nullable | `onFailed(Exception)` |
| Set | `setXxx([area,] value, [onComplete,] onFailed)` | `onComplete(설정된 값?)`. `onComplete`·`onFailed` 모두 기본값 `{}`가 있는 선택 인자라 생략 가능(Set은 실패를 받으려면 `onFailed`를 꼭 넘길 것). 실측: 에뮬레이터에서 `setDoorPosition`은 `onFailed` 없이 끝났지만 값은 바뀌지 않음 | `onFailed(Exception)` |
| Callback | `registerXxx([area,] listener)` | 리스너의 `onXxxUpdated(값?)`. 실측: 등록 즉시 현재값으로 1회 호출된 뒤 변경 시마다 호출 | 리스너의 `onFailed(e: Exception)`(모든 리스너가 상속하는 `VehicleListener`의 메서드) |

> **실측 주의**: `onFailed`로 오는 예외는 문서가 말하는 `VehicleException`(`errorCode`)이 아닙니다. 미지원 영역이면 일반 `Exception`(원인은 `message`), 권한이 없으면 `SecurityException`(`"<메서드> need Permission"`)이 옵니다. `VehicleException`은 AAR에서 공개되지 않아 `is VehicleException`이 컴파일되지 않습니다.

### 1-3. 도메인별 API와 콜백

표의 「콜백」 열에서 `onComplete(T)`는 성공 시 받는 값의 타입이고, 리스너는 `리스너 → 호출되는 메서드`입니다. 해제(`unregisterXxx`)는 등록과 같은 인자를 받고 콜백이 없어 표에서 뺐습니다.

#### Brake — `vehicle.getBrake()`

Provides APIs for brake pedal status. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getBrake.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkBrakePedalStatusCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the brake pedal status capabilities. |
| `getCurrentBrakePedalStatus` | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the brake pedal status. If true, the brake pedal is being pressed. |
| `registerBrakePedalStatus` | Callback |  | `BrakePedalStatusListener` → `onBrakePedalStatusUpdated(status: Boolean?)` | CAR_DRIVE | Registers a listener for brake pedal status property updates. |

#### CarInfo — `vehicle.getCarInfo()`

Provides APIs for vehicle identification information and capability info. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getCarInfo.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkCRNInfoCapabilities` *(deprecated)* | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_INFO | Gets CRN info capabilities. |
| `checkVINInfoCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_INFO | Gets VIN info capabilities. |
| `getCRNInfo` *(deprecated)* | Get |  | `onComplete(String?)`<br>`onFailed(Exception)` | CAR_INFO | Gets the Connected Resource Name (CRN) Info. |
| `getVINInfo` | Get |  | `onComplete(String?)`<br>`onFailed(Exception)` | CAR_INFO | Gets the Vehicle Identification Number (VIN). |
| `getCapabilityInfo` **[미문서]** | Get |  | `onComplete(Map<String, CapabilityConfig<Any>>)`<br>`onFailed(Exception)` | 미확인 |  |

#### Display — `vehicle.getDisplay()`

Provides APIs for temperature display unit. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDisplay.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkTemperatureDisplayUnitCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_INFO | Gets temperature display unit capabilities. |
| `getCurrentTemperatureDisplayUnit` | Get |  | `onComplete(TemperatureUnit?)`<br>`onFailed(Exception)` | CAR_INFO | Gets current temperature units for display. |
| `registerTemperatureDisplayUnit` | Callback |  | `TemperatureDisplayUnitListener` → `onTemperatureDisplayUnitUpdated(unit: TemperatureUnit?)` | CAR_INFO | Registers TemperatureDisplayUnit to get property updates. |

#### Door — `vehicle.getDoor()`

Provides APIs for door lock, door position, and glove box. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDoor.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkDoorLockCapabilities` *(deprecated)* | Capability |  | `onComplete(List<DoorArea>)`<br>`onFailed(Exception)` | CAR_DOORS | Gets DoorLock capabilities. |
| `checkDoorLockCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<DoorArea>)`<br>`onFailed(Exception)` | CAR_DOORS | Gets DoorLock capabilities. |
| `checkDoorPositionCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<DoorArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_DOORS | Gets DoorPosition capabilities. |
| `checkDoorPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<DoorArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_DOORS | Gets DoorPosition capabilities. |
| `checkGloveBoxLockCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DOORS | Gets the glove box lock capabilities. |
| `getCurrentDoorPosition` | Get | `area: DoorArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_DOORS | Gets the current door position state. |
| `getCurrentGloveBoxLockState` | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_DOORS | Gets the current glove lock state. |
| `getDoorLockState` | Get | `area: DoorArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_DOORS | Gets current door lock state. |
| `registerDoorLockState` | Callback | `area: DoorArea` | `DoorLockStateListener` → `onDoorLockStateUpdated(state: Boolean?)` | CAR_DOORS | Registers the door lock state to get property updates. |
| `registerDoorPosition` | Callback | `area: DoorArea` | `DoorPositionListener` → `onPositionUpdated(pos: Int?)` | CAR_DOORS | Registers the door area to get property updates. |
| `registerGloveBoxLockState` | Callback |  | `GloveBoxLockStateListener` → `onGloveBoxLockStateUpdated(state: Boolean?)` | CAR_DOORS | Registers the glove box lock state to get property updates. |
| `setDoorLock` | Set | `area: DoorArea`, `value: Boolean` | `onComplete(DoorArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_DOORS | Sets the current door lock state. |
| `setDoorPosition` | Set | `area: DoorArea`, `value: Int` | `onComplete(DoorArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_DOORS | Sets the current door position state. The maximum (fully open) value is vehicle-dependent and is not fixed. Call checkDoorPositionCapabilities first. That API's callback returns CapabilityConfig plus a Map<DoorArea, AreaBounds<Int>>; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig). Min is typically 0 for fully closed. |
| `setGloveBoxLock` | Set | `value: Boolean` | `onComplete(Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_DOORS | Sets the current glove lock state. |

#### DrivingMode — `vehicle.getDrivingMode()`

Provides APIs for driving mode and cruise control. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDrivingMode.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkCruiseControlStateCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets CruiseControlState capabilities. |
| `checkCruiseControlTargetSpeedCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets CruiseControlTargetSpeed capabilities. |
| `checkDrivingModeStateCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets DrivingModeState capabilities. |
| `getCruiseControlState` | Get |  | `onComplete(CruiseControl?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the current cruise control state. |
| `getCruiseControlTargetSpeed` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the current cruise control target speed in meters per second (m/s). |
| `getCurrentDrivingModeState` | Get |  | `onComplete(DrivingModeType?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets current driving mode state. |
| `registerCruiseControlState` | Callback |  | `CruiseControlStateListener` → `onCruiseControlStateUpdated(state: CruiseControl?)` | CAR_DRIVE | Registers CruiseControlState to get property updates. |
| `registerCruiseControlTargetSpeed` | Callback |  | `CruiseControlTargetSpeedListener` → `onCruiseControlTargetSpeedUpdated(speed: Float?)` | CAR_DRIVE | Registers a listener for cruise control target speed updates in meters per second (m/s). |
| `registerDrivingModeState` | Callback |  | `DrivingModeStateListener` → `onDrivingModeStateUpdated(state: DrivingModeType?)` | CAR_DRIVE | Registers DrivingModeState to get property updates. |

#### EvBattery — `vehicle.getEvBattery()`

Provides APIs for EV battery efficiency. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getEvBattery.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkCumulativeEvEfficiencyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Cumulative EV Efficiency capabilities. |
| `checkCurrentEvEfficiencyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Current EV Efficiency capabilities. |
| `checkEvBatteryStateOfChargeCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EV Battery State Of Charge capabilities. |
| `checkEvChargeCurrentDrawLimitCapabilities` *(deprecated)* | Capability |  | `onComplete(List<Float>)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets the supported EV charge current draw limits in amperes (A). |
| `checkEvChargeCurrentDrawLimitCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<Float>)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets capabilities for EV charge current draw limits in amperes (A). |
| `checkEvChargePercentLimitCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<Float>)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets capabilities for EV charge limits, each expressed as a percentage (%). |
| `checkEvChargePortOpenCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EvChargePortOpen capabilities. |
| `checkEvChargeSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EvChargeSwitch capabilities. |
| `checkInstantaneousEvEfficiencyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Instantaneous EV Efficiency capabilities. |
| `checkSinceChargeEvEfficiencyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EV Efficiency Since Charge capabilities. |
| `getCumulativeEvEfficiency` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets cumulative EV efficiency. |
| `getCurrentEvEfficiency` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets current EV efficiency. |
| `getEvBatteryStateOfCharge` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EV battery state of charge as a percentage (%). |
| `getEvChargeCurrentDrawLimit` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets the EV charge current draw limit in amperes (A). |
| `getEvChargeSwitchState` | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets the current EV battery charging state. |
| `getInstantaneousEvEfficiency` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets instantaneous EV efficiency. |
| `getSinceChargeEvEfficiency` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets EV efficiency since charge. |
| `registerCumulativeEvEfficiency` | Callback |  | `EvBatteryEfficiencyListener` → `onEvBatteryEfficiencyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Cumulative EV Efficiency listener. |
| `registerCurrentEvEfficiency` | Callback |  | `EvBatteryEfficiencyListener` → `onEvBatteryEfficiencyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Current EV Efficiency listener. |
| `registerEvBatteryStateOfCharge` | Callback |  | `EvBatteryStateOfChargeListener` → `onEvBatteryStateOfChargeUpdated(value: Int?)` | CAR_ENERGY | Registers a listener for EV battery state of charge updates expressed as a percentage (%). |
| `registerInstantaneousEvEfficiency` | Callback |  | `EvBatteryEfficiencyListener` → `onEvBatteryEfficiencyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Instantaneous EV Efficiency listener. |
| `registerSinceChargeEvEfficiency` | Callback |  | `EvBatteryEfficiencyListener` → `onEvBatteryEfficiencyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers EV Efficiency Since Charge listener. |
| `setEvChargeCurrentDrawLimit` | Set | `value: Float` | `onComplete(Float?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_ENERGY | Sets the EV charge current draw limit in amperes (A). |
| `setEvChargePercentLimit` | Set | `value: Float` | `onComplete(Float?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_ENERGY | Sets the battery state-of-charge limit for EV charging, expressed as a percentage (%). |
| `setEvChargePortOpen` | Set | `value: Boolean` | `onComplete(Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_ENERGY | Sets the EV charge port open state to On or Off. |
| `setEvChargeSwitch` | Set | `value: Boolean` | `onComplete(Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_ENERGY | Sets whether to start or stop EV battery charging. |
| `getEvBatteryLevel` **[미문서]** | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | EV 배터리 잔량(실측 77400.0) |
| `getEvBatteryCapacity` **[미문서]** | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | EV 배터리 용량(실측 77400.0) |
| `isEvChargePortOpen` **[미문서]** | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_ENERGY | 충전구 열림 여부 |
| `registerEvBatteryLevel` **[미문서]** | Callback |  | `EvBatteryLevelListener` → `onEvBatteryLevelUpdated(level: Float?)` | CAR_ENERGY |  |
| `registerEvChargePortOpen` **[미문서]** | Callback |  | `EvChargePortOpenListener` → `onEvChargePortOpenUpdated(open: Boolean?)` | CAR_ENERGY |  |

#### Fuel — `vehicle.getFuel()`

Provides APIs for fuel economy. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getFuel.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkCumulativeFuelEconomyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Cumulative Fuel Economy capabilities. |
| `checkCurrentFuelEconomyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Current Fuel Economy capabilities. |
| `checkFuelPercentCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Fuel Percent capabilities. |
| `checkInstantaneousFuelEconomyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Instantaneous Fuel Economy capabilities. |
| `checkSinceChargeFuelEconomyCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets Fuel Economy Since Refuel capabilities. |
| `getCumulativeFuelEconomy` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets cumulative fuel economy. |
| `getCurrentFuelEconomy` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets current fuel economy. |
| `getFuelPercent` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets the fuel level as a percentage (%). |
| `getInstantaneousFuelEconomy` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets instantaneous fuel economy. |
| `getSinceChargeFuelEconomy` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | Gets fuel economy since refuel. |
| `registerCumulativeFuelEconomy` | Callback |  | `FuelEconomyListener` → `onFuelEconomyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Cumulative Fuel Economy listener. |
| `registerCurrentFuelEconomy` | Callback |  | `FuelEconomyListener` → `onFuelEconomyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Current Fuel Economy listener. |
| `registerFuelPercent` | Callback |  | `FuelPercentListener` → `onFuelPercentUpdated(value: Int?)` | CAR_ENERGY | Registers a listener for fuel level updates expressed as a percentage (%). |
| `registerInstantaneousFuelEconomy` | Callback |  | `FuelEconomyListener` → `onFuelEconomyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Instantaneous Fuel Economy listener. |
| `registerSinceChargeFuelEconomy` | Callback |  | `FuelEconomyListener` → `onFuelEconomyUpdated(efficiency: Float?)` | CAR_ENERGY | Registers Fuel Economy Since Refuel listener. |

#### Gear — `vehicle.getGear()` **[미문서]**

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `getCurrentGearSelectionState` **[미문서]** | Get |  | `onComplete(VehicleGear?)`<br>`onFailed(Exception)` | CAR_DRIVE | 현재 기어 선택 상태 |
| `registerGearSelection` **[미문서]** | Callback |  | `GearSelectionListener` → `onSelected(gear: VehicleGear?)` | CAR_DRIVE | 기어 변경 구독 |

#### HVAC — `vehicle.getHVAC()`

Provides APIs for climate control (HVAC). Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getHVAC.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkHvacAcCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HvacAC capabilities. |
| `checkHvacAcCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HvacAC capabilities. |
| `checkHvacAirCleanerCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC air cleaner capabilities. |
| `checkHvacAirCleanerCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC air cleaner capabilities. |
| `checkHvacAutoCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HvacAuto capabilities. |
| `checkHvacAutoCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HvacAuto capabilities. |
| `checkHvacAutoRecirculationCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC auto recirculation capabilities. |
| `checkHvacAutoRecirculationCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC auto recirculation capabilities. |
| `checkHvacDefrosterCapabilities` *(deprecated)* | Capability |  | `onComplete(List<WindowArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC defroster capabilities. |
| `checkHvacDefrosterCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<WindowArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC defroster capabilities. |
| `checkHvacDualCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC dual capabilities. |
| `checkHvacDualCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC dual capabilities. |
| `checkHvacFanDirectionCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, List<FanDirectionType>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC fan direction capabilities. |
| `checkHvacFanDirectionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, List<FanDirectionType>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC fan direction capabilities. |
| `checkHvacFanSpeedCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC fan speed capabilities. |
| `checkHvacFanSpeedCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC fan speed capabilities. |
| `checkHvacMaxAcCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC max AC capabilities. |
| `checkHvacMaxAcCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC max AC capabilities. |
| `checkHvacMaxDefrostCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC max defrost capabilities. |
| `checkHvacMaxDefrostCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC max defrost capabilities. |
| `checkHvacPowerCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC power capabilities. |
| `checkHvacPowerCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC power capabilities. |
| `checkHvacRecirculationCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC recirculation capabilities. |
| `checkHvacRecirculationCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC recirculation capabilities. |
| `checkHvacTemperatureCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Float>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the supported target temperature ranges in degrees Celsius (°C). |
| `checkHvacTemperatureCapabilities` *(deprecated)* | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Float>>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC temperature range capabilities as AreaBounds in degrees Celsius (°C). |
| `checkHvacTemperatureCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Float>>, HvacTemperatureConfigArray?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC temperature capabilities including supported temperature ranges in degrees Celsius (°C) and the vehicle-specific temperature conversion config array. |
| `checkHvacTemperatureCurrentCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets HVAC temperature current capabilities. |
| `getHvacAcState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current AC state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacAirCleanerState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current air cleaner state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacAutoRecirculationState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current automatic recirculation. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacAutoState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current automatic mode. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacDefrosterState` | Get | `area: WindowArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current defroster state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacDualState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current temperature coupling state between areas. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacFanDirection` | Get | `area: SeatArea` | `onComplete(FanDirectionType?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current fan direction. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacFanSpeed` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current fan speed. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacMaxAcState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current max AC state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacMaxDefrostState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current max defrost state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacPowerState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current global power state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacRecirculationState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current recirculation state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacTemperature` | Get | `area: SeatArea`, `unit: TemperatureUnit` | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the target temperature in the specified unit. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `getHvacTemperatureCurrent` | Get | `area: SeatArea` | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current internal temperature of the vehicle in degrees Celsius (°C). |
| `getHvacTemperatureCurrent` | Get | `area: SeatArea`, `unit: TemperatureUnit` | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current internal temperature of the vehicle in the requested unit. |
| `registerHvacAcState` | Callback | `area: SeatArea` | `HvacAcStateListener` → `onHvacAcStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacAC state to get property updates. |
| `registerHvacAirCleanerState` | Callback | `area: SeatArea` | `HvacAirCleanerStateListener` → `onHvacAirCleanerStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacAirCleaner state to get property updates. |
| `registerHvacAutoRecirculationState` | Callback | `area: SeatArea` | `HvacRecirculationStateListener` → `onHvacAutoRecirculationStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacAutoRecirculation state to get property updates. |
| `registerHvacAutoState` | Callback | `area: SeatArea` | `HvacAutoStateListener` → `onHvacAutoStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacAuto state to get property updates. |
| `registerHvacDefrosterState` | Callback | `area: WindowArea` | `HvacDefrosterStateListener` → `onHvacDefrosterStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacDefroster state to get property updates. |
| `registerHvacDualState` | Callback | `area: SeatArea` | `HvacDualStateListener` → `onHvacDualStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacDual state to get property updates. |
| `registerHvacFanSpeed` | Callback | `area: SeatArea` | `HvacFanSpeedListener` → `onHvacFanSpeedUpdated(speed: Int?)` | CAR_CLIMATE | Registers the HvacFanSpeed to get property updates. |
| `registerHvacMaxAcState` | Callback | `area: SeatArea` | `HvacMaxAcStateListener` → `onHvacMaxAcStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacMaxAc state to get property updates. |
| `registerHvacMaxDefrostState` | Callback | `area: SeatArea` | `HvacMaxDefrostStateListener` → `onHvacMaxDefrostStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacMaxDefrost state to get property updates. |
| `registerHvacPowerState` | Callback | `area: SeatArea` | `HvacPowerStateListener` → `onHvacPowerStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacPower state to get property updates. |
| `registerHvacRecirculationState` | Callback | `area: SeatArea` | `HvacRecirculationStateListener` → `onHvacAutoRecirculationStateUpdated(state: Boolean?)` | CAR_CLIMATE | Registers the HvacRecirculation to get property updates. |
| `registerHvacTemperature` | Callback | `area: SeatArea` | `HvacTemperatureListener` → `onHvacTemperatureUpdated(temperature: Float?)` | CAR_CLIMATE | Registers a listener for target temperature updates in degrees Celsius (°C) only. |
| `registerHvacTemperature` | Callback | `area: SeatArea` | `HvacTemperatureValueListener` → `onHvacTemperatureUpdated(celsius: Float?, fahrenheit: Float?)` | CAR_CLIMATE | Registers the HvacTemperature to get property updates in degrees Celsius (°C) and degrees Fahrenheit (°F). |
| `registerHvacTemperatureCurrent` | Callback | `area: SeatArea` | `HvacTemperatureListener` → `onHvacTemperatureUpdated(temperature: Float?)` | CAR_CLIMATE | Registers a listener for current interior temperature updates in degrees Celsius (°C). |
| `registerHvacTemperatureCurrent` | Callback | `area: SeatArea` | `HvacTemperatureValueListener` → `onHvacTemperatureUpdated(celsius: Float?, fahrenheit: Float?)` | CAR_CLIMATE | Registers HvacTemperatureCurrent updates in degrees Celsius (°C) and degrees Fahrenheit (°F). |
| `setHvacAc` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets AC to On/Off for the designated areaId. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacAirCleaner` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the air cleaner to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacAuto` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets automatic mode to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacAutoRecirculation` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the automatic recirculation to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacDefroster` | Set | `area: WindowArea`, `value: Boolean` | `onComplete(WindowArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the defroster to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacDual` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Enable temperature coupling between areas. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacFanDirection` | Set | `area: SeatArea`, `value: FanDirectionType` | `onComplete(SeatArea, FanDirectionType?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets fan direction. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacFanSpeed` | Set | `area: SeatArea`, `value: Int` | `onComplete(SeatArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the fan speed. The supported fan speed range is vehicle-dependent and is not fixed. Call checkHvacFanSpeedCapabilities first. That API's callback returns CapabilityConfig plus a Map<SeatArea, AreaBounds<Int>>; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig). |
| `setHvacMaxAc` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the max AC to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacMaxDefrost` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the max defrost to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacPower` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the global power state to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacRecirculation` | Set | `area: SeatArea`, `value: Boolean` | `onComplete(SeatArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets recirculation to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |
| `setHvacTemperature` | Set | `area: SeatArea`, `value: Float`, `unit: TemperatureUnit` | `onComplete(SeatArea, Float?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets the target temperature in the specified unit. The supported temperature range is vehicle-dependent and is not fixed. Call checkHvacTemperatureCapabilities first. That API's callback returns CapabilityConfig, a Map<SeatArea, AreaBounds<Float>>, and optional HvacTemperatureConfigArray; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig). AreaBounds are in degrees Celsius (°C); use the config array when converting to Fahrenheit. |

#### Light — `vehicle.getLight()`

Provides APIs for exterior, cabin, and ambient lights. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getLight.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkAmbientColorAutoByDriveModeEnabledCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets Ambient Color Auto By Drive Mode Enabled capabilities. |
| `checkAmbientLightBrightnessCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets ambient light brightness capabilities. |
| `checkAmbientLightBrightnessColorCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets ambient light brightness color capabilities. |
| `checkAmbientLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, List<LightState>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets ambient light state capabilities. |
| `checkAmbientLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, List<LightSwitch>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets ambient light switch capabilities. |
| `checkCabinLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets cabin light state capabilities. |
| `checkCabinLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets cabin light switch capabilities. |
| `checkCabinLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets cabin light switch capabilities. |
| `checkFrontFogLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets front fog light state capabilities. |
| `checkFrontFogLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets front fog light switch capabilities. |
| `checkFrontFogLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets front fog light switch capabilities. |
| `checkHighBeamLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets high beam light state capabilities. |
| `checkHighBeamLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets high beam light switch capabilities. |
| `checkHighBeamLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets high beam light switch capabilities. |
| `checkLowBeamLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets low beam light state capabilities. |
| `checkLowBeamLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets low beam light switch capabilities. |
| `checkLowBeamLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets low beam light switch capabilities. |
| `checkReadingLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, List<LightState>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets reading light state capabilities. |
| `checkReadingLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, List<LightSwitch>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets reading light switch capabilities. |
| `checkReadingLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, List<LightSwitch>>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets reading light switch capabilities. |
| `checkRearFogLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets rear fog light state capabilities. |
| `checkRearFogLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets rear fog light switch capabilities. |
| `checkRearFogLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets rear fog light switch capabilities. |
| `checkTailLampLightStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightState>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets tail lamp light state capabilities. |
| `checkTailLampLightSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets tail lamp light switch capabilities. |
| `checkTailLampLightSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<LightSwitch>)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets tail lamp light switch capabilities. |
| `getAmbientColorAutoByDriveModeEnabled` | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets whether Ambient Color Auto By Drive Mode is enabled. |
| `getAmbientLightBrightness` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current Ambient Light Brightness for the specified seat area. |
| `getAmbientLightBrightnessColor` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current Ambient Light Brightness Color (32-bit ARGB) for the specified seat area. |
| `getAmbientLightState` | Get | `area: SeatArea` | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current Ambient Light State for the specified seat area. |
| `getAmbientLightSwitch` | Get | `area: SeatArea` | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current Ambient Light Switch for the specified seat area. |
| `getCabinLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current cabin light switch. |
| `getCurrentCabinLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current cabin light state. |
| `getCurrentFrontFogLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current front fog light state. |
| `getCurrentHighBeamLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current high beam light state. |
| `getCurrentLowBeamLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current low beam light state. |
| `getCurrentReadingLightState` | Get | `area: SeatArea` | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current reading light state. |
| `getCurrentRearFogLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current rear fog light state. |
| `getCurrentTailLampLightState` | Get |  | `onComplete(LightState?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current tail lamp light state. |
| `getFrontFogLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current front fog light switch. |
| `getHighBeamLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current high beam light switch. |
| `getLowBeamLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current low beam light switch. |
| `getReadingLightSwitch` | Get | `area: SeatArea` | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current reading light switch. |
| `getRearFogLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current rear fog light switch. |
| `getTailLampLightSwitch` | Get |  | `onComplete(LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current tail lamp light switch. |
| `registerAmbientColorAutoByDriveModeEnabled` | Callback |  | `AmbientColorAutoByDriveModeEnabledListener` → `onAmbientColorAutoByDriveModeEnabledUpdated(enabled: Boolean?)` | CAR_LIGHTS | Registers a listener to receive Ambient Color Auto By Drive Mode Enabled updates. |
| `registerAmbientLightBrightnessColorListener` | Callback | `area: SeatArea` | `AmbientLightBrightnessColorListener` → `onBrightnessColorUpdated(argbColor: Int?)` | CAR_LIGHTS | Registers a listener to receive Ambient Light Brightness Color updates for the specified seat area. |
| `registerAmbientLightBrightnessListener` | Callback | `area: SeatArea` | `AmbientLightBrightnessListener` → `onBrightnessUpdated(brightness: Int?)` | CAR_LIGHTS | Registers a listener to receive Ambient Light Brightness updates for the specified seat area. |
| `registerAmbientLightStateListener` | Callback | `area: SeatArea` | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers a listener to receive Ambient Light State updates for the specified seat area. |
| `registerCabinLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the CabinLightState to get property updates. |
| `registerFrontFogLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the FrontFogLightState to get property updates. |
| `registerHighBeamLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the HighBeamLightState to get property updates. |
| `registerLowBeamLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the LowBeamLightState to get property updates. |
| `registerReadingLightState` | Callback | `area: SeatArea` | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the ReadingLightState to get property updates. |
| `registerRearFogLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the RearFogLightState to get property updates. |
| `registerTailLampLightState` | Callback |  | `LightStateListener` → `onLightStateUpdated(state: LightState?)` | CAR_LIGHTS | Registers the TailLampLightState to get property updates. |
| `setAmbientColorAutoByDriveModeEnabled` | Set | `value: Boolean` | `onComplete(Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets whether Ambient Color Auto By Drive Mode is enabled. |
| `setAmbientLightBrightness` | Set | `area: SeatArea`, `value: Int` | `onComplete(SeatArea, Int)`<br>`onFailed(Exception)` | CAR_LIGHTS | Sets the Ambient Light Brightness for the specified seat area. |
| `setAmbientLightBrightnessColor` | Set | `area: SeatArea`, `value: Int` | `onComplete(SeatArea, Int)`<br>`onFailed(Exception)` | CAR_LIGHTS | Sets the Ambient Light Brightness Color for the specified seat area. |
| `setAmbientLightSwitch` | Set | `area: SeatArea`, `value: LightSwitch` | `onComplete(SeatArea, LightSwitch?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Sets the Ambient Light Switch for the specified seat area. |
| `setCabinLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the cabin light switch. |
| `setFrontFogLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the front fog light switch. |
| `setHighBeamLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the high beam light switch. |
| `setLowBeamLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the low beam light switch. |
| `setReadingLightSwitch` | Set | `area: SeatArea`, `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the reading light switch. |
| `setRearFogLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the rear fog light switch. |
| `setTailLampLightSwitch` | Set | `value: LightSwitch` | `onComplete(LightSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_LIGHTS | Sets the tail lamp light switch. |

#### Odometer — `vehicle.getOdometer()`

Provides APIs for odometer and trip data. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getOdometer.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkCurrentDistanceDrivenCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets current distance driven capabilities. |
| `checkTripCumulativeDrivingDistanceCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Cumulative Driving Distance capabilities. |
| `checkTripCumulativeDrivingTimeCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Cumulative Driving Time capabilities. |
| `checkTripCurrentDrivingDistanceCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Current Driving Distance capabilities. |
| `checkTripCurrentDrivingTimeCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Current Driving Time capabilities. |
| `checkTripDrivingDistanceCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Driving Distance capabilities. |
| `checkTripDrivingTimeCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Driving Time capabilities. |
| `checkTripInstantaneousDrivingDistanceCapabilities` *(deprecated)* | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Instantaneous Driving Distance capabilities. |
| `checkTripSinceRefillDrivingDistanceCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Since Refuel Driving Distance capabilities. |
| `checkTripSinceRefillDrivingTimeCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets Trip Since Refuel Driving Time capabilities. |
| `getCurrentDistanceDriven` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Current odometer value of the vehicle in kilometers (km). |
| `getTripCumulativeDrivingDistance` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip cumulative driving distance in meters (m). |
| `getTripCumulativeDrivingTime` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip cumulative driving time. |
| `getTripCurrentDrivingDistance` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip current driving distance in meters (m). |
| `getTripCurrentDrivingTime` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip current driving time. |
| `getTripInstantaneousDrivingDistance` *(deprecated)* | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip instantaneous driving distance in meters (m). |
| `getTripSinceRefillDrivingDistance` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip since refuel driving distance in meters (m). |
| `getTripSinceRefillDrivingTime` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets trip since refuel driving time. |
| `registerDistanceDriven` | Callback |  | `DistanceDrivenListener` → `onDistanceDrivenUpdated(value: Float?)` | CAR_DRIVE | Registers a listener for odometer updates in kilometers (km). |
| `registerTripCumulativeDrivingDistance` | Callback |  | `DistanceDrivenListener` → `onDistanceDrivenUpdated(value: Float?)` | CAR_DRIVE | Registers a listener for trip cumulative driving distance updates in meters (m). |
| `registerTripCumulativeDrivingTime` | Callback |  | `DrivingTimeListener` → `onDrivingTimeUpdated(value: Int?)` | CAR_DRIVE | Registers Trip Cumulative Driving Time listener. |
| `registerTripCurrentDrivingDistance` | Callback |  | `DistanceDrivenListener` → `onDistanceDrivenUpdated(value: Float?)` | CAR_DRIVE | Registers a listener for trip current driving distance updates in meters (m). |
| `registerTripCurrentDrivingTime` | Callback |  | `DrivingTimeListener` → `onDrivingTimeUpdated(value: Int?)` | CAR_DRIVE | Registers Trip Current Driving Time listener. |
| `registerTripInstantaneousDrivingDistance` *(deprecated)* | Callback |  | `DistanceDrivenListener` → `onDistanceDrivenUpdated(value: Float?)` | CAR_DRIVE | Registers a listener for trip instantaneous driving distance updates in meters (m). |
| `registerTripSinceRefillDrivingDistance` | Callback |  | `DistanceDrivenListener` → `onDistanceDrivenUpdated(value: Float?)` | CAR_DRIVE | Registers a listener for trip since refuel driving distance updates in meters (m). |
| `registerTripSinceRefillDrivingTime` | Callback |  | `DrivingTimeListener` → `onDrivingTimeUpdated(value: Int?)` | CAR_DRIVE | Registers Trip Since Refuel Driving Time listener. |
| `setRangeRemainingDistance` *(deprecated)* | Set | `value: Float` | `onComplete(Float?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_DRIVE | Sets the estimated remaining range in meters (m). |
| `getRangeRemainingDistance` **[미문서]** | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_ENERGY | 주행가능거리(실측 500000.0) |
| `registerRangeRemainingListener` **[미문서]** | Callback |  | `RangeRemainingListener` → `onRangeRemainingChanged(range: Float?)` | CAR_ENERGY |  |

#### Safety — `vehicle.getSafety()`

Provides APIs for airbags, warnings, fluid levels, and telltales. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSafety.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkAirbagCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets airbag capabilities. |
| `checkAirbagCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets airbag capabilities. |
| `checkAirbagDeployedCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets airbag deployed capabilities. |
| `checkBrakeFluidLevelStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<OilLevelState>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets brake fluid level state capabilities. |
| `checkEngineOilLevelStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<OilLevelState>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets engine oil level state capabilities. |
| `checkLowWasherFluidWarningStateCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets low washer fluid warning state capabilities. |
| `checkPowerDownWarningIndicatorCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets power down warning indicator capabilities. |
| `checkSeatBuckledCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets seat buckled capabilities. |
| `checkSeatBuckledCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets seat buckled capabilities. |
| `checkSeatNotBuckledWarningCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets seat not buckled warning capabilities. |
| `checkTelltalesCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | PRIVATE_CAR_SAFETY | Gets telltales capabilities. |
| `checkTransmissionOilLevelStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<OilLevelState>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets transmission oil level state capabilities. |
| `getBrakeFluidLevelState` | Get |  | `onComplete(OilLevelState?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the current brake fluid level. |
| `getEngineOilLevelState` | Get |  | `onComplete(OilLevelState?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the current engine oil level. |
| `getTelltalesInfo` | Get |  | `onComplete(telltales: Telltales)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets telltales info. |
| `getTransmissionOilLevelState` | Get |  | `onComplete(OilLevelState?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the current transmission oil level. |
| `hasLowWasherFluidWarning` | Set |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SAFETY | Checks the current washer fluid low warning status. If the status is true, there is an issue with the washer fluid |
| `hasPowerDownWarningIndicator` | Set |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the current power down warning indicator state. If the state is true, there is a problem with the engine. |
| `hasSeatAirbagBeenDeployed` | Set | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the current airbag deployment status. If the state is true, the airbag is deployed. |
| `hasSeatAirbagEnabled` | Set | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the seat airbag enabled state. If the state is true, there is a problem with the seat airbag in the area. |
| `hasSeatBeltNotBuckledWarning` | Set | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the seat belt buckled warning state. If the state is true, the seat belt in the area is not buckled. |
| `registerBrakeFluidLevelState` | Callback |  | `OilLevelStateListener` → `onOilLevelUpdated(level: OilLevelState?)` | CAR_SAFETY | Registers the BrakeFluidLevelState to get property updates. |
| `registerEngineOilLevelState` | Callback |  | `OilLevelStateListener` → `onOilLevelUpdated(level: OilLevelState?)` | CAR_SAFETY | Registers the EngineOilLevelState to get property updates. |
| `registerLowWasherFluidWaringState` | Callback |  | `LowWasherFluidWaringListener` → `onLowWasherFluidWaringUpdated(state: Boolean?)` | CAR_SAFETY | Registers the LowWasherFluidWaringState to get property updates. |
| `registerPowerDownWarningIndicator` | Callback |  | `PowerDownWarningIndicatorListener` → `onPowerDownWarningIndicatorUpdated(state: Boolean?)` | CAR_SAFETY | Registers the PowerDownWarningIndicator to get property updates. |
| `registerSeatAirbagDeployed` | Callback | `area: SeatArea` | `SeatAirbagDeployedListener` → `onSeatAirbagDeployUpdated(deployed: Boolean?)` | CAR_SAFETY | Registers the SeatAirbagDeployed to get property updates. |
| `registerSeatAirbagEnabled` | Callback | `area: SeatArea` | `SeatAirbagEnabledListener` → `onSeatAirbagEnabledUpdated(enabled: Boolean?)` | CAR_SAFETY | Registers the SeatAirbagEnabled to get property updates. |
| `registerSeatBeltNotBuckledWarning` | Callback | `area: SeatArea` | `SeatBeltNotBuckledWarningListener` → `onSeatBeltNotBuckledWarningUpdated(state: Boolean?)` | CAR_SAFETY | Registers the SeatBeltNotBuckledWarning to get property updates. |
| `registerTelltales` | Callback |  | `TelltaleInfoListener` → `onTelltaleInfoUpdated(telltales: Telltales?)` | PRIVATE_CAR_SAFETY | Registers a Telltales listener to receive telltale information updates. |
| `registerTransmissionOilLevelState` | Callback |  | `OilLevelStateListener` → `onOilLevelUpdated(level: OilLevelState?)` | CAR_SAFETY | Registers the TransmissionOilLevel to get property updates. |

#### Seat — `vehicle.getSeat()`

Provides APIs for seat position, occupancy, seat belt, and seat climate. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSeat.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkMoveSeatBackrestAngleCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat BackrestAngle capabilities. |
| `checkMoveSeatBeltHeightCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat belt height capabilities. |
| `checkMoveSeatDepthCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat depth capabilities. |
| `checkMoveSeatForeAftCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat ForeAft capabilities. |
| `checkMoveSeatHeadrestAngleCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat headrest angle capabilities. |
| `checkMoveSeatHeadrestForeAftCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat HeadrestForeAft capabilities. |
| `checkMoveSeatHeadrestHeightCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat headrest height capabilities. |
| `checkMoveSeatHeightCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat height capabilities. |
| `checkMoveSeatLumbarForeAftCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat lumbar ForeAft capabilities. |
| `checkMoveSeatLumbarSideSupportCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat lumbar side support capabilities. |
| `checkMoveSeatTiltCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets move seat tilt capabilities. |
| `checkSeatBackrestAngleCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatBackrestAnglePositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat BackrestAnglePosition capabilities. |
| `checkSeatBeltBuckledCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat belt buckled capabilities. |
| `checkSeatBeltBuckledCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat belt buckled capabilities. |
| `checkSeatBeltHeightCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatBeltHeightPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat belt height position capabilities. |
| `checkSeatDepthCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatDepthPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat depth position capabilities. |
| `checkSeatForeAftCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatForeAftPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat ForeAft position capabilities. |
| `checkSeatHeadrestAngleCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatHeadrestAnglePositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat HeadrestAnglePosition capabilities. |
| `checkSeatHeadrestForeAftCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatHeadrestForeAftPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat HeadrestForeAftPosition capabilities. |
| `checkSeatHeadrestHeightCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatHeadrestHeightPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat HeadrestHeightPosition capabilities. |
| `checkSeatHeightCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatHeightPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat height position capabilities. |
| `checkSeatLumbarForeAftCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatLumbarForeAftPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat LumbarForeAftPosition capabilities. |
| `checkSeatLumbarSideSupportCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatLumbarSideSupportPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat LumbarSideSupportPosition capabilities. |
| `checkSeatMemoryCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat memory capabilities. |
| `checkSeatOccupancyCapabilities` *(deprecated)* | Capability |  | `onComplete(List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat occupancy capabilities. |
| `checkSeatOccupancyCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<SeatArea>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat occupancy capabilities. |
| `checkSeatTemperatureCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat temperature capabilities. |
| `checkSeatTemperatureCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat temperature capabilities. |
| `checkSeatTiltCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS |  |
| `checkSeatTiltPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat tilt position capabilities. |
| `checkSeatVentilationCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat ventilation capabilities. |
| `checkSeatVentilationCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<SeatArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_SEATS | Gets seat ventilation capabilities. |
| `getCurrentSeatBackrestAnglePosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat backrest angle position. |
| `getCurrentSeatBeltBuckledState` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat belt buckled state. |
| `getCurrentSeatBeltHeightPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat belt height position. |
| `getCurrentSeatDepthPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat depth position. |
| `getCurrentSeatForeAftPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat fore/aft position. |
| `getCurrentSeatHeadrestAnglePosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat headrest angle position. |
| `getCurrentSeatHeadrestForeAftPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets current seat headrest fore/aft position. |
| `getCurrentSeatHeadrestHeightAftPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat headrest height aft position. |
| `getCurrentSeatHeightPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat height position. |
| `getCurrentSeatLumbarForeAftPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat lumbar fore/aft position. |
| `getCurrentSeatLumbarSideSupportPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the seat lumbar side support position. |
| `getCurrentSeatOccupancy` | Get | `area: SeatArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets current seat occupancy. |
| `getCurrentSeatTiltPosition` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat tilt position. |
| `getHvacSeatTemperature` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat heating/cooling temperature. |
| `getHvacSeatVentilationState` | Get | `area: SeatArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_SEATS | Gets the current seat ventilation state. |
| `moveSeatBackrestAngle` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Move seat backrestAngle. |
| `moveSeatBeltHeight` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat belt height. |
| `moveSeatDepth` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat depth. |
| `moveSeatForeAft` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat fore/aft. |
| `moveSeatHeadrestAngle` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat headrest angle. |
| `moveSeatHeadrestForeAft` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves seat headrest fore/aft. |
| `moveSeatHeadrestHeight` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves seat headrest height. |
| `moveSeatHeight` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat height. |
| `moveSeatLumbarForeAft` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves seat lumbar fore/aft. |
| `moveSeatLumbarSideSupport` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves the seat lumbar side support. |
| `moveSeatTilt` | Set | `area: SeatArea`, `value: MoveControl` | `onComplete(SeatArea, MoveControl?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Moves seat tilt. |
| `registerHvacSeatTemperature` | Callback | `area: SeatArea` | `HvacSeatTemperatureListener` → `onHvacSeatTemperatureUpdated(temperature: Int?)` | CAR_SEATS | Registers the HvacSeatTemperature to get property updates. |
| `registerHvacSeatVentilation` | Callback | `area: SeatArea` | `HvacSeatVentilationListener` → `onHvacSeatVentilationUpdated(ventilation: Int?)` | CAR_SEATS | Registers the HvacSeatVentilation to get property updates. |
| `registerSeatBackrestAnglePosition` | Callback | `area: SeatArea` | `SeatBackrestAnglePositionListener` → `onSeatBackrestAnglePositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatBackrestAnglePosition to get property updates. |
| `registerSeatBeltBuckledState` | Callback | `area: SeatArea` | `SeatBeltBuckledStateListener` → `onSeatBeltBuckledStateUpdated(state: Boolean?)` | CAR_SEATS | Registers the SeatBeltBuckledState to get property updates. |
| `registerSeatBeltHeightPosition` | Callback | `area: SeatArea` | `SeatBeltHeightPositionListener` → `onSeatBeltHeightPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatBeltHeightPosition to get property updates. |
| `registerSeatDepthPosition` | Callback | `area: SeatArea` | `SeatDepthPositionListener` → `onSeatDepthPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatDepthPosition to get property updates. |
| `registerSeatForeAftPosition` | Callback | `area: SeatArea` | `SeatForeAftPositionListener` → `onSeatForeAftPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatForeAftPosition to get property updates. |
| `registerSeatHeadrestAnglePosition` | Callback | `area: SeatArea` | `SeatHeadrestAnglePositionListener` → `onSeatHeadrestAnglePositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatHeadrestAnglePosition to get property updates. |
| `registerSeatHeadrestForeAftPosition` | Callback | `area: SeatArea` | `SeatHeadrestForeAftPositionListener` → `onSeatHeadrestForeAftPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatHeadrestForeAftPosition to get property updates. |
| `registerSeatHeadrestHeightAftPosition` | Callback | `area: SeatArea` | `SeatHeadrestHeightAftPositionListener` → `onSeatHeadrestHeightAftPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatHeadrestHeightAftPosition to get property updates. |
| `registerSeatHeightPosition` | Callback | `area: SeatArea` | `SeatHeightPositionListener` → `onSeatHeightPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatHeightPosition to get property updates. |
| `registerSeatLumbarForeAftPosition` | Callback | `area: SeatArea` | `SeatLumbarForeAftPositionListener` → `onSeatLumbarForeAftPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatLumbarForeAftPosition to get property updates. |
| `registerSeatLumbarSideSupportPosition` | Callback | `area: SeatArea` | `SeatLumbarSideSupportPositionListener` → `onSeatLumbarSideSupportPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatLumbarSideSupportPosition to get property updates. |
| `registerSeatOccupancy` | Callback | `area: SeatArea` | `SeatOccupancyListener` → `onSeatOccupancyUpdated(occupancy: Boolean?)` | CAR_SEATS | Registers the SeatOccupancy to get property updates. |
| `registerSeatTiltPosition` | Callback | `area: SeatArea` | `SeatTiltPositionListener` → `onSeatTiltPositionUpdated(position: Int?)` | CAR_SEATS | Registers the SeatTiltPosition to get property updates. |
| `selectSeatMemory` | Set | `area: SeatArea`, `value: Int` | `onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Selects seat memory. |
| `setHvacSeatTemperature` | Set | `area: SeatArea`, `value: Int` | `onComplete(SeatArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Sets the seat heating/cooling temperature. |
| `setHvacSeatVentilation` | Set | `area: SeatArea`, `value: Int` | `onComplete(SeatArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_SEATS | Sets the seat ventilation. |

#### SideMirror — `vehicle.getSideMirror()`

Provides APIs for side mirror heat. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSideMirror.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkHvacSideMirrorHeatCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<MirrorArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_MIRRORS | Gets hvac side mirror heat capabilities. |
| `checkHvacSideMirrorHeatCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<MirrorArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_MIRRORS | Gets hvac side mirror heat capabilities. |
| `getHvacSideMirrorHeatState` | Get | `area: MirrorArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_MIRRORS | Gets the current side mirror heat state. |
| `registerHvacSideMirrorHeatState` | Callback | `area: MirrorArea` | `HvacSideMirrorHeatStateListener` → `onHvacSideMirrorHeatStateUpdated(state: Int?)` | CAR_CLIMATE | Registers HvacSideMirrorHeatState state to get property updates. |
| `setHvacSideMirrorHeat` | Set | `area: MirrorArea`, `value: Int` | `onComplete(MirrorArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_MIRRORS | Sets the side mirror heat state. |

#### SteeringWheel — `vehicle.getSteeringWheel()`

Provides APIs for the front-wheel steering angle and steering wheel heating. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSteeringWheel.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkHvacSteeringWheelHeatCapabilities` *(deprecated)* | Capability |  | `onComplete(AreaBounds<Int>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets hvac steering wheel heat capabilities. |
| `checkHvacSteeringWheelHeatCapabilities` | Capability |  | `onComplete(CapabilityConfig, AreaBounds<Int>)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets hvac steering wheel heat capabilities. |
| `checkSteeringWheelAngleCapabilities` | Capability |  | `onComplete(CapabilityConfig, AreaBounds<Float>)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the capabilities for the front-wheel steering angle. |
| `getCurrentSteeringWheelAngle` | Get |  | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_DRIVE | Gets the current front-wheel steering angle in degrees. |
| `getHvacSteeringWheelHeatState` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_CLIMATE | Gets the current SteeringWheel heating/cooling state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal) |
| `registerHvacSteeringWheelHeatState` | Callback |  | `HvacSteeringWheelHeatStateListener` → `onHvacSteeringWheelHeatStateUpdated(state: Int?)` | CAR_CLIMATE | Registers Hvac SteeringWheel heat state to get property updates. |
| `registerSteeringWheelAngle` | Callback |  | `SteeringWheelAngleListener` → `onSteeringWheelAngleUpdated(angle: Float?)` | CAR_DRIVE | Registers a listener to receive front-wheel steering angle updates. |
| `setHvacSteeringWheelHeat` | Set | `value: Int` | `onComplete(Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_CLIMATE | Sets SteeringWheel heating/cooling temperature. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal) |

#### Tire — `vehicle.getTire()`

Provides APIs for tire pressure. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getTire.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkLowTirePressureCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<WheelArea>)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets low tire pressure capabilities. |
| `checkWheelAreaCapabilities` *(deprecated)* | Capability |  | `onComplete(List<WheelArea>)`<br>`onFailed(Exception)` | CAR_SAFETY |  |
| `getLowTirePressure` | Get | `area: WheelArea` | `onComplete(Float?)`<br>`onFailed(Exception)` | CAR_SAFETY | Gets the critically low tire pressure threshold in kilopascals (kPa). |
| `checkTirePressureCapabilities` **[미문서]** | Capability |  | `onComplete(CapabilityConfig, List<WheelArea>)`<br>`onFailed(Exception)` | 미확인(CAR_SAFETY로는 SecurityException) |  |
| `getTirePressure` **[미문서]** | Get | `area: WheelArea` | `onComplete(Float?)`<br>`onFailed(Exception)` | 미확인(CAR_SAFETY로는 SecurityException) |  |
| `registerTirePressure` **[미문서]** | Callback | `area: WheelArea` | `TirePressureListener` → `onTirePressureUpdated(pressure: Float?)` | 미확인(CAR_SAFETY로는 SecurityException) |  |

#### Toll — `vehicle.getToll()`

Provides APIs for ETCS voice guidance volume. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getToll.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkEtcsVoiceGuidanceVolumeCapabilities` | Capability |  | `onComplete(CapabilityConfig, AreaBounds<Int>)`<br>`onFailed(Exception)` | CAR_TOLL | Gets ETCS voice guidance volume capabilities. |
| `getCurrentEtcsVoiceGuidanceVolume` | Get |  | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_TOLL | Gets current ETCS voice guidance volume. |
| `registerEtcsVoiceGuidanceVolume` | Callback |  | `EtcsVoiceGuidanceVolumeListener` → `onEtcsVoiceGuidanceVolumeUpdated(volume: Int?)` | CAR_TOLL | Registers ETCS voice guidance volume updates. |
| `setEtcsVoiceGuidanceVolume` | Set | `value: Int` | `onComplete()`<br>`onFailed(Exception)` | CONTROL_CAR_TOLL | Sets ETCS voice guidance volume. |

#### TurnSignal — `vehicle.getTurnSignal()`

Provides APIs for turn signal state. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getTurnSignal.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkTurnSignalStateCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets turn signal state capabilities. |
| `getCurrentTurnSignalState` | Get |  | `onComplete(TurnSignalValue?)`<br>`onFailed(Exception)` | CAR_LIGHTS | Gets the current turn signal state. |
| `registerTurnSignalState` | Callback |  | `TurnSignalStateListener` → `onTurnSignalStateUpdated(state: TurnSignalValue?)` | CAR_LIGHTS | Registers the turn signal state to get property updates. |

#### Window — `vehicle.getWindow()`

Provides APIs for window position, lock, blinds, and smart glass. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getWindow.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkMoveWindowBlindCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<WindowArea>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets move window blind capabilities. |
| `checkSmartGlassLevelCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<WindowArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets smart glass level capabilities. |
| `checkSmartGlassStateCapabilities` | Capability |  | `onComplete(CapabilityConfig)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets smart glass state capabilities. |
| `checkSmartGlassZoneStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<WindowArea>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets smart glass zone state capabilities. |
| `checkWindowBlindPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<WindowArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets window blind position capabilities. |
| `checkWindowLockCapabilities` *(deprecated)* | Capability |  | `onComplete(List<WindowArea>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets window lock capabilities. |
| `checkWindowLockCapabilities` | Capability |  | `onComplete(CapabilityConfig, List<WindowArea>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets window lock capabilities. |
| `checkWindowPositionCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<WindowArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets window position capabilities. |
| `checkWindowPositionCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<WindowArea, AreaBounds<Int>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets window position capabilities. |
| `getCurrentSmartGlassLevel` | Get | `area: WindowArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current Smart Glass Level for the specified window area. |
| `getCurrentSmartGlassState` | Get |  | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current Smart Glass state. |
| `getCurrentWindowBlindPosition` | Get | `area: WindowArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current Window Blind Position for the specified window area. |
| `getCurrentWindowLockState` | Get | `area: WindowArea` | `onComplete(Boolean?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current window lock state. |
| `getCurrentWindowPosition` | Get | `area: WindowArea` | `onComplete(Int?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current window position. |
| `getSmartGlassConfig` | Get | `area: WindowArea` | `onComplete(SmartGlassConfig?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the Smart Glass configuration for the specified window area. |
| `moveWindowBlind` | Set | `area: WindowArea`, `value: MoveControl` | `onComplete(WindowArea, MoveControl?)`<br>`onFailed(Exception)` | CONTROL_CAR_WINDOWS | Moves the Window Blind for the specified window area. |
| `registerSmartGlassLevel` | Callback | `area: WindowArea` | `SmartGlassLevelListener` → `onSmartGlassLevelUpdated(level: Int?)` | CAR_WINDOWS | Registers a listener to receive Smart Glass Level updates for the specified window area. |
| `registerSmartGlassState` | Callback |  | `SmartGlassStateListener` → `onSmartGlassStateUpdated(state: Boolean?)` | CAR_WINDOWS | Registers Smart Glass state listener. |
| `registerWindowBlindPosition` | Callback | `area: WindowArea` | `WindowPositionListener` → `onWindowPositionUpdated(position: Int?)` | CAR_WINDOWS | Registers a listener to receive Window Blind Position updates for the specified window area. |
| `registerWindowLockState` | Callback | `area: WindowArea` | `WindowLockStateListener` → `onWindowLockStateUpdated(state: Boolean?)` | CAR_WINDOWS | Registers the window lock state to get property updates. |
| `registerWindowPosition` | Callback | `area: WindowArea` | `WindowPositionListener` → `onWindowPositionUpdated(position: Int?)` | CAR_WINDOWS | Registers the window area to get property updates. |
| `setSmartGlassLevel` | Set | `area: WindowArea`, `value: Int` | `onComplete(WindowArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the Smart Glass Level for the specified window area. |
| `setSmartGlassState` | Set | `value: Boolean` | `onComplete(Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the Smart Glass state. |
| `setSmartGlassZoneState` | Set | `area: WindowArea`, `value: List<SmartGlassZoneCommand>` | `onComplete(WindowArea, List<SmartGlassZoneCommand>?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the Smart Glass zone state by submitting one command per smart glass panel for the specified window area. |
| `setWindowLock` | Set | `area: WindowArea`, `value: Boolean` | `onComplete(WindowArea, Boolean?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the current window lock state. |
| `setWindowPosition` | Set | `area: WindowArea`, `value: Int` | `onComplete(WindowArea, Int?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the current window position state. |

#### Wiper — `vehicle.getWiper()`

Provides APIs for wiper state and switch. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getWiper.

| API | 종류 | 추가 인자 | 콜백 | 권한 | 설명 |
|---|---|---|---|---|---|
| `checkWiperStateCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<WindowArea, List<WiperState>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets wiper state capabilities. |
| `checkWiperStateCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<WindowArea, List<WiperState>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets wiper state capabilities. |
| `checkWiperSwitchCapabilities` *(deprecated)* | Capability |  | `onComplete(Map<WindowArea, List<WiperSwitch>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets wiper switch capabilities. |
| `checkWiperSwitchCapabilities` | Capability |  | `onComplete(CapabilityConfig, Map<WindowArea, List<WiperSwitch>>)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets wiper switch capabilities. |
| `getCurrentWiperState` | Get | `area: WindowArea` | `onComplete(WiperState?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current wiper mode state. |
| `getCurrentWiperSwitch` | Get | `area: WindowArea` | `onComplete(WiperSwitch?)`<br>`onFailed(Exception)` | CAR_WINDOWS | Gets the current Wiper Switch state for the specified window area. |
| `registerWiperState` | Callback | `area: WindowArea` | `WiperStateListener` → `onWiperStateUpdated(state: WiperState?)` | CAR_WINDOWS | Registers the wiper state to get property updates. |
| `setWiperSwitch` | Set | `area: WindowArea`, `value: WiperSwitch` | `onComplete(WindowArea, WiperSwitch?)` *(선택)*<br>`onFailed(Exception)` *(선택)* | CONTROL_CAR_WINDOWS | Sets the current wiper switch. |

### 1-4. 리스너 전체 목록

모든 리스너는 `VehicleListener`를 상속하며, 값 콜백 외에 `onFailed(e: Exception)`를 함께 구현해야 합니다.

| 리스너 | 콜백 메서드 | 설명 |
|---|---|---|
| `AmbientColorAutoByDriveModeEnabledListener` | `onAmbientColorAutoByDriveModeEnabledUpdated(enabled: Boolean?)` | Callback invoked when AmbientColorAutoByDriveModeEnabled updates. |
| `AmbientLightBrightnessColorListener` | `onBrightnessColorUpdated(argbColor: Int?)` | Callback invoked when AmbientLightBrightnessColor updates. |
| `AmbientLightBrightnessListener` | `onBrightnessUpdated(brightness: Int?)` | Callback invoked when AmbientLightBrightness updates. |
| `BrakePedalStatusListener` | `onBrakePedalStatusUpdated(status: Boolean?)` | Callback invoked when BrakePedalStatus updates. |
| `CruiseControlStateListener` | `onCruiseControlStateUpdated(state: CruiseControl?)` | Callback invoked when the CruiseControlState updates. |
| `CruiseControlTargetSpeedListener` | `onCruiseControlTargetSpeedUpdated(speed: Float?)` | Called with the updated cruise control target speed in meters per second (m/s). |
| `DistanceDrivenListener` | `onDistanceDrivenUpdated(value: Float?)` | Called with the updated distance in kilometers (km) or meters (m), depending on the registration API. |
| `DoorLockStateListener` | `onDoorLockStateUpdated(state: Boolean?)` | Callback invoked when the door lock state updates. |
| `DoorPositionListener` | `onPositionUpdated(pos: Int?)` | Callback invoked when the door position updates. |
| `DrivingModeStateListener` | `onDrivingModeStateUpdated(state: DrivingModeType?)` | Callback invoked when the DrivingModeState updates. |
| `DrivingTimeListener` | `onDrivingTimeUpdated(value: Int?)` | Callback invoked when driving time updates. |
| `EtcsVoiceGuidanceVolumeListener` | `onEtcsVoiceGuidanceVolumeUpdated(volume: Int?)` | Callback invoked when ETCS voice guidance volume updates. |
| `EvBatteryEfficiencyListener` | `onEvBatteryEfficiencyUpdated(efficiency: Float?)` | Callback invoked when EV battery efficiency updates. |
| `EvBatteryLevelListener` **[미문서]** | `onEvBatteryLevelUpdated(level: Float?)` | |
| `EvBatteryStateOfChargeListener` | `onEvBatteryStateOfChargeUpdated(value: Int?)` | Callback invoked with the updated EV battery state of charge as a percentage (%). |
| `EvChargePortOpenListener` **[미문서]** | `onEvChargePortOpenUpdated(open: Boolean?)` | |
| `FuelEconomyListener` | `onFuelEconomyUpdated(efficiency: Float?)` | Callback invoked when fuel economy updates. |
| `FuelPercentListener` | `onFuelPercentUpdated(value: Int?)` | Callback invoked with the updated fuel level as a percentage (%). |
| `GearSelectionListener` **[미문서]** | `onSelected(gear: VehicleGear?)` | |
| `GloveBoxLockStateListener` | `onGloveBoxLockStateUpdated(state: Boolean?)` | Callback invoked when the GloveBox lock state updates. |
| `HvacAcStateListener` | `onHvacAcStateUpdated(state: Boolean?)` | Callback invoked when HvacAcState updates. |
| `HvacAirCleanerStateListener` | `onHvacAirCleanerStateUpdated(state: Boolean?)` | Callback invoked when HvacAirCleanerState updates. |
| `HvacAutoStateListener` | `onHvacAutoStateUpdated(state: Boolean?)` | Callback invoked when HvacAutoState updates. |
| `HvacDefrosterStateListener` | `onHvacDefrosterStateUpdated(state: Boolean?)` | Callback invoked when HvacDefrosterState updates. |
| `HvacDualStateListener` | `onHvacDualStateUpdated(state: Boolean?)` | Callback invoked when HvacDualState updates. |
| `HvacFanSpeedListener` | `onHvacFanSpeedUpdated(speed: Int?)` | Callback invoked when HvacFanSpeed updates. |
| `HvacMaxAcStateListener` | `onHvacMaxAcStateUpdated(state: Boolean?)` | Callback invoked when HvacMaxAcState updates. |
| `HvacMaxDefrostStateListener` | `onHvacMaxDefrostStateUpdated(state: Boolean?)` | Callback invoked when HvacMaxDefrostState updates. |
| `HvacPowerStateListener` | `onHvacPowerStateUpdated(state: Boolean?)` | Callback invoked when HvacPowerState updates. |
| `HvacRecirculationStateListener` | `onHvacAutoRecirculationStateUpdated(state: Boolean?)` | Callback invoked when HvacAutoRecirculationState updates. |
| `HvacSeatTemperatureListener` | `onHvacSeatTemperatureUpdated(temperature: Int?)` | Callback invoked when HvacSeatTemperature updates. |
| `HvacSeatVentilationListener` | `onHvacSeatVentilationUpdated(ventilation: Int?)` | Callback invoked when HvacSeatVentilation updates. |
| `HvacSideMirrorHeatStateListener` | `onHvacSideMirrorHeatStateUpdated(state: Int?)` | Callback invoked when HvacSideMirrorHeatState updates. |
| `HvacSteeringWheelHeatStateListener` | `onHvacSteeringWheelHeatStateUpdated(state: Int?)` | Callback invoked when the HvacSteeringWheelHeatState updates. |
| `HvacTemperatureListener` | `onHvacTemperatureUpdated(temperature: Float?)` | Called with the updated HVAC temperature in degrees Celsius (°C). |
| `HvacTemperatureValueListener` | `onHvacTemperatureUpdated(celsius: Float?, fahrenheit: Float?)` | Callback invoked when HVAC temperature updates. |
| `LightStateListener` | `onLightStateUpdated(state: LightState?)` | Callback invoked when HighBeamLightState updates. |
| `LowWasherFluidWaringListener` | `onLowWasherFluidWaringUpdated(state: Boolean?)` | Callback invoked when the LowWasherFluidWaring updates. |
| `OilLevelStateListener` | `onOilLevelUpdated(level: OilLevelState?)` | Callback invoked when the OilLevelState updates. |
| `PowerDownWarningIndicatorListener` | `onPowerDownWarningIndicatorUpdated(state: Boolean?)` | Callback invoked when the PowerDownWarningIndicator updates. |
| `RangeRemainingListener` **[미문서]** | `onRangeRemainingChanged(range: Float?)` | |
| `SeatAirbagDeployedListener` | `onSeatAirbagDeployUpdated(deployed: Boolean?)` | Callback invoked when SeatAirbagDeployed updates. |
| `SeatAirbagEnabledListener` | `onSeatAirbagEnabledUpdated(enabled: Boolean?)` | Callback invoked when SeatAirbagEnabled updates. |
| `SeatBackrestAnglePositionListener` | `onSeatBackrestAnglePositionUpdated(position: Int?)` | Callback invoked when SeatBackrestAnglePosition updates. |
| `SeatBeltBuckledStateListener` | `onSeatBeltBuckledStateUpdated(state: Boolean?)` | Callback invoked when SeatBeltBuckledState updates. |
| `SeatBeltHeightPositionListener` | `onSeatBeltHeightPositionUpdated(position: Int?)` | Callback invoked when SeatBeltHeightPosition updates. |
| `SeatBeltNotBuckledWarningListener` | `onSeatBeltNotBuckledWarningUpdated(state: Boolean?)` | Callback invoked when SeatBeltNotBuckledWarning updates. |
| `SeatDepthPositionListener` | `onSeatDepthPositionUpdated(position: Int?)` | Callback invoked when SeatDepthPosition updates. |
| `SeatForeAftPositionListener` | `onSeatForeAftPositionUpdated(position: Int?)` | Callback invoked when SeatForeAftPosition updates. |
| `SeatHeadrestAnglePositionListener` | `onSeatHeadrestAnglePositionUpdated(position: Int?)` | Callback invoked when SeatHeadrestAnglePosition updates. |
| `SeatHeadrestForeAftPositionListener` | `onSeatHeadrestForeAftPositionUpdated(position: Int?)` | Callback invoked when SeatHeadrestForeAftPosition updates. |
| `SeatHeadrestHeightAftPositionListener` | `onSeatHeadrestHeightAftPositionUpdated(position: Int?)` | Callback invoked when SeatHeadrestHeightAftPosition updates. |
| `SeatHeightPositionListener` | `onSeatHeightPositionUpdated(position: Int?)` | Callback invoked when SeatHeightPosition updates. |
| `SeatLumbarForeAftPositionListener` | `onSeatLumbarForeAftPositionUpdated(position: Int?)` | Callback invoked when SeatLumbarForeAftPosition updates. |
| `SeatLumbarSideSupportPositionListener` | `onSeatLumbarSideSupportPositionUpdated(position: Int?)` | Callback invoked when SeatLumbarSideSupportPosition updates. |
| `SeatOccupancyListener` | `onSeatOccupancyUpdated(occupancy: Boolean?)` | Callback invoked when SeatOccupancy updates. |
| `SeatTiltPositionListener` | `onSeatTiltPositionUpdated(position: Int?)` | Callback invoked when SeatTiltPosition updates. |
| `SmartGlassLevelListener` | `onSmartGlassLevelUpdated(level: Int?)` | Callback invoked when SmartGlass Level updates. |
| `SmartGlassStateListener` | `onSmartGlassStateUpdated(state: Boolean?)` | Callback invoked when SmartGlassState updates. |
| `SteeringWheelAngleListener` | `onSteeringWheelAngleUpdated(angle: Float?)` | Called when a front-wheel steering angle update is available. |
| `TelltaleInfoListener` | `onTelltaleInfoUpdated(telltales: Telltales?)` | Callback invoked when TelltaleInfo updates. |
| `TemperatureDisplayUnitListener` | `onTemperatureDisplayUnitUpdated(unit: TemperatureUnit?)` | Callback invoked when TemperatureDisplayUnit updates. |
| `TirePressureListener` **[미문서]** | `onTirePressureUpdated(pressure: Float?)` | |
| `TripDrivingDistanceListener` | `onTripDrivingDistanceUpdated(statistics: TripStatistics<Float>?)` | Callback invoked when trip driving distance updates. |
| `TripDrivingTimeListener` | `onTripDrivingTimeUpdated(statistics: TripStatistics<Int>?)` | Callback invoked when trip driving time updates. |
| `TripEvEfficiencyListener` | `onTripEvEfficiencyUpdated(statistics: TripStatistics<Float>?)` | Callback invoked when trip EV efficiency updates. |
| `TripFuelEconomyListener` | `onTripFuelEconomyUpdated(statistics: TripStatistics<Float>?)` | Callback invoked when trip fuel economy updates. |
| `TurnSignalStateListener` | `onTurnSignalStateUpdated(state: TurnSignalValue?)` | Callback invoked when TurnSignalStates updates. |
| `WindowLockStateListener` | `onWindowLockStateUpdated(state: Boolean?)` | Callback invoked when WindowLockState updates. |
| `WindowPositionListener` | `onWindowPositionUpdated(position: Int?)` | Callback invoked when WindowPosition updates. |
| `WiperStateListener` | `onWiperStateUpdated(state: WiperState?)` | Callback invoked when WiperState updates. |

- 공통(`VehicleListener`): `onFailed(e: Exception)` — Callback invoked when a exception occurs.

---

## 2. NaviHelper SDK

NaviHelper는 Vehicle과 달리 **API 호출이 결과를 직접 돌려주지 않습니다.** 모든 결과·상태는 `addListener(NaviHelperEventListener)`로 등록한 리스너로 옵니다. 리스너 메서드는 모두 기본 구현이 있는 `open fun`이라 필요한 것만 재정의하면 됩니다.

### 2-1. 실행·해제

| 호출 | 콜백 | 비고 |
|---|---|---|
| `NaviHelper(context).initialize(onConnected, onFailed)` | `onConnected()` / `onFailed(Throwable)` | 첫 서비스 연결 성공 시 `onConnected`, 초기 연결 재시도를 모두 실패하면 `onFailed`. 실측: `onConnected` 이후 리스너 등록·API 호출 |
| `initialize()` | 없음 | 콜백 없는 오버로드 |
| `addListener(listener)` / `removeListener(listener)` | — | 결과 수신 리스너 등록/해제 |
| `release()` | 없음 | |

### 2-2. 요청 → 결과 콜백

콜백 대응은 이름과 문서 설명으로 짝지은 것입니다. 실측으로 확인한 것은 비고에 적었고, 나머지는 문서의 「Navigation 앱의 구현 시나리오에 따라 전달되는 이벤트가 달라질 수 있습니다」를 전제로 보세요. 실패하면 공통으로 `onError(errorCode: NaviErrorCode)`가 옵니다.

| 호출 | 인자 | 결과 콜백 | 권한 | 비고 |
|---|---|---|---|---|
| `addWaypoint` | `requestWaypointInfo: RequestWaypointInfo` | `onWaypointChanged` | NAVI_ROUTE_SEARCH | Adds waypoint. |
| `cancelRoute` |  | `onRouteCancelled` | NAVI_ROUTE_SEARCH | 실측: `onRouteCancelled` → `onDestinationInfo`(빈 값) 수신 |
| `changeRouteOption` | `routeOption: RouteOption` | `onRouteOptionChanged` | NAVI_ROUTE_SEARCH | 실측: `onRouteOptionChanged` 대신 **[미문서]** `onRouteEnded` 수신 |
| `getBookmarkInfo` |  | `onBookmarkInfo` | NAVI_ROUTE | Gets bookmark info. |
| `getChargerOperatorInfo` |  | `onChargerOperatorInfo` | NAVI_ROUTE | Gets Charger Operator info. |
| `getCurrentLocationInfo` |  | `onCurrentLocationInfo` | NAVI_ROUTE | Gets current location info. |
| `getDestinationInfo` |  | `onDestinationInfo` | NAVI_ROUTE | 실측: 경로 시작 전에는 빈 값(위경도 0), 시작 후 정상 값 |
| `getRecentDestinationInfo` |  | `onRecentDestinationInfo` | NAVI_ROUTE | Gets recent destination info. |
| `getRouteStateInfo` |  | `onRouteStateInfo` | NAVI_ROUTE | Gets route state info. |
| `getTBTInfo` |  | `onTBTInfo` | NAVI_ROUTE | Gets TBT info. |
| `getWaypointInfo` |  | `onWayPointInfo` | NAVI_ROUTE | Gets waypoint info. |
| `removeWaypoint` | `waypointIndex: WaypointIndex` | `onWaypointChanged` | NAVI_ROUTE_SEARCH | Removes waypoint. |
| `requestNavigationScreen` | `requestScreen: NavigationScreenRequest` | 전용 콜백 없음(내비 화면 전환) | NAVI_CUSTOM_MAP | Requests navigation screen. |
| `requestReRoute` |  | `onReRouteEnded` | NAVI_ROUTE_SEARCH | Requests re-route. |
| `requestRoute` | `routeInfo: RouteInfo` | `onRouteStarted` → `onRoutingCompleted` | NAVI_ROUTE_SEARCH | 실측: 약 9초 뒤 `onRouteStarted` 수신, `onRoutingCompleted`는 오지 않음 |
| `requestRouteWithWaypoints` | `request: RouteWithWaypointsRequest` | `onRouteStarted` → `onRoutingCompleted` | NAVI_ROUTE_SEARCH | Requests a route with waypoints. |
| `requestTrip` | `tripRequest: TripRequest` | `onTripInfo` | NAVI_ROUTE | Requests trip. |

### 2-3. `NaviHelperEventListener` 전체 콜백

| 콜백 | 받는 값 | 언제 | 분류 |
|---|---|---|---|
| `onBookMarkChanged` | — | Called when arriving at or removing the bookmark. | 정보 조회·알림 |
| `onBookmarkInfo` | `bookmarkInfoList: List<BookmarkInfo>` | Called when the bookmark information is changed. | 정보 조회·알림 |
| `onCameraAlert` | `cameraAlertList: List<CameraAlert>` | Called when camera alerts are notified. Received when speeding camera exists. | 정보 조회·알림 |
| `onCautionPointInfo` | `cautionPointList: List<CautionPoint>` | Called when caution point information is notified. | 정보 조회·알림 |
| `onChargerOperatorInfo` | `chargerOperatorList: List<ChargerOperator>` | Called when charger operator information is notified. | 정보 조회·알림 |
| `onCurrentLocationInfo` | `currentLocationInfo: CurrentLocationInfo` | Called when the current location information is changed. | 정보 조회·알림 |
| `onCurrentRoadSpeedLimit` | `speedLimit: Int` | Called when the road speed limit is notified. Received every second after the event listener registration. | 정보 조회·알림 |
| `onDestinationArrived` | `destinationArrivedInfo: DestinationArrivedInfo` | Called when arriving at the destination. | 목적지·경유지 |
| `onDestinationChanged` | — | Called when arriving at or removing the destination. | 목적지·경유지 |
| `onDestinationInfo` | `destinationInfo: DestinationInfo` | Called when the destination information is changed. | 정보 조회·알림 |
| `onDrivingInfo` | `drivingInfo: DrivingInfo` | Called when driving information is notified. | 정보 조회·알림 |
| `onError` | `errorCode: NaviErrorCode` | Called when a navigation error occurs. | 오류 |
| `onNavigationStatus` | `status: NavigationStatus` | Called when the navigation status is notified. Received whenever the driving status changes | 정보 조회·알림 |
| `onRecentDestinationInfo` | `recentDestinationInfoList: List<RecentDestinationInfo>` | Called when the recent destination information is changed. | 정보 조회·알림 |
| `onReRouteEnded` | — | Called when navigation re-route is completed. | 경로 |
| `onRouteCancelled` | — | Called when the current route is canceled. | 경로 |
| `onRouteOptionChanged` | `routeOption: RouteOption` | Called when the route type is changed. | 경로 |
| `onRouteStarted` | `routeStartInfo: RouteStartInfo` | Called when navigation route search begins. | 경로 |
| `onRouteStateInfo` | `routeStateInfo: RouteStateInfo` | Called when the route state information is changed. | 정보 조회·알림 |
| `onRoutingCompleted` | — | Called when navigation route search is completed. | 경로 |
| `onTBTInfo` | `tbtInfoList: List<TBTInfo>` | Called when the TBT information is notified. | 정보 조회·알림 |
| `onTripInfo` | `tripInfo: TripInfo` | Called when trip information is notified. | 정보 조회·알림 |
| `onWayPointArrived` | `waypointArrivedInfo: WaypointArrivedInfo` | Called when arriving at the waypoint. | 목적지·경유지 |
| `onWaypointChanged` | `waypointChangedInfo: WaypointChangedInfo` | Called when arriving at or removing the waypoint. | 목적지·경유지 |
| `onWayPointInfo` | `waypointInfoList: List<WaypointInfo>` | Called when the waypoint information is changed. | 정보 조회·알림 |
| `onRouteEnded` **[미문서]** | — | 사이트 문서에 없음. 실측: `changeRouteOption` 직후 수신 | 경로 |

> **문서 오류**: 05 「Waypoints」 Kotlin 예제의 `override fun onWaypointInfo(...)`는 실제 메서드 이름이 `onWayPointInfo`(대문자 P)라서 컴파일되지 않습니다. 반면 `onWaypointChanged`는 소문자 p가 맞습니다.

---

## 3. TextToSpeech SDK

`initialize(OnInitializeListener)`로 초기화 결과를, `registerApp(…, OnServerConnectionListener)`로 서버 연결 결과를, `addEventListener(EventListener)`로 합성 진행 상태를 받습니다. 순서는 `initialize()` → `addEventListener()`입니다. 거꾸로 하면 실측에서 `UninitializedPropertyAccessException`이 났습니다.

### 3-1. 호출

| 호출 | 결과를 받는 리스너 | 반환값 | 권한 | 설명 |
|---|---|---|---|---|
| `addEventListener(listener)` | `EventListener` | — | TTS_SERVICE | Adds a listener that will be notified of various events related to TextToSpeech |
| `initialize()` | — | — | TTS_SERVICE | Initializes TextToSpeech SDK When the app starts or is created, it should call the initialization of SDK |
| `initialize(listener)` | `OnInitializeListener` | — | TTS_SERVICE | Initializes TextToSpeech SDK with a listener for initialization result. When the app starts or is created, it should call the initialization of SDK. |
| `registerApp(clientID, clientSecret, listener)` | `OnServerConnectionListener` | — | TTS_SERVICE | Register app |
| `release()` | — | — | TTS_SERVICE | Releases TextToSpeech SDK When the app terminates or is destroyed, it should call the release of SDK |
| `removeEventListener(listener)` | `EventListener` | — | TTS_SERVICE | Removes a previously registered listener |
| `setAudioAttributes(audioAttributes)` | — | — | TTS_SERVICE | Sets the audio attributes to be used for TextToSpeech SDK |
| `setAudioZoneId(zoneId)` | — | — | TTS_SERVICE | Update audio zone id that user want to set. Requires the caller to have TTS_PERMISSION. |
| `setLocale(locale)` | — | — |  | Updates the locale that the user wants to set. Requires the caller to have TTS_PERMISSION. |
| `setSpeakerType(type)` | — | — |  | Updates the speaker type (voice) used for speech synthesis. Requires the caller to have TTS_PERMISSION. If this method is not called, the speaker previously saved for the app is used. If no speaker has been saved for the app, the Gleo AI speaker setting is used. If neither is set, haeun is used. |
| `speak(text, isFinal)` | — | — | TTS_SERVICE | Converts Text-To-Speech and speaks the text |
| `stop()` | — | — | TTS_SERVICE | Interrupts the current TextToSpeech playback |

### 3-2. 리스너 콜백

| 리스너 | 콜백 | 설명 |
|---|---|---|
| `EventListener` | `onDone()` | Called when TextToSpeech has successfully completed processing. |
| `EventListener` | `onError(errorType: ErrorType)` | Called when an error has occurred during processing. |
| `EventListener` | `onReady()` | Called when TextToSpeech speak has been called. |
| `EventListener` | `onStart()` | Called when TextToSpeech starts as perceived by the caller. |
| `EventListener` | `onStop()` | Called when TextToSpeech stops while in progress. |
| `EventListener` | `onUpdatedRms(rms: Double)` | Called when RMS is updated. |
| `OnInitializeListener` | `onFailure(errorType: ErrorType)` | Called when the SDK initialization has failed. |
| `OnInitializeListener` | `onSuccess()` | Called when the SDK has been successfully initialized and bound to the TTS service. |
| `OnServerConnectionListener` | `onConnected()` | Called when connected to the server |
| `OnServerConnectionListener` | `onFailed(errorType: ErrorType)` | Called when failure to connection to server |

- 실측(`Mode.ON_DEVICE`): `initialize` → `onSuccess` → `speak` → `onReady` → `onStart` → `onDone` 순서로 수신했습니다. `SERVER`/`HYBRID` 모드는 Client ID/Secret이 없어 확인하지 못했습니다.

---

## 4. SpeechToText SDK

`initialize(OnInitializeListener)`로 초기화 결과를, `registerApp(…, OnServerConnectionListener)`로 서버 연결 결과를, `addListener(ResultListener)`로 인식 진행·결과를 받습니다.

### 4-1. 호출

| 호출 | 결과를 받는 리스너 | 반환값 | 권한 | 설명 |
|---|---|---|---|---|
| `addListener(listener)` | `ResultListener` | — | STT_SERVICE | Adds a listener that will be notified of various events related to SpeechToText Requires the caller to have STT_PERMISSION. |
| `completeAudioSend()` | — | — | STT_SERVICE | Completes the external audio buffer input started by sendAudio and finishes recognition. Available only in Mode.ON_DEVICE. In other modes the call is ignored. Requires the caller to have STT_PERMISSION. |
| `initialize()` | — | — | STT_SERVICE | Initializes SDK to use SpeechToText whether the application is created or not. Requires the caller to have STT_PERMISSION. |
| `initialize(listener)` | `OnInitializeListener` | — | STT_SERVICE | Initializes SDK to use SpeechToText with a listener for initialization result. Requires the caller to have STT_PERMISSION. |
| `registerApp(clientID, clientSecret, listener)` | `OnServerConnectionListener` | — | STT_SERVICE | Registers the application to use the server-oriented or hybrid SpeechToText service. Requires the caller to have STT_PERMISSION. |
| `release()` | — | — | STT_SERVICE | Releases SDK whether the application is destroyed or not. Requires the caller to have STT_PERMISSION. |
| `removeListener(listener)` | `ResultListener` | — | STT_SERVICE | Removes a previously registered listener Requires the caller to have STT_PERMISSION. |
| `request()` | — | — | STT_SERVICE | Requests for starting speech recognition. After the application invoke request(), microphone will be open, and then you can stream your audio through microphone. |
| `sendAudio(bytes)` | — | — | STT_SERVICE | Sends an external audio buffer (PCM) for recognition. Available only in Mode.ON_DEVICE. In other modes the call is ignored and ResultListener.onError is not called. Recognition starts when the first buffer is sent; calling request is not required. In this path, ResultListener.onReady and ResultListener.onUpdatedRms are not called. Call completeAudioSend after sending the last buffer. Requires the caller to have STT_PERMISSION. |
| `setLocale(locale)` | — | — |  | Updates the locale that the user wants to set. Requires the caller to have STT_PERMISSION. |
| `stop()` | — | — | STT_SERVICE | Stops speech recognition. After the application invoke stop(), the microphone will be disabled. |

### 4-2. 리스너 콜백

| 리스너 | 콜백 | 설명 |
|---|---|---|
| `OnInitializeListener` | `onFailure(errorType: ErrorType)` | Called when the SDK initialization has failed. |
| `OnInitializeListener` | `onSuccess()` | Called when the SDK has been successfully initialized and bound to the STT service. |
| `OnServerConnectionListener` | `onConnected()` | Called when connected to the server |
| `OnServerConnectionListener` | `onFailed(errorType: ErrorType)` | Called when failure to connection to server |
| `ResultListener` | `onEndedRecognition()` | Called when caller recognition is finished. |
| `ResultListener` | `onError(errorType: ErrorType)` | Called when caller recognition fails. |
| `ResultListener` | `onReady()` | Called when listener recognition is ready. |
| `ResultListener` | `onStartedRecognition()` | Called when caller recognition is started. |
| `ResultListener` | `onUpdated(stt: String, completed: Boolean)` | Called when the SpeechToText result is updated. |
| `ResultListener` | `onUpdatedEpdData(on: Long, off: Long)` | Called when the SpeechToText EPD result is updated. |
| `ResultListener` | `onUpdatedRms(rms: Double)` | Called when RMS is updated. |

- 실측(`Mode.ON_DEVICE`): `initialize` → `onSuccess` → `request()` → `onReady`까지 수신했습니다. 마이크 입력이 없어 `onUpdated` 이후는 확인하지 못했습니다.

---

## 5. FusedLocation SDK

`initialize()`, `isSupported()`, `release()`는 동기 호출이고 콜백이 없습니다. 위치는 `registerFusedLocationCallback(FusedLocationListener)`로, 근접 알림은 `addProximityAlert(request, ProximityAlertListener)`로 받습니다.

### 5-1. 호출

| 호출 | 결과를 받는 리스너 | 반환값 | 권한 | 설명 |
|---|---|---|---|---|
| `addProximityAlert(request, listener)` | `ProximityAlertListener` | — | FUSED_LOCATION | Registers a proximity alert for the specified area and transitions. |
| `getLastKnownLocation(callback)` | `FusedLocationListener` | — | FUSED_LOCATION | Gets the last known location. |
| `initialize()` | — | — |  | Initializes the SDK. |
| `isSupported()` | — | `Boolean` |  | Returns whether the Fused Location Service package is present on Pleos Connect. |
| `registerFusedLocationCallback(listener)` | `FusedLocationListener` | — | FUSED_LOCATION | Registers a callback to receive location updates. |
| `release()` | — | — |  | Releases the SDK. |
| `removeProximityAlert(alertId)` | — | — | FUSED_LOCATION | Removes a previously registered proximity alert. |
| `showLocationAccessBlockedPopup()` | — | — |  | Displays a popup message informing the user that location access is blocked due to their current privacy settings. |
| `unregisterFusedLocationCallback(listener)` | `FusedLocationListener` | — | FUSED_LOCATION | Stops receiving location updates through the specified listener. |

### 5-2. 리스너 콜백

| 리스너 | 콜백 | 설명 |
|---|---|---|
| `FusedLocationListener` | `onChanged(location: LocationData?)` | Invoked when a location update is available. |
| `FusedLocationListener` | `onError(exception: Exception)` |  |
| `ProximityAlertListener` | `onError(exception: Exception)` | Invoked when the alert cannot be registered or processed. |
| `ProximityAlertListener` | `onRegistered(alertId: String)` | Invoked when the alert is registered successfully. |
| `ProximityAlertListener` | `onTransition(event: ProximityAlertEvent)` | Invoked when a configured proximity transition is detected. |

- 실측: `isSupported()=true`. `onChanged`가 약 1초 간격으로 왔고(위도 37.5037, 경도 127.1040, confidence 5.0), `addProximityAlert` 직후 `onRegistered(alertId)`가 왔습니다.
- [미문서] AAR에 `exception.LocationException`과 `constant.ErrorCode`가 있어, `onError(exception)`로 이 타입이 올 가능성이 있습니다(미확인).

---

## 6. 오류 값 정리

### NaviHelper `NaviErrorCode` (`onError`)

| 값 | 설명 |
|---|---|
| `CAUSE_LOW_BATTERY` | Low battery alert |
| `CAUSE_SEARCH_ROUTE_OVER_DISTANCE` | Error: route path over max distance |
| `CAUSE_SEARCH_ROUTE` | Error: route server side |
| `CAUSE_SEARCH_ROUTE_TOO_SHORT_DISTANCE` | Error: distance between current location and destination is too short |
| `CAUSE_SEARCH_TRIP` | Error: trip server side |

### TextToSpeech `ErrorType` (`onFailure`, `onFailed`, `onError`)

| 값 | 설명 |
|---|---|
| `AUDIO_ERROR` | Audio playback/output related error. |
| `MIC_ERROR` | Microphone input/recording related error. |
| `SERVER_INTERNAL_ERROR` | Server-side processing failure (5xx, gRPC internal issues). |
| `NETWORK_ERROR` | Network connectivity failure. |
| `INVALID_REQUEST_ERROR` | Client-side invalid request. |
| `TIMEOUT` | Request/response timeout. |
| `UNAUTHENTICATED` | Authentication failure (invalid/expired token). |
| `PERMISSION_DENIED` | Permission denied or insufficient access rights. |
| `ON_DEVICE_ENGINE_ERROR` | On-device engine (e.g. Speech SDK) failure. |
| `UNKNOWN` | Unknown error type. |
| `UNAVAILABLE_LANGUAGE` | Requested language is not available. |
| `NOT_SUPPORTED_API` | Requested API is not supported. |

### SpeechToText `ErrorType` (`onFailure`, `onFailed`, `onError`)

| 값 | 설명 |
|---|---|
| `AUDIO_ERROR` | Audio data processing error (e.g. failed to read the audio buffer). |
| `MIC_ERROR` | Microphone input/recording related error. |
| `SERVER_INTERNAL_ERROR` | Server-side processing failure (5xx, gRPC internal issues). |
| `NETWORK_ERROR` | Network connectivity failure. |
| `INVALID_REQUEST_ERROR` | Client-side invalid request. |
| `TIMEOUT` | Request/response timeout. |
| `UNAUTHENTICATED` | Authentication failure (invalid/expired token). |
| `PERMISSION_DENIED` | Permission denied or insufficient access rights. |
| `ON_DEVICE_ENGINE_ERROR` | On-device engine (e.g. Speech SDK) failure. |
| `UNKNOWN` | Unknown error type. |
| `UNAVAILABLE_LANGUAGE` | Requested language is not available. |
| `NOT_SUPPORTED_API` | Requested API is not supported. |

### Vehicle `ErrorCode` (문서상. 실측에서는 전달되지 않음)

| 값 | 설명 |
|---|---|
| `ERROR_INTERNAL_SERVICE` |  |
| `ERROR_SERVICE_CONNECTION_FAILURE` |  |
| `ERROR_DRIVING_RESTRICTED` |  |
| `ERROR_NOT_SUPPORTED_PROPERTY` |  |
| `ERROR_NOT_SUPPORTED_VALUE_TYPE` |  |
| `ERROR_NOT_SUPPORTED_AREA` |  |
| `ERROR_EMPTY_RESULT` |  |
| `ERROR_SECURITY_PERMISSION` |  |
| `ERROR_UNDEFINED` |  |

