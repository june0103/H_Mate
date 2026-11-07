# FusedLocation SDK — API Reference

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/entry 이하 Dokka API Reference (2026-11-05 수집)
> 사이트 HTML 본문은 비어 있고 JS 청크에 실제 내용이 들어 있어 거기서 추출했습니다. 클래스(타입) 페이지 기준이며 멤버별 상세 페이지 설명은 포함하지 않습니다.
> `*(deprecated)*`는 원문에서 취소선으로 표시된 시그니처입니다. 사용 패턴은 상위 폴더의 SDK 사용 가이드 문서를 함께 보세요.

## 패키지 목록

- `ai.pleos.playground.location.fused.listener`
- `ai.pleos.playground.location.fused.model`
- `ai.pleos.playground.location.fused`

---

## 패키지 `ai.pleos.playground.location.fused.listener`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.listener/entry


**Types**
- `FusedLocationListener` — Defines the listener interface for receiving location updates.
  - `interface FusedLocationListener`
- `ProximityAlertListener` — Receives events and errors for a registered proximity alert.
  - `interface ProximityAlertListener`

### FusedLocationListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.listener/fused-location-listener/entry

`interface FusedLocationListener`
Defines the listener interface for receiving location updates.

**Functions**
- `onChanged` — Invoked when a location update is available.
  - `abstract fun onChanged(location: LocationData?)`
- `onError`
  - `abstract fun onError(exception: Exception)`

### ProximityAlertListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.listener/proximity-alert-listener/entry

`interface ProximityAlertListener`
Receives events and errors for a registered proximity alert. Reports the alert identifier after registration and transitions for the configured area.

**Functions**
- `onError` — Invoked when the alert cannot be registered or processed.
  - `abstract fun onError(exception: Exception)`
- `onRegistered` — Invoked when the alert is registered successfully.
  - `abstract fun onRegistered(alertId: String)`
- `onTransition` — Invoked when a configured proximity transition is detected.
  - `abstract fun onTransition(event: ProximityAlertEvent)`


---

## 패키지 `ai.pleos.playground.location.fused.model`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/entry


**Types**
- `LocationData`
  - `data class LocationData(val latitude: Double, val longitude: Double, val altitude: Double, val heading: Float, val confidence: Float, val timestampMillis: Long)`
- `ProximityAlertEvent` — Describes a transition detected for a registered proximity alert.
  - `data class ProximityAlertEvent(val alertId: String, val transition: ProximityTransition)`
- `ProximityAlertRequest` — Defines an area and the transitions to monitor for a proximity alert.
  - `data class ProximityAlertRequest(val latitude: Double, val longitude: Double, val radiusMeters: Float, val transitionTypes: Set<ProximityTransition>)`
- `ProximityTransition` — Describes a transition that can trigger a proximity alert.
  - `enum ProximityTransition: Enum<ProximityTransition>`

### LocationData

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/location-data/entry

`data class LocationData(val latitude: Double, val longitude: Double, val altitude: Double, val heading: Float, val confidence: Float, val timestampMillis: Long)`

**Constructors**
- `LocationData`
  - `constructor(latitude: Double, longitude: Double, altitude: Double, heading: Float, confidence: Float, timestampMillis: Long)`

**Properties**
- `altitude`
  - `val altitude: Double`
- `confidence`
  - `val confidence: Float`
- `heading`
  - `val heading: Float`
- `latitude`
  - `val latitude: Double`
- `longitude`
  - `val longitude: Double`
- `timestampMillis`
  - `val timestampMillis: Long`

### ProximityAlertEvent

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/proximity-alert-event/entry

`data class ProximityAlertEvent(val alertId: String, val transition: ProximityTransition)`
Describes a transition detected for a registered proximity alert.

**Constructors**
- `ProximityAlertEvent`
  - `constructor(alertId: String, transition: ProximityTransition)`

**Properties**
- `alertId` — String: Identifier delivered when the alert registration succeeds.
  - `val alertId: String`
- `transition` — ProximityTransition: Detected transition.
  - `val transition: ProximityTransition`

### ProximityAlertRequest

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/proximity-alert-request/entry

`data class ProximityAlertRequest(val latitude: Double, val longitude: Double, val radiusMeters: Float, val transitionTypes: Set<ProximityTransition>)`
Defines an area and the transitions to monitor for a proximity alert.

**Constructors**
- `ProximityAlertRequest`
  - `constructor(latitude: Double, longitude: Double, radiusMeters: Float, transitionTypes: Set<ProximityTransition>)`

**Properties**
- `latitude` — Double: Finite center latitude in degrees, from -90 to 90 inclusive.
  - `val latitude: Double`
- `longitude` — Double: Finite center longitude in degrees, from -180 to 180 inclusive.
  - `val longitude: Double`
- `radiusMeters` — Float: Finite radius in meters, greater than zero.
  - `val radiusMeters: Float`
- `transitionTypes` — Set of ProximityTransition: Non-empty set of transitions that trigger the alert.
  - `val transitionTypes: Set<ProximityTransition>`

### ProximityTransition

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/proximity-transition/entry

`enum ProximityTransition: Enum<ProximityTransition>`
Describes a transition that can trigger a proximity alert.

**Entries**
- `ENTER` — Triggered when the device moves into the configured area.
- `EXIT` — Triggered when the device moves out of the configured area.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<ProximityTransition>`
- `name`
  - `val name: String`
- `ordinal`
  - `val ordinal: Int`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): ProximityTransition`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<ProximityTransition>`


---

## 패키지 `ai.pleos.playground.location.fused`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused/entry


**Types**
- `FusedLocation`
  - `class FusedLocation(context: Context)`

### FusedLocation

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused/fused-location/entry

`class FusedLocation(context: Context)`

**Constructors**
- `FusedLocation`
  - `constructor(context: Context)`

**Functions**
- `addProximityAlert` — Registers a proximity alert for the specified area and transitions.
  - `@RequiresPermission(value = "pleos.car.permission.FUSED_LOCATION") fun addProximityAlert(request: ProximityAlertRequest, listener: ProximityAlertListener)`
- `getLastKnownLocation` — Gets the last known location.
  - `@RequiresPermission(value = "pleos.car.permission.FUSED_LOCATION") fun getLastKnownLocation(callback: FusedLocationListener)`
- `initialize` — Initializes the SDK.
  - `fun initialize()`
- `isSupported` — Returns whether the Fused Location Service package is present on Pleos Connect.
  - `fun isSupported(): Boolean`
- `registerFusedLocationCallback` — Registers a callback to receive location updates.
  - `@RequiresPermission(value = "pleos.car.permission.FUSED_LOCATION") fun registerFusedLocationCallback(listener: FusedLocationListener)`
- `release` — Releases the SDK.
  - `fun release()`
- `removeProximityAlert` — Removes a previously registered proximity alert.
  - `@RequiresPermission(value = "pleos.car.permission.FUSED_LOCATION") fun removeProximityAlert(alertId: String)`
- `showLocationAccessBlockedPopup` — Displays a popup message informing the user that location access is blocked due to their current privacy settings.
  - `fun showLocationAccessBlockedPopup()`
- `unregisterFusedLocationCallback` — Stops receiving location updates through the specified listener.
  - `@RequiresPermission(value = "pleos.car.permission.FUSED_LOCATION") fun unregisterFusedLocationCallback(listener: FusedLocationListener)`
