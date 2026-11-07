# Pleos Connect 개발 문서 (에이전트 참고용)

Pleos Playground 개발자 가이드(https://document.pleos.ai)의 **앱 기획·설계 가이드**, **앱 개발·연동 가이드** 원문을 모은 로컬 사본입니다. 수집일은 2026-11-05이고, 수집 후 서브에이전트가 페이지마다 원문과 대조해 검토했습니다.

에이전트는 Pleos Connect 앱(AAOS 기반 차량 인포테인먼트 앱)을 설계·구현·연동할 때 이 폴더를 먼저 확인합니다. 각 섹션 머리의 `> 원문:` 링크가 근거입니다. 원문과 다르게 보이거나 문서가 오래됐다고 판단되면 그 링크를 열어 다시 확인하세요.

## 읽는 법 (에이전트용)
- **큰 파일은 통째로 읽지 말고 검색해서 필요한 부분만 읽으세요.** `api/vehicle.md`(약 250 KB), `12-sdk-callbacks.md`(약 120 KB), `api/navihelper.md`·`09-design-guide.md`·`03-app-development-guide.md`(각 70~80 KB)가 특히 큽니다. 클래스·메서드 이름(예: `registerDoorPosition`, `onRouteStarted`)이나 `## ` 제목으로 찾은 뒤 그 주변만 읽습니다.
- 코드를 쓸 때 우선순위: **[11-sdk-verification.md](11-sdk-verification.md)와 [examples/](examples/README.md)의 실측 코드 → `api/`의 시그니처 → 04~07 원문 예제**. 원문 예제에는 그대로는 컴파일되지 않는 코드가 있고, 해당 위치에 `[실측 검증 2026-11-05]` 메모를 달아 두었습니다.
- 표에 **[미문서]**라고 붙은 API는 사이트 문서에는 없고 실제 SDK(AAR)에만 있는 것입니다.
- 아래 「확인되지 않은 것」에 있는 동작은 추측으로 구현하지 말고, 구현 전에 사용자에게 알리거나 에뮬레이터로 확인하세요.

## 작업별로 볼 문서

| 하려는 일 | 먼저 볼 문서 |
|---|---|
| 새 프로젝트 셋업, Maven 저장소·의존성, 에뮬레이터 설치·설정, Virtual Console(차량 신호 시뮬레이션) | [01-getting-started.md](01-getting-started.md) |
| 화면 구성, 주행 중 허용 기능, 앱 카테고리, 성능 한도, 보안 | [02-planning-design-guide.md](02-planning-design-guide.md) |
| 오디오 포커스, 주행 규제(`CarUxRestrictions`), CCP(로터리), 내비·음악 앱 정책, TTS 선행 묵음, IME, ADB | [03-app-development-guide.md](03-app-development-guide.md) |
| Vehicle SDK(차량 상태 조회·제어) | [04-sdk-overview-vehicle.md](04-sdk-overview-vehicle.md) |
| NaviHelper SDK(목적지·경로·경유지·내비 이벤트) | [05-sdk-navihelper.md](05-sdk-navihelper.md) |
| TextToSpeech / SpeechToText SDK | [06-sdk-tts-stt.md](06-sdk-tts-stt.md) |
| FusedLocation SDK(차량 위치) | [07-sdk-fusedlocation.md](07-sdk-fusedlocation.md) |
| Gleo(AI 에이전트) 연동: App Capability, Action Callback, Schema | [08-gleo-interface.md](08-gleo-interface.md) |
| UI 컴포넌트, 레이아웃, 색·타이포·아이콘 | [09-design-guide.md](09-design-guide.md) |
| SDK 클래스·메서드 **시그니처·필요 권한** | [api/](api/) — `vehicle.md`, `navihelper.md`, `texttospeech.md`, `speechtotext.md`, `fusedlocation.md`, `llm.md` |
| SDK 클래스·메서드 이름만 빠르게 찾기 | [10-sdk-api-index.md](10-sdk-api-index.md) |
| **SDK 실행·제어·조회 시 오는 콜백 전체(호출 → 콜백 대응, 리스너, 오류 값)** | [12-sdk-callbacks.md](12-sdk-callbacks.md) |
| **문서와 실제 SDK의 차이, 문서에 없는 API(기어·EV·주행가능거리 등)** | [11-sdk-verification.md](11-sdk-verification.md) — **충돌하면 이 파일 우선** |
| **에뮬레이터에서 실제로 컴파일·동작한 SDK 5종 호출 코드** | [examples/](examples/README.md) — `SdkCheckActivity.kt` |

## 빠른 참조

### 개발 환경 (01)
- `minSdk` **28 이상**(차량 사이버 보안 요건), compile/target 기준 Android API 34, Gradle 8.0 이상, Kotlin·Java 지원
- 에뮬레이터: Pleos Connect Emulator 2.1.0(API 34 system image), AVD는 Cold boot(Quick boot로는 VHAL 사용 불가), RAM 4 GB
- Virtual Console 연결: `adb forward tcp:8088 tcp:8088`
- Maven 저장소(`settings.gradle.kts`):
  ```kotlin
  maven { url = uri("https://nexus-playground.pleos.ai/repository/maven-releases/") }
  ```
- 의존성 좌표(각 SDK의 Importing 페이지 기준. 시작하기 페이지에는 Vehicle `2.3.1`로 적혀 있어 버전이 다름):

  | SDK | 좌표 |
  |---|---|
  | Vehicle | `ai.pleos.playground:Vehicle:2.3.2` |
  | NaviHelper | `ai.pleos.playground:NaviHelper:3.0.5` |
  | TextToSpeech | `ai.pleos.playground:TextToSpeech:3.3.1` |
  | SpeechToText | `ai.pleos.playground:SpeechToText:3.3.1` |
  | FusedLocation | `ai.pleos.playground:FusedLocation:1.1.3` |

- 권한은 `pleos.car.permission.*` 형식입니다(예: `CAR_DOORS`, `CAR_DRIVE`, `NAVI_ROUTE`, `FUSED_LOCATION`, `TTS_SERVICE`, `STT_SERVICE`). 정확한 API별 권한은 04~07의 권한 섹션을 보세요.
- TTS/STT를 `SERVER` 모드나 `HYBRID` 모드의 서버 합성으로 쓰려면 Playground My Project에서 발급한 **Client ID / Client Secret**으로 `registerApp()`을 호출해야 합니다(06).
- SDK 사용 신청 상태: Vehicle State와 NaviHelper는 신청 없이 바로 쓸 수 있습니다. 나머지는 Playground에서 상태를 확인하세요(01).

### 빌드 환경 함정
- 빌드가 `Error: could not open ...\jvm.cfg`처럼 JDK 오류로 실패하면 Android Studio 번들 JDK(`jbr`)가 깨진 것입니다. 정상인 JDK 17 이상을 `JAVA_HOME`으로 지정하고 다시 빌드하세요. 작성 당시 PC에서는 기본 `C:\Program Files\Android\Android Studio\jbr`가 깨져 있었고, 다른 설치본의 `jbr`(JDK 25)로 성공했습니다.
- 검증에 쓴 빌드 구성: AGP 9.3.3(Kotlin 내장이라 `kotlin-android` 플러그인 불필요), compileSdk 37, minSdk 28.
- 에뮬레이터는 Cold boot로 켜야 VHAL(차량 신호)이 동작합니다. 권한은 `adb install -g`로 한꺼번에 부여할 수 있습니다.

### Vehicle SDK 호출 패턴 (04, 11)
`Vehicle(context)` → `initialize()` → `getDoor()` 같은 `getXxx()`로 도메인 객체 획득 → Capability / Get / Set / Callback API 호출 → 다 쓰면 `release()`.
- 결과는 IPC를 거쳐 `onComplete` / `onFailed`로 **비동기** 전달됩니다. 콜백이 끝나기 전에 `release()`를 부르면 안 됩니다.
- 기능을 쓰기 전에 Capability API로 지원 여부와 허용 범위를 확인합니다.
- **실측 주의(11)**: `VehicleException`은 공개 클래스가 아니라 `is VehicleException`이 컴파일되지 않습니다. `onFailed`에는 일반 `Exception`(미지원)이나 `SecurityException`(권한 없음)이 옵니다. Callback 등록은 `registerXxx(area, listener)`처럼 영역 인자가 필요한 경우가 있으니 `api/vehicle.md`의 시그니처를 따르세요.
- 표준 데이터(속도·기어·외기온·연료 등)는 AAOS Car API가, 제조사 특화 기능은 Pleos SDK가 담당하는 것이 원칙입니다(02 「SDK API 정책」).

### 반드시 지킬 정책 (심사·안전)
- **주행 규제**: `CarUxRestrictionsManager.registerListener()`로 `CarUxRestrictions`를 받고 `ActiveRestrictions` 플래그(예: `UX_RESTRICTIONS_NO_VIDEO`, `NO_KEYBOARD`, `NO_SETUP`)를 기능별로 적용합니다. DO(Distraction Optimization)를 선언하지 않은 앱은 주행 중 사용할 수 없습니다. 허용 범위는 지역(북미·유럽·그 외)마다 다릅니다(02, 03).
- **화면 모델**: Driving view 옆 앱 영역에 앱이 최대 2개 뜹니다. `portrait`(window 1개)와 `landscape`(window 2개) 화면을 모두 설계하고 window 크기 변경에 대응합니다. 화면에서 사라진 앱은 백그라운드에 남을 수 있습니다(02).
- **성능 한도**: CPU는 주행 중 내비 앱 15% 이하, 내비가 아닌 앱 5% 미만, 정차 중 모든 앱 25% 이하입니다. RAM은 400 MB 이하입니다(02).
- **오디오 포커스**: 재생 전에 `AudioAttributes`와 `AudioFocusRequest`로 포커스를 요청하고, `LOSS`/`LOSS_TRANSIENT`/`CAN_DUCK` 이벤트를 반드시 처리합니다. 판단은 Pleos Connect Interaction Matrix(E/C/R)를 따릅니다(03).
- **CCP Back 하드키**: 앱 최상단 화면에서 Back이 들어오면 `finish()`하지 말고 `moveTaskToBack(true)`로 숨깁니다. 지키지 않으면 심사에서 반려될 수 있습니다(03).
- **하드웨어 직접 접근 금지**: 플랫폼 API만 씁니다. Telephony, SMS, 일부 센서, 캘린더, 주소록은 제한될 수 있으니 Optional 구조로 설계합니다. 주행 중 네트워크 단절에 대비합니다(02).
- **보안·개인정보**: 토큰은 Keystore에 저장하고, 계정 전환·로그아웃을 쉽게 만들고, 로그 속 PII는 마스킹합니다(02).
- **내비 앱**: 매니페스트에 `android.intent.category.APP_MAPS`를 선언하고 단일 경로 정책을 따릅니다(03).

## 범위와 한계
- 포함한 페이지는 시작하기, 프로젝트 생성·SDK 신청, 에뮬레이터 설치·설정, Virtual Console 셋업, 앱 기획·설계 가이드 10개, 앱 개발 가이드 11개, SDK 사용 가이드(Overview, Vehicle, NaviHelper, TTS, STT, FusedLocation), Gleo Interface 6개, 디자인 가이드 42개입니다. 앱 테스트·등록·튜토리얼·릴리스 노트는 요청 범위 밖이라 넣지 않았습니다.
- **SDK API Reference**: 사이트의 Dokka 페이지는 HTML 본문이 비어 있어서, 페이지 JS 청크에 든 내용을 클래스(타입) 단위로 추출했습니다(`api/`). 멤버별 상세 페이지의 긴 설명은 넣지 않았습니다. 사이트에 없는 API도 있습니다. 예를 들어 Vehicle 2.3.2의 기어 API(`GearSelectionListener`, `VehicleGear`)는 문서에 없습니다. 이렇게 문서에 없는 API는 실측과 함께 [11-sdk-verification.md](11-sdk-verification.md)에 정리했습니다. 그 밖의 API는 Gradle 캐시의 AAR(`~/.gradle/caches/modules-2/files-2.1/ai.pleos.playground/...`)을 `javap`로 확인하세요.
- API Reference의 멤버 설명은 Dokka 목록에 나오는 한 줄 요약뿐입니다(@param 상세 없음). 원문 표기와 실제 SDK가 다른 곳도 있습니다. 예를 들어 원문은 `class DoorArea(val values: DoorValue)`로 적혀 있지만 실제 생성자는 `vararg`입니다.
- **LLM SDK**: 사용 가이드가 사이트 어디에도 없고(사이드바에도 없음) API Reference(`api/llm.md`)만 있습니다. 확인된 메서드는 `initialize`, `registerApp`, `setServerType`, `setModelParameter`, `startChat`, `sendMessage`, `generateContent`, `getOnDeviceModelPromptsContents`, `release`이고, 리스너는 `OnServerConnectionListener`, `ResultListener`, 모드는 `ON_DEVICE`, `SERVER`입니다.
- 이미지로만 전달되는 내용(오디오 Interaction Matrix 표, 디자인 시안 등)은 `[이미지: ...]`처럼 대체 텍스트만 남아 있습니다. 필요하면 원문 링크를 여세요.

## 확인되지 않은 것
문서에도 없고 에뮬레이터로도 확인하지 못한 동작입니다. 이 부분에 기대는 기능은 구현 전에 확인이 필요합니다.
- **NaviHelper 요청 → 결과 콜백 대응**: 결과가 모두 리스너로 와서 API에 짝이 명시돼 있지 않습니다. [12-sdk-callbacks.md](12-sdk-callbacks.md)의 대응은 이름·설명으로 추정한 것이고, 실측한 것은 `requestRoute`·`getDestinationInfo`·`changeRouteOption`·`cancelRoute` 4개뿐입니다. `onRouteOptionChanged`·`onRoutingCompleted`는 받지 못했습니다.
- **STT 인식 결과**: 에뮬레이터에 마이크 입력이 없어 `onReady`까지만 확인했습니다.
- **TTS/STT `SERVER`·`HYBRID` 모드**: Client ID/Secret이 없어 확인하지 못했습니다.
- **Vehicle Set의 실제 반영**: `setDoorPosition`은 오류 없이 끝났지만 에뮬레이터에서 값이 바뀌지 않았습니다.
- **타이어 공기압 API**: `CAR_SAFETY` 권한으로도 `SecurityException`이 납니다. 필요한 권한을 모릅니다.
- **앱이 꺼져 있을 때의 알림·백그라운드 동작**: Pleos 문서에 보장 내용이 없습니다. 디자인 가이드는 광고성 알림을 금지하고, 기획 가이드는 백그라운드 상주·폴링을 최소화하라고만 합니다.
- **LLM SDK, Gleo 연동**: 실행해 보지 않았습니다.

## 다른 프로젝트에서 쓰기
이 폴더(`pleos/`)는 프로젝트와 독립적입니다. 폴더째 새 프로젝트의 `docs/pleos/`로 복사하고, [CLAUDE-snippet.md](CLAUDE-snippet.md)의 내용을 그 프로젝트의 `CLAUDE.md`에 붙이면 에이전트가 이 문서를 먼저 찾습니다. 문서 안의 링크는 모두 이 폴더 기준 상대 경로라 위치를 옮겨도 깨지지 않습니다. 다른 경로에 두면 snippet의 경로만 고치세요.
