# My Car Racing (Pleos Connect)

내 차의 핸들·브레이크·방향지시등으로 하는 간단한 레이싱 게임. **주차(P) 상태에서만** 동작한다.

| 차량 조작 | 게임 동작 | 읽는 API (Pleos Vehicle SDK 2.3.2) |
|---|---|---|
| 핸들 | 조향 (180° = 최대) | `SteeringWheel.registerSteeringWheelAngle` |
| 브레이크 페달 | 감속 / 밟았다 떼면 출발 | `Brake.registerBrakePedalStatus` (눌림 여부만 옴) |
| 방향지시등 오른쪽 / 왼쪽 | 부스트 / 점프(누르고 있으면 호버). 서킷 시작 화면에서는 트랙 선택 | `TurnSignal.registerTurnSignalState` |
| 변속 레버 | P가 아니면 게임 정지 | `Gear.registerGearSelection` |
| 엑셀 | 자동 가속 (엑셀 페달 API가 SDK에 없음) | - |

권한: `pleos.car.permission.CAR_DRIVE`, `pleos.car.permission.CAR_LIGHTS` (App Market 사전 승인 필요)

## 모드
- **하이웨이**: 끝없는 고속도로. 교통 차량과 시속 50km 이상으로 부딪히면 게임 오버.
- **서킷**: 트랙 4개(알파인 링·캐니언 플라이트·하버 런·릿지라인) 중 하나에서 AI 라이벌 3대와 3랩 레이스. JUMP 구간은 점프로 넘는다.

두 모드 모두 [mission-t](https://github.com/thvlou/mission-t)의 하이웨이 엔진(Three.js)을 가져와 차량 입력으로 바꾼 것이다.

## 차량 SDK 시험 화면
- **차량 제어** (`control.html`, `VehicleControl.kt`): 공조 전원·에어컨·온도·풍량, 앰비언트 켜기·색·밝기, 창문 4개 읽기·제어. 창문은 Virtual Console 페이지가 열려 있어야(브라우저 연결 1개 이상) 실제로 움직인다.
- **차곡 데이터** (`chagok.html`, `ChagokBridge.kt`): 피그마 "구현 가능성 재검토 (11/2)" 표의 SDK 항목.

| 항목 | API | 에뮬레이터(전기차) |
|---|---|---|
| 연료 %, 연비(현재·순간·누적) | `Fuel.getFuelPercent`, `get…FuelEconomy` | 지원 안 함 → 화면의 모의 연료 % 사용 |
| 배터리 SOC·잔량·용량, 전비 | `EvBattery.*` | SOC·잔량·용량 읽힘(Wh 단위), 전비 지원 안 함 |
| 주행가능거리, 누적 주행 | `Odometer.getRangeRemainingDistance`, `getCurrentDistanceDriven` | 읽힘. 주행가능거리는 m(414000 = 414 km), 누적 주행은 km. 콘솔 API 창에서 바꿀 수 있음 |
| 충전 중 | `EvBattery.getEvChargeSwitchState` | 읽힘. 콘솔 Charge Switch를 켜고 끄면 따라 바뀜 |
| 차대번호 | `CarInfo.getVINInfo` | 에러 없이 `"invalid"`만 옴 → 에뮬레이터에서는 차종 판별 불가 |
| 주행 규제 | AAOS `CarUxRestrictionsManager` (`useLibrary("android.car")`) | 기어 P = 규제 없음, D(속도 0이어도) = 규제. 규제 중에는 시스템이 "주행 중에는 사용할 수 없습니다" 화면으로 이 앱을 가린다(`distractionOptimized` 미선언). `ChagokActivity`는 주행 최적화로 선언했지만 adb 설치본은 그래도 가려진다 |
| 트립 거리·시간, 주유 후 거리 | `Odometer.getTrip…` | 지원 안 함 |
| 주행 모드 | `DrivingMode.getCurrentDrivingModeState` | 지원 안 함 |
| 기어·브레이크 | `Gear`, `Brake` | 읽힘 |
| 문 위치·착석 | `Door.getCurrentDoorPosition`, `Seat.getCurrentSeatOccupancy` | 읽힘. 콘솔에서 문을 열면 "도착" 판정. 착석은 SDK가 반대로 읽어(SDK는 1 = 앉음, AAOS 표준·콘솔은 1 = 빈 좌석, 2 = 앉음) 브리지에서 뒤집는다. 실차 동작은 기술지원 확인 필요 |
| 순정 내비 경로·경유지(2곳)·취소·재탐색 | `NaviHelper.requestRoute`, `requestRouteWithWaypoints`, `cancelRoute`, `requestReRoute` | 동작(내비 앱이 앞으로 나와 안내 시작) |
| 목적지 남은 거리·시간·**톨비**, 경로 상태, 현재 위치 | `NaviHelper.getDestinationInfo` 등 → 리스너 | 동작 |
| 경로 옵션(무료 도로·최소 시간) | `RouteInfo.routeOption`, `changeRouteOption` | 반영 안 됨. `RouteInfo`의 옵션은 내비까지 전달되지 않고(내비 로그의 RouteInfo에 옵션 필드 없음), `changeRouteOption`은 내비가 받기만 하고 무시한다. 화면은 안내 시작 후 옵션 변경을 한 번 더 보내고, 내비가 실제로 쓰는 옵션과 다르면 표시한다 |
| 도착 이벤트 | `onDestinationArrived` | 모의 주행이 안 돼 미확인 |
| 경유지 있을 때 목적지 정보 | `getDestinationInfo` | 남은 거리·통행료가 첫 구간(경유지까지) 기준으로 온다 |

`Odometer.setRangeRemainingDistance`는 SDK에서 사용 금지(오류 수준 deprecated)라 넣지 않았다.

## 차곡 데모 (`chagok-app.html`)
차곡 PRD v1.1의 MVP(F1~F11)를 탭 4개로 빨리 확인하는 데모. `ChagokBridge`를 그대로 쓰고, 후보지·통행료·주차비·차종 표는 `assets/chagok/data.js`의 데모 고정 데이터다. 기록·설정은 WebView localStorage에 저장한다.

| 탭 | PRD 기능 | 차량 신호 |
|---|---|---|
| 홈 | F1 잔고 카드(잔량 × 용량 × 단가, 거리 = 잔량 × 전비·연비), F9 주유·충전 직후 번역 카드, 진행 중 여행, F11 동승 인원 | SOC·용량, 충전 스위치, 착석. 연비·전비는 SDK가 안 주면 차종 표 값 |
| 갈 곳 찾기 | F2 범위(추가 0원/+1만/+3만, 당일/1박), F3 테마(0~2개), F5 추천 카드(교체·비용 분해·정렬), F8 출발("휴게소 1곳 들르기"를 켜면 가는 경로 위(출발지와 목적지 사이, 더 돌아가는 거리 8km 이하) 휴게소만 최대 3곳 보여 주고 고름. 없으면 목록 없이 바로 출발) | 내비 현재 위치, `requestRoute`/`requestRouteWithWaypoints` |
| 여행 기록 | F10 도착 기록 지도(핀, 누적 곳 수·금액) | `onDestinationArrived`, 잔량 변화, 내비 `tollFare` |
| 설정 | 차종(탱크·배터리 용량), 단가, 안전 여유, 카드 수, 인원 고정, 내연 시연용 모의 연료 % | - |

- 범위: 반경 ≈ (갈 수 있는 거리 − 안전 여유(50km 또는 15% 중 큰 값) + 추가 금액만큼의 거리) ÷ 2 ÷ 1.3. 당일은 편도 3시간 이내, 1박은 현지 이동 30km를 뺀다. 추가 0원 범위에 후보가 없으면 +1만부터 보여 준다.
- 주유·충전 감지: 잔량이 3%p 이상 오르거나 충전 스위치가 켜졌다 꺼지면 금액 확인(3만/5만/채운 만큼/직접) 후 번역 카드.
- 도착: 에뮬레이터는 모의주행이 안 돼 "도착 처리" 버튼으로 대신한다. 실제 든 돈 = 잔량 변화 × 용량 × 단가 + 통행료(내비 값, 없으면 추정). 잔량이 그대로면 추정치로 기록한다.
- 주행 중: 차곡은 게임과 분리한 `ChagokActivity`에서 열고, 이 액티비티만 `distractionOptimized`로 선언했다. 규제 신호가 오면 페이지가 정차 전용 화면을 숨기고 3줄 카드만 보인다. 다만 에뮬레이터에서는 선언해도 시스템 차단 화면이 덮는다. adb로 설치한 일반 앱(설치 출처 없음)의 주행 최적화 선언은 시스템이 인정하지 않는 것으로 보이며, 스토어 배포·인증 경로는 기술지원 확인이 필요하다.
- 디자인: [Pleos Connect UI Design Guide](https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/intro)의 Dark/Light 시스템 컬러 토큰(background·surface·text·button·informative), 타이포 스케일(Headline 60/56/48 · Title 40/36/32 · Body 30/28/26 · Label 24/20, 디스플레이 실제 px 기준이라 `--u = 1/devicePixelRatio px`로 환산), 알약형 Basic/Filled 버튼, 아이콘 + 텍스트 라벨 탭을 따른다. 주간 모드는 `prefers-color-scheme: light`. Window Policy에 맞춰 `ChagokActivity`는 가로 고정을 풀고 900px 이하(1:1 분할·세로 창)에서 한 단으로 쌓는다. 시스템 UI 폰트 Asta Sans(42dot, SIL OFL 1.1)는 `assets/chagok/fonts/`에 가변 폰트(굵기 300~800, 5.8MB)와 라이선스 `OFL.txt`를 함께 넣었다.
- 시연(콘솔): SOC를 내리고 "도착 처리" → 기록, 충전 스위치 켜기 → SOC 올리기 → 끄기 → 번역 카드, 좌석 Occupied 개수 → 1인 몫, 내연 장면은 설정에서 "내연" + 모의 연료 %를 올리면 주유로 감지.

## 구조
- `app/src/main/assets/index.html`: 모드 선택 메뉴.
- `app/src/main/assets/highway/`: 게임. `index.html`(하이웨이), `index.html?mode=circuit`(서킷). 진입점은 `src/app.js`, 서킷 레이스는 `src/circuit.js`, 나머지 `src/`는 mission-t 모듈. Three.js·Draco 디코더·차량 모델·트랙·엔진음은 `vendor/`, `assets/`에 내장(오프라인 동작).
- `VehicleInput.kt`: SDK 신호를 읽어 `window.car.set({...})`로 게임에 넘긴다.
- `MainActivity.kt`: WebView 하나. ES 모듈과 `fetch()` 때문에 에셋을 `https://appassets.androidplatform.net/assets/`로 서빙한다(`WebViewAssetLoader`). 페이지 콘솔은 logcat `CarAppWeb` 태그.

## 빌드·실행
```bash
./gradlew assembleDebug
adb install -r -g app/build/outputs/apk/debug/app-debug.apk
```
에뮬레이터에서는 Virtual Console(`adb forward tcp:8088 tcp:8088` → http://127.0.0.1:8088)에서 기어 P, 브레이크, 조향, 방향지시등을 조작한다.

### Virtual Console 키보드 조작
Virtual Console 탭에서 F12 → Console에 [`tools/vc-keyboard.js`](tools/vc-keyboard.js) 내용을 붙여넣는다. 콘솔이 쓰는 HTTP API(`PUT /vehicle/controls`, `/vehicle/turn-signal`, `/vehicle/gear`)를 직접 호출한다.

| 키 | 차량 신호 |
|---|---|
| ← → | 핸들 (떼면 가운데로) |
| ↓ / Space | 브레이크 |
| E / Q | 방향지시등 오른쪽 / 왼쪽 (누르는 동안) |
| P / D | 변속 레버 P / D |

속도·엑셀은 항상 0으로 보낸다. 끄려면 Console에서 `__vcKeyboard.stop()`. 페이지를 새로고침하면 다시 붙여넣어야 한다.

브라우저 테스트: `app/src/main/assets`를 로컬 웹 서버로 띄우고 `highway/index.html`(또는 `?mode=circuit`)을 연다(ES 모듈이라 `file://`로는 안 열린다). ←→ 조향, ↓/Space 브레이크, E 부스트, Q 점프, P 변속 레버 P↔D.
