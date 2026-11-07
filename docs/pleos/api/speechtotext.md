# SpeechToText SDK — API Reference

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/entry 이하 Dokka API Reference (2026-11-05 수집)
> 사이트 HTML 본문은 비어 있고 JS 청크에 실제 내용이 들어 있어 거기서 추출했습니다. 클래스(타입) 페이지 기준이며 멤버별 상세 페이지 설명은 포함하지 않습니다.
> `*(deprecated)*`는 원문에서 취소선으로 표시된 시그니처입니다. 사용 패턴은 상위 폴더의 SDK 사용 가이드 문서를 함께 보세요.

## 패키지 목록

- `ai.pleos.playground.stt.constant`
- `ai.pleos.playground.stt.data`
- `ai.pleos.playground.stt.listener`
- `ai.pleos.playground.stt`

---

## 패키지 `ai.pleos.playground.stt.constant`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.constant/entry


**Types**
- `ErrorType` — Represents the types of errors that can occur within the system.
  - `enum ErrorType: Enum<ErrorType>`
- `Mode` — This class provides SpeechToText mode.
  - `enum Mode: Enum<Mode>`

### ErrorType

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.constant/error-type/entry

`enum ErrorType: Enum<ErrorType>`
Represents the types of errors that can occur within the system.

**Entries**
- `AUDIO_ERROR` — Audio data processing error (e.g. failed to read the audio buffer).
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

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.constant/mode/entry

`enum Mode: Enum<Mode>`
This class provides SpeechToText mode.

**Entries**
- `HYBRID` — Streams audio to remote servers to perform SpeechToText. In case of network failure, SpeechToText will stream audio to the on-device engine automatically.
- `SERVER` — Streams audio to remote servers to perform SpeechToText.
- `ON_DEVICE` — Streams audio to the on-device engine to perform SpeechToText. The application doesn't need to be registered in Pleos Playground if it only uses ON_DEVICE mode.

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<Mode>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): Mode`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<Mode>`


---

## 패키지 `ai.pleos.playground.stt.data`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.data/entry


**Types**
- `InferenceSource`
  - `enum InferenceSource: Enum<InferenceSource>`

### InferenceSource

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.data/inference-source/entry

`enum InferenceSource: Enum<InferenceSource>`

**Entries**
- `ON_DEVICE`
  - `@SerializedName(value = "on_device") ON_DEVICE`
- `SERVER`
  - `@SerializedName(value = "server") SERVER`

**Types**
- `Companion`
  - `object Companion`

**Properties**
- `entries` — Returns a representation of an immutable list of all enum entries, in the order they're declared.
  - `val entries: EnumEntries<InferenceSource>`

**Functions**
- `valueOf` — Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
  - `fun valueOf(value: String): InferenceSource`
- `values` — Returns an array containing the constants of this enum type, in the order they're declared.
  - `fun values(): Array<InferenceSource>`


---

## 패키지 `ai.pleos.playground.stt.listener`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.listener/entry


**Types**
- `OnInitializeListener` — Listener for the result of SpeechToText SDK initialization.
  - `interface OnInitializeListener`
- `OnServerConnectionListener` — Receives events from SpeechToText when the server is connected successfully.
  - `interface OnServerConnectionListener`
- `ResultListener` — Receives events from SpeechToText when recognition-related events occur. Adds and removes with addListener/removeListener APIs respectively.
  - `interface ResultListener`

### OnInitializeListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.listener/on-initialize-listener/entry

`interface OnInitializeListener`
Listener for the result of SpeechToText SDK initialization.

**See also**
- `SpeechToText.initialize`

**Functions**
- `onFailure` — Called when the SDK initialization has failed.
  - `abstract fun onFailure(errorType: ErrorType)`
- `onSuccess` — Called when the SDK has been successfully initialized and bound to the STT service.
  - `abstract fun onSuccess()`

### OnServerConnectionListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.listener/on-server-connection-listener/entry

`interface OnServerConnectionListener`
Receives events from SpeechToText when the server is connected successfully.

**Functions**
- `onConnected` — Called when connected to the server
  - `abstract fun onConnected()`
- `onFailed` — Called when failure to connection to server
  - `abstract fun onFailed(errorType: ErrorType)`

### ResultListener

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt.listener/result-listener/entry

`interface ResultListener`
Receives events from SpeechToText when recognition-related events occur. Adds and removes with addListener/removeListener APIs respectively.

**Functions**
- `onEndedRecognition` — Called when caller recognition is finished.
  - `abstract fun onEndedRecognition()`
- `onError` — Called when caller recognition fails.
  - `abstract fun onError(errorType: ErrorType)`
- `onReady` — Called when listener recognition is ready.
  - `abstract fun onReady()`
- `onStartedRecognition` — Called when caller recognition is started.
  - `abstract fun onStartedRecognition()`
- `onUpdated` — Called when the SpeechToText result is updated.
  - `abstract fun onUpdated(stt: String, completed: Boolean)`
- `onUpdatedEpdData` — Called when the SpeechToText EPD result is updated.
  - `abstract fun onUpdatedEpdData(on: Long, off: Long)`
- `onUpdatedRms` — Called when RMS is updated.
  - `abstract fun onUpdatedRms(rms: Double)`


---

## 패키지 `ai.pleos.playground.stt`

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt/entry


**Types**
- `SpeechToText` — Provides access to the SpeechToText service. This SDK allows access to the SpeechToText.
  - `class SpeechToText(context: Context, mode: Mode = Mode.HYBRID)`

### SpeechToText

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/SpeechToText/speech-to-text/ai.pleos.playground.stt/speech-to-text/entry

`class SpeechToText(context: Context, mode: Mode = Mode.HYBRID)`
Provides access to the SpeechToText service. This SDK allows access to the SpeechToText.
Important: the caller MUST invoke release() when it is no longer needed and the application is destroyed.
Streams audio to remote servers or on-device engines to perform SpeechToText.
Please note that the application must be registered in Pleos Playground to use the server-oriented speech recognizer.

**Parameters**
- `context` — Application context
- `mode` — The user can set the mode that they want. We support three modes. HYBRID, SERVER, and ON_DEVICE. If the user doesn't select anything, HYBRID will be set automatically.

**See also**
- `Mode`

**Constructors**
- `SpeechToText`
  - `constructor(context: Context, mode: Mode = Mode.HYBRID)`

**Types**
- `Companion`
  - `object Companion`

**Functions**
- `addListener` — Adds a listener that will be notified of various events related to SpeechToText Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun addListener(listener: ResultListener)`
- `completeAudioSend` — Completes the external audio buffer input started by sendAudio and finishes recognition. Available only in Mode.ON_DEVICE. In other modes the call is ignored. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun completeAudioSend()`
- `initialize` — Initializes SDK to use SpeechToText whether the application is created or not. Requires the caller to have STT_PERMISSION. / Initializes SDK to use SpeechToText with a listener for initialization result. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun initialize()` — Initializes SDK to use SpeechToText whether the application is created or not. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun initialize(listener: OnInitializeListener)` — Initializes SDK to use SpeechToText with a listener for initialization result. Requires the caller to have STT_PERMISSION.
- `registerApp` — Registers the application to use the server-oriented or hybrid SpeechToText service. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun registerApp(clientID: String, clientSecret: String, listener: OnServerConnectionListener)`
- `release` — Releases SDK whether the application is destroyed or not. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun release()`
- `removeListener` — Removes a previously registered listener Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun removeListener(listener: ResultListener)`
- `request` — Requests for starting speech recognition. After the application invoke request(), microphone will be open, and then you can stream your audio through microphone.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun request()`
- `sendAudio` — Sends an external audio buffer (PCM) for recognition. Available only in Mode.ON_DEVICE. In other modes the call is ignored and ResultListener.onError is not called. Recognition starts when the first buffer is sent; calling request is not required. In this path, ResultListener.onReady and ResultListener.onUpdatedRms are not called. Call completeAudioSend after sending the last buffer. Requires the caller to have STT_PERMISSION.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun sendAudio(bytes: ByteArray)`
- `setLocale` — Updates the locale that the user wants to set. Requires the caller to have STT_PERMISSION.
  - `fun setLocale(locale: Locale)`
- `stop` — Stops speech recognition. After the application invoke stop(), the microphone will be disabled.
  - `@RequiresPermission(value = "pleos.car.permission.STT_SERVICE") fun stop()`
