# 01. Pleos Connect 앱 개발 시작하기

앱 개발 첫 단계(프로젝트 생성·SDK 신청·에뮬레이터·첫 API 호출) 원문 정리.

## 목차

- [Pleos Connect 앱 개발 시작하기](#pleos-connect-앱-개발-시작하기)
- [프로젝트 생성 및 SDK 사용 신청](#프로젝트-생성-및-sdk-사용-신청)
- [Pleos Connect Emulator 설치 및 실행](#pleos-connect-emulator-설치-및-실행)
- [Pleos Connect Emulator 설정](#pleos-connect-emulator-설정)
- [Pleos Connect Virtual Console 셋업 가이드](#pleos-connect-virtual-console-셋업-가이드)


---

## Pleos Connect 앱 개발 시작하기

> 원문: https://document.pleos.ai/docs/pleos-only/getting-started

이 문서는 Pleos Connect 앱 개발을 처음 시작하는 개발자를 위한 빠른 시작 가이드입니다.
Android Studio에서 프로젝트를 만들고 Pleos Connect Emulator를 실행한 다음, Vehicle SDK의 첫 번째 API를 호출합니다.

### 사전 준비

- Android Studio
- Android SDK API 34
- Android 프로젝트의 `minSdk` API 28 이상
- Pleos Connect Emulator system image

### 1. Android 프로젝트 만들기

Android Studio를 열고 **File > New > New Project**로 이동합니다.

1. **Templates**에서 **Car** 또는 **Automotive** 카테고리를 선택합니다. Android Studio 버전에 따라 카테고리 이름이 다르게 표시될 수 있습니다.
2. 기본 템플릿에서 **Empty Activity**를 선택합니다.
3. 프로젝트 이름과 저장 위치를 지정합니다.
4. 언어는 **Kotlin**을 선택하고 **Minimum SDK**는 Android API 28 이상으로 설정합니다.
5. **Finish**를 클릭해 프로젝트를 생성합니다.

Android Studio가 필요한 종속 항목을 다운로드하고 프로젝트를 빌드합니다. 프로젝트가 생성되면 `app` 모듈이 정상적으로 빌드되는지 먼저 확인하세요.

### 2. Pleos Connect Emulator 설치 및 구성

1. Android Studio에서 **Tools > SDK Manager > SDK Update Sites**로 이동한 뒤 **Add**를 선택합니다.
2. 아래 정보를 입력해 Pleos Connect system image 저장소를 추가합니다.
   - **Name**: `Pleos Connect System Image`
   - **URL**: 제공받은 Pleos Connect Emulator 다운로드 URL
3. **SDK Platforms**에서 **API 34**의 Pleos Connect system image를 선택하고 설치한 뒤, Android Studio를 재시작합니다.
4. **Tools > Device Manager > Create device**에서 **Automotive**를 선택하고 Pleos Connect 디바이스를 생성합니다.
5. AVD 고급 설정에서 **Boot option을 Cold boot**, **RAM을 4 GB**로 설정한 다음, API 34 Pleos Connect system image를 선택해 생성합니다.
6. Device Manager에서 생성한 AVD를 실행해 Pleos Connect Emulator를 시작합니다.

설치 화면, 버전별 메뉴 차이, 상세 옵션은 [Pleos Connect Emulator 설치 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator)을 참조하세요.

### 3. Vehicle SDK 가져오기

#### Maven 저장소 등록

프로젝트의 `settings.gradle.kts`에 Pleos Maven 저장소를 추가합니다.

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://nexus-playground.pleos.ai/repository/maven-releases/")
        }
    }
}
```

#### SDK 의존성 추가

`app/build.gradle.kts`의 `dependencies` 블록에 Vehicle SDK를 추가합니다.

```kotlin
dependencies {
    implementation("ai.pleos.playground:Vehicle:2.3.1")
}
```

이후 **Sync Now**를 선택합니다. 의존성을 찾지 못하면 Maven 저장소 접근 권한과 SDK 버전을 확인하세요. 자세한 내용은 [Importing Vehicle SDK](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/project-setup)를 참조하세요.

### 4. 차량 권한 선언

예제에서는 현재 도어 위치를 조회하므로 `CAR_DOORS` 권한을 선언합니다. `app/src/main/AndroidManifest.xml`에 추가합니다.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="pleos.car.permission.CAR_DOORS" />

    <application
        ...>
        ...
    </application>
</manifest>
```

API별 권한은 [Vehicle SDK permissions](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle-permission)에서 확인하세요.

### 5. 첫 번째 API 호출

`Vehicle` 객체를 생성하여 Vehicle service에 연결한 뒤, `getDoor()`로 도메인 객체를 얻은 후 도어 위치 조회 API를 호출합니다. 아래 예제는 Vehicle SDK API의 호출 흐름과 SDK 리소스 초기화/해제(`initialize()`, `release()`)만 보여주며, Android 앱 생명주기(`onCreate`, `onStart`, `onStop`, `onDestroy`)나 화면 구성 코드는 포함하지 않습니다.

```kotlin
val vehicle = Vehicle(context)
vehicle.initialize()

val door = vehicle.getDoor()
door.getCurrentDoorPosition(
    area = DoorArea(DoorValue.ROW_1_LEFT),
    onComplete = { pos: Int? ->
        // Use the current door position.
    },
    onFailed = { e: Exception ->
        // Handle the Vehicle SDK error.
    }
)

// Do not call release() here.
// Call release() when the app no longer needs Vehicle SDK (for example, in onDestroy()).
```

`onComplete`와 `onFailed`는 비동기 콜백이며, `vehicle.release()`는 콜백 처리 완료 후 또는 SDK를 더 이상 사용하지 않는 앱 생명주기 시점(예: `onDestroy`)에서 호출하세요.

### 6. 에뮬레이터에서 확인

1. Pleos Connect Emulator가 완전히 부팅되었는지 확인합니다.
2. Android Studio에서 앱 실행 대상 기기로 Pleos Connect Emulator를 선택합니다.
3. **Run**을 눌러 앱을 빌드하고 설치합니다.
4. 앱에서 현재 도어 위치 조회 결과가 처리되는지 확인합니다.

값을 읽지 못하면 다음 항목을 확인하세요.

- `minSdk`가 API 28 이상인지 확인합니다.
- `pleos.car.permission.CAR_DOORS`가 선언되어 있는지 확인합니다.
- SDK 의존성이 정상적으로 동기화되었는지 확인합니다.
- 에뮬레이터가 정상적으로 실행되었는지 확인합니다.
- `onFailed`에 전달된 예외 메시지를 확인합니다.

### 다음 단계

첫 API 호출을 확인한 후에는 다음 순서로 앱의 설계, 개발, 테스트, 배포를 진행하세요.

1. [Pleos Connect 앱 계획 및 설계 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/intro)에서 차량용 앱의 화면, 주행 중 사용성, 성능 및 개인정보 보호 기준을 확인합니다.
2. [Pleos Connect 앱 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/intro)에서 차량용 앱의 기능 구현 방법을 확인합니다.
3. [Pleos Connect SDK 및 API 문서](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/)에서 사용할 SDK를 선택하고 상세 API를 확인합니다.
4. [Vehicle SDK 권한](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle-permission), [Capability API](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/check-capability), [Get API](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/get-property)에서 실제 앱에 필요한 권한과 API 사용 방법을 확인합니다.
5. [Vehicle SDK Sample App 사용 가이드](https://document.pleos.ai/docs/pleos-only/tutorial/vehicle-sdk-sample-app)에서 Vehicle SDK를 사용하는 상세한 방법과 API 호출 흐름을 확인합니다.
6. [Pleos Connect Virtual Console 사용 가이드](https://document.pleos.ai/docs/pleos-only/app-test-guide/virtual-console-guide)에 따라 차량 상태와 앱 연동을 테스트합니다.
7. [Pleos Connect 앱 등록 및 배포 가이드](https://document.pleos.ai/docs/pleos-only/distribute/app-submission-guide)에 따라 앱을 검토하고 Pleos App Market에 제출합니다.


---

## 프로젝트 생성 및 SDK 사용 신청

> 원문: https://document.pleos.ai/docs/pleos-only/create-my-project

Pleos Connect 앱 개발을 시작하려면 [Pleos Playground](https://developers.pleos.ai)에 가입한 후 프로젝트를 생성해야 합니다.

### 회원 가입

Pleos Playground에서 회원 가입을 진행합니다. 이미 Pleos 계정이 있는 경우에는 이 단계를 건너뜁니다.

1. [Pleos Playground](https://developers.pleos.ai)에 접속합니다.
2. 우측 상단에 있는 **로그인**을 선택합니다.
3. 로그인 화면 하단에 **Pleos 계정 만들기** 버튼을 선택합니다.
4. 이용약관 동의 후 계정 정보를 입력합니다.
5. 가입이 완료되면 Playground에 로그인합니다.

### 프로젝트 생성

Pleos Connect 앱 개발 및 등록을 위한 프로젝트를 생성합니다. 프로젝트를 생성한 후 팀 멤버 추가 등 팀 관리 기능을 사용할 수 있고, SDK 사용 권한을 신청할 수 있습니다.

1. [Pleos Playground](https://developers.pleos.ai)에 로그인합니다.
2. 상단 GNB에서 **Workspace** > **My Project**를 선택합니다.
3. **프로젝트 생성**을 클릭합니다.
4. **프로젝트 생성** 화면에서 아래 정보를 입력합니다.
   - **프로젝트 이름**: 앱 이름 또는 앱 개발 과제의 이름을 입력합니다. 이 값은 나중에 변경 가능합니다.
   - **개발사명**: 소속 회사의 이름을 입력합니다.
   - **카테고리**: 개발하려는 앱의 카테고리를 선택합니다. 앱의 카테고리는 앱 개발 과정에서 필수적인 정보이므로 카테고리를 정의하고 시작하실 것을 권장합니다.
   - **애플리케이션 설명**: 개발하려는 앱에 대한 간단한 설명을 작성합니다.
5. **생성** 버튼을 클릭합니다.

### 프로젝트 정보 확인

프로젝트를 생성하고 나면 **프로젝트 정보** 화면에서 다음 정보를 확인할 수 있습니다.

- **프로젝트 이름**: 프로젝트 생성 단계에서 입력한 **프로젝트 이름**이 나타납니다. 이 화면에서 수정 가능합니다.
- **Application 상세**: 프로젝트 생성 단계에서 입력한 정보가 나타납니다.
- **키 정보** > **프로젝트 ID(Key)**: 앱 심사를 요청 시 Pleos App Market Console에 입력해야 하는 정보입니다.
- **SDK** > **클라이언트 ID**: 앱 등록 등 서버 연결을 요구하는 SDK를 사용할 때 필요한 정보입니다. (TextToSpeech, SpeechToText)
- **SDK** > **클라이언트 시크릿**: 앱 등록 등 서버 연결을 요구하는 SDK를 사용할 때 필요한 정보입니다. (TextToSpeech, SpeechToText)
- **테스트 정보** > **CRN**: Pleos Connect Emulator 2.1.0 버전 이후부터는 CRN 입력이 요구되지 않습니다.

### SDK 사용 신청

프로젝트에서 사용할 SDK에 대해 사용 권한을 신청합니다.

1. **프로젝트 정보** 화면 좌측 사이드바에서 **SDK**를 선택합니다.
2. 확장된 하위 메뉴에서 앱 개발에 사용할 SDK를 선택합니다.
3. SDK 상세 화면에서 각 기능의 상태 값을 확인합니다.

- **준비 중** 상태: API 제공 준비 중인 상태로 신청 버튼이 나타나지 않습니다. `Comming Soon` 배지가 표시됩니다.
  - FusedLocation, TextToSpeech, SpeechToText, Vehicle Control SDK가 이 상태에 해당합니다.
  - 해당 상태에서는 신청할 수 없으므로 **신청 필요** 상태로 변경된 후 진행하시기 바랍니다.
- **사용 가능** 상태: 신청 없이 사용 가능한 SDK이거나 신청이 승인되어 사용 가능한 상태입니다.
  - Vehicle State와 NaviHelper SDK가 이 상태에 해당합니다. 이 두 SDK는 신청 없이 바로 사용 가능합니다.
- **신청 필요** 상태: 신청해야 사용 가능한데 아직 신청하지 않는 상태입니다.
  - **신청**을 클릭하여 사용 권한을 신청합니다.
  - 신청 절차와 신청 상태에 따른 상태 값 변화는 하기 4번~5번 절차를 확인합니다.

4. 신청 화면에 필요한 정보를 입력한 뒤 신청을 제출합니다. 신청 후 승인 전까지는 **검토 중** 상태로 표시됩니다.
5. 승인될 때까지 대기합니다. 승인이 완료되면 **사용 가능** 상태로 표시되며, 이때부터 앱 개발에 사용하실 수 있습니다. 사용 신청을 했으나 신청이 반려된 경우에는 **반려됨** 상태로 표시됩니다.

> [INFO]
>
> **제휴 계약 필요** 상태로 표시된 항목의 경우에는 웹 사이트를 통한 신청 방법을 제공하지 않습니다. 서비스 계약 담당자에게 별도 문의하여 제휴 계약을 진행하신 후 사용 가능한 항목입니다.

#### 다음 단계

프로젝트 생성과 SDK 사용 신청을 마치면 [개발 환경 구성](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator)을 진행합니다.


---

## Pleos Connect Emulator 설치 및 실행

> 원문: https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator

### 소프트웨어 개발 환경

Pleos Connect SDK를 사용하기 위한 소프트웨어 개발 환경은 다음과 같습니다.

| 구분 | 버전 |
| --- | --- |
| Minimum Android SDK | **Android API 28 이상** |
| Gradle 버전 | 8.0 이상 |
| 개발 언어 | Java, Kotlin |
| Pleos Connect Emulator | 버전 2.1.0 |
| Pleos Connect Virtual Console | Pleos Connect Virtual Console를 지원하는 Pleos Connect Emulator 최소 버전: 2.1.0 |

> [WARNING]
>
> minSDK 버전은 Android API 28 이상이어야 합니다. 이는 차량 사이버 보안 요건(사이닝 키 로테이션·리니지 지원)에 따른 요구사항입니다.

### Pleos Connect Emulator

Pleos Connect Emulator는 Android Studio와 같은 Android 개발 도구에서 Pleos Connect 앱을 개발하고 검증할 수 있는 가상 실행 환경입니다. Android API와 Pleos Connect API를 함께 테스트할 수 있으며, 차량 제어 기능과 차량 컨텍스트 데이터를 에뮬레이션해 앱의 동작을 확인할 수 있습니다.

#### Installing the Pleos Connect Emulator

##### Prerequisite

1. Android Studio의 최신 버전을 설치합니다.
2. Android Studio는 [여기](https://developer.android.com/studio?hl=ko)에서 다운로드 가능합니다.
3. Android Emulator를 실행할 수 있는 [시스템 요구 사항](https://developer.android.com/studio/run/emulator?hl=ko#requirements)이 있는지 확인합니다.

##### Emulator를 Android Studio에 추가하기

> [INFO]
>
> Pleos Connect Emulator의 이전 버전이 설치되어 있다면 기존 버전을 삭제한 후 다음 설치 절차를 진행해 주시기 바랍니다.

1. Android Studio에서 **Menu** > **Tools** > **SDK Manager**를 선택합니다.
2. **SDK Update Sites** 탭을 클릭합니다.
3. 목록 좌측 상단에 있는 **+** 버튼을 클릭합니다.

4. 아래의 사항을 입력해 Pleos Connect SDK site를 추가합니다.

   - Name: Pleos Connect System Image
   - URL for Mac: `https://nexus-playground.pleos.ai/repository/raw-releases/connect-avd/2.x/crp-arm64-sys-img.xml`
   - URL for Windows/Linux: `https://nexus-playground.pleos.ai/repository/raw-releases/connect-avd/2.x/crp-sys-img.xml`

5. **OK** 버튼을 클릭합니다.
6. **SDK Platforms** 탭을 클릭합니다.
7. Android API Level 34에 있는 Pleos Connect system Image를 체크합니다.
8. 하단에 있는 **Show Package Details** 체크박스도 활성화합니다.
9. **OK** 버튼을 클릭하면 아래 그림과 같이 Pleos Connect Emulator image 설치가 진행됩니다.
10. 설치가 완료되면 **Finish** 버튼을 누르고 Android Studio를 재시작합니다.

#### Setting up the emulator

1. Android Studio를 시작합니다. (이전 단계에서 Android Studio를 재시작하지 않았다면 먼저 재시작합니다)
2. Pleos Connect Emulator를 이용하여 Android Virtual Device(AVD)를 생성합니다.
3. Android Studio에서 **Menu** > **Tools** > **Device Manager**를 선택합니다. 이전 버전의 Android Studio일 경우 **Menu** > **Tools** > **AVD Manager**를 선택합니다.
4. **Create device**를 클릭합니다. 이전 버전의 Android Studio일 경우 **Create Virtual Device**를 클릭합니다.
5. 왼쪽 **Form Factor**에서 `Automotive`를 선택합니다.
6. `Pleos Connect v2.1.0`을 선택하고 **Next** 버튼을 클릭합니다.
7. **Configure virtual device** 창에서 **Advanced Settings** 탭을 선택합니다. 이전 버전의 Android Studio일 경우 **Show Advanced Settings** 버튼을 클릭합니다.
8. **Startup** > **Default boot** 항목에서 `Cold`를 선택합니다. 이전 버전의 Android Studio일 경우 **Emulated Performance** 항목에서 **Graphics** 옵션을 `Hardware`로 설정하고 **Boot option** 항목에서 `Cold boot`를 선택합니다.
9. **Emulated Performance** 항목에서 **RAM** Size를 `4 GB`로 설정합니다.
10. 다시 **Device** 탭으로 이동하여 **Select system image** > **API** 항목에서 `API 34`를 선택한 후, 하단 테이블에 나타나는 `Pleos Connect v2.1.0 APIs`를 선택하고 **Finish** 버튼을 누릅니다.

#### Running on the emulator

- Android Studio에서 **Menu** > **Tools** > **Device Manager**에서 생성한 AVD를 실행합니다.

> [NOTE]
>
> 에뮬레이터의 실행 환경 설정은 [Pleos Connect Emulator 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator)을 참조하시기 바랍니다.

#### Troubleshooting

Android Emulator에는 알려진 여러 가지 문제점이 있으니 아래 링크를 참고하시기 바랍니다.
[Android Emulator의 알려진 문제 해결](https://developer.android.com/studio/run/emulator-troubleshooting?hl=ko)

Android Studio와 Gradle plugin에도 알려진 여러 가지 문제점이 있으니 아래 링크를 참고하시기 바랍니다.
[Android 스튜디오 및 Android Gradle 플러그인의 알려진 문제](https://developer.android.com/studio/known-issues?hl=ko)


---

## Pleos Connect Emulator 설정

> 원문: https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator

### AVD 설정

Pleos Connect Emulator는 Pleos Connect SDK를 사용해서 구현한 앱을 테스트할 수 있는 환경을 제공합니다.

실제 차량에 접근한 것처럼 테스트 해볼 수 있도록 Pleos Connect Virtual Console을 제공합니다.
셋업 방법은 [Pleos Connect Virtual Console 셋업 가이드](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator)를, 사용 방법은 [Pleos Connect Virtual Console 사용 가이드](https://document.pleos.ai/docs/pleos-only/app-test-guide/virtual-console-guide)를 참조하시기 바랍니다.

Pleos Connect Emulator를 설치한 후에 다음과 같이 AVD를 설정합니다.

> [INFO] AVD 그래픽 카드 권장/최소 사양
>
> Pleos Connect Emulator를 실행하려면 그래픽 카드가 필수입니다.
> 권장 사양과 최소 사양을 확인하세요.
>
> **권장 사양**
>
> - NVIDIA RTX 4060/5060 Laptop 8GB 이상
> - AMD Radeon RX 7700S/RX 7600M XT 8GB급 이상 외장 GPU
>
> **최소 사양**
>
> - NVIDIA GeForce RTX 3060 Laptop GPU 6GB 이상
> - AMD Radeon RX 6600M 8GB 또는 RX 6700S 8GB 이상

#### 언어 설정

Pleos Connect Emulator는 한글을 기본으로 사용합니다. 영어로 바꾸려면 에뮬레이터 UI 메뉴에서 언어를 변경합니다.

**차량 설정** > **일반 설정** > **언어/Language** 항목에서 `English`를 선택합니다.

### 마이크 접근 설정

SpeechToText(STT) 기능 테스트를 위해 에뮬레이터에서 마이크 접근을 설정합니다.

1. 에뮬레이터 패널에서 **Extended Controls** 화면을 엽니다.

2. **Microphone** 메뉴 화면에서 오디오 입력을 수신하도록 설정합니다.

> [INFO]
>
> 자세한 내용은 안드로이드 [공식문서](https://developer.android.com/studio/run/emulator-extended-controls?hl=ko#standalone-window)를 참조 바랍니다.

### Cold boot 설정

Pleos Connect Emulator 설치 후 AVD 디바이스를 추가할 때 `Boot options`를 반드시 `cold boot`로 설정해야 합니다. `quick boot`로 설정할 경우에는 VHAL을 이용할 수 없습니다. 설정 절차에 대한 상세한 설명은 다음 문서를 참고하여 주십시오.

- [Pleos Connect Emulator 설치 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator)

### AVD에서 Bluetooth 동글 연동

본 가이드에서 다루는 Pleos Connect Emulator 버전에서는 AVD Bluetooth 동글 연동을 지원하지 않습니다.


---

## Pleos Connect Virtual Console 셋업 가이드

> 원문: https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator

### Pleos Connect Virtual Console

Pleos Connect Virtual Console은 Pleos Connect Emulator 환경에서 차량 핵심 기능을 재현하고 시뮬레이션하는 개발·테스트 도구입니다.
앱 개발자는 실제 사용자 동작과 유사한 방식으로 차량 상태와 이벤트를 조작하여 다음 항목을 확인할 수 있습니다.

- 차량 속도, 도어, 조명 등 차량 상태 변경
- 주행 및 차량 이벤트 시나리오 재현
- 앱 화면에 반영되는 실시간 차량 상태 확인
- 차량 제어 명령의 정상 동작 확인
- 반복 테스트 및 데모 시나리오 실행

> [INFO]
>
> Pleos Connect Virtual Console를 지원하는 Pleos Connect Emulator 최소 버전은 v2.1.0입니다.
> Pleos Connect Virtual Console가 정상 동작하지 않을 경우 Pleos Connect Emulator의 버전을 확인하세요.

### Pleos Connect Virtual Console 실행 방법

Pleos Connect Emulator를 실행한 후 Pleos Connect Virtual Console를 사용하려면 다음 두 단계를 수행합니다.

1. 터미널에서 에뮬레이터의 8088 포트를 포워딩합니다.

   ```bash
   adb forward tcp:8088 tcp:8088
   ```
2. PC 브라우저에서 다음 주소를 엽니다.

   ```text
   http://127.0.0.1:8088/
   ```

### 주요 기능

Pleos Connect Virtual Console에서 기어, 스티어링 휠, 공조, 도어·윈도우 등의 차량 기능을 시뮬레이션할 수 있습니다.

[이미지: Pleos Connect Virtual Console의 주요 기능 시연]

#### Emulation Controls

Emulation Controls에서는 Domain별로 차량 기능을 찾아 상태를 설정하고, Pleos Connect Emulator와 앱의 반응을 확인할 수 있습니다. 왼쪽 Domain 목록에서 영역을 선택한 다음 원하는 기능 카드의 값을 변경할 수 있습니다.

[이미지: Pleos Connect Virtual Console의 Emulation Controls 화면]

| Domain | 설명 |
| --- | --- |
| Vehicle | 차량 알림의 표시 또는 숨김 상태를 시뮬레이션합니다. 알림 상태에 따른 Pleos Connect Emulator와 앱의 동작을 확인할 수 있습니다. |
| Motion | 기어, 브레이크, 스티어링 휠 각도와 주행 거리를 설정합니다. 스티어링 휠 열선 단계도 시뮬레이션할 수 있습니다. |
| ADAS | 크루즈 제어의 준비 및 활성 상태와 목표 속도를 설정합니다. 설정한 주행 보조 상태가 앱에 반영되는지 확인할 수 있습니다. |
| Convenience | 도어·창문·트렁크, 시트, 조명, 와이퍼와 같은 편의 장치의 상태와 동작을 시뮬레이션합니다. ETCS 시나리오와 글로브 박스도 제어할 수 있습니다. |
| Safety | 주차 브레이크, 안전벨트, ABS 등 차량 경고등의 꺼짐·켜짐·점멸 상태를 설정합니다. 경고 상태에 따른 화면 표시를 확인할 수 있습니다. |
| Energy | 주행 가능 거리, 배터리 용량과 충전 상태를 설정합니다. 충전 전류·목표 충전량·충전 포트 상태도 시뮬레이션할 수 있습니다. |
| Climate | 공조 전원, 온도, 풍량, 송풍 방향, 내기순환과 제상 상태를 설정합니다. 좌석 열선·통풍과 공기 청정 기능도 제어할 수 있습니다. |

Emulation Controls에서 제공하는 전체 카드와 설정 범위는 [Pleos Connect Virtual Console 사용 가이드](https://document.pleos.ai/docs/pleos-only/app-test-guide/virtual-console-guide)에서 확인하세요.

#### 테마 및 시점 변경

테마 메뉴를 통해 화면 상단의 주행 시각화 영역의 디자인을 변경할 수 있습니다. 테마 변경은 차량 테스트 기능에 영향을 주지 않습니다.

- 기어가 P 상태일 때 왼쪽 상단 테마 버튼을 통해 Highway 테마 또는 Forest 테마로 전환할 수 있습니다.
- Highway 테마에서는 오른쪽 상단의 First person 또는 Third person 버튼을 통해 1인칭 또는 3인칭 시점을 선택할 수 있습니다.

[이미지: Pleos Connect Virtual Console의 테마 및 시점 변경 시연]

### 문제 해결

Pleos Connect Virtual Console에 접속할 수 없는 경우 다음 항목을 순서대로 확인합니다.

- Pleos Connect Emulator가 실행 중인지 확인합니다.
- Pleos Connect Virtual Console를 지원하는 Pleos Connect Emulator 이미지를 사용하고 있는지 확인합니다.
- `adb devices` 명령으로 에뮬레이터가 ADB에 연결되어 있는지 확인합니다.
- `adb forward tcp:8088 tcp:8088`을 다시 실행합니다.
- PC의 8088 포트를 다른 프로세스가 사용하고 있지 않은지 확인합니다.
- PC 브라우저에서 `http://127.0.0.1:8088/`로 다시 접속합니다.
