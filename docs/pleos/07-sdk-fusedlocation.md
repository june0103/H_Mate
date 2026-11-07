# 07. SDK 사용 가이드 — FusedLocation SDK

차량 위치 정보 SDK 사용 가이드 원문.

## 목차

- [Introduction](#introduction)
- [Importing FusedLocation SDK](#importing-fusedlocation-sdk)
- [Declaring permission](#declaring-permission)
- [Initialization / Release](#initialization--release)
- [위치 데이터 접근](#위치-데이터-접근)
- [오류 처리](#오류-처리)


---

## Introduction

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/intro

### FusedLocation SDK 소개

FusedLocation SDK는 앱이 차량 위치 데이터를 수신할 수 있도록 API를 제공합니다. Pleos Connect 앱은 FusedLocation SDK를 통해 Pleos Connect의 FusedLocation Service와 연동됩니다.

[이미지: FusedLocation SDK architecture diagram — 3rd Party Application 안의 FusedLocation SDK → Pleos Connect OS의 FusedLocation Service; 그 아래 Android Automotive OS]

> [INFO] SDK 버전
>
> 이 가이드는 FusedLocation SDK v1.1.3을 기준으로 작성되었습니다.

### 제공 기능

FusedLocation SDK는 다음과 같은 위치 데이터 접근 기능을 제공합니다.

- 마지막으로 확인된 위치 1회 조회: `getLastKnownLocation()`
- 위치 업데이트 수신: `registerFusedLocationCallback()`
- 위치 업데이트 중지: `unregisterFusedLocationCallback()`
- FusedLocation SDK 지원 환경 확인: `isSupported()`
- 위치 접근이 차단된 경우 안내 팝업 표시: `showLocationAccessBlockedPopup()`
- 설정 영역 진입/이탈 알림 등록: `addProximityAlert()`
- 설정 영역 진입/이탈 알림 해제: `removeProximityAlert()`

`getLastKnownLocation()`은 새로운 위치를 계산하는 API가 아니라 현재 사용할 수 있는 마지막 위치를 반환하는 API입니다. 위치 데이터가 없으면 콜백에 `null`이 전달될 수 있습니다.

### FusedLocation SDK 지원 환경 확인

FusedLocation API를 사용하기 전에 `isSupported()`로 현재 실행 환경의 지원 여부를 확인해야 합니다.
지원되지 않는 환경에서는 API가 정상적으로 동작하지 않을 수 있으므로, FusedLocation 기능을 제공하지 않도록 처리해야 합니다.

### 진입점

`FusedLocation` 클래스가 SDK의 주요 진입점입니다. 일반적인 사용 순서는 다음과 같습니다.

1. `FusedLocation` 객체를 생성합니다.
2. `isSupported()`로 현재 실행 환경이 FusedLocation SDK를 지원하는지 확인합니다.
3. `initialize()`로 SDK를 초기화합니다.
4. `getLastKnownLocation()`으로 마지막 위치를 조회하거나 `registerFusedLocationCallback()`으로 위치 업데이트 수신을 시작합니다.
5. `unregisterFusedLocationCallback()`으로 위치 업데이트 수신을 중지합니다.
6. SDK 사용이 끝나면 `release()`로 리소스를 해제합니다.

패키지 구조와 전체 API 명세는 [FusedLocation API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/entry)에서 확인할 수 있습니다.

### 위치 데이터

위치 결과는 `LocationData`로 전달됩니다. `LocationData`는 위도, 경도, 고도, 진행 방향, 신뢰도 및 타임스탬프를 포함합니다.
필드의 타입과 상세 설명은 [LocationData API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/fused-location/ai.pleos.playground.location.fused.model/location-data/entry)를 참고하세요.

### 권한 및 접근 제약

위치 API를 사용하려면 `pleos.car.permission.FUSED_LOCATION` 권한을 선언해야 합니다. 이 권한은 SDK의 Dokka API Reference에서 각 위치 API에 요구 권한으로 정의되어 있습니다. 권한 선언 방법은 [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/declaring-permissions)을 참고하세요.

### Emulator setup

FusedLocation SDK는 Pleos Connect Emulator 환경에서 개발 및 검증합니다. 아래 순서로 개발 환경을 준비하세요.

1. [소프트웨어 개발 환경 요구사항 확인](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#소프트웨어-개발-환경)
2. [Pleos Connect Emulator 설치](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#installing-the-pleos-connect-emulator)
3. [AVD 생성 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#setting-up-the-emulator)
4. [Pleos Connect Virtual Console 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator)
5. [마이크 접근 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#마이크-접근-설정)
6. [Cold boot 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#cold-boot-설정)

### 다음 단계

1. [FusedLocation SDK 가져오기](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/project-setup)
2. [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/declaring-permissions)
3. [위치 데이터 접근](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/location-data-access)
4. [SDK 초기화 및 해제](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/initialization-and-release)
5. [오류 처리](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/error-handling)
6. [FusedLocation API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/entry)


---

## Importing FusedLocation SDK

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/project-setup

FusedLocation SDK를 사용하려면 Maven 저장소를 등록하고 앱 모듈에 SDK 의존성을 추가합니다.

### Maven 저장소 등록

프로젝트의 `settings.gradle.kts` 또는 `settings.gradle`에 FusedLocation SDK가 배포된 Maven 저장소를 추가합니다.

[탭: Kotlin DSL]

settings.gradle.kts

```kotlin
dependencyResolutionManagement {
    repositories {
        maven("https://nexus-playground.pleos.ai/repository/maven-releases/")
    }
}
```

[탭: Groovy]

settings.gradle

```groovy
dependencyResolutionManagement {
    repositories {
        maven {
            url "https://nexus-playground.pleos.ai/repository/maven-releases/"
        }
    }
}
```

### SDK 의존성 추가

앱 모듈의 `build.gradle.kts` 또는 `build.gradle`에 FusedLocation SDK 의존성을 추가합니다. 아래 예시는 SDK `1.1.3`을 기준으로 합니다.

[탭: Kotlin DSL]

build.gradle.kts

```kotlin
dependencies {
    implementation("ai.pleos.playground:FusedLocation:1.1.3")
}
```

[탭: Groovy]

build.gradle

```groovy
dependencies {
    implementation "ai.pleos.playground:FusedLocation:1.1.3"
}
```

의존성을 추가한 후 Gradle 프로젝트를 동기화합니다.


---

## Declaring permission

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/declaring-permissions

### 위치 데이터 접근 권한

FusedLocation API 호출에 필요한 권한은 [FusedLocation API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/entry)의 해당 API 상세 페이지에서 확인할 수 있으며, `pleos.car.permission.FUSED_LOCATION`이 정의되어 있습니다.

| 권한 | 보호 수준 | 사용 API |
| --- | --- | --- |
| `pleos.car.permission.FUSED_LOCATION` | `dangerous` | `getLastKnownLocation`, `registerFusedLocationCallback`, `unregisterFusedLocationCallback` |

### 권한 선언

FusedLocation SDK의 위치 정보 API를 호출하려면 다음 권한을 `AndroidManifest.xml`에 선언해야 합니다.

AndroidManifest.xml

```xml
<manifest ...>
    <uses-permission android:name="pleos.car.permission.FUSED_LOCATION" />

    <application ... />
</manifest>
```

권한이 허용되지 않은 상태에서 권한이 필요한 API를 호출하면 API가 정상적으로 동작하지 않을 수 있습니다. 권한 요청과 결과 처리는 앱의 권한 처리 흐름에 맞게 구현하세요.

### 위치 접근 차단 상태

차량의 현재 개인정보 보호 설정으로 위치 접근이 차단된 경우 `showLocationAccessBlockedPopup()`을 호출하여 사용자에게 접근 차단 상태를 안내할 수 있습니다.

이 API는 위치 접근을 허용하는 API가 아닙니다. 위치 데이터가 전달되지 않는 경우에는 콜백의 `null` 및 `onError(Exception)`을 함께 처리하고, 앱의 화면과 기능을 적절히 제한해야 합니다.

> [INFO]
>
> Android의 `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION`, `ACCESS_BACKGROUND_LOCATION`에 관한 동작과 포그라운드·백그라운드 위치 접근 정책은 이 SDK 문서에서 별도로 보장하지 않습니다. Google Fused Location SDK의 설명을 Pleos Connect의 동작 보장으로 해석하면 안 됩니다.


---

## Initialization / Release

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/initialization-and-release

### SDK 생명주기

`FusedLocation`은 애플리케이션 생명주기에 맞춰 초기화하고 해제해야 합니다.

| 단계 | API | 설명 |
| --- | --- | --- |
| 지원 환경 확인 | `isSupported()` | 현재 환경이 FusedLocation SDK를 지원하는지 확인합니다. |
| 초기화 | `initialize()` | `FusedLocation` 인스턴스를 생성한 후 SDK를 초기화합니다. |
| 위치 데이터 수신 | `getLastKnownLocation()`, `registerFusedLocationCallback()` | `initialize()`를 호출한 후 [위치 데이터 접근](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/location-data-access) 가이드를 참고하여 필요한 방식으로 위치 데이터를 조회하거나 위치 업데이트를 수신합니다. 각 API에 필요한 권한도 함께 확인해야 합니다. |
| 위치 업데이트 수신 중지 | `unregisterFusedLocationCallback()` | 더 이상 위치 업데이트가 필요하지 않을 때 등록한 리스너를 해제합니다. |
| 리소스 해제 | `release()` | SDK 사용이 끝나면 SDK 리소스를 해제합니다. |

`initialize()`를 호출한 경우에는 SDK 사용이 끝날 때 반드시 `release()`를 호출해야 합니다. `unregisterFusedLocationCallback()`은 위치 업데이트 리스너를 해제하는 API이고 `release()`는 SDK 자체를 해제하는 API이므로 서로 대체할 수 없습니다.

### 초기화 및 해제 예시

애플리케이션 범위에서 하나의 `FusedLocation` 인스턴스를 관리하는 예시입니다.

```kotlin
class MyApplication : Application() {
    lateinit var fusedLocation: FusedLocation
        private set

    override fun onCreate() {
        super.onCreate()

        // 1. 애플리케이션 생성 단계에서 인스턴스를 생성합니다.
        fusedLocation = FusedLocation(this)

        // 2. 지원 여부를 확인한 후 SDK를 초기화합니다.
        if (fusedLocation.isSupported()) {
            fusedLocation.initialize()
        } else {
            // 위치 관련 UI와 기능을 비활성화합니다.
        }
    }

    fun shutdownFusedLocation(listener: FusedLocationListener) {
        // 3. 등록된 위치 업데이트 리스너를 먼저 해제합니다.
        fusedLocation.unregisterFusedLocationCallback(listener)

        // 4. SDK 사용이 끝나면 SDK 리소스를 해제합니다.
        fusedLocation.release()
    }
}
```

`initialize()`를 호출한 후 위치 데이터 API를 사용할 수 있습니다. 위치 업데이트를 등록한 경우 `release()`를 호출하기 전에 `unregisterFusedLocationCallback()`으로 리스너를 해제하세요.
`release()`를 호출한 뒤에는 같은 인스턴스로 FusedLocation API를 호출하지 마세요. 다시 사용하려면 새 인스턴스를 생성하고 지원 여부를 확인한 후 초기화하세요.


---

## 위치 데이터 접근

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/location-data-access

### 마지막 위치 1회 조회

`getLastKnownLocation()`은 현재 사용할 수 있는 마지막 위치를 콜백으로 전달합니다. 새 위치를 계산하는 API가 아니기 때문에 계산해 놓은 위치 데이터가 없을 경우 `LocationData?`가 `null`일 수 있습니다.

### 위치 업데이트 수신

`registerFusedLocationCallback()`에 `FusedLocationListener`를 전달하면 위치가 변경될 때 `onChanged(LocationData?)`가 호출됩니다. 오류가 발생하면 `onError(Exception)`이 호출됩니다.

```kotlin
val listener = object : FusedLocationListener {
    override fun onChanged(location: LocationData?) {
        location ?: return
        // Use the location data.
    }

    override fun onError(exception: Exception) {
        // Handle the error.
    }
}

fusedLocation.registerFusedLocationCallback(listener)
```

### 위치 업데이트 중지

더 이상 업데이트가 필요하지 않으면 등록할 때 사용한 동일한 리스너를 `unregisterFusedLocationCallback()`에 전달합니다.

```kotlin
fusedLocation.unregisterFusedLocationCallback(listener)
```

각 API의 요구 권한은 [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/declaring-permissions)을 참고하세요.


---

## 오류 처리

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/error-handling

FusedLocation SDK 오류는 `FusedLocationListener.onError(Exception)` 콜백으로 전달됩니다. 앱은 예외를 기록하고, 위치를 사용할 수 없는 상태를 사용자에게 안내하거나 앱의 기능을 적절히 제한해야 합니다.

`onChanged(LocationData?)`의 인자가 `null`일 수 있으므로 위치 데이터를 사용하기 전에 null 여부를 확인하세요. `null`을 유효한 위치로 처리하지 마세요.

위치 접근이 차단된 경우에는 [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/FusedLocation/declaring-permissions)의 위치 접근 차단 상태 섹션을 참고하여 차단 상태 안내를 제공할 수 있습니다. 예외의 구체적인 종류나 재시도 가능 여부는 SDK가 전달한 예외와 실행 환경을 기준으로 판단하세요.
