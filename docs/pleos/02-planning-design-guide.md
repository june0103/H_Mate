# 02. 앱 기획·설계 가이드

Pleos Connect 앱 기획·설계 단계에서 반드시 지켜야 할 원칙·정책 원문 정리.

## 목차

- [Pleos Connect 앱 설계 가이드](#pleos-connect-앱-설계-가이드)
- [주행 안전을 고려한 설계](#주행-안전을-고려한-설계)
- [지역별 주행 규제 정책](#지역별-주행-규제-정책)
- [앱 카테고리](#앱-카테고리)
- [앱 구조 및 동작 모델](#앱-구조-및-동작-모델)
- [하드웨어 접근 정책](#하드웨어-접근-정책)
- [SDK API 정책](#sdk-api-정책)
- [API 호환성 및 가용성](#api-호환성-및-가용성)
- [보안 및 개인정보 보호를 고려한 설계](#보안-및-개인정보-보호를-고려한-설계)
- [성능 및 리소스 효율을 고려한 설계](#성능-및-리소스-효율을-고려한-설계)


---

## Pleos Connect 앱 설계 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/intro

이 문서는 모바일 앱을 Pleos Connect 앱으로 전환하려고 하거나, 처음부터 Pleos Connect 앱을 설계하려고 할 때 필수적으로 고려해야 할 사항을 가이드합니다.

- 주행 안전을 고려한 설계 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driver-distraction-guideline) — 주행 중 운전자 주의 분산을 줄이기 위한 차량용 앱 UX 설계 원칙을 안내합니다.

- 지역별 주행 규제 정책 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy) — 차량 상태와 지역 규정에 따른 주행 규제 판단 기준 및 앱 기능 허용 범위를 안내합니다.

- 앱 카테고리 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/app-category) — Pleos Connect 앱 카테고리의 정의와 카테고리별 주행 중 기능 제약 및 예외 허용 범위를 안내합니다.

- 앱 구조 및 동작 모델 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/app-structure-screen-model) — Pleos Connect 차량용 앱의 멀티 윈도우 화면 구성, 화면 방향·크기 대응 및 포그라운드·백그라운드 동작 설계 원칙을 안내합니다.

- 하드웨어 접근 정책 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/platform-api-architecture) — Pleos Connect 하드웨어 접근 정책과 주행 중 네트워크 대응 전략을 안내합니다.

- SDK API 정책 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/pleos-connect-sdk-api) — AAOS Alignment 및 하위 호환성 등 Pleos Connect SDK API 정책을 설명합니다.

- API 호환성 및 가용성 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/api-compatibility-availability) — Pleos Connect 적용 차량의 API 호환성과 AAOS Vehicle Property 가용성을 안내합니다.

- 보안 및 개인정보 보호를 고려한 설계 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/security-privacy) — 차량 환경에서의 계정·세션 관리와 개인정보 보호 설계 원칙을 안내합니다.

- 성능 및 리소스 효율을 고려한 설계 (https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/performance-resource-mgmt) — 앱 기동·리소스 효율과 오디오 포커스를 고려한 성능 설계 원칙을 안내합니다.


---

## 주행 안전을 고려한 설계

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driver-distraction-guideline

Pleos Connect에서 동작하는 앱은 차량 환경과 주행 상황을 고려하여 운전자의 주의를 분산시키지 않도록 설계해야 합니다. 앱의 주행 중 사용 가능 여부와 기능 제한은 주행 상태, 지역 및 앱 카테고리에 따라 달라질 수 있습니다. [AAOS 운전자 주의 분산 행동 가이드라인](https://source.android.com/docs/automotive/driver_distraction/guidelines?hl=ko)을 준수하고, 아래 UX 원칙에 유의해 설계·개발합니다.

### 주행 규제 상태에 따른 앱 동작

주행 중 앱의 사용 가능 여부와 기능별 제한 기준은 주행 상황, 지역 및 앱 카테고리에 따라 달라질 수 있습니다. 자세한 내용은 다음 문서를 참조합니다.

- 주행 상황 및 지역별 제한: [지역별 주행 규제 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy)
- 앱 카테고리별 제한 및 예외: [앱 카테고리](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/app-category)
- `CarUxRestrictions` 연동 및 구현 방법: [주행 규제 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/driving-restriction-development-guide)

앱은 `CarUxRestrictions`를 기준으로 주행 중 제한 상태를 확인하고, 제한 대상 기능의 UI와 동작을 적절히 축소하거나 비활성화해야 합니다. 주행 규제 상태에서 영상 재생을 시작하거나 재개하지 않도록 처리하고, 오디오 재생은 [오디오 포커스 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/audio-focus-development)에 정의된 정책을 준수합니다.

### 주의 분산 방지 UX 설계 원칙

#### 시선 분산 최소화

- 운전에 필요한 정보는 운전자가 짧은 시간 안에 이해할 수 있도록 간결하고 명확하게 표시합니다. 플랫폼에서 제공하는 HUD·클러스터·OSD 영역을 사용하는 경우 해당 영역의 정책을 준수합니다.
- 주행 중에는 운전자의 시선을 과도하게 끄는 동영상, 애니메이션 및 움직이는 이미지를 최소화하거나 제한합니다.
- 이미지는 정보 전달에 필요한 범위에서 최소한으로 사용하고, 장식적이거나 반복적으로 움직이는 시각 요소는 지양합니다.

#### 정보 구조·인터랙션

- 주행 중 필요한 정보와 기능에 우선순위를 두고, 운전자가 짧은 시간 안에 이해하고 사용할 수 있는 위치에 배치합니다.
- 필요한 기능에 최소한의 조작으로 도달할 수 있도록 화면과 메뉴 구조를 단순화합니다.
- 자주 쓰는 기능은 첫 화면·상단에 두고, 드롭다운·다단 메뉴는 남용하지 않습니다.
- 주행 중에는 시선을 과도하게 끄는 자동 애니메이션이나 반복적인 화면 전환을 최소화합니다.
- 운전에 중요한 오디오 안내가 재생될 때는 오디오 포커스 정책에 따라 미디어 재생을 일시 중지하거나 볼륨을 조정하는 등 적절히 대응합니다.

#### 터치 조작 최적화

- [Pleos Connect UI Design Guide](https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/intro)에 따라 큰 터치 영역(large touch target), 높은 대비(high contrast), 텍스트 최소화 패턴을 적용합니다.
- 터치스크린 조작을 최소화하고, 음성 명령이나 물리 버튼 등 차량에서 제공하는 입력 방식과 연계한 인터랙션을 지원합니다.
- 게임, 인터넷 서핑, 피트니스 등 운전자의 주의를 분산시킬 수 있는 활동을 유도하지 않습니다.
- 버튼 크기와 터치 영역 간 간격은 [Pleos Connect UI Design Guide](https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/intro)의 기준을 따릅니다.

[이미지: 터치 조작 가이드]

[버튼 보러 가기 >](https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/basic-button)

#### 텍스트 입력 최소화·음성·프리셋 우선

- 검색·로그인에서도 키보드 입력보다 프리셋 선택, 최근 항목 및 음성 입력을 우선하도록 설계합니다.
- 음악 앱은 프리셋 플레이리스트, 최근 재생 및 즐겨찾기와 같이 자주 사용하는 기능을 첫 화면에 배치합니다.


---

## 지역별 주행 규제 정책

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy

> [INFO]
>
> `CarUxRestrictions` 연동 방법은 [주행 규제 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/driving-restriction-development-guide)를 참조합니다.
> 플랫폼 버전과 양산 차량에 따라 주행 규제 정책이 다를 수 있습니다.

### 주행 규제 개요

Pleos Connect의 주행 규제는 차량의 속도·기어·브레이크 등 하드웨어 신호와 지역별 안전 규정에 따라 적용됩니다.

### 주행 상황에 따른 앱 사용 여부

앱에서 Distraction Optimization(DO)를 선언하지 않았을 때, 주행 상황에 따라 앱 사용 가능 여부가 달라집니다.
다음 표는 상황별 기본 동작을 설명합니다.

| 상황 | DO 미선언 시 앱 사용 |
| --- | --- |
| Parking(주차) | 사용 가능 |
| Drive(주행) | 사용 불가 |

### 지역별 기능 허용 범위

주행 규제가 적용된 상태에서, 주행 상황(Parking, Idling, Driving)과 지역에 따라 기능별 사용 가능 여부가 달라집니다.
✅는 사용 가능, ⛔는 사용 제한, `음성한정`은 음성으로만 사용 가능함을 의미합니다.

#### 주행 상황별 기능 제한

##### 북미 지역

| 구분 | 키보드 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) | 다이얼패드 UX\_RESTRICTIONS\_NO\_DIALPAD(1) | 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 미디어 앱 로그인, 설정, 검색 UX\_RESTRICTIONS\_NO\_SETUP(64) UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 디지털키, 도어, 시트, 차량 설정 UX\_RESTRICTIONS\_NO\_SETUP(64) | 동영상 시청 UX\_RESTRICTIONS\_NO\_VIDEO(16) | 내비게이션 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | QR 로그인 UX\_RESTRICTIONS\_NO\_SETUP(64) | 매뉴얼  (대용량 텍스트) UX\_RESTRICTIONS\_LIMIT\_CONTENT(32) UX\_RESTRICTIONS\_LIMIT\_STRING\_LENGTH(4) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Parking | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Driving | ⛔ | ⛔ | `음성한정` | ⛔ | ⛔ | ⛔ | ⛔ | ⛔ | ⛔ |

- 표의 「검색」은 웹 검색이 아니라, 차량 인포테인먼트 시스템 내 통합 검색을 의미합니다.

> [TIP] 지역별 키보드 분기 처리
>
> InfoLINK용 앱을 개발하는 경우 지역별 키보드 정책에 따른 분기 처리가 필요할 수 있습니다. 자세한 사항은 [partnership@pleos.ai](mailto:partnership@pleos.ai)로 문의하시기 바랍니다.

##### 유럽 지역

| 구분 | 키보드 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) | 다이얼패드 UX\_RESTRICTIONS\_NO\_DIALPAD(1) | 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 미디어 앱 로그인, 설정, 검색 UX\_RESTRICTIONS\_NO\_SETUP(64) UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 디지털키, 도어, 시트, 차량 설정 UX\_RESTRICTIONS\_NO\_SETUP(64) | 동영상 시청 UX\_RESTRICTIONS\_NO\_VIDEO(16) | 내비게이션 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | QR 로그인 UX\_RESTRICTIONS\_NO\_SETUP(64) | 매뉴얼  (대용량 텍스트) UX\_RESTRICTIONS\_LIMIT\_CONTENT(32) UX\_RESTRICTIONS\_LIMIT\_STRING\_LENGTH(4) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Parking | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Driving | ✅ | ✅ | ✅ | ⛔ | ⛔ | ⛔ | ✅ | ⛔ | ⛔ |

##### 그외 지역

| 구분 | 키보드 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) | 다이얼패드 UX\_RESTRICTIONS\_NO\_DIALPAD(1) | 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 미디어 앱 로그인, 설정, 검색 UX\_RESTRICTIONS\_NO\_SETUP(64) UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | 디지털키, 도어, 시트, 차량 설정 UX\_RESTRICTIONS\_NO\_SETUP(64) | 동영상 시청 UX\_RESTRICTIONS\_NO\_VIDEO(16) | 내비게이션 검색 UX\_RESTRICTIONS\_NO\_KEYBOARD(8) UX\_RESTRICTIONS\_NO\_FILTERING(2) | QR 로그인 UX\_RESTRICTIONS\_NO\_SETUP(64) | 매뉴얼  (대용량 텍스트) UX\_RESTRICTIONS\_LIMIT\_CONTENT(32) UX\_RESTRICTIONS\_LIMIT\_STRING\_LENGTH(4) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Parking | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Driving | ✅ | ✅ | ✅ | ✅ | ⛔ | ⛔ | ✅ | ⛔ | ⛔ |

> [INFO]
>
> 주행 규제 상태에서의 미디어 재생 제한 및 입력 처리 원칙은 [주행 안전을 고려한 설계](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driver-distraction-guideline)을 참고하시기 바랍니다.


---

## 앱 카테고리

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/app-category

### 앱 카테고리 정의

앱 설계 시점부터 앱의 카테고리를 명확히 정의해야 합니다.

Pleos Connect가 제공하는 앱 카테고리에는 Maps/Navigation, Mobility Services, Lifestyle, Entertainment, Business, Music/Audio, Kids, Social/Communication 등이 있습니다.

| 카테고리 | 핵심 목적 |
| --- | --- |
| Maps/Navigation | 위치 기반 길 안내 |
| Mobility Services | 이동 수단/이동 서비스 제공 |
| Lifestyle | 일상생활 관리 및 향상 |
| Entertainment | 오락 및 여가 제공 |
| Business | 업무 및 생산성 지원 |
| Music/Audio | 오디오 콘텐츠 중심 |
| Kids | 아동 대상 콘텐츠 |
| Social/Communication | 사람 간 소통 및 네트워크 |

### 앱 카테고리별 제약조건

앱 카테고리와 주행 상태에 따라 주행 중 사용할 수 있는 기능의 범위가 달라질 수 있습니다. 구체적인 주행 규제 기준은 [지역별 주행 규제 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy)을 참조합니다.

일부 카테고리는 주행 중 운전자에게 필요한 정보를 제공하거나 최소한의 조작을 지원하는 범위에서 기능이 제한적으로 허용될 수 있습니다. 다음 섹션에서는 카테고리별 허용 가능 기능의 예시를 설명합니다.

#### 주행 중 기능이 제한적으로 허용될 수 있는 카테고리

일부 카테고리는 주행 중 운전자에게 필요한 정보를 제공하거나 최소한의 조작을 지원하는 경우 기능이 제한적으로 허용될 수 있습니다. 아래 내용은 허용 가능한 기능의 예시이며, 실제 적용 여부는 주행 상태와 지역별 정책에 따라 달라질 수 있습니다.

- Music/Audio 카테고리: 청각 위주의 콘텐츠 소비 앱에서는 다음과 같은 기능이 제한적으로 허용될 수 있습니다.

  - 앨범 아트 등 단순 시각 정보 표시 가능
  - 재생/일시정지/곡 넘기기 등 단순 조작
  - 운전 중 음악 스트리밍 청취
  - 라디오 채널 변경
  - 오디오북 재생
- Maps/Navigation 카테고리: 주행 경로를 안내하는 앱에서는 다음과 같은 기능이 제한적으로 허용될 수 있습니다.

  - 주행 경로 안내 및 실시간 교통 정보
  - 운전자의 주의를 분산시키지 않는 간결한 지도
  - Turn By Turn(TBT) 음성 안내 활성화
  - 목적지까지 경로 탐색 기능
  - 실시간 빠른 길 안내
  - 주변 교통 상황 안내
- Mobility Services 카테고리: 위치 기반 서비스를 제공하는 앱에서는 다음과 같은 기능이 제한적으로 허용될 수 있습니다.

  - 주행과 밀접한 관련이 있는 차량 관리 서비스
  - 현재 위치 기반의 즉각적인 정보 제공
  - 전기차 충전소 찾기 및 경유지 설정
  - 가까운 주유소 탐색
  - 주행 중 접근 가능한 주차장 위치 확인


---

## 앱 구조 및 동작 모델

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/app-structure-screen-model

Pleos Connect는 Driving view와 앱 영역을 함께 표시하고, 앱 영역에 최대 2개의 앱을 배치할 수 있는 차량용 멀티 윈도우 환경을 제공합니다.
앱은 화면에 표시되는 영역과 화면 방향이 변경될 수 있으므로, 모바일 환경과 다른 화면 구성 및 앱 생명주기를 고려해야 합니다.

### 화면 구성 및 멀티 윈도우 대응

Pleos Connect 화면은 운전자에게 차량 정보를 제공하는 Driving view 영역과 앱을 표시하는 영역으로 구성됩니다.
Driving view는 운전자와 가까운 고정 영역에 표시되며, 앱이 임의로 가리거나 종료할 수 없습니다.
앱 영역은 차량 및 디스플레이 구성에 따라 하나 또는 두 개의 window로 나뉩니다.

#### Portrait와 landscape 화면 모델

Pleos Connect에서 앱은 화면이 차지하는 window 영역에 따라 `portrait` 또는 `landscape` 형태로 표시됩니다.

- `portrait`: 앱이 앱 영역 중 하나의 window를 사용하는 화면 형태입니다. 두 개의 앱을 동시에 표시할 때 각 앱은 일반적으로 portrait로 표시됩니다.
- `landscape`: 하나의 앱이 두 개의 앱 영역을 확장해 사용하는 화면 형태입니다. 앱이 더 넓은 화면을 사용하므로 추가 정보나 확장된 콘텐츠를 제공할 수 있습니다.
- Portrait와 landscape 각각에 맞는 화면을 설계해야 합니다.
- Portrait에서는 핵심 기능과 주요 정보를 제공하고, landscape에서는 확장된 영역을 활용해 부가 정보와 확장 기능을 제공하도록 구분해 설계합니다.

#### 앱 실행 및 window 전환

새로운 앱이 실행되거나 현재 앱의 window 상태가 변경되면 앱의 표시 위치와 화면 방향이 달라질 수 있습니다. 세부적인 window 배치와 전환 방식은 차량 및 디스플레이 구성에 따라 달라질 수 있습니다.

- 새 앱은 현재 표시 중인 앱의 화면 방향을 기준으로 표시됩니다. 하나의 landscape 앱이 표시 중이면 새 앱도 landscape로 실행되고, 두 개의 portrait 앱이 표시 중이면 새 앱은 primary window에 portrait로 실행됩니다.
- 화면에서 사라진 앱이 종료되는 것은 아닙니다. 해당 앱은 백그라운드에 남아 있을 수 있으며, 표시 중인 앱이 종료되면 최근에 사용한 앱 순으로 다시 window에 표시될 수 있으므로 앱이 다시 foreground로 전환되는 상황을 고려해야 합니다.
- 사용자는 window bar 또는 제스처를 통해 앱의 위치와 크기를 변경할 수 있습니다. Window 크기가 변경되어도 핵심 기능을 사용할 수 있도록 화면을 유연하게 구성해야 합니다.

### 화면 방향 및 크기 대응

Pleos Connect에서는 Activity의 화면 방향 설정에 따라 사용하는 window 수와 화면 크기가 달라집니다.

- `screenOrientation`을 별도로 지정하지 않으면 Activity는 기본적으로 portrait로 실행되며 하나의 window를 사용합니다.
- `screenOrientation`을 `landscape`로 지정하면 두 개의 window 영역을 사용할 수 있습니다.
- portrait와 landscape 화면을 각각 설계하고, 화면 전환 시 콘텐츠의 우선순위와 배치가 자연스럽게 변경되도록 구성합니다.
- 화면 방향과 window 크기 변경에 따라 레이아웃이 깨지지 않도록 반응형 레이아웃을 사용합니다. 화면 방향은 `AndroidManifest.xml`의 `screenOrientation` 설정을 기준으로 동작하므로 앱의 화면 설계와 함께 검토해야 합니다.

### 포그라운드·백그라운드 전환 및 동작 설계

Pleos Connect 앱은 화면 분할, window 위치 변경, 다른 앱 실행 및 주행 상황 변화에 따라 포그라운드와 백그라운드를 빈번하게 전환할 수 있습니다. 화면에서 사라진 앱이 종료되지 않고 백그라운드에 남아 있을 수 있다는 점을 고려해야 합니다.

- 포그라운드 진입 시 현재 상태를 복원하고, 화면 크기와 방향에 맞게 UI를 다시 구성합니다.
- 백그라운드 전환 시 화면 갱신, 애니메이션, 불필요한 센서 수집 등 즉시 중단할 수 있는 작업을 정리합니다.
- 오디오 재생, 통신, 다운로드와 같이 백그라운드에서도 지속할 작업은 작업별 실행 조건과 중단·재개 정책을 명확히 정의합니다.
- 백그라운드에서 불필요한 상주, 타이머, 폴링 및 네트워크 요청을 최소화하여 차량의 시스템 리소스를 절약합니다.
- 앱이 종료되거나 프로세스가 재생성되는 경우에도 사용자 데이터와 진행 상태를 복원할 수 있도록 상태 저장 전략을 마련합니다.


---

## 하드웨어 접근 정책

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/platform-api-architecture

Pleos Connect 적용 차량에서 실행되는 앱은 Pleos Connect SDK가 제공하는 표준 플랫폼 API를 통해 하드웨어에 접근해야 합니다.
모바일 환경을 기준으로 개발한 앱은 차량에서 그대로 실행하기 어려우며, 모바일용 API가 차량 환경에서 제공되지 않거나 제한될 수 있습니다.
또한 Pleos Connect 적용 차량에서 실행되는 앱은 주행 중 네트워크 불안정에 대비해 설계해야 합니다.

### 차량 하드웨어 접근 정책

Pleos Connect 적용 차량에서 실행되는 앱은 보안과 시스템 안정성을 위해 차량 하드웨어에 직접 접근할 수 없습니다.

- 직접 접근 금지: 차량의 물리적 제어나 센서에 직접 접근해서는 안 됩니다.
- 플랫폼 API 우선 원칙: 차량 상태 정보는 Pleos Connect SDK가 제공하는 표준 플랫폼 API를 통해 확인하고 제어해야 합니다.

> [WARNING] 모바일 전용 API 사용 시 주의사항
>
> 모바일 앱 개발에 사용한 API는 차량용 플랫폼에서 제공되지 않거나 기능이 제한될 수 있습니다.
> 모바일 앱을 차량용 앱으로 전환할 때는 사용하는 API가 차량 환경에서 지원되는지 반드시 확인해야 합니다.
>
> - 제한될 수 있는 기능: Telephony(전화), SMS, 일부 센서(자이로, 근접 등), 캘린더, 주소록 등
> - 대응 전략: 차량 환경에서 필수적이지 않은 기능은 제거하거나, API 지원 여부를 확인한 뒤 동작하도록 Optional 구조로 설계합니다.

### 주행 중 네트워크 환경 대응

Pleos Connect 적용 차량에서 실행되는 앱은 주행 중 셀룰러 또는 Wi-Fi 연결이 끊기거나 대역폭이 급격히 낮아지는 상황에 대비해야 합니다.
오프라인 또는 저대역폭 환경에서도 핵심 기능을 사용할 수 있도록 다음과 같이 설계하는 것을 권장합니다.

- 캐싱 전략: 최근 사용한 콘텐츠를 로컬에 저장하여 오프라인 상태에서도 표시
- 통신 최적화: 요청 재시도(Retry) 로직과 적절한 타임아웃 설정 적용
- 사용자 피드백: 네트워크가 끊겼을 때 사용자가 상태를 인지할 수 있도록 오프라인 모드 안내 UI 제공


---

## SDK API 정책

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/pleos-connect-sdk-api

AAOS Alignment 정책, 하위 호환성 정책 등 Pleos Connect SDK API 정책을 설명합니다.

### API 디자인 원칙: AAOS Alignment

Pleos Connect SDK는 동일 혹은 유사 기능의 API가 AAOS에 추가될 경우 플랫폼 파편화를 최소화하고 앱의 이식성을 극대화하기 위해 AAOS Alignment 정책을 준수합니다.

- 표준 우선 정책: 동일하거나 유사한 기능의 API가 AAOS 표준에 이미 존재하는 경우, 해당 표준 API 사용을 권장하며 Pleos Connect SDK에서는 중복 기능을 제공하지 않습니다.
- 확장 기능 제공: AAOS 표준 API로 접근이 불가능하거나, 제조사 고유의 보안 및 특화 제어가 필요한 영역에 한하여 Pleos Connect SDK 전용 API를 제공합니다.

[이미지: Pleos Connect API Policy]

[이미지 설명 필요: Pleos Connect 플랫폼 아키텍처 다이어그램(Android 14 Automotive OS 기반). 위에서부터 3rd Party Application(Android SDK / Pleos Connect SDK — Vehicle·NaviHelper·SpeechToText·TextToSpeech·FusedLocation SDK, Pleos App Market APK 배포) → Pleos Connect OS(Navigation, Gleo AI ↔ Cloud(G/W)) → Pleos Connect Service Layer(Pleos Connect Automotive Service, Permission Handling) → Vehicle Property(Android API, Android API(System Permission), OEM API(Add On)) → VHAL(Vehicle, Audio..) → Pleos Vehicle OS 계층 구조. Android SDK는 Vehicle Property의 Android API로 직접 연결됨]

### 하위 호환성 및 마이그레이션

차량 플랫폼의 진화에 발맞추어, SDK 전용 기능이 향후 AAOS 표준 스펙으로 편입될 수 있습니다. 이 경우 SDK는 다음과 같이 개발자의 마이그레이션을 지원합니다.

- 호환성 보장: Pleos Connect SDK의 특정 API가 AAOS 표준으로 수용되더라도, 기존 SDK API는 최소 2개의 메이저 버전(Major Version)까지 하위 호환성을 유지합니다.
- 안정적 전환: 개발사는 충분한 유예 기간 동안 기존 코드를 유지하면서 차세대 표준 API로 점진적인 전환을 진행할 수 있습니다.

### API 선택 기준: AAOS와 Pleos Connect SDK 비교

앱 개발 시 필요한 데이터나 제어 기능의 성격에 따라 어떤 API를 사용할지 확인한 뒤 설계에 반영합니다.

| 구분 | 접근 경로 | 대상 기능 예시 |
| --- | --- | --- |
| 표준 데이터 | AAOS Car API | 속도, 기어 상태, 외기 온도, 연료 레벨 등 |
| 특화 기능 | Pleos Connect SDK | 제조사 전용 시트 제어, 특화 라이팅, 고유 보안 인증 등 |

### 차량 모델별 API 호환성

Pleos Connect 차량 모델에 따라 지원 API가 다를 수 있습니다.
자세한 내용은 [API 호환성 및 가용성](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/api-compatibility-availability) 문서를 참조하시기 바랍니다.


---

## API 호환성 및 가용성

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/api-compatibility-availability

Pleos Connect 앱을 설계·개발할 때는 차량에서 사용할 수 있는 Pleos Connect SDK의 API와 AAOS vehicle property를 미리 확인해야 합니다.
이 페이지에서는 Pleos Connect 적용 차량의 API 호환성과 AAOS Vehicle Property 가용성을 정리합니다.

### Pleos Connect SDK API 호환성

Pleos Connect가 적용된 차량 모델에 따라 API 지원 여부가 달라질 수 있습니다.

| Pleos Connect SDK | 차량 모델별 API 호환성 |
| --- | --- |
| Vehicle SDK | 모델에 따라 다름    API 가용 여부는 Vehicle SDK 도메인별 `checkXXXCapabilities` API로 확인할 수 있습니다. |
| NaviHelper SDK | 내비게이션이 탑재된 차량에 한해 전체 호환 |
| TextToSpeech SDK, SpeechToText SDK | 전체 호환 |
| FusedLocation SDK | 모델에 따라 다름    FusedLocation SDK 가용 여부는 FusedLocation SDK `isSupported` API로 확인할 수 있습니다. |

### AAOS Vehicle Property 가용성

AAOS Vehicle Property의 지원 여부와 접근 가능 상태는 차량 모델 및 프로퍼티에 따라 다를 수 있습니다. 프로퍼티의 정의와 대상 차량에서의 지원 여부를 확인하려면 Google 공식 문서를 참조하시기 바랍니다.

- Property 정의와 ID 확인: [VehiclePropertyIds](https://developer.android.com/reference/android/car/VehiclePropertyIds)
- 실제 차량에서의 설정 및 접근 가능 여부 확인: [CarPropertyManager](https://developer.android.com/reference/android/car/hardware/property/CarPropertyManager)

> [WARNING]
>
> - 차량 모델에 따라 프로퍼티 지원 여부가 다를 수 있으므로, 모델명을 기준으로 분기 처리하지 말고 `VehiclePropertyIds`와 `CarPropertyManager`를 사용하여 실제 지원 여부와 접근 가능 상태를 확인하시기 바랍니다.
> - Pleos Connect SDK API가 제공하지 않는 특수 프로퍼티가 필요한 경우, 직접 구현 전 반드시 기술 지원팀에 검토를 요청하시기 바랍니다.


---

## 보안 및 개인정보 보호를 고려한 설계

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/security-privacy

차량은 여러 사람이 함께 쓰는 기기이므로, 보안 및 개인 정보 보호에 유의하여 앱을 설계해야 합니다.
Pleos App Market 등록 시 준수해야 하는 상세 보안 기준은 [앱 보안 가이드](https://document.pleos.ai/docs/pleos-only/app-registration-guide/app-security-guide)를 참조하시기 바랍니다.

### 계정/세션 관리

- 차량 소유자 변경, 운전자 교체 시 쉽게 로그아웃 및 계정 전환이 가능해야 합니다.
- 소셜 로그인, OTT 계정 등 토큰은 Keystore 등 안전한 스토리지에 저장해야 합니다.

### 개인정보 최소 수집

- 앱에서 사용자의 위치, 음성, 계정 정보 등을 요구할 때 “왜 필요한지” 명확히 해야 하며, 동의 플로우를 간단하고 이해하기 쉽게 설계해야 합니다.
- 불필요한 개인정보(PII)는 저장하지 않고, 로그에 개인정보가 포함될 경우 마스킹하여 기록합니다.

### 차량 외부로 나가는 데이터

Telemetry/분석 이벤트 설계 시 다음 사항을 설계에 포함해야 합니다.

- 차량 ID, 사용자 ID를 어떻게 익명화/가명화할지 정책을 수립합니다.
- 운전 중 행동 데이터를 마케팅 목적으로 사용하지 않도록 구분합니다.


---

## 성능 및 리소스 효율을 고려한 설계

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/performance-resource-mgmt

인포테인먼트 시스템에서는 여러 앱이 동시에 동작하므로, 리소스 사용을 절제하는 것이 중요합니다. 빠른 기동과 가벼운 상주를 염두에 두고 앱을 설계해야 합니다.

### 앱 시작 시간 최적화

앱이 사용자에게 빨리 보여질수록 체감 품질이 높아집니다. Cold start 시 스플래시 화면에 오래 머물지 않도록 설계합니다.

- Cold start 시 스플래시에서 오래 묶이지 않도록 필요한 최소한만 초기화합니다.
- 초기에는 꼭 필요한 리소스만 로딩하고, 나머지는 지연 로딩(lazy loading)으로 처리합니다.

### 메모리·CPU·배터리

차량용 플랫폼은 제한된 리소스를 여러 앱이 나누어 쓰므로, 메모리·CPU·배터리 사용을 최소화해야 합니다.

- CPU 사용률은 다음 기준을 따릅니다.
  - 내비게이션 앱(주행 중): 15% 이하
  - 내비게이션 앱이 아닌 앱(주행 중): 5% 미만
  - 모든 앱(정차 중): 25% 이하
- RAM 사용량은 400 MB 이하로 유지합니다.
- 백그라운드에서의 긴 작업, 불필요한 타이머·폴링은 최소화합니다.
- 이미지·리스트 뷰 등에서는 리사이클 뷰 활용, 캐시 전략 등으로 메모리 사용을 최적화합니다.

### 오디오·미디어 재생

오디오·미디어 앱은 시스템 전체 오디오 포커스 정책을 따라야 하며, 다른 앱이나 알림과 충돌하지 않도록 설계합니다.

- Pleos Connect 오디오 포커스 정책을 준수합니다.
- 포커스 획득·상실 등 포커스 변화에 대응하는 로직을 반드시 구현합니다.

> [INFO]
>
> Pleos Connect 오디오 포커스 정책은 [Pleos Connect 오디오 포커스 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/audio-focus-development) 문서를 참조합니다.
