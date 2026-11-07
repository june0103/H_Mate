# TextToSpeech SDK — API Reference

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/entry 이하 Dokka API Reference (2026-11-05 수집)
> 사이트 HTML 본문은 비어 있고 JS 청크에 실제 내용이 들어 있어 거기서 추출했습니다. 클래스(타입) 페이지 기준이며 멤버별 상세 페이지 설명은 포함하지 않습니다.
> `*(deprecated)*`는 원문에서 취소선으로 표시된 시그니처입니다. 사용 패턴은 상위 폴더의 SDK 사용 가이드 문서를 함께 보세요.

## 패키지 목록

- `ai.pleos.playground.tts.constant`
- `ai.pleos.playground.tts.listener`
- `ai.pleos.playground.tts`

---

## 패키지 `ai.pleos.playground.tts.constant`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.constant/entry


**Types**
- `ErrorType` — Represents the types of errors that can occur within the system.
  - `enum ErrorType: Enum<ErrorType>`
- `Mode`
  - `enum Mode: Enum<Mode>`

### ErrorType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.constant/error-type/entry

`enum ErrorType: Enum<ErrorType>`
Represents the types of errors that can occur within the system.

**Entries**
- `AUDIO_ERROR` — Audio playback/output related error.
- `MIC_ERROR` — Microphone input/recording related error.
- `SERVER_INTERNAL_ERROR` — Server-side processing failure (5xx, gRPC internal issues).
- `NETWORK_ERROR` — Network connectivity failure.
- `INVALID_REQUEST_ERROR` — Client-side invalid request.
- `TIMEOUT` — Request/response timeout.
- `UNAUTHENTICATED` — Authentication failure (invalid/expired token).
- `PERMISSION_DENIED` — Permission denied or insufficient access rights.
- `ON_DEVICE_ENGINE_ERROR` — On-device engine (e.g. Speech SDK) failure.
- `UNKNOWN` — Unknown error type.
- `UNAVAILABLE_LANGUAGE` — Requested language is not available.
- `NOT_SUPPORTED_API` — Requested API is not supported.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<ErrorType>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): ErrorType`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<ErrorType>`

### Mode

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.constant/mode/entry

`enum Mode: Enum<Mode>`

**Entries**
- `SERVER` — Server-oriented Streaming TextToSpeech will be running. The application gets an error event if the network connection fails.
- `ON_DEVICE` — OnDevice-oriented Streaming TextToSpeech will be running. The application doesn't need to be registered to Pleos Playground if it only uses ON_DEVICE mode.
- `HYBRID` — Server-oriented Streaming TextToSpeech will be running. In case of network failure, SDK will stream TextToSpeech audio to the on-device engine automatically.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<Mode>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): Mode`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<Mode>`


---

## 패키지 `ai.pleos.playground.tts.listener`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.listener/entry


**Types**
- `EventListener` — Listener for events relating to the progress of Text-To-Speech. Adds and removes with addEventListener/removeEventListener APIs respectively.
  - `interface EventListener`
- `OnInitializeListener` — Listener for the result of TextToSpeech SDK initialization.
  - `interface OnInitializeListener`
- `OnServerConnectionListener` — Server connection result listener
  - `interface OnServerConnectionListener`

### EventListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.listener/event-listener/entry

`interface EventListener`
Listener for events relating to the progress of Text-To-Speech. Adds and removes with addEventListener/removeEventListener APIs respectively.

**Functions**
- `onDone` — Called when TextToSpeech has successfully completed processing.
  - `abstract fun onDone()`
- `onError` — Called when an error has occurred during processing.
  - `abstract fun onError(errorType: ErrorType)`
- `onReady` — Called when TextToSpeech speak has been called.
  - `abstract fun onReady()`
- `onStart` — Called when TextToSpeech starts as perceived by the caller.
  - `abstract fun onStart()`
- `onStop` — Called when TextToSpeech stops while in progress.
  - `abstract fun onStop()`
- `onUpdatedRms` — Called when RMS is updated.
  - `abstract fun onUpdatedRms(rms: Double)`

### OnInitializeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.listener/on-initialize-listener/entry

`interface OnInitializeListener`
Listener for the result of TextToSpeech SDK initialization.

**See also**
- `TextToSpeech.initialize`

**Functions**
- `onFailure` — Called when the SDK initialization has failed.
  - `abstract fun onFailure(errorType: ErrorType)`
- `onSuccess` — Called when the SDK has been successfully initialized and bound to the TTS service.
  - `abstract fun onSuccess()`

### OnServerConnectionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts.listener/on-server-connection-listener/entry

`interface OnServerConnectionListener`
Server connection result listener

**Functions**
- `onConnected` — Called when connected to the server
  - `abstract fun onConnected()`
- `onFailed` — Called when failure to connection to server
  - `abstract fun onFailed(errorType: ErrorType)`


---

## 패키지 `ai.pleos.playground.tts`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts/entry


**Types**
- `TextToSpeech` — Provides access to the TextToSpeech service. This SDK allows access to the TextToSpeech.
  - `class TextToSpeech(context: Context, mode: Mode = Mode.HYBRID)`

### TextToSpeech

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/TextToSpeech/text-to-speech/ai.pleos.playground.tts/text-to-speech/entry

`class TextToSpeech(context: Context, mode: Mode = Mode.HYBRID)`
Provides access to the TextToSpeech service. This SDK allows access to the TextToSpeech.
Important: the caller MUST invoke release() when it is no longer needed and the application is destroyed.
Streams audio to remote servers or on-device engines to perform TextToSpeech.
Please note that the application must register your application in Pleos Playground to use the server-oriented speech synthesizer.

**Parameters**
- `context` — Application context
- `mode` — The user can set the mode that they want. We support three modes. HYBRID, SERVER, and ON_DEVICE. If the user doesn't select anything, HYBRID will be set automatically.

**See also**
- `Mode`

**Constructors**
- `TextToSpeech`
  - `constructor(context: Context, mode: Mode = Mode.HYBRID)`

**Types**
- `Companion`
  - `object Companion`

**Functions**
- `addEventListener` — Adds a listener that will be notified of various events related to TextToSpeech
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun addEventListener(listener: EventListener)`
- `initialize` — Initializes TextToSpeech SDK When the app starts or is created, it should call the initialization of SDK / Initializes TextToSpeech SDK with a listener for initialization result. When the app starts or is created, it should call the initialization of SDK.
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun initialize()` — Initializes TextToSpeech SDK When the app starts or is created, it should call the initialization of SDK
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun initialize(listener: OnInitializeListener)` — Initializes TextToSpeech SDK with a listener for initialization result. When the app starts or is created, it should call the initialization of SDK.
- `registerApp` — Register app
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun registerApp(clientID: String, clientSecret: String, listener: OnServerConnectionListener)`
- `release` — Releases TextToSpeech SDK When the app terminates or is destroyed, it should call the release of SDK
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun release()`
- `removeEventListener` — Removes a previously registered listener
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun removeEventListener(listener: EventListener)`
- `setAudioAttributes` — Sets the audio attributes to be used for TextToSpeech SDK
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun setAudioAttributes(audioAttributes: AudioAttributes)`
- `setAudioZoneId` — Update audio zone id that user want to set. Requires the caller to have TTS_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun setAudioZoneId(zoneId: Int)`
- `setLocale` — Updates the locale that the user wants to set. Requires the caller to have TTS_PERMISSION.
  - `fun setLocale(locale: Locale)`
- `setSpeakerType` — Updates the speaker type (voice) used for speech synthesis. Requires the caller to have TTS_PERMISSION. If this method is not called, the speaker previously saved for the app is used. If no speaker has been saved for the app, the Gleo AI speaker setting is used. If neither is set, haeun is used.
  - `fun setSpeakerType(type: String)`
- `speak` — Converts Text-To-Speech and speaks the text
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun speak(text: String, isFinal: Boolean = true)`
- `stop` — Interrupts the current TextToSpeech playback
  - `@RequiresPermission(value = "pleos.car.permission.TTS_SERVICE") fun stop()`
