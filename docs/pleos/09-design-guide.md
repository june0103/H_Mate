# 09. 디자인 가이드

Pleos Connect 디자인 시스템(Introduction·Getting started·Foundations·Experiences·Components) 원문. 이미지 위주 페이지는 텍스트만 남아 있음.

## 목차

- [Pleos Connect UI Design Guide](#pleos-connect-ui-design-guide)
- [Introduction](#introduction)
- [Theme](#theme)
- [Design Token](#design-token)
- [Window Policy](#window-policy)
- [Display Layout](#display-layout)
- [Colors](#colors)
- [Typography](#typography)
- [Interaction](#interaction)
- [Animation](#animation)
- [Shadow](#shadow)
- [Iconography](#iconography)
- [App Icon](#app-icon)
- [3D Assets](#3d-assets)
- [Driving view](#driving-view)
- [Gleo AI](#gleo-ai)
- [Basic button](#basic-button)
- [Toggle button](#toggle-button)
- [Text button](#text-button)
- [Icon button](#icon-button)
- [Keypad](#keypad)
- [App library](#app-library)
- [Bar](#bar)
- [Controllers](#controllers)
- [Dropdown](#dropdown)
- [Text Field](#text-field)
- [Number Field](#number-field)
- [Global Navigation Bar(GNB)](#global-navigation-bargnb)
- [Indicator](#indicator)
- [System Notification](#system-notification)
- [Vehicle Notification](#vehicle-notification)
- [Page Navigation](#page-navigation)
- [System Popup](#system-popup)
- [Toast Popup](#toast-popup)
- [Tabs](#tabs)
- [Checkbox](#checkbox)
- [Radio button](#radio-button)
- [Switch](#switch)
- [Spinner](#spinner)
- [Status bar](#status-bar)
- [Toast](#toast)
- [Widgets](#widgets)


---

## Pleos Connect UI Design Guide

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/intro

##### Pleos Connect는 모빌리티 환경을 위한 소프트웨어로 다양한 고객, 차종, 국가, 브랜드에 따라 최적화된 경험을 제공합니다. Pleos Playground에서 제공하는 가이드를 사용하여 자동차 경험에 최적화된 앱을 디자인해보세요.

[이미지: pleos_connect_deisgnguide_intro_01]

### 기본 디자인 원칙

#### 사용성과 안전성 (Easy & Safe)

- 운전 중 시각적, 인지적 부담을 최소화하여, 쉽고 명확한 정보 전달을 통해 사용성과 안전성을 확보합니다.
- 운전 중 동영상, 애니메이션 이미지, 텍스트 자동 스크롤과 같이 운전자의 시선을 유도하면 안 됩니다.
- 앱 조작은 한 손만으로도 가능해야 하며, 양손을 모두 사용하게 해서는 안 됩니다. 단순한 조작만을 요구해야 합니다. 운전자의 손이나 발을 움직이는 동작을 유도해서는 안 됩니다.

#### 일관성 (Consistency)

- 아이콘, 용어, 상호작용 패턴은 일관되게 제공되어야 합니다.
- Pleos Connect에서 제공하는 디자인 가이드를 활용하여 기본 탑재된 UI와 일관성을 유지하여 사용자의 학습 부담을 줄입니다.

#### 즉시성 (Glanceability)

- 한눈에 확인할 수 있는 정보를 제공하고, 단순한 조작만을 요구하세요.
- 운전자는 짧은 시간(1~2초) 내에 정보를 파악해야 하므로, 핵심 정보만 노출하고 불필요한 요소는 배제합니다.
- 사용자 입력 후 시스템 응답 시간이 0.25초를 초과하지 않아야 합니다. 응답이 지연되거나 시간이 필요한 경우에는 시각적으로 응답 중인 상태를 알리세요.

### 주행 안전을 고려한 상호 작용 원칙

차량 내 인터페이스의 사용성은 운전자의 안전과 직결되는 중요한 요소입니다. 흔들리는 주행 환경에서도 한 손으로 직관적이고 안정적으로 조작할 수 있도록, 주행 환경과 상호작용 원칙을 종합적으로 고려한 인터페이스 설계가 필요합니다.

> [INFO]
>
> 주행 안전을 고려한 설계 원칙은 **Pleos Connect 앱 설계 가이드**의 [주행 안전을 고려한 설계](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driver-distraction-guideline) 문서를 참조하시기 바랍니다.

### 인터페이스 디자인 가이드

#### 색상 및 대비 (Color & Contrast)

- 중요한 정보는 강한 대비로 표현하되, 과도한 색상 사용은 피합니다.
- 배경색과 글자색 간 충분한 대비(Contrast)를 확보하여 명확하게 보이도록 합니다. 아이콘, 텍스트 및 기타 이미지의 대비율이 4.5:1 이상이어야 합니다.

[색상 가이드 보러 가기 >](https://document.pleos.ai/docs/connect/guide/docs-design/foundations/colors)

#### 글꼴 및 가독성 (Typography & Readability)

- 장문의 텍스트는 최소화하고, 단순한 아이콘과 짧은 문장으로 전달합니다.
  - 영어로 된 단일 메시지 또는 텍스트는 120자(영문 기준) 또는 24 단어(영문 기준) 이하여야 합니다.
  - 일본어 단일 메시지 또는 일본어 텍스트는 30자(영문 기준) 이하여야 합니다.
  - 한 줄의 텍스트는 80자(영문 기준) 이하여야 합니다.
  - 문장 또는 단락은 텍스트 3줄을 넘지 않아야 합니다.
- 차량 내 화면 크기와 사용자 간 거리를 고려하여 적절한 글꼴 크기를 유지해야 합니다.
- 중요 정보(예: 속도, 내비게이션 안내, 경고 메시지 등)를 표시하는 기본 텍스트는 높이 5 mm 이상, 보조 텍스트는 최소 3 mm 이상으로 설정하여 주행 중 빠르게 인식할 수 있도록 합니다.

[글자 사이즈 가이드 보러 가기 >](https://document.pleos.ai/docs/connect/guide/docs-design/foundations/typography)

#### 아이콘 및 터치 피드백 (Icons & Feedback)

- 국제적으로 통용되는 직관적인 아이콘을 사용하며, 텍스트 라벨과 함께 제공하여 이해도를 높입니다.
- 터치 시 진동 피드백(Haptic Feedback)이나 음성 피드백을 제공하여 조작의 정확성을 높입니다.

[아이콘 가이드 보러 가기 >](https://document.pleos.ai/docs/connect/guide/docs-design/foundations/contents-creation/iconography)

#### 알림(Notification)

- 알림은 중요한 정보를 간결하게 전달해야 합니다.
- 앱은 운전자의 필요와 관련된 경우에만 알림을 표시해야 합니다.
- 알림을 통해 광고를 표시하거나, 광고주 이름 또는 제품 이름 외의 텍스트 기반 광고를 표시해서는 안 됩니다.

### 유연성 및 확장성 고려

#### 야간/주간 모드 지원

야간/주간에 따른 조명 환경에 따라 UI 밝기와 대비를 자동 조정하여 가독성을 유지합니다.

#### 음성 인터랙션 강화

Pleos Connect는 Gleo AI를 통해 음성 인터랙션을 지원합니다. 앱 설계 시 음성 인터랙션을 적절히 활용하여 사용성과 주행 안전을 높일 수 있는지 검토합니다.

음성 기반 통신 시스템은 핸즈프리로 말하고 들을 수 있어야 합니다. 핸즈프리 음성 인터페이스는 음성 대화를 시작, 종료 또는 중단하는 작업은 수동으로 실행할 수 있습니다.

#### OTA 업데이트

앱 업데이트가 용이하도록 OTA(Over-the-Air) 방식을 고려하고, 지속적인 유지보수가 가능하도록 설계합니다.

#### 확장성 고려

- Pleos Connect는 다양한 국가에서 제공되므로, 다국어 지원에 적합한 UI를 설계합니다.
- Pleos Connect는 여러 차종의 다양한 디스플레이를 지원합니다. 따라서, 다양한 해상도와 화면 비율에 유연하게 대응할 수 있도록 확장성을 고려하여 설계합니다.

[디스플레이 레이아웃 보러 가기 >](https://document.pleos.ai/docs/connect/guide/docs-design/getting-started/layout)

#### Design Resources Download

[이미지: Download][Pleos Connect display guide](https://document.pleos.ai/img/Pleos_Connect_display_guide_215.fig)

[이미지: Download][Pleos Connect design assets](https://document.pleos.ai/img/Pleos_Connect_design_assets_215.fig)


---

## Introduction

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/designing-apps-for-driving

##### Pleos Connect는 모빌리티 환경을 위한 소프트웨어로, 다양한 고객, 차종, 국가, 브랜드에 맞춰 최적화된 사용자 경험을 제공합니다.

### Pleos Connect

Pleos Connect는 Connect-S, Connect-W 등 디스플레이의 폼팩터에 유연하게 대응할 수 있도록 디자인 시스템을 업데이트하여, 다양한 차종을 지원하는 플랫폼으로서 디자인 가이드를 강화하는 데 중점을 두었습니다.

[이미지: pleos_connect_deisgnguide_intro_01]

### 기본 디자인 원칙

#### 사용성과 안전성 (Easy & Safe)

Pleos Connect는 `Easy & Safe`라는 철학 아래, 글로벌 모빌리티 환경에서 발생할 수 있는 다양한 상황과 고객 요구에 유연하게 대응할 수 있도록 지속적인 고도화를 이어가고 있습니다.

Connect-W는 사용자의 좌석에 따라 특화된 UI와 인터랙션을 제공함으로써, 차량을 이용하는 모든 사용자를 배려한 경험을 제공합니다.

- 운전 중 시각적, 인지적 부담을 최소화하여, 쉽고 명확한 정보 전달을 통해 사용성과 안전성을 확보합니다.
- 운전 중 동영상, 애니메이션 이미지, 텍스트 자동 스크롤과 같이 운전자의 시선을 유도하면 안 됩니다.
- 앱 조작은 한 손만으로도 가능해야 하며, 양손을 모두 사용하게 해서는 안 됩니다. 단순한 조작만을 요구해야 합니다. 운전자의 손이나 발을 움직이는 동작을 유도해서는 안 됩니다.

#### 일관성 (Consistency)

- 아이콘, 용어, 상호작용 패턴은 일관되게 제공되어야 합니다.
- Pleos Connect에서 제공하는 디자인 가이드를 활용하여 기본 탑재된 UI와 일관성을 유지하여 사용자의 학습 부담을 줄입니다.

#### 즉시성 (Glanceability)

- 한눈에 확인할 수 있는 정보를 제공하고, 단순한 조작만을 요구하세요.
- 운전자는 짧은 시간(1~2초) 내에 정보를 파악해야 하므로, 핵심 정보만 노출하고 불필요한 요소는 배제합니다.
- 사용자 입력 후 시스템 응답 시간이 0.25초를 초과하지 않아야 합니다. 응답이 지연되거나 시간이 필요한 경우에는 시각적으로 응답 중인 상태를 알리세요.


---

## Theme

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/theme

테마는 아이콘, 폰트, 그리고 컬러를 중심으로 브랜드 아이덴티티를 유지하기 위하여 사용합니다.

### Types

[이미지: Theme1.svg]

### Theme selected

[이미지: Theme2.svg]

### Theme samples

[이미지: Theme3.svg]

[이미지 위주 페이지: 원문 참조]


---

## Design Token

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/design-token

디자인 토큰은 디자인 시스템의 "스타일 속성 값(색상, 폰트, 간격, 그림자 등)"을 코드로 추상화 단위입니다. 이 토큰은 플랫폼 간 일관성을 유지하며 디자인을 구현하기 위한 "싱글 소스 오브 트루스(Single Source of Truth)"로 활용됩니다.

### 사용 목적

- 시스템 전체에서 일관된 UI 구현
- 디자이너와 개발자 간의 공통 언어 형성
- 유지보수 비용 절감 및 스케일 가능한 구조 제공
- 다크모드 / 라이트모드 / 브랜드 테마 전환 용이

### 구조

[이미지: display-token-1]

### 이름 규칙

[이미지: display-token-2]

1. 토큰 이름은 약속된 규칙에 따라 생성됩니다.

   - Prefix : ivi
   - Type : Snake case
2. 디자이너나 개발자는 토큰 이름을 통해 어디에서 사용되는 요소인지 인지할 수 있어야 합니다.
3. 토큰 이름은 축약된 언어가 아닌, 전체 이름을 풀어서 쓰도록 합니다.

   > 예시.
   > Do : vertical / Don’t : v
   > Do : vehicle\_setting / Don’t : vs

[이미지 위주 페이지: 원문 참조]


---

## Window Policy

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/getting-started/window

|  | Connect-S | Connect-W |
| --- | --- | --- |
| 화면 구성 | [이미지: window-policy-01]   앱 2개 실행 시: 1:1   앱 1개 실행 시: 2:0 (기본값: 1:1) | [이미지: window-policy-02]   [이미지: window-policy-03]   2:1 또는 1:2 비율로 제공됨 (기본값: 2:1) |
| 기본 화면 | [이미지: window-policy-04]   IVI 최초 실행 시   - Primary : Navigation 앱   최초 실행 이후 재탑승 시   - Primary : Navigation 앱 | [이미지: window-policy-05]   IVI 최초 실행 시   - Primary : Navigation 앱   - Secondary : Media 앱    최초 실행 이후 재탑승 시   - Primary : Navigation 앱   - Secondary : 운전자가 마지막에 사용했던 Media 앱 |
| 앱 실행 시 | 신규 앱은 이전 앱의 orientation과 동일하게 표시   - 기존에 하나의 landscape 앱이 실행 중이면, 신규 앱도 landscape 모드로 실행   - 기존에 두 개의 portrait 앱이 표시 중이면, 신규 앱은 primary window 영역에서 portrait 모드로 실행    화면에서 사라진 앱은 종료되는 것은 아니며, 화면에 표시된 앱이 종료될 경우 최근 활성화된 앱 순으로 다시 표시됨 | 신규 앱은 항상 landscape 상태로 primary window에 표시   - Primary window에 있던 앱은 secondary window로 이동   - Secondary window 에 있던 앱은 사라짐    화면에서 사라진 앱은 종료되는 것은 아니며, 화면에 표시된 앱이 종료될 경우 최근 활성화된 앱 순으로 다시 표시됨 |
| 앱 화면 위치 변경 | 3 핑거 스와이프 혹은 윈도 바를 탭하여 두 개의 앱 위치를 변경 가능 | 3 핑거 스와이프 혹은 윈도 바를 탭하여 두 개의 앱 위치를 변경 가능 |
| 앱 화면 사이즈 변경 | 3 핑거 스와이프 혹은 윈도 바를 드래그하거나 앱 윈도 버튼을 탭하여 landscape - portrait 전환 가능 | 3 핑거 스와이프 혹은 윈도 바를 드래그하거나 앱 윈도 버튼을 탭하여 landscape - portrait 전환 가능 |
| 앱 종료 | 3 핑거 스와이프로 앱 화면을 종료    앱을 종료시키면 오른쪽에서 왼쪽으로 최근 활성화된 앱 순으로 표시   - Landscape window가 종료된 경우, landscape로 표시   - Portrait window가 종료된 경우, portrait 상태로 표시   디폴트 앱인 Navigation 앱은 종료할 수 없으며, 모든 앱이 종료되면 IVI가 처음 실행될 때와 동일한 화면 표시 | 3 핑거 스와이프로 앱 화면을 종료    앱을 종료시키면 오른쪽에서 왼쪽으로 최근 활성화된 앱 순으로 표시   - Primary window가 종료된 경우, secondary window 화면이 orientation을 유지한 상태로 primary window 위치에 표시   - Secondary window가 종료된 경우, 최근 앱이 종료된 앱의 orientation과 동일한 상태로 표시    디폴트 앱인 내비 앱은 종료할 수 없으며, 모든 앱이 종료되면 IVI가 처음 실행될 때와 동일한 화면 표시 |

### Connect-W specification

- 최대 실행 가능한 앱의 개수는 2개로 제한합니다.
- App launcher(App library)는 운전석, 조수석에 모두 표시됩니다.
- Status bar는 운전석 GNB와 앱 영역 사이에 위치합니다.
- 조수석 쪽 App library에서 앱을 실행한 경우 해당 앱은 조수석 윈도에서만 실행됩니다.

> [INFO]
>
> - Pleos Connect에서 제공하는 프리로딩 앱은 landscape view, portrait view 모두를 지원합니다.
> - Driving view의 landscape view는 삭제되었고, resize handle이 제공되지 않습니다.
> - Deep link(App to App): App A에서 App B를 실행할 경우 App A를 덮고 실행되며, App B의 기능 수행이 종료될 때 화면은 App A로 전환됩니다.
> - 후방카메라 등 3:0 window를 full screen으로 나타낼 수 있도록 예외케이스를 적용할 예정입니다.


---

## Display Layout

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/getting-started/layout

### 화면 구성 요소 및 레이어

[이미지: display-layout-1]

#### 화면 구성 요소

Pleos Connect 화면은 다음과 같은 시스템 UI 요소와 애플리케이션 윈도로 구성됩니다.

1. Status bar: Driving status, Gleo AI와 검색, 시계, 네트워크 상태, 마이크 상태 표시, 알림 센터 및 프로필 선택 등 System status를 포함합니다.
2. Driving view(드라이빙뷰): Driving view는 Telltale, 속도, 차량의 상태 등 주행/안전 정보를 포함합니다.
3. Global Navigation Bar(GNB) : App library, 즐겨찾는 앱, 공조(Climate) 컨트롤 등을 포함합니다.
4. Application window : 실행 중인 애플리케이션이 표시되는 영역으로 1개, 혹은 2개의 앱을 노출할 수 있습니다. 따라서 가로 전용 게임 등 한 가지 모드로만 실행 가능한 애플리케이션을 제외한 모든 앱은 Landscape, Portrait 두 가지 레이아웃에 적절하게 조절되도록 설계해야 합니다.

#### 화면 레이어

상단의 상태 표시줄, GNB의 App library, 공조 등 패널과 키보드 등 System UI는 앱보다 상위 레이어에 위치하므로, 사용자가 해당 기능을 사용할 경우 일시적으로 애플리케이션 윈도우를 가릴 수 있습니다.

### 화면 유형

Pleos Connect의 디스플레이는 Standard, Wide 2가지 타입으로 나누어져 있습니다. 기존의 Connect-L은 Connect-S로 재분류되었습니다.

[이미지: display-layout-2]

- **Connect-S**: OS 기본 크기로 제공되는 디스플레이이며, S는 Standard의 줄임말입니다. 17인치, 14.6인치, 12.9인치(Connect-L) 세 가지 크기를 제공합니다.
- **Connect-W**: Wide 디스플레이로, Connect-S보다 가로가 긴 형태이며 주로 고급 차종에 탑재됩니다. 차량 상태에 따라 세로 높이가 확장될 수 있으며, 이 확장형이 Connect-W-Extended입니다.
  - **Connect-WF**: Waterfall이라 불리며, Wide 대비 작은 해상도의 디스플레이입니다. 크기는 작지만 차량 내 핵심 기능을 제공합니다.

[이미지: display-layout-3]

[이미지 위주 페이지: 원문 참조]


---

## Colors

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/colors

Color는 하나의 제품으로써 브랜드 아이덴티티를 유지하기 위한 도구이자, 고객의 혼란을 최소화 하기 위해 색상 대비를 통해 소프트웨어 내에서 명확한 정보의 위계를 표현할 수 있어야 합니다.

> 업데이트 구분
> 🟠 기존 업데이트
> 🔵 신규 추가

---

### Basic Color

Scale Token은 프로덕트의 일관된 색상 체계를 정의하는 표준화된 컬러 토큰입니다. 특별한 시맨틱한 성격이나 맥락을 갖고있지 않은 경우에 사용하며, 그 어떤 경우에도 Scale Token을 사용할 수 있습니다.

#### Grayscale

가장 기본적인 흑백 컬러로 투명도가 없는 색상입니다.

[이미지: Color1]

#### Alpha Color

투명도를 가진 색상으로, 컬러의 투명도를 조절하여 주로 배경이나 다른 요소들과 조합에 사용됩니다.

##### White Alpha

[이미지: Color2]

##### Gray Alpha

[이미지: Color3]

#### Static Color

라이트, 다크 테마에 영향을 받지 않으며, 항상 raw값을 유지하는 컬러입니다. 주로 Overlay 위에 올라가는 요소에 사용됩니다.

##### Light

[이미지: Color4]

##### Dark

[이미지: Color5]

---

### System Color

#### Background

콘텐츠와 정보를 감싸고 있는 Background 컬러입니다. Z축의 가장 하위에 위치합니다.

[이미지: Color6]

#### Button

[이미지: Color7]

#### Controller

[이미지: Color8]

#### Climate

[이미지: Color9]

#### Dropdown

[이미지: Color10]

#### Dim

[이미지: Color11]

#### Driving

[이미지: Color12]

#### Fields

[이미지: Color13]

#### Gleo AI

[이미지: Color14]

#### Icon

[이미지: Color15]

#### Informative

[이미지: Color15]

#### Keyboard

##### Normal

[이미지: Color16]

##### Static

[이미지: Color17]

#### Media

[이미지: Color18]

#### Overlay

[이미지: Color19]

#### Phone

[이미지: Color20]

#### Regulation

[이미지: Color21]

#### Surface

[이미지: Color22]

#### Switch

[이미지: Color23]

#### Text

[이미지: Color24]

[이미지 위주 페이지: 원문 참조]


---

## Typography

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/typography

##### 폰트는 중립적인 형태와 균일한 수준의 시인성을 확보할 수 있는 서체를 사용해야 합니다.

### Operation guideline

- 정보의 위계에 따라 컴포넌트 간의 일관된 사이즈를 적용하는 것을 권장합니다.
- 추가 정보를 위한 서브 텍스트는 위계를 구분하여 사용하도록 권장합니다.
- 각 브랜드는 아이덴티티에 따른 폰트를 지정할 수 있으나 안정성을 고려하여 캐릭터가 강조된 폰트를 적용하는 것을 지양합니다.
- Android 접근성 가이드라인에 따르면, 최소 폰트 크기는 12sp 이상을 권장합니다. (12sp ≈ 12dp → 12 × (160dpi / 160) = 12px)
  - 가독성을 위해 일반적으로 1.5배 이상 사용하는 것이 권장되며, 약 18~20px 정도의 높이를 확보하는 것이 안전합니다.
  - 실제 픽셀 기준 최소 높이: 18px 이상

### Material

System UI에 적용된 폰트 설치하기

- Language Support : Korean, English
- Sources : [Asta Sans Font](https://fonts.google.com/specimen/Asta+Sans)

---

### Font Hierarchy

#### Headline

[이미지: Headline.svg]

#### Title

[이미지: title.svg]

#### Body

[이미지: title.svg]

#### Label

[이미지: title.svg]

[이미지 위주 페이지: 원문 참조]


---

## Interaction

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/interaction

### Tension

기본적인 Interaction을 구성하는 Tension 값입니다.

#### Easing

[이미지: Motion1.svg]

#### Durations

- **Short durations** : These are used for small utility-focused transitions.
- **Medium durations** : These are used for transitions that traverse a medium area of the screen.
- **Long durations** : These durations are often paired with Emphasized easing. They're used for large expressive transitions.

[이미지: Motion2.svg]

### Gestures

| Types | Description |
| --- | --- |
| Pressed | Vehicle Setting, Climate, Apps 선택 시 각 버튼과 연결된 영역 실행/종료 |
| Long Pressed | Vehicle Setting, Climate, Apps 2-3초 홀드 시 편집 활성화 |
| Swipe | Pressed 후 Left/Right/Top/Bottom에 대한 이동 값이 있을 경우 부여된 값 호출, 그 외에는 모두 Cancel 처리되며, 일정 속도 이하는 Drag 처리 됩니다.   예)  - Window Bar Swipe Down: 앱 종료 - Climate 숫자 Swipe Left/Right: 공조 온도 상/하 조절 |
| Drag & Drop | Pressed 후 Left/Right/Top/Bottom에 대한 이동 값이 있을 경우 부여된 값 호출, 그 외에는 모두 Cancel 처리되며, 일정 속도 이상은 Swipe 처리 됩니다.   예)  - Window Bar Swipe Down: 앱 종료 - Climate 숫자 Swipe Left/Right: 공조 온도 상/하 조절 |

### Interaction

#### Button interaction

버튼 및 터치 가능한 요소는 모두 shrink effect가 적용되었습니다.

**Step1. Enabled** : This is the default state of a component before it is touched.
**Step2. Pressed** : Change to scale down(95%) + background color by Mode.

- Add Background : Included a background with effect, and button should add different background color
- Cancel : If customers move out from touch area, cancel release

**Step3. Release** : When finger point is pressed out in touch area, release the interaction such as App Release, Change state...etc

[이미지: Interaction1.svg]

#### Window interaction

각 window 또한 사용자의 니즈에 맞춰 조작 시 일관성 있는 effect가 적용되었습니다.

##### Drag start

Window bar를 누르기 시작하면, 수축이 시작됩니다.

[이미지: Interaction2.svg]

##### Drag end

Window bar를 누르기 종료 시, 수축이 사라집니다.

[이미지: Interaction3.svg]

##### Reorder(앱 이동)

다른 window width의 translateX(50%) 이상 이동 시 window 바로 이동

[이미지: Interaction4.svg]

##### Close(앱 종료)

0 ~ 50% 사이 Y축으로 drag할 경우 (\*Window height 100% 기준, y축으로 이동)

[이미지: Interaction5.svg]

51 ~ 100% 사이 Y축으로 drag할 경우

[이미지: Interaction6.svg]

0 ~ 59%(기존 74%) 이내 drag 종료할 경우 이벤트 복귀
60%(기존 75%) 이후 drag end할 경우 window 종료

[이미지: Interaction7.svg]

#### End of scoll

- \*\* Bounce Effect \*\* : In order to awareness, we provide the bounce effect in end area (ex Page, Menu, Carousel, List...etc)
- \*\* Range \*\* : The maximum allowable range of overscroll is approximate.
  2/5 of the total scroll area horizontally or vertically.

[이미지: Interaction2.svg]


---

## Animation

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/animation

사용자에게 상태 설명을 위한 목적에서 animation이 사용됩니다.

[Download Link](https://document.pleos.ai/assets/files/Loading-a564bf9ddfbe2fa3c9640ec7708d9e1d.zip)

[이미지: Loading.svg]

### Properties

| Options | Description |
| --- | --- |
| Linear | 정적인 로딩이 필요한 상황에서 사용 |
| Tension | 페이지 로딩 등 로딩 시간이 다소 길어지는 상황에서 사용 |


---

## Shadow

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/shadow

### Types

#### Level 1

가장 낮은 elevation을 가진 콘텐츠에 사용됩니다.

[이미지: Shadow1.svg]

**Shadow 1-2**

- X: 0
- Y: 6
- Blur: 24
- Spread: 0
- Color:#14000000 (8%)

#### Level 2

Level 1보다 높은 elevation을 표현할 때 사용합니다.

[이미지: Shadow2.svg]

**Shadow 1-1**

- X: 0
- Y: 8
- Blur: 32
- Spread: 0
- Color: #29000000 (16%)

**Shadow 1-2**

- X: 0
- Y: 2
- Blur: 2
- Spread: 0
- Color: #14000000 (8%)

#### Level 3

가장 상위 elevation을 가진 콘텐츠에 사용됩니다.

[이미지: Shadow3.svg]

**Shadow 1-1**

- X: 0
- Y: 20
- Blur: 40
- Spread: 0
- Color: #3D000000 (24%)

**Shadow 1-2**

- X: 0
- Y: 2
- Blur: 4
- Spread: 0
- Color: #33000000 (20%)


---

## Iconography

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/contents-creation/iconography

##### 아이콘은 누구나 직관적으로 인지할 수 있어야 합니다.

### Creation guideline

**1. Grid system** : 기본 사이즈(48 x 48dp)를 기준으로 작성된 그리드 시스템으로 제작합니다.
**2. Style** : 시인성 확보를 위해 상황에 따라 Filled/Outlined 2가지 타입으로 사용할 수 있습니다.
**3. Variant** : 1벌을 제작 후 필요한 사이즈로 조정하여 디자인을 합니다.

[이미지: Icon1.svg]
[이미지: Icon2.svg]

### Icon Types

[이미지: Icon3.svg]

[이미지 위주 페이지: 원문 참조]


---

## App Icon

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/contents-creation/app-icon

##### App Icon은 차량 내 제공할 서비스의 대표 이미지로, GNB, App library, App Market 등에서 사용됩니다.

### App Icon Design Principles

1. **Grid system**: 그리드 시스템을 참고하여 제작합니다.
2. **Layer**: Foreground(로고 및 주요 그래픽)와 Background(배경)를 분리하여 제작합니다.
3. **Style**: Pleos Connect OS의 차량 브랜드별 디자인 정책에 따라 아이콘 스타일을 적용합니다.

[이미지: app_icon_grid_system]

### Adaptive Icon Structure

Adaptive Icon은 Foreground(로고 및 주요 그래픽)와 Background(배경) 레이어로 구성되며, 시스템 마스킹을 통해 다양한 형태로 표시됩니다.
Pleos Connect OS의 차량 브랜드별 아이콘 스타일도 Android Adaptive Icon 형식으로 제작합니다.

자세한 제작 규칙은 [Adaptive Icon Design Guide](https://developer.android.com/develop/ui/views/launch/icon_design_adaptive)를 참고하세요.

[이미지: app_icon_masking_sample]

### Design Resources

App Icon 제작을 위한 Grid System과 차량 브랜드별 마스킹 스타일을 확인할 수 있는 Figma 템플릿을 제공합니다.
해당 템플릿은 Pleos Connect UI Design Guide의 'Pleos Connect Design Assets'에서 다운로드할 수 있습니다.

[Pleos Connect UI Design Guide > Design Resources Download](https://document.pleos.ai/docs/connect/guide/docs-design/Introduction/intro#design-resources-download)


---

## 3D Assets

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/foundations/contents-creation/3d-contents

Pleos Connect는 직관적인 사용성을 위해 3D model data를 활용하는 것을 권장합니다.
[Go to Sample](https://www.figma.com/design/FYc6zTha14GLPvqsb1zmkD/%5BInternal%5D-Connect-2.0-Design-Kit(WIP)?node-id=29874-25562)

### 3D Model

- Lowpoly 상태의 차량 데이터 필요

  - 내외장 포함 3Ds Max 기준 30 MB, FBX 기준 6 MB
  - Catia, Alias 같은 도면 데이터에서 추출된 Highpoly 데이터일 경우, Retopology 진행
  - Material, object 최적화 하여 Drawcall 최소화 작업
- 3D Data Format

  - glTF(gl Transmission Format)
  - 개발사: Khronos group 3D formats working group(Rhino3D)
  - 3D 모델 정보를 JSON 형식으로 저장하는 3D 파일 형식

### Engine

- Google Filament(link [Filament google](https://github.com/google/filament), [Filament git](https://google.github.io/filament/))
- PBR 엔진으로써 모바일 우선이지만 멀티 플랫폼을 지원

### Properties

- glTF animation : Naming, 속성 값으로 컨트롤 되며 해당 naming은 차량 특성에 따라 변경 가능

  - 3D Tool에서 animation key setting 후 export
  - glTF의 animation 값을 control하거나, glTF의 rotation 값 변경으로 animation 가능
- Part namaing rule : 각 Part 별 컨트롤에 영향이 없도록 정확한 naming을 부여

### Use cases

#### Climate

공조를 버튼이 아닌 실내 환경 모델링 이미지를 통해 좌석별 편리한 공조 조작 경험을 제공할 수 있습니다.

**Light**

[이미지: Climate1]

**Dark**

[이미지: Climate2]

#### Steering wheel

3D 소스 직관적으로 인지될 수 있도록 필요한 영역을 클로즈업하여 사용할 수 있습니다.

[이미지: 3D1]

#### Seat Position

3D 소스와 함께 버튼을 활용한 조작을 연동할 때 사용할 수 있습니다.

[이미지: 3D2]

---

### Appendix

**glTF Part Naming\_Exterior**
Connect 모델링별 조작 가능한 요소에 대해 안내합니다.

| 구분 | Exterior 구성요소 | Partname |
| --- | --- | --- |
| Base | Static Object | Body |
| Door | Door Front Left | Door\_F\_L |
| Door Front Right | Door\_F\_R |
| Door Rear Left | Door\_R\_L |
| Door Rear Right | Door\_R\_R |
| Sidemirror Right | Door\_Sidemirror\_L |
| Sidemirror Left | Door\_Sidemirror\_R |
| Door Front Left Window | Door\_F\_L\_Window |
| Door Front Right Window | Door\_F\_R\_Window |
| Door Rear Left Window | Door\_R\_L\_Window |
| Door Rear Right Window | Door\_R\_R\_Window |
| Trunk | Trunk | Trunk |
| Trunk shock absorber | Trunk\_shock\_absorber |
| Frunk | Frunk | Frunk |
| Frunk shock absorber | Frunk\_shock\_absorber |
| Wheel | Wheel Front Right | Wheel\_F\_R |
| Wheel Front Light | Wheel\_F\_L |
| Wheel Rear All | Wheel\_R\_RL |
| Charge | Charging Port | Charging\_Port |
| Roof | Sun roof | Sunroof\_Glass |
| Sunroof\_Shade |
| Roof glass | Roofglass\_Shade |
| Wiper | Wiper Front | Wiper\_F\_R |
| Wiper\_F\_L |
| Wiper Rear | Wiper\_R |

**glTF Part Naming\_Interior(Connect기준)**

| 구분 | Interior 구성요소 | Partname |
| --- | --- | --- |
| Base | Static Object | Body |
| Steering Wheel | Steering Wheel | Steering\_Wheel |
| Seat\_Front | Seat Front Left Top | Seat\_F\_L\_Top |
| Seat Front Left Low | Seat\_F\_L\_Low |
| Seat Front Right Top | Seat\_F\_R\_Top |
| Seat Front Right Low | Seat\_F\_R\_Low |
| Seat\_Rear | Seat Rear Top | Seat\_R\_Top |
| Seat Rear Low | Seat\_R\_Low |

**glTF Part Naming\_Lamp(Connect기준)**

| Lamp | Material name | Emissive | Alpha | etc |
| --- | --- | --- | --- | --- |
| Head lamp All Off(Default) | Head\_L\_All | 0 | 1 | Head\_L\_Light glTF Hide |
| Head lamp All On | Head\_L\_All | 10 | 1 | Head\_L\_Light glTF Show |
| Rear lamp Positioning Off(Default) | Rear\_L\_Pos | 0 | 1 | cell |
| Rear lamp Positioning On | Rear\_L\_Pos | 2 | 1 | cell |
| Rear lamp Stop Off(Default) | Rear\_L\_Stop | 0 | 0.5 | cell |
| Rear lamp Stop On | Rear\_L\_Stop | 5 | 1 | cell |
| Turn signal All Off(Default) | Turn\_S\_All | 0 | 0 | cell |
| Turn signal All On | Turn\_S\_All | 5 | 1 | cell |


---

## Driving view

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Experiences/driving-view

##### 드라이빙 뷰는 운전자를 위한 차량의 상태나 주행 정보를 제공하는 영역입니다.

### Anatomy

[이미지: Driving1.svg]

**1. Indicator**: Telltale, 도로표지판 정보, 에너지 정보, 자율주행 indicator, 회생제동 indicator(상황에 따라 노출), 도어 잠김/잠금해제 switch(상황에 따라 미노출) 등
**2. Driving information** : 변속단(상황에 따라 노출), 주행 속도, 에너지/퍼포먼스 게이지, 주행거리계, miniTBT(상황에 따라 노출), 드라이빙모드 등
**3. View contents** : 차량 외부 컨트롤, 근접 센서 및 주변 물체 표시, 차선 및 도로노면 표시 등

### Case Variation

[이미지: Driving2.svg]
[이미지: Driving3.svg]
[이미지: Driving4.svg]

[이미지 위주 페이지: 원문 참조]


---

## Gleo AI

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/Experiences/gleo-ai

Gleo AI는 사용자의 편리한 이동 경험을 챙겨주는 개인형 모빌리티 어시스턴트입니다.
사용자가 놓치면 안 되는 중요 사항들을 가이드 하며, 대화를 통해 사용자에게 필요한 답변을 제공하거나 기능을 실행합니다.

---

### Branding

#### Gleo (글레오)

Gleo는 16세기 인류 최초로 새로운 위성을 관측해 우주과학사의 전환점(태양 중심설)을 만든 갈릴레오를 모티브로 한 이름으로 기존 레거시를 넘어 sdv를 통해 새로운 모빌리티의 패러다임을 만들고 있는 42dot을 표현합니다.

#### Brand Narrative

갈릴레이는 목성과 위성의 움직임을 관찰하여 지동설을 입증했으며, 이를 통해 태양을 중심으로 한 행성의 공전을 밝혔습니다. 태양의 주위를 도는 공전의 움직임에서 착안하여 각 시간대에 따라 변하는 빛의 모습을 chrome과 logo의 메타포로 반영했습니다.

[이미지: amb_brand1.svg]

#### Gleo Logo

[이미지: amb_brand3.svg]

#### Gleo Chrome

Gleo brand concept에 따라 Gleo와 사용자가 발화하는 status별 chrome이 디자인되었습니다. chrome은 대화의 상태를 빛으로 구분하여 표현합니다.

[이미지: amb_brand2.svg]

### Status

- 음성인식 기반의 대화는 사용자의 발화를 들을 수 있는 상태와 Gleo가 응답하는 상태를 명확히 구분되어야 합니다.
- 이에 음성인식 및 응답 단계들을 구분하여 정의하고 있으며, 각 단계별로 사용자가 사용할 수 있는 상태가 명확히 구분됩니다.
- Gleo와 사용자간의 대화 상태를 아래와 같이 구분합니다.
- chrome은 status를 시각적으로 구분해주는 역할을 합니다.

[이미지: amb_status2.svg]

| Status | Description |
| --- | --- |
| Sleep | 대화 중이지 않고, Gleo가 대기 중인 상태 |
| Listening\_Passive | 음성 입력을 받기 시작하였으며, 사용자의 발화가 입력되기 전까지 대기 상태.  음성 미입력 2초 후 “운전석에서 듣는 중..."와 같이 음성 입력 좌석 메시지를 표시. |
| Listening\_Active | 사용자 발화하고 있으며, 음성 입력을 받는 중인 상태 |
| Thinking | 사용자의 발화가 종료되고, 입력된 음성 데이터를 처리 중인 상태 |
| Asking | 사용자의 발화에 대해 응답을 하기 전에 추가적인 입력을 받기 위해 되묻는 상태 |
| Speaking | 입력된 사용자 발화에 대한 최종 응답을 제공하는 상태 |
| Standby | 진행중이던 대화를 종료 후 Sleep으로 가기 직전, 혹시나 대화를 이어나갈 수 있는 가능성을 위해 응답 화면이 있을 경우 5초(응답 화면이 없을 경우 0초) 동안 응답 화면을 유지하는 대기상태로 제공되며, 대기시간 이후 자동으로 Sleep 되며 화면은 내려갑니다. |

#### Chrome

Chrome은 lottie file로 제작되어 status별 애니메이션되며 노출됩니다.

[이미지: amb_status2amb_status_chrome.svg]

### Response Display

#### Elevation

- Gleo는 OS layer 상 GNB 다음 하위 layer에 위치합니다.
- Gleo와 대화는 1) 사용자가 PTT나 wake word 등을 통해 대화를 진행하겠다는 의사를 밝히거나 2) proactive suggestion을 통해 사용자에게 필요한 제안을 전달하는 경우 시작됩니다.
- Gleo는 상위 layer에 위치하기 때문에 Gleo 영역이 표시되면 다른 앱 뷰를 일시적으로 가릴 수 있습니다.
- Elevation과 별개로 특정 조건이나 외부 이벤트에 따라 Gleo가 닫힐 수 있습니다.

[이미지: amb_display2.svg]

#### Layout

- Chrome: chrome 표시 상태로 대화 상태를 구분할 수 있습니다.
- Bubble: 사용자 발화를 표시하거나 Gleo 응답을 표시합니다.
- Gleo Panel:
  - Template: 대화 중 필요한 콘텐츠를 표시하는 영역입니다. list, card 타입이 있습니다.
  - Chips: 대화 상황에 따라 필요한 선택지를 추가 표시합니다.

##### Speaker Bubble + Panel

[이미지: amb_display3.svg]

##### Panel

[이미지: amb_display4.svg]

##### Event Conflict

[이미지: amb_display5.svg]

---

### Panel Interaction

#### Touch Interaction

- LIST의 list item을 touch하면 지정된 기능을 실행합니다.
- Chips의 버튼을 touch하면 지정된 기능을 실행합니다. ex. 그래/아니. 답장해줘. 다음 메시지 읽어줘
- Panel을 상단으로 touch 드래그, Panel 구성 요소 이외 영역을 Touch하면 대화를 종료합니다.
- Panel을 하단으로 touch 드래그하면 Panel 창이 Gleo AI App으로 전환됩니다.

[이미지: amb_touch_Interaction_1.svg]

#### Center Control Panel(CCP)

- CCP는 차량 IVI(차량용 인포테인먼트 시스템) 내에서 메뉴 탐색과 선택을 지원하는 조그 다이얼 형태의 하드웨어 조작 장치입니다.
- 사용자는 Gleo와의 대화 중이거나 Gleo AI 앱을 사용할 때 CCP를 통해 메뉴를 탐색하고 항목을 선택할 수 있습니다.

##### Interaction

- CCP 사용이 가능한 상황에서는 화면에 CCP focus가 표시됩니다.
- 이 focus는 현재 rotate, tilt, push 조작이 적용되는 대상을 나타냅니다.

[이미지: amb_ccp_1.svg]

##### CCP Focus in Conversation

- Gleo와의 대화 상황은 OS CCP 정책상 ‘팝업’ 상황과 동일하게 처리됩니다. 예를 들어, 이때는 tilt 조작이 제한되며, Gleo 화면이 다른 앱보다 우선적으로 포커스를 받습니다.
- CCP focus는 화면에 패널이 있고, 그 안에 터치 가능한 선택 대상이 있는 경우에만 표시됩니다.

[이미지: amb_ccp_2.svg]

##### CCP Focus in Gleo AI App

- Gleo AI 앱은 기본적으로 OS의 CCP 정책을 따릅니다.
- 앱 화면은 크게 상단 타이틀 영역, 하단 히스토리 영역(기본 영역)으로 나뉘며, 가장 최근의 대화 버블이 기본 포커스(default focus)로 설정됩니다.

[이미지: amb_ccp_3.svg]

---

### Panel Template

Gleo는 사용자의 질문에 응답 시 정의된 template별로 panel 응답이 내려오며, template은 display template과 하단에 chips로 구성될 수 있습니다.

[이미지: amb_panel_template_1.svg]

[이미지: amb_panel_template_2.svg]

#### Multi Screen Size

- 스크린 사이즈별로 아래와 같이 표시될 수 있습니다.
- wide의 경우 사용자의 발화 좌석을 인식하여 가까운 위치에 Gleo가 표시됩니다.

##### Standard 14.6

[이미지: amb_multiscreen_1.svg]

##### Standard 17

[이미지: amb_multiscreen_1.svg]

##### Wide Standard

[이미지: amb_multiscreen_2.svg]

##### Wide Extended

[이미지: amb_multiscreen_3.svg]

##### Waterfall

[이미지: amb_multiscreen_4.svg]

[이미지 위주 페이지: 원문 참조]


---

## Basic button

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/basic-button

##### 가장 기본적인 버튼으로 명확한 영역과 텍스트를 통해 고객의 행동을 유도할 수 있습니다.

[이미지: basic_anatomy.svg]

**1. Root** : 버튼을 감싸는 최상위 컨테이너 영역
**2. Label** : 버튼 레이블
**3. Icon**(Prefix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역
**4. Icon**(Suffix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역

---

### States

| State | Value | Description |
| --- | --- | --- |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Button이 제어불가능한 상태 |
| Loading | Boolean | True일 경우, Button내 Loader가 표시되고 제어불가한 상태가 됨 |

[이미지: basic_state.svg]

---

### Properties

| State | Value | Description |
| --- | --- | --- |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Button이 제어불가능한 상태 |
| Loading | Boolean | True일 경우, Button내 Loader가 표시되고 제어불가한 상태가 됨 |

[이미지: basic_hierarchy.svg]
[이미지: basic_size.svg]


---

## Toggle button

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/toggle-button

##### On/Off 가 가능한 버튼으로 다중 옵션을 제공해야하는 경우 상태 인지에 유용한 버튼입니다.

[이미지: toggle_anatomy.svg]

**1. Root** : 버튼을 감싸는 최상위 컨테이너 영역
**2. Label** : 버튼 레이블
**3. Icon**(Prefix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역
**4. Icon**(Suffix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역

---

### States

| State | Value | Default | Description |
| --- | --- | --- | --- |
| Toggled | Boolean | False | Toggle button이 활성화 된 경우 True으로 전환. Default 값은 False |
| Pressed | Boolean | False | Drag 가능한 window일 때 True로 전환 |
| Disabled | Boolean | - | True일 경우, Button이 제어 불가능한 상태 |

[이미지: toggle_state.svg]

---

### Properties

| State | Value | Description |
| --- | --- | --- |
| Floating | Boolean | 배경 스타일에 따라 Shadow 또는 컬로로 표현됩니다. |
| Size | Large, Medium, Small | Button의 사이즈 옵션을 선택하여 사용할 수 있습니다. |

[이미지: toggle_property.svg]


---

## Text button

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/text-button

##### 텍스트 버튼은 부가 정보에 대한 링크를 위해 주로 사용되며, 앞/뒤 아이콘을 통해 정보 전달력을 강화할 수 있습니다.

[이미지: text_button_anatomy.svg]

**1. Root** : 버튼을 감싸는 최상위 컨테이너 영역
**2. Label** : 버튼 레이블
**3. Icon**(Prefix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역
**4. Icon**(Suffix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역

---

### States

| State | Value | Description |
| --- | --- | --- |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Button이 제어불가능한 상태 |
| Loading | Boolean | True일 경우, Button내 Loader가 표시되고 제어불가한 상태가 됨 |

[이미지: text_state.svg]

---

### Properties

| State | Value | Description |
| --- | --- | --- |
| Size | Medium, Small | Button의 사이즈 옵션을 선택하여 사용할 수 있습니다. |

[이미지: text_property.svg]


---

## Icon button

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/icon-button

##### 아이콘 버튼은 어떤 고객이든 직관적으로 인지할 수 있는 경우에 사용합니다.

[이미지: icon_anatomy.svg]

**1. Root** : 버튼을 감싸는 최상위 컨테이너 영역
**2. Icon** : 아이콘을 통한 버튼의 정보를 제공하는 영역

---

### States

| State | Value | Default | Description |
| --- | --- | --- | --- |
| Pressed | Boolean | False | Drag 가능한 window일 때 True로 전환 |
| Disabled | Boolean | - | True일 경우, Button이 제어 불가능한 상태 |

---

### Properties

| State | Value | Description |
| --- | --- | --- |
| Style | Basic, Plain, Circle | Button의 스타일을 선택할 수 있습니다. |
| Size | Large, Medium, Small | Button의 사이즈 옵션을 선택하여 사용할 수 있습니다. |

[이미지: icon_type1.svg]
[이미지: icon_type2.svg]
[이미지: icon_type3.svg]


---

## Keypad

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/actions/keypad

##### 키패드는 숫자, 텍스트 등 서비스 사용 시 필요한 정보 입력을 위해 사용됩니다.

[이미지: keypad_anatomy.svg]

**1. Root** : 버튼을 감싸는 최상위 컨테이너 영역
**2. Label** : 버튼 레이블
**3. Icon**(Prefix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역
**4. Icon**(Suffix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역

---

### States

| State | Value | Default | Description |
| --- | --- | --- | --- |
| Pressed | Boolean | False | 터치 상태일 때 True로 전환 |
| Pressed | Boolean | False | Drag 가능한 window일 때 True로 전환 |
| Disabled | Boolean | - | True일 경우, Button이 제어 불가능한 상태 |

[이미지: keypad_state.svg]

---

### Properties

| State | Value | Description |
| --- | --- | --- |
| Keypad | Primary, Secondary, Special | 키보드의 역할에 따라 다른 타입을 선택할 수 있습니다. |
| Keyboard | Text, Voice | 키패드 또는 음성을 통한 텍스트 입력을 지원합니다. |

[이미지: keypad_numberic.svg]
[이미지: keypad_qwerty.svg]
[이미지: keypad_keyboard.svg]


---

## App library

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/app-library

##### App library는 고객이 앱 마켓을 통해 설치한 다양한 앱 서비스가 모여있는 영역입니다.

[이미지: library_anatomy.svg]

**1. Root** : 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Editing guide** : Editing mode에서 표시
**3. Complete button** : Control button 및 App 순서 변경 및 삭제 후 수정 내용을 저장
**4. Climate control** : Heat, ventilation 등 공조 컨트롤 버튼 영역
**5. App** : Embed, download app 영역
**6. Pagination** : 페이지당 Climate control 및 App 노출 가능 수가 넘어가는 경우 표시
**7. Divider** : Climate과 App 영역을 구분합니다.

---

### States

[이미지: library_state_apps.svg]
[이미지: library_state_climate.svg]

---

### Behaviors

#### Normal

[이미지: library_behavior.svg]

- Connect-S를 기준으로 1줄에 최대 7개까지 제공할 수 있습니다.
- Connect-S를 기준으로 2줄 이상 넘어가게 되면 좌/우 이동이 가능한 인디케이터가 표시됩니다.
- 상단 메뉴는 차량 제어, 하단 메뉴는 앱 실행을 위한 메뉴로 구성됩니다.

#### Editing

[이미지: library_edit_app.svg]
[이미지: library_edit_control.svg]

- 기본 앱이나 GNB에 존재하여 이동할 수 없는 앱들은 비활성화됩니다.
- 차량 제어 메뉴와 앱은 각각 지정된 영역으로만 이동이 가능합니다.
- Drag를 통해 각 영역의 메뉴를 제어할 때 이동할 수 없는 영역은 비활성화됩니다.
- 각 국가의 법규에 따라 Frost와 Defrost는 GNB에 필수 제공될 수 있습니다.

[이미지 위주 페이지: 원문 참조]


---

## Bar

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/bar

##### 디스플레이 내 앱 화면의 사이즈나 위치 조정 또는 콘텐츠 조작을 위한 요소입니다.

[이미지: Bar.svg]

### States

| State | Value | Description |
| --- | --- | --- |
| Variant | Light / Dark | App에서 정한 타입에 따라 전환 |
| Dragging | True / False | Drag 가능한 window일 때 'True'로 전환 |
| Disabled | True / False | True일 경우, handle이 안보이고 drag를 제어할 수 없는 상태 |
| Pressed | True / False | Handle을 잡고 drag를 시작할 때의 상태 |

---

### Types

| Types | Description |
| --- | --- |
| Window bar | App의 window 사이즈 조정을 위한 컴포넌트 |
| Scroll bar | App의 content를 탐색하기 위한 컴포넌트 |

---

### Window bar

[이미지: WindowBar_Anatomy.svg]

**1. Root** : Window bar 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Control** : Handle과 최소 drag 영역을 감싸는 컨테이너 영역
**3. Handle** : Drag 할 수 있는 컨트롤 요소

### Behaviors

[이미지: WindowBar_Light.svg]
[이미지: WindowBar_Dark.svg]

---

### Scroll bar

[이미지: ScrollBar_Anatomy.svg]

**1. Root** : Scroll bar 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Control** : Handle과 최소 drag 영역을 감싸는 컨테이너 영역
**3. Handle** : Drag 할 수 있는 컨트롤 요소

#### Behaviors

[이미지: ScrollBar_Light.svg]
[이미지: ScrollBar_Dark.svg]


---

## Controllers

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/controllers

Controllers는 최솟값과 최댓값을 가지며, 범위 내 volume, temporature 등과 같은 속성을 슬라이더 또는 클릭을 통해 조작 가능한 컴포넌트입니다.

[이미지: Controller.svg]

**States**

| State | Value | Description |
| --- | --- | --- |
| Pressed or Dragging | True / False | Handle 영역을 Pressed 시작할 때의 상태 |

**Options**

| Options | Value | Description |
| --- | --- | --- |
| Disabled | True / False | Min 또는 Max value에 도달했을 때 True로 전환 |
| MinValue | Number | 슬라이더에 지정하는 최솟값 |
| MaxValue | Number | 슬라이더에 지정하는 최댓값 |
| Step | Number | 슬라이더의 값을 제어하는 단계 지정 값 |

---

### Continuous Slider

#### Anatomy

[이미지: ContinuousSlider_Anatomy.svg]

**1. Root** : Slider 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Control** : Slider의 track과 handle을 감싸는 컨테이너 영역
**3. Handle** : 사용자가 드래그 할 수 있는 컨트롤 요소
**4. Track** : Handle이 움직일 수 있는 영역을 표현하는 요소
**5. Icon**(Prefix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역(Optional)
**6. Icon**(Suffix) : Label에 대한 시각적 부연설명을 하는 아이콘 영역
**7. Stepper** : 버튼을 눌러 handle을 제어할 수 있는 컨트롤 요소

---

#### Behaviors

##### Slider

**Basic**
일반적인 슬라이더로 사용됩니다.

[이미지: Slider_Basic.svg]

**Floating**
밝은 배경 또는 배경과 구분이 필요한 곳에서 사용됩니다.

[이미지: Slider_Float.svg]

##### Buttons

[이미지: Slider_Button.svg]

---

##### Volume

**Max** 100%

[이미지: Slider_Max.svg]

**High** 76~100%

[이미지: Slider_High.svg]

**Mid** 26~75%

[이미지: Slider_Mid.svg]

**Low** 1~25%

[이미지: Slider_Low.svg]

**Min** 0%

[이미지: Slider_Min.svg]

---

##### Optional

**Label** True/False

[이미지: Slider_Label.svg]

**Icon** True/False

[이미지: Slider_Icon.svg]

##### Control

[이미지: Slider_Control.svg]

---

### Centered Slider

#### Anatomy

[이미지: CenteredSlider_Anatomy.svg]

**1. Root** : Slider 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Control** : Slider의 track과 handle을 감싸는 컨테이너 영역
**3. Handle** : 사용자가 드래그 할 수 있는 컨트롤 요소
**4. Track** : Handle이 움직일 수 있는 영역을 표현하는 요소
**5. Stepper** : 버튼을 눌러 handle을 제어할 수 있는 컨트롤 요소

---

#### Behaviors

**Slider**

[이미지: Slider_State.svg]
[이미지: Slider_Control.svg]

**Track**

[이미지: Slider_Track.svg]
[이미지: Slider_Control.svg]

---

### Stepper

#### Anatomy

[이미지: Stepper_Anatomy.svg]

**1. Root** : Stepper 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Current value** : Current value와 Step 버튼을 감싸는 컨테이너 영역
**3. i++** : Stepper 값이 증가하는 컨트롤 요소
**4. i--** : Stepper 값이 감소하는 컨트롤 요소

#### Behaviors

[이미지: Stepper_State.svg]
[이미지: Stepper_Control.svg]

[이미지 위주 페이지: 원문 참조]


---

## Dropdown

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/dropdown

##### 메뉴의 이동이나 원하는 메뉴 속성 변경을 위한 요소입니다.

[이미지: Dropdown1.svg]

**1. Root** : Dropdown 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Label** : 버튼 레이블
**3. Prefix** : 메뉴 앞쪽에 제공되는 옵션 영역
**4. Suffix** : 메뉴 뒷쪽에 제공되는 옵션 영역
**5. Container** : Dropdown 요소를 담고 있는 영역

---

### States

| States | Value | Description |
| --- | --- | --- |
| Opened | Boolean | Dropdown 컨테이너가 열린 상태일 때 True로 전환 |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Dropdown이 제어 불가능한 상태 |

[이미지: Dropdown4.svg]
[이미지: Dropdown2.svg]

---

### Properties

| Property | Value | Description |
| --- | --- | --- |
| Prefix | App Icon, Icon | 메뉴 앞쪽에 제공되는 옵션 |
| Suffix | Switch, Icon | 메뉴 뒷쪽에 제공되는 옵션 |
| Size | Medium, Small | Dropdown, 컨테이너 아이템의 사이즈 옵션 |

[이미지: Dropdown3.svg]
[이미지: Dropdown6.svg]


---

## Text Field

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/fields/text-field

##### 텍스트, 비밀번호, 이메일 등 다양한 텍스트 정보 입력을 위한 필드입니다.

[이미지: text_anatomy.svg]

**1. Root** : 텍스트 필드 전체를 감싸는 최상위 컨테이너 영역
**2. Field** : 사용자가 정보를 입력하는 영역
**3. Cursor** : 텍스트를 입력할 수 있는 상태에 제공되는 커서 요소
**4. Place Holder** : 입력해야 하는 텍스트 필드 정보 맥락을 제공하는 라벨
**5. Suffix** : 텍스트 필드에 작성될 값의 맥락을 텍스트, 아이콘으로 표시하여 주는 영역(Optional)

---

### States

| State | Value | Description |
| --- | --- | --- |
| Value | String | Text field에 입력된 텍스트 정보 |
| Focused | True / False | 터치 또는 보이스 접근 시 text field 영역을 인식하고 있는 상태일 때 True로 전환 |
| Disabled | True / False | True일 경우, text field 입력 및 접근이 불가능한 상태. Focused state가 작동하지 않음 |
| Read Only | True / False | True일 경우, text field가 입력 값에 접근할 순 있지만 변경할 순 없는 상태. Focused state는 작동하는 상태 |
| Invalid | True / False | 입력 값이 유효한 값인지 아닌지 여부를 체크하는 상태 |

[이미지: text_status.svg]

### Typing Interaction

[이미지: typing_interaction.svg]

### Properties

| Property | Value | Description |
| --- | --- | --- |
| Type | Text / Tel / Url / Email / Password | Text field의 유형 |
| Size | Medium, Samll | Text field의 사이즈 |

[이미지: text_type.svg]


---

## Number Field

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/fields/number-field

##### 숫자 정보를 입력하기 위한 필드입니다.

[이미지: num_anatomy.svg]

**1. Root** : 텍스트 필드 전체를 감싸는 최상위 컨테이너 영역
**2. Label** : 사용자가 입력한 요소
**3. Cursor** : 텍스트를 입력할 수 있는 상태에 제공되는 커서 요소
**4. Field** : 사용자가 정보를 입력하는 영역

### States

| State | Value | Description |
| --- | --- | --- |
| Value | String | Text Field에 입력된 텍스트 정보 |
| Focused | True / False | 터치 또는 보이스오버로 접근시 Text Field영역을 인식하고있는 상태일 때 True로 전환 |
| Disabled | True / False | True일 경우, Text Field 입력 및 접근이 불가능한 상태. Focused state가 작동하지 않음 |
| Read Only | True / False | True일 경우, Text Field가 입력값에 접근할 순 있지만 변경할 순 없음 Focused state는 작동하는 상태 |
| Invalid | True / False | 입력 값이 유효한 값인지 아닌지 여부를 체크하는 상태 |

[이미지: num_states.svg]

### Properties

| State | Value | Description |
| --- | --- | --- |
| Size | Large, Medium | Text field의 사이즈 |

[이미지: num_size.svg]


---

## Global Navigation Bar(GNB)

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/gnb

##### Global Navigation Bar(GNB)는 Pleos Connect 이용에 필요한 필수 기능을 빠르게 실행하기 위한 영역입니다.

[이미지: gnb_anatomy.svg]

**1. Vehicle control for driver** : 운전석 및 보조석의 공조 제어 영역
**2. Application control** : 차량 내 설치된 앱 조작 영역
**3. Vehicle control for passenger** : 조수석 공조 제어 영역

---

### Control area

[이미지: gnb_vehicle_control.svg]

**1. Vehicle settings** : Vehicle settings 이동 (편집 불가)
**2. Climate control** : 이 영역을 탭하면 Climate panel이 표시되며, 같은 영역을 다시 탭하면 Climate panel이 닫힙니다.
**3. FR/RR Def** : FR/RR Def가 토글로 동작합니다. 터치 시 미니 팝업이 표시되며, 미니 팝업을 통한 FR/RR 개별 제어가 가능합니다.
**4. Editable slots** : 운전자가 원하는 앱을 이 위치에 두도록 편집할 수 있습니다. 운전석 시트 열선 (또는 시트 통풍) 앱으로 디폴트 구성되어 있습니다.
**5. Fixed app** : Navigation으로 고정되어 있습니다. Center 영역 내 위치 이동은 가능합니다.
**6. Editable slots** : 운전자가 원하는 앱을 최대 3개까지 등록할 수 있는 영역입니다. 앱 또는 Climate shortcut을 등록할 수 있습니다. 디폴트 구성은 Bluetooth Music, Phone, App Market 앱입니다.
**7. App library** : 아이콘을 터치 시 App library panel이 표시됩니다. App library panel이 열린 상태에서 동일 영역 터치 시 panel이 닫힙니다.
**8. Recent apps** : 최근 실행 앱 2개가 표시됩니다. 최신 앱이 왼쪽에 표시됩니다. Editable slots에 있는 앱은 recent apps로 표시되지 않습니다.
**9. Editable slots** : 보조석 탑승자가 원하는 앱을 이 위치에 두도록 편집할 수 있습니다. 디폴트 구성은 보조석 시트 통풍 앱, 보조석 시트 열선 앱입니다.
**10. Climate control** : 운전석 Climate control과 동일하게 동작합니다.
**11. Editable slots** : 보조석 탑승자가 원하는 앱을 이 위치에 두도록 편집할 수 있습니다. 디폴트 구성은 Auto 앱입니다.

---

### GNB 편집

[이미지: gnb_edit.png]

#### 편집모드 진입 방법

- GNB에서 진입하기: Editable slots 영역을 long-press 합니다.
- App library에서 진입하기: App library의 climate shortcut 또는 app icon을 long-press 합니다.

#### GNB의 편집 가능 영역

- Editable slots로 정의된 모든 영역에 climate shortcut 또는 app icon을 배치할 수 있습니다.
- Fixed app은 삭제 불가하지만 위치는 변경 가능합니다.
- Fixed app을 지원하지 않는 사양의 경우, Editable slots 1개가 확장됩니다.
- Recent apps 영역은 편집할 수 없습니다.

#### App library 편집 가이드

- Climate shortcut 영역: 이동만 가능하고 삭제 불가합니다.
  - 아이콘을 끌어 GNB로 이동시킬 수 있습니다.
  - GNB에 이미 배치된 shortcut은 uneditable 상태로 표시되며, 선택할 수 없습니다.
- App 영역: 삭제 및 이동 가능합니다.
  - 삭제 아이콘을 눌러 앱을 삭제할 수 있습니다.
  - 앱을 끌어 GNB로 이동시킬 수 있습니다.

---

### States

#### GNB menus

[이미지: gnb_prop1.svg]
[이미지: gnb_prop2.svg]
[이미지: gnb_prop3.svg]
[이미지: gnb_prop4.svg]

---

### On Screen Display(OSD)

##### 차량 스티어링 휠에 있는 버튼 조작을 통해 차량 제어 및 앱 제어를 할 때 나타나는 패널입니다.

- OSD 패널은 물리키 조작을 통해 IVI의 기능을 동작시킬 때 바뀐 상태를 표시합니다.
- Connect-S에서는 GNB 영역에 표시되고, Connect-W에서는 GNB 우측에 표시됩니다.
- OSD 패널이 나타나고 나서 추가적인 조작이 없을 경우 2초 후에 자동으로 사라집니다,

#### Case 1. Media & Sound control

운전자가 음악 또는 사운드를 조절 시 보조석에서 쉽게 음악 선곡이나 음량 조작 가능합니다.

[이미지: gnb_osd1.svg]
[이미지: gnb_osd2.svg]

#### Case 2. Vehicle control

Wiper, Light, Seat Position 등 제어 시 빠른 단계 조절 또는 부가 기능을 조작 가능합니다.

[이미지: gnb_osd3.svg]
[이미지: gnb_osd4.svg]
[이미지: gnb_osd5.svg]

#### Case 3. Distance control

주행 중 앞차와의 간격을 조절 가능합니다.

[이미지: gnb_osd6.svg]

[이미지 위주 페이지: 원문 참조]


---

## Indicator

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/indicator

### Anatomy

[이미지: Indicator1.svg]

**1. Root** : App bar 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Present Value** : 현재 선택된 value를 보여주는 영역
**3. Next, Prev Value** : 선택 가능한 value가 나열되어 보여지는 영역

#### States & Options

**States**

| State | Value | Description |
| --- | --- | --- |
| Present | Boolean | 현재 Value가 Present에 해당할 때 True로 전환 |

| Option | Value | Description |
| --- | --- | --- |
| Size | Wide / Narrow | Indicator 아이템의 사이즈 옵션 |

#### Behaviors

[이미지: Indicator2.svg]


---

## System Notification

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/notification/system-notification

##### OS 시스템에서 기본적으로 제공하는 알림입니다.

[이미지: system_noti.svg]

**1. Root**: Notification 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Imagery** : 상황에 따라 구성요소를 사용할 수 있는 영역
**3. Header** : 알림의 상황에 따라 피드백하는 설명 텍스트
**4. Action** : Description이 전달하는 내용을 확인했거나, 알림에 따른 엑션 유도 버튼

---

### Types

**1. App icon** : 앱의 알림을 받을 때 사용됩니다.
**2. Profile** : 전화 또는 문자에 대한 알림을 받을 때 사용되며, 앱 아이콘 정보를 나타낼 수 있습니다.
**3. Image** : 내내비게이션 정보 혹은 기타 알림을 받을 때 사용됩니다.
**4. Action** : Description이 전달하는 내용을 확인했거나, 알림에 따른 엑션 유도 버튼

[이미지: system_types.svg]

---

### Properties

| Properties | Value | Description |
| --- | --- | --- |
| Button Align | Single / Vertical / Horizontal | 버튼 정렬에 대한 옵션 |

[이미지: system_properties.svg]


---

## Vehicle Notification

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/notification/vehicle-notification

### Driving Notification

#### Anatomy

[이미지: noti_driving1.svg]

**1. Root** : 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Telltale** : 알림과 관련된 telltale icon
**3. Title text** : Notification의 주요 메시지
**4. Description text** : 부가 설명
**5. Guide image** : 부가 이미지

#### Properties

[이미지: noti_driving3.svg]

#### Position

[이미지: noti_driving4.svg]

[이미지 위주 페이지: 원문 참조]


---

## Page Navigation

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/page-navigation

##### 앱 서비스를 위해 필요한 주요 고객 경험 여정을 제공하기 위한 요소입니다.

[이미지: nav_anatomy.svg]

**1. Root** : Page Navigation 컴포넌트를 감싸는 최소단위 컨테이너 영역
**2. App icon** : 앱 아이콘을 나타내는 시각 요소
**3. Primary Menu** : 페이지 첫번째 카테고리를 그룹핑하는 Dropdown Menu
**4. Secondary Menu** : 페이지 두번째 그룹에 대한 Dropdown Menu(Optional)
**5. Suffix** : 페이지에 필요한 기타 메뉴에 대한 아이콘 메뉴, 앱의 목적에 따라 교체 가능

---

### Types

| Type | Value | Description |
| --- | --- | --- |
| Main | Boolean | 기본으로 제공 되는 메뉴 |
| Depth | Boolean | Depth 상태일 경우 True로 전환 (페이지 이동 시 Back 버튼이 나오는 경우) |

[이미지: nav_types.svg]

---

### Properties

| Properties | Value | Description |
| --- | --- | --- |
| Primary | App Icon, Suffix | Depth 상태일 경우 True로 전환(페이지 이동 시 Back 버튼이 나오는 경우) |
| Secondary | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Subtitle | Boolean | 영역의 부가 정보를 제공하기 위한 경우 True로 전환 |

[이미지: nav_main.svg]
[이미지: nav_depth.svg]


---

## System Popup

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/popups/system-popup

##### 앱 내에서 기본적으로 제공되는 팝업으로 서비스의 맥락에 맞게 Body 영역에 콘텐츠도 제공할 수 있습니다.

[이미지: pop_anatomy.svg]

**1. Root** : Popup 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Title** : Popup의 주요 텍스트가 표시되는 영역
**3. Description** : Dialog가 사용자에게 전달하고자 하는 주요 텍스트 (Optional)
**4. Confirm Action** : Popup이 전달하는 내용을 확인했거나, 다른 진입점으로 링크해주는 버튼
**5. Cancel Action** : Popup을 취소하거나 닫을 수 있는 버튼 (Optional)

---

### Properties

| Property | Value | Description |
| --- | --- | --- |
| Header | Icon, SubTitle, Description | True일 경우, Header 노출 |
| Body | Boolean | True일 경우 Header와 Body사이 설정된 콘텐츠 노출 |
| Size | Large, Medium | 팝업의 사이즈 종류 |
| Button Align | Single / Vertical / Horizontal | 버튼 정렬에 따른 옵션 |

[이미지: pop_size.svg]
[이미지: pop_align.svg]

### Body Contents

Body 영역은 사용자에게 전달하고자 하는 주요 팝업 아이템을 보여줄 수 있습니다.

[이미지: pop_contents.svg]


---

## Toast Popup

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/popups/toast-popup

##### 앱 서비스를 운영 중 필요한 일시적인 알림을 제공할 수 있는 요소입니다.

[이미지: Toast1.svg]

**1. Root** : 컴포넌트를 감싸는 최소단위 컨테이너 영역
**2. Toast message** : 알림 메시지

---

### Guideline

앱에서 필요한 시스템 메시지를 24dp 공간을 두고 Max로 제공할 수 있습니다.

[이미지: Toast2.svg]
[이미지: Toast3.svg]


---

## Tabs

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/segmented-menu

##### 2개 이상의 콘텐츠나 기능 옵션을 제공하기 위한 요소입니다.

[이미지: Segmented1.svg]

**1. Root** : Tabs 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Tab Item** : 사용자가 선택하여 전환시킬 수 있는 컨텐츠(페이지) 그룹
**3. Label** : Tab item에 대한 정보를 전달하는 라벨
**4. Selected indicator** : 사용자가 선택한 Tab item을 나타내는 요소
**5. Tab Container** : Tab item이 나열되는 영역

---

### States

| States | Value | Description |
| --- | --- | --- |
| Enable | Boolean | 터치 가능한 기본 상태 |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Selected | Boolean | Tabs 컴포넌트에서 입력되는 선택값 |

[이미지: Segmented7.svg]

---

### Properties

| Property | Value | Description |
| --- | --- | --- |
| Type | Box, Line | 2가지 스타일의 Tab 종류 |
| Fitted | Boolean | True일 경우, Tabs의 너비에 맞게 Tab들이 일정한 간격으로 늘어남   False일 경우, Left Aligned 정렬로 표현됨 |

[이미지: Segmented2.svg]
[이미지: Segmented4.svg]


---

## Checkbox

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/selections/checkbox

##### 차량 내 조작이 필요한 스위치 중 하나로 다중 선택이 가능한 옵션을 제공할 수 있습니다.

[이미지: Checkbox1.svg]

**1. Root** : Checkbox 컴포넌트를 감싸는 최소 단위 컨테이너 영역
**2. Control** : 체크박스의 상태를 나타내는 시각 요소
**3. Icon** : 체크박스에 대한 정보를 전달하는 아이콘

---

### States

| State | Value | Description |
| --- | --- | --- |
| Checked | Boolean | Checkbox가 체크된 상태일 때 True로 전환 |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, checkbox 제어 불가능한 상태 |

### Behaviors

[이미지: Checkbox2.svg]
[이미지: Checkbox4.svg]


---

## Radio button

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/selections/radio

##### 차량 내 조작이 필요한 스위치 중 하나로 독립적인 선택 옵션을 제공할 때 사용됩니다.

[이미지: radio1.svg]

**1. Root** : Checkbox 컴포넌트를 감싸는 최소단위 컨테이너 영역
**2. Control** : 체크박스의 상태를 나타내는 시각 요소

---

### States

| State | Value | Description |
| --- | --- | --- |
| Value (Selected) | Num / Null | Null: 선택되지 않은 Radio   Num: 선택된 Radio의 순서 값 |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Radio 제어 불가능한 상태 |
| Invalid | Boolean | True일 경우, 유효하지 않은 값임을 나타냄 |

### Behaviors

[이미지: radio2.svg]
[이미지: radio3.svg]


---

## Switch

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/selections/switches

##### 차량 내 조작이 필요한 스위치 중 하나로 On/Off를 통해 상태 변경이 필요할 때 사용됩니다.

[이미지: Switch1.svg]

**1. Root** : 스위치 컴포넌트를 감싸는 최소단위 컨테이너 영역
**2. Handle** : 스위치를 제어에 관여하는 시각요소
**3. Track** : 스위치의 상태를 나타내는 시각요소

---

### States

| State | Value | Description |
| --- | --- | --- |
| Checked | On/Off | Switch의 ON/OFF 여부에 따라 상태 전환 |
| Pressed | Boolean | 터치로 클릭 중인 상태일 때 True로 전환 |
| Disabled | Boolean | True일 경우, Button이 제어 불가능한 상태 |
| Invalid | Boolean | True일 경우, 유효하지 않은 값임을 나타냄 |

### Behaviors

[이미지: Switch2.svg]
[이미지: Switch3.svg]


---

## Spinner

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/spinner

##### Drag를 통해 여러 옵션 중 하나를 선택할 수 있는 기능을 제공할 수 있습니다.

[이미지: Picker1.svg]

**1. Root** : 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Selection Value** :현재 선택된 value를 보여주는 영역
**3. Contents** : 선택 가능한 value가 나열되어 보여지는 영역

### Properties

| Type | Description |
| --- | --- |
| Date | 날짜를 기반으로 선택 가능 |
| Time | 시간을 기반으로 선택 가능 |

[이미지: Picker4.svg]

### Interaction Guide

[이미지: Picker7.svg]


---

## Status bar

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/status-bar

##### Status bar는 차량 내 주행 및 주요 시스템 상태를 인지할 수 있는 영역입니다.

[이미지: statusbar_anatomy.svg]

---

### Anatomy

[이미지: driving_status.svg]

[이미지: driving_status.svg]

**1. SP0 Static** : 우선순위가 높으며, 항상 보여야 하는 고정 버튼 또는 상태를 표시합니다. Door Lock, Search 상태가 표시됩니다.
**2. SP0 Dynamic** : 우선순위가 높으며, 메뉴 중에 이벤트에 따라 발생하는 버튼 또는 상태를 표시합니다. Notification center, In-call status, privacy indicator가 표시됩니다.
**3. SP1 Dynamic** : 우선순위가 낮으며, 메뉴 중에 이벤트에 따라 발생하는 버튼 또는 상태를 표시합니다. Download, Update, WPC, Hi-Pass, Sound 상태가 표시됩니다.
**3-1. SP1 Dynamic Hotspot 버튼** : Hotspot 버튼은 네트워크 관련성이 높으므로 SP1 Static > network 우측에 배치됩니다.
**4. SP1 Static** : 우선순위가 낮으며, 항상 보여야 하는 고정 버튼 또는 상태를 표시합니다. Profile, bluetooth, network 상태가 표시됩니다.
**5. Time** : 실시간 시간을 시스템 설정에 따라 24h 혹은 12h로 표시합니다.
**6. PAB** : 보조석 에어백 활성 상태를 표시합니다. 단, 차량 하드웨어에 PAB 표시가 없을 때에만 IVI에 표시합니다.
**7. More Menu** : 영역 내 표시할 수 없는 더 많은 icon을 표시합니다.

> [INFO]
>
> - SP0과 SP1 영역의 시각적 구분을 위해, 40dp의 safe bounds를 right padding으로 포함합니다.
> - SP1 dynamic indicator 표시 영역이 부족할 경우, more 메뉴로 이동합니다.
> - SP0 dynamic indicator가 늘어날 경우, SP1 dynamic indicator 영역이 줄어듭니다.

---

### Menu states

#### Static icon

| State | Value | Default Value | Description |
| --- | --- | --- | --- |
| Pressed | Boolean | False | 터치로 클릭 중인 상태일 때 True로 전환 |
| Selected | Boolean | False | 활성화 된 경우 True으로 전환 |

[이미지: static_menu_state.svg]

#### Dynamic icon

| State | Value | Default Value | Description |
| --- | --- | --- | --- |
| Activate | Boolean | False | Static indicator의 왼편에 발생 순서대로 표시됨 |

[이미지: dynamic_menu_state.svg]

---

### Menu panels

상태 메뉴를 클릭하여 앱 서비스 제공에 필요한 부가 기능(장치 연결, 프로필 변경, 알림 등)을 제공할 수 있습니다.

| States | Value | Default Value | Description |
| --- | --- | --- | --- |
| Pressed | Boolean | False | 터치로 클릭 중인 상태일 때 True로 전환 |
| Selected | Boolean | False | 활성화 된 경우 True으로 전환 |

[이미지: menu_list_panel.svg]


---

## Toast

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/toast

### Anatomy

[이미지: Toast1.svg]

**1. Root** : 컴포넌트를 감싸는 최소단위 컨테이너 영역
**2. Toast message** : 알림 메시지

### Behaviors

**Padding**
App panel 내 side padding 24, bottom padding 24

[이미지: Toast2.svg]

**Max width**
Toast 전체 max width 792
Message text max width 712

[이미지: Toast3.svg]


---

## Widgets

> 원문: https://document.pleos.ai/docs/connect/guide/docs-design/components/widgets

### Anatomy

[이미지: Widget1.svg]

**1. Root** : 컴포넌트를 감싸는 최상위 컨테이너 영역
**2. Handle** : 아래로 끌어내려 위젯을 숨길 수 있는 영역
**3. Pagination** : 확인 가능한 위젯의 개수와 현재 확인 중인 위젯의 순서를 파악
**4. Title/Action area** : 위젯의 타이틀 및 조작 영역
**5. Information area** : 위젯이 제공하는 주요 정보 영역

### Properties

[이미지: Widget2.svg]
[이미지: Widget3.svg]

### Interaction

[이미지: Widget4.svg]

[이미지 위주 페이지: 원문 참조]
