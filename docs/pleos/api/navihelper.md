# NaviHelper SDK — API Reference

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/entry 이하 Dokka API Reference (2026-11-05 수집)
> 사이트 HTML 본문은 비어 있고 JS 청크에 실제 내용이 들어 있어 거기서 추출했습니다. 클래스(타입) 페이지 기준이며 멤버별 상세 페이지 설명은 포함하지 않습니다.
> `*(deprecated)*`는 원문에서 취소선으로 표시된 시그니처입니다. 사용 패턴은 상위 폴더의 SDK 사용 가이드 문서를 함께 보세요.

## 패키지 목록

- `ai.pleos.playground.navi.constants`
- `ai.pleos.playground.navi.data`
- `ai.pleos.playground.navi.helper.listener`
- `ai.pleos.playground.navi.helper`

---

## 패키지 `ai.pleos.playground.navi.constants`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/entry


**Types**
- `BookmarkType`
  - `@Serializable enum BookmarkType: Enum<BookmarkType>`
- `CameraType`
  - `@Serializable enum CameraType: Enum<CameraType>`
- `CautionPointType`
  - `@Serializable enum CautionPointType: Enum<CautionPointType>`
- `NaviErrorCode`
  - `@Serializable enum NaviErrorCode: Enum<NaviErrorCode>`
- `NavigationStatus`
  - `@Serializable enum NavigationStatus: Enum<NavigationStatus>`
- `NaviMode`
  - `@Serializable enum NaviMode: Enum<NaviMode>`
- `RouteOption`
  - `@Serializable enum RouteOption: Enum<RouteOption>`
- `RoutePointPoiType`
  - `@Serializable enum RoutePointPoiType: Enum<RoutePointPoiType>`
- `RoutePointSettingType`
  - `@Serializable enum RoutePointSettingType: Enum<RoutePointSettingType>`
- `RoutePointType`
  - `@Serializable enum RoutePointType: Enum<RoutePointType>`
- `TrafficCongestionType`
  - `@Serializable enum TrafficCongestionType: Enum<TrafficCongestionType>`
- `TrafficEventType`
  - `@Serializable enum TrafficEventType: Enum<TrafficEventType>`
- `TurnType`
  - `@Serializable enum TurnType: Enum<TurnType>`
- `WaypointCmdType`
  - `@Serializable enum WaypointCmdType: Enum<WaypointCmdType>`
- `WaypointIndex`
  - `@Serializable enum WaypointIndex: Enum<WaypointIndex>`

### BookmarkType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/bookmark-type/entry

`@Serializable enum BookmarkType: Enum<BookmarkType>`

**Entries**
- `HOME` — Home
- `OFFICE` — Office
- `EV_CHARGING_STATION` — EV Charging Station
- `DEFAULT` — Default

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<BookmarkType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): BookmarkType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<BookmarkType>`

### CameraType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/camera-type/entry

`@Serializable enum CameraType: Enum<CameraType>`

**Entries**
- `SPEED_CAMERA_FIX` — Fixed speed camera
- `SPEED_CAMERA_MOV` — Mobile speed camera
- `SPEED_CAMERA_SECTION_START` — Section enforcement start camera
- `SECTION_SPEED_ALERT` — Section speed alert
- `SPEED_CAMERA_SECTION_END` — Section enforcement end camera
- `SPEED_CAMERA_TRAFFIC_LIGHT` — Red light speed camera
- `SPEED_CAMERA_REAR` — Rear-facing speed enforcement camera
- `VARIABLE_SPEED_CAMERA` — Variable speed limit enforcement camera
- `SPEED_CAMERA_SCHOOL_ZONE` — School zone speed enforcement camera
- `SPEED_CAMERA_SCHOOL_ZONE_TRAFFIC_LIGHT` — School zone red light speed enforcement camera

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<CameraType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): CameraType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<CameraType>`

### CautionPointType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/caution-point-type/entry

`@Serializable enum CautionPointType: Enum<CautionPointType>`

**Entries**
- `SPEED_BUMP` — Speed bump
- `SCHOOL_ZONE` — School zone
- `ACCIDENT_PRONE_AREA` — High accident area
- `RAILROAD_CROSSING` — Railroad crossing
- `FOG_AREA` — Fog area
- `FALLING_ROCK_AREA` — Falling rocks caution zone
- `DOWNHILL_CAUTION_AREA` — Steep descent caution zone
- `WILDLIFE_CROSSING` — Wild animal caution zone
- `NARROW_ROAD` — Narrowing road
- `HIGHWAY_CONSTRUCTION` — Highway construction
- `TRAFFIC_INFO_COLLECTION` — Traffic information collection
- `NO_PARKING_ZONE` — No parking or stopping caution
- `TRAFFIC_LIGHT_VIOLATION_ENFORCEMENT` — Red light enforcement caution
- `BUS_LANE_VIOLATION_ENFORCEMENT` — Bus lane violation enforcement caution
- `CUT_IN_VIOLATION_ENFORCEMENT` — Cut-in violation enforcement caution
- `BLOCK_INTERSECTION_ENFORCEMENT` — Blocking intersection violation enforcement caution
- `LANE_CHANGE_CAMERA_ALERT` — Lane change violation camera caution
- `SLIPPERY_ROAD_ALERT` — Slippery road detection caution
- `ROAD_DAMAGE_ALERT` — Road damage detection caution
- `WRONG_WAY_DRIVING_ALERT` — Wrong-way driving alert
- `BUS_LANE_UNAVAILABLE_NOTICE` — Bus lane access restriction alert
- `BUS_LANE_AVAILABLE_NOTICE` — Bus lane access allowed alert
- `SIGN_MALFUNCION` — Sign system malfunction

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<CautionPointType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): CautionPointType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<CautionPointType>`

### NaviErrorCode

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/navi-error-code/entry

`@Serializable enum NaviErrorCode: Enum<NaviErrorCode>`

**Entries**
- `CAUSE_LOW_BATTERY` — Low battery alert
- `CAUSE_SEARCH_ROUTE_OVER_DISTANCE` — Error: route path over max distance
- `CAUSE_SEARCH_ROUTE` — Error: route server side
- `CAUSE_SEARCH_ROUTE_TOO_SHORT_DISTANCE` — Error: distance between current location and destination is too short
- `CAUSE_SEARCH_TRIP` — Error: trip server side

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<NaviErrorCode>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): NaviErrorCode`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<NaviErrorCode>`

### NaviMode

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/navi-mode/entry

`@Serializable enum NaviMode: Enum<NaviMode>`

**Entries**
- `NOT_WORKING` — Navi Initializing or downloading map, etc.
- `SAFE_GUIDING` — Safe guiding
- `ROUTE_GUIDING` — Route guiding
- `SIMULATION_GUIDING` — Simulation guiding

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<NaviMode>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): NaviMode`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<NaviMode>`

### NavigationStatus

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/navigation-status/entry

`@Serializable enum NavigationStatus: Enum<NavigationStatus>`

**Entries**
- `INACTIVE` — The navigation is not functioning or has completely shut down.
- `INITIALIZING` — The navigation service or map UI is being prepared. This includes initialization of settings, map rendering, and GPS sensor preparation.
- `FREE_DRIVE` — A state where only the map is shown following the user's location without route guidance. Normal driving mode.
- `ROUTE_DRIVE` — A state where the user has set a destination and route guidance is activated. Real-time navigation is in operation.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<NavigationStatus>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): NavigationStatus`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<NavigationStatus>`

### RouteOption

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/route-option/entry

`@Serializable enum RouteOption: Enum<RouteOption>`

**Entries**
- `RECOMMENDED` — Recommended
- `SHORT_DISTANCE` — Short distance
- `SHORT_TIME` — Short time
- `FREE` — Free
- `HIGHWAY` — Highway
- `COMFORTABLE` — Comfortable
- `SCHOOL_ZONE` — School zone

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<RouteOption>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): RouteOption`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<RouteOption>`

### RoutePointPoiType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/route-point-poi-type/entry

`@Serializable enum RoutePointPoiType: Enum<RoutePointPoiType>`

**Entries**
- `NORMAL` — General location or no POI search performed.
- `EV_CHARGING` — EV charging station

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<RoutePointPoiType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): RoutePointPoiType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<RoutePointPoiType>`

### RoutePointSettingType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/route-point-setting-type/entry

`@Serializable enum RoutePointSettingType: Enum<RoutePointSettingType>`

**Entries**
- `AUTO` — Automatic
- `MANUAL` — User-defined

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<RoutePointSettingType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): RoutePointSettingType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<RoutePointSettingType>`

### RoutePointType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/route-point-type/entry

`@Serializable enum RoutePointType: Enum<RoutePointType>`

**Entries**
- `ORIGIN` — Departure
- `WAYPOINT` — Waypoint
- `DESTINATION` — Destination

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<RoutePointType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): RoutePointType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<RoutePointType>`

### TrafficCongestionType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/traffic-congestion-type/entry

`@Serializable enum TrafficCongestionType: Enum<TrafficCongestionType>`

**Entries**
- `UNKNOWN` — Unknown (No traffic data collected or SafeGuiding)
- `FAST` — Fast (Smooth traffic)
- `MODERATE` — Moderate (Moderate traffic)
- `SLOW` — Slow (Slow traffic)
- `HEAVY` — Heavy (Congested traffic)

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TrafficCongestionType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TrafficCongestionType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TrafficCongestionType>`

### TrafficEventType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/traffic-event-type/entry

`@Serializable enum TrafficEventType: Enum<TrafficEventType>`

**Entries**
- `ETC` — Etc. (Other)
- `ACCIDENT` — Accident
- `ROAD_DAMAGE` — Road damage
- `REPAIRING` — Road repair
- `EVENT` — Event
- `DISASTER` — Disaster

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TrafficEventType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TrafficEventType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TrafficEventType>`

### TurnType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/turn-type/entry

`@Serializable enum TurnType: Enum<TurnType>`

**Entries**
- `TURN_UNKNOWN` — UNKNOWN
- `TURN_STRAIGHT` — Go straight
- `TURN_RIGHT` — Turn right
- `TURN_LEFT` — Turn left
- `TURN_KEEP_R` — Right side
- `TURN_KEEP_L` — Left side
- `TURN_SHARP_R` — Sharp right turn
- `TURN_SHARP_L` — Sharp left turn
- `TURN_UTURN` — U-turn
- `TURN_ENTER_IC_R` — Enter highway (Right)
- `TURN_ENTER_IC_L` — Enter highway (Left)
- `TURN_ENTER_IC_C` — Enter highway (Front)
- `TURN_EXIT_IC_R` — Exit highway (Right)
- `TURN_EXIT_IC_L` — Exit highway (Left)
- `TURN_EXIT_IC_C` — Exit highway (Front)
- `TURN_ENTER_UNDER_R` — Enter underpass (Right)
- `TURN_ENTER_UNDER_L` — Enter underpass (Left)
- `TURN_ENTER_UNDER_C` — Enter underpass (Front)
- `TURN_AVOID_UNDER_R` — Exit underpass (Right)
- `TURN_AVOID_UNDER_L` — Exit underpass (Left)
- `TURN_AVOID_UNDER_C` — Exit underpass (Front)
- `TURN_ENTER_OVER_R` — Enter overpass (Right)
- `TURN_ENTER_OVER_L` — Enter overpass (Left)
- `TURN_ENTER_OVER_C` — Enter overpass (Front)
- `TURN_AVOID_OVER_R` — Exit overpass (Right)
- `TURN_AVOID_OVER_L` — Exit overpass (Left)
- `TURN_AVOID_OVER_C` — Exit overpass (Front)
- `TURN_ENTER_ROUNDABOUT_02` — Enter roundabout (2 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_03` — Enter roundabout (3 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_04` — Enter roundabout (4 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_06` — Enter roundabout (6 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_08` — Enter roundabout (8 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_09` — Enter roundabout (9 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_10` — Enter roundabout (10 o'clock direction)
- `TURN_ENTER_ROUNDABOUT_12` — Enter roundabout (12 o'clock direction)
- `TURN_EXIT_ROUNDABOUT` — Exit roundabout
- `TURN_WAY` — Waypoint
- `TURN_ARRIVE` — Arrival information
- `TURN_TUNNEL` — Tunnel
- `TURN_DEVIDE_MERGE` — Split and merge road
- `TURN_TOLL` — Tollgate
- `TURN_ENTER_SERVICEAREA` — Enter the rest area
- `TURN_RESTAREA` — Drowsy driving rest area

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<TurnType>`
- `value`
  - `val value: Long`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): TurnType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<TurnType>`

### WaypointCmdType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/waypoint-cmd-type/entry

`@Serializable enum WaypointCmdType: Enum<WaypointCmdType>`

**Entries**
- `ADD` — Add
- `REMOVE` — Remove

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WaypointCmdType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WaypointCmdType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WaypointCmdType>`

### WaypointIndex

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.constants/waypoint-index/entry

`@Serializable enum WaypointIndex: Enum<WaypointIndex>`

**Entries**
- `FIRST` — First
- `SECOND` — Second
- `ALL` — All

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<WaypointIndex>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): WaypointIndex`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<WaypointIndex>`


---

## 패키지 `ai.pleos.playground.navi.data`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/entry


**Types**
- `BookmarkInfo` — Data class representing bookmark information for a specific location.
  - `@Serializable data class BookmarkInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val poiId: String, val poiSubId: String?, val address: String, val category: BookmarkType, val poiProvider: String = "")`
- `CameraAlert` — Data class representing a camera alert, typically used in navigation systems.
  - `@Serializable data class CameraAlert(val id: String = "", val type: CameraType, val status: Boolean, val speedLimit: Int, val remainDistance: Int, val sectionAvrSpeed: Int, val isOverSpeed: Boolean = false, val isOverSectionAvrSpeed: Boolean = false)`
- `CautionPoint` — Data class representing caution point alerts.
  - `@Serializable data class CautionPoint(val id: String, val type: CautionPointType, val remainDistance: Int)`
- `ChargerOperator` — Data class representing an electric vehicle (EV) charger operator.
  - `@Serializable data class ChargerOperator(val id: String, val name: String)`
- `ChargingPlan` — Data class representing the specific charging plan session at a route point.
  - `@Serializable data class ChargingPlan(val duration: Int = 0, val startSoc: Int = 0, val endSoc: Int = 0)`
- `CurrentLocationInfo` — Data class representing the current location information in a navigation system.
  - `@Serializable data class CurrentLocationInfo(val latitude: Double, val longitude: Double, val heading: Double = 0.0, val address: String, val routeId: String, val currentRouteIndex: Int?)`
- `DestinationArrivedInfo` — Data class representing information when the destination is reached in a navigation system.
  - `@Serializable data class DestinationArrivedInfo(val duration: Int, val distance: Int, val name: String, val address: String)`
- `DestinationInfo` — Data class representing destination information in a navigation system.
  - `@Serializable data class DestinationInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val address: String, val remainDistance: Int, val remainTime: Int, val tollFare: Int, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`
- `DrivingInfo` — Data class representing driving information, including the destination and waypoints.
  - `@Serializable data class DrivingInfo(val destination: DrivingState, val waypoints: List<DrivingState>)`
- `DrivingState` — Data class representing the driving state in a route.
  - `@Serializable data class DrivingState(val name: String, val duration: Int, val distance: Int, val remainDestinationChargingValue: Int, val isChargingStation: Boolean = false, val isRecommendedChargingStation: Boolean = false, val maxChargingPower: Int = 0, val targetSoc: Int = 0, val chargerCount: Int = 0, val chargingPlan: ChargingPlan? = null)`
- `NavigationScreenRequest` — Sealed class representing a request to switch the navigation screen.
  - `@Serializable sealed class NavigationScreenRequest`
- `RecentDestinationInfo` — Data class representing information about a recently visited destination.
  - `@Serializable data class RecentDestinationInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val address: String, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`
- `RequestWaypointInfo` — Data class representing a waypoint request in a navigation system.
  - `@Serializable data class RequestWaypointInfo(val latitude: Double, val longitude: Double, val poiName: String?, val waypointIndex: WaypointIndex, val poiId: String, val address: String, val poiSubId: String, val poiProvider: String = "")`
- `RouteInfo` — Data class representing navigation route information.
  - `@Serializable data class RouteInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val poiName: String, val poiId: String, val address: String, val poiSubId: String, val routeOption: RouteOption = RouteOption.RECOMMENDED, val poiProvider: String = "", val isMobileRequest: Boolean = false)`
- `RouteOverviewShowInfo` — Data class representing the route overview display settings.
  - `@Serializable data class RouteOverviewShowInfo(val durationMs: Int = 5000)`
- `RoutePointInfo` — Data class representing destination information in a navigation system.
  - `@Serializable data class RoutePointInfo(val latitude: Double, val longitude: Double, val poiName: String, val poiId: String, val ratioOnRoute: Double, val poiType: RoutePointPoiType, val pointType: RoutePointType, val settingType: RoutePointSettingType)`
- `RouteStartInfo` — Data class representing information when starting a route in a navigation system.
  - `@Serializable data class RouteStartInfo(val duration: Int, val distance: Int, val tollFare: Int, val name: String, val remainDestinationChargingValue: Int)`
- `RouteStateInfo` — Data class representing the current state of a navigation route.
  - `@Serializable data class RouteStateInfo(val termsAgreement: Boolean, val naviMode: NaviMode, val isRouting: Boolean, val routeOption: RouteOption, val remainDestinationChargingInt: Int, val remainWaypointChargingInt: List<Int>, val trafficStatus: TrafficStatus?)`
- `RouteWithWaypointsRequest` — Data class representing a route request, including the destination and waypoints.
  - `@Serializable data class RouteWithWaypointsRequest(val destination: RouteInfo, val waypoints: List<RequestWaypointInfo>)`
- `TBTInfo` — Data class representing Turn-By-Turn (TBT) navigation information.
  - `@Serializable data class TBTInfo(val type: TurnType, val distance: Double, val description: String)`
- `TrafficCongestionInfo` — Data class representing traffic congestion information.
  - `@Serializable data class TrafficCongestionInfo(val offset: Double, val distance: Double, val trafficCongestionTime: Long, val trafficCongestionType: TrafficCongestionType, val avrSpeed: Double, val roadName: String, val startCrossName: String, val endCrossName: String)`
- `TrafficCongestionSummary` — Data class representing a summary of traffic congestion.
  - `@Serializable data class TrafficCongestionSummary(val totalCongestionDist: Double, val totalCongestionTime: Long, val totalCongestionAvrSpeed: Double)`
- `TrafficEventInfo` — Data class representing traffic information.
  - `@Serializable data class TrafficEventInfo(val latitude: Double, val longitude: Double, val eventType: TrafficEventType, val startTime: String, val endTime: String, val description: String)`
- `TrafficInfo` — Data class representing traffic congestion information.
  - `@Serializable data class TrafficInfo(val congestionType: TrafficCongestionType, val startRatio: Int, val endRatio: Int)`
- `TrafficStatus` — Data class representing the overall traffic status, including congestion and traffic events.
  - `@Serializable data class TrafficStatus(val summary: TrafficCongestionSummary, val congestionItemList: List<TrafficCongestionInfo>, val trafficEventList: List<TrafficEventInfo>)`
- `TripInfo` — Data class representing trip information.
  - `@Serializable data class TripInfo(val distance: Int, val eta: Int, val estimatedSocAtDest: Int, val energyConsumption: Double, val summary: TripSummary)`
- `TripRequest` — Data class representing a trip request.
  - `@Serializable data class TripRequest(val latitude: Double, val longitude: Double, val poiId: String, val poiName: String, val poiSubId: String?)`
- `TripSummary` — Data class representing driving route information.
  - `@Serializable data class TripSummary(val trafficConditions: List<TrafficInfo>, val routePoints: List<RoutePointInfo>)`
- `WaypointArrivedInfo` — Data class representing information when arriving at a waypoint during navigation.
  - `@Serializable data class WaypointArrivedInfo(val duration: Int, val distance: Int, val index: WaypointIndex, val name: String, val address: String)`
- `WaypointChangedInfo` — Data class representing information about a waypoint change in a navigation system.
  - `@Serializable data class WaypointChangedInfo(val index: WaypointIndex, val cmdType: WaypointCmdType, val routeStartInfo: RouteStartInfo)`
- `WaypointInfo` — Data class representing information about a waypoint in a navigation system.
  - `@Serializable data class WaypointInfo(val name: String, val address: String, val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val remainDistance: Int, val remainTime: Int, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`

### BookmarkInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/bookmark-info/entry

`@Serializable data class BookmarkInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val poiId: String, val poiSubId: String?, val address: String, val category: BookmarkType, val poiProvider: String = "")`
Data class representing bookmark information for a specific location.

**Constructors**
- `BookmarkInfo`
  - `constructor(latitude: Double, longitude: Double, displayLatitude: Double? = null, displayLongitude: Double? = null, name: String, poiId: String, poiSubId: String?, address: String, category: BookmarkType, poiProvider: String = "")`

**Properties**
- `address` — The Address is the complete location information of a place (ex 123 Main St, Los Angeles, CA 90001, United States)
  - `val address: String`
- `category` — The category of the bookmark. @see BookmarkType
  - `val category: BookmarkType`
- `displayLatitude` — Displays the latitude coordinate on the map.
  - `val displayLatitude: Double? = null`
- `displayLongitude` — Displays the longitude coordinate on the map.
  - `val displayLongitude: Double? = null`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `name` — The name of the bookmarked location.
  - `val name: String`
- `poiId` — POI ID (Point of Interest ID) is a unique identifier for a specific POI on a map
  - `val poiId: String`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String?`

### CameraAlert

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/camera-alert/entry

`@Serializable data class CameraAlert(val id: String = "", val type: CameraType, val status: Boolean, val speedLimit: Int, val remainDistance: Int, val sectionAvrSpeed: Int, val isOverSpeed: Boolean = false, val isOverSectionAvrSpeed: Boolean = false)`
Data class representing a camera alert, typically used in navigation systems.

**Constructors**
- `CameraAlert`
  - `constructor(id: String = "", type: CameraType, status: Boolean, speedLimit: Int, remainDistance: Int, sectionAvrSpeed: Int, isOverSpeed: Boolean = false, isOverSectionAvrSpeed: Boolean = false)`

**Properties**
- `id` — Camera ID
  - `val id: String`
- `isOverSectionAvrSpeed` — Indicates whether there is over speed on the average speed over a section.
  - `val isOverSectionAvrSpeed: Boolean = false`
- `isOverSpeed` — Indicates whether over speed has occurred
  - `val isOverSpeed: Boolean = false`
- `remainDistance` — The remaining distance (in meters) to the camera from the current location.
  - `val remainDistance: Int`
- `sectionAvrSpeed` — The average speed (km/h), within a speed enforcement section. CameraType.SPEED_CAMERA_SECTION only
  - `val sectionAvrSpeed: Int`
- `speedLimit` — The speed limit enforced by the camera. (km/h)
  - `val speedLimit: Int`
- `status` — Indicates whether the camera alert is active (true) or inactive (false).
  - `val status: Boolean`
- `type` — The type of camera. @see CameraType
  - `val type: CameraType`

### CautionPoint

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/caution-point/entry

`@Serializable data class CautionPoint(val id: String, val type: CautionPointType, val remainDistance: Int)`
Data class representing caution point alerts.

**Constructors**
- `CautionPoint`
  - `constructor(id: String, type: CautionPointType, remainDistance: Int)`

**Properties**
- `id` — Caution Point ID
  - `val id: String`
- `remainDistance` — Remaining distance to caution zone (in meters)
  - `val remainDistance: Int`
- `type` — The type of caution point (e.g., school zone, fog area).
  - `val type: CautionPointType`

### ChargerOperator

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/charger-operator/entry

`@Serializable data class ChargerOperator(val id: String, val name: String)`
Data class representing an electric vehicle (EV) charger operator.

**Constructors**
- `ChargerOperator`
  - `constructor(id: String, name: String)`

**Properties**
- `id` — The unique identifier for the charger operator.
  - `val id: String`
- `name` — The name of the charger operator or service provider.
  - `val name: String`

### ChargingPlan

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/charging-plan/entry

`@Serializable data class ChargingPlan(val duration: Int = 0, val startSoc: Int = 0, val endSoc: Int = 0)`
Data class representing the specific charging plan session at a route point.

**Constructors**
- `ChargingPlan`
  - `constructor(duration: Int = 0, startSoc: Int = 0, endSoc: Int = 0)`

**Properties**
- `duration` — The estimated charging time required at this location (seconds).
  - `val duration: Int = 0`
- `endSoc` — The target battery level to achieve after completing the charge (%).
  - `val endSoc: Int = 0`
- `startSoc` — The estimated battery level when starting the charge (%).
  - `val startSoc: Int = 0`

### CurrentLocationInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/current-location-info/entry

`@Serializable data class CurrentLocationInfo(val latitude: Double, val longitude: Double, val heading: Double = 0.0, val address: String, val routeId: String, val currentRouteIndex: Int?)`
Data class representing the current location information in a navigation system.

**Constructors**
- `CurrentLocationInfo`
  - `constructor(latitude: Double, longitude: Double, heading: Double = 0.0, address: String, routeId: String, currentRouteIndex: Int?)`

**Properties**
- `address` — The address of the current location.
  - `val address: String`
- `currentRouteIndex` — The current index in the route sequence (nullable).
  - `val currentRouteIndex: Int?`
- `heading` — The direction of travel.
  - `val heading: Double = 0.0`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `routeId` — The unique identifier of the route being followed.
  - `val routeId: String`

### DestinationArrivedInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/destination-arrived-info/entry

`@Serializable data class DestinationArrivedInfo(val duration: Int, val distance: Int, val name: String, val address: String)`
Data class representing information when the destination is reached in a navigation system.

**Constructors**
- `DestinationArrivedInfo`
  - `constructor(duration: Int, distance: Int, name: String, address: String)`

**Properties**
- `address` — The address of the destination.
  - `val address: String`
- `distance` — The total distance traveled in meters to the destination.
  - `val distance: Int`
- `duration` — The total travel time in seconds to reach the destination.
  - `val duration: Int`
- `name` — The name of the destination.
  - `val name: String`

### DestinationInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/destination-info/entry

`@Serializable data class DestinationInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val address: String, val remainDistance: Int, val remainTime: Int, val tollFare: Int, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`
Data class representing destination information in a navigation system.

**Constructors**
- `DestinationInfo`
  - `constructor(latitude: Double, longitude: Double, displayLatitude: Double? = null, displayLongitude: Double? = null, name: String, address: String, remainDistance: Int, remainTime: Int, tollFare: Int, poiId: String, poiSubId: String?, poiProvider: String = "")`

**Properties**
- `address` — The address of the destination.
  - `val address: String`
- `displayLatitude` — Displays the latitude coordinate on the map.
  - `val displayLatitude: Double? = null`
- `displayLongitude` — Displays the longitude coordinate on the map.
  - `val displayLongitude: Double? = null`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `name` — The name of the destination.
  - `val name: String`
- `poiId` — The unique identifier for POI.
  - `val poiId: String`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String?`
- `remainDistance` — The remaining distance from the starting point to the waypoint (in meters).
  - `val remainDistance: Int`
- `remainTime` — The estimated remaining travel time to the waypoint (in seconds).
  - `val remainTime: Int`
- `tollFare` — The total toll fare required for the route to the destination (in won).
  - `val tollFare: Int`

### DrivingInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/driving-info/entry

`@Serializable data class DrivingInfo(val destination: DrivingState, val waypoints: List<DrivingState>)`
Data class representing driving information, including the destination and waypoints.

**Constructors**
- `DrivingInfo`
  - `constructor(destination: DrivingState, waypoints: List<DrivingState>)`

**Properties**
- `destination` — The final destination state in the driving route
  - `val destination: DrivingState`
- `waypoints` — The state of the list of waypoints in the driving route
  - `val waypoints: List<DrivingState>`

### DrivingState

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/driving-state/entry

`@Serializable data class DrivingState(val name: String, val duration: Int, val distance: Int, val remainDestinationChargingValue: Int, val isChargingStation: Boolean = false, val isRecommendedChargingStation: Boolean = false, val maxChargingPower: Int = 0, val targetSoc: Int = 0, val chargerCount: Int = 0, val chargingPlan: ChargingPlan? = null)`
Data class representing the driving state in a route.

**Constructors**
- `DrivingState`
  - `constructor(name: String, duration: Int, distance: Int, remainDestinationChargingValue: Int, isChargingStation: Boolean = false, isRecommendedChargingStation: Boolean = false, maxChargingPower: Int = 0, targetSoc: Int = 0, chargerCount: Int = 0, chargingPlan: ChargingPlan? = null)`

**Properties**
- `chargerCount` — The total number of chargers available at the charging station.
  - `val chargerCount: Int = 0`
- `chargingPlan` — The specific charging plan session if charging is scheduled at this point. Returns null if no charging is planned. @see ChargingPlan
  - `val chargingPlan: ChargingPlan? = null`
- `distance` — The distance in meters to this point from the start.
  - `val distance: Int`
- `duration` — The estimated travel time in seconds from the current location to this target point (waypoint or destination).
  - `val duration: Int`
- `isChargingStation` — Indicates whether this point is a charging station.
  - `val isChargingStation: Boolean = false`
- `isRecommendedChargingStation` — Indicates whether this charging station was recommended by the navigation app. This value is meaningful only when isChargingStation is true. otherwise, it should be ignored.
  - `val isRecommendedChargingStation: Boolean = false`
- `maxChargingPower` — Indicates the maximum charging power of the charging station (kW).
  - `val maxChargingPower: Int = 0`
- `name` — The name of the location.
  - `val name: String`
- `remainDestinationChargingValue` — The estimated remaining battery or fuel level upon reaching this point.
  - `val remainDestinationChargingValue: Int`
- `targetSoc` — The required minimum battery level upon arrival at this point (%).
  - `val targetSoc: Int = 0`

### NavigationScreenRequest

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/navigation-screen-request/entry

`@Serializable sealed class NavigationScreenRequest`
Sealed class representing a request to switch the navigation screen.

**Inheritors**
- `Foreground`
- `FavoriteMain`
- `AddHomeFavorite`
- `AddWorkFavorite`
- `NearbyChargingStations`
- `Settings`
- `MoveToCurrentLocationOnMap`
- `OverviewRoute`
- `SearchPlace`
- `POIDetailPopup`
- `RecentDestinations`
- `RouteOptionList`
- `EditRoute`

**Types**
- `AddHomeFavorite` — Add to home favorites screen
  - `@Serializable @SerialName(value = "AddHomeFavorite") data object AddHomeFavorite: NavigationScreenRequest`
- `AddWorkFavorite` — Add to work favorites screen
  - `@Serializable @SerialName(value = "AddWorkFavorite") data object AddWorkFavorite: NavigationScreenRequest`
- `EditRoute` — Route editing screen
  - `@Serializable @SerialName(value = "EditRoute") data object EditRoute: NavigationScreenRequest`
- `FavoriteMain` — Main favorites screen
  - `@Serializable @SerialName(value = "FavoriteMain") data object FavoriteMain: NavigationScreenRequest`
- `Foreground` — Navigation Foreground
  - `@Serializable @SerialName(value = "Foreground") data object Foreground: NavigationScreenRequest`
- `MoveToCurrentLocationOnMap` — Current location screen on map
  - `@Serializable @SerialName(value = "MoveToCurrentLocationOnMap") data object MoveToCurrentLocationOnMap: NavigationScreenRequest`
- `NearbyChargingStations` — Nearby charging stations search screen
  - `@Serializable @SerialName(value = "NearbyChargingStations") data object NearbyChargingStations: NavigationScreenRequest`
- `OverviewRoute` — View entire route
  - `@Serializable @SerialName(value = "OverviewRoute") data class OverviewRoute(val info: RouteOverviewShowInfo): NavigationScreenRequest`
- `POIDetailPopup` — POI details popup screen
  - `@Serializable @SerialName(value = "POIDetailPopup") data class POIDetailPopup(val poiId: String, val poiProvider: String = ""): NavigationScreenRequest`
- `RecentDestinations` — Recent destinations screen
  - `@Serializable @SerialName(value = "RecentDestinations") data object RecentDestinations: NavigationScreenRequest`
- `RouteOptionList` — Route option list screen
  - `@Serializable @SerialName(value = "RouteOptionList") data object RouteOptionList: NavigationScreenRequest`
- `SearchPlace` — Place search screen
  - `@Serializable @SerialName(value = "SearchPlace") data class SearchPlace(val keyword: String): NavigationScreenRequest`
- `Settings` — Navigation settings screen
  - `@Serializable @SerialName(value = "Settings") data object Settings: NavigationScreenRequest`

### RecentDestinationInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/recent-destination-info/entry

`@Serializable data class RecentDestinationInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val name: String, val address: String, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`
Data class representing information about a recently visited destination.

**Constructors**
- `RecentDestinationInfo`
  - `constructor(latitude: Double, longitude: Double, displayLatitude: Double? = null, displayLongitude: Double? = null, name: String, address: String, poiId: String, poiSubId: String?, poiProvider: String = "")`

**Properties**
- `address` — The Address is the complete location information of a place. (ex 123 Main St, Los Angeles, CA 90001, United States)
  - `val address: String`
- `displayLatitude` — Displays the latitude coordinate on the map.
  - `val displayLatitude: Double? = null`
- `displayLongitude` — Displays the longitude coordinate on the map.
  - `val displayLongitude: Double? = null`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `name` — The name of the recent destination.
  - `val name: String`
- `poiId` — POI ID is a unique identifier for a specific POI on a map.
  - `val poiId: String`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String?`

### RequestWaypointInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/request-waypoint-info/entry

`@Serializable data class RequestWaypointInfo(val latitude: Double, val longitude: Double, val poiName: String?, val waypointIndex: WaypointIndex, val poiId: String, val address: String, val poiSubId: String, val poiProvider: String = "")`
Data class representing a waypoint request in a navigation system.

**Constructors**
- `RequestWaypointInfo`
  - `constructor(latitude: Double, longitude: Double, poiName: String?, waypointIndex: WaypointIndex, poiId: String, address: String, poiSubId: String, poiProvider: String = "")`

**Properties**
- `address` — The Address is the complete location information of a place (ex 123 Main St, Los Angeles, CA 90001, United States)
  - `val address: String`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `poiId` — POI ID is a unique identifier for a specific POI on a map
  - `val poiId: String`
- `poiName` — The name of the POI at the waypoint.
  - `val poiName: String?`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String`
- `waypointIndex` — The index of the waypoint in the route sequence. @see WaypointIndex
  - `val waypointIndex: WaypointIndex`

### RouteInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-info/entry

`@Serializable data class RouteInfo(val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val poiName: String, val poiId: String, val address: String, val poiSubId: String, val routeOption: RouteOption = RouteOption.RECOMMENDED, val poiProvider: String = "", val isMobileRequest: Boolean = false)`
Data class representing navigation route information.

**Constructors**
- `RouteInfo`
  - `constructor(latitude: Double, longitude: Double, displayLatitude: Double? = null, displayLongitude: Double? = null, poiName: String, poiId: String, address: String, poiSubId: String, routeOption: RouteOption = RouteOption.RECOMMENDED, poiProvider: String = "", isMobileRequest: Boolean = false)`

**Properties**
- `address` — The Address is the complete location information of a place (ex 123 Main St, Los Angeles, CA 90001, United States)
  - `val address: String`
- `displayLatitude` — Displays the latitude coordinate on the map.
  - `val displayLatitude: Double? = null`
- `displayLongitude` — Displays the longitude coordinate on the map.
  - `val displayLongitude: Double? = null`
- `isMobileRequest` — Whether the request is received from mobile (default: false)
  - `val isMobileRequest: Boolean = false`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `poiId` — POI ID is a unique identifier for a specific POI on a map
  - `val poiId: String`
- `poiName` — The name of the POI.
  - `val poiName: String`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String`
- `routeOption` — The routing option to be used (default: recommended route)
  - `val routeOption: RouteOption`

### RouteOverviewShowInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-overview-show-info/entry

`@Serializable data class RouteOverviewShowInfo(val durationMs: Int = 5000)`
Data class representing the route overview display settings.

**Constructors**
- `RouteOverviewShowInfo`
  - `constructor(durationMs: Int = 5000)`

**Properties**
- `durationMs` — Shows the route overview duration in milliseconds (default: 5000ms).
  - `val durationMs: Int = 5000`

### RoutePointInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-point-info/entry

`@Serializable data class RoutePointInfo(val latitude: Double, val longitude: Double, val poiName: String, val poiId: String, val ratioOnRoute: Double, val poiType: RoutePointPoiType, val pointType: RoutePointType, val settingType: RoutePointSettingType)`
Data class representing destination information in a navigation system.

**Constructors**
- `RoutePointInfo`
  - `constructor(latitude: Double, longitude: Double, poiName: String, poiId: String, ratioOnRoute: Double, poiType: RoutePointPoiType, pointType: RoutePointType, settingType: RoutePointSettingType)`

**Properties**
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `poiId` — The unique identifier for POI.
  - `val poiId: String`
- `poiName` — The name of the POI.
  - `val poiName: String`
- `pointType` — Type of the route point. @see RoutePointType
  - `val pointType: RoutePointType`
- `poiType` — POI type of the route point. @see RoutePointPoiType
  - `val poiType: RoutePointPoiType`
- `ratioOnRoute` — Ratio along the route (0.0–100.0).
  - `val ratioOnRoute: Double`
- `settingType` — Setting type of the route point. @see RoutePointSettingType
  - `val settingType: RoutePointSettingType`

### RouteStartInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-start-info/entry

`@Serializable data class RouteStartInfo(val duration: Int, val distance: Int, val tollFare: Int, val name: String, val remainDestinationChargingValue: Int)`
Data class representing information when starting a route in a navigation system.

**Constructors**
- `RouteStartInfo`
  - `constructor(duration: Int, distance: Int, tollFare: Int, name: String, remainDestinationChargingValue: Int)`

**Properties**
- `distance` — The total distance of the route in meters.
  - `val distance: Int`
- `duration` — The estimated travel time for the route in seconds.
  - `val duration: Int`
- `name` — The name of the destination or starting point.
  - `val name: String`
- `remainDestinationChargingValue` — The estimated remaining battery or fuel level upon reaching the destination.
  - `val remainDestinationChargingValue: Int`
- `tollFare` — The total toll fare required for the route.
  - `val tollFare: Int`

### RouteStateInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-state-info/entry

`@Serializable data class RouteStateInfo(val termsAgreement: Boolean, val naviMode: NaviMode, val isRouting: Boolean, val routeOption: RouteOption, val remainDestinationChargingInt: Int, val remainWaypointChargingInt: List<Int>, val trafficStatus: TrafficStatus?)`
Data class representing the current state of a navigation route.

**Constructors**
- `RouteStateInfo`
  - `constructor(termsAgreement: Boolean, naviMode: NaviMode, isRouting: Boolean, routeOption: RouteOption, remainDestinationChargingInt: Int, remainWaypointChargingInt: List<Int>, trafficStatus: TrafficStatus?)`

**Properties**
- `isRouting` — Indicates whether a route is currently active.
  - `val isRouting: Boolean`
- `naviMode` — The current navigation mode. @See NaviMode
  - `val naviMode: NaviMode`
- `remainDestinationChargingInt` — The estimated remaining battery or fuel level upon reaching the destination.
  - `val remainDestinationChargingInt: Int`
- `remainWaypointChargingInt` — A list of estimated remaining battery or fuel levels at each waypoint.
  - `val remainWaypointChargingInt: List<Int>`
- `routeOption` — The selected routing option for the navigation. @See RouteOption
  - `val routeOption: RouteOption`
- `termsAgreement` — Indicates whether the user has agreed to the navigation terms.
  - `val termsAgreement: Boolean`
- `trafficStatus` — Current traffic status along the route (nullable). @see TrafficStatus
  - `val trafficStatus: TrafficStatus?`

### RouteWithWaypointsRequest

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-with-waypoints-request/entry

`@Serializable data class RouteWithWaypointsRequest(val destination: RouteInfo, val waypoints: List<RequestWaypointInfo>)`
Data class representing a route request, including the destination and waypoints.

**Constructors**
- `RouteWithWaypointsRequest`
  - `constructor(destination: RouteInfo, waypoints: List<RequestWaypointInfo>)`

**Properties**
- `destination` — The final destination information in the requested route. @see RouteInfo
  - `val destination: RouteInfo`
- `waypoints` — The list of waypoint information in the requested route. @see RequestWaypointInfo
  - `val waypoints: List<RequestWaypointInfo>`

### TBTInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/t-b-t-info/entry

`@Serializable data class TBTInfo(val type: TurnType, val distance: Double, val description: String)`
Data class representing Turn-By-Turn (TBT) navigation information.

**Constructors**
- `TBTInfo`
  - `constructor(type: TurnType, distance: Double, description: String)`

**Properties**
- `description` — A text description of the turn instruction.
  - `val description: String`
- `distance` — Distance to the upcoming turn (in meters)
  - `val distance: Double`
- `type` — The type of turn instruction (e.g., left turn, right turn).
  - `val type: TurnType`

### TrafficCongestionInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/traffic-congestion-info/entry

`@Serializable data class TrafficCongestionInfo(val offset: Double, val distance: Double, val trafficCongestionTime: Long, val trafficCongestionType: TrafficCongestionType, val avrSpeed: Double, val roadName: String, val startCrossName: String, val endCrossName: String)`
Data class representing traffic congestion information.

**Constructors**
- `TrafficCongestionInfo`
  - `constructor(offset: Double, distance: Double, trafficCongestionTime: Long, trafficCongestionType: TrafficCongestionType, avrSpeed: Double, roadName: String, startCrossName: String, endCrossName: String)`

**Properties**
- `avrSpeed` — The average speed within the traffic congestion (in km/h).
  - `val avrSpeed: Double`
- `distance` — The length of the traffic congestion (in meters).
  - `val distance: Double`
- `endCrossName` — The name of the nearby intersection at the end of the congestion section.
  - `val endCrossName: String`
- `offset` — The distance from the current location to the start of the congested area (meters).
  - `val offset: Double`
- `roadName` — Represents the main congested road name.
  - `val roadName: String`
- `startCrossName` — The name of the nearby intersection at the start of the congestion section.
  - `val startCrossName: String`
- `trafficCongestionTime` — The duration of traffic congestion (in seconds).
  - `val trafficCongestionTime: Long`
- `trafficCongestionType` — The type of traffic congestion. @see TrafficCongestionType
  - `val trafficCongestionType: TrafficCongestionType`

### TrafficCongestionSummary

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/traffic-congestion-summary/entry

`@Serializable data class TrafficCongestionSummary(val totalCongestionDist: Double, val totalCongestionTime: Long, val totalCongestionAvrSpeed: Double)`
Data class representing a summary of traffic congestion.

**Constructors**
- `TrafficCongestionSummary`
  - `constructor(totalCongestionDist: Double, totalCongestionTime: Long, totalCongestionAvrSpeed: Double)`

**Properties**
- `totalCongestionAvrSpeed` — The average speed across all congested areas (in km/h).
  - `val totalCongestionAvrSpeed: Double`
- `totalCongestionDist` — The total distance of all congested areas (in meters).
  - `val totalCongestionDist: Double`
- `totalCongestionTime` — The total duration of traffic congestion (in seconds).
  - `val totalCongestionTime: Long`

### TrafficEventInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/traffic-event-info/entry

`@Serializable data class TrafficEventInfo(val latitude: Double, val longitude: Double, val eventType: TrafficEventType, val startTime: String, val endTime: String, val description: String)`
Data class representing traffic information.

**Constructors**
- `TrafficEventInfo`
  - `constructor(latitude: Double, longitude: Double, eventType: TrafficEventType, startTime: String, endTime: String, description: String)`

**Properties**
- `description` — A brief description of the traffic event.
  - `val description: String`
- `endTime` — The end time of the traffic event (yyyy-MM-dd hh:mm "2023-11-05T22:08:28")
  - `val endTime: String`
- `eventType` — The type of traffic event (e.g., accident, roadwork, closure).
  - `val eventType: TrafficEventType`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `startTime` — The start time of the traffic event (yyyy-MM-dd hh:mm "2023-11-05T22:08:28")
  - `val startTime: String`

### TrafficInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/traffic-info/entry

`@Serializable data class TrafficInfo(val congestionType: TrafficCongestionType, val startRatio: Int, val endRatio: Int)`
Data class representing traffic congestion information.

**Constructors**
- `TrafficInfo`
  - `constructor(congestionType: TrafficCongestionType, startRatio: Int, endRatio: Int)`

**Properties**
- `congestionType` — The type of traffic congestion. @see TrafficCongestionType
  - `val congestionType: TrafficCongestionType`
- `endRatio` — End ratio along the route (0–100).
  - `val endRatio: Int`
- `startRatio` — Start ratio along the route (0–100).
  - `val startRatio: Int`

### TrafficStatus

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/traffic-status/entry

`@Serializable data class TrafficStatus(val summary: TrafficCongestionSummary, val congestionItemList: List<TrafficCongestionInfo>, val trafficEventList: List<TrafficEventInfo>)`
Data class representing the overall traffic status, including congestion and traffic events.

**Constructors**
- `TrafficStatus`
  - `constructor(summary: TrafficCongestionSummary, congestionItemList: List<TrafficCongestionInfo>, trafficEventList: List<TrafficEventInfo>)`

**Properties**
- `congestionItemList` — A list of detailed traffic congestion sections. @see TrafficCongestionInfo
  - `val congestionItemList: List<TrafficCongestionInfo>`
- `summary` — A summary of the total traffic congestion details. @see TrafficCongestionSummary
  - `val summary: TrafficCongestionSummary`
- `trafficEventList` — A list of traffic events such as accidents or road closures. @see TrafficEventInfo
  - `val trafficEventList: List<TrafficEventInfo>`

### TripInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/trip-info/entry

`@Serializable data class TripInfo(val distance: Int, val eta: Int, val estimatedSocAtDest: Int, val energyConsumption: Double, val summary: TripSummary)`
Data class representing trip information.

**Constructors**
- `TripInfo`
  - `constructor(distance: Int, eta: Int, estimatedSocAtDest: Int, energyConsumption: Double, summary: TripSummary)`

**Properties**
- `distance` — The total distance traveled in meters to the destination.
  - `val distance: Int`
- `energyConsumption` — Estimated battery consumption after route driving (Wh)
  - `val energyConsumption: Double`
- `estimatedSocAtDest` — Estimated remaining battery after route driving (%)
  - `val estimatedSocAtDest: Int`
- `eta` — Estimated Time of Arrival (ETA) in seconds.
  - `val eta: Int`
- `summary` — Driving route information. @see TripSummary
  - `val summary: TripSummary`

### TripRequest

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/trip-request/entry

`@Serializable data class TripRequest(val latitude: Double, val longitude: Double, val poiId: String, val poiName: String, val poiSubId: String?)`
Data class representing a trip request.

**Constructors**
- `TripRequest`
  - `constructor(latitude: Double, longitude: Double, poiId: String, poiName: String, poiSubId: String?)`

**Properties**
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `poiId` — POI ID is a unique identifier for a specific POI on a map.
  - `val poiId: String`
- `poiName` — The name of the POI.
  - `val poiName: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String?`

### TripSummary

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/trip-summary/entry

`@Serializable data class TripSummary(val trafficConditions: List<TrafficInfo>, val routePoints: List<RoutePointInfo>)`
Data class representing driving route information.

**Constructors**
- `TripSummary`
  - `constructor(trafficConditions: List<TrafficInfo>, routePoints: List<RoutePointInfo>)`

**Properties**
- `routePoints` — List of waypoint and destination information.
  - `val routePoints: List<RoutePointInfo>`
- `trafficConditions` — List of traffic congestion information.
  - `val trafficConditions: List<TrafficInfo>`

### WaypointArrivedInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/waypoint-arrived-info/entry

`@Serializable data class WaypointArrivedInfo(val duration: Int, val distance: Int, val index: WaypointIndex, val name: String, val address: String)`
Data class representing information when arriving at a waypoint during navigation.

**Constructors**
- `WaypointArrivedInfo`
  - `constructor(duration: Int, distance: Int, index: WaypointIndex, name: String, address: String)`

**Properties**
- `address` — The address of the waypoint.
  - `val address: String`
- `distance` — The total distance traveled in meters to the waypoint.
  - `val distance: Int`
- `duration` — The total travel time in seconds to reach the waypoint.
  - `val duration: Int`
- `index`
  - `val index: WaypointIndex`
- `name` — The name of the waypoint.
  - `val name: String`

### WaypointChangedInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/waypoint-changed-info/entry

`@Serializable data class WaypointChangedInfo(val index: WaypointIndex, val cmdType: WaypointCmdType, val routeStartInfo: RouteStartInfo)`
Data class representing information about a waypoint change in a navigation system.

**Constructors**
- `WaypointChangedInfo`
  - `constructor(index: WaypointIndex, cmdType: WaypointCmdType, routeStartInfo: RouteStartInfo)`

**Properties**
- `cmdType` — Type of waypoint change command. @See WaypointCmdType
  - `val cmdType: WaypointCmdType`
- `index` — Index of the waypoint in the route sequence. @See WaypointIndex
  - `val index: WaypointIndex`
- `routeStartInfo` — Updated route information after waypoint change. @See RouteStartInfo
  - `val routeStartInfo: RouteStartInfo`

### WaypointInfo

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/waypoint-info/entry

`@Serializable data class WaypointInfo(val name: String, val address: String, val latitude: Double, val longitude: Double, val displayLatitude: Double? = null, val displayLongitude: Double? = null, val remainDistance: Int, val remainTime: Int, val poiId: String, val poiSubId: String?, val poiProvider: String = "")`
Data class representing information about a waypoint in a navigation system.

**Constructors**
- `WaypointInfo`
  - `constructor(name: String, address: String, latitude: Double, longitude: Double, displayLatitude: Double? = null, displayLongitude: Double? = null, remainDistance: Int, remainTime: Int, poiId: String, poiSubId: String?, poiProvider: String = "")`

**Properties**
- `address` — The waypoint address.
  - `val address: String`
- `displayLatitude` — Displays the latitude coordinate on the map.
  - `val displayLatitude: Double? = null`
- `displayLongitude` — Displays the longitude coordinate on the map.
  - `val displayLongitude: Double? = null`
- `latitude` — Latitude is the geographic coordinate that specifies the north-south position of a point on Earth. It is measured in degrees (°), ranging from -90° (South) to 90° (North).
  - `val latitude: Double`
- `longitude` — Longitude is the geographic coordinate that specifies the east-west position of a point on Earth. It is measured in degrees (°), ranging from -180° (West) to 180° (East).
  - `val longitude: Double`
- `name` — The waypoint name.
  - `val name: String`
- `poiId` — The unique identifier for POI.
  - `val poiId: String`
- `poiProvider` — POI Content Provider Name (e.g., 42dot, Google)
  - `val poiProvider: String`
- `poiSubId` — POI Sub ID is a POI sequence, in case of the same POI, it is distinguished by sequence.
  - `val poiSubId: String?`
- `remainDistance` — The remaining distance from the starting point to the waypoint (in meters).
  - `val remainDistance: Int`
- `remainTime` — The estimated remaining travel time to the waypoint (in seconds).
  - `val remainTime: Int`


---

## 패키지 `ai.pleos.playground.navi.helper.listener`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.helper.listener/entry


**Types**
- `NaviHelperEventListener` — Interface definition for callback data received from the Navi application.
  - `interface NaviHelperEventListener`

### NaviHelperEventListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.helper.listener/navi-helper-event-listener/entry

`interface NaviHelperEventListener`
Interface definition for callback data received from the Navi application.

**Functions**
- `onBookMarkChanged` — Called when arriving at or removing the bookmark.
  - `open fun onBookMarkChanged()`
- `onBookmarkInfo` — Called when the bookmark information is changed.
  - `open fun onBookmarkInfo(bookmarkInfoList: List<BookmarkInfo>)`
- `onCameraAlert` — Called when camera alerts are notified. Received when speeding camera exists.
  - `open fun onCameraAlert(cameraAlertList: List<CameraAlert>)`
- `onCautionPointInfo` — Called when caution point information is notified.
  - `open fun onCautionPointInfo(cautionPointList: List<CautionPoint>)`
- `onChargerOperatorInfo` — Called when charger operator information is notified.
  - `open fun onChargerOperatorInfo(chargerOperatorList: List<ChargerOperator>)`
- `onCurrentLocationInfo` — Called when the current location information is changed.
  - `open fun onCurrentLocationInfo(currentLocationInfo: CurrentLocationInfo)`
- `onCurrentRoadSpeedLimit` — Called when the road speed limit is notified. Received every second after the event listener registration.
  - `open fun onCurrentRoadSpeedLimit(speedLimit: Int)`
- `onDestinationArrived` — Called when arriving at the destination.
  - `open fun onDestinationArrived(destinationArrivedInfo: DestinationArrivedInfo)`
- `onDestinationChanged` — Called when arriving at or removing the destination.
  - `open fun onDestinationChanged()`
- `onDestinationInfo` — Called when the destination information is changed.
  - `open fun onDestinationInfo(destinationInfo: DestinationInfo)`
- `onDrivingInfo` — Called when driving information is notified.
  - `open fun onDrivingInfo(drivingInfo: DrivingInfo)`
- `onError` — Called when a navigation error occurs.
  - `open fun onError(errorCode: NaviErrorCode)`
- `onNavigationStatus` — Called when the navigation status is notified. Received whenever the driving status changes
  - `open fun onNavigationStatus(status: NavigationStatus)`
- `onRecentDestinationInfo` — Called when the recent destination information is changed.
  - `open fun onRecentDestinationInfo(recentDestinationInfoList: List<RecentDestinationInfo>)`
- `onReRouteEnded` — Called when navigation re-route is completed.
  - `open fun onReRouteEnded()`
- `onRouteCancelled` — Called when the current route is canceled.
  - `open fun onRouteCancelled()`
- `onRouteOptionChanged` — Called when the route type is changed.
  - `open fun onRouteOptionChanged(routeOption: RouteOption)`
- `onRouteStarted` — Called when navigation route search begins.
  - `open fun onRouteStarted(routeStartInfo: RouteStartInfo)`
- `onRouteStateInfo` — Called when the route state information is changed.
  - `open fun onRouteStateInfo(routeStateInfo: RouteStateInfo)`
- `onRoutingCompleted` — Called when navigation route search is completed.
  - `open fun onRoutingCompleted()`
- `onTBTInfo` — Called when the TBT information is notified.
  - `open fun onTBTInfo(tbtInfoList: List<TBTInfo>)`
- `onTripInfo` — Called when trip information is notified.
  - `open fun onTripInfo(tripInfo: TripInfo)`
- `onWayPointArrived` — Called when arriving at the waypoint.
  - `open fun onWayPointArrived(waypointArrivedInfo: WaypointArrivedInfo)`
- `onWaypointChanged` — Called when arriving at or removing the waypoint.
  - `open fun onWaypointChanged(waypointChangedInfo: WaypointChangedInfo)`
- `onWayPointInfo` — Called when the waypoint information is changed.
  - `open fun onWayPointInfo(waypointInfoList: List<WaypointInfo>)`


---

## 패키지 `ai.pleos.playground.navi.helper`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.helper/entry


**Types**
- `NaviHelper` — The entry point of the NaviHelper SDK. Create a NaviHelper instance with an Android Context to call NaviHelper APIs directly and register event-listener callbacks.
  - `class NaviHelper(context: Context)`

### NaviHelper

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.helper/navi-helper/entry

`class NaviHelper(context: Context)`
The entry point of the NaviHelper SDK. Create a NaviHelper instance with an Android Context to call NaviHelper APIs directly and register event-listener callbacks.

**Constructors**
- `NaviHelper`
  - `constructor(context: Context)`

**Functions**
- `addListener` — Adds a listener. Registers a listener for NaviHelper callback function
  - `fun addListener(listener: NaviHelperEventListener)`
- `addWaypoint` — Adds waypoint.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun addWaypoint(requestWaypointInfo: RequestWaypointInfo)`
- `cancelRoute` — Cancels route.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun cancelRoute()`
- `changeRouteOption` — Changes route option.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun changeRouteOption(routeOption: RouteOption)`
- `getBookmarkInfo` — Gets bookmark info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getBookmarkInfo()`
- `getChargerOperatorInfo` — Gets Charger Operator info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getChargerOperatorInfo()`
- `getCurrentLocationInfo` — Gets current location info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getCurrentLocationInfo()`
- `getDestinationInfo` — Gets destination info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getDestinationInfo()`
- `getRecentDestinationInfo` — Gets recent destination info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getRecentDestinationInfo()`
- `getRouteStateInfo` — Gets route state info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getRouteStateInfo()`
- `getTBTInfo` — Gets TBT info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getTBTInfo()`
- `getWaypointInfo` — Gets waypoint info.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun getWaypointInfo()`
- `initialize` — Initializes NaviHelper SDK. When the app starts or is created, it should call the initialization of SDK. / Initializes NaviHelper SDK with connection callbacks. Success is called when the first service connection is established. Failure is called when the initial connection retries are exhausted.
  - `fun initialize()` — Initializes NaviHelper SDK. When the app starts or is created, it should call the initialization of SDK.
  - `fun initialize(onConnected: () -> Unit, onFailed: (Throwable) -> Unit)` — Initializes NaviHelper SDK with connection callbacks. Success is called when the first service connection is established. Failure is called when the initial connection retries are exhausted.
- `release` — Releases NaviHelper SDK. When the app terminates or is destroyed, it should call the release of SDK
  - `fun release()`
- `removeListener` — Removes a listener.
  - `fun removeListener(listener: NaviHelperEventListener)`
- `removeWaypoint` — Removes waypoint.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun removeWaypoint(waypointIndex: WaypointIndex)`
- `requestNavigationScreen` — Requests navigation screen.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_CUSTOM_MAP") fun requestNavigationScreen(requestScreen: NavigationScreenRequest)`
- `requestReRoute` — Requests re-route.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun requestReRoute()`
- `requestRoute` — Requests route.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun requestRoute(routeInfo: RouteInfo)`
- `requestRouteWithWaypoints` — Requests a route with waypoints.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE_SEARCH") fun requestRouteWithWaypoints(request: RouteWithWaypointsRequest)`
- `requestTrip` — Requests trip.
  - `@RequiresPermission(value = "pleos.car.permission.NAVI_ROUTE") fun requestTrip(tripRequest: TripRequest)`
