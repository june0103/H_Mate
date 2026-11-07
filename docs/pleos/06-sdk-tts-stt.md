# 06. SDK 사용 가이드 — TextToSpeech / SpeechToText SDK

음성 합성(TTS)·음성 인식(STT) SDK 사용 가이드 원문.

## 목차

- [Introduction](#introduction)
- [Importing TextToSpeech SDK](#importing-texttospeech-sdk)
- [Declaring permissions](#declaring-permissions)
- [App registration](#app-registration)
- [Initialization](#initialization)
- [Set locale](#set-locale)
- [Start and stop synthesis](#start-and-stop-synthesis)
- [Event listeners](#event-listeners)
- [Error code](#error-code)
- [Introduction](#introduction)
- [Importing SpeechToText SDK](#importing-speechtotext-sdk)
- [Declaring permissions](#declaring-permissions)
- [App registration](#app-registration)
- [Initialization](#initialization)
- [Set locale](#set-locale)
- [Start and stop recognition](#start-and-stop-recognition)
- [Sending audio data](#sending-audio-data)
- [Event listeners](#event-listeners)
- [Error code](#error-code)


---

## Introduction

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/intro

### TextToSpeech SDK 소개

TextToSpeech SDK는 텍스트를 음성으로 합성하고 재생하기 위한 API를 제공합니다. 3rd party 앱은 TextToSpeech SDK를 통해 Pleos Connect의 Speech Service와 연동됩니다. 3rd party 앱이 텍스트의 음성 합성을 요청하면 Speech Service가 이를 처리하고, 합성된 음성을 차량의 오디오 시스템으로 재생합니다.

[이미지: TextToSpeech SDK 구조도 — 3rd Party Application 안의 TextToSpeech SDK가 Speak "Hello" 요청 → (Send Text) → Pleos Connect OS의 Speech Service (On-Device / Server)가 Play Audio → 그 아래 Android Automotive OS]

> [INFO] SDK 버전
>
> 이 가이드는 TextToSpeech SDK v3.3.1을 기준으로 작성되었습니다.

### 음성 합성 모드

TextToSpeech SDK는 다음과 같이 세 가지 음성 합성 모드를 제공합니다.

- `HYBRID`: 기본 모드입니다. 서버 기반 streaming TextToSpeech를 우선 사용하고, 네트워크 연결에 실패하면 SDK가 On-device 엔진으로 자동 전환합니다. 서버 기반 합성을 사용하려면 Pleos Playground의 My Project 자격 증명을 사용한 앱 등록이 필요합니다.
- `SERVER`: 서버 기반 streaming TextToSpeech만 사용합니다. 네트워크 연결과 앱 등록이 필요하며, 네트워크 연결에 실패하면 On-device 엔진으로 전환하지 않고 오류 이벤트를 전달합니다.
- `ON_DEVICE`: 차량의 On-device 엔진에서 Streaming TextToSpeech를 실행합니다. 네트워크 연결이 필요하지 않으며, 서버 기반 합성을 사용하지 않으므로 앱 등록이 필요하지 않습니다.

### 진입점

`ai.pleos.playground.tts.TextToSpeech` 클래스는 TextToSpeech SDK 기능에 접근하기 위한 entry point입니다. 생성자에 애플리케이션 `Context`와 실행 `Mode`를 전달하여 객체를 생성합니다. `Mode`를 생략하면 `HYBRID`가 적용됩니다.

일반적인 사용 순서는 다음과 같습니다.

1. `TextToSpeech` 객체를 생성합니다.
2. `initialize()`로 SDK를 초기화합니다.
3. `addEventListener()`로 이벤트 리스너를 등록합니다.
4. 필요한 경우 `setLocale()`로 음성 합성 언어/지역을 설정합니다.
5. 서버 기반 합성 모드(`HYBRID`, `SERVER`)를 사용하는 경우 앱 등록을 완료합니다.
6. 서버 연결이 필요한 모드에서는 `OnServerConnectionListener.onConnected()` 이후 `speak()`를 호출합니다.
7. 인식이 끝나면 `stop()`을 호출합니다.
8. SDK 사용이 끝나면 `removeEventListener()` 후 `release()`로 리소스를 해제합니다.

#### `TextToSpeech` 객체 생성 예시

[탭: Kotlin]

```kotlin
import ai.pleos.playground.tts.TextToSpeech
import ai.pleos.playground.tts.constant.Mode

val textToSpeech = TextToSpeech(applicationContext, Mode.HYBRID)
```

[탭: Java]

```java
import ai.pleos.playground.tts.TextToSpeech;
import ai.pleos.playground.tts.constant.Mode;

TextToSpeech textToSpeech =
        new TextToSpeech(getApplicationContext(), Mode.HYBRID);
```

### Emulator setup

TextToSpeech SDK를 사용하려면 Android Studio에 Pleos Connect Emulator를 설치하고 AVD를 설정합니다.

1. [소프트웨어 개발 환경 요구사항 확인](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#소프트웨어-개발-환경)
2. [Pleos Connect Emulator 설치](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#installing-the-pleos-connect-emulator)
3. [AVD 생성 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#setting-up-the-emulator)
4. [Pleos Connect Virtual Console 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator)
5. [마이크 접근 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#마이크-접근-설정)
6. [Cold boot 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#cold-boot-설정)

### 다음 단계

1. [TextToSpeech SDK 가져오기](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/importing-sdk)
2. [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/declaring-permissions)
3. [SDK 초기화 및 해제](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/initialization-and-release)
4. [이벤트 리스너](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/event-listeners)
5. [로케일 설정](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/set-locale)
6. [앱 등록](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/app-registration)
7. [음성 합성 시작 및 중지](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/start-and-stop)
8. [오류 코드](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/error-code)
9. [TextToSpeech API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/entry)


---

## Importing TextToSpeech SDK

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/importing-sdk

TextToSpeech SDK를 사용하려면 Pleos Maven 저장소를 등록하고 앱 모듈에 SDK 의존성을 추가합니다.

### Maven 저장소 등록

프로젝트의 `settings.gradle.kts` 또는 `settings.gradle` 파일에 Pleos Maven 저장소를 추가합니다.

[탭: Kotlin DSL]

settings.gradle.kts

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://nexus-playground.pleos.ai/repository/maven-releases/")
    }
}
```

[탭: Groovy]

settings.gradle

```groovy
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url "https://nexus-playground.pleos.ai/repository/maven-releases/"
        }
    }
}
```

### SDK 의존성 추가

앱 모듈의 Gradle 파일에 TextToSpeech SDK 의존성을 추가합니다.

[탭: Kotlin DSL]

app/build.gradle.kts

```kotlin
dependencies {
    implementation("ai.pleos.playground:TextToSpeech:3.3.1")
}
```

[탭: Groovy]

app/build.gradle

```groovy
dependencies {
    implementation "ai.pleos.playground:TextToSpeech:3.3.1"
}
```

Gradle 동기화를 완료한 후 [TextToSpeech 권한을 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/declaring-permissions)합니다.


---

## Declaring permissions

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/declaring-permissions

TextToSpeech SDK API를 사용하려면 앱의 `AndroidManifest.xml`에 `TTS_SERVICE` 권한을 선언해야 합니다.

AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="pleos.car.permission.TTS_SERVICE" />

    <application>
        <!-- ... -->
    </application>
</manifest>
```

이 권한은 SDK 초기화, 이벤트 리스너 등록, 앱 등록, 음성 합성 요청 등 TextToSpeech 서비스에 접근하는 API에 필요합니다.

> [WARNING]
>
> 권한을 선언하지 않으면 SDK 초기화나 API 호출이 실패할 수 있습니다. 오류 콜백에서 `PERMISSION_DENIED`가 전달되는 경우 앱의 manifest와 실행 환경에서 권한이 허용되었는지 확인하세요.

권한 선언을 마쳤으면 [SDK를 초기화](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/initialization-and-release)합니다.


---

## App registration

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/app-registration

서버 기반 음성 합성을 사용하려면 Pleos Playground에서 My Project를 생성할 때 발급받은 `Client ID`와 `Client Secret`으로 앱을 등록합니다. SDK 초기화가 완료된 후 `registerApp()`을 호출하세요.

> [INFO]
>
> `SERVER` 모드에서는 앱 등록이 필요합니다. `HYBRID` 모드에서도 서버 기반 합성을 사용하려면 앱을 등록해야 합니다. `ON_DEVICE` 모드만 사용하는 경우에는 등록하지 않아도 됩니다.

[탭: Kotlin]

```kotlin
textToSpeech.registerApp(
    clientID = clientId,
    clientSecret = clientSecret,
    listener = object : OnServerConnectionListener {
        override fun onConnected() {
            // 서버 기반 음성 합성을 요청할 수 있습니다.
        }

        override fun onFailed(errorType: ErrorType) {
            // 등록 또는 서버 연결 실패를 처리합니다.
        }
    }
)
```

[탭: Java]

```java
textToSpeech.registerApp(
        clientId,
        clientSecret,
        new OnServerConnectionListener() {
            @Override
            public void onConnected() {
                // 서버 기반 음성 합성을 요청할 수 있습니다.
            }

            @Override
            public void onFailed(ErrorType errorType) {
                // 등록 또는 서버 연결 실패를 처리합니다.
            }
        }
);
```

`onConnected()`가 호출된 이후 서버 기반 요청을 시작하세요.


---

## Initialization

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/initialization-and-release

`TextToSpeech` 객체를 생성한 후 `initialize()`를 호출해야 API를 사용할 수 있습니다. SDK 사용을 마치면 `release()`를 호출하여 연결과 내부 리소스를 해제합니다.

### Initialize the SDK

초기화 결과가 필요한 경우 `OnInitializeListener`를 전달합니다.

[탭: Kotlin]

```kotlin
import ai.pleos.playground.tts.listener.OnInitializeListener
import ai.pleos.playground.tts.constant.ErrorType

textToSpeech.initialize(object : OnInitializeListener {
    override fun onSuccess() {
        // TextToSpeech API를 사용할 수 있습니다.
    }

    override fun onFailure(errorType: ErrorType) {
        // 초기화 실패 원인을 처리합니다.
    }
})
```

[탭: Java]

```java
import ai.pleos.playground.tts.constant.ErrorType;
import ai.pleos.playground.tts.listener.OnInitializeListener;

textToSpeech.initialize(new OnInitializeListener() {
    @Override
    public void onSuccess() {
        // TextToSpeech API를 사용할 수 있습니다.
    }

    @Override
    public void onFailure(ErrorType errorType) {
        // 초기화 실패 원인을 처리합니다.
    }
});
```

결과 콜백이 필요하지 않으면 인자 없이 호출할 수 있습니다.

```kotlin
textToSpeech.initialize()
```

### Release the SDK

SDK를 더 이상 사용하지 않을 때 호출합니다. 등록한 이벤트 리스너는 해제한 후 `release()`를 호출하는 것을 권장합니다.

> [TIP]
>
> `TextToSpeech` 객체는 사용하는 컴포넌트의 수명주기에 맞춰 재사용하세요. 더 이상 필요하지 않거나 애플리케이션이 종료될 때는 `release()`를 호출하여 SDK 리소스를 해제해야 합니다.

[탭: Kotlin]

```kotlin
override fun onDestroy() {
    textToSpeech.removeEventListener(eventListener)
    textToSpeech.release()
    super.onDestroy()
}
```

[탭: Java]

```java
@Override
protected void onDestroy() {
    textToSpeech.removeEventListener(eventListener);
    textToSpeech.release();
    super.onDestroy();
}
```

`release()` 이후 SDK를 다시 사용하려면 새 객체를 생성하고 초기화 절차를 다시 수행하세요.


---

## Set locale

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/set-locale

`setLocale(locale: Locale)`는 음성 합성에 사용할 언어/지역(Locale)을 업데이트합니다.

### Set locale before synthesis

음성 합성 요청 전에 `setLocale()`를 호출해 합성 언어/지역을 먼저 설정합니다.

- `speak()` 호출 전에 설정한 locale이 해당 합성 요청의 기준으로 사용됩니다.
- `setLocale()`로 설정한 locale은 다음 음성 합성 요청의 언어/지역 기준으로 사용됩니다.

### Supported locales

지원 locale은 다음과 같습니다.

- `ko-KR`: 한국어
- `en-US`: 영어
- `fr-CA`: 프랑스어
- `es-US`, `es-MX`: 스페인어

### Default behavior when `setLocale()` is not called

`setLocale()`을 호출하지 않으면 SDK는 같은 앱에서 이전에 `setLocale()`로 설정한 locale 값이 있는지 먼저 확인합니다.

- 이전 설정 값이 있으면 해당 locale 값을 사용합니다.
- 이전 설정 값이 없으면 시스템 locale을 사용합니다.

### Example

[탭: Kotlin]

```kotlin
import java.util.Locale

textToSpeech.setLocale(Locale.KOREA)
```

[탭: Java]

```java
import java.util.Locale;

textToSpeech.setLocale(Locale.KOREA);
```


---

## Start and stop synthesis

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/start-and-stop

### Send a synthesis request

`speak()`에 읽을 텍스트를 전달하여 음성 합성을 시작합니다. 기본적으로 `isFinal`은 `true`이며, 전달한 텍스트를 하나의 완성된 요청으로 처리합니다.

[탭: Kotlin]

```kotlin
textToSpeech.speak("안녕하세요. 무엇을 도와드릴까요?")
```

[탭: Java]

```java
textToSpeech.speak("안녕하세요. 무엇을 도와드릴까요?", true);
```

여러 텍스트 조각을 하나의 발화로 전달할 때는 마지막 조각을 제외한 요청의 `isFinal`을 `false`로 지정합니다.

```kotlin
textToSpeech.speak("안녕하세요. ", isFinal = false)
textToSpeech.speak("TextToSpeech SDK입니다.", isFinal = true)
```

앱 등록이 필요한 모드에서는 `OnServerConnectionListener.onConnected()`가 호출된 후 요청하세요. 진행 상태와 결과는 [Event listeners](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/event-listeners)에서 확인할 수 있습니다.

### Stop synthesis and playback

진행 중인 음성 합성과 재생을 중지하려면 `stop()`을 호출합니다.

[탭: Kotlin]

```kotlin
textToSpeech.stop()
```

[탭: Java]

```java
textToSpeech.stop();
```

중지가 완료되면 등록된 `EventListener`의 `onStop()`이 호출됩니다.


---

## Event listeners

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/event-listeners

`EventListener`를 등록하면 음성 합성 요청의 준비, 시작, 완료, 중지, 음량 변화 및 오류 이벤트를 받을 수 있습니다.

| Callback | Description |
| --- | --- |
| `onReady()` | `speak()` 요청을 처리할 준비가 되었을 때 호출됩니다. |
| `onStart()` | 음성 재생이 시작될 때 호출됩니다. |
| `onUpdatedRms(rms)` | 재생 중 RMS(Root Mean Square) 값이 갱신될 때 호출됩니다. |
| `onDone()` | 요청한 음성 재생이 정상적으로 끝나면 호출됩니다. |
| `onStop()` | 진행 중인 재생이 중지되면 호출됩니다. |
| `onError(errorType)` | 처리 중 오류가 발생하면 호출됩니다. |

[탭: Kotlin]

```kotlin
private val eventListener = object : EventListener {
    override fun onReady() = Unit
    override fun onStart() = Unit
    override fun onUpdatedRms(rms: Double) = Unit
    override fun onDone() = Unit
    override fun onStop() = Unit

    override fun onError(errorType: ErrorType) {
        // 오류를 처리합니다.
    }
}

textToSpeech.addEventListener(eventListener)
```

[탭: Java]

```java
private final EventListener eventListener = new EventListener() {
    @Override public void onReady() {}
    @Override public void onStart() {}
    @Override public void onUpdatedRms(double rms) {}
    @Override public void onDone() {}
    @Override public void onStop() {}

    @Override
    public void onError(ErrorType errorType) {
        // 오류를 처리합니다.
    }
};

textToSpeech.addEventListener(eventListener);
```

리스너가 더 이상 필요하지 않거나 SDK를 해제하기 전에는 등록할 때 사용한 동일한 인스턴스를 제거합니다.

```kotlin
textToSpeech.removeEventListener(eventListener)
```

오류별 처리 방법은 [Error code](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/error-code)를 참고하세요.


---

## Error code

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/error-code

TextToSpeech SDK는 초기화, 앱 등록 및 음성 합성 중 발생한 오류를 `ErrorType`으로 전달합니다. 오류는 `OnInitializeListener`, `OnServerConnectionListener` 또는 `EventListener`에서 처리할 수 있습니다.

### ErrorType

| ErrorType | Description | Recommended action |
| --- | --- | --- |
| `AUDIO_ERROR` | 오디오 출력 또는 재생 처리에 실패했습니다. | 오디오 설정과 출력 장치 상태를 확인한 후 다시 요청합니다. |
| `INVALID_REQUEST_ERROR` | 요청 값이나 요청 상태가 올바르지 않습니다. | 텍스트와 호출 순서 등 요청 조건을 확인합니다. |
| `MIC_ERROR` | 오디오 입력 장치 처리 중 오류가 발생했습니다. | 실행 환경의 오디오 장치 상태를 확인합니다. |
| `NETWORK_ERROR` | 네트워크 통신에 실패했습니다. | 네트워크 연결을 확인하고 다시 요청합니다. |
| `NOT_SUPPORTED_API` | 현재 모드 또는 실행 환경에서 지원하지 않는 API입니다. | 선택한 `Mode`와 플랫폼 지원 범위를 확인합니다. |
| `ON_DEVICE_ENGINE_ERROR` | On-device 음성 합성 엔진에서 오류가 발생했습니다. | 엔진 상태를 확인하거나 `HYBRID` 또는 `SERVER` 모드를 검토합니다. |
| `PERMISSION_DENIED` | TextToSpeech 서비스 접근 권한이 없습니다. | manifest의 `TTS_SERVICE` 권한과 실행 환경의 권한 상태를 확인합니다. |
| `SERVER_INTERNAL_ERROR` | Speech Service 내부 오류가 발생했습니다. | 잠시 후 다시 요청하고, 반복되면 서비스 관리자에게 문의합니다. |
| `TIMEOUT` | 제한 시간 안에 요청을 완료하지 못했습니다. | 네트워크와 서버 상태를 확인한 후 다시 요청합니다. |
| `UNAUTHENTICATED` | 앱 인증에 실패했습니다. | Pleos Playground의 My Project 자격 증명 또는 UA 앱 정보와 접속 환경을 확인합니다. |
| `UNAVAILABLE_LANGUAGE` | 요청한 언어를 사용할 수 없습니다. | 지원되는 `Locale`로 변경합니다. |
| `UNKNOWN` | 분류되지 않은 오류가 발생했습니다. | 호출 시점과 환경 정보를 수집하여 서비스 관리자에게 문의합니다. |

### Handle errors

`ErrorType`은 새로운 오류 타입이 추가될 수 있으므로 `when` 또는 `switch` 문에 기본 분기를 두는 것을 권장합니다.

```kotlin
override fun onError(errorType: ErrorType) {
    when (errorType) {
        ErrorType.PERMISSION_DENIED -> checkTtsPermission()
        ErrorType.UNAUTHENTICATED -> checkCredentials()
        ErrorType.NETWORK_ERROR,
        ErrorType.TIMEOUT -> retryWhenNetworkIsAvailable()
        else -> logError(errorType.name)
    }
}
```

각 오류 타입의 정확한 선언은 [TextToSpeech API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/entry)에서 확인할 수 있습니다.


---

## Introduction

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/intro

### SpeechToText SDK 소개

SpeechToText SDK는 음성을 텍스트로 변환하기 위한 API를 제공합니다. 3rd party 앱은 SpeechToText SDK를 통해 Pleos Connect의 Speech Service와 연동됩니다. 3rd party 앱이 음성 인식을 요청하면 Speech Service는 인식된 음성을 텍스트로 변환하고, 인식 결과를 앱에 전달합니다.

[이미지: SpeechToText SDK 구조도 — 3rd Party Application 안의 SpeechToText SDK가 Request → (Request Recognition) → Pleos Connect OS의 Speech Service (On-Device / Server)가 Mic Input · Speech Recognition 수행 → (Send Recognized Text) → SDK로 결과 반환; 그 아래 Android Automotive OS]

> [TIP] 음성 명령 기능을 제공하지 않습니다
>
> SpeechToText는 사용자 발화를 `"음악 틀어줘"`와 같은 텍스트로 변환하여 반환하며, 음악 재생과 같은 실제 동작은 실행하지 않습니다. 인식한 명령에 따라 앱의 기능을 실행하려면 [Gleo Interface](https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/core-concepts)를 참고하세요.

> [INFO] SDK 버전
>
> 이 가이드는 SpeechToText SDK v3.3.1을 기준으로 작성되었습니다.

### 음성 인식 모드

SpeechToText SDK는 다음과 같이 세 가지 음성 인식 모드를 제공합니다.

- `HYBRID`: 기본 모드입니다. 서버 기반 음성 인식을 우선 사용하고, 네트워크 연결에 실패하면 SDK가 On-device 엔진으로 자동 전환합니다. 서버 기반 인식을 사용하려면 Pleos Playground의 My Project 자격 증명을 사용한 앱 등록이 필요합니다.
- `SERVER`: 서버 기반 음성 인식 모드입니다. 음성 데이터를 원격 Speech Service로 전송하여 인식합니다. 네트워크 연결과 앱 등록이 필요하며, 네트워크 연결에 실패해도 On-device 엔진으로 전환하지 않습니다.
- `ON_DEVICE`: 차량의 On-device 엔진에서 음성을 인식합니다. 네트워크 연결이 필요하지 않으며, 이 모드만 사용하는 경우 앱 등록은 필요하지 않습니다.

### 진입점

`ai.pleos.playground.stt.SpeechToText` 클래스는 SpeechToText SDK 기능에 접근하기 위한 entry point입니다. 생성자에 애플리케이션 `Context`와 인식 `Mode`를 전달하여 객체를 생성합니다. `Mode`를 생략하면 `HYBRID`가 적용됩니다.

일반적인 사용 순서는 다음과 같습니다.

1. `SpeechToText` 객체를 생성합니다.
2. `initialize()`로 SDK를 초기화합니다.
3. `addListener()`로 `ResultListener`를 등록합니다.
4. 필요한 경우 `setLocale()`로 인식 언어/지역을 설정합니다.
5. 서버 기반 인식 모드(`HYBRID`, `SERVER`)를 사용하는 경우 앱 등록을 완료합니다.
6. `request()`로 음성 인식을 시작합니다.
7. 인식이 끝나면 `stop()`을 호출합니다.
8. SDK 사용이 끝나면 `removeListener()` 후 `release()`로 리소스를 해제합니다.

#### `SpeechToText` 객체 생성 예시

[탭: Kotlin]

```kotlin
import ai.pleos.playground.stt.SpeechToText
import ai.pleos.playground.stt.constant.Mode

val speechToText = SpeechToText(applicationContext, Mode.HYBRID)
```

[탭: Java]

```java
import ai.pleos.playground.stt.SpeechToText;
import ai.pleos.playground.stt.constant.Mode;

SpeechToText speechToText =
        new SpeechToText(getApplicationContext(), Mode.HYBRID);
```

### Emulator setup

SpeechToText SDK를 사용하려면 Android Studio에 Pleos Connect Emulator를 설치하고 AVD를 설정합니다.

1. [소프트웨어 개발 환경 요구사항 확인](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#소프트웨어-개발-환경)
2. [Pleos Connect Emulator 설치](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#installing-the-pleos-connect-emulator)
3. [AVD 생성 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#setting-up-the-emulator)
4. [Pleos Connect Virtual Console 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator)
5. [마이크 접근 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#마이크-접근-설정)
6. [Cold boot 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#cold-boot-설정)

### 다음 단계

1. [SpeechToText SDK 가져오기](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/importing-sdk)
2. [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/declaring-permissions)
3. [SDK 초기화 및 해제](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/initialization-and-release)
4. [결과 리스너](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/result-listeners)
5. [앱 등록](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/app-registration)
6. [음성 인식 시작 및 중지](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/start-and-stop)
7. [오디오 데이터 전송](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/sending-audio-data)
8. [오류 코드](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/error-code)
9. [SpeechToText API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/entry)


---

## Importing SpeechToText SDK

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/importing-sdk

SpeechToText SDK를 사용하려면 Pleos Maven 저장소를 등록하고 앱 모듈에 SDK 의존성을 추가합니다.

### Maven 저장소 등록

프로젝트의 `settings.gradle.kts` 또는 `settings.gradle` 파일에 Pleos Maven 저장소를 추가합니다.

[탭: Kotlin DSL]

settings.gradle.kts

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://nexus-playground.pleos.ai/repository/maven-releases/")
    }
}
```

[탭: Groovy]

settings.gradle

```groovy
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url "https://nexus-playground.pleos.ai/repository/maven-releases/"
        }
    }
}
```

### SDK 의존성 추가

앱 모듈의 Gradle 파일에 SpeechToText SDK 의존성을 추가합니다.

[탭: Kotlin DSL]

app/build.gradle.kts

```kotlin
dependencies {
    implementation("ai.pleos.playground:SpeechToText:3.3.1")
}
```

[탭: Groovy]

app/build.gradle

```groovy
dependencies {
    implementation "ai.pleos.playground:SpeechToText:3.3.1"
}
```

Gradle 동기화를 완료한 후 [SpeechToText 권한을 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/declaring-permissions)합니다.


---

## Declaring permissions

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/declaring-permissions

SpeechToText SDK API를 사용하려면 앱의 `AndroidManifest.xml`에 `STT_SERVICE` 권한을 선언해야 합니다.

AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="pleos.car.permission.STT_SERVICE" />

    <application>
        <!-- ... -->
    </application>
</manifest>
```

이 권한은 SDK 초기화, 결과 리스너 등록, 앱 등록, 음성 인식 요청 및 오디오 데이터 전송 등 SpeechToText 서비스에 접근하는 API에 필요합니다.

> [WARNING]
>
> 권한을 선언하지 않으면 SDK 초기화나 API 호출이 실패할 수 있습니다. 오류 콜백에서 `PERMISSION_DENIED`가 전달되는 경우 앱의 manifest와 실행 환경에서 권한이 허용되었는지 확인하세요.

권한 선언을 마쳤으면 [SDK를 초기화](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/initialization-and-release)합니다.


---

## App registration

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/app-registration

서버 기반 음성 인식을 사용하려면 Pleos Playground에서 My Project를 생성할 때 발급받은 `Client ID`와 `Client Secret`으로 앱을 등록합니다. SDK 초기화가 완료된 후 `registerApp()`을 호출하세요.

> [INFO]
>
> `SERVER` 모드에서는 앱 등록이 필요합니다. `HYBRID` 모드에서도 서버 기반 인식을 사용하려면 앱을 등록해야 합니다. `ON_DEVICE` 모드만 사용하는 경우에는 등록하지 않아도 됩니다.

[탭: Kotlin]

```kotlin
speechToText.registerApp(
    clientID = clientId,
    clientSecret = clientSecret,
    listener = object : OnServerConnectionListener {
        override fun onConnected() {
            // 서버 기반 음성 인식을 요청할 수 있습니다.
        }

        override fun onFailed(errorType: ErrorType) {
            // 등록 또는 서버 연결 실패를 처리합니다.
        }
    }
)
```

[탭: Java]

```java
speechToText.registerApp(
        clientId,
        clientSecret,
        new OnServerConnectionListener() {
            @Override
            public void onConnected() {
                // 서버 기반 음성 인식을 요청할 수 있습니다.
            }

            @Override
            public void onFailed(ErrorType errorType) {
                // 등록 또는 서버 연결 실패를 처리합니다.
            }
        }
);
```

`onConnected()`가 호출된 이후 서버 기반 요청을 시작하세요.


---

## Initialization

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/initialization-and-release

`SpeechToText` 객체를 생성한 후 `initialize()`를 호출해야 API를 사용할 수 있습니다. SDK 사용을 마치면 `release()`를 호출하여 연결과 내부 리소스를 해제합니다.

### Initialize the SDK

초기화 결과가 필요한 경우 `OnInitializeListener`를 전달합니다.

[탭: Kotlin]

```kotlin
import ai.pleos.playground.stt.constant.ErrorType
import ai.pleos.playground.stt.listener.OnInitializeListener

speechToText.initialize(object : OnInitializeListener {
    override fun onSuccess() {
        // SpeechToText API를 사용할 수 있습니다.
    }

    override fun onFailure(errorType: ErrorType) {
        // 초기화 실패 원인을 처리합니다.
    }
})
```

[탭: Java]

```java
import ai.pleos.playground.stt.constant.ErrorType;
import ai.pleos.playground.stt.listener.OnInitializeListener;

speechToText.initialize(new OnInitializeListener() {
    @Override
    public void onSuccess() {
        // SpeechToText API를 사용할 수 있습니다.
    }

    @Override
    public void onFailure(ErrorType errorType) {
        // 초기화 실패 원인을 처리합니다.
    }
});
```

결과 콜백이 필요하지 않으면 인자 없이 호출할 수 있습니다.

```kotlin
speechToText.initialize()
```

### Release the SDK

`SpeechToText` 객체는 사용하는 컴포넌트의 수명주기에 맞춰 재사용하세요. 더 이상 필요하지 않거나 애플리케이션이 종료될 때는 등록한 결과 리스너를 제거하고 `release()`를 호출합니다.

[탭: Kotlin]

```kotlin
override fun onDestroy() {
    speechToText.removeListener(resultListener)
    speechToText.release()
    super.onDestroy()
}
```

[탭: Java]

```java
@Override
protected void onDestroy() {
    speechToText.removeListener(resultListener);
    speechToText.release();
    super.onDestroy();
}
```

`release()` 이후 SDK를 다시 사용하려면 새 객체를 생성하고 초기화 절차를 다시 수행하세요.


---

## Set locale

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/set-locale

`setLocale(locale: Locale)`는 음성 인식에 사용할 언어/지역(Locale)을 업데이트합니다.

### Set locale before recognition

음성 인식 요청 전에 `setLocale()`를 호출해 인식 언어/지역을 먼저 설정합니다.

- `request()` 호출 전에 설정한 locale이 해당 인식 세션의 기준으로 사용됩니다.
- `setLocale()`로 설정한 locale은 다음 인식 요청의 언어/지역 기준으로 사용됩니다.

### Supported locales

지원 locale은 다음과 같습니다.

- `ko-KR`: 한국어
- `en-US`: 영어
- `fr-CA`: 프랑스어
- `es-US`, `es-MX`: 스페인어

### Default behavior when `setLocale()` is not called

`setLocale()`을 호출하지 않으면 SDK는 같은 앱에서 이전에 `setLocale()`로 설정한 locale 값이 있는지 먼저 확인합니다.

- 이전 설정 값이 있으면 해당 locale 값을 사용합니다.
- 이전 설정 값이 없으면 시스템 locale을 사용합니다.

### Example

[탭: Kotlin]

```kotlin
import java.util.Locale

speechToText.setLocale(Locale.KOREA)
```

[탭: Java]

```java
import java.util.Locale;

speechToText.setLocale(Locale.KOREA);
```


---

## Start and stop recognition

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/start-and-stop

### Start recognition

차량 마이크로 음성 인식을 시작하려면 `request()`를 호출합니다. 호출 시 SDK가 마이크를 활성화하고 입력 음성의 인식을 시작합니다.

`request()`를 호출하기 전에 SDK 초기화와 결과 리스너 등록을 완료하세요. 서버 기반 인식이 필요한 모드에서는 앱 등록 또는 UA 인증도 완료해야 합니다.

[탭: Kotlin]

```kotlin
speechToText.addListener(resultListener)
speechToText.request()
```

[탭: Java]

```java
speechToText.addListener(resultListener);
speechToText.request();
```

준비 및 인식 결과는 등록한 `ResultListener`에서 확인합니다.

### Stop recognition

진행 중인 음성 인식을 중지하고 마이크 사용을 종료하려면 `stop()`을 호출합니다.

[탭: Kotlin]

```kotlin
speechToText.stop()
```

[탭: Java]

```java
speechToText.stop();
```

인식이 종료되면 `ResultListener.onEndedRecognition()`이 호출됩니다.


---

## Sending audio data

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/sending-audio-data

차량 마이크 대신 앱이 보유한 PCM 오디오를 인식하려면 `sendAudio()`에 `ByteArray`를 전달합니다. 이 기능은 ON\_DEVICE 모드에서만 사용할 수 있습니다. 전체 데이터를 한 번에 보내거나 여러 청크로 나눠 전송할 수 있습니다.

> [INFO]
>
> 이 문서의 3,200-byte 청크와 100ms 간격은 16 kHz, 16-bit, mono PCM을 실시간 속도로 전송하는 예시입니다. 다른 오디오 형식을 사용하는 경우 해당 형식의 100ms 분량에 맞춰 청크 크기를 계산하세요.

### Send a complete buffer

[탭: Kotlin]

```kotlin
val pcmData = applicationContext.assets
    .open("test_audio.pcm")
    .use { it.readBytes() }

speechToText.sendAudio(pcmData)
speechToText.completeAudioSend()
```

[탭: Java]

```java
byte[] pcmData;
try (InputStream input = getAssets().open("test_audio.pcm");
     ByteArrayOutputStream output = new ByteArrayOutputStream()) {
    byte[] buffer = new byte[8_192];
    int read;
    while ((read = input.read(buffer)) != -1) {
        output.write(buffer, 0, read);
    }
    pcmData = output.toByteArray();
}

speechToText.sendAudio(pcmData);
speechToText.completeAudioSend();
```

### Stream chunks

[탭: Kotlin]

```kotlin
private const val BYTES_PER_100_MS = 3_200

val pcmData = applicationContext.assets
    .open("test_audio.pcm")
    .use { it.readBytes() }

pcmData.asList()
    .chunked(BYTES_PER_100_MS)
    .forEach { chunk ->
        speechToText.sendAudio(chunk.toByteArray())
        Thread.sleep(100L)
    }

speechToText.completeAudioSend()
```

[탭: Java]

```java
private static final int BYTES_PER_100_MS = 3_200;

byte[] buffer = new byte[BYTES_PER_100_MS];
try (InputStream input = getAssets().open("test_audio.pcm")) {
    int read;
    while ((read = input.read(buffer)) != -1) {
        speechToText.sendAudio(Arrays.copyOf(buffer, read));
        Thread.sleep(100L);
    }
}

speechToText.completeAudioSend();
```

마지막 오디오 데이터를 보낸 후 반드시 `completeAudioSend()`를 호출하여 입력이 끝났음을 알리세요. 인식 결과는 미리 등록한 `ResultListener`로 전달됩니다.


---

## Event listeners

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/result-listeners

`ResultListener`를 등록하면 음성 인식의 준비, 시작, 중간·최종 결과, 음성 구간, 입력 크기 및 오류 이벤트를 받을 수 있습니다.

| Callback | Description |
| --- | --- |
| `onReady()` | 음성 인식 요청을 처리할 준비가 되었을 때 호출됩니다. |
| `onStartedRecognition()` | 음성 인식이 시작될 때 호출됩니다. |
| `onUpdated(stt, completed)` | 인식 텍스트가 갱신될 때 호출됩니다. `completed`가 `true`이면 해당 인식 결과가 완료된 것입니다. |
| `onUpdatedEpdData(on, off)` | EPD(End Point Detection)가 감지한 음성 시작(`on`)과 종료(`off`) 위치가 갱신될 때 호출됩니다. 위치 값은 입력 오디오 버퍼의 byte offset입니다. |
| `onUpdatedRms(rms)` | 입력 음성 신호의 RMS(Root Mean Square) 값이 갱신될 때 호출됩니다. RMS는 신호 크기를 나타내는 값이며 dB 값이 아닙니다. |
| `onEndedRecognition()` | 음성 인식이 종료될 때 호출됩니다. |
| `onError(errorType)` | 처리 중 오류가 발생하면 호출됩니다. |

[탭: Kotlin]

```kotlin
private val resultListener = object : ResultListener {
    override fun onReady() = Unit
    override fun onStartedRecognition() = Unit

    override fun onUpdated(stt: String, completed: Boolean) {
        // 중간 또는 최종 인식 결과를 처리합니다.
    }

    override fun onUpdatedEpdData(on: Long, off: Long) = Unit
    override fun onUpdatedRms(rms: Double) = Unit
    override fun onEndedRecognition() = Unit

    override fun onError(errorType: ErrorType) {
        // 오류를 처리합니다.
    }
}

speechToText.addListener(resultListener)
```

[탭: Java]

```java
private final ResultListener resultListener = new ResultListener() {
    @Override public void onReady() {}
    @Override public void onStartedRecognition() {}

    @Override
    public void onUpdated(String stt, boolean completed) {
        // 중간 또는 최종 인식 결과를 처리합니다.
    }

    @Override public void onUpdatedEpdData(long on, long off) {}
    @Override public void onUpdatedRms(double rms) {}
    @Override public void onEndedRecognition() {}

    @Override
    public void onError(ErrorType errorType) {
        // 오류를 처리합니다.
    }
};

speechToText.addListener(resultListener);
```

인식 이벤트를 놓치지 않도록 `request()` 또는 `sendAudio()`를 호출하기 전에 리스너를 등록하세요. 리스너가 더 이상 필요하지 않거나 SDK를 해제하기 전에는 등록할 때 사용한 동일한 인스턴스를 제거합니다.

```kotlin
speechToText.removeListener(resultListener)
```

오류별 처리 방법은 [Error code](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/error-code)를 참고하세요.


---

## Error code

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/error-code

SpeechToText SDK는 초기화, 앱 등록 및 음성 인식 중 발생한 오류를 `ErrorType`으로 전달합니다. 오류는 `OnInitializeListener`, `OnServerConnectionListener` 또는 `ResultListener`에서 처리할 수 있습니다.

### ErrorType

| ErrorType | Description | Recommended action |
| --- | --- | --- |
| `AUDIO_ERROR` | 입력 오디오 데이터 처리에 실패했습니다. | 입력 데이터와 오디오 형식을 확인한 후 다시 요청합니다. |
| `INVALID_REQUEST_ERROR` | 요청 값이나 요청 상태가 올바르지 않습니다. | 호출 순서, 중복 요청 및 입력 값을 확인합니다. |
| `MIC_ERROR` | 마이크 입력을 시작하거나 처리하는 중 오류가 발생했습니다. | 마이크 상태와 센서 개인정보 보호 설정을 확인합니다. |
| `NETWORK_ERROR` | 네트워크 통신에 실패했습니다. | 네트워크 연결을 확인하고 다시 요청합니다. |
| `NOT_SUPPORTED_API` | 현재 모드 또는 실행 환경에서 지원하지 않는 API입니다. | 선택한 `Mode`와 플랫폼의 지원 범위를 확인합니다. |
| `ON_DEVICE_ENGINE_ERROR` | On-device 음성 인식 엔진에서 오류가 발생했습니다. | 엔진 상태를 확인하거나 `HYBRID` 또는 `SERVER` 모드를 검토합니다. |
| `PERMISSION_DENIED` | SpeechToText 서비스 접근 권한이 없습니다. | manifest의 `STT_SERVICE` 권한과 실행 환경의 권한 상태를 확인합니다. |
| `SERVER_INTERNAL_ERROR` | Speech Service 내부 오류가 발생했습니다. | 잠시 후 다시 요청하고, 반복되면 서비스 관리자에게 문의합니다. |
| `TIMEOUT` | 제한 시간 안에 요청이 완료되지 못했습니다. | 네트워크와 서버 상태를 확인한 후 다시 요청합니다. |
| `UNAUTHENTICATED` | 앱 인증에 실패했습니다. | Pleos Playground의 My Project 자격 증명 또는 UA 앱 정보와 접속 환경을 확인합니다. |
| `UNAVAILABLE_LANGUAGE` | 요청한 언어를 사용할 수 없습니다. | 지원되는 `Locale`로 변경합니다. |
| `UNKNOWN` | 분류되지 않은 오류가 발생했습니다. | 호출 시점과 환경 정보를 수집하여 서비스 관리자에게 문의합니다. |

### Handle errors

`ErrorType`은 새로운 오류 타입이 추가될 수 있으므로 `when` 또는 `switch` 문에 기본 분기를 두는 것을 권장합니다.

```kotlin
override fun onError(errorType: ErrorType) {
    when (errorType) {
        ErrorType.PERMISSION_DENIED -> checkSttPermission()
        ErrorType.UNAUTHENTICATED -> checkCredentials()
        ErrorType.NETWORK_ERROR,
        ErrorType.TIMEOUT -> retryWhenNetworkIsAvailable()
        else -> logError(errorType.name)
    }
}
```

각 오류 타입의 정확한 선언은 [SpeechToText API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/entry)에서 확인할 수 있습니다.
