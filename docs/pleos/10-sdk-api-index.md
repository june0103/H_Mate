# 10. SDK API Reference 색인 (클래스·멤버 이름)

> 출처: https://document.pleos.ai/sitemap.xml 의 `/docs/pleos-only/connect-sdk-pleos/<SDK>/<module>/<package>/<Class>/<member>` 경로 (2026-11-05 수집)
>
> **주의**
> - 이 파일은 **이름 색인**입니다(멤버 상세 페이지까지 포함). 시그니처·설명·필요 권한은 [`api/`](api/) 폴더의 SDK별 API Reference(`vehicle.md`, `navihelper.md`, `texttospeech.md`, `speechtotext.md`, `fusedlocation.md`, `llm.md`)를 보세요.
> - 이름은 URL 슬러그를 되돌려 만든 것입니다(`check-door-lock-capabilities` → `checkDoorLockCapabilities`, `h-v-a-c` → `HVAC`). 약어 대소문자가 실제와 다를 수 있습니다.
> - **사이트 색인은 실제 SDK보다 적을 수 있습니다** (실측 목록: [11-sdk-verification.md](11-sdk-verification.md)). 예: 이 프로젝트가 쓰는 Vehicle SDK 2.3.2의 `GearSelectionListener`, `VehicleGear`(기어) 관련 API는 사이트맵에 없습니다.
> - 정확한 시그니처가 필요하면 Gradle 캐시의 AAR을 직접 확인하세요:
>   `~/.gradle/caches/modules-2/files-2.1/ai.pleos.playground/<Artifact>/<version>/` → `.aar` 안의 `classes.jar`를 `javap -cp classes.jar <FQCN>`로 조회.
> - 사용 패턴(초기화·콜백·권한)은 04~07 문서를 우선 참고하세요.
>
> 패키지 아래 `- **Class**: member, ...` 형식. 멤버가 없는 항목은 enum 값·상수·인터페이스 등입니다.

## FusedLocation


### `ai.pleos.playground.location.fused.listener`

- **FusedLocationListener**: `onChanged`, `onError`
- **ProximityAlertListener**: `onError`, `onRegistered`, `onTransition`

### `ai.pleos.playground.location.fused.model`

- **LocationData**: `LocationData`, `altitude`, `confidence`, `heading`, `latitude`, `longitude`, `timestampMillis`
- **ProximityAlertEvent**: `ProximityAlertEvent`, `alertId`, `transition`
- **ProximityAlertRequest**: `ProximityAlertRequest`, `latitude`, `longitude`, `radiusMeters`, `transitionTypes`
- **ProximityTransition**: `ENTER`, `EXIT`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.location.fused`

- **FusedLocation**: `FusedLocation`, `addProximityAlert`, `getLastKnownLocation`, `initialize`, `isSupported`, `registerFusedLocationCallback`, `release`, `removeProximityAlert`, `showLocationAccessBlockedPopup`, `unregisterFusedLocationCallback`

## LLM


### `ai.pleos.playground.llm.constant`

- **Mode**: `ON_DEVICE`, `SERVER`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.llm.data`

- **LLMContent**: `LLMContent`, `parts`, `role`
- **ModelParameter**: `MAX_TOKENS`, `MODEL_NAME`, `TEMPERATURE`, `entries`, `valueOf`, `values`
- **Part**
- **TextPart**: `TextPart`, `text`

### `ai.pleos.playground.llm.listener`

- **OnServerConnectionListener**: `onConnected`, `onFailed`
- **ResultListener**: `onError`, `onResponse`

### `ai.pleos.playground.llm`

- **LLM**: `Companion`, `Companion.ERROR_IS_NOT_RUNNING`, `LLM`, `generateContent`, `getOnDeviceModelPromptsContents`, `initialize`, `registerApp`, `release`, `sendMessage`, `setModelParameter`, `setServerType`, `startChat`

## NaviHelper


### `ai.pleos.playground.navi.constants`

- **BookmarkType**: `DEFAULT`, `EV_CHARGING_STATION`, `HOME`, `OFFICE`, `entries`, `valueOf`, `values`
- **CameraType**: `SECTION_SPEED_ALERT`, `SPEED_CAMERA_FIX`, `SPEED_CAMERA_MOV`, `SPEED_CAMERA_REAR`, `SPEED_CAMERA_SCHOOL_ZONE`, `SPEED_CAMERA_SCHOOL_ZONE_TRAFFIC_LIGHT`, `SPEED_CAMERA_SECTION_END`, `SPEED_CAMERA_SECTION_START`, `SPEED_CAMERA_TRAFFIC_LIGHT`, `VARIABLE_SPEED_CAMERA`, `entries`, `valueOf`, `values`
- **CautionPointType**: `ACCIDENT_PRONE_AREA`, `BLOCK_INTERSECTION_ENFORCEMENT`, `BUS_LANE_AVAILABLE_NOTICE`, `BUS_LANE_UNAVAILABLE_NOTICE`, `BUS_LANE_VIOLATION_ENFORCEMENT`, `CUT_IN_VIOLATION_ENFORCEMENT`, `DOWNHILL_CAUTION_AREA`, `FALLING_ROCK_AREA`, `FOG_AREA`, `HIGHWAY_CONSTRUCTION`, `LANE_CHANGE_CAMERA_ALERT`, `NARROW_ROAD`, `NO_PARKING_ZONE`, `RAILROAD_CROSSING`, `ROAD_DAMAGE_ALERT`, `SCHOOL_ZONE`, `SIGN_MALFUNCION`, `SLIPPERY_ROAD_ALERT`, `SPEED_BUMP`, `TRAFFIC_INFO_COLLECTION`, `TRAFFIC_LIGHT_VIOLATION_ENFORCEMENT`, `WILDLIFE_CROSSING`, `WRONG_WAY_DRIVING_ALERT`, `entries`, `valueOf`, `values`
- **NaviErrorCode**: `CAUSE_LOW_BATTERY`, `CAUSE_SEARCH_ROUTE`, `CAUSE_SEARCH_ROUTE_OVER_DISTANCE`, `CAUSE_SEARCH_ROUTE_TOO_SHORT_DISTANCE`, `CAUSE_SEARCH_TRIP`, `entries`, `valueOf`, `values`
- **NaviMode**: `NOT_WORKING`, `ROUTE_GUIDING`, `SAFE_GUIDING`, `SIMULATION_GUIDING`, `entries`, `valueOf`, `values`
- **NavigationStatus**: `FREE_DRIVE`, `INACTIVE`, `INITIALIZING`, `ROUTE_DRIVE`, `entries`, `valueOf`, `values`
- **RouteOption**: `COMFORTABLE`, `FREE`, `HIGHWAY`, `RECOMMENDED`, `SCHOOL_ZONE`, `SHORT_DISTANCE`, `SHORT_TIME`, `entries`, `valueOf`, `values`
- **RoutePointPoiType**: `EV_CHARGING`, `NORMAL`, `entries`, `valueOf`, `values`
- **RoutePointSettingType**: `AUTO`, `MANUAL`, `entries`, `valueOf`, `values`
- **RoutePointType**: `DESTINATION`, `ORIGIN`, `WAYPOINT`, `entries`, `valueOf`, `values`
- **TrafficCongestionType**: `FAST`, `HEAVY`, `MODERATE`, `SLOW`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **TrafficEventType**: `ACCIDENT`, `DISASTER`, `ETC`, `EVENT`, `REPAIRING`, `ROAD_DAMAGE`, `entries`, `valueOf`, `values`
- **TurnType**: `TURN_ARRIVE`, `TURN_AVOID_OVER_C`, `TURN_AVOID_OVER_L`, `TURN_AVOID_OVER_R`, `TURN_AVOID_UNDER_C`, `TURN_AVOID_UNDER_L`, `TURN_AVOID_UNDER_R`, `TURN_DEVIDE_MERGE`, `TURN_ENTER_IC_C`, `TURN_ENTER_IC_L`, `TURN_ENTER_IC_R`, `TURN_ENTER_OVER_C`, `TURN_ENTER_OVER_L`, `TURN_ENTER_OVER_R`, `TURN_ENTER_ROUNDABOUT_02`, `TURN_ENTER_ROUNDABOUT_03`, `TURN_ENTER_ROUNDABOUT_04`, `TURN_ENTER_ROUNDABOUT_06`, `TURN_ENTER_ROUNDABOUT_08`, `TURN_ENTER_ROUNDABOUT_09`, `TURN_ENTER_ROUNDABOUT_10`, `TURN_ENTER_ROUNDABOUT_12`, `TURN_ENTER_SERVICEAREA`, `TURN_ENTER_UNDER_C`, `TURN_ENTER_UNDER_L`, `TURN_ENTER_UNDER_R`, `TURN_EXIT_IC_C`, `TURN_EXIT_IC_L`, `TURN_EXIT_IC_R`, `TURN_EXIT_ROUNDABOUT`, `TURN_KEEP_L`, `TURN_KEEP_R`, `TURN_LEFT`, `TURN_RESTAREA`, `TURN_RIGHT`, `TURN_SHARP_L`, `TURN_SHARP_R`, `TURN_STRAIGHT`, `TURN_TOLL`, `TURN_TUNNEL`, `TURN_UNKNOWN`, `TURN_UTURN`, `TURN_WAY`, `entries`, `value`, `valueOf`, `values`
- **WaypointCmdType**: `ADD`, `REMOVE`, `entries`, `valueOf`, `values`
- **WaypointIndex**: `ALL`, `FIRST`, `SECOND`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.navi.data`

- **BookmarkInfo**: `BookmarkInfo`, `address`, `category`, `displayLatitude`, `displayLongitude`, `latitude`, `longitude`, `name`, `poiId`, `poiProvider`, `poiSubId`
- **CameraAlert**: `CameraAlert`, `id`, `isOverSectionAvrSpeed`, `isOverSpeed`, `remainDistance`, `sectionAvrSpeed`, `speedLimit`, `status`, `type`
- **CautionPoint**: `CautionPoint`, `id`, `remainDistance`, `type`
- **ChargerOperator**: `ChargerOperator`, `id`, `name`
- **ChargingPlan**: `ChargingPlan`, `duration`, `endSoc`, `startSoc`
- **CurrentLocationInfo**: `CurrentLocationInfo`, `address`, `currentRouteIndex`, `heading`, `latitude`, `longitude`, `routeId`
- **DestinationArrivedInfo**: `DestinationArrivedInfo`, `address`, `distance`, `duration`, `name`
- **DestinationInfo**: `DestinationInfo`, `address`, `displayLatitude`, `displayLongitude`, `latitude`, `longitude`, `name`, `poiId`, `poiProvider`, `poiSubId`, `remainDistance`, `remainTime`, `tollFare`
- **DrivingInfo**: `DrivingInfo`, `destination`, `waypoints`
- **DrivingState**: `DrivingState`, `chargerCount`, `chargingPlan`, `distance`, `duration`, `isChargingStation`, `isRecommendedChargingStation`, `maxChargingPower`, `name`, `remainDestinationChargingValue`, `targetSoc`
- **NavigationScreenRequest**: `AddHomeFavorite`, `AddWorkFavorite`, `EditRoute`, `FavoriteMain`, `Foreground`, `MoveToCurrentLocationOnMap`, `NearbyChargingStations`, `OverviewRoute`, `OverviewRoute.OverviewRoute`, `OverviewRoute.info`, `POIDetailPopup`, `POIDetailPopup.POIDetailPopup`, `POIDetailPopup.poiId`, `POIDetailPopup.poiProvider`, `RecentDestinations`, `RouteOptionList`, `SearchPlace`, `SearchPlace.SearchPlace`, `SearchPlace.keyword`, `Settings`
- **RecentDestinationInfo**: `RecentDestinationInfo`, `address`, `displayLatitude`, `displayLongitude`, `latitude`, `longitude`, `name`, `poiId`, `poiProvider`, `poiSubId`
- **RequestWaypointInfo**: `RequestWaypointInfo`, `address`, `latitude`, `longitude`, `poiId`, `poiName`, `poiProvider`, `poiSubId`, `waypointIndex`
- **RouteInfo**: `RouteInfo`, `address`, `displayLatitude`, `displayLongitude`, `isMobileRequest`, `latitude`, `longitude`, `poiId`, `poiName`, `poiProvider`, `poiSubId`, `routeOption`
- **RouteOverviewShowInfo**: `RouteOverviewShowInfo`, `durationMs`
- **RoutePointInfo**: `RoutePointInfo`, `latitude`, `longitude`, `poiId`, `poiName`, `poiType`, `pointType`, `ratioOnRoute`, `settingType`
- **RouteStartInfo**: `RouteStartInfo`, `distance`, `duration`, `name`, `remainDestinationChargingValue`, `tollFare`
- **RouteStateInfo**: `RouteStateInfo`, `isRouting`, `naviMode`, `remainDestinationChargingInt`, `remainWaypointChargingInt`, `routeOption`, `termsAgreement`, `trafficStatus`
- **RouteWithWaypointsRequest**: `RouteWithWaypointsRequest`, `destination`, `waypoints`
- **TBTInfo**: `TBTInfo`, `description`, `distance`, `type`
- **TrafficCongestionInfo**: `TrafficCongestionInfo`, `avrSpeed`, `distance`, `endCrossName`, `offset`, `roadName`, `startCrossName`, `trafficCongestionTime`, `trafficCongestionType`
- **TrafficCongestionSummary**: `TrafficCongestionSummary`, `totalCongestionAvrSpeed`, `totalCongestionDist`, `totalCongestionTime`
- **TrafficEventInfo**: `TrafficEventInfo`, `description`, `endTime`, `eventType`, `latitude`, `longitude`, `startTime`
- **TrafficInfo**: `TrafficInfo`, `congestionType`, `endRatio`, `startRatio`
- **TrafficStatus**: `TrafficStatus`, `congestionItemList`, `summary`, `trafficEventList`
- **TripInfo**: `TripInfo`, `distance`, `energyConsumption`, `estimatedSocAtDest`, `eta`, `summary`
- **TripRequest**: `TripRequest`, `latitude`, `longitude`, `poiId`, `poiName`, `poiSubId`
- **TripSummary**: `TripSummary`, `routePoints`, `trafficConditions`
- **WaypointArrivedInfo**: `-Index--`, `WaypointArrivedInfo`, `address`, `distance`, `duration`, `name`
- **WaypointChangedInfo**: `-Index--`, `WaypointChangedInfo`, `cmdType`, `routeStartInfo`
- **WaypointInfo**: `WaypointInfo`, `address`, `displayLatitude`, `displayLongitude`, `latitude`, `longitude`, `name`, `poiId`, `poiProvider`, `poiSubId`, `remainDistance`, `remainTime`

### `ai.pleos.playground.navi.helper.listener`

- **NaviHelperEventListener**: `onBookMarkChanged`, `onBookmarkInfo`, `onCameraAlert`, `onCautionPointInfo`, `onChargerOperatorInfo`, `onCurrentLocationInfo`, `onCurrentRoadSpeedLimit`, `onDestinationArrived`, `onDestinationChanged`, `onDestinationInfo`, `onDrivingInfo`, `onError`, `onNavigationStatus`, `onReRouteEnded`, `onRecentDestinationInfo`, `onRouteCancelled`, `onRouteOptionChanged`, `onRouteStarted`, `onRouteStateInfo`, `onRoutingCompleted`, `onTBTInfo`, `onTripInfo`, `onWayPointArrived`, `onWayPointInfo`, `onWaypointChanged`

### `ai.pleos.playground.navi.helper`

- **NaviHelper**: `NaviHelper`, `addListener`, `addWaypoint`, `cancelRoute`, `changeRouteOption`, `getBookmarkInfo`, `getChargerOperatorInfo`, `getCurrentLocationInfo`, `getDestinationInfo`, `getRecentDestinationInfo`, `getRouteStateInfo`, `getTBTInfo`, `getWaypointInfo`, `initialize`, `release`, `removeListener`, `removeWaypoint`, `requestNavigationScreen`, `requestReRoute`, `requestRoute`, `requestRouteWithWaypoints`, `requestTrip`

## SpeechToText


### `ai.pleos.playground.stt.constant`

- **ErrorType**: `AUDIO_ERROR`, `INVALID_REQUEST_ERROR`, `MIC_ERROR`, `NETWORK_ERROR`, `NOT_SUPPORTED_API`, `ON_DEVICE_ENGINE_ERROR`, `PERMISSION_DENIED`, `SERVER_INTERNAL_ERROR`, `TIMEOUT`, `UNAUTHENTICATED`, `UNAVAILABLE_LANGUAGE`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **Mode**: `HYBRID`, `ON_DEVICE`, `SERVER`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.stt.data`

- **InferenceSource**: `Companion`, `Companion.DEFAULT`, `ON_DEVICE`, `SERVER`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.stt.listener`

- **OnInitializeListener**: `onFailure`, `onSuccess`
- **OnServerConnectionListener**: `onConnected`, `onFailed`
- **ResultListener**: `onEndedRecognition`, `onError`, `onReady`, `onStartedRecognition`, `onUpdated`, `onUpdatedEpdData`, `onUpdatedRms`

### `ai.pleos.playground.stt`

- **SpeechToText**: `Companion`, `Companion.version`, `SpeechToText`, `addListener`, `completeAudioSend`, `initialize`, `registerApp`, `release`, `removeListener`, `request`, `sendAudio`, `setLocale`, `stop`

## TextToSpeech


### `ai.pleos.playground.tts.constant`

- **ErrorType**: `AUDIO_ERROR`, `INVALID_REQUEST_ERROR`, `MIC_ERROR`, `NETWORK_ERROR`, `NOT_SUPPORTED_API`, `ON_DEVICE_ENGINE_ERROR`, `PERMISSION_DENIED`, `SERVER_INTERNAL_ERROR`, `TIMEOUT`, `UNAUTHENTICATED`, `UNAVAILABLE_LANGUAGE`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **Mode**: `HYBRID`, `ON_DEVICE`, `SERVER`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.tts.listener`

- **EventListener**: `onDone`, `onError`, `onReady`, `onStart`, `onStop`, `onUpdatedRms`
- **OnInitializeListener**: `onFailure`, `onSuccess`
- **OnServerConnectionListener**: `onConnected`, `onFailed`

### `ai.pleos.playground.tts`

- **TextToSpeech**: `Companion`, `Companion.version`, `TextToSpeech`, `addEventListener`, `initialize`, `registerApp`, `release`, `removeEventListener`, `setAudioAttributes`, `setAudioZoneId`, `setLocale`, `setSpeakerType`, `speak`, `stop`

## Vehicle


### `ai.pleos.playground.vehicle.api.model.area`

- **Area**
- **DoorArea**: `DoorArea`, `equals`, `hashCode`, `values`
- **MirrorArea**: `MirrorArea`, `equals`, `hashCode`, `values`
- **SeatArea**: `SeatArea`, `equals`, `hashCode`, `values`
- **TurnSignalArea**: `TurnSignalArea`, `equals`, `hashCode`, `values`
- **WheelArea**: `WheelArea`, `equals`, `hashCode`, `values`
- **WindowArea**: `WindowArea`, `equals`, `hashCode`, `values`

### `ai.pleos.playground.vehicle.api.model.capability`

- **AccessType**: `NONE`, `READ`, `READ_WRITE`, `WRITE`, `entries`, `valueOf`, `values`
- **AreaBounds**: `AreaBounds`, `maxValue`, `minValue`
- **CapabilityConfig**: `CapabilityConfig`, `accessType`
- **HvacTemperatureConfigArray**: `HvacTemperatureConfigArray`, `incrementCelsius`, `incrementFahrenheit`, `maxCelsius`, `maxFahrenheit`, `minCelsius`, `minFahrenheit`, `raw`
- **SmartGlassConfig**: `SmartGlassConfig`, `levelCount`, `zoneCount`

### `ai.pleos.playground.vehicle.api.model.common`

- **TripStatistics**: `TripStatistics`, `cumulative`, `current`, `get`, `instantaneous`, `raw`, `sinceRefill`

### `ai.pleos.playground.vehicle.api.model.telltale`

- **TelltaleData**: `TelltaleData`, `color`, `priority`, `state`, `type`, `warningCount`
- **Telltales**: `Telltales`, `telltalesProperty`

### `ai.pleos.playground.vehicle.api`

- **Brake**: `checkBrakePedalStatusCapabilities`, `getCurrentBrakePedalStatus`, `registerBrakePedalStatus`, `unregisterBrakePedalStatus`
- **CarInfo**: `checkCRNInfoCapabilities`, `checkVINInfoCapabilities`, `getCRNInfo`, `getVINInfo`
- **Display**: `checkTemperatureDisplayUnitCapabilities`, `getCurrentTemperatureDisplayUnit`, `registerTemperatureDisplayUnit`, `unregisterTemperatureDisplayUnit`
- **Door**: `checkDoorLockCapabilities`, `checkDoorPositionCapabilities`, `checkGloveBoxLockCapabilities`, `getCurrentDoorPosition`, `getCurrentGloveBoxLockState`, `getDoorLockState`, `registerDoorLockState`, `registerDoorPosition`, `registerGloveBoxLockState`, `setDoorLock`, `setDoorPosition`, `setGloveBoxLock`, `unregisterDoorLockState`, `unregisterDoorPosition`, `unregisterGloveBoxLockState`
- **DrivingMode**: `checkCruiseControlStateCapabilities`, `checkCruiseControlTargetSpeedCapabilities`, `checkDrivingModeStateCapabilities`, `getCruiseControlState`, `getCruiseControlTargetSpeed`, `getCurrentDrivingModeState`, `registerCruiseControlState`, `registerCruiseControlTargetSpeed`, `registerDrivingModeState`, `unregisterCruiseControlState`, `unregisterCruiseControlTargetSpeed`, `unregisterDrivingModeState`
- **EvBattery**: `checkCumulativeEvEfficiencyCapabilities`, `checkCurrentEvEfficiencyCapabilities`, `checkEvBatteryStateOfChargeCapabilities`, `checkEvChargeCurrentDrawLimitCapabilities`, `checkEvChargePercentLimitCapabilities`, `checkEvChargePortOpenCapabilities`, `checkEvChargeSwitchCapabilities`, `checkInstantaneousEvEfficiencyCapabilities`, `checkSinceChargeEvEfficiencyCapabilities`, `getCumulativeEvEfficiency`, `getCurrentEvEfficiency`, `getEvBatteryStateOfCharge`, `getEvChargeCurrentDrawLimit`, `getEvChargeSwitchState`, `getInstantaneousEvEfficiency`, `getSinceChargeEvEfficiency`, `registerCumulativeEvEfficiency`, `registerCurrentEvEfficiency`, `registerEvBatteryStateOfCharge`, `registerInstantaneousEvEfficiency`, `registerSinceChargeEvEfficiency`, `setEvChargeCurrentDrawLimit`, `setEvChargePercentLimit`, `setEvChargePortOpen`, `setEvChargeSwitch`, `unregisterCumulativeEvEfficiency`, `unregisterCurrentEvEfficiency`, `unregisterEvBatteryStateOfCharge`, `unregisterInstantaneousEvEfficiency`, `unregisterSinceChargeEvEfficiency`
- **Fuel**: `checkCumulativeFuelEconomyCapabilities`, `checkCurrentFuelEconomyCapabilities`, `checkFuelPercentCapabilities`, `checkInstantaneousFuelEconomyCapabilities`, `checkSinceChargeFuelEconomyCapabilities`, `getCumulativeFuelEconomy`, `getCurrentFuelEconomy`, `getFuelPercent`, `getInstantaneousFuelEconomy`, `getSinceChargeFuelEconomy`, `registerCumulativeFuelEconomy`, `registerCurrentFuelEconomy`, `registerFuelPercent`, `registerInstantaneousFuelEconomy`, `registerSinceChargeFuelEconomy`, `unregisterCumulativeFuelEconomy`, `unregisterCurrentFuelEconomy`, `unregisterFuelPercent`, `unregisterInstantaneousFuelEconomy`, `unregisterSinceChargeFuelEconomy`
- **HVAC**: `checkHvacAcCapabilities`, `checkHvacAirCleanerCapabilities`, `checkHvacAutoCapabilities`, `checkHvacAutoRecirculationCapabilities`, `checkHvacDefrosterCapabilities`, `checkHvacDualCapabilities`, `checkHvacFanDirectionCapabilities`, `checkHvacFanSpeedCapabilities`, `checkHvacMaxAcCapabilities`, `checkHvacMaxDefrostCapabilities`, `checkHvacPowerCapabilities`, `checkHvacRecirculationCapabilities`, `checkHvacTemperatureCapabilities`, `checkHvacTemperatureCurrentCapabilities`, `getHvacAcState`, `getHvacAirCleanerState`, `getHvacAutoRecirculationState`, `getHvacAutoState`, `getHvacDefrosterState`, `getHvacDualState`, `getHvacFanDirection`, `getHvacFanSpeed`, `getHvacMaxAcState`, `getHvacMaxDefrostState`, `getHvacPowerState`, `getHvacRecirculationState`, `getHvacTemperature`, `getHvacTemperatureCurrent`, `registerHvacAcState`, `registerHvacAirCleanerState`, `registerHvacAutoRecirculationState`, `registerHvacAutoState`, `registerHvacDefrosterState`, `registerHvacDualState`, `registerHvacFanSpeed`, `registerHvacMaxAcState`, `registerHvacMaxDefrostState`, `registerHvacPowerState`, `registerHvacRecirculationState`, `registerHvacTemperature`, `registerHvacTemperatureCurrent`, `setHvacAc`, `setHvacAirCleaner`, `setHvacAuto`, `setHvacAutoRecirculation`, `setHvacDefroster`, `setHvacDual`, `setHvacFanDirection`, `setHvacFanSpeed`, `setHvacMaxAc`, `setHvacMaxDefrost`, `setHvacPower`, `setHvacRecirculation`, `setHvacTemperature`, `unregisterHvacAcState`, `unregisterHvacAirCleanerState`, `unregisterHvacAutoRecirculationState`, `unregisterHvacAutoState`, `unregisterHvacDefrosterState`, `unregisterHvacDualState`, `unregisterHvacFanSpeed`, `unregisterHvacMaxAcState`, `unregisterHvacMaxDefrostState`, `unregisterHvacPowerState`, `unregisterHvacRecirculationState`, `unregisterHvacTemperature`, `unregisterHvacTemperatureCurrent`
- **Light**: `checkAmbientColorAutoByDriveModeEnabledCapabilities`, `checkAmbientLightBrightnessCapabilities`, `checkAmbientLightBrightnessColorCapabilities`, `checkAmbientLightStateCapabilities`, `checkAmbientLightSwitchCapabilities`, `checkCabinLightStateCapabilities`, `checkCabinLightSwitchCapabilities`, `checkFrontFogLightStateCapabilities`, `checkFrontFogLightSwitchCapabilities`, `checkHighBeamLightStateCapabilities`, `checkHighBeamLightSwitchCapabilities`, `checkLowBeamLightStateCapabilities`, `checkLowBeamLightSwitchCapabilities`, `checkReadingLightStateCapabilities`, `checkReadingLightSwitchCapabilities`, `checkRearFogLightStateCapabilities`, `checkRearFogLightSwitchCapabilities`, `checkTailLampLightStateCapabilities`, `checkTailLampLightSwitchCapabilities`, `getAmbientColorAutoByDriveModeEnabled`, `getAmbientLightBrightness`, `getAmbientLightBrightnessColor`, `getAmbientLightState`, `getAmbientLightSwitch`, `getCabinLightSwitch`, `getCurrentCabinLightState`, `getCurrentFrontFogLightState`, `getCurrentHighBeamLightState`, `getCurrentLowBeamLightState`, `getCurrentReadingLightState`, `getCurrentRearFogLightState`, `getCurrentTailLampLightState`, `getFrontFogLightSwitch`, `getHighBeamLightSwitch`, `getLowBeamLightSwitch`, `getReadingLightSwitch`, `getRearFogLightSwitch`, `getTailLampLightSwitch`, `registerAmbientColorAutoByDriveModeEnabled`, `registerAmbientLightBrightnessColorListener`, `registerAmbientLightBrightnessListener`, `registerAmbientLightStateListener`, `registerCabinLightState`, `registerFrontFogLightState`, `registerHighBeamLightState`, `registerLowBeamLightState`, `registerReadingLightState`, `registerRearFogLightState`, `registerTailLampLightState`, `setAmbientColorAutoByDriveModeEnabled`, `setAmbientLightBrightness`, `setAmbientLightBrightnessColor`, `setAmbientLightSwitch`, `setCabinLightSwitch`, `setFrontFogLightSwitch`, `setHighBeamLightSwitch`, `setLowBeamLightSwitch`, `setReadingLightSwitch`, `setRearFogLightSwitch`, `setTailLampLightSwitch`, `unregisterAmbientColorAutoByDriveModeEnabled`, `unregisterAmbientLightBrightnessColorListener`, `unregisterAmbientLightBrightnessListener`, `unregisterAmbientLightStateListener`, `unregisterCabinLightState`, `unregisterFrontFogLightState`, `unregisterHighBeamLightState`, `unregisterLowBeamLightState`, `unregisterReadingLightState`, `unregisterRearFogLightState`, `unregisterTailLampLightState`
- **Odometer**: `checkCurrentDistanceDrivenCapabilities`, `checkTripCumulativeDrivingDistanceCapabilities`, `checkTripCumulativeDrivingTimeCapabilities`, `checkTripCurrentDrivingDistanceCapabilities`, `checkTripCurrentDrivingTimeCapabilities`, `checkTripDrivingDistanceCapabilities`, `checkTripDrivingTimeCapabilities`, `checkTripInstantaneousDrivingDistanceCapabilities`, `checkTripSinceRefillDrivingDistanceCapabilities`, `checkTripSinceRefillDrivingTimeCapabilities`, `getCurrentDistanceDriven`, `getTripCumulativeDrivingDistance`, `getTripCumulativeDrivingTime`, `getTripCurrentDrivingDistance`, `getTripCurrentDrivingTime`, `getTripInstantaneousDrivingDistance`, `getTripSinceRefillDrivingDistance`, `getTripSinceRefillDrivingTime`, `registerDistanceDriven`, `registerTripCumulativeDrivingDistance`, `registerTripCumulativeDrivingTime`, `registerTripCurrentDrivingDistance`, `registerTripCurrentDrivingTime`, `registerTripInstantaneousDrivingDistance`, `registerTripSinceRefillDrivingDistance`, `registerTripSinceRefillDrivingTime`, `setRangeRemainingDistance`, `unregisterDistanceDriven`, `unregisterTripCumulativeDrivingDistance`, `unregisterTripCumulativeDrivingTime`, `unregisterTripCurrentDrivingDistance`, `unregisterTripCurrentDrivingTime`, `unregisterTripInstantaneousDrivingDistance`, `unregisterTripSinceRefillDrivingDistance`, `unregisterTripSinceRefillDrivingTime`
- **Safety**: `checkAirbagCapabilities`, `checkAirbagDeployedCapabilities`, `checkBrakeFluidLevelStateCapabilities`, `checkEngineOilLevelStateCapabilities`, `checkLowWasherFluidWarningStateCapabilities`, `checkPowerDownWarningIndicatorCapabilities`, `checkSeatBuckledCapabilities`, `checkSeatNotBuckledWarningCapabilities`, `checkTelltalesCapabilities`, `checkTransmissionOilLevelStateCapabilities`, `getBrakeFluidLevelState`, `getEngineOilLevelState`, `getTelltalesInfo`, `getTransmissionOilLevelState`, `hasLowWasherFluidWarning`, `hasPowerDownWarningIndicator`, `hasSeatAirbagBeenDeployed`, `hasSeatAirbagEnabled`, `hasSeatBeltNotBuckledWarning`, `registerBrakeFluidLevelState`, `registerEngineOilLevelState`, `registerLowWasherFluidWaringState`, `registerPowerDownWarningIndicator`, `registerSeatAirbagDeployed`, `registerSeatAirbagEnabled`, `registerSeatBeltNotBuckledWarning`, `registerTelltales`, `registerTransmissionOilLevelState`, `unregisterBrakeFluidLevelState`, `unregisterEngineOilLevelState`, `unregisterLowWasherFluidWaringState`, `unregisterPowerDownWarningIndicator`, `unregisterSeatAirbagDeployed`, `unregisterSeatAirbagEnabled`, `unregisterSeatBeltNotBuckledWarning`, `unregisterTelltales`, `unregisterTransmissionOilLevelState`
- **Seat**: `checkMoveSeatBackrestAngleCapabilities`, `checkMoveSeatBeltHeightCapabilities`, `checkMoveSeatDepthCapabilities`, `checkMoveSeatForeAftCapabilities`, `checkMoveSeatHeadrestAngleCapabilities`, `checkMoveSeatHeadrestForeAftCapabilities`, `checkMoveSeatHeadrestHeightCapabilities`, `checkMoveSeatHeightCapabilities`, `checkMoveSeatLumbarForeAftCapabilities`, `checkMoveSeatLumbarSideSupportCapabilities`, `checkMoveSeatTiltCapabilities`, `checkSeatBackrestAngleCapabilities`, `checkSeatBackrestAnglePositionCapabilities`, `checkSeatBeltBuckledCapabilities`, `checkSeatBeltHeightCapabilities`, `checkSeatBeltHeightPositionCapabilities`, `checkSeatDepthCapabilities`, `checkSeatDepthPositionCapabilities`, `checkSeatForeAftCapabilities`, `checkSeatForeAftPositionCapabilities`, `checkSeatHeadrestAngleCapabilities`, `checkSeatHeadrestAnglePositionCapabilities`, `checkSeatHeadrestForeAftCapabilities`, `checkSeatHeadrestForeAftPositionCapabilities`, `checkSeatHeadrestHeightCapabilities`, `checkSeatHeadrestHeightPositionCapabilities`, `checkSeatHeightCapabilities`, `checkSeatHeightPositionCapabilities`, `checkSeatLumbarForeAftCapabilities`, `checkSeatLumbarForeAftPositionCapabilities`, `checkSeatLumbarSideSupportCapabilities`, `checkSeatLumbarSideSupportPositionCapabilities`, `checkSeatMemoryCapabilities`, `checkSeatOccupancyCapabilities`, `checkSeatTemperatureCapabilities`, `checkSeatTiltCapabilities`, `checkSeatTiltPositionCapabilities`, `checkSeatVentilationCapabilities`, `getCurrentSeatBackrestAnglePosition`, `getCurrentSeatBeltBuckledState`, `getCurrentSeatBeltHeightPosition`, `getCurrentSeatDepthPosition`, `getCurrentSeatForeAftPosition`, `getCurrentSeatHeadrestAnglePosition`, `getCurrentSeatHeadrestForeAftPosition`, `getCurrentSeatHeadrestHeightAftPosition`, `getCurrentSeatHeightPosition`, `getCurrentSeatLumbarForeAftPosition`, `getCurrentSeatLumbarSideSupportPosition`, `getCurrentSeatOccupancy`, `getCurrentSeatTiltPosition`, `getHvacSeatTemperature`, `getHvacSeatVentilationState`, `moveSeatBackrestAngle`, `moveSeatBeltHeight`, `moveSeatDepth`, `moveSeatForeAft`, `moveSeatHeadrestAngle`, `moveSeatHeadrestForeAft`, `moveSeatHeadrestHeight`, `moveSeatHeight`, `moveSeatLumbarForeAft`, `moveSeatLumbarSideSupport`, `moveSeatTilt`, `registerHvacSeatTemperature`, `registerHvacSeatVentilation`, `registerSeatBackrestAnglePosition`, `registerSeatBeltBuckledState`, `registerSeatBeltHeightPosition`, `registerSeatDepthPosition`, `registerSeatForeAftPosition`, `registerSeatHeadrestAnglePosition`, `registerSeatHeadrestForeAftPosition`, `registerSeatHeadrestHeightAftPosition`, `registerSeatHeightPosition`, `registerSeatLumbarForeAftPosition`, `registerSeatLumbarSideSupportPosition`, `registerSeatOccupancy`, `registerSeatTiltPosition`, `selectSeatMemory`, `setHvacSeatTemperature`, `setHvacSeatVentilation`, `unregisterHvacSeatTemperature`, `unregisterHvacSeatVentilation`, `unregisterSeatBackrestAnglePosition`, `unregisterSeatBeltBuckledState`, `unregisterSeatBeltHeightPosition`, `unregisterSeatDepthPosition`, `unregisterSeatForeAftPosition`, `unregisterSeatHeadrestAnglePosition`, `unregisterSeatHeadrestForeAftPosition`, `unregisterSeatHeadrestHeightAftPosition`, `unregisterSeatHeightPosition`, `unregisterSeatLumbarForeAftPosition`, `unregisterSeatLumbarSideSupportPosition`, `unregisterSeatOccupancy`, `unregisterSeatTiltPosition`
- **SideMirror**: `checkHvacSideMirrorHeatCapabilities`, `getHvacSideMirrorHeatState`, `registerHvacSideMirrorHeatState`, `setHvacSideMirrorHeat`, `unregisterHvacSideMirrorHeatState`
- **SteeringWheel**: `checkHvacSteeringWheelHeatCapabilities`, `checkSteeringWheelAngleCapabilities`, `getCurrentSteeringWheelAngle`, `getHvacSteeringWheelHeatState`, `registerHvacSteeringWheelHeatState`, `registerSteeringWheelAngle`, `setHvacSteeringWheelHeat`, `unregisterHvacSteeringWheelHeatState`, `unregisterSteeringWheelAngle`
- **Tire**: `checkLowTirePressureCapabilities`, `checkWheelAreaCapabilities`, `getLowTirePressure`
- **Toll**: `checkEtcsVoiceGuidanceVolumeCapabilities`, `getCurrentEtcsVoiceGuidanceVolume`, `registerEtcsVoiceGuidanceVolume`, `setEtcsVoiceGuidanceVolume`, `unregisterEtcsVoiceGuidanceVolume`
- **TurnSignal**: `checkTurnSignalStateCapabilities`, `getCurrentTurnSignalState`, `registerTurnSignalState`, `unregisterTurnSignalState`
- **Window**: `checkMoveWindowBlindCapabilities`, `checkSmartGlassLevelCapabilities`, `checkSmartGlassStateCapabilities`, `checkSmartGlassZoneStateCapabilities`, `checkWindowBlindPositionCapabilities`, `checkWindowLockCapabilities`, `checkWindowPositionCapabilities`, `getCurrentSmartGlassLevel`, `getCurrentSmartGlassState`, `getCurrentWindowBlindPosition`, `getCurrentWindowLockState`, `getCurrentWindowPosition`, `getSmartGlassConfig`, `moveWindowBlind`, `registerSmartGlassLevel`, `registerSmartGlassState`, `registerWindowBlindPosition`, `registerWindowLockState`, `registerWindowPosition`, `setSmartGlassLevel`, `setSmartGlassState`, `setSmartGlassZoneState`, `setWindowLock`, `setWindowPosition`, `unregisterSmartGlassLevel`, `unregisterSmartGlassState`, `unregisterWindowBlindPosition`, `unregisterWindowLockState`, `unregisterWindowPosition`
- **Wiper**: `checkWiperStateCapabilities`, `checkWiperSwitchCapabilities`, `getCurrentWiperState`, `getCurrentWiperSwitch`, `registerWiperState`, `setWiperSwitch`, `unregisterWiperState`

### `ai.pleos.playground.vehicle.callback.exception`

- **VehicleException**: `VehicleException`, `errorCode`, `message`

### `ai.pleos.playground.vehicle.constant.area`

- **AreaValue**: `id`
- **DoorValue**: `HOOD`, `REAR`, `ROW_1_LEFT`, `ROW_1_RIGHT`, `ROW_2_LEFT`, `ROW_2_RIGHT`, `ROW_3_LEFT`, `ROW_3_RIGHT`, `entries`, `id`, `valueOf`, `values`
- **MirrorValue**: `DRIVER_CENTER`, `DRIVER_LEFT`, `DRIVER_RIGHT`, `entries`, `id`, `valueOf`, `values`
- **SeatValue**: `ROW_1_CENTER`, `ROW_1_LEFT`, `ROW_1_RIGHT`, `ROW_2_CENTER`, `ROW_2_LEFT`, `ROW_2_RIGHT`, `ROW_3_CENTER`, `ROW_3_LEFT`, `ROW_3_RIGHT`, `entries`, `id`, `valueOf`, `values`
- **TurnSignalValue**: `LEFT`, `OFF`, `RIGHT`, `entries`, `id`, `valueOf`, `values`
- **WheelValue**: `LEFT_FRONT`, `LEFT_REAR`, `RIGHT_FRONT`, `RIGHT_REAR`, `UNKNOWN`, `entries`, `id`, `valueOf`, `values`
- **WindowValue**: `FRONT_WINDSHIELD`, `REAR_WINDSHIELD`, `ROOF_TOP_1`, `ROOF_TOP_2`, `ROW_1_LEFT`, `ROW_1_RIGHT`, `ROW_2_LEFT`, `ROW_2_RIGHT`, `entries`, `id`, `valueOf`, `values`

### `ai.pleos.playground.vehicle.constant.control`

- **CruiseControl**: `ACTIVATED`, `ENABLED`, `FORCED_DEACTIVATION_WARNING`, `OTHER`, `SUSPENDED`, `USER_OVERRIDE`, `entries`, `valueOf`, `values`
- **DrivingModeType**: `ECO`, `NORMAL`, `SPORT`, `entries`, `valueOf`, `values`
- **EvChargeState**: `CHARGING`, `ERROR`, `FULLY_CHARGED`, `NOT_CHARGING`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **EvConnectorType**: `GBT_AC`, `GBT_DC`, `IEC_TYPE_1_AC`, `IEC_TYPE_1_CCS_DC`, `IEC_TYPE_2_AC`, `IEC_TYPE_2_CCS_DC`, `IEC_TYPE_3_AC`, `IEC_TYPE_4_DC`, `OTHER`, `TESLA_HPWC`, `TESLA_ROADSTER`, `TESLA_SUPERCHARGER`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **EvRegenerativeBrakingState**: `DISABLED`, `FULLY_ENABLED`, `PARTIALLY_ENABLED`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **FanDirectionType**: `DEFROST`, `DEFROST_AND_FLOOR`, `FACE`, `FACE_AND_DEFROST`, `FACE_AND_FLOOR`, `FACE_AND_FLOOR_AND_DEFROST`, `FLOOR`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **FuelType**: `BIODIESEL`, `CNG`, `DIESEL_1`, `DIESEL_2`, `E85`, `ELECTRIC`, `HYDROGEN`, `LEADED`, `LNG`, `LPG`, `OTHER`, `UNKNOWN`, `UNLEADED`, `entries`, `valueOf`, `values`
- **GsrComplianceType**: `GSR_COMPLIANCE_TYPE_NOT_REQUIRED`, `GSR_COMPLIANCE_TYPE_REQUIRED_V1`, `entries`, `valueOf`, `values`
- **LightState**: `DAYTIME_RUNNING`, `OFF`, `ON`, `entries`, `valueOf`, `values`
- **LightSwitch**: `AUTOMATIC`, `DAYTIME_RUNNING`, `OFF`, `ON`, `entries`, `valueOf`, `values`
- **MoveControl**: `BACKWARD`, `FORWARD`, `STOP`, `entries`, `valueOf`, `values`
- **OilLevelState**: `CRITICALLY_LOW`, `ERROR`, `HIGH`, `LOW`, `NORMAL`, `entries`, `valueOf`, `values`
- **PortLocationType**: `FRONT`, `FRONT_LEFT`, `FRONT_RIGHT`, `REAR`, `REAR_LEFT`, `REAR_RIGHT`, `UNKNOWN`, `entries`, `valueOf`, `values`
- **SmartGlassZoneCommand**: `OFF`, `ON`, `SKIP`, `entries`, `valueOf`, `values`
- **SteeringControl**: `ACTIVE`, `OFF`, `READY`, `entries`, `valueOf`, `values`
- **TripStatisticType**: `CUMULATIVE`, `CURRENT`, `Companion`, `Companion.findById`, `INSTANTANEOUS`, `SINCE_REFILL`, `entries`, `id`, `valueOf`, `values`
- **WiperState**: `OFF`, `ON`, `OTHER`, `SERVICE`, `entries`, `valueOf`, `values`
- **WiperSwitch**: `AUTO`, `CONTINUOUS_LEVEL_1`, `CONTINUOUS_LEVEL_2`, `CONTINUOUS_LEVEL_3`, `CONTINUOUS_LEVEL_4`, `CONTINUOUS_LEVEL_5`, `INTERMITTENT_LEVEL_1`, `INTERMITTENT_LEVEL_2`, `INTERMITTENT_LEVEL_3`, `INTERMITTENT_LEVEL_4`, `INTERMITTENT_LEVEL_5`, `MIST`, `OFF`, `OTHER`, `SERVICE`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.vehicle.constant.telltale`

- **TelltaleColor**: `BLUE`, `GRAY`, `GREEN`, `RED`, `WHITE`, `YELLOW`, `entries`, `valueOf`, `values`
- **TelltaleState**: `BLINK`, `OFF`, `ON`, `entries`, `valueOf`, `values`
- **TelltaleType**: `ABS`, `ACCEL_RESPONSE_ACCEL`, `ACCEL_RESPONSE_DASH`, `AFS`, `AIRBAG`, `AUTO_HIGH_BEAM`, `AUTO_HOLD`, `AWD_LOCK`, `CHILD_PRESENCE_DETECTION_OFF`, `CREEP_OFF`, `DBC`, `DOOR_OPEN`, `DRIFT_MODE`, `DRIVER_ATTENTION`, `DRIVE_MODE`, `DRIVE_MODE_COMFORT`, `DRIVE_MODE_ECO`, `DRIVE_MODE_SPORT`, `DRIVE_MODE_TERRAIN`, `ELECTRONIC_PARKING_BRAKE`, `ELK`, `EMERGENCY_CALL`, `ENGINE`, `ENGINE_OIL_PRESSURE`, `ENGINE_TEMPERATURE`, `EREV_MODE`, `EREV_SYSTEM`, `ESC`, `ESC_OFF`, `ESC_SPORT`, `EV_BATTERY_CONDITIONING`, `EV_CHARGING_CONNECTOR`, `EV_SYSTEM`, `FCA`, `FCEV_SYSTEM`, `FORCED_ALL_WHEEL_DRIVE`, `FOUR_WHEEL_DRIVE_MODE_2H`, `FOUR_WHEEL_DRIVE_MODE_4A`, `FOUR_WHEEL_DRIVE_MODE_4H`, `FOUR_WHEEL_DRIVE_MODE_4L`, `FRONT_FOG_LAMP`, `GREEN_ZONE`, `H2_LEAK`, `H2_SENSOR`, `HEADLAMP_HIGH`, `HEADLAMP_LOW`, `HEV_MODE`, `HEV_SYSTEM`, `ICC`, `ICY_ROAD`, `IMMOBILIZER`, `ISG`, `I_PEDAL`, `LAUNCH_MODE`, `LCA_LEFT`, `LCA_RIGHT`, `LED_HEADLAMP`, `LFA`, `LKA`, `LOW_EV_BATTERY`, `LOW_FUEL`, `LOW_FUEL_FCEV`, `LOW_VOLT_BATTERY`, `LPI`, `MASTER_WARNING`, `MDPS`, `N_BATTERY_DRAG`, `N_BATTERY_ENDURANCE`, `N_BATTERY_SPRINT`, `OVER_SPEED`, `PARKING_BRAKE`, `PARTICLE_FILTER`, `PASSENGER_AIRBAG_OFF`, `PASSENGER_AIRBAG_ON`, `PHEV_MODE`, `PHEV_MODE_AUTO`, `PHEV_MODE_EV_PLUS`, `PHEV_MODE_HEV`, `POSITION_LAMP`, `POWER_DOWN`, `PREHEAT`, `RBS`, `REAR_FOG_LAMP`, `REV`, `SCR`, `SEAT_BELT`, `TOW_MODE`, `TPMS`, `TRACK_MODE`, `TRUNK_OPEN`, `TURN_SIGNAL_LEFT`, `TURN_SIGNAL_RIGHT`, `UTILITY_MODE`, `WATER_SEPARATOR`, `XEV_READY`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.vehicle.constant.unit`

- **DistanceDisplayUnit**: `KILOMETER`, `MILE`, `entries`, `valueOf`, `values`
- **EvBatteryDisplayUnit**: `AMPERE_HOURS`, `KILOWATT_HOUR`, `WATT_HOUR`, `entries`, `valueOf`, `values`
- **SpeedDisplayUnit**: `KILOMETERS_PER_HOUR`, `METER_PER_SEC`, `MILES_PER_HOUR`, `entries`, `valueOf`, `values`
- **TemperatureUnit**: `CELSIUS`, `FAHRENHEIT`, `entries`, `valueOf`, `values`

### `ai.pleos.playground.vehicle.constant`

- **ErrorCode**: `Companion`, `Companion.findById`, `ERROR_DRIVING_RESTRICTED`, `ERROR_EMPTY_RESULT`, `ERROR_INTERNAL_SERVICE`, `ERROR_NOT_SUPPORTED_AREA`, `ERROR_NOT_SUPPORTED_PROPERTY`, `ERROR_NOT_SUPPORTED_VALUE_TYPE`, `ERROR_SECURITY_PERMISSION`, `ERROR_SERVICE_CONNECTION_FAILURE`, `ERROR_UNDEFINED`, `entries`, `id`, `valueOf`, `values`
- **PleosPermission**: `CAR_CLIMATE`, `CAR_DOORS`, `CAR_DRIVE`, `CAR_ENERGY`, `CAR_INFO`, `CAR_LIGHTS`, `CAR_MIRRORS`, `CAR_SAFETY`, `CAR_SEATS`, `CAR_TOLL`, `CAR_WINDOWS`, `CONTROL_CAR_CLIMATE`, `CONTROL_CAR_DOORS`, `CONTROL_CAR_DRIVE`, `CONTROL_CAR_ENERGY`, `CONTROL_CAR_INFO`, `CONTROL_CAR_LIGHTS`, `CONTROL_CAR_MIRRORS`, `CONTROL_CAR_SAFETY`, `CONTROL_CAR_SEATS`, `CONTROL_CAR_TOLL`, `CONTROL_CAR_WINDOWS`, `PRIVATE_CAR_CLIMATE`, `PRIVATE_CAR_DOORS`, `PRIVATE_CAR_DRIVE`, `PRIVATE_CAR_ENERGY`, `PRIVATE_CAR_INFO`, `PRIVATE_CAR_LIGHTS`, `PRIVATE_CAR_MIRRORS`, `PRIVATE_CAR_SAFETY`, `PRIVATE_CAR_SEATS`, `PRIVATE_CAR_TOLL`, `PRIVATE_CAR_WINDOWS`

### `ai.pleos.playground.vehicle.listener`

- **AmbientColorAutoByDriveModeEnabledListener**: `onAmbientColorAutoByDriveModeEnabledUpdated`
- **AmbientLightBrightnessColorListener**: `onBrightnessColorUpdated`
- **AmbientLightBrightnessListener**: `onBrightnessUpdated`
- **BrakePedalStatusListener**: `onBrakePedalStatusUpdated`
- **CruiseControlStateListener**: `onCruiseControlStateUpdated`
- **CruiseControlTargetSpeedListener**: `onCruiseControlTargetSpeedUpdated`
- **DistanceDrivenListener**: `onDistanceDrivenUpdated`
- **DoorLockStateListener**: `onDoorLockStateUpdated`
- **DoorPositionListener**: `onPositionUpdated`
- **DrivingModeStateListener**: `onDrivingModeStateUpdated`
- **DrivingTimeListener**: `onDrivingTimeUpdated`
- **EtcsVoiceGuidanceVolumeListener**: `onEtcsVoiceGuidanceVolumeUpdated`
- **EvBatteryEfficiencyListener**: `onEvBatteryEfficiencyUpdated`
- **EvBatteryStateOfChargeListener**: `onEvBatteryStateOfChargeUpdated`
- **FuelEconomyListener**: `onFuelEconomyUpdated`
- **FuelPercentListener**: `onFuelPercentUpdated`
- **GloveBoxLockStateListener**: `onGloveBoxLockStateUpdated`
- **HvacAcStateListener**: `onHvacAcStateUpdated`
- **HvacAirCleanerStateListener**: `onHvacAirCleanerStateUpdated`
- **HvacAutoStateListener**: `onHvacAutoStateUpdated`
- **HvacDefrosterStateListener**: `onHvacDefrosterStateUpdated`
- **HvacDualStateListener**: `onHvacDualStateUpdated`
- **HvacFanSpeedListener**: `onHvacFanSpeedUpdated`
- **HvacMaxAcStateListener**: `onHvacMaxAcStateUpdated`
- **HvacMaxDefrostStateListener**: `onHvacMaxDefrostStateUpdated`
- **HvacPowerStateListener**: `onHvacPowerStateUpdated`
- **HvacRecirculationStateListener**: `onHvacAutoRecirculationStateUpdated`
- **HvacSeatTemperatureListener**: `onHvacSeatTemperatureUpdated`
- **HvacSeatVentilationListener**: `onHvacSeatVentilationUpdated`
- **HvacSideMirrorHeatStateListener**: `onHvacSideMirrorHeatStateUpdated`
- **HvacSteeringWheelHeatStateListener**: `onHvacSteeringWheelHeatStateUpdated`
- **HvacTemperatureListener**: `onHvacTemperatureUpdated`
- **HvacTemperatureValueListener**: `onHvacTemperatureUpdated`
- **LightStateListener**: `onLightStateUpdated`
- **LowWasherFluidWaringListener**: `onLowWasherFluidWaringUpdated`
- **OilLevelStateListener**: `onOilLevelUpdated`
- **PowerDownWarningIndicatorListener**: `onPowerDownWarningIndicatorUpdated`
- **SeatAirbagDeployedListener**: `onSeatAirbagDeployUpdated`
- **SeatAirbagEnabledListener**: `onSeatAirbagEnabledUpdated`
- **SeatBackrestAnglePositionListener**: `onSeatBackrestAnglePositionUpdated`
- **SeatBeltBuckledStateListener**: `onSeatBeltBuckledStateUpdated`
- **SeatBeltHeightPositionListener**: `onSeatBeltHeightPositionUpdated`
- **SeatBeltNotBuckledWarningListener**: `onSeatBeltNotBuckledWarningUpdated`
- **SeatDepthPositionListener**: `onSeatDepthPositionUpdated`
- **SeatForeAftPositionListener**: `onSeatForeAftPositionUpdated`
- **SeatHeadrestAnglePositionListener**: `onSeatHeadrestAnglePositionUpdated`
- **SeatHeadrestForeAftPositionListener**: `onSeatHeadrestForeAftPositionUpdated`
- **SeatHeadrestHeightAftPositionListener**: `onSeatHeadrestHeightAftPositionUpdated`
- **SeatHeightPositionListener**: `onSeatHeightPositionUpdated`
- **SeatLumbarForeAftPositionListener**: `onSeatLumbarForeAftPositionUpdated`
- **SeatLumbarSideSupportPositionListener**: `onSeatLumbarSideSupportPositionUpdated`
- **SeatOccupancyListener**: `onSeatOccupancyUpdated`
- **SeatTiltPositionListener**: `onSeatTiltPositionUpdated`
- **SmartGlassLevelListener**: `onSmartGlassLevelUpdated`
- **SmartGlassStateListener**: `onSmartGlassStateUpdated`
- **SteeringWheelAngleListener**: `onSteeringWheelAngleUpdated`
- **TelltaleInfoListener**: `onTelltaleInfoUpdated`
- **TemperatureDisplayUnitListener**: `onTemperatureDisplayUnitUpdated`
- **TripDrivingDistanceListener**: `onTripDrivingDistanceUpdated`
- **TripDrivingTimeListener**: `onTripDrivingTimeUpdated`
- **TripEvEfficiencyListener**: `onTripEvEfficiencyUpdated`
- **TripFuelEconomyListener**: `onTripFuelEconomyUpdated`
- **TurnSignalStateListener**: `onTurnSignalStateUpdated`
- **VehicleListener**: `onFailed`
- **WindowLockStateListener**: `onWindowLockStateUpdated`
- **WindowPositionListener**: `onWindowPositionUpdated`
- **WiperStateListener**: `onWiperStateUpdated`

### `ai.pleos.playground.vehicle`

- **Vehicle**: `Vehicle`, `getBrake`, `getCarInfo`, `getDisplay`, `getDoor`, `getDrivingMode`, `getEvBattery`, `getFuel`, `getHVAC`, `getLight`, `getOdometer`, `getSafety`, `getSeat`, `getSideMirror`, `getSteeringWheel`, `getTire`, `getToll`, `getTurnSignal`, `getWindow`, `getWiper`, `initialize`, `release`
