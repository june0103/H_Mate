# Vehicle SDK — API Reference

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/entry 이하 Dokka API Reference (2026-11-05 수집)
> 사이트 HTML 본문은 비어 있고 JS 청크에 실제 내용이 들어 있어 거기서 추출했습니다. 클래스(타입) 페이지 기준이며 멤버별 상세 페이지 설명은 포함하지 않습니다.
> `*(deprecated)*`는 원문에서 취소선으로 표시된 시그니처입니다. 사용 패턴은 상위 폴더의 SDK 사용 가이드 문서를 함께 보세요.

## 패키지 목록

- `ai.pleos.playground.vehicle.api.model.area`
- `ai.pleos.playground.vehicle.api.model.capability`
- `ai.pleos.playground.vehicle.api.model.common`
- `ai.pleos.playground.vehicle.api.model.telltale`
- `ai.pleos.playground.vehicle.api`
- `ai.pleos.playground.vehicle.callback.exception`
- `ai.pleos.playground.vehicle.constant.area`
- `ai.pleos.playground.vehicle.constant.control`
- `ai.pleos.playground.vehicle.constant.telltale`
- `ai.pleos.playground.vehicle.constant.unit`
- `ai.pleos.playground.vehicle.constant`
- `ai.pleos.playground.vehicle.listener`
- `ai.pleos.playground.vehicle`

---

## 패키지 `ai.pleos.playground.vehicle.api.model.area`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/entry


**Types**
- `Area` — Base type for vehicle areas.
  - `sealed class Area`
- `DoorArea` — Represents one or more door areas.
  - `class DoorArea(val values: DoorValue): Area`
- `MirrorArea` — Represents one or more mirror areas.
  - `class MirrorArea(val values: MirrorValue): Area`
- `SeatArea` — Represents one or more seat areas.
  - `class SeatArea(val values: SeatValue): Area`
- `TurnSignalArea` — Represents one or more turn signal areas.
  - `class TurnSignalArea(val values: TurnSignalValue): Area`
- `WheelArea` — Represents one or more wheel areas.
  - `class WheelArea(val values: WheelValue): Area`
- `WindowArea` — Represents one or more window areas.
  - `class WindowArea(val values: WindowValue): Area`

### Area

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/area/entry

`sealed class Area`
Base type for vehicle areas.

**Inheritors**
- `DoorArea`
- `MirrorArea`
- `SeatArea`
- `TurnSignalArea`
- `WheelArea`
- `WindowArea`

### DoorArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/door-area/entry

`class DoorArea(val values: DoorValue): Area`
Represents one or more door areas.

**Constructors**
- `DoorArea`
  - `constructor(vararg values: DoorValue)`

**Properties**
- `values` — The selected door area values.
  - `val values: Array<out DoorValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`

### MirrorArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/mirror-area/entry

`class MirrorArea(val values: MirrorValue): Area`
Represents one or more mirror areas.

**Constructors**
- `MirrorArea`
  - `constructor(vararg values: MirrorValue)`

**Properties**
- `values` — The selected mirror area values.
  - `val values: Array<out MirrorValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`

### SeatArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/seat-area/entry

`class SeatArea(val values: SeatValue): Area`
Represents one or more seat areas.

**Constructors**
- `SeatArea`
  - `constructor(vararg values: SeatValue)`

**Properties**
- `values` — The selected seat area values.
  - `val values: Array<out SeatValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`

### TurnSignalArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/turn-signal-area/entry

`class TurnSignalArea(val values: TurnSignalValue): Area`
Represents one or more turn signal areas.

**Constructors**
- `TurnSignalArea`
  - `constructor(vararg values: TurnSignalValue)`

**Properties**
- `values` — The selected turn signal area values.
  - `val values: Array<out TurnSignalValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`

### WheelArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/wheel-area/entry

`class WheelArea(val values: WheelValue): Area`
Represents one or more wheel areas.

**Constructors**
- `WheelArea`
  - `constructor(vararg values: WheelValue)`

**Properties**
- `values` — The selected wheel area values.
  - `val values: Array<out WheelValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`

### WindowArea

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.area/window-area/entry

`class WindowArea(val values: WindowValue): Area`
Represents one or more window areas.

**Constructors**
- `WindowArea`
  - `constructor(vararg values: WindowValue)`

**Properties**
- `values` — The selected window area values.
  - `val values: Array<out WindowValue>`

**Functions**
- `equals`
  - `open operator override fun equals(other: Any?): Boolean`
- `hashCode`
  - `open override fun hashCode(): Int`


---

## 패키지 `ai.pleos.playground.vehicle.api.model.capability`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/entry


**Types**
- `AccessType` — Represents the access level for a capability.
  - `enum AccessType: Enum<AccessType>`
- `AreaBounds` — Represents the available value range for an area.
  - `data class AreaBounds<T>(val minValue: T, val maxValue: T)`
- `CapabilityConfig` — Represents the configuration for a specific capability.
  - `data class CapabilityConfig(val accessType: AccessType = AccessType.NONE)`
- `HvacTemperatureConfigArray` — HVAC temperature conversion table derived from the vehicle temperature setting config array.
  - `data class HvacTemperatureConfigArray(val minCelsius: Float, val maxCelsius: Float, val incrementCelsius: Float, val minFahrenheit: Float, val maxFahrenheit: Float, val incrementFahrenheit: Float, val raw: List<Int>)`
- `SmartGlassConfig` — Describes the smart glass zones and levels for a window area.
  - `data class SmartGlassConfig(val zoneCount: Int, val levelCount: Int)`

### AccessType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/access-type/entry

`enum AccessType: Enum<AccessType>`
Represents the access level for a capability.

**Entries**
- `NONE`
- `READ`
- `WRITE`
- `READ_WRITE`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<AccessType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): AccessType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<AccessType>`

### AreaBounds

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/area-bounds/entry

`data class AreaBounds<T>(val minValue: T, val maxValue: T)`
Represents the available value range for an area.

**Constructors**
- `AreaBounds`
  - `constructor(minValue: T, maxValue: T)`

**Properties**
- `maxValue` — The maximum value.
  - `val maxValue: T`
- `minValue` — The minimum value.
  - `val minValue: T`

### CapabilityConfig

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/capability-config/entry

`data class CapabilityConfig(val accessType: AccessType = AccessType.NONE)`
Represents the configuration for a specific capability.

**Constructors**
- `CapabilityConfig`
  - `constructor(accessType: AccessType = AccessType.NONE)`

**Properties**
- `accessType` — The level of access granted. Defaults to AccessType.NONE.
  - `val accessType: AccessType`

### HvacTemperatureConfigArray

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/hvac-temperature-config-array/entry

`data class HvacTemperatureConfigArray(val minCelsius: Float, val maxCelsius: Float, val incrementCelsius: Float, val minFahrenheit: Float, val maxFahrenheit: Float, val incrementFahrenheit: Float, val raw: List<Int>)`
HVAC temperature conversion table derived from the vehicle temperature setting config array. The VHAL configuration array stores temperatures and increments as integers multiplied by 10. The temperature and increment properties below are Float values obtained by dividing those integers by 10.

**Constructors**
- `HvacTemperatureConfigArray`
  - `constructor(minCelsius: Float, maxCelsius: Float, incrementCelsius: Float, minFahrenheit: Float, maxFahrenheit: Float, incrementFahrenheit: Float, raw: List<Int>)`

**Properties**
- `incrementCelsius` — Supported temperature increment in degrees Celsius (°C).
  - `val incrementCelsius: Float`
- `incrementFahrenheit` — Supported temperature increment in degrees Fahrenheit (°F).
  - `val incrementFahrenheit: Float`
- `maxCelsius` — Maximum supported temperature in degrees Celsius (°C).
  - `val maxCelsius: Float`
- `maxFahrenheit` — Maximum supported temperature in degrees Fahrenheit (°F).
  - `val maxFahrenheit: Float`
- `minCelsius` — Minimum supported temperature in degrees Celsius (°C).
  - `val minCelsius: Float`
- `minFahrenheit` — Minimum supported temperature in degrees Fahrenheit (°F).
  - `val minFahrenheit: Float`
- `raw` — Original six integer values from the VHAL configuration array, without conversion. Each value represents a temperature or increment multiplied by 10.
  - `val raw: List<Int>`

### SmartGlassConfig

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.capability/smart-glass-config/entry

`data class SmartGlassConfig(val zoneCount: Int, val levelCount: Int)`
Describes the smart glass zones and levels for a window area.

**Constructors**
- `SmartGlassConfig`
  - `constructor(zoneCount: Int, levelCount: Int)`

**Properties**
- `levelCount` — Number of smart glass levels reported by the vehicle. This value does not indicate smart glass level support; check ai.pleos.playground.vehicle.api.Window.checkSmartGlassLevelCapabilities separately.
  - `val levelCount: Int`
- `zoneCount` — Number of smart glass zones (panels) in the window area. Pass this many commands to ai.pleos.playground.vehicle.api.Window.setSmartGlassZoneState.
  - `val zoneCount: Int`


---

## 패키지 `ai.pleos.playground.vehicle.api.model.common`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.common/entry


**Types**
- `TripStatistics` — Represents trip statistics grouped by type.
  - `data class TripStatistics<T>(val current: T? = null, val instantaneous: T? = null, val cumulative: T? = null, val sinceRefill: T? = null, val raw: List<T?> = emptyList())`

### TripStatistics

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.common/trip-statistics/entry

`data class TripStatistics<T>(val current: T? = null, val instantaneous: T? = null, val cumulative: T? = null, val sinceRefill: T? = null, val raw: List<T?> = emptyList())`
Represents trip statistics grouped by type.

**Constructors**
- `TripStatistics`
  - `constructor(current: T? = null, instantaneous: T? = null, cumulative: T? = null, sinceRefill: T? = null, raw: List<T?> = emptyList())`

**Properties**
- `cumulative` — The cumulative value.
  - `val cumulative: T? = null`
- `current` — The current value.
  - `val current: T? = null`
- `instantaneous` — The instantaneous value.
  - `val instantaneous: T? = null`
- `raw` — The full list of available values.
  - `val raw: List<T?>`
- `sinceRefill` — The value since refill.
  - `val sinceRefill: T? = null`

**Functions**
- `get`
  - `fun get(type: TripStatisticType): T?`


---

## 패키지 `ai.pleos.playground.vehicle.api.model.telltale`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.telltale/entry


**Types**
- `TelltaleData` — Represents a single telltale item.
  - `data class TelltaleData(val type: TelltaleType, val state: TelltaleState, val color: Int, val priority: Int, val warningCount: Int)`
- `Telltales` — Represents a collection of telltales.
  - `data class Telltales(val telltales: List<TelltaleData> = arrayListOf())`

### TelltaleData

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.telltale/telltale-data/entry

`data class TelltaleData(val type: TelltaleType, val state: TelltaleState, val color: Int, val priority: Int, val warningCount: Int)`
Represents a single telltale item.

**Constructors**
- `TelltaleData`
  - `constructor(type: TelltaleType, state: TelltaleState, color: Int, priority: Int, warningCount: Int)`

**Properties**
- `color` — The telltale color.
  - `val color: Int`
- `priority` — The telltale priority.
  - `val priority: Int`
- `state` — The telltale state.
  - `val state: TelltaleState`
- `type` — The telltale type.
  - `val type: TelltaleType`
- `warningCount` — The warning count.
  - `val warningCount: Int`

### Telltales

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api.model.telltale/telltales/entry

`data class Telltales(val telltales: List<TelltaleData> = arrayListOf())`
Represents a collection of telltales.

**Constructors**
- `Telltales`
  - `constructor(telltales: List<TelltaleData> = arrayListOf())`

**Properties**
- `telltales` — The telltale items.
  - `val telltales: List<TelltaleData>`


---

## 패키지 `ai.pleos.playground.vehicle.api`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/entry


**Types**
- `Brake` — Provides APIs for brake pedal status.
  - `interface Brake`
- `CarInfo` — Provides APIs for vehicle identification information and capability info.
  - `interface CarInfo`
- `Display` — Provides APIs for temperature display unit.
  - `interface Display`
- `Door` — Provides APIs for door lock, door position, and glove box.
  - `interface Door`
- `DrivingMode` — Provides APIs for driving mode and cruise control.
  - `interface DrivingMode`
- `EvBattery` — Provides APIs for EV battery efficiency.
  - `interface EvBattery`
- `Fuel` — Provides APIs for fuel economy.
  - `interface Fuel`
- `HVAC` — Provides APIs for climate control (HVAC).
  - `interface HVAC`
- `Light` — Provides APIs for exterior, cabin, and ambient lights.
  - `interface Light`
- `Odometer` — Provides APIs for odometer and trip data.
  - `interface Odometer`
- `Safety` — Provides APIs for airbags, warnings, fluid levels, and telltales.
  - `interface Safety`
- `Seat` — Provides APIs for seat position, occupancy, seat belt, and seat climate.
  - `interface Seat`
- `SideMirror` — Provides APIs for side mirror heat.
  - `interface SideMirror`
- `SteeringWheel` — Provides APIs for the front-wheel steering angle and steering wheel heating.
  - `interface SteeringWheel`
- `Tire` — Provides APIs for tire pressure.
  - `interface Tire`
- `Toll` — Provides APIs for ETCS voice guidance volume.
  - `interface Toll`
- `TurnSignal` — Provides APIs for turn signal state.
  - `interface TurnSignal`
- `Window` — Provides APIs for window position, lock, blinds, and smart glass.
  - `interface Window`
- `Wiper` — Provides APIs for wiper state and switch.
  - `interface Wiper`

### Brake

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/brake/entry

`interface Brake`
Provides APIs for brake pedal status. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getBrake.

**Functions**
- `checkBrakePedalStatusCapabilities` — Gets the brake pedal status capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkBrakePedalStatusCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentBrakePedalStatus` — Gets the brake pedal status. If true, the brake pedal is being pressed.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCurrentBrakePedalStatus(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerBrakePedalStatus` — Registers a listener for brake pedal status property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerBrakePedalStatus(listener: BrakePedalStatusListener)`
- `unregisterBrakePedalStatus` — Unregisters the listener so brake pedal status property updates stop.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterBrakePedalStatus(listener: BrakePedalStatusListener)`

### CarInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/car-info/entry

`interface CarInfo`
Provides APIs for vehicle identification information and capability info. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getCarInfo.

**Functions**
- `checkCRNInfoCapabilities` — Gets CRN info capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun checkCRNInfoCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkVINInfoCapabilities` — Gets VIN info capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun checkVINInfoCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCRNInfo` — Gets the Connected Resource Name (CRN) Info.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun getCRNInfo(onComplete: (String?) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `getVINInfo` — Gets the Vehicle Identification Number (VIN).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun getVINInfo(onComplete: (String?) -> Unit, onFailed: (Exception) -> Unit)`

### Display

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/display/entry

`interface Display`
Provides APIs for temperature display unit. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDisplay.

**Functions**
- `checkTemperatureDisplayUnitCapabilities` — Gets temperature display unit capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun checkTemperatureDisplayUnitCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentTemperatureDisplayUnit` — Gets current temperature units for display.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun getCurrentTemperatureDisplayUnit(onComplete: (TemperatureUnit?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerTemperatureDisplayUnit` — Registers TemperatureDisplayUnit to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun registerTemperatureDisplayUnit(listener: TemperatureDisplayUnitListener)`
- `unregisterTemperatureDisplayUnit` — Stops getting property updates for the given TemperatureDisplayUnit.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_INFO") abstract fun unregisterTemperatureDisplayUnit(listener: TemperatureDisplayUnitListener)`

### Door

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/door/entry

`interface Door`
Provides APIs for door lock, door position, and glove box. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDoor.

**Functions**
- `checkDoorLockCapabilities` — Gets DoorLock capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun checkDoorLockCapabilities(onComplete: (List<DoorArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun checkDoorLockCapabilities(onComplete: (CapabilityConfig, List<DoorArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets DoorLock capabilities.
- `checkDoorPositionCapabilities` — Gets DoorPosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun checkDoorPositionCapabilities(onComplete: (Map<DoorArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun checkDoorPositionCapabilities(onComplete: (CapabilityConfig, Map<DoorArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets DoorPosition capabilities.
- `checkGloveBoxLockCapabilities` — Gets the glove box lock capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun checkGloveBoxLockCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentDoorPosition` — Gets the current door position state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun getCurrentDoorPosition(area: DoorArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentGloveBoxLockState` — Gets the current glove lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun getCurrentGloveBoxLockState(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getDoorLockState` — Gets current door lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun getDoorLockState(area: DoorArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerDoorLockState` — Registers the door lock state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun registerDoorLockState(area: DoorArea, listener: DoorLockStateListener)`
- `registerDoorPosition` — Registers the door area to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun registerDoorPosition(area: DoorArea, listener: DoorPositionListener)`
- `registerGloveBoxLockState` — Registers the glove box lock state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun registerGloveBoxLockState(listener: GloveBoxLockStateListener)`
- `setDoorLock` — Sets the current door lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_DOORS") abstract fun setDoorLock(area: DoorArea, value: Boolean, onComplete: (DoorArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setDoorPosition` — Sets the current door position state. The maximum (fully open) value is vehicle-dependent and is not fixed. Call checkDoorPositionCapabilities first. That API's callback returns CapabilityConfig plus a Map<DoorArea, AreaBounds<Int>>; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig). Min is typically 0 for fully closed.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_DOORS") abstract fun setDoorPosition(area: DoorArea, value: Int, onComplete: (DoorArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setGloveBoxLock` — Sets the current glove lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_DOORS") abstract fun setGloveBoxLock(value: Boolean, onComplete: (Boolean?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `unregisterDoorLockState` — Stops getting property updates for the given door lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun unregisterDoorLockState(area: DoorArea, listener: DoorLockStateListener)`
- `unregisterDoorPosition` — Stops getting property updates for the given door area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun unregisterDoorPosition(area: DoorArea, listener: DoorPositionListener)`
- `unregisterGloveBoxLockState` — Stops getting property updates for the given glove box lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DOORS") abstract fun unregisterGloveBoxLockState(listener: GloveBoxLockStateListener)`

### DrivingMode

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/driving-mode/entry

`interface DrivingMode`
Provides APIs for driving mode and cruise control. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getDrivingMode.

**Functions**
- `checkCruiseControlStateCapabilities` — Gets CruiseControlState capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkCruiseControlStateCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkCruiseControlTargetSpeedCapabilities` — Gets CruiseControlTargetSpeed capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkCruiseControlTargetSpeedCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkDrivingModeStateCapabilities` — Gets DrivingModeState capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkDrivingModeStateCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCruiseControlState` — Gets the current cruise control state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCruiseControlState(onComplete: (CruiseControl?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCruiseControlTargetSpeed` — Gets the current cruise control target speed in meters per second (m/s).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCruiseControlTargetSpeed(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentDrivingModeState` — Gets current driving mode state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCurrentDrivingModeState(onComplete: (DrivingModeType?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerCruiseControlState` — Registers CruiseControlState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerCruiseControlState(listener: CruiseControlStateListener)`
- `registerCruiseControlTargetSpeed` — Registers a listener for cruise control target speed updates in meters per second (m/s).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerCruiseControlTargetSpeed(listener: CruiseControlTargetSpeedListener)`
- `registerDrivingModeState` — Registers DrivingModeState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerDrivingModeState(listener: DrivingModeStateListener)`
- `unregisterCruiseControlState` — Stops getting property updates for the given CruiseControlState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterCruiseControlState(listener: CruiseControlStateListener)`
- `unregisterCruiseControlTargetSpeed` — Stops getting property updates for the given CruiseControlTargetSpeed.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterCruiseControlTargetSpeed(listener: CruiseControlTargetSpeedListener)`
- `unregisterDrivingModeState` — Stops getting property updates for the given DrivingModeState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterDrivingModeState(listener: DrivingModeStateListener)`

### EvBattery

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/ev-battery/entry

`interface EvBattery`
Provides APIs for EV battery efficiency. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getEvBattery.

**Functions**
- `checkCumulativeEvEfficiencyCapabilities` — Gets Cumulative EV Efficiency capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkCumulativeEvEfficiencyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkCurrentEvEfficiencyCapabilities` — Gets Current EV Efficiency capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkCurrentEvEfficiencyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkEvBatteryStateOfChargeCapabilities` — Gets EV Battery State Of Charge capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvBatteryStateOfChargeCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkEvChargeCurrentDrawLimitCapabilities` — Gets the supported EV charge current draw limits in amperes (A). / Gets capabilities for EV charge current draw limits in amperes (A).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvChargeCurrentDrawLimitCapabilities(onComplete: (List<Float>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)* — Gets the supported EV charge current draw limits in amperes (A).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvChargeCurrentDrawLimitCapabilities(onComplete: (CapabilityConfig, List<Float>) -> Unit, onFailed: (Exception) -> Unit)` — Gets capabilities for EV charge current draw limits in amperes (A).
- `checkEvChargePercentLimitCapabilities` — Gets capabilities for EV charge limits, each expressed as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvChargePercentLimitCapabilities(onComplete: (CapabilityConfig, List<Float>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkEvChargePortOpenCapabilities` — Gets EvChargePortOpen capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvChargePortOpenCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkEvChargeSwitchCapabilities` — Gets EvChargeSwitch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkEvChargeSwitchCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkInstantaneousEvEfficiencyCapabilities` — Gets Instantaneous EV Efficiency capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkInstantaneousEvEfficiencyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSinceChargeEvEfficiencyCapabilities` — Gets EV Efficiency Since Charge capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkSinceChargeEvEfficiencyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCumulativeEvEfficiency` — Gets cumulative EV efficiency.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getCumulativeEvEfficiency(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentEvEfficiency` — Gets current EV efficiency.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getCurrentEvEfficiency(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getEvBatteryStateOfCharge` — Gets EV battery state of charge as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getEvBatteryStateOfCharge(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getEvChargeCurrentDrawLimit` — Gets the EV charge current draw limit in amperes (A).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getEvChargeCurrentDrawLimit(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getEvChargeSwitchState` — Gets the current EV battery charging state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getEvChargeSwitchState(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getInstantaneousEvEfficiency` — Gets instantaneous EV efficiency.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getInstantaneousEvEfficiency(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getSinceChargeEvEfficiency` — Gets EV efficiency since charge.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getSinceChargeEvEfficiency(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerCumulativeEvEfficiency` — Registers Cumulative EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerCumulativeEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `registerCurrentEvEfficiency` — Registers Current EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerCurrentEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `registerEvBatteryStateOfCharge` — Registers a listener for EV battery state of charge updates expressed as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerEvBatteryStateOfCharge(listener: EvBatteryStateOfChargeListener)`
- `registerInstantaneousEvEfficiency` — Registers Instantaneous EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerInstantaneousEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `registerSinceChargeEvEfficiency` — Registers EV Efficiency Since Charge listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerSinceChargeEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `setEvChargeCurrentDrawLimit` — Sets the EV charge current draw limit in amperes (A).
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_ENERGY") abstract fun setEvChargeCurrentDrawLimit(value: Float, onComplete: (Float?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setEvChargePercentLimit` — Sets the battery state-of-charge limit for EV charging, expressed as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_ENERGY") abstract fun setEvChargePercentLimit(value: Float, onComplete: (Float?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setEvChargePortOpen` — Sets the EV charge port open state to On or Off.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_ENERGY") abstract fun setEvChargePortOpen(value: Boolean, onComplete: (Boolean?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setEvChargeSwitch` — Sets whether to start or stop EV battery charging.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_ENERGY") abstract fun setEvChargeSwitch(value: Boolean, onComplete: (Boolean?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `unregisterCumulativeEvEfficiency` — Unregisters Cumulative EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterCumulativeEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `unregisterCurrentEvEfficiency` — Unregisters Current EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterCurrentEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `unregisterEvBatteryStateOfCharge` — Unregisters EV Battery State Of Charge listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterEvBatteryStateOfCharge(listener: EvBatteryStateOfChargeListener)`
- `unregisterInstantaneousEvEfficiency` — Unregisters Instantaneous EV Efficiency listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterInstantaneousEvEfficiency(listener: EvBatteryEfficiencyListener)`
- `unregisterSinceChargeEvEfficiency` — Unregisters EV Efficiency Since Charge listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterSinceChargeEvEfficiency(listener: EvBatteryEfficiencyListener)`

### Fuel

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/fuel/entry

`interface Fuel`
Provides APIs for fuel economy. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getFuel.

**Functions**
- `checkCumulativeFuelEconomyCapabilities` — Gets Cumulative Fuel Economy capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkCumulativeFuelEconomyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkCurrentFuelEconomyCapabilities` — Gets Current Fuel Economy capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkCurrentFuelEconomyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkFuelPercentCapabilities` — Gets Fuel Percent capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkFuelPercentCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkInstantaneousFuelEconomyCapabilities` — Gets Instantaneous Fuel Economy capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkInstantaneousFuelEconomyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSinceChargeFuelEconomyCapabilities` — Gets Fuel Economy Since Refuel capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun checkSinceChargeFuelEconomyCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCumulativeFuelEconomy` — Gets cumulative fuel economy.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getCumulativeFuelEconomy(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentFuelEconomy` — Gets current fuel economy.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getCurrentFuelEconomy(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getFuelPercent` — Gets the fuel level as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getFuelPercent(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getInstantaneousFuelEconomy` — Gets instantaneous fuel economy.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getInstantaneousFuelEconomy(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getSinceChargeFuelEconomy` — Gets fuel economy since refuel.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun getSinceChargeFuelEconomy(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerCumulativeFuelEconomy` — Registers Cumulative Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerCumulativeFuelEconomy(listener: FuelEconomyListener)`
- `registerCurrentFuelEconomy` — Registers Current Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerCurrentFuelEconomy(listener: FuelEconomyListener)`
- `registerFuelPercent` — Registers a listener for fuel level updates expressed as a percentage (%).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerFuelPercent(listener: FuelPercentListener)`
- `registerInstantaneousFuelEconomy` — Registers Instantaneous Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerInstantaneousFuelEconomy(listener: FuelEconomyListener)`
- `registerSinceChargeFuelEconomy` — Registers Fuel Economy Since Refuel listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun registerSinceChargeFuelEconomy(listener: FuelEconomyListener)`
- `unregisterCumulativeFuelEconomy` — Unregisters Cumulative Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterCumulativeFuelEconomy(listener: FuelEconomyListener)`
- `unregisterCurrentFuelEconomy` — Unregisters Current Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterCurrentFuelEconomy(listener: FuelEconomyListener)`
- `unregisterFuelPercent` — Unregisters Fuel Percent listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterFuelPercent(listener: FuelPercentListener)`
- `unregisterInstantaneousFuelEconomy` — Unregisters Instantaneous Fuel Economy listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterInstantaneousFuelEconomy(listener: FuelEconomyListener)`
- `unregisterSinceChargeFuelEconomy` — Unregisters Fuel Economy Since Refuel listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_ENERGY") abstract fun unregisterSinceChargeFuelEconomy(listener: FuelEconomyListener)`

### HVAC

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/h-v-a-c/entry

`interface HVAC`
Provides APIs for climate control (HVAC). Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getHVAC.

**Functions**
- `checkHvacAcCapabilities` — Gets HvacAC capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAcCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAcCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HvacAC capabilities.
- `checkHvacAirCleanerCapabilities` — Gets HVAC air cleaner capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAirCleanerCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAirCleanerCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC air cleaner capabilities.
- `checkHvacAutoCapabilities` — Gets HvacAuto capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAutoCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAutoCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HvacAuto capabilities.
- `checkHvacAutoRecirculationCapabilities` — Gets HVAC auto recirculation capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAutoRecirculationCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacAutoRecirculationCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC auto recirculation capabilities.
- `checkHvacDefrosterCapabilities` — Gets HVAC defroster capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacDefrosterCapabilities(onComplete: (List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacDefrosterCapabilities(onComplete: (CapabilityConfig, List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC defroster capabilities.
- `checkHvacDualCapabilities` — Gets HVAC dual capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacDualCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacDualCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC dual capabilities.
- `checkHvacFanDirectionCapabilities` — Gets HVAC fan direction capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacFanDirectionCapabilities(onComplete: (Map<SeatArea, List<FanDirectionType>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacFanDirectionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, List<FanDirectionType>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC fan direction capabilities.
- `checkHvacFanSpeedCapabilities` — Gets HVAC fan speed capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacFanSpeedCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacFanSpeedCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC fan speed capabilities.
- `checkHvacMaxAcCapabilities` — Gets HVAC max AC capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacMaxAcCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacMaxAcCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC max AC capabilities.
- `checkHvacMaxDefrostCapabilities` — Gets HVAC max defrost capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacMaxDefrostCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacMaxDefrostCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC max defrost capabilities.
- `checkHvacPowerCapabilities` — Gets HVAC power capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacPowerCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacPowerCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC power capabilities.
- `checkHvacRecirculationCapabilities` — Gets HVAC recirculation capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacRecirculationCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacRecirculationCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC recirculation capabilities.
- `checkHvacTemperatureCapabilities` — Gets the supported target temperature ranges in degrees Celsius (°C). / Gets HVAC temperature range capabilities as AreaBounds in degrees Celsius (°C). / Gets HVAC temperature capabilities including supported temperature ranges in degrees Celsius (°C) and the vehicle-specific temperature conversion config array.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacTemperatureCapabilities(onComplete: (Map<SeatArea, AreaBounds<Float>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)* — Gets the supported target temperature ranges in degrees Celsius (°C).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacTemperatureCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Float>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)* — Gets HVAC temperature range capabilities as AreaBounds in degrees Celsius (°C).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacTemperatureCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Float>>, HvacTemperatureConfigArray?) -> Unit, onFailed: (Exception) -> Unit)` — Gets HVAC temperature capabilities including supported temperature ranges in degrees Celsius (°C) and the vehicle-specific temperature conversion config array.
- `checkHvacTemperatureCurrentCapabilities` — Gets HVAC temperature current capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacTemperatureCurrentCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacAcState` — Gets the current AC state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacAcState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacAirCleanerState` — Gets the current air cleaner state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacAirCleanerState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacAutoRecirculationState` — Gets the current automatic recirculation. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacAutoRecirculationState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacAutoState` — Gets the current automatic mode. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacAutoState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacDefrosterState` — Gets the current defroster state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacDefrosterState(area: WindowArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacDualState` — Gets the current temperature coupling state between areas. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacDualState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacFanDirection` — Gets the current fan direction. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacFanDirection(area: SeatArea, onComplete: (FanDirectionType?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacFanSpeed` — Gets the current fan speed. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacFanSpeed(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacMaxAcState` — Gets the current max AC state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacMaxAcState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacMaxDefrostState` — Gets the current max defrost state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacMaxDefrostState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacPowerState` — Gets the current global power state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacPowerState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacRecirculationState` — Gets the current recirculation state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacRecirculationState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacTemperature` — Gets the target temperature in the specified unit. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacTemperature(area: SeatArea, unit: TemperatureUnit, onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacTemperatureCurrent` — Gets the current internal temperature of the vehicle in degrees Celsius (°C). / Gets the current internal temperature of the vehicle in the requested unit.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacTemperatureCurrent(area: SeatArea, onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)` — Gets the current internal temperature of the vehicle in degrees Celsius (°C).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacTemperatureCurrent(area: SeatArea, unit: TemperatureUnit, onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)` — Gets the current internal temperature of the vehicle in the requested unit.
- `registerHvacAcState` — Registers the HvacAC state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacAcState(area: SeatArea, listener: HvacAcStateListener)`
- `registerHvacAirCleanerState` — Registers the HvacAirCleaner state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacAirCleanerState(area: SeatArea, listener: HvacAirCleanerStateListener)`
- `registerHvacAutoRecirculationState` — Registers the HvacAutoRecirculation state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacAutoRecirculationState(area: SeatArea, listener: HvacRecirculationStateListener)`
- `registerHvacAutoState` — Registers the HvacAuto state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacAutoState(area: SeatArea, listener: HvacAutoStateListener)`
- `registerHvacDefrosterState` — Registers the HvacDefroster state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacDefrosterState(area: WindowArea, listener: HvacDefrosterStateListener)`
- `registerHvacDualState` — Registers the HvacDual state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacDualState(area: SeatArea, listener: HvacDualStateListener)`
- `registerHvacFanSpeed` — Registers the HvacFanSpeed to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacFanSpeed(area: SeatArea, listener: HvacFanSpeedListener)`
- `registerHvacMaxAcState` — Registers the HvacMaxAc state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacMaxAcState(area: SeatArea, listener: HvacMaxAcStateListener)`
- `registerHvacMaxDefrostState` — Registers the HvacMaxDefrost state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacMaxDefrostState(area: SeatArea, listener: HvacMaxDefrostStateListener)`
- `registerHvacPowerState` — Registers the HvacPower state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacPowerState(area: SeatArea, listener: HvacPowerStateListener)`
- `registerHvacRecirculationState` — Registers the HvacRecirculation to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacRecirculationState(area: SeatArea, listener: HvacRecirculationStateListener)`
- `registerHvacTemperature` — Registers a listener for target temperature updates in degrees Celsius (°C) only. / Registers the HvacTemperature to get property updates in degrees Celsius (°C) and degrees Fahrenheit (°F).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacTemperature(area: SeatArea, listener: HvacTemperatureListener)` — Registers a listener for target temperature updates in degrees Celsius (°C) only.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacTemperature(area: SeatArea, listener: HvacTemperatureValueListener)` — Registers the HvacTemperature to get property updates in degrees Celsius (°C) and degrees Fahrenheit (°F).
- `registerHvacTemperatureCurrent` — Registers a listener for current interior temperature updates in degrees Celsius (°C). / Registers HvacTemperatureCurrent updates in degrees Celsius (°C) and degrees Fahrenheit (°F).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacTemperatureCurrent(area: SeatArea, listener: HvacTemperatureListener)` — Registers a listener for current interior temperature updates in degrees Celsius (°C).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacTemperatureCurrent(area: SeatArea, listener: HvacTemperatureValueListener)` — Registers HvacTemperatureCurrent updates in degrees Celsius (°C) and degrees Fahrenheit (°F).
- `setHvacAc` — Sets AC to On/Off for the designated areaId. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacAc(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacAirCleaner` — Sets the air cleaner to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacAirCleaner(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacAuto` — Sets automatic mode to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacAuto(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacAutoRecirculation` — Sets the automatic recirculation to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacAutoRecirculation(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacDefroster` — Sets the defroster to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacDefroster(area: WindowArea, value: Boolean, onComplete: (WindowArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacDual` — Enable temperature coupling between areas. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacDual(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacFanDirection` — Sets fan direction. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacFanDirection(area: SeatArea, value: FanDirectionType, onComplete: (SeatArea, FanDirectionType?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacFanSpeed` — Sets the fan speed. The supported fan speed range is vehicle-dependent and is not fixed. Call checkHvacFanSpeedCapabilities first. That API's callback returns CapabilityConfig plus a Map<SeatArea, AreaBounds<Int>>; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig).
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacFanSpeed(area: SeatArea, value: Int, onComplete: (SeatArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacMaxAc` — Sets the max AC to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacMaxAc(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacMaxDefrost` — Sets the max defrost to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacMaxDefrost(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacPower` — Sets the global power state to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacPower(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacRecirculation` — Sets recirculation to On/Off. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacRecirculation(area: SeatArea, value: Boolean, onComplete: (SeatArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacTemperature` — Sets the target temperature in the specified unit. The supported temperature range is vehicle-dependent and is not fixed. Call checkHvacTemperatureCapabilities first. That API's callback returns CapabilityConfig, a Map<SeatArea, AreaBounds<Float>>, and optional HvacTemperatureConfigArray; read each area's AreaBounds.minValue/AreaBounds.maxValue from that map (not from CapabilityConfig). AreaBounds are in degrees Celsius (°C); use the config array when converting to Fahrenheit.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacTemperature(area: SeatArea, value: Float, unit: TemperatureUnit, onComplete: (SeatArea, Float?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `unregisterHvacAcState` — Stops getting property updates for the given HvacAC state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacAcState(area: SeatArea, listener: HvacAcStateListener)`
- `unregisterHvacAirCleanerState` — Stops getting property updates for the given HvacAirCleaner state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacAirCleanerState(area: SeatArea, listener: HvacAirCleanerStateListener)`
- `unregisterHvacAutoRecirculationState` — Stops getting property updates for the given HvacAutoRecirculation state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacAutoRecirculationState(area: SeatArea, listener: HvacRecirculationStateListener)`
- `unregisterHvacAutoState` — Stops getting property updates for the given HvacAuto state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacAutoState(area: SeatArea, listener: HvacAutoStateListener)`
- `unregisterHvacDefrosterState` — Stops getting property updates for the given HvacDefroster state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacDefrosterState(area: WindowArea, listener: HvacDefrosterStateListener)`
- `unregisterHvacDualState` — Stops getting property updates for the given HvacDual state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacDualState(area: SeatArea, listener: HvacDualStateListener)`
- `unregisterHvacFanSpeed` — Stops getting property updates for the given HvacFanSpeed.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacFanSpeed(area: SeatArea, listener: HvacFanSpeedListener)`
- `unregisterHvacMaxAcState` — Stop getting property updates for the given HvacMaxAc state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacMaxAcState(area: SeatArea, listener: HvacMaxAcStateListener)`
- `unregisterHvacMaxDefrostState` — Stops getting property updates for the given HvacMaxDefrost state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacMaxDefrostState(area: SeatArea, listener: HvacMaxDefrostStateListener)`
- `unregisterHvacPowerState` — Stops getting property updates for the given HvacPower state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacPowerState(area: SeatArea, listener: HvacPowerStateListener)`
- `unregisterHvacRecirculationState` — Stops getting property updates for the given HvacRecirculation.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacRecirculationState(area: SeatArea, listener: HvacRecirculationStateListener)`
- `unregisterHvacTemperature` — Stops getting property updates for the given HvacTemperature. / Stops getting HVAC temperature updates for the given listener that receives both Celsius and Fahrenheit values.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacTemperature(area: SeatArea, listener: HvacTemperatureListener)` — Stops getting property updates for the given HvacTemperature.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacTemperature(area: SeatArea, listener: HvacTemperatureValueListener)` — Stops getting HVAC temperature updates for the given listener that receives both Celsius and Fahrenheit values.
- `unregisterHvacTemperatureCurrent` — Stops getting property updates for the given HvacTemperatureCurrent. / Stops getting HvacTemperatureCurrent updates for the listener receiving both Celsius and Fahrenheit values.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacTemperatureCurrent(area: SeatArea, listener: HvacTemperatureListener)` — Stops getting property updates for the given HvacTemperatureCurrent.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacTemperatureCurrent(area: SeatArea, listener: HvacTemperatureValueListener)` — Stops getting HvacTemperatureCurrent updates for the listener receiving both Celsius and Fahrenheit values.

### Light

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/light/entry

`interface Light`
Provides APIs for exterior, cabin, and ambient lights. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getLight.

**Functions**
- `checkAmbientColorAutoByDriveModeEnabledCapabilities` — Gets Ambient Color Auto By Drive Mode Enabled capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkAmbientColorAutoByDriveModeEnabledCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkAmbientLightBrightnessCapabilities` — Gets ambient light brightness capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkAmbientLightBrightnessCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkAmbientLightBrightnessColorCapabilities` — Gets ambient light brightness color capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkAmbientLightBrightnessColorCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkAmbientLightStateCapabilities` — Gets ambient light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkAmbientLightStateCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, List<LightState>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkAmbientLightSwitchCapabilities` — Gets ambient light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkAmbientLightSwitchCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, List<LightSwitch>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkCabinLightStateCapabilities` — Gets cabin light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkCabinLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkCabinLightSwitchCapabilities` — Gets cabin light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkCabinLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkCabinLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets cabin light switch capabilities.
- `checkFrontFogLightStateCapabilities` — Gets front fog light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkFrontFogLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkFrontFogLightSwitchCapabilities` — Gets front fog light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkFrontFogLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkFrontFogLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets front fog light switch capabilities.
- `checkHighBeamLightStateCapabilities` — Gets high beam light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkHighBeamLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkHighBeamLightSwitchCapabilities` — Gets high beam light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkHighBeamLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkHighBeamLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets high beam light switch capabilities.
- `checkLowBeamLightStateCapabilities` — Gets low beam light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkLowBeamLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkLowBeamLightSwitchCapabilities` — Gets low beam light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkLowBeamLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkLowBeamLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets low beam light switch capabilities.
- `checkReadingLightStateCapabilities` — Gets reading light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkReadingLightStateCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, List<LightState>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkReadingLightSwitchCapabilities` — Gets reading light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkReadingLightSwitchCapabilities(onComplete: (Map<SeatArea, List<LightSwitch>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkReadingLightSwitchCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, List<LightSwitch>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets reading light switch capabilities.
- `checkRearFogLightStateCapabilities` — Gets rear fog light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkRearFogLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkRearFogLightSwitchCapabilities` — Gets rear fog light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkRearFogLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkRearFogLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets rear fog light switch capabilities.
- `checkTailLampLightStateCapabilities` — Gets tail lamp light state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkTailLampLightStateCapabilities(onComplete: (CapabilityConfig, List<LightState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTailLampLightSwitchCapabilities` — Gets tail lamp light switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkTailLampLightSwitchCapabilities(onComplete: (List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkTailLampLightSwitchCapabilities(onComplete: (CapabilityConfig, List<LightSwitch>) -> Unit, onFailed: (Exception) -> Unit)` — Gets tail lamp light switch capabilities.
- `getAmbientColorAutoByDriveModeEnabled` — Gets whether Ambient Color Auto By Drive Mode is enabled.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getAmbientColorAutoByDriveModeEnabled(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getAmbientLightBrightness` — Gets the current Ambient Light Brightness for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getAmbientLightBrightness(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getAmbientLightBrightnessColor` — Gets the current Ambient Light Brightness Color (32-bit ARGB) for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getAmbientLightBrightnessColor(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getAmbientLightState` — Gets the current Ambient Light State for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getAmbientLightState(area: SeatArea, onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getAmbientLightSwitch` — Gets the current Ambient Light Switch for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getAmbientLightSwitch(area: SeatArea, onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCabinLightSwitch` — Gets the current cabin light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCabinLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentCabinLightState` — Gets the current cabin light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentCabinLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentFrontFogLightState` — Gets the current front fog light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentFrontFogLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentHighBeamLightState` — Gets the current high beam light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentHighBeamLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentLowBeamLightState` — Gets the current low beam light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentLowBeamLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentReadingLightState` — Gets the current reading light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentReadingLightState(area: SeatArea, onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentRearFogLightState` — Gets the current rear fog light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentRearFogLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentTailLampLightState` — Gets the current tail lamp light state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentTailLampLightState(onComplete: (LightState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getFrontFogLightSwitch` — Gets the current front fog light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getFrontFogLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHighBeamLightSwitch` — Gets the current high beam light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getHighBeamLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getLowBeamLightSwitch` — Gets the current low beam light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getLowBeamLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getReadingLightSwitch` — Gets the current reading light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getReadingLightSwitch(area: SeatArea, onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getRearFogLightSwitch` — Gets the current rear fog light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getRearFogLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTailLampLightSwitch` — Gets the current tail lamp light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getTailLampLightSwitch(onComplete: (LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerAmbientColorAutoByDriveModeEnabled` — Registers a listener to receive Ambient Color Auto By Drive Mode Enabled updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerAmbientColorAutoByDriveModeEnabled(listener: AmbientColorAutoByDriveModeEnabledListener)`
- `registerAmbientLightBrightnessColorListener` — Registers a listener to receive Ambient Light Brightness Color updates for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerAmbientLightBrightnessColorListener(area: SeatArea, listener: AmbientLightBrightnessColorListener)`
- `registerAmbientLightBrightnessListener` — Registers a listener to receive Ambient Light Brightness updates for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerAmbientLightBrightnessListener(area: SeatArea, listener: AmbientLightBrightnessListener)`
- `registerAmbientLightStateListener` — Registers a listener to receive Ambient Light State updates for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerAmbientLightStateListener(area: SeatArea, listener: LightStateListener)`
- `registerCabinLightState` — Registers the CabinLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerCabinLightState(listener: LightStateListener)`
- `registerFrontFogLightState` — Registers the FrontFogLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerFrontFogLightState(listener: LightStateListener)`
- `registerHighBeamLightState` — Registers the HighBeamLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerHighBeamLightState(listener: LightStateListener)`
- `registerLowBeamLightState` — Registers the LowBeamLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerLowBeamLightState(listener: LightStateListener)`
- `registerReadingLightState` — Registers the ReadingLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerReadingLightState(area: SeatArea, listener: LightStateListener)`
- `registerRearFogLightState` — Registers the RearFogLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerRearFogLightState(listener: LightStateListener)`
- `registerTailLampLightState` — Registers the TailLampLightState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerTailLampLightState(listener: LightStateListener)`
- `setAmbientColorAutoByDriveModeEnabled` — Sets whether Ambient Color Auto By Drive Mode is enabled.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setAmbientColorAutoByDriveModeEnabled(value: Boolean, onComplete: (Boolean?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setAmbientLightBrightness` — Sets the Ambient Light Brightness for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun setAmbientLightBrightness(area: SeatArea, value: Int, onComplete: (SeatArea, Int) -> Unit, onFailed: (Exception) -> Unit)`
- `setAmbientLightBrightnessColor` — Sets the Ambient Light Brightness Color for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun setAmbientLightBrightnessColor(area: SeatArea, value: Int, onComplete: (SeatArea, Int) -> Unit, onFailed: (Exception) -> Unit)`
- `setAmbientLightSwitch` — Sets the Ambient Light Switch for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun setAmbientLightSwitch(area: SeatArea, value: LightSwitch, onComplete: (SeatArea, LightSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `setCabinLightSwitch` — Sets the cabin light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setCabinLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setFrontFogLightSwitch` — Sets the front fog light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setFrontFogLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setHighBeamLightSwitch` — Sets the high beam light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setHighBeamLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setLowBeamLightSwitch` — Sets the low beam light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setLowBeamLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setReadingLightSwitch` — Sets the reading light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setReadingLightSwitch(area: SeatArea, value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setRearFogLightSwitch` — Sets the rear fog light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setRearFogLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `setTailLampLightSwitch` — Sets the tail lamp light switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_LIGHTS") abstract fun setTailLampLightSwitch(value: LightSwitch, onComplete: (LightSwitch?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `unregisterAmbientColorAutoByDriveModeEnabled` — Unregisters a previously registered Ambient Color Auto By Drive Mode Enabled listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterAmbientColorAutoByDriveModeEnabled(listener: AmbientColorAutoByDriveModeEnabledListener)`
- `unregisterAmbientLightBrightnessColorListener` — Unregisters a previously registered Ambient Light Brightness Color listener for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterAmbientLightBrightnessColorListener(area: SeatArea, listener: AmbientLightBrightnessColorListener)`
- `unregisterAmbientLightBrightnessListener` — Unregisters a previously registered Ambient Light Brightness listener for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterAmbientLightBrightnessListener(area: SeatArea, listener: AmbientLightBrightnessListener)`
- `unregisterAmbientLightStateListener` — Unregisters a previously registered Ambient Light State listener for the specified seat area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterAmbientLightStateListener(area: SeatArea, listener: LightStateListener)`
- `unregisterCabinLightState` — Stops getting property updates for the given CabinLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterCabinLightState(listener: LightStateListener)`
- `unregisterFrontFogLightState` — Stops getting property updates for the given FrontFogLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterFrontFogLightState(listener: LightStateListener)`
- `unregisterHighBeamLightState` — Stops getting property updates for the given HighBeamLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterHighBeamLightState(listener: LightStateListener)`
- `unregisterLowBeamLightState` — Stops getting property updates for the given LowBeamLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterLowBeamLightState(listener: LightStateListener)`
- `unregisterReadingLightState` — Stops getting property updates for the given ReadingLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterReadingLightState(area: SeatArea, listener: LightStateListener)`
- `unregisterRearFogLightState` — Stops getting property updates for the given RearFogLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterRearFogLightState(listener: LightStateListener)`
- `unregisterTailLampLightState` — Stops getting property updates for the given TailLampLightState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterTailLampLightState(listener: LightStateListener)`

### Odometer

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/odometer/entry

`interface Odometer`
Provides APIs for odometer and trip data. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getOdometer.

**Functions**
- `checkCurrentDistanceDrivenCapabilities` — Gets current distance driven capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkCurrentDistanceDrivenCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripCumulativeDrivingDistanceCapabilities` — Gets Trip Cumulative Driving Distance capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripCumulativeDrivingDistanceCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripCumulativeDrivingTimeCapabilities` — Gets Trip Cumulative Driving Time capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripCumulativeDrivingTimeCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripCurrentDrivingDistanceCapabilities` — Gets Trip Current Driving Distance capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripCurrentDrivingDistanceCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripCurrentDrivingTimeCapabilities` — Gets Trip Current Driving Time capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripCurrentDrivingTimeCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripDrivingDistanceCapabilities` — Gets Trip Driving Distance capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripDrivingDistanceCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripDrivingTimeCapabilities` — Gets Trip Driving Time capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripDrivingTimeCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripInstantaneousDrivingDistanceCapabilities` — Gets Trip Instantaneous Driving Distance capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripInstantaneousDrivingDistanceCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkTripSinceRefillDrivingDistanceCapabilities` — Gets Trip Since Refuel Driving Distance capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripSinceRefillDrivingDistanceCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTripSinceRefillDrivingTimeCapabilities` — Gets Trip Since Refuel Driving Time capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkTripSinceRefillDrivingTimeCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentDistanceDriven` — Current odometer value of the vehicle in kilometers (km).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCurrentDistanceDriven(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripCumulativeDrivingDistance` — Gets trip cumulative driving distance in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripCumulativeDrivingDistance(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripCumulativeDrivingTime` — Gets trip cumulative driving time.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripCumulativeDrivingTime(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripCurrentDrivingDistance` — Gets trip current driving distance in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripCurrentDrivingDistance(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripCurrentDrivingTime` — Gets trip current driving time.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripCurrentDrivingTime(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripInstantaneousDrivingDistance` — Gets trip instantaneous driving distance in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripInstantaneousDrivingDistance(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `getTripSinceRefillDrivingDistance` — Gets trip since refuel driving distance in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripSinceRefillDrivingDistance(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTripSinceRefillDrivingTime` — Gets trip since refuel driving time.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getTripSinceRefillDrivingTime(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerDistanceDriven` — Registers a listener for odometer updates in kilometers (km).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerDistanceDriven(listener: DistanceDrivenListener)`
- `registerTripCumulativeDrivingDistance` — Registers a listener for trip cumulative driving distance updates in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripCumulativeDrivingDistance(listener: DistanceDrivenListener)`
- `registerTripCumulativeDrivingTime` — Registers Trip Cumulative Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripCumulativeDrivingTime(listener: DrivingTimeListener)`
- `registerTripCurrentDrivingDistance` — Registers a listener for trip current driving distance updates in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripCurrentDrivingDistance(listener: DistanceDrivenListener)`
- `registerTripCurrentDrivingTime` — Registers Trip Current Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripCurrentDrivingTime(listener: DrivingTimeListener)`
- `registerTripInstantaneousDrivingDistance` — Registers a listener for trip instantaneous driving distance updates in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripInstantaneousDrivingDistance(listener: DistanceDrivenListener)` *(deprecated)*
- `registerTripSinceRefillDrivingDistance` — Registers a listener for trip since refuel driving distance updates in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripSinceRefillDrivingDistance(listener: DistanceDrivenListener)`
- `registerTripSinceRefillDrivingTime` — Registers Trip Since Refuel Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerTripSinceRefillDrivingTime(listener: DrivingTimeListener)`
- `setRangeRemainingDistance` — Sets the estimated remaining range in meters (m).
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_DRIVE") abstract fun setRangeRemainingDistance(value: Float, onComplete: (Float?) -> Unit = {}, onFailed: (Exception) -> Unit = {})` *(deprecated)*
- `unregisterDistanceDriven` — Stops getting property updates for the given DistanceDriven.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterDistanceDriven(listener: DistanceDrivenListener)`
- `unregisterTripCumulativeDrivingDistance` — Unregisters Trip Cumulative Driving Distance listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripCumulativeDrivingDistance(listener: DistanceDrivenListener)`
- `unregisterTripCumulativeDrivingTime` — Unregisters Trip Cumulative Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripCumulativeDrivingTime(listener: DrivingTimeListener)`
- `unregisterTripCurrentDrivingDistance` — Unregisters Trip Current Driving Distance listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripCurrentDrivingDistance(listener: DistanceDrivenListener)`
- `unregisterTripCurrentDrivingTime` — Unregisters Trip Current Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripCurrentDrivingTime(listener: DrivingTimeListener)`
- `unregisterTripInstantaneousDrivingDistance` — Unregisters Trip Instantaneous Driving Distance listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripInstantaneousDrivingDistance(listener: DistanceDrivenListener)` *(deprecated)*
- `unregisterTripSinceRefillDrivingDistance` — Unregisters Trip Since Refuel Driving Distance listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripSinceRefillDrivingDistance(listener: DistanceDrivenListener)`
- `unregisterTripSinceRefillDrivingTime` — Unregisters Trip Since Refuel Driving Time listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterTripSinceRefillDrivingTime(listener: DrivingTimeListener)`

### Safety

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/safety/entry

`interface Safety`
Provides APIs for airbags, warnings, fluid levels, and telltales. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSafety.

**Functions**
- `checkAirbagCapabilities` — Gets airbag capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkAirbagCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkAirbagCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets airbag capabilities.
- `checkAirbagDeployedCapabilities` — Gets airbag deployed capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkAirbagDeployedCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkBrakeFluidLevelStateCapabilities` — Gets brake fluid level state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkBrakeFluidLevelStateCapabilities(onComplete: (CapabilityConfig, List<OilLevelState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkEngineOilLevelStateCapabilities` — Gets engine oil level state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkEngineOilLevelStateCapabilities(onComplete: (CapabilityConfig, List<OilLevelState>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkLowWasherFluidWarningStateCapabilities` — Gets low washer fluid warning state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkLowWasherFluidWarningStateCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkPowerDownWarningIndicatorCapabilities` — Gets power down warning indicator capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkPowerDownWarningIndicatorCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatBuckledCapabilities` — Gets seat buckled capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkSeatBuckledCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkSeatBuckledCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets seat buckled capabilities.
- `checkSeatNotBuckledWarningCapabilities` — Gets seat not buckled warning capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkSeatNotBuckledWarningCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTelltalesCapabilities` — Gets telltales capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.PRIVATE_CAR_SAFETY") abstract fun checkTelltalesCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkTransmissionOilLevelStateCapabilities` — Gets transmission oil level state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkTransmissionOilLevelStateCapabilities(onComplete: (CapabilityConfig, List<OilLevelState>) -> Unit, onFailed: (Exception) -> Unit)`
- `getBrakeFluidLevelState` — Gets the current brake fluid level.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun getBrakeFluidLevelState(onComplete: (OilLevelState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getEngineOilLevelState` — Gets the current engine oil level.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun getEngineOilLevelState(onComplete: (OilLevelState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getTelltalesInfo` — Gets telltales info.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun getTelltalesInfo(onComplete: (telltales: Telltales) -> Unit, onFailed: (Exception) -> Unit)`
- `getTransmissionOilLevelState` — Gets the current transmission oil level.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun getTransmissionOilLevelState(onComplete: (OilLevelState?) -> Unit, onFailed: (Exception) -> Unit)`
- `hasLowWasherFluidWarning` — Checks the current washer fluid low warning status. If the status is true, there is an issue with the washer fluid
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun hasLowWasherFluidWarning(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `hasPowerDownWarningIndicator` — Gets the current power down warning indicator state. If the state is true, there is a problem with the engine.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun hasPowerDownWarningIndicator(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `hasSeatAirbagBeenDeployed` — Gets the current airbag deployment status. If the state is true, the airbag is deployed.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun hasSeatAirbagBeenDeployed(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `hasSeatAirbagEnabled` — Gets the seat airbag enabled state. If the state is true, there is a problem with the seat airbag in the area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun hasSeatAirbagEnabled(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `hasSeatBeltNotBuckledWarning` — Gets the seat belt buckled warning state. If the state is true, the seat belt in the area is not buckled.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun hasSeatBeltNotBuckledWarning(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerBrakeFluidLevelState` — Registers the BrakeFluidLevelState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerBrakeFluidLevelState(listener: OilLevelStateListener)`
- `registerEngineOilLevelState` — Registers the EngineOilLevelState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerEngineOilLevelState(listener: OilLevelStateListener)`
- `registerLowWasherFluidWaringState` — Registers the LowWasherFluidWaringState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerLowWasherFluidWaringState(listener: LowWasherFluidWaringListener)`
- `registerPowerDownWarningIndicator` — Registers the PowerDownWarningIndicator to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerPowerDownWarningIndicator(listener: PowerDownWarningIndicatorListener)`
- `registerSeatAirbagDeployed` — Registers the SeatAirbagDeployed to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerSeatAirbagDeployed(area: SeatArea, listener: SeatAirbagDeployedListener)`
- `registerSeatAirbagEnabled` — Registers the SeatAirbagEnabled to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerSeatAirbagEnabled(area: SeatArea, listener: SeatAirbagEnabledListener)`
- `registerSeatBeltNotBuckledWarning` — Registers the SeatBeltNotBuckledWarning to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerSeatBeltNotBuckledWarning(area: SeatArea, listener: SeatBeltNotBuckledWarningListener)`
- `registerTelltales` — Registers a Telltales listener to receive telltale information updates.
  - `@RequiresPermission(value = "pleos.car.permission.PRIVATE_CAR_SAFETY") abstract fun registerTelltales(listener: TelltaleInfoListener)`
- `registerTransmissionOilLevelState` — Registers the TransmissionOilLevel to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun registerTransmissionOilLevelState(listener: OilLevelStateListener)`
- `unregisterBrakeFluidLevelState` — Stops getting property updates for the given BrakeFluidLevelState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterBrakeFluidLevelState(listener: OilLevelStateListener)`
- `unregisterEngineOilLevelState` — Stops getting property updates for the given EngineOilLevelState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterEngineOilLevelState(listener: OilLevelStateListener)`
- `unregisterLowWasherFluidWaringState` — Stops getting property updates for the given LowWasherFluidWaringState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterLowWasherFluidWaringState(listener: LowWasherFluidWaringListener)`
- `unregisterPowerDownWarningIndicator` — Stops getting property updates for the given PowerDownWarningIndicator.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterPowerDownWarningIndicator(listener: PowerDownWarningIndicatorListener)`
- `unregisterSeatAirbagDeployed` — Stops getting property updates for the given SeatAirbagDeployed.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterSeatAirbagDeployed(area: SeatArea, listener: SeatAirbagDeployedListener)`
- `unregisterSeatAirbagEnabled` — Stops getting property updates for the given SeatAirbagEnabled.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterSeatAirbagEnabled(area: SeatArea, listener: SeatAirbagEnabledListener)`
- `unregisterSeatBeltNotBuckledWarning` — Stops getting property updates for the given SeatBeltNotBuckledWarning.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterSeatBeltNotBuckledWarning(area: SeatArea, listener: SeatBeltNotBuckledWarningListener)`
- `unregisterTelltales` — Unregisters a previously registered Telltales listener.
  - `@RequiresPermission(value = "pleos.car.permission.PRIVATE_CAR_SAFETY") abstract fun unregisterTelltales(listener: TelltaleInfoListener)`
- `unregisterTransmissionOilLevelState` — Stops getting property updates for the given TransmissionOilLevel.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun unregisterTransmissionOilLevelState(listener: OilLevelStateListener)`

### Seat

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/seat/entry

`interface Seat`
Provides APIs for seat position, occupancy, seat belt, and seat climate. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSeat.

**Functions**
- `checkMoveSeatBackrestAngleCapabilities` — Gets move seat BackrestAngle capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatBackrestAngleCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatBeltHeightCapabilities` — Gets move seat belt height capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatBeltHeightCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatDepthCapabilities` — Gets move seat depth capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatDepthCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatForeAftCapabilities` — Gets move seat ForeAft capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatForeAftCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatHeadrestAngleCapabilities` — Gets move seat headrest angle capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatHeadrestAngleCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatHeadrestForeAftCapabilities` — Gets move seat HeadrestForeAft capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatHeadrestForeAftCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatHeadrestHeightCapabilities` — Gets move seat headrest height capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatHeadrestHeightCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatHeightCapabilities` — Gets move seat height capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatHeightCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatLumbarForeAftCapabilities` — Gets move seat lumbar ForeAft capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatLumbarForeAftCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatLumbarSideSupportCapabilities` — Gets move seat lumbar side support capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatLumbarSideSupportCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkMoveSeatTiltCapabilities` — Gets move seat tilt capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkMoveSeatTiltCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatBackrestAngleCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBackrestAngleCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatBackrestAnglePositionCapabilities` — Gets seat BackrestAnglePosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBackrestAnglePositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatBeltBuckledCapabilities` — Gets seat belt buckled capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBeltBuckledCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBeltBuckledCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets seat belt buckled capabilities.
- `checkSeatBeltHeightCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBeltHeightCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatBeltHeightPositionCapabilities` — Gets seat belt height position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatBeltHeightPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatDepthCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatDepthCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatDepthPositionCapabilities` — Gets seat depth position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatDepthPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatForeAftCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatForeAftCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatForeAftPositionCapabilities` — Gets seat ForeAft position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatForeAftPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatHeadrestAngleCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestAngleCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatHeadrestAnglePositionCapabilities` — Gets seat HeadrestAnglePosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestAnglePositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatHeadrestForeAftCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestForeAftCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatHeadrestForeAftPositionCapabilities` — Gets seat HeadrestForeAftPosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestForeAftPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatHeadrestHeightCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestHeightCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatHeadrestHeightPositionCapabilities` — Gets seat HeadrestHeightPosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeadrestHeightPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatHeightCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeightCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatHeightPositionCapabilities` — Gets seat height position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatHeightPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatLumbarForeAftCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatLumbarForeAftCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatLumbarForeAftPositionCapabilities` — Gets seat LumbarForeAftPosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatLumbarForeAftPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatLumbarSideSupportCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatLumbarSideSupportCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatLumbarSideSupportPositionCapabilities` — Gets seat LumbarSideSupportPosition capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatLumbarSideSupportPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatMemoryCapabilities` — Gets seat memory capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatMemoryCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatOccupancyCapabilities` — Gets seat occupancy capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatOccupancyCapabilities(onComplete: (List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatOccupancyCapabilities(onComplete: (CapabilityConfig, List<SeatArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets seat occupancy capabilities.
- `checkSeatTemperatureCapabilities` — Gets seat temperature capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatTemperatureCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatTemperatureCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets seat temperature capabilities.
- `checkSeatTiltCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatTiltCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `checkSeatTiltPositionCapabilities` — Gets seat tilt position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatTiltPositionCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSeatVentilationCapabilities` — Gets seat ventilation capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatVentilationCapabilities(onComplete: (Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun checkSeatVentilationCapabilities(onComplete: (CapabilityConfig, Map<SeatArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets seat ventilation capabilities.
- `getCurrentSeatBackrestAnglePosition` — Gets the current seat backrest angle position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatBackrestAnglePosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatBeltBuckledState` — Gets the current seat belt buckled state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatBeltBuckledState(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatBeltHeightPosition` — Gets the current seat belt height position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatBeltHeightPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatDepthPosition` — Gets the current seat depth position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatDepthPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatForeAftPosition` — Gets the current seat fore/aft position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatForeAftPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatHeadrestAnglePosition` — Gets the current seat headrest angle position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatHeadrestAnglePosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatHeadrestForeAftPosition` — Gets current seat headrest fore/aft position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatHeadrestForeAftPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatHeadrestHeightAftPosition` — Gets the current seat headrest height aft position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatHeadrestHeightAftPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatHeightPosition` — Gets the current seat height position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatHeightPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatLumbarForeAftPosition` — Gets the current seat lumbar fore/aft position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatLumbarForeAftPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatLumbarSideSupportPosition` — Gets the seat lumbar side support position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatLumbarSideSupportPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatOccupancy` — Gets current seat occupancy.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatOccupancy(area: SeatArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSeatTiltPosition` — Gets the current seat tilt position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getCurrentSeatTiltPosition(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacSeatTemperature` — Gets the current seat heating/cooling temperature.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getHvacSeatTemperature(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacSeatVentilationState` — Gets the current seat ventilation state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun getHvacSeatVentilationState(area: SeatArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `moveSeatBackrestAngle` — Move seat backrestAngle.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatBackrestAngle(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatBeltHeight` — Moves the seat belt height.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatBeltHeight(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatDepth` — Moves the seat depth.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatDepth(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatForeAft` — Moves the seat fore/aft.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatForeAft(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatHeadrestAngle` — Moves the seat headrest angle.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatHeadrestAngle(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatHeadrestForeAft` — Moves seat headrest fore/aft.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatHeadrestForeAft(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatHeadrestHeight` — Moves seat headrest height.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatHeadrestHeight(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatHeight` — Moves the seat height.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatHeight(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatLumbarForeAft` — Moves seat lumbar fore/aft.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatLumbarForeAft(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatLumbarSideSupport` — Moves the seat lumbar side support.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatLumbarSideSupport(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `moveSeatTilt` — Moves seat tilt.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun moveSeatTilt(area: SeatArea, value: MoveControl, onComplete: (SeatArea, MoveControl?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `registerHvacSeatTemperature` — Registers the HvacSeatTemperature to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerHvacSeatTemperature(area: SeatArea, listener: HvacSeatTemperatureListener)`
- `registerHvacSeatVentilation` — Registers the HvacSeatVentilation to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerHvacSeatVentilation(area: SeatArea, listener: HvacSeatVentilationListener)`
- `registerSeatBackrestAnglePosition` — Registers the SeatBackrestAnglePosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatBackrestAnglePosition(area: SeatArea, listener: SeatBackrestAnglePositionListener)`
- `registerSeatBeltBuckledState` — Registers the SeatBeltBuckledState to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatBeltBuckledState(area: SeatArea, listener: SeatBeltBuckledStateListener)`
- `registerSeatBeltHeightPosition` — Registers the SeatBeltHeightPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatBeltHeightPosition(area: SeatArea, listener: SeatBeltHeightPositionListener)`
- `registerSeatDepthPosition` — Registers the SeatDepthPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatDepthPosition(area: SeatArea, listener: SeatDepthPositionListener)`
- `registerSeatForeAftPosition` — Registers the SeatForeAftPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatForeAftPosition(area: SeatArea, listener: SeatForeAftPositionListener)`
- `registerSeatHeadrestAnglePosition` — Registers the SeatHeadrestAnglePosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatHeadrestAnglePosition(area: SeatArea, listener: SeatHeadrestAnglePositionListener)`
- `registerSeatHeadrestForeAftPosition` — Registers the SeatHeadrestForeAftPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatHeadrestForeAftPosition(area: SeatArea, listener: SeatHeadrestForeAftPositionListener)`
- `registerSeatHeadrestHeightAftPosition` — Registers the SeatHeadrestHeightAftPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatHeadrestHeightAftPosition(area: SeatArea, listener: SeatHeadrestHeightAftPositionListener)`
- `registerSeatHeightPosition` — Registers the SeatHeightPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatHeightPosition(area: SeatArea, listener: SeatHeightPositionListener)`
- `registerSeatLumbarForeAftPosition` — Registers the SeatLumbarForeAftPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatLumbarForeAftPosition(area: SeatArea, listener: SeatLumbarForeAftPositionListener)`
- `registerSeatLumbarSideSupportPosition` — Registers the SeatLumbarSideSupportPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatLumbarSideSupportPosition(area: SeatArea, listener: SeatLumbarSideSupportPositionListener)`
- `registerSeatOccupancy` — Registers the SeatOccupancy to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatOccupancy(area: SeatArea, listener: SeatOccupancyListener)`
- `registerSeatTiltPosition` — Registers the SeatTiltPosition to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun registerSeatTiltPosition(area: SeatArea, listener: SeatTiltPositionListener)`
- `selectSeatMemory` — Selects seat memory.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun selectSeatMemory(area: SeatArea, value: Int, onFailed: (Exception) -> Unit = {})`
- `setHvacSeatTemperature` — Sets the seat heating/cooling temperature.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun setHvacSeatTemperature(area: SeatArea, value: Int, onComplete: (SeatArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setHvacSeatVentilation` — Sets the seat ventilation.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_SEATS") abstract fun setHvacSeatVentilation(area: SeatArea, value: Int, onComplete: (SeatArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `unregisterHvacSeatTemperature` — Stops getting property updates for the given HvacSeatTemperature.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterHvacSeatTemperature(area: SeatArea, listener: HvacSeatTemperatureListener)`
- `unregisterHvacSeatVentilation` — Stops getting property updates for the given HvacSeatVentilation.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterHvacSeatVentilation(area: SeatArea, listener: HvacSeatVentilationListener)`
- `unregisterSeatBackrestAnglePosition` — Stops getting property updates for the given SeatBackrestAnglePosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatBackrestAnglePosition(area: SeatArea, listener: SeatBackrestAnglePositionListener)`
- `unregisterSeatBeltBuckledState` — Stops getting property updates for the given SeatBeltBuckledState.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatBeltBuckledState(area: SeatArea, listener: SeatBeltBuckledStateListener)`
- `unregisterSeatBeltHeightPosition` — Stops getting property updates for the given SeatBeltHeightPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatBeltHeightPosition(area: SeatArea, listener: SeatBeltHeightPositionListener)`
- `unregisterSeatDepthPosition` — Stops getting property updates for the given SeatDepthPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatDepthPosition(area: SeatArea, listener: SeatDepthPositionListener)`
- `unregisterSeatForeAftPosition` — Stops getting property updates for the given SeatForeAftPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatForeAftPosition(area: SeatArea, listener: SeatForeAftPositionListener)`
- `unregisterSeatHeadrestAnglePosition` — Stops getting property updates for the given SeatHeadrestAnglePosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatHeadrestAnglePosition(area: SeatArea, listener: SeatHeadrestAnglePositionListener)`
- `unregisterSeatHeadrestForeAftPosition` — Stops getting property updates for the given SeatHeadrestForeAftPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatHeadrestForeAftPosition(area: SeatArea, listener: SeatHeadrestForeAftPositionListener)`
- `unregisterSeatHeadrestHeightAftPosition` — Stops getting property updates for the given SeatHeadrestHeightAftPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatHeadrestHeightAftPosition(area: SeatArea, listener: SeatHeadrestHeightAftPositionListener)`
- `unregisterSeatHeightPosition` — Stops getting property updates for the given SeatHeightPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatHeightPosition(area: SeatArea, listener: SeatHeightPositionListener)`
- `unregisterSeatLumbarForeAftPosition` — Stops getting property updates for the given SeatLumbarForeAftPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatLumbarForeAftPosition(area: SeatArea, listener: SeatLumbarForeAftPositionListener)`
- `unregisterSeatLumbarSideSupportPosition` — Stops getting property updates for the given SeatLumbarSideSupportPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatLumbarSideSupportPosition(area: SeatArea, listener: SeatLumbarSideSupportPositionListener)`
- `unregisterSeatOccupancy` — Stops getting property updates for the given SeatOccupancy.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatOccupancy(area: SeatArea, listener: SeatOccupancyListener)`
- `unregisterSeatTiltPosition` — Stops getting property updates for the given SeatTiltPosition.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SEATS") abstract fun unregisterSeatTiltPosition(area: SeatArea, listener: SeatTiltPositionListener)`

### SideMirror

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/side-mirror/entry

`interface SideMirror`
Provides APIs for side mirror heat. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSideMirror.

**Functions**
- `checkHvacSideMirrorHeatCapabilities` — Gets hvac side mirror heat capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_MIRRORS") abstract fun checkHvacSideMirrorHeatCapabilities(onComplete: (Map<MirrorArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_MIRRORS") abstract fun checkHvacSideMirrorHeatCapabilities(onComplete: (CapabilityConfig, Map<MirrorArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets hvac side mirror heat capabilities.
- `getHvacSideMirrorHeatState` — Gets the current side mirror heat state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_MIRRORS") abstract fun getHvacSideMirrorHeatState(area: MirrorArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerHvacSideMirrorHeatState` — Registers HvacSideMirrorHeatState state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacSideMirrorHeatState(area: MirrorArea, listener: HvacSideMirrorHeatStateListener)`
- `setHvacSideMirrorHeat` — Sets the side mirror heat state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_MIRRORS") abstract fun setHvacSideMirrorHeat(area: MirrorArea, value: Int, onComplete: (MirrorArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `unregisterHvacSideMirrorHeatState` — Stops getting property updates for the given HvacSideMirrorHeatState state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacSideMirrorHeatState(area: MirrorArea, listener: HvacSideMirrorHeatStateListener)`

### SteeringWheel

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/steering-wheel/entry

`interface SteeringWheel`
Provides APIs for the front-wheel steering angle and steering wheel heating. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getSteeringWheel.

**Functions**
- `checkHvacSteeringWheelHeatCapabilities` — Gets hvac steering wheel heat capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacSteeringWheelHeatCapabilities(onComplete: (AreaBounds<Int>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun checkHvacSteeringWheelHeatCapabilities(onComplete: (CapabilityConfig, AreaBounds<Int>) -> Unit, onFailed: (Exception) -> Unit)` — Gets hvac steering wheel heat capabilities.
- `checkSteeringWheelAngleCapabilities` — Gets the capabilities for the front-wheel steering angle.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun checkSteeringWheelAngleCapabilities(onComplete: (CapabilityConfig, AreaBounds<Float>) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSteeringWheelAngle` — Gets the current front-wheel steering angle in degrees.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun getCurrentSteeringWheelAngle(onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`
- `getHvacSteeringWheelHeatState` — Gets the current SteeringWheel heating/cooling state. Permission: PleosPermission.CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun getHvacSteeringWheelHeatState(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerHvacSteeringWheelHeatState` — Registers Hvac SteeringWheel heat state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun registerHvacSteeringWheelHeatState(listener: HvacSteeringWheelHeatStateListener)`
- `registerSteeringWheelAngle` — Registers a listener to receive front-wheel steering angle updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun registerSteeringWheelAngle(listener: SteeringWheelAngleListener)`
- `setHvacSteeringWheelHeat` — Sets SteeringWheel heating/cooling temperature. Permission: PleosPermission.CONTROL_CAR_CLIMATE (protection level: normal)
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_CLIMATE") abstract fun setHvacSteeringWheelHeat(value: Int, onComplete: (Int?) -> Unit = {}, onFailed: (Exception) -> Unit = {})`
- `unregisterHvacSteeringWheelHeatState` — Stops getting property updates for the given Hvac SteeringWheel heat state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_CLIMATE") abstract fun unregisterHvacSteeringWheelHeatState(listener: HvacSteeringWheelHeatStateListener)`
- `unregisterSteeringWheelAngle` — Unregisters the given listener from front-wheel steering angle updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_DRIVE") abstract fun unregisterSteeringWheelAngle(listener: SteeringWheelAngleListener)`

### Tire

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/tire/entry

`interface Tire`
Provides APIs for tire pressure. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getTire.

**Functions**
- `checkLowTirePressureCapabilities` — Gets low tire pressure capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkLowTirePressureCapabilities(onComplete: (CapabilityConfig, List<WheelArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkWheelAreaCapabilities`
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun checkWheelAreaCapabilities(onComplete: (List<WheelArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
- `getLowTirePressure` — Gets the critically low tire pressure threshold in kilopascals (kPa).
  - `@RequiresPermission(value = "pleos.car.permission.CAR_SAFETY") abstract fun getLowTirePressure(area: WheelArea, onComplete: (Float?) -> Unit, onFailed: (Exception) -> Unit)`

### Toll

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/toll/entry

`interface Toll`
Provides APIs for ETCS voice guidance volume. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getToll.

**Functions**
- `checkEtcsVoiceGuidanceVolumeCapabilities` — Gets ETCS voice guidance volume capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_TOLL") abstract fun checkEtcsVoiceGuidanceVolumeCapabilities(onComplete: (CapabilityConfig, AreaBounds<Int>) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentEtcsVoiceGuidanceVolume` — Gets current ETCS voice guidance volume.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_TOLL") abstract fun getCurrentEtcsVoiceGuidanceVolume(onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerEtcsVoiceGuidanceVolume` — Registers ETCS voice guidance volume updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_TOLL") abstract fun registerEtcsVoiceGuidanceVolume(listener: EtcsVoiceGuidanceVolumeListener)`
- `setEtcsVoiceGuidanceVolume` — Sets ETCS voice guidance volume.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_TOLL") abstract fun setEtcsVoiceGuidanceVolume(value: Int, onComplete: () -> Unit, onFailed: (Exception) -> Unit)`
- `unregisterEtcsVoiceGuidanceVolume` — Stops ETCS voice guidance volume updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_TOLL") abstract fun unregisterEtcsVoiceGuidanceVolume(listener: EtcsVoiceGuidanceVolumeListener)`

### TurnSignal

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/turn-signal/entry

`interface TurnSignal`
Provides APIs for turn signal state. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getTurnSignal.

**Functions**
- `checkTurnSignalStateCapabilities` — Gets turn signal state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun checkTurnSignalStateCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentTurnSignalState` — Gets the current turn signal state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun getCurrentTurnSignalState(onComplete: (TurnSignalValue?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerTurnSignalState` — Registers the turn signal state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun registerTurnSignalState(listener: TurnSignalStateListener)`
- `unregisterTurnSignalState` — Stops getting property updates for the given turn signal state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_LIGHTS") abstract fun unregisterTurnSignalState(listener: TurnSignalStateListener)`

### Window

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/window/entry

`interface Window`
Provides APIs for window position, lock, blinds, and smart glass. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getWindow.

**Functions**
- `checkMoveWindowBlindCapabilities` — Gets move window blind capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkMoveWindowBlindCapabilities(onComplete: (CapabilityConfig, List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSmartGlassLevelCapabilities` — Gets smart glass level capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkSmartGlassLevelCapabilities(onComplete: (CapabilityConfig, Map<WindowArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSmartGlassStateCapabilities` — Gets smart glass state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkSmartGlassStateCapabilities(onComplete: (CapabilityConfig) -> Unit, onFailed: (Exception) -> Unit)`
- `checkSmartGlassZoneStateCapabilities` — Gets smart glass zone state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkSmartGlassZoneStateCapabilities(onComplete: (CapabilityConfig, List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkWindowBlindPositionCapabilities` — Gets window blind position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWindowBlindPositionCapabilities(onComplete: (CapabilityConfig, Map<WindowArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)`
- `checkWindowLockCapabilities` — Gets window lock capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWindowLockCapabilities(onComplete: (List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWindowLockCapabilities(onComplete: (CapabilityConfig, List<WindowArea>) -> Unit, onFailed: (Exception) -> Unit)` — Gets window lock capabilities.
- `checkWindowPositionCapabilities` — Gets window position capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWindowPositionCapabilities(onComplete: (Map<WindowArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWindowPositionCapabilities(onComplete: (CapabilityConfig, Map<WindowArea, AreaBounds<Int>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets window position capabilities.
- `getCurrentSmartGlassLevel` — Gets the current Smart Glass Level for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentSmartGlassLevel(area: WindowArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentSmartGlassState` — Gets the current Smart Glass state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentSmartGlassState(onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentWindowBlindPosition` — Gets the current Window Blind Position for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentWindowBlindPosition(area: WindowArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentWindowLockState` — Gets the current window lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentWindowLockState(area: WindowArea, onComplete: (Boolean?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentWindowPosition` — Gets the current window position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentWindowPosition(area: WindowArea, onComplete: (Int?) -> Unit, onFailed: (Exception) -> Unit)`
- `getSmartGlassConfig` — Gets the Smart Glass configuration for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getSmartGlassConfig(area: WindowArea, onComplete: (SmartGlassConfig?) -> Unit, onFailed: (Exception) -> Unit)`
- `moveWindowBlind` — Moves the Window Blind for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun moveWindowBlind(area: WindowArea, value: MoveControl, onComplete: (WindowArea, MoveControl?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerSmartGlassLevel` — Registers a listener to receive Smart Glass Level updates for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerSmartGlassLevel(area: WindowArea, listener: SmartGlassLevelListener)`
- `registerSmartGlassState` — Registers Smart Glass state listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerSmartGlassState(listener: SmartGlassStateListener)`
- `registerWindowBlindPosition` — Registers a listener to receive Window Blind Position updates for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerWindowBlindPosition(area: WindowArea, listener: WindowPositionListener)`
- `registerWindowLockState` — Registers the window lock state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerWindowLockState(area: WindowArea, listener: WindowLockStateListener)`
- `registerWindowPosition` — Registers the window area to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerWindowPosition(area: WindowArea, listener: WindowPositionListener)`
- `setSmartGlassLevel` — Sets the Smart Glass Level for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setSmartGlassLevel(area: WindowArea, value: Int, onComplete: (WindowArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setSmartGlassState` — Sets the Smart Glass state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setSmartGlassState(value: Boolean, onComplete: (Boolean?) -> Unit = { _ -> }, onFailed: (Exception) -> Unit = {})`
- `setSmartGlassZoneState` — Sets the Smart Glass zone state by submitting one command per smart glass panel for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setSmartGlassZoneState(area: WindowArea, value: List<SmartGlassZoneCommand>, onComplete: (WindowArea, List<SmartGlassZoneCommand>?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setWindowLock` — Sets the current window lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setWindowLock(area: WindowArea, value: Boolean, onComplete: (WindowArea, Boolean?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `setWindowPosition` — Sets the current window position state.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setWindowPosition(area: WindowArea, value: Int, onComplete: (WindowArea, Int?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `unregisterSmartGlassLevel` — Unregisters a previously registered Smart Glass Level listener for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterSmartGlassLevel(area: WindowArea, listener: SmartGlassLevelListener)`
- `unregisterSmartGlassState` — Unregisters Smart Glass state listener.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterSmartGlassState(listener: SmartGlassStateListener)`
- `unregisterWindowBlindPosition` — Unregisters a previously registered Window Blind Position listener for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterWindowBlindPosition(area: WindowArea, listener: WindowPositionListener)`
- `unregisterWindowLockState` — Stops getting property updates for the given window lock state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterWindowLockState(area: WindowArea, listener: WindowLockStateListener)`
- `unregisterWindowPosition` — Stops getting property updates for the given window position.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterWindowPosition(area: WindowArea, listener: WindowPositionListener)`

### Wiper

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/wiper/entry

`interface Wiper`
Provides APIs for wiper state and switch. Obtain an instance from ai.pleos.playground.vehicle.Vehicle.getWiper.

**Functions**
- `checkWiperStateCapabilities` — Gets wiper state capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWiperStateCapabilities(onComplete: (Map<WindowArea, List<WiperState>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWiperStateCapabilities(onComplete: (CapabilityConfig, Map<WindowArea, List<WiperState>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets wiper state capabilities.
- `checkWiperSwitchCapabilities` — Gets wiper switch capabilities.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWiperSwitchCapabilities(onComplete: (Map<WindowArea, List<WiperSwitch>>) -> Unit, onFailed: (Exception) -> Unit)` *(deprecated)*
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun checkWiperSwitchCapabilities(onComplete: (CapabilityConfig, Map<WindowArea, List<WiperSwitch>>) -> Unit, onFailed: (Exception) -> Unit)` — Gets wiper switch capabilities.
- `getCurrentWiperState` — Gets the current wiper mode state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentWiperState(area: WindowArea, onComplete: (WiperState?) -> Unit, onFailed: (Exception) -> Unit)`
- `getCurrentWiperSwitch` — Gets the current Wiper Switch state for the specified window area.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun getCurrentWiperSwitch(area: WindowArea, onComplete: (WiperSwitch?) -> Unit, onFailed: (Exception) -> Unit)`
- `registerWiperState` — Registers the wiper state to get property updates.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun registerWiperState(area: WindowArea, listener: WiperStateListener)`
- `setWiperSwitch` — Sets the current wiper switch.
  - `@RequiresPermission(value = "pleos.car.permission.CONTROL_CAR_WINDOWS") abstract fun setWiperSwitch(area: WindowArea, value: WiperSwitch, onComplete: (WindowArea, WiperSwitch?) -> Unit = { _, _ -> }, onFailed: (Exception) -> Unit = {})`
- `unregisterWiperState` — Stops getting property updates for the given wiper state.
  - `@RequiresPermission(value = "pleos.car.permission.CAR_WINDOWS") abstract fun unregisterWiperState(area: WindowArea, listener: WiperStateListener)`


---

## 패키지 `ai.pleos.playground.vehicle.callback.exception`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.callback.exception/entry


**Types**
- `VehicleException` — Exception type used by the vehicle SDK.
  - `class VehicleException(error: String? = null): Exception`

### VehicleException

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.callback.exception/vehicle-exception/entry

`class VehicleException(error: String? = null): Exception`
Exception type used by the vehicle SDK.

**Parameters**
- `error` — The error value used to create the exception.

**Constructors**
- `VehicleException`
  - `constructor(error: String? = null)`

**Properties**
- `errorCode`
  - `val errorCode: ErrorCode`
- `message`
  - `open override val message: String`


---

## 패키지 `ai.pleos.playground.vehicle.constant.area`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/entry


**Types**
- `AreaValue`
  - `interface AreaValue`
- `DoorValue` — Represents available door area values.
  - `enum DoorValue: Enum<DoorValue>, AreaValue`
- `MirrorValue` — Represents available mirror area values.
  - `enum MirrorValue: Enum<MirrorValue>, AreaValue`
- `SeatValue` — Represents available seat area values.
  - `enum SeatValue: Enum<SeatValue>, AreaValue`
- `TurnSignalValue` — Represents available turn signal area values.
  - `enum TurnSignalValue: Enum<TurnSignalValue>, AreaValue`
- `WheelValue` — Represents available wheel area values.
  - `enum WheelValue: Enum<WheelValue>, AreaValue`
- `WindowValue` — Represents available window area values.
  - `enum WindowValue: Enum<WindowValue>, AreaValue`

### AreaValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/area-value/entry

`interface AreaValue`
**Inheritors**
- `DoorValue`
- `MirrorValue`
- `SeatValue`
- `TurnSignalValue`
- `WheelValue`
- `WindowValue`

**Properties**
- `id`
  - `abstract val id: Int`

### DoorValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/door-value/entry

`enum DoorValue: Enum<DoorValue>, AreaValue`
Represents available door area values.

**Entries**
- `ROW_1_LEFT`
- `ROW_1_RIGHT`
- `ROW_2_LEFT`
- `ROW_2_RIGHT`
- `ROW_3_LEFT`
- `ROW_3_RIGHT`
- `HOOD`
- `REAR`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<DoorValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): DoorValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<DoorValue>`

### MirrorValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/mirror-value/entry

`enum MirrorValue: Enum<MirrorValue>, AreaValue`
Represents available mirror area values.

**Entries**
- `DRIVER_LEFT`
- `DRIVER_RIGHT`
- `DRIVER_CENTER`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<MirrorValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): MirrorValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<MirrorValue>`

### SeatValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/seat-value/entry

`enum SeatValue: Enum<SeatValue>, AreaValue`
Represents available seat area values.

**Entries**
- `ROW_1_LEFT`
- `ROW_1_CENTER`
- `ROW_1_RIGHT`
- `ROW_2_LEFT`
- `ROW_2_CENTER`
- `ROW_2_RIGHT`
- `ROW_3_LEFT`
- `ROW_3_CENTER`
- `ROW_3_RIGHT`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<SeatValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): SeatValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<SeatValue>`

### TurnSignalValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/turn-signal-value/entry

`enum TurnSignalValue: Enum<TurnSignalValue>, AreaValue`
Represents available turn signal area values.

**Entries**
- `OFF`
- `RIGHT`
- `LEFT`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TurnSignalValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TurnSignalValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TurnSignalValue>`

### WheelValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/wheel-value/entry

`enum WheelValue: Enum<WheelValue>, AreaValue`
Represents available wheel area values.

**Entries**
- `UNKNOWN`
- `LEFT_FRONT`
- `RIGHT_FRONT`
- `LEFT_REAR`
- `RIGHT_REAR`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WheelValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WheelValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WheelValue>`

### WindowValue

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.area/window-value/entry

`enum WindowValue: Enum<WindowValue>, AreaValue`
Represents available window area values.

**Entries**
- `FRONT_WINDSHIELD`
- `REAR_WINDSHIELD`
- `ROW_1_LEFT`
- `ROW_1_RIGHT`
- `ROW_2_LEFT`
- `ROW_2_RIGHT`
- `ROOF_TOP_1`
- `ROOF_TOP_2`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WindowValue>`
- `id`
  - `open override val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WindowValue`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WindowValue>`


---

## 패키지 `ai.pleos.playground.vehicle.constant.control`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/entry


**Types**
- `CruiseControl` — Represents the cruise control state.
  - `enum CruiseControl: Enum<CruiseControl>`
- `DrivingModeType` — Represents the driving mode.
  - `enum DrivingModeType: Enum<DrivingModeType>`
- `EvChargeState` — Represents the EV charge state.
  - `enum EvChargeState: Enum<EvChargeState>`
- `EvConnectorType` — Represents the EV charging connector type.
  - `enum EvConnectorType: Enum<EvConnectorType>`
- `EvRegenerativeBrakingState` — Represents the EV regenerative braking state.
  - `enum EvRegenerativeBrakingState: Enum<EvRegenerativeBrakingState>`
- `FanDirectionType` — Represents the HVAC fan direction.
  - `enum FanDirectionType: Enum<FanDirectionType>`
- `FuelType` — Represents the fuel type.
  - `enum FuelType: Enum<FuelType>`
- `GsrComplianceType` — Represents the GSR compliance type.
  - `enum GsrComplianceType: Enum<GsrComplianceType>`
- `LightState` — Represents the light state.
  - `enum LightState: Enum<LightState>`
- `LightSwitch` — Represents the light switch state.
  - `enum LightSwitch: Enum<LightSwitch>`
- `MoveControl` — Represents the direction used for movement control.
  - `enum MoveControl: Enum<MoveControl>`
- `OilLevelState` — Represents the oil level state.
  - `enum OilLevelState: Enum<OilLevelState>`
- `PortLocationType` — Represents the charge port location.
  - `enum PortLocationType: Enum<PortLocationType>`
- `SmartGlassZoneCommand` — Represents the command for a single smart glass panel.
  - `enum SmartGlassZoneCommand: Enum<SmartGlassZoneCommand>`
- `SteeringControl` — Represents the steering control state.
  - `enum SteeringControl: Enum<SteeringControl>`
- `TripStatisticType` — Represents the type of trip statistic.
  - `enum TripStatisticType: Enum<TripStatisticType>`
- `WiperState` — Represents the wiper state.
  - `enum WiperState: Enum<WiperState>`
- `WiperSwitch` — Represents the wiper switch state.
  - `enum WiperSwitch: Enum<WiperSwitch>`

### CruiseControl

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/cruise-control/entry

`enum CruiseControl: Enum<CruiseControl>`
Represents the cruise control state.

**Entries**
- `OTHER`
- `ENABLED`
- `ACTIVATED`
- `USER_OVERRIDE`
- `SUSPENDED`
- `FORCED_DEACTIVATION_WARNING`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<CruiseControl>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): CruiseControl`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<CruiseControl>`

### DrivingModeType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/driving-mode-type/entry

`enum DrivingModeType: Enum<DrivingModeType>`
Represents the driving mode.

**Entries**
- `NORMAL`
- `ECO`
- `SPORT`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<DrivingModeType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): DrivingModeType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<DrivingModeType>`

### EvChargeState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/ev-charge-state/entry

`enum EvChargeState: Enum<EvChargeState>`
Represents the EV charge state.

**Entries**
- `UNKNOWN`
- `CHARGING`
- `FULLY_CHARGED`
- `NOT_CHARGING`
- `ERROR`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<EvChargeState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): EvChargeState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<EvChargeState>`

### EvConnectorType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/ev-connector-type/entry

`enum EvConnectorType: Enum<EvConnectorType>`
Represents the EV charging connector type.

**Entries**
- `UNKNOWN`
- `IEC_TYPE_1_AC`
- `IEC_TYPE_2_AC`
- `IEC_TYPE_3_AC`
- `IEC_TYPE_4_DC`
- `IEC_TYPE_1_CCS_DC`
- `IEC_TYPE_2_CCS_DC`
- `TESLA_ROADSTER`
- `TESLA_HPWC`
- `TESLA_SUPERCHARGER`
- `GBT_AC`
- `GBT_DC`
- `OTHER`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<EvConnectorType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): EvConnectorType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<EvConnectorType>`

### EvRegenerativeBrakingState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/ev-regenerative-braking-state/entry

`enum EvRegenerativeBrakingState: Enum<EvRegenerativeBrakingState>`
Represents the EV regenerative braking state.

**Entries**
- `UNKNOWN`
- `DISABLED`
- `PARTIALLY_ENABLED`
- `FULLY_ENABLED`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<EvRegenerativeBrakingState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): EvRegenerativeBrakingState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<EvRegenerativeBrakingState>`

### FanDirectionType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/fan-direction-type/entry

`enum FanDirectionType: Enum<FanDirectionType>`
Represents the HVAC fan direction.

**Entries**
- `UNKNOWN`
- `FACE`
- `FLOOR`
- `FACE_AND_FLOOR`
- `DEFROST`
- `FACE_AND_DEFROST`
- `DEFROST_AND_FLOOR`
- `FACE_AND_FLOOR_AND_DEFROST`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<FanDirectionType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): FanDirectionType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<FanDirectionType>`

### FuelType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/fuel-type/entry

`enum FuelType: Enum<FuelType>`
Represents the fuel type.

**Entries**
- `BIODIESEL`
- `CNG`
- `DIESEL_1`
- `DIESEL_2`
- `E85`
- `ELECTRIC`
- `HYDROGEN`
- `LEADED`
- `LNG`
- `LPG`
- `OTHER`
- `UNKNOWN`
- `UNLEADED`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<FuelType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): FuelType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<FuelType>`

### GsrComplianceType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/gsr-compliance-type/entry

`enum GsrComplianceType: Enum<GsrComplianceType>`
Represents the GSR compliance type.

**Entries**
- `GSR_COMPLIANCE_TYPE_NOT_REQUIRED`
- `GSR_COMPLIANCE_TYPE_REQUIRED_V1`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<GsrComplianceType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): GsrComplianceType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<GsrComplianceType>`

### LightState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/light-state/entry

`enum LightState: Enum<LightState>`
Represents the light state.

**Entries**
- `OFF`
- `ON`
- `DAYTIME_RUNNING`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<LightState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): LightState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<LightState>`

### LightSwitch

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/light-switch/entry

`enum LightSwitch: Enum<LightSwitch>`
Represents the light switch state.

**Entries**
- `OFF`
- `ON`
- `DAYTIME_RUNNING`
- `AUTOMATIC`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<LightSwitch>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): LightSwitch`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<LightSwitch>`

### MoveControl

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/move-control/entry

`enum MoveControl: Enum<MoveControl>`
Represents the direction used for movement control.

**Entries**
- `BACKWARD`
- `STOP`
- `FORWARD`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<MoveControl>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): MoveControl`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<MoveControl>`

### OilLevelState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/oil-level-state/entry

`enum OilLevelState: Enum<OilLevelState>`
Represents the oil level state.

**Entries**
- `LOW`
- `HIGH`
- `NORMAL`
- `ERROR`
- `CRITICALLY_LOW`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<OilLevelState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): OilLevelState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<OilLevelState>`

### PortLocationType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/port-location-type/entry

`enum PortLocationType: Enum<PortLocationType>`
Represents the charge port location.

**Entries**
- `UNKNOWN`
- `FRONT_LEFT`
- `FRONT_RIGHT`
- `REAR_RIGHT`
- `REAR_LEFT`
- `FRONT`
- `REAR`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<PortLocationType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): PortLocationType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<PortLocationType>`

### SmartGlassZoneCommand

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/smart-glass-zone-command/entry

`enum SmartGlassZoneCommand: Enum<SmartGlassZoneCommand>`
Represents the command for a single smart glass panel. Used by ai.pleos.playground.vehicle.api.Window.setSmartGlassZoneState with one entry per panel in panel order.

**Entries**
- `SKIP` — Requests no command for the panel and leaves it unchanged.
- `OFF` — Turns the panel off.
- `ON` — Turns the panel on.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<SmartGlassZoneCommand>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): SmartGlassZoneCommand`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<SmartGlassZoneCommand>`

### SteeringControl

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/steering-control/entry

`enum SteeringControl: Enum<SteeringControl>`
Represents the steering control state.

**Entries**
- `OFF`
- `READY`
- `ACTIVE`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<SteeringControl>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): SteeringControl`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<SteeringControl>`

### TripStatisticType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/trip-statistic-type/entry

`enum TripStatisticType: Enum<TripStatisticType>`
Represents the type of trip statistic.

**Entries**
- `CURRENT`
- `INSTANTANEOUS`
- `CUMULATIVE`
- `SINCE_REFILL`

**Types**
- `Companion`
  - `object Companion`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TripStatisticType>`
- `id`
  - `val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TripStatisticType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TripStatisticType>`

### WiperState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/wiper-state/entry

`enum WiperState: Enum<WiperState>`
Represents the wiper state.

**Entries**
- `OTHER`
- `OFF`
- `ON`
- `SERVICE`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WiperState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WiperState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WiperState>`

### WiperSwitch

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.control/wiper-switch/entry

`enum WiperSwitch: Enum<WiperSwitch>`
Represents the wiper switch state.

**Entries**
- `OTHER`
- `OFF`
- `MIST`
- `INTERMITTENT_LEVEL_1`
- `INTERMITTENT_LEVEL_2`
- `INTERMITTENT_LEVEL_3`
- `INTERMITTENT_LEVEL_4`
- `INTERMITTENT_LEVEL_5`
- `CONTINUOUS_LEVEL_1`
- `CONTINUOUS_LEVEL_2`
- `CONTINUOUS_LEVEL_3`
- `CONTINUOUS_LEVEL_4`
- `CONTINUOUS_LEVEL_5`
- `AUTO`
- `SERVICE`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WiperSwitch>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WiperSwitch`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WiperSwitch>`


---

## 패키지 `ai.pleos.playground.vehicle.constant.telltale`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.telltale/entry


**Types**
- `TelltaleColor` — Represents the telltale color.
  - `enum TelltaleColor: Enum<TelltaleColor>`
- `TelltaleState` — Represents the telltale state.
  - `enum TelltaleState: Enum<TelltaleState>`
- `TelltaleType` — Represents the telltale type.
  - `enum TelltaleType: Enum<TelltaleType>`

### TelltaleColor

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.telltale/telltale-color/entry

`enum TelltaleColor: Enum<TelltaleColor>`
Represents the telltale color.

**Entries**
- `WHITE`
- `GREEN`
- `BLUE`
- `YELLOW`
- `RED`
- `GRAY`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TelltaleColor>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TelltaleColor`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TelltaleColor>`

### TelltaleState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.telltale/telltale-state/entry

`enum TelltaleState: Enum<TelltaleState>`
Represents the telltale state.

**Entries**
- `OFF`
- `ON`
- `BLINK`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TelltaleState>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TelltaleState`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TelltaleState>`

### TelltaleType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.telltale/telltale-type/entry

`enum TelltaleType: Enum<TelltaleType>`
Represents the telltale type.

**Entries**
- `PARKING_BRAKE`
- `ELECTRONIC_PARKING_BRAKE`
- `SEAT_BELT`
- `DOOR_OPEN`
- `ABS`
- `MDPS`
- `TPMS`
- `LOW_VOLT_BATTERY`
- `ESC`
- `ESC_OFF`
- `AIRBAG`
- `AFS`
- `MASTER_WARNING`
- `AUTO_HOLD`
- `AUTO_HIGH_BEAM`
- `HEADLAMP_LOW`
- `HEADLAMP_HIGH`
- `POSITION_LAMP`
- `FRONT_FOG_LAMP`
- `REAR_FOG_LAMP`
- `ENGINE_OIL_PRESSURE`
- `ENGINE_TEMPERATURE`
- `EMERGENCY_CALL`
- `EV_SYSTEM`
- `POWER_DOWN`
- `RBS`
- `IMMOBILIZER`
- `ENGINE`
- `LED_HEADLAMP`
- `PREHEAT`
- `PARTICLE_FILTER`
- `H2_SENSOR`
- `H2_LEAK`
- `PASSENGER_AIRBAG_OFF`
- `PASSENGER_AIRBAG_ON`
- `TURN_SIGNAL_LEFT`
- `TURN_SIGNAL_RIGHT`
- `ICY_ROAD`
- `OVER_SPEED`
- `WATER_SEPARATOR`
- `SCR`
- `LOW_EV_BATTERY`
- `HEV_SYSTEM`
- `FCEV_SYSTEM`
- `EREV_SYSTEM`
- `LPI`
- `EV_CHARGING_CONNECTOR`
- `AWD_LOCK`
- `HEV_MODE`
- `ISG`
- `DBC`
- `REV`
- `XEV_READY`
- `I_PEDAL`
- `LOW_FUEL`
- `LOW_FUEL_FCEV`
- `EV_BATTERY_CONDITIONING`
- `TRUNK_OPEN`
- `CHILD_PRESENCE_DETECTION_OFF`
- `DRIVE_MODE`
- `DRIVE_MODE_SPORT`
- `DRIVE_MODE_ECO`
- `DRIVE_MODE_COMFORT`
- `DRIVE_MODE_TERRAIN`
- `FORCED_ALL_WHEEL_DRIVE`
- `FOUR_WHEEL_DRIVE_MODE_2H`
- `FOUR_WHEEL_DRIVE_MODE_4A`
- `FOUR_WHEEL_DRIVE_MODE_4H`
- `FOUR_WHEEL_DRIVE_MODE_4L`
- `TOW_MODE`
- `ESC_SPORT`
- `CREEP_OFF`
- `DRIFT_MODE`
- `TRACK_MODE`
- `UTILITY_MODE`
- `GREEN_ZONE`
- `PHEV_MODE`
- `PHEV_MODE_HEV`
- `PHEV_MODE_AUTO`
- `PHEV_MODE_EV_PLUS`
- `EREV_MODE`
- `LAUNCH_MODE`
- `ACCEL_RESPONSE_DASH`
- `ACCEL_RESPONSE_ACCEL`
- `N_BATTERY_DRAG`
- `N_BATTERY_SPRINT`
- `N_BATTERY_ENDURANCE`
- `FCA`
- `LKA`
- `LFA`
- `LCA_LEFT`
- `LCA_RIGHT`
- `ELK`
- `ICC`
- `DRIVER_ATTENTION`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TelltaleType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TelltaleType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TelltaleType>`


---

## 패키지 `ai.pleos.playground.vehicle.constant.unit`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.unit/entry


**Types**
- `DistanceDisplayUnit` — Represents the distance display unit.
  - `enum DistanceDisplayUnit: Enum<DistanceDisplayUnit>`
- `EvBatteryDisplayUnit` — Represents the EV battery display unit.
  - `enum EvBatteryDisplayUnit: Enum<EvBatteryDisplayUnit>`
- `SpeedDisplayUnit` — Represents the speed display unit.
  - `enum SpeedDisplayUnit: Enum<SpeedDisplayUnit>`
- `TemperatureUnit` — Represents the temperature unit.
  - `enum TemperatureUnit: Enum<TemperatureUnit>`

### DistanceDisplayUnit

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.unit/distance-display-unit/entry

`enum DistanceDisplayUnit: Enum<DistanceDisplayUnit>`
Represents the distance display unit.

**Entries**
- `KILOMETER` — Kilometers (km).
- `MILE` — Miles (mi).

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<DistanceDisplayUnit>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): DistanceDisplayUnit`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<DistanceDisplayUnit>`

### EvBatteryDisplayUnit

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.unit/ev-battery-display-unit/entry

`enum EvBatteryDisplayUnit: Enum<EvBatteryDisplayUnit>`
Represents the EV battery display unit.

**Entries**
- `WATT_HOUR` — Watt-hours (Wh).
- `AMPERE_HOURS` — Ampere-hours (Ah).
- `KILOWATT_HOUR` — Kilowatt-hours (kWh).

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<EvBatteryDisplayUnit>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): EvBatteryDisplayUnit`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<EvBatteryDisplayUnit>`

### SpeedDisplayUnit

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.unit/speed-display-unit/entry

`enum SpeedDisplayUnit: Enum<SpeedDisplayUnit>`
Represents the speed display unit.

**Entries**
- `METER_PER_SEC` — Meters per second (m/s).
- `MILES_PER_HOUR` — Miles per hour (mph).
- `KILOMETERS_PER_HOUR` — Kilometers per hour (km/h).

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<SpeedDisplayUnit>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): SpeedDisplayUnit`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<SpeedDisplayUnit>`

### TemperatureUnit

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant.unit/temperature-unit/entry

`enum TemperatureUnit: Enum<TemperatureUnit>`
Represents the temperature unit.

**Entries**
- `CELSIUS` — Degrees Celsius (°C).
- `FAHRENHEIT` — Degrees Fahrenheit (°F).

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TemperatureUnit>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TemperatureUnit`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TemperatureUnit>`


---

## 패키지 `ai.pleos.playground.vehicle.constant`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant/entry


**Types**
- `ErrorCode`
  - `enum ErrorCode: Enum<ErrorCode>`
- `PleosPermission`
  - `object PleosPermission`

### ErrorCode

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant/error-code/entry

`enum ErrorCode: Enum<ErrorCode>`

**Entries**
- `ERROR_INTERNAL_SERVICE`
- `ERROR_SERVICE_CONNECTION_FAILURE`
- `ERROR_DRIVING_RESTRICTED`
- `ERROR_NOT_SUPPORTED_PROPERTY`
- `ERROR_NOT_SUPPORTED_VALUE_TYPE`
- `ERROR_NOT_SUPPORTED_AREA`
- `ERROR_EMPTY_RESULT`
- `ERROR_SECURITY_PERMISSION`
- `ERROR_UNDEFINED`

**Types**
- `Companion`
  - `object Companion`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<ErrorCode>`
- `id`
  - `val id: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): ErrorCode`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<ErrorCode>`

### PleosPermission

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.constant/pleos-permission/entry

`object PleosPermission`

**Properties**
- `CAR_CLIMATE`
  - `const val CAR_CLIMATE: String`
- `CAR_DOORS`
  - `const val CAR_DOORS: String`
- `CAR_DRIVE`
  - `const val CAR_DRIVE: String`
- `CAR_ENERGY`
  - `const val CAR_ENERGY: String`
- `CAR_INFO`
  - `const val CAR_INFO: String`
- `CAR_LIGHTS`
  - `const val CAR_LIGHTS: String`
- `CAR_MIRRORS`
  - `const val CAR_MIRRORS: String`
- `CAR_SAFETY`
  - `const val CAR_SAFETY: String`
- `CAR_SEATS`
  - `const val CAR_SEATS: String`
- `CAR_TOLL`
  - `const val CAR_TOLL: String`
- `CAR_WINDOWS`
  - `const val CAR_WINDOWS: String`
- `CONTROL_CAR_CLIMATE`
  - `const val CONTROL_CAR_CLIMATE: String`
- `CONTROL_CAR_DOORS`
  - `const val CONTROL_CAR_DOORS: String`
- `CONTROL_CAR_DRIVE`
  - `const val CONTROL_CAR_DRIVE: String`
- `CONTROL_CAR_ENERGY`
  - `const val CONTROL_CAR_ENERGY: String`
- `CONTROL_CAR_INFO`
  - `const val CONTROL_CAR_INFO: String`
- `CONTROL_CAR_LIGHTS`
  - `const val CONTROL_CAR_LIGHTS: String`
- `CONTROL_CAR_MIRRORS`
  - `const val CONTROL_CAR_MIRRORS: String`
- `CONTROL_CAR_SAFETY`
  - `const val CONTROL_CAR_SAFETY: String`
- `CONTROL_CAR_SEATS`
  - `const val CONTROL_CAR_SEATS: String`
- `CONTROL_CAR_TOLL`
  - `const val CONTROL_CAR_TOLL: String`
- `CONTROL_CAR_WINDOWS`
  - `const val CONTROL_CAR_WINDOWS: String`
- `PRIVATE_CAR_CLIMATE`
  - `const val PRIVATE_CAR_CLIMATE: String`
- `PRIVATE_CAR_DOORS`
  - `const val PRIVATE_CAR_DOORS: String`
- `PRIVATE_CAR_DRIVE`
  - `const val PRIVATE_CAR_DRIVE: String`
- `PRIVATE_CAR_ENERGY`
  - `const val PRIVATE_CAR_ENERGY: String`
- `PRIVATE_CAR_INFO`
  - `const val PRIVATE_CAR_INFO: String`
- `PRIVATE_CAR_LIGHTS`
  - `const val PRIVATE_CAR_LIGHTS: String`
- `PRIVATE_CAR_MIRRORS`
  - `const val PRIVATE_CAR_MIRRORS: String`
- `PRIVATE_CAR_SAFETY`
  - `const val PRIVATE_CAR_SAFETY: String`
- `PRIVATE_CAR_SEATS`
  - `const val PRIVATE_CAR_SEATS: String`
- `PRIVATE_CAR_TOLL`
  - `const val PRIVATE_CAR_TOLL: String`
- `PRIVATE_CAR_WINDOWS`
  - `const val PRIVATE_CAR_WINDOWS: String`


---

## 패키지 `ai.pleos.playground.vehicle.listener`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/entry


**Types**
- `AmbientColorAutoByDriveModeEnabledListener` — Interface definition for the AmbientColorAutoByDriveModeEnabled updates.
  - `interface AmbientColorAutoByDriveModeEnabledListener: VehicleListener`
- `AmbientLightBrightnessColorListener` — Interface definition for the AmbientLightBrightnessColor updates
  - `interface AmbientLightBrightnessColorListener: VehicleListener`
- `AmbientLightBrightnessListener` — Interface definition for the AmbientLightBrightness updates
  - `interface AmbientLightBrightnessListener: VehicleListener`
- `BrakePedalStatusListener` — Interface definition for the BrakePedalStatus updates
  - `interface BrakePedalStatusListener: VehicleListener`
- `CruiseControlStateListener` — Interface definition for the CruiseControlState updates.
  - `interface CruiseControlStateListener: VehicleListener`
- `CruiseControlTargetSpeedListener` — Listener for cruise control target speed updates.
  - `interface CruiseControlTargetSpeedListener: VehicleListener`
- `DistanceDrivenListener` — Listener for distance updates.
  - `interface DistanceDrivenListener: VehicleListener`
- `DoorLockStateListener` — Interface definition for the door lock state updates.
  - `interface DoorLockStateListener: VehicleListener`
- `DoorPositionListener` — Interface definition for the door position updates.
  - `interface DoorPositionListener: VehicleListener`
- `DrivingModeStateListener` — Interface definition for the DrivingModeState updates.
  - `interface DrivingModeStateListener: VehicleListener`
- `DrivingTimeListener` — Interface definition for the driving time updates.
  - `interface DrivingTimeListener: VehicleListener`
- `EtcsVoiceGuidanceVolumeListener` — Interface definition for ETCS voice guidance volume updates.
  - `interface EtcsVoiceGuidanceVolumeListener: VehicleListener`
- `EvBatteryEfficiencyListener` — Interface definition for the EV battery efficiency updates.
  - `interface EvBatteryEfficiencyListener: VehicleListener`
- `EvBatteryStateOfChargeListener` — Interface definition for the EV battery state of charge updates.
  - `interface EvBatteryStateOfChargeListener: VehicleListener`
- `FuelEconomyListener` — Interface definition for the fuel economy updates.
  - `interface FuelEconomyListener: VehicleListener`
- `FuelPercentListener` — Interface definition for the fuel percentage updates.
  - `interface FuelPercentListener: VehicleListener`
- `GloveBoxLockStateListener` — Interface definition for the GloveBox lock state updates.
  - `interface GloveBoxLockStateListener: VehicleListener`
- `HvacAcStateListener` — Interface definition for the HvacAcState updates
  - `interface HvacAcStateListener: VehicleListener`
- `HvacAirCleanerStateListener` — Interface definition for the HvacAirCleanerState updates
  - `interface HvacAirCleanerStateListener: VehicleListener`
- `HvacAutoStateListener` — Interface definition for the HvacAutoState updates
  - `interface HvacAutoStateListener: VehicleListener`
- `HvacDefrosterStateListener` — Interface definition for the HvacDefrosterState updates
  - `interface HvacDefrosterStateListener: VehicleListener`
- `HvacDualStateListener` — Interface definition for the HvacDualState updates
  - `interface HvacDualStateListener: VehicleListener`
- `HvacFanSpeedListener` — Interface definition for the HvacFanSpeed updates
  - `interface HvacFanSpeedListener: VehicleListener`
- `HvacMaxAcStateListener` — Interface definition for the HvacMaxAcState updates
  - `interface HvacMaxAcStateListener: VehicleListener`
- `HvacMaxDefrostStateListener` — Interface definition for the HvacMaxDefrostState updates
  - `interface HvacMaxDefrostStateListener: VehicleListener`
- `HvacPowerStateListener` — Interface definition for the HvacPowerState updates
  - `interface HvacPowerStateListener: VehicleListener`
- `HvacRecirculationStateListener` — Interface definition for the HvacAutoRecirculationState updates
  - `interface HvacRecirculationStateListener: VehicleListener`
- `HvacSeatTemperatureListener` — Interface definition for the HvacSeatTemperature updates
  - `interface HvacSeatTemperatureListener: VehicleListener`
- `HvacSeatVentilationListener` — Interface definition for the HvacSeatVentilation updates
  - `interface HvacSeatVentilationListener: VehicleListener`
- `HvacSideMirrorHeatStateListener` — Interface definition for the HvacSideMirrorHeatState updates.
  - `interface HvacSideMirrorHeatStateListener: VehicleListener`
- `HvacSteeringWheelHeatStateListener` — Interface definition for the HvacSteeringWheelHeatState updates.
  - `interface HvacSteeringWheelHeatStateListener: VehicleListener`
- `HvacTemperatureListener` — Listener for HVAC temperature updates.
  - `interface HvacTemperatureListener: VehicleListener`
- `HvacTemperatureValueListener` — Interface definition for HVAC temperature updates in degrees Celsius (°C) and degrees Fahrenheit (°F).
  - `interface HvacTemperatureValueListener: VehicleListener`
- `LightStateListener` — Interface definition for the HighBeamLightState updates
  - `interface LightStateListener: VehicleListener`
- `LowWasherFluidWaringListener` — Interface definition for the LowWasherFluidWaring updates.
  - `interface LowWasherFluidWaringListener: VehicleListener`
- `OilLevelStateListener` — Interface definition for the OilLevelState updates.
  - `interface OilLevelStateListener: VehicleListener`
- `PowerDownWarningIndicatorListener` — Interface definition for the PowerDownWarningIndicator updates.
  - `interface PowerDownWarningIndicatorListener: VehicleListener`
- `SeatAirbagDeployedListener` — Interface definition for the SeatAirbagDeployed updates
  - `interface SeatAirbagDeployedListener: VehicleListener`
- `SeatAirbagEnabledListener` — Interface definition for the SeatAirbagEnabled updates
  - `interface SeatAirbagEnabledListener: VehicleListener`
- `SeatBackrestAnglePositionListener` — Interface definition for the SeatBackrestAnglePosition updates
  - `interface SeatBackrestAnglePositionListener: VehicleListener`
- `SeatBeltBuckledStateListener` — Interface definition for the SeatBeltBuckledState updates
  - `interface SeatBeltBuckledStateListener: VehicleListener`
- `SeatBeltHeightPositionListener` — Interface definition for the SeatBeltHeightPosition updates
  - `interface SeatBeltHeightPositionListener: VehicleListener`
- `SeatBeltNotBuckledWarningListener` — Interface definition for the SeatBeltNotBuckledWarning updates
  - `interface SeatBeltNotBuckledWarningListener: VehicleListener`
- `SeatDepthPositionListener` — Interface definition for the SeatDepthPosition updates
  - `interface SeatDepthPositionListener: VehicleListener`
- `SeatForeAftPositionListener` — Interface definition for the SeatForeAftPosition updates
  - `interface SeatForeAftPositionListener: VehicleListener`
- `SeatHeadrestAnglePositionListener` — Interface definition for the SeatHeadrestAnglePosition updates
  - `interface SeatHeadrestAnglePositionListener: VehicleListener`
- `SeatHeadrestForeAftPositionListener` — Interface definition for the SeatHeadrestForeAftPosition updates
  - `interface SeatHeadrestForeAftPositionListener: VehicleListener`
- `SeatHeadrestHeightAftPositionListener` — Interface definition for the SeatHeadrestHeightAftPosition updates
  - `interface SeatHeadrestHeightAftPositionListener: VehicleListener`
- `SeatHeightPositionListener` — Interface definition for the SeatHeightPosition updates
  - `interface SeatHeightPositionListener: VehicleListener`
- `SeatLumbarForeAftPositionListener` — Interface definition for the SeatLumbarForeAftPosition updates
  - `interface SeatLumbarForeAftPositionListener: VehicleListener`
- `SeatLumbarSideSupportPositionListener` — Interface definition for the SeatLumbarSideSupportPosition updates
  - `interface SeatLumbarSideSupportPositionListener: VehicleListener`
- `SeatOccupancyListener` — Interface definition for the SeatOccupancy updates
  - `interface SeatOccupancyListener: VehicleListener`
- `SeatTiltPositionListener` — Interface definition for the SeatTiltPosition updates
  - `interface SeatTiltPositionListener: VehicleListener`
- `SmartGlassLevelListener` — Interface definition for the SmartGlass Level updates
  - `interface SmartGlassLevelListener: VehicleListener`
- `SmartGlassStateListener` — Interface definition for the SmartGlassState updates
  - `interface SmartGlassStateListener: VehicleListener`
- `SteeringWheelAngleListener` — Listener for front-wheel steering angle updates.
  - `interface SteeringWheelAngleListener: VehicleListener`
- `TelltaleInfoListener` — Interface definition for the TelltaleInfo updates
  - `interface TelltaleInfoListener: VehicleListener`
- `TemperatureDisplayUnitListener` — Interface definition for the TemperatureDisplayUnit updates
  - `interface TemperatureDisplayUnitListener: VehicleListener`
- `TripDrivingDistanceListener` — Interface definition for the trip driving distance updates.
  - `interface TripDrivingDistanceListener: VehicleListener`
- `TripDrivingTimeListener` — Interface definition for the trip driving time updates.
  - `interface TripDrivingTimeListener: VehicleListener`
- `TripEvEfficiencyListener` — Interface definition for the trip EV efficiency updates.
  - `interface TripEvEfficiencyListener: VehicleListener`
- `TripFuelEconomyListener` — Interface definition for the trip fuel economy updates.
  - `interface TripFuelEconomyListener: VehicleListener`
- `TurnSignalStateListener` — Interface definition for the TurnSignalState updates
  - `interface TurnSignalStateListener: VehicleListener`
- `VehicleListener` — VehicleListener is an interface for constructing standard responses from the vehicle call.
  - `sealed interface VehicleListener`
- `WindowLockStateListener` — Interface definition for the WindowLockState updates
  - `interface WindowLockStateListener: VehicleListener`
- `WindowPositionListener` — Interface definition for the WindowPosition updates
  - `interface WindowPositionListener: VehicleListener`
- `WiperStateListener` — Interface definition for the WiperState updates
  - `interface WiperStateListener: VehicleListener`

### AmbientColorAutoByDriveModeEnabledListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/ambient-color-auto-by-drive-mode-enabled-listener/entry

`interface AmbientColorAutoByDriveModeEnabledListener: VehicleListener`
Interface definition for the AmbientColorAutoByDriveModeEnabled updates.

**Functions**
- `onAmbientColorAutoByDriveModeEnabledUpdated` — Callback invoked when AmbientColorAutoByDriveModeEnabled updates.
  - `abstract fun onAmbientColorAutoByDriveModeEnabledUpdated(enabled: Boolean?)`

### AmbientLightBrightnessColorListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/ambient-light-brightness-color-listener/entry

`interface AmbientLightBrightnessColorListener: VehicleListener`
Interface definition for the AmbientLightBrightnessColor updates

**Functions**
- `onBrightnessColorUpdated` — Callback invoked when AmbientLightBrightnessColor updates.
  - `abstract fun onBrightnessColorUpdated(argbColor: Int?)`

### AmbientLightBrightnessListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/ambient-light-brightness-listener/entry

`interface AmbientLightBrightnessListener: VehicleListener`
Interface definition for the AmbientLightBrightness updates

**Functions**
- `onBrightnessUpdated` — Callback invoked when AmbientLightBrightness updates.
  - `abstract fun onBrightnessUpdated(brightness: Int?)`

### BrakePedalStatusListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/brake-pedal-status-listener/entry

`interface BrakePedalStatusListener: VehicleListener`
Interface definition for the BrakePedalStatus updates

**Functions**
- `onBrakePedalStatusUpdated` — Callback invoked when BrakePedalStatus updates.
  - `abstract fun onBrakePedalStatusUpdated(status: Boolean?)`

### CruiseControlStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/cruise-control-state-listener/entry

`interface CruiseControlStateListener: VehicleListener`
Interface definition for the CruiseControlState updates.

**Functions**
- `onCruiseControlStateUpdated` — Callback invoked when the CruiseControlState updates.
  - `abstract fun onCruiseControlStateUpdated(state: CruiseControl?)`

### CruiseControlTargetSpeedListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/cruise-control-target-speed-listener/entry

`interface CruiseControlTargetSpeedListener: VehicleListener`
Listener for cruise control target speed updates.

**Functions**
- `onCruiseControlTargetSpeedUpdated` — Called with the updated cruise control target speed in meters per second (m/s).
  - `abstract fun onCruiseControlTargetSpeedUpdated(speed: Float?)`

### DistanceDrivenListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/distance-driven-listener/entry

`interface DistanceDrivenListener: VehicleListener`
Listener for distance updates.
The unit depends on the method used to register this listener:
- ai.pleos.playground.vehicle.api.Odometer.registerDistanceDriven: kilometers (km).
- ai.pleos.playground.vehicle.api.Odometer.registerTripCurrentDrivingDistance, ai.pleos.playground.vehicle.api.Odometer.registerTripInstantaneousDrivingDistance, ai.pleos.playground.vehicle.api.Odometer.registerTripCumulativeDrivingDistance, and ai.pleos.playground.vehicle.api.Odometer.registerTripSinceRefillDrivingDistance: meters (m).

**Functions**
- `onDistanceDrivenUpdated` — Called with the updated distance in kilometers (km) or meters (m), depending on the registration API.
  - `abstract fun onDistanceDrivenUpdated(value: Float?)`

### DoorLockStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/door-lock-state-listener/entry

`interface DoorLockStateListener: VehicleListener`
Interface definition for the door lock state updates.

**Functions**
- `onDoorLockStateUpdated` — Callback invoked when the door lock state updates.
  - `abstract fun onDoorLockStateUpdated(state: Boolean?)`

### DoorPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/door-position-listener/entry

`interface DoorPositionListener: VehicleListener`
Interface definition for the door position updates.

**Functions**
- `onPositionUpdated` — Callback invoked when the door position updates.
  - `abstract fun onPositionUpdated(pos: Int?)`

### DrivingModeStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/driving-mode-state-listener/entry

`interface DrivingModeStateListener: VehicleListener`
Interface definition for the DrivingModeState updates.

**Functions**
- `onDrivingModeStateUpdated` — Callback invoked when the DrivingModeState updates.
  - `abstract fun onDrivingModeStateUpdated(state: DrivingModeType?)`

### DrivingTimeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/driving-time-listener/entry

`interface DrivingTimeListener: VehicleListener`
Interface definition for the driving time updates.

**Functions**
- `onDrivingTimeUpdated` — Callback invoked when driving time updates.
  - `abstract fun onDrivingTimeUpdated(value: Int?)`

### EtcsVoiceGuidanceVolumeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/etcs-voice-guidance-volume-listener/entry

`interface EtcsVoiceGuidanceVolumeListener: VehicleListener`
Interface definition for ETCS voice guidance volume updates.

**Functions**
- `onEtcsVoiceGuidanceVolumeUpdated` — Callback invoked when ETCS voice guidance volume updates.
  - `abstract fun onEtcsVoiceGuidanceVolumeUpdated(volume: Int?)`

### EvBatteryEfficiencyListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/ev-battery-efficiency-listener/entry

`interface EvBatteryEfficiencyListener: VehicleListener`
Interface definition for the EV battery efficiency updates.

**Functions**
- `onEvBatteryEfficiencyUpdated` — Callback invoked when EV battery efficiency updates.
  - `abstract fun onEvBatteryEfficiencyUpdated(efficiency: Float?)`

### EvBatteryStateOfChargeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/ev-battery-state-of-charge-listener/entry

`interface EvBatteryStateOfChargeListener: VehicleListener`
Interface definition for the EV battery state of charge updates.

**Functions**
- `onEvBatteryStateOfChargeUpdated` — Callback invoked with the updated EV battery state of charge as a percentage (%).
  - `abstract fun onEvBatteryStateOfChargeUpdated(value: Int?)`

### FuelEconomyListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/fuel-economy-listener/entry

`interface FuelEconomyListener: VehicleListener`
Interface definition for the fuel economy updates.

**Functions**
- `onFuelEconomyUpdated` — Callback invoked when fuel economy updates.
  - `abstract fun onFuelEconomyUpdated(efficiency: Float?)`

### FuelPercentListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/fuel-percent-listener/entry

`interface FuelPercentListener: VehicleListener`
Interface definition for the fuel percentage updates.

**Functions**
- `onFuelPercentUpdated` — Callback invoked with the updated fuel level as a percentage (%).
  - `abstract fun onFuelPercentUpdated(value: Int?)`

### GloveBoxLockStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/glove-box-lock-state-listener/entry

`interface GloveBoxLockStateListener: VehicleListener`
Interface definition for the GloveBox lock state updates.

**Functions**
- `onGloveBoxLockStateUpdated` — Callback invoked when the GloveBox lock state updates.
  - `abstract fun onGloveBoxLockStateUpdated(state: Boolean?)`

### HvacAcStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-ac-state-listener/entry

`interface HvacAcStateListener: VehicleListener`
Interface definition for the HvacAcState updates

**Functions**
- `onHvacAcStateUpdated` — Callback invoked when HvacAcState updates.
  - `abstract fun onHvacAcStateUpdated(state: Boolean?)`

### HvacAirCleanerStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-air-cleaner-state-listener/entry

`interface HvacAirCleanerStateListener: VehicleListener`
Interface definition for the HvacAirCleanerState updates

**Functions**
- `onHvacAirCleanerStateUpdated` — Callback invoked when HvacAirCleanerState updates.
  - `abstract fun onHvacAirCleanerStateUpdated(state: Boolean?)`

### HvacAutoStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-auto-state-listener/entry

`interface HvacAutoStateListener: VehicleListener`
Interface definition for the HvacAutoState updates

**Functions**
- `onHvacAutoStateUpdated` — Callback invoked when HvacAutoState updates.
  - `abstract fun onHvacAutoStateUpdated(state: Boolean?)`

### HvacDefrosterStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-defroster-state-listener/entry

`interface HvacDefrosterStateListener: VehicleListener`
Interface definition for the HvacDefrosterState updates

**Functions**
- `onHvacDefrosterStateUpdated` — Callback invoked when HvacDefrosterState updates.
  - `abstract fun onHvacDefrosterStateUpdated(state: Boolean?)`

### HvacDualStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-dual-state-listener/entry

`interface HvacDualStateListener: VehicleListener`
Interface definition for the HvacDualState updates

**Functions**
- `onHvacDualStateUpdated` — Callback invoked when HvacDualState updates.
  - `abstract fun onHvacDualStateUpdated(state: Boolean?)`

### HvacFanSpeedListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-fan-speed-listener/entry

`interface HvacFanSpeedListener: VehicleListener`
Interface definition for the HvacFanSpeed updates

**Functions**
- `onHvacFanSpeedUpdated` — Callback invoked when HvacFanSpeed updates.
  - `abstract fun onHvacFanSpeedUpdated(speed: Int?)`

### HvacMaxAcStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-max-ac-state-listener/entry

`interface HvacMaxAcStateListener: VehicleListener`
Interface definition for the HvacMaxAcState updates

**Functions**
- `onHvacMaxAcStateUpdated` — Callback invoked when HvacMaxAcState updates.
  - `abstract fun onHvacMaxAcStateUpdated(state: Boolean?)`

### HvacMaxDefrostStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-max-defrost-state-listener/entry

`interface HvacMaxDefrostStateListener: VehicleListener`
Interface definition for the HvacMaxDefrostState updates

**Functions**
- `onHvacMaxDefrostStateUpdated` — Callback invoked when HvacMaxDefrostState updates.
  - `abstract fun onHvacMaxDefrostStateUpdated(state: Boolean?)`

### HvacPowerStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-power-state-listener/entry

`interface HvacPowerStateListener: VehicleListener`
Interface definition for the HvacPowerState updates

**Functions**
- `onHvacPowerStateUpdated` — Callback invoked when HvacPowerState updates.
  - `abstract fun onHvacPowerStateUpdated(state: Boolean?)`

### HvacRecirculationStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-recirculation-state-listener/entry

`interface HvacRecirculationStateListener: VehicleListener`
Interface definition for the HvacAutoRecirculationState updates

**Functions**
- `onHvacAutoRecirculationStateUpdated` — Callback invoked when HvacAutoRecirculationState updates.
  - `abstract fun onHvacAutoRecirculationStateUpdated(state: Boolean?)`

### HvacSeatTemperatureListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-seat-temperature-listener/entry

`interface HvacSeatTemperatureListener: VehicleListener`
Interface definition for the HvacSeatTemperature updates

**Functions**
- `onHvacSeatTemperatureUpdated` — Callback invoked when HvacSeatTemperature updates.
  - `abstract fun onHvacSeatTemperatureUpdated(temperature: Int?)`

### HvacSeatVentilationListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-seat-ventilation-listener/entry

`interface HvacSeatVentilationListener: VehicleListener`
Interface definition for the HvacSeatVentilation updates

**Functions**
- `onHvacSeatVentilationUpdated` — Callback invoked when HvacSeatVentilation updates.
  - `abstract fun onHvacSeatVentilationUpdated(ventilation: Int?)`

### HvacSideMirrorHeatStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-side-mirror-heat-state-listener/entry

`interface HvacSideMirrorHeatStateListener: VehicleListener`
Interface definition for the HvacSideMirrorHeatState updates.

**Functions**
- `onHvacSideMirrorHeatStateUpdated` — Callback invoked when HvacSideMirrorHeatState updates.
  - `abstract fun onHvacSideMirrorHeatStateUpdated(state: Int?)`

### HvacSteeringWheelHeatStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-steering-wheel-heat-state-listener/entry

`interface HvacSteeringWheelHeatStateListener: VehicleListener`
Interface definition for the HvacSteeringWheelHeatState updates.

**Functions**
- `onHvacSteeringWheelHeatStateUpdated` — Callback invoked when the HvacSteeringWheelHeatState updates.
  - `abstract fun onHvacSteeringWheelHeatStateUpdated(state: Int?)`

### HvacTemperatureListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-temperature-listener/entry

`interface HvacTemperatureListener: VehicleListener`
Listener for HVAC temperature updates. Reports the target temperature when registered with ai.pleos.playground.vehicle.api.HVAC.registerHvacTemperature, or the current interior temperature when registered with ai.pleos.playground.vehicle.api.HVAC.registerHvacTemperatureCurrent. Both registration methods report values in degrees Celsius (°C).

**Functions**
- `onHvacTemperatureUpdated` — Called with the updated HVAC temperature in degrees Celsius (°C).
  - `abstract fun onHvacTemperatureUpdated(temperature: Float?)`

### HvacTemperatureValueListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/hvac-temperature-value-listener/entry

`interface HvacTemperatureValueListener: VehicleListener`
Interface definition for HVAC temperature updates in degrees Celsius (°C) and degrees Fahrenheit (°F).

**Functions**
- `onHvacTemperatureUpdated` — Callback invoked when HVAC temperature updates.
  - `abstract fun onHvacTemperatureUpdated(celsius: Float?, fahrenheit: Float?)`

### LightStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/light-state-listener/entry

`interface LightStateListener: VehicleListener`
Interface definition for the HighBeamLightState updates

**Functions**
- `onLightStateUpdated` — Callback invoked when HighBeamLightState updates.
  - `abstract fun onLightStateUpdated(state: LightState?)`

### LowWasherFluidWaringListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/low-washer-fluid-waring-listener/entry

`interface LowWasherFluidWaringListener: VehicleListener`
Interface definition for the LowWasherFluidWaring updates.

**Functions**
- `onLowWasherFluidWaringUpdated` — Callback invoked when the LowWasherFluidWaring updates.
  - `abstract fun onLowWasherFluidWaringUpdated(state: Boolean?)`

### OilLevelStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/oil-level-state-listener/entry

`interface OilLevelStateListener: VehicleListener`
Interface definition for the OilLevelState updates.

**Functions**
- `onOilLevelUpdated` — Callback invoked when the OilLevelState updates.
  - `abstract fun onOilLevelUpdated(level: OilLevelState?)`

### PowerDownWarningIndicatorListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/power-down-warning-indicator-listener/entry

`interface PowerDownWarningIndicatorListener: VehicleListener`
Interface definition for the PowerDownWarningIndicator updates.

**Functions**
- `onPowerDownWarningIndicatorUpdated` — Callback invoked when the PowerDownWarningIndicator updates.
  - `abstract fun onPowerDownWarningIndicatorUpdated(state: Boolean?)`

### SeatAirbagDeployedListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-airbag-deployed-listener/entry

`interface SeatAirbagDeployedListener: VehicleListener`
Interface definition for the SeatAirbagDeployed updates

**Functions**
- `onSeatAirbagDeployUpdated` — Callback invoked when SeatAirbagDeployed updates.
  - `abstract fun onSeatAirbagDeployUpdated(deployed: Boolean?)`

### SeatAirbagEnabledListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-airbag-enabled-listener/entry

`interface SeatAirbagEnabledListener: VehicleListener`
Interface definition for the SeatAirbagEnabled updates

**Functions**
- `onSeatAirbagEnabledUpdated` — Callback invoked when SeatAirbagEnabled updates.
  - `abstract fun onSeatAirbagEnabledUpdated(enabled: Boolean?)`

### SeatBackrestAnglePositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-backrest-angle-position-listener/entry

`interface SeatBackrestAnglePositionListener: VehicleListener`
Interface definition for the SeatBackrestAnglePosition updates

**Functions**
- `onSeatBackrestAnglePositionUpdated` — Callback invoked when SeatBackrestAnglePosition updates.
  - `abstract fun onSeatBackrestAnglePositionUpdated(position: Int?)`

### SeatBeltBuckledStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-belt-buckled-state-listener/entry

`interface SeatBeltBuckledStateListener: VehicleListener`
Interface definition for the SeatBeltBuckledState updates

**Functions**
- `onSeatBeltBuckledStateUpdated` — Callback invoked when SeatBeltBuckledState updates.
  - `abstract fun onSeatBeltBuckledStateUpdated(state: Boolean?)`

### SeatBeltHeightPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-belt-height-position-listener/entry

`interface SeatBeltHeightPositionListener: VehicleListener`
Interface definition for the SeatBeltHeightPosition updates

**Functions**
- `onSeatBeltHeightPositionUpdated` — Callback invoked when SeatBeltHeightPosition updates.
  - `abstract fun onSeatBeltHeightPositionUpdated(position: Int?)`

### SeatBeltNotBuckledWarningListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-belt-not-buckled-warning-listener/entry

`interface SeatBeltNotBuckledWarningListener: VehicleListener`
Interface definition for the SeatBeltNotBuckledWarning updates

**Functions**
- `onSeatBeltNotBuckledWarningUpdated` — Callback invoked when SeatBeltNotBuckledWarning updates.
  - `abstract fun onSeatBeltNotBuckledWarningUpdated(state: Boolean?)`

### SeatDepthPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-depth-position-listener/entry

`interface SeatDepthPositionListener: VehicleListener`
Interface definition for the SeatDepthPosition updates

**Functions**
- `onSeatDepthPositionUpdated` — Callback invoked when SeatDepthPosition updates.
  - `abstract fun onSeatDepthPositionUpdated(position: Int?)`

### SeatForeAftPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-fore-aft-position-listener/entry

`interface SeatForeAftPositionListener: VehicleListener`
Interface definition for the SeatForeAftPosition updates

**Functions**
- `onSeatForeAftPositionUpdated` — Callback invoked when SeatForeAftPosition updates.
  - `abstract fun onSeatForeAftPositionUpdated(position: Int?)`

### SeatHeadrestAnglePositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-headrest-angle-position-listener/entry

`interface SeatHeadrestAnglePositionListener: VehicleListener`
Interface definition for the SeatHeadrestAnglePosition updates

**Functions**
- `onSeatHeadrestAnglePositionUpdated` — Callback invoked when SeatHeadrestAnglePosition updates.
  - `abstract fun onSeatHeadrestAnglePositionUpdated(position: Int?)`

### SeatHeadrestForeAftPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-headrest-fore-aft-position-listener/entry

`interface SeatHeadrestForeAftPositionListener: VehicleListener`
Interface definition for the SeatHeadrestForeAftPosition updates

**Functions**
- `onSeatHeadrestForeAftPositionUpdated` — Callback invoked when SeatHeadrestForeAftPosition updates.
  - `abstract fun onSeatHeadrestForeAftPositionUpdated(position: Int?)`

### SeatHeadrestHeightAftPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-headrest-height-aft-position-listener/entry

`interface SeatHeadrestHeightAftPositionListener: VehicleListener`
Interface definition for the SeatHeadrestHeightAftPosition updates

**Functions**
- `onSeatHeadrestHeightAftPositionUpdated` — Callback invoked when SeatHeadrestHeightAftPosition updates.
  - `abstract fun onSeatHeadrestHeightAftPositionUpdated(position: Int?)`

### SeatHeightPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-height-position-listener/entry

`interface SeatHeightPositionListener: VehicleListener`
Interface definition for the SeatHeightPosition updates

**Functions**
- `onSeatHeightPositionUpdated` — Callback invoked when SeatHeightPosition updates.
  - `abstract fun onSeatHeightPositionUpdated(position: Int?)`

### SeatLumbarForeAftPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-lumbar-fore-aft-position-listener/entry

`interface SeatLumbarForeAftPositionListener: VehicleListener`
Interface definition for the SeatLumbarForeAftPosition updates

**Functions**
- `onSeatLumbarForeAftPositionUpdated` — Callback invoked when SeatLumbarForeAftPosition updates.
  - `abstract fun onSeatLumbarForeAftPositionUpdated(position: Int?)`

### SeatLumbarSideSupportPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-lumbar-side-support-position-listener/entry

`interface SeatLumbarSideSupportPositionListener: VehicleListener`
Interface definition for the SeatLumbarSideSupportPosition updates

**Functions**
- `onSeatLumbarSideSupportPositionUpdated` — Callback invoked when SeatLumbarSideSupportPosition updates.
  - `abstract fun onSeatLumbarSideSupportPositionUpdated(position: Int?)`

### SeatOccupancyListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-occupancy-listener/entry

`interface SeatOccupancyListener: VehicleListener`
Interface definition for the SeatOccupancy updates

**Functions**
- `onSeatOccupancyUpdated` — Callback invoked when SeatOccupancy updates.
  - `abstract fun onSeatOccupancyUpdated(occupancy: Boolean?)`

### SeatTiltPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/seat-tilt-position-listener/entry

`interface SeatTiltPositionListener: VehicleListener`
Interface definition for the SeatTiltPosition updates

**Functions**
- `onSeatTiltPositionUpdated` — Callback invoked when SeatTiltPosition updates.
  - `abstract fun onSeatTiltPositionUpdated(position: Int?)`

### SmartGlassLevelListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/smart-glass-level-listener/entry

`interface SmartGlassLevelListener: VehicleListener`
Interface definition for the SmartGlass Level updates

**Functions**
- `onSmartGlassLevelUpdated` — Callback invoked when SmartGlass Level updates.
  - `abstract fun onSmartGlassLevelUpdated(level: Int?)`

### SmartGlassStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/smart-glass-state-listener/entry

`interface SmartGlassStateListener: VehicleListener`
Interface definition for the SmartGlassState updates

**Functions**
- `onSmartGlassStateUpdated` — Callback invoked when SmartGlassState updates.
  - `abstract fun onSmartGlassStateUpdated(state: Boolean?)`

### SteeringWheelAngleListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/steering-wheel-angle-listener/entry

`interface SteeringWheelAngleListener: VehicleListener`
Listener for front-wheel steering angle updates.

**Functions**
- `onSteeringWheelAngleUpdated` — Called when a front-wheel steering angle update is available.
  - `abstract fun onSteeringWheelAngleUpdated(angle: Float?)`

### TelltaleInfoListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/telltale-info-listener/entry

`interface TelltaleInfoListener: VehicleListener`
Interface definition for the TelltaleInfo updates

**Functions**
- `onTelltaleInfoUpdated` — Callback invoked when TelltaleInfo updates.
  - `abstract fun onTelltaleInfoUpdated(telltales: Telltales?)`

### TemperatureDisplayUnitListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/temperature-display-unit-listener/entry

`interface TemperatureDisplayUnitListener: VehicleListener`
Interface definition for the TemperatureDisplayUnit updates

**Functions**
- `onTemperatureDisplayUnitUpdated` — Callback invoked when TemperatureDisplayUnit updates.
  - `abstract fun onTemperatureDisplayUnitUpdated(unit: TemperatureUnit?)`

### TripDrivingDistanceListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/trip-driving-distance-listener/entry

`interface TripDrivingDistanceListener: VehicleListener`
Interface definition for the trip driving distance updates.

**Functions**
- `onTripDrivingDistanceUpdated` — Callback invoked when trip driving distance updates.
  - `abstract fun onTripDrivingDistanceUpdated(statistics: TripStatistics<Float>?)`

### TripDrivingTimeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/trip-driving-time-listener/entry

`interface TripDrivingTimeListener: VehicleListener`
Interface definition for the trip driving time updates.

**Functions**
- `onTripDrivingTimeUpdated` — Callback invoked when trip driving time updates.
  - `abstract fun onTripDrivingTimeUpdated(statistics: TripStatistics<Int>?)`

### TripEvEfficiencyListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/trip-ev-efficiency-listener/entry

`interface TripEvEfficiencyListener: VehicleListener`
Interface definition for the trip EV efficiency updates.

**Functions**
- `onTripEvEfficiencyUpdated` — Callback invoked when trip EV efficiency updates.
  - `abstract fun onTripEvEfficiencyUpdated(statistics: TripStatistics<Float>?)`

### TripFuelEconomyListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/trip-fuel-economy-listener/entry

`interface TripFuelEconomyListener: VehicleListener`
Interface definition for the trip fuel economy updates.

**Functions**
- `onTripFuelEconomyUpdated` — Callback invoked when trip fuel economy updates.
  - `abstract fun onTripFuelEconomyUpdated(statistics: TripStatistics<Float>?)`

### TurnSignalStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/turn-signal-state-listener/entry

`interface TurnSignalStateListener: VehicleListener`
Interface definition for the TurnSignalState updates

**Functions**
- `onTurnSignalStateUpdated` — Callback invoked when TurnSignalStates updates.
  - `abstract fun onTurnSignalStateUpdated(state: TurnSignalValue?)`

### VehicleListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/vehicle-listener/entry

`sealed interface VehicleListener`
VehicleListener is an interface for constructing standard responses from the vehicle call.

**Inheritors**
- `AmbientColorAutoByDriveModeEnabledListener`
- `AmbientLightBrightnessColorListener`
- `AmbientLightBrightnessListener`
- `BrakePedalStatusListener`
- `CruiseControlStateListener`
- `CruiseControlTargetSpeedListener`
- `DistanceDrivenListener`
- `DoorLockStateListener`
- `DoorPositionListener`
- `DrivingModeStateListener`
- `DrivingTimeListener`
- `EtcsVoiceGuidanceVolumeListener`
- `EvBatteryEfficiencyListener`
- `EvBatteryStateOfChargeListener`
- `FuelEconomyListener`
- `FuelPercentListener`
- `GloveBoxLockStateListener`
- `HvacAcStateListener`
- `HvacAirCleanerStateListener`
- `HvacAutoStateListener`
- `HvacDefrosterStateListener`
- `HvacDualStateListener`
- `HvacFanSpeedListener`
- `HvacMaxAcStateListener`
- `HvacMaxDefrostStateListener`
- `HvacPowerStateListener`
- `HvacRecirculationStateListener`
- `HvacSeatTemperatureListener`
- `HvacSeatVentilationListener`
- `HvacSideMirrorHeatStateListener`
- `HvacSteeringWheelHeatStateListener`
- `HvacTemperatureListener`
- `HvacTemperatureValueListener`
- `LightStateListener`
- `LowWasherFluidWaringListener`
- `OilLevelStateListener`
- `PowerDownWarningIndicatorListener`
- `SeatAirbagDeployedListener`
- `SeatAirbagEnabledListener`
- `SeatBackrestAnglePositionListener`
- `SeatBeltBuckledStateListener`
- `SeatBeltHeightPositionListener`
- `SeatBeltNotBuckledWarningListener`
- `SeatDepthPositionListener`
- `SeatForeAftPositionListener`
- `SeatHeadrestAnglePositionListener`
- `SeatHeadrestForeAftPositionListener`
- `SeatHeadrestHeightAftPositionListener`
- `SeatHeightPositionListener`
- `SeatLumbarForeAftPositionListener`
- `SeatLumbarSideSupportPositionListener`
- `SeatOccupancyListener`
- `SeatTiltPositionListener`
- `SmartGlassLevelListener`
- `SmartGlassStateListener`
- `SteeringWheelAngleListener`
- `TelltaleInfoListener`
- `TemperatureDisplayUnitListener`
- `TripDrivingDistanceListener`
- `TripDrivingTimeListener`
- `TripEvEfficiencyListener`
- `TripFuelEconomyListener`
- `TurnSignalStateListener`
- `WindowLockStateListener`
- `WindowPositionListener`
- `WiperStateListener`

**Functions**
- `onFailed` — Callback invoked when a exception occurs.
  - `abstract fun onFailed(e: Exception)`

### WindowLockStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/window-lock-state-listener/entry

`interface WindowLockStateListener: VehicleListener`
Interface definition for the WindowLockState updates

**Functions**
- `onWindowLockStateUpdated` — Callback invoked when WindowLockState updates.
  - `abstract fun onWindowLockStateUpdated(state: Boolean?)`

### WindowPositionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/window-position-listener/entry

`interface WindowPositionListener: VehicleListener`
Interface definition for the WindowPosition updates

**Functions**
- `onWindowPositionUpdated` — Callback invoked when WindowPosition updates.
  - `abstract fun onWindowPositionUpdated(position: Int?)`

### WiperStateListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.listener/wiper-state-listener/entry

`interface WiperStateListener: VehicleListener`
Interface definition for the WiperState updates

**Functions**
- `onWiperStateUpdated` — Callback invoked when WiperState updates.
  - `abstract fun onWiperStateUpdated(state: WiperState?)`


---

## 패키지 `ai.pleos.playground.vehicle`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle/entry


**Types**
- `Vehicle` — The entry point of the Vehicle SDK.
  - `class Vehicle(context: Context)`

### Vehicle

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle/vehicle/entry

`class Vehicle(context: Context)`
The entry point of the Vehicle SDK.
Create an instance with Vehicle(context), then call initialize to connect to the vehicle service. Use factory methods such as getDoor and getDisplay to obtain domain API objects (Door, Display, and other interfaces in ai.pleos.playground.vehicle.api), then call property, capability, or callback APIs on those objects. Call release when you no longer need the SDK.
Typical flow:
1. Vehicle(context) → initialize
2. getXxx() → domain API (for example Door)
3. Capability / get / set / callback on that domain API
4. release

**Parameters**
- `context` — Android android.content.Context used to bind to the vehicle service.

*Kotlin*
Get Property API example
```kotlin
// Create Vehicle instance
val vehicle = Vehicle(context)

// Start Vehicle
vehicle.initialize()

val door = vehicle.getDoor()
door.getCurrentDoorPosition(
  area = DoorArea(DoorValue.ROW_1_LEFT),
  onComplete = { pos: Int? ->
    // do something
  },
  onFailed = { e: Exception ->
    // do something
  }
)

val display = vehicle.getDisplay()
display.getCurrentSpeedDisplayUnit(
  onComplete = { unit: SpeedDisplayUnit? ->
    // do something
  },
  onFailed = { e: Exception ->
    // do something
  }
)

// End Vehicle
vehicle.release()
```
Set Property API example
```kotlin
// Create Vehicle instance
val vehicle = Vehicle(context)

// Start Vehicle
vehicle.initialize()

val door = vehicle.getDoor()
door.setDoorPosition(
  area = DoorArea(DoorValue.ROW_1_LEFT),
  value = 1, // Integer type
  onFailed = { e: Exception ->
    // do something
  }
)

// End Vehicle
vehicle.release()
```
Callback API example
```kotlin
// Create Vehicle instance
val vehicle = Vehicle(context)

// Start Vehicle
vehicle.initialize()

// Create callback listener
val callbackListener = object : GearSelectionListener {
  override fun onSelected(selection: VehicleGear?) {
    // do something
  }

  override fun onFailed(e: Exception) {
    // do something
  }
}

val gear = vehicle.getGear()

// register callback
gear.registerGearSelection(
  gearSelectionListener = callbackListener
)
// unregister callback
gear.unregisterGearSelection(
  gearSelectionListener = callbackListener
)

// End Vehicle
vehicle.release()
```
Capability API example
Check whether a property is supported on the vehicle and inspect its ai.pleos.playground.vehicle.api.model.capability.CapabilityConfig (for example access type and value bounds) before get/set calls.
```kotlin
// Create Vehicle instance
val vehicle = Vehicle(context)

// Start Vehicle
vehicle.initialize()

val door = vehicle.getDoor()
door.checkDoorPositionCapabilities(
  onComplete = { config, areaMap ->
    when (config.accessType) {
      AccessType.READ, AccessType.READ_WRITE -> {
        val bounds = areaMap[DoorArea(DoorValue.ROW_1_LEFT)]
        // Use bounds?.minValue / bounds?.maxValue, then call getCurrentDoorPosition
      }
      else -> {
        // NONE or WRITE only — disable UI or use an alternate flow
      }
    }
  },
  onFailed = { e: Exception ->
    if (e is VehicleException) {
      // Capability lookup failed: e.errorCode
    }
  }
)

// End Vehicle
vehicle.release()
```

*Java*
Get Property API example
```java
// Create Vehicle instance
Vehicle vehicle = new Vehicle(context);

// Start Vehicle
vehicle.initialize();

Door door = vehicle.getDoor();
door.getCurrentDoorPosition(
  new DoorArea(DoorValue.ROW_1_LEFT),
  pos -> {
    // do something
    return Unit.INSTANCE;
  },
  e -> {
    // do something
    return Unit.INSTANCE;
  }
);

Display display = vehicle.getDisplay();
display.getCurrentSpeedDisplayUnit(
  unit -> {
    // do something
    return Unit.INSTANCE;
  },
  e -> {
    // do something
    return Unit.INSTANCE;
  }
);

// End Vehicle
vehicle.release();
```
Set Property API example
```java
// Create Vehicle instance
Vehicle vehicle = new Vehicle(context);

// Start Vehicle
vehicle.initialize();

Door door = vehicle.getDoor();
door.setDoorPosition(
  new DoorArea(DoorValue.ROW_1_LEFT),
  1,
  e -> {
    // do something
    return Unit.INSTANCE;
  }
);

// End Vehicle
vehicle.release();
```
Callback API example
```java
// Create Vehicle instance
Vehicle vehicle = new Vehicle(context);

// Start Vehicle
vehicle.initialize();

// Create callback listener
GearSelectionListener callbackListener = new GearSelectionListener() {
  @Override
  public void onSelected(@Nullable VehicleGear gear) {
    // do something
  }

  @Override
  public void onFailed(@NonNull Exception e) {
    // do something
  }
};

Gear gear = vehicle.getGear();

// register callback
gear.registerGearSelection(callbackListener);

// unregister callback
gear.unregisterGearSelection(callbackListener);

// End Vehicle
vehicle.release();
```

**Constructors**
- `Vehicle`
  - `constructor(context: Context)`

**Functions**
- `getBrake` — Get the object of Brake to use Brake APIs.
  - `fun getBrake(): Brake`
- `getCarInfo` — Get the object of CarInfo to use CarInfo APIs.
  - `fun getCarInfo(): CarInfo`
- `getDisplay` — Get the object of Display to use Display APIs.
  - `fun getDisplay(): Display`
- `getDoor` — Get the object of Door to use Door APIs.
  - `fun getDoor(): Door`
- `getDrivingMode` — Get the object of DrivingMode to use DrivingMode APIs.
  - `fun getDrivingMode(): DrivingMode`
- `getEvBattery` — Get the object of EvBattery to use EvBattery APIs.
  - `fun getEvBattery(): EvBattery`
- `getFuel` — Get the object of Fuel to use Fuel APIs.
  - `fun getFuel(): Fuel`
- `getHVAC` — Get the object of HVAC to use HVAC APIs.
  - `fun getHVAC(): HVAC`
- `getLight` — Get the object of Light to use Light APIs.
  - `fun getLight(): Light`
- `getOdometer` — Get the object of Odometer to use Odometer APIs.
  - `fun getOdometer(): Odometer`
- `getSafety` — Get the object of Safety to use Safety APIs.
  - `fun getSafety(): Safety`
- `getSeat` — Get the object of Seat to use Seat APIs.
  - `fun getSeat(): Seat`
- `getSideMirror` — Get the object of SideMirror to use SideMirror APIs.
  - `fun getSideMirror(): SideMirror`
- `getSteeringWheel` — Get the object of SteeringWheel to use SteeringWheel APIs.
  - `fun getSteeringWheel(): SteeringWheel`
- `getTire` — Get the object of Tire to use Tire APIs.
  - `fun getTire(): Tire`
- `getToll` — Get the object of Toll to use Toll APIs.
  - `fun getToll(): Toll`
- `getTurnSignal` — Get the object of TurnSignal to use TurnSignal APIs.
  - `fun getTurnSignal(): TurnSignal`
- `getWindow` — Get the object of Window to use Window APIs.
  - `fun getWindow(): Window`
- `getWiper` — Get the object of Wiper to use Wiper APIs.
  - `fun getWiper(): Wiper`
- `initialize` — Initialize sdk. It is called while the application is created.
  - `fun initialize()`
- `release` — Release sdk. It is called while the application is destroyed.
  - `fun release()`
