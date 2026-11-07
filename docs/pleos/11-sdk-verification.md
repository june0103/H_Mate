# 11. SDK 실측 검증 결과 (문서 vs 실제 SDK)

docs/pleos 문서대로 SDK 5종을 불러와 Pleos Connect Emulator에서 실행하고, 실제 AAR의 공개 API와도 대조한 결과입니다. **이 파일의 내용이 01~10 문서와 충돌하면 이 파일을 우선합니다.**

- 검증일: 2026-11-05
- 에뮬레이터: Pleos Connect Emulator v2.1.0 (`Pleos/crp_avd/crp_avd_x86_64:14/RELEASE.release_connect_v2.1.0`, API 34)
- SDK: Vehicle 2.3.2, NaviHelper 3.0.5, TextToSpeech 3.3.1, SpeechToText 3.3.1, FusedLocation 1.1.3
  - Playground "Resources & Emulator" 페이지의 공식 SDK 목록과 같은 5종입니다. LLM SDK는 목록에 없습니다.
- 검증 코드: [examples/sdkcheck/](examples/README.md) 모듈의 `SdkCheckActivity.kt`. 문서 예제를 옮겨 호출하고 logcat 태그 `PLEOSCHECK`로 결과를 남깁니다.
- 정적 비교: 각 AAR의 `classes.jar`를 `javap -public`으로 조회한 결과와 `api/*.md`를 클래스·멤버 이름 단위로 대조했습니다.

## 1. 동작 확인 결과 (에뮬레이터)

| SDK | 호출 | 결과 |
|---|---|---|
| Vehicle | `Vehicle(context)` → `initialize()` → `getDoor()` | 정상. `initialize()` 직후 바로 API를 호출해도 결과가 옴 |
| Vehicle | `checkDoorPositionCapabilities` | `READ_WRITE`, 지원 영역 6개, `ROW_1_LEFT` 범위 0..1 |
| Vehicle | `getCurrentDoorPosition(ROW_1_LEFT)` / `getDisplay().getCurrentTemperatureDisplayUnit` | `0` / `CELSIUS` |
| Vehicle | `registerDoorPosition(area, listener)` | 등록 즉시 현재값(`0`)으로 1회 콜백 |
| Vehicle | 참고: Set API 시그니처 | `setDoorPosition(area, value, onComplete = { _, _ -> }, onFailed = {})`처럼 `onComplete`·`onFailed` 모두 기본값이 있는 선택 인자. 콜백 전체는 [12-sdk-callbacks.md](12-sdk-callbacks.md) |
| Vehicle | `setDoorPosition(ROW_1_LEFT, 1)` | `onFailed`는 오지 않았지만 3초 뒤 Get 값이 여전히 `0`. 에뮬레이터 VHAL이 반영하지 않는 것으로 보이며, 실차에서는 확인하지 못함 |
| Vehicle | 미지원 영역 Get(`ROW_3_LEFT`) | `onFailed`로 **일반 `java.lang.Exception`** 수신. 메시지는 `IllegalArgumentException: area ID: 0x100 not supported for property ID: DOOR_POS` |
| NaviHelper | `initialize(onConnected, onFailed)` → `requestRoute` | `onConnected` 후 약 9초 뒤 `onRouteStarted` 수신 |
| NaviHelper | `getDestinationInfo` | 경로 시작 **전**에 부르면 빈 값(위경도 0)이 오고, 시작 **후**에는 정상 값이 옴. 에뮬레이터 내비는 요청 좌표와 다른 모의 좌표를 돌려줌 |
| NaviHelper | `changeRouteOption(HIGHWAY)` / `cancelRoute` | `onRouteOptionChanged`는 오지 않았고 대신 미문서 콜백 `onRouteEnded`가 옴 / `onRouteCancelled` 정상 |
| TextToSpeech | `Mode.ON_DEVICE`, `initialize(listener)` → `addEventListener` → `speak` | `onSuccess` → `onReady` → `onStart` → `onDone` 정상. 앱 등록 없이 동작 |
| SpeechToText | `Mode.ON_DEVICE`, `initialize` → `addListener` + `request` | `onSuccess`, `onReady`까지 확인. 마이크 입력이 없어 인식 결과는 확인하지 못함 |
| FusedLocation | `isSupported` → `initialize` → `registerFusedLocationCallback` / `addProximityAlert` | `isSupported=true`, 약 1초 간격으로 `onChanged`(37.5037, 127.1040, confidence 5.0), `onRegistered(alertId)` 정상 |

## 2. 문서와 다른 점

### 2-1. 문서 예제가 그대로는 컴파일되지 않음 (Vehicle)

| 문서 | 실제 SDK (2.3.2) | 대응 |
|---|---|---|
| 04 「Callback」 예제: `door.registerDoorPosition(doorPositionListener = …)` / `unregisterDoorPosition(doorPositionListener = …)` | `registerDoorPosition(area: DoorArea, listener: DoorPositionListener)`. 영역 인자가 필수이고 파라미터 이름은 `listener` | `door.registerDoorPosition(DoorArea(DoorValue.ROW_1_LEFT), listener)`처럼 호출. `api/vehicle.md`의 시그니처는 실제와 같음 |
| 04 「Capability」·「Error code」 예제: `if (e is VehicleException) { e.errorCode }` | `VehicleException` 클래스가 AAR에 공개되어 있지 않음. 난독화되어 `ai.pleos.playground.vehicle.callback.exception.a`로 들어 있음. **컴파일 오류** | `is VehicleException` 분기를 쓸 수 없음. 아래 2-2처럼 `Exception`의 타입과 `message`로 처리 |
| `ErrorCode`는 `ai.pleos.playground.vehicle.constant` 패키지 | 실제로는 `vehicle-data` 아티팩트에 있고, Vehicle POM에서 `runtime` 스코프라 **컴파일 클래스패스에 없음** | 꼭 써야 하면 `implementation("ai.pleos.playground:vehicle-data:1.1.11")`을 직접 추가해야 함(이 방법은 미검증). 2-2 때문에 실익도 작음 |

### 2-1b. 문서 예제가 그대로는 컴파일되지 않음 (NaviHelper)

| 문서 | 실제 SDK (3.0.5) | 대응 |
|---|---|---|
| 05 「Waypoints」 Kotlin 예제: `override fun onWaypointInfo(waypointInfo: List<WaypointInfo>)` | 메서드 이름이 `onWayPointInfo`(대문자 P). `onWayPointArrived`도 같은 표기이고, `onWaypointChanged`만 소문자 p | `override fun onWayPointInfo(…)`로 작성. Java 탭 예제와 이벤트 표는 맞게 적혀 있음 |

### 2-2. 오류 전달 방식 (Vehicle)

문서는 `onFailed`의 오류가 `VehicleException`과 `ErrorCode`로 매핑된다고 설명합니다. 실측 결과는 다릅니다.

- 미지원 영역: `java.lang.Exception`이 오고, 원인은 `message`에 문자열로만 담김(`IllegalArgumentException: area ID … not supported …`)
- 권한 없음: `java.lang.SecurityException`이 옴(`message`: `"<메서드명> need Permission"`). `ERROR_SECURITY_PERMISSION` 같은 코드는 없음
- 그러니 `onFailed`에서는 `SecurityException`인지 아닌지, 그리고 `message`로 분기하는 것이 현실적입니다.

### 2-3. AAR에 있지만 문서에 없는 공개 API

사이트 API Reference(`api/*.md`)와 사용 가이드 어디에도 없는 API입니다. 에뮬레이터 결과도 함께 적었습니다.

| SDK | API | 실측 |
|---|---|---|
| Vehicle | `Vehicle.getGear(): Gear`<br>`Gear.getCurrentGearSelectionState(onComplete: (VehicleGear?) -> Unit, onFailed)`<br>`Gear.registerGearSelection(GearSelectionListener)` / `unregisterGearSelection`<br>`GearSelectionListener.onSelected(VehicleGear?)`<br>`VehicleGear`: `GEAR_UNKNOWN, GEAR_NEUTRAL, GEAR_REVERSE, GEAR_PARK, GEAR_DRIVE, GEAR_1..GEAR_9` | `CAR_DRIVE` 권한으로 `GEAR_PARK` 수신 |
| Vehicle | `EvBattery.getEvBatteryLevel` / `getEvBatteryCapacity`(`Float?`), `isEvChargePortOpen`(`Boolean?`)<br>`registerEvBatteryLevel(EvBatteryLevelListener)` · `onEvBatteryLevelUpdated(Float?)`<br>`registerEvChargePortOpen(EvChargePortOpenListener)` · `onEvChargePortOpenUpdated(Boolean?)` | `CAR_ENERGY` 권한으로 level과 capacity 모두 `77400.0` 수신 |
| Vehicle | `Odometer.getRangeRemainingDistance`(`Float?`)<br>`registerRangeRemainingListener(RangeRemainingListener)` · `onRangeRemainingChanged(Float?)` | `CAR_ENERGY` 권한으로 `500000.0` 수신(단위 미확인, m로 추정) |
| Vehicle | `Tire.checkTirePressureCapabilities`, `getTirePressure(WheelArea, …)`, `registerTirePressure(WheelArea, TirePressureListener)` | `CAR_SAFETY` 권한으로도 `SecurityException`. 일반 앱이 받기 어려운 `PRIVATE_CAR_*` 권한이 필요한 것으로 보임 |
| Vehicle | `CarInfo.getCapabilityInfo(onComplete: (Map<String, CapabilityConfig<Any>>) -> Unit, onFailed)` | 호출하지 않음 |
| NaviHelper | `NaviHelperEventListener.onRouteEnded()` | `changeRouteOption` 직후 수신 |
| TTS / STT | `TextToSpeech.getAmbientAi()`, `SpeechToText.getAmbientAi()` / `getCallApi()` | 호출하지 않음. `PrivilegedApi`·`ApiChecker` 클래스가 함께 있어 특권 앱 전용으로 보임 |
| FusedLocation | `exception.LocationException`, `constant.ErrorCode`, `constant.PleosPermission` | 호출하지 않음. 오류 처리용 타입으로 보임 |

- 권한 상수(`vehicle.constant.PleosPermission`)에는 `CAR_*`, `CONTROL_CAR_*` 말고도 `PRIVATE_CAR_*` 11종이 정의되어 있습니다.
- 그 밖의 `*Impl`, `Vehicle*Value`, `PleosPropertyIds`, `factory`, `eventbus`, `utils` 같은 공개 클래스는 내부 구현으로 보여 목록에서 뺐습니다.

### 2-4. 문서에 있으나 확인하지 못한 동작
- NaviHelper `onRouteOptionChanged`: 에뮬레이터에서는 오지 않았습니다(`onRouteEnded`가 대신 옴).
- NaviHelper `onRoutingCompleted`: 에뮬레이터에서 오지 않았습니다.
- TTS/STT 서버 모드(`SERVER`, `HYBRID`)와 `registerApp`: Client ID/Secret이 없어 확인하지 못했습니다.
- STT 인식 결과(`onUpdated`): 마이크 입력이 없어 확인하지 못했습니다. `sendAudio`에 PCM을 넣으면 확인할 수 있습니다.

## 3. 일치한 것
- 5종 모두 Importing 페이지의 Maven 좌표·버전과 저장소 URL로 의존성이 받아집니다.
- 문서의 권한 이름(`CAR_DOORS`, `CONTROL_CAR_DOORS`, `CAR_INFO`, `NAVI_ROUTE`, `NAVI_ROUTE_SEARCH`, `TTS_SERVICE`, `STT_SERVICE`, `FUSED_LOCATION`)은 `install -g`로 모두 부여되고 실제로 동작합니다.
- 06·07·05 예제 코드(초기화, 리스너, `RouteInfo`, `ProximityAlertRequest`, `Mode`, `ErrorType` import 경로)는 수정 없이 컴파일됩니다.
- TTS 문서의 호출 순서(`initialize()` → `addEventListener()`)대로 해야 합니다. `initialize()`보다 먼저 `addEventListener()`를 부르면 `UninitializedPropertyAccessException`(lateinit `tts`)이 납니다.
- `api/*.md`와 AAR을 이름 단위로 대조했을 때, 문서에 있는데 AAR에 없는 항목은 `VehicleException` 하나뿐입니다. 나머지 차이는 2-3의 미문서 API입니다.

## 4. 다시 검증하는 방법

[examples/README.md](examples/README.md)의 「새 프로젝트에서 돌려 보려면」을 따릅니다. 모듈을 프로젝트에 넣고 빌드·설치·실행한 뒤 logcat 태그 `PLEOSCHECK`를 봅니다.
