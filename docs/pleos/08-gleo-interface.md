# 08. Gleo Interface 구현 가이드

Gleo(AI 에이전트)와 앱 연동(App Capability·Action Callback·Schema) 가이드 원문.

## 목차

- [Gleo Interface](#gleo-interface)
- [App Schema Writing Guide](#app-schema-writing-guide)
- [Callback Schema Writing Guide](#callback-schema-writing-guide)
- [Code Details with Examples](#code-details-with-examples)
- [Delivering the Schema to Gleo](#delivering-the-schema-to-gleo)
- [How to Integrate Radio App with Gleo AI](#how-to-integrate-radio-app-with-gleo-ai)


---

## Gleo Interface

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/core-concepts

### 1. Core Concepts

- **Gleo Interface**는 Gleo AI와 타사 앱이 차량 내에서 사용자의 음성 명령을 처리하기 위해 서로 상호작용하는 방식을 정의하는 인터페이스입니다.
- Gleo Interface는 앱에서 정의한 function call을 LLM이 생성하고 앱의 응답을 바탕으로 최종 답변을 생성하는 function calling 방식에 기반합니다.
  - 이 방식은 기본적으로 OpenAI의 function calling 메커니즘과 유사하지만, Android 애플리케이션을 지원하기 위해 스키마에 추가적인 수정 및 확장을 적용하였습니다.
- Gleo AI와 연동하려면, 타사 앱은 먼저 앱에서 Gleo AI가 호출할 수 있는 function과 그 호출 방식을 정의한 JSON 파일인 **App Schema**를 등록해야 합니다.
- Gleo Interface를 사용하면, Gleo AI는 **Gleo Action**을 통해 앱을 호출하고, **Callback**을 통해 앱의 응답을 받을 수 있습니다.
- 아래는 음악 앱이 Gleo Action 및 Callback을 통해 Gleo AI와 상호작용하는 예시를 보여줍니다.

[이미지: action_callback]

[이미지 설명 필요: 음악 앱이 Gleo Action 및 Callback을 통해 Gleo AI와 상호작용하는 흐름 예시 다이어그램. 원본: https://document.pleos.ai/assets/images/action_callback-fb82919ad6c042cb8fb199754d4fff8d.png]

- 현재 가이드는 개발이 진행됨에 따라 지속적으로 개선될 예정입니다.


---

## App Schema Writing Guide

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/app-capability

### 1. App Schema란?

- App Schema는 Gleo AI가 앱 내 특정 function을 호출할 수 있도록 필요한 필드와 형식을 정의합니다.
- App Schema는 아래 명세를 따르며 [JSON Schema](https://json-schema.org/)를 기반으로 해야 합니다.

### 2. App Schema

#### 2.1 Fields

- **packageName**(`string`): Android 애플리케이션의 패키지명입니다.
- **appDescription**(`string`): 앱의 주요 목적을 명확히 나타내는 간결한 설명입니다. App Schema 작성 가이드라인를 참조합니다.
- **version**(`string`): Pleos 앱 마켓에 등록된 애플리케이션 버전입니다.
- **actionCalls**(`array`): 음성 명령으로 호출할 수 있는 function 목록입니다 (최대 3개).
  - **type**(`string`, "function"으로 고정)
  - **function**(`object`)
    - **component**(`string`, 기본값 "activity"): Android에서 Gleo AI가 function을 호출하는 방식을 지정합니다. 앱의 설계와 사용 사례에 따라 두 방법 중 하나를 적용할 수 있습니다.
      - "activity": Android의 `context.startActivity()` 메서드를 사용하여 action을 실행합니다. 포어그라운드 UI 실행이나 사용자 상호작용이 필요한 경우 사용합니다.
      - "service": Android의 `context.sendBroadcast()` 메서드를 사용하여 action을 전송합니다. UI 없이 백그라운드 처리가 필요한 경우에 적합합니다.
    - **name**(`string`): 앱 내에서 호출을 구분하기 위해 사용되는 고유한 function 이름입니다. 반드시 모든 글자를 알파벳 대문자로 작성하고 각 단어는 밑줄(\_)로 구분해야 합니다.
    - **description**(`string`): Function의 목적과 역할을 명확하고 간결하며 완전하게 설명하는 문장입니다.
    - **parameters**(`object`)
      - **type**(`string`, "object"로 고정)
      - **properties**(`object<string, object>`): function 호출 때 Gleo AI가 전달할 parameter를 정의하는 object입니다.
        - 각 property의 이름은 앱이 function 입력으로 받을 parameter명입니다.
        - 각 property의 값은 parameter의 `type`과 `description`을 지정하는 object입니다. `type`은 전달될 데이터 유형을, `description`은 그 값이 무엇을 나타내는지 설명합니다. 허용 가능한 값의 범위가 정해져 있는 경우, `enum`에 해당 문자열 값의 목록을 지정할 수 있습니다.
        - JSON Schema 전체 기능을 지원하지 않기 때문에, 아래 App Schema 작성 가이드라인과 App Schema 예시를 참조하세요.
      - **required**(`array<string>`): function 실행 시 필수적인 parameter 이름의 배열입니다.

### 3. App Schema 예시

다음 예시는 세 가지 function을 지원하는 앱(packageName: `ai.dot42.pleos.reminderapp`)을 기반으로 합니다.

```json
{
  "packageName": "ai.dot42.pleos.reminderapp",
  "appDescription": "A reminder app that lets you schedule tasks and receive notifications at the right time.",
  "invokeWord" : "리마인더",
  "version" : "1.0.0",
  "actionCalls": [
    {
      "type": "function",
      "function": {
        "name": "ADD_REMINDER",
        "description": "Add a reminder that will notify the user at the given date and time.",
        "parameters": {
          "type": "object",
          "properties": {
            "title": {
              "type": "string",
              "description": "Title of the reminder."
            },
            "dateTime": {
              "type": "string",
              "description": "Date and time to trigger the reminder notification, in ISO 8601 date and time format."
            }
          },
          "required": ["title", "dateTime"]
        },
        "component": "activity"
      }
    },
    {
      "type": "function",
      "function": {
        "name": "SEARCH_REMINDER",
        "description": "Search for a reminder that matches or contains the search term.",
        "parameters": {
          "type": "object",
          "properties": {
            "searchTerm": {
              "type": "string",
              "description": "The keyword for searching a reminder."
            }
          },
          "required": ["searchTerm"]
        },
        "component": "activity"
      }
    },
    {
      "type": "function",
      "function": {
        "name": "DELETE_REMINDER",
        "description": "Delete a reminder from the list of reminders.",
        "parameters": {
          "type": "object",
          "properties": {
            "searchTerm": {
              "type": "string",
              "description": "The title or keyword for deleting a reminder."
            }
          },
          "required": ["searchTerm"]
        },
        "component": "activity"
      }
    }
  ]
}
```

### 4. App Schema 작성 가이드라인

- `actionCalls`
  - `actionCalls`는 사용자의 음성 명령으로 실행할 수 있는 function들을 포함합니다. 최대 3개로 제한되지만, 사용자는 화면(디스플레이) 상의 상호작용을 통해 앱의 다른 기능들도 사용할 수 있습니다.
- Function 이름
  - App Schema 내 각 function 이름은 고유해야 하며, 다른 function과 중복되지 않아야 합니다.
  - 최상의 성능을 위해 각 function은 여러 호출 없이도 단독으로 사용자 명령을 수행할 수 있도록 설계하는 것이 좋습니다.
  - 단순히 동사만 사용하는 것보다, 2~3개의 단어로 구체적인 대상까지 포함해 작성하는 것을 권장합니다.
- 앱, function 및 parameter의 description
  - 모든 description은 영어로 작성해야 합니다.
  - Gleo AI의 동작을 조작하려 하거나 다른 앱이나 function 사용을 억제하는 문구를 포함하지 마세요.
  - Description 내에 구체적인 예시를 제공하지 마세요. 이는 Gleo AI가 맥락에 기반하여 action을 호출하는 데 방해가 될 수 있습니다.
  - Description은 간결하지만 앱, function 또는 parameter의 의미와 역할을 포괄적으로 설명해야 합니다. 한 문장(최대 두 문장)으로 작성하는 것이 권장됩니다.
- Parameter properties
  - Parameter의 type은 string, number, boolean, array 중 하나여야 합니다.
    - `type`에는 하나의 값만 지정할 수 있습니다. `"type": ["string", "null"]`처럼 여러 type을 배열로 지정하는 형식은 지원하지 않습니다.
  - `enum`을 지정하는 경우 문자열 값만 사용할 수 있습니다. JSON Schema에서 허용하는 `null` 값은 지원하지 않습니다.
    - 현재 지원되는 type은 제한적이지만, 향후 type 검증 기능과 함께 점차 확대될 예정입니다.
  - Parameter 이름은 LLM이 쉽게 이해할 수 있도록 값을 명확하게 나타내는 이름이어야 합니다.
- `required` 필드
  - function 호출 시 필요한 parameter를 포함해야 합니다. 선택적 parameter는 포함하지 마세요.
  - Gleo AI가 모든 필수 parameter를 채워줄 것을 보장하지는 않지만, 이 필드에 명시하면 실행 성공률을 높일 수 있습니다.


---

## Callback Schema Writing Guide

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/action_callback

### 1. Callback Schema란?

- Callback Schema는 앱이 Gleo AI에 응답을 반환하는 방법을 정의하며, Gleo AI는 이를 바탕으로 사용자에게 표시할 텍스트 또는 음성 출력 형태의 최종 응답을 생성합니다.
- Callback Schema는 아래의 명세를 따르는 [JSON 스키마](https://json-schema.org/)를 따라야 합니다.

### 2. Callback Schema

#### 2.1 Fields

- **functionName**(`string`, required): Callback을 발생시킨 intent action의 이름입니다.
- **status**(`enum`, 필수): 앱에서 **Gleo Action**을 호출한 의 상태를 나타냅니다.
  - 가능한 값: "success", "asking", "fail"
- **message**(`string`, 필수): 호출 결과에 대한 짧은 설명으로, 완전한 문장으로 작성되어야 합니다.
  - Message는 영어 문장이어야 하지만, 필요에 따라 다른 언어의 콘텐츠(예: 검색어, 특정 위치, 항목 이름 등)를 포함할 수 있습니다.
- **mode**(`enum`, 선택, 기본값은 "voice"): Gleo AI가 사용자에게 답변을 전달하는 방법을 나타냅니다.
  - "voice" : 답변을 음성 출력으로 재생합니다.
  - "beep" : 음성 출력 대신 효과음만을 재생합니다.
  - "silent" : 음성 출력이나 효과음 없이 아무 소리도 재생하지 않습니다.
    - 앱이 자체적으로 음성 출력이나 소리를 재생해야 할 때 이 mode를 선택할 수 있습니다.
- **data**(`string`, 선택): 요청 결과를 JSON 객체 배열 문자열 형식(key-value 형식의 객체들)으로 표현한 값입니다.
  - 전체 결과를 그대로 반환하기보다는, Gleo AI가 적절한 응답을 생성하는 데 필요한 정보만을 포함하도록 하세요. Callback Schema 작성 가이드라인을 참조하세요.

#### 2.2 `Status`에 대한 세부 정보

1. **success**

- Action이 정상적으로 처리되었을 때 사용됩니다.
- 결과가 없더라도 정상 처리된 경우라면 사용할 수 있습니다.
- `message` 예시:
  - *"The reminder has been added successfully."*
  - *"The search was completed, but no matching results were found."*

2. **asking**

- Action을 실행하기 위해 추가 정보(예: 필수 parameter)가 필요하거나 사용자 확인 같은 후속 상호작용이 필요한 경우 사용됩니다.
- Message에는 누락되었거나 필요한 추가 정보에 대한 안내를 포함합니다.
- `message` 예시
  - *"Search term is required to search for a reminder."*
  - *"It is necessary to select from the retrieved data."*
  - *"Confirmation for the deletion request is required."*

3. **fail**

- Action이 완료될 수 없을 때 사용됩니다. 충족되지 않은 preconditions, conflicts, 기타 domain-level constraints로 인한 비즈니스 로직 실패뿐 아니라 app crash, network failure, internal server timeout 같은 system-level error도 포함합니다.
- message에는 실패 이유를 간략히 설명합니다.
- 참고로, Gleo는 timeout을 위해 7초 후 Action을 취소합니다.
- `message` 예시
  - *"The item already exists."*
  - *"The list exceeds 10 items, so a new item cannot be added."*
  - *"A timeout occurred while processing the request."*
  - *"An unexpected internal error occurred."*

### 3. Callback Schema 예시

- Function이 앱에서 정상적으로 처리된 경우, Callback은 다음과 같습니다.

```json
{
  "functionName": "ADD_REMINDER",
  "status": "success",
  "message": "The reminder was successfully added.",
  "data": "[ { \"title\": \"미팅\", \"dateTime\": \"2025-02-20T06:00:00Z\" } ]"
}
```

- 필수 parameter가 누락된 경우, Callback은 다음과 같습니다.

```json
{
  "functionName": "ADD_REMINDER",
  "status": "asking",
  "message": "A registration date is required.",
  "data": "[ { \"missingField\": \"dateTime\" } ]"
}
```

- 논리 조건으로 인해 실행을 완료할 수 없을 경우:

```json
{
  "functionName": "ADD_REMINDER",
  "status": "fail",
  "message": "It exceeds 10 items, so it cannot be added."
}
```

- System-level error (예: app crash, timeout 또는 internal exception)로 실행이 실패한 경우에도 `fail`을 사용합니다.

```json
{
  "functionName": "ADD_REMINDER",
  "status": "fail",
  "message": "A timeout occurred while processing the request."
}
```

- 앱에서 자체적으로 TTS를 재생하거나 음악 재생을 시작하고자 할 경우, `"mode": "silent"`를 설정하면 Gleo의 TTS를 비활성화할 수 있습니다.
- 이 경우 Gleo AI는 실제 사용자 상호작용에 접근할 수 없기 때문에 Callback에만 전적으로 의존하게 됩니다.

```json
{
  "functionName": "PLAY_FREQUENCY",
  "status": "success",
  "message": "Starting the requested station now.",
  "data": "[ { \"stationName\": \"SBS 파워FM\", \"frequency\": \"107.7MHz\" } ]",
  "mode": "silent"
}
```

- Gleo AI가 TTS 대신 짧은 완료음만 재생하도록 하려면, `"mode": "beep"`를 사용하세요.

```json
{
  "functionName": "PLAY_MUSIC",
  "status": "success",
  "message": "Playback started.",
  "data": "[ { \"track\": \"Love Dive\", \"artist\": \"IVE\" } ]",
  "mode": "beep"
}
```

### 4. Callback Schema 작성 가이드라인

- `functionName` 필드
  - Callback을 발생시킨 App Schema의 function 이름(actionCalls → function → name)과 일치해야 합니다.
- `data` 필드
  - 선택 사항이지만 `data`를 반환하는 것을 강력히 권장합니다. 이는 Gleo AI가 더 정확하고 근거 있는 응답을 생성하는 데 필요한 문맥을 제공하기 때문입니다.
  - 또한, Callback의 `mode`가 "silent" 또는 "beep"일 때도 `data`를 포함하면 Gleo AI가 상호작용의 문맥을 이해하고 연속성을 유지하는 데 도움이 될 수 있습니다.


---

## Code Details with Examples

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/examples

### 1. Gleo Action

#### 1.1 Android Intent 변환

Gleo AI가 사용자의 요청인 “오늘 오후 3시에 미팅 리마인더를 추가해줘”를 해석하면, 다음과 같은 JSON 객체를 생성합니다.

```json
{
    "packageName": "ai.dot42.pleos.reminderapp",
    "action": "ADD_REMINDER",
    "actionParameters": {
      "title": "미팅",
      "dateTime": "2025-02-20T06:00:00Z"
    }
}
```

- 이후 Gleo AI는 이 객체를 Android Intent로 변환하여 외부 앱으로 전달합니다. 변환 과정은 다음 규칙을 따릅니다.
  - `action` 값은 Intent의 action으로 설정됩니다.
  - `packageName` 값은 대상 앱을 지정하는 데 사용됩니다.
  - `actionParameters` JSON 객체는 파싱되며, 각 key-value 쌍이 개별적으로 Intent에 추가됩니다 (putExtra).

- 아래는 startActivity를 사용하여 데이터 전달 및 외부 앱을 실행하는 예시입니다 (`component`="activity").

Sending Side (Gleo AI)

```kotlin
val intent = Intent()
intent.action = "ADD_REMINDER"
intent.putExtra(
    "ACTION_PARAM",
    "\"{\"title\":\"미팅\", \"dateTime\":\"2025-02-20T06:00:00Z\"}\""
)
intent.setPackage("ai.dot42.pleos.reminderapp")
context.startActivity(intent)
```

Receiving Side (3rd-party App)

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        val bundle = intent?.extras
        bundle?.let { data ->
            runCatching {
                val actionParam = data.getString("ACTION_PARAM")
                // ...
            }.onFailure {
                Log.e(logTag, "Failed to get intent extras: $it")
            }
        }
    }
}
```

- 아래는 sendBroadcast를 사용하여 데이터 전달 및 외부 앱을 실행하는 예시입니다 (`component`="service")

Sending Side (Gleo AI)

```kotlin
val intent = Intent()
intent.action = "ADD_REMINDER"
intent.putExtra(
    "ACTION_PARAM",
    "\"{\"title\":\"미팅\", \"dateTime\":\"2025-02-20T06:00:00Z\"}\""
)
intent.setPackage("ai.dot42.pleos.reminderapp")
context.sendBroadcast(intent)
```

Receiving Side (3rd-party App)

```kotlin
class CustomReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        if (intent.action == "ADD_REMINDER") {
            val rawJson = intent.getStringExtra("ACTION_PARAM") ?: "{}"
            try {
                // JSON parsing
                val json = JSONObject(rawJson)
                val title = json.optString("title", "No Title")
                val dateTime = json.optString("dateTime", "No DateTime")

                // To Do..
                Log.d("ReminderReceiver", "Title: $title, DateTime: $dateTime")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ReminderReceiver", "Failed to parse actionParameters")
            }
        }
    }
}
```

### 2. Callback

- **Gleo AI**는 다음 형식의 Broadcast를 수신해야 합니다.

```json
{
  "functionName": "SWITCH_BAND",
  "status": "success",
  "message": "Successfully switched to the requested radio channel.",
  "data": "[{\"frequency\": \"91.9MHz\", \"frequencyBand\": \"FM\", \"stationName\": \"MBC\", \"channelName\": \"MBC FM4U\"}]"
}
```

- **Gleo AI**는 **Android 서비스**이므로 broadcast 수신이 가능합니다.
- 아래는 Gleo AI에 broadcast 응답을 전송하는 샘플 코드입니다.

Sending Side (3rd-party App)

```kotlin
import android.content.Intent
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

fun sendGleoBroadcast(
    functionName: String,
    status: String,
    message: String,
    data: String? = null
) {
    runCatching {
        val jsonObject = JSONObject().apply {
            put("functionName", functionName)
            put("status", status)
            put("message", message)
            data?.let { put("data", it) }
        }

        Intent("ai.pleos.playground.intent.action.APP_ACTION_RESPONSE").apply {
            putExtra("packageName", packageName)
            putExtra("response", jsonObject.toString())
        }.also {
            sendBroadcast(it)
            Log.d("ThirdPartyApp", "respondAction success jsonObject: $jsonObject")
        }
    }.onFailure {
        Log.e("ThirdPartyApp", "respondAction onFailure >> $it")
    }
}
```

Sending Side (3rd-party App)

```kotlin
sendGleoBroadcast(
    functionName = "SWITCH_BAND",
    status = "success",
    message = "Successfully switched to the requested radio channel.",
    data = """[{"frequency":"91.9MHz","frequencyBand":"FM","stationName":"MBC","channelName":"MBC FM4U"}]"""
)
```


---

## Delivering the Schema to Gleo

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/deliver-schema

### App Schema를 Gleo에 전달하기

[이미지: deliver_schema]

[이미지 설명 필요: App Schema를 Gleo에 전달하는 과정 다이어그램. 원본: https://document.pleos.ai/assets/images/deliver_schema-4ad29f564fd2aee3bab3f0a6cafb6405.png]

- App Schema 파일 생성
  - 파일 이름은 반드시 `schema.json`이어야 합니다.
  - 파일은 앱의 `assets/` 디렉토리에 위치시켜야 합니다. (경로 : `assets/schema.json`)
  - App Schema를 작성할 때 [App Schema 작성 가이드](https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/app-capability)를 반드시 따라주세요.
- App Schema 검토
  - App Schema를 전달받으면, 당사 가이드라인을 준수하는지 검토합니다.
  - 승인 후에는 통합이 자동으로 활성화됩니다.
- `AndroidManifest.xml`에 action filter 정의
  - Gleo Action을 수신하려면 각 function에 대한 action filter를 정의해야 합니다.
  - 다음은 리마인더 앱의 `ADD_REMINDER` function 예시입니다.

```json
<activity android:name=".YourActivity">
    <intent-filter>
        <action android:name="ADD_REMINDER" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

- 앱 부팅 시 동작
  - Gleo AI 앱(Gleo AI를 실행하고 타사 앱과 상호작용하는 앱)이 부팅되면, `AssetManager`를 사용하여 모든 시스템 또는 타사 앱을 스캔합니다.
  - `assets/` 디렉토리에서 발견된 모든 `schema.json` 파일은 읽혀 데이터베이스에 저장됩니다.
  - 해당 프로세스는 **부팅 시점에 한 번만** 실행됩니다.
- 앱 설치/삭제 시 동작(AAOS 환경)
  - Gleo AI 앱이 설치(ADB install 또는 앱 마켓을 통해 설치)되거나 삭제될 때, 앱의 `assets/` 디렉토리에서 `schema.json` 파일을 읽고 데이터베이스를 갱신합니다.
- 해당 부분에 대해 추가적인 안내가 필요하다면 이 문서의 저자에게 문의해 주시기 바랍니다.


---

## How to Integrate Radio App with Gleo AI

> 원문: https://document.pleos.ai/docs/pleos-only/app-integration-guide/use-cases/radio-app

### 1. App Schema 예시

- 라디오 앱을 위한 App Schema의 예시는 다음과 같습니다.

```json
  {
    "packageName": "ai.umos.mediaapp",
    "appDescription": "An app for streaming live radio stations.",
    "actionCalls": [
      {
        "type": "function",
        "function": {
          "name": "SWITCH_BAND",
          "description": "Changes between FM and AM radio bands.",
          "parameters": {
            "type": "object",
            "properties": {
              "band": {
                "type": "string",
                "enum": [
                  "FM",
                  "AM"
                ],
                "description": "The radio band to switch to."
              }
            },
            "required": [
              "band"
            ]
          },
          "component": "service"
        }
      },
      {
        "type": "function",
        "function": {
          "name": "SEARCH_STATION",
          "description": "Searches radio stations based on a keyword or frequency.",
          "parameters": {
            "type": "object",
            "properties": {
              "searchTerm": {
                "type": "string",
                "description": "The keyword to search for in station names or frequencies."
              }
            },
            "required": [
              "searchTerm"
            ]
          },
          "component": "activity"
        }
      },
      {
        "type": "function",
        "function": {
          "name": "PLAY_FREQUENCY",
          "description": "Plays the specified radio frequency.",
          "parameters": {
            "type": "object",
            "properties": {
              "frequency": {
                "type": "number",
                "description": "The radio frequency to play. For FM band it can be both in MHz and kHz. AM frequency should be given in kHz."
              }
            },
            "required": [
              "frequency"
            ]
          },
          "component": "service"
        }
      }
    ]
  }
```

Receiving Side (Radio App)

```kotlin
private fun handleIntent(intent: Intent?) {
    if (intent == null) return

    val bundle = intent.extras
    bundle?.let { data ->
        runCatching {
            val actionParam = data.getString("ACTION_PARAM") ?: "{}"
            val json = JSONObject(actionParam)

            when (intent.action) {
                "SEARCH_STATION" -> {
                    val searchTerm = json.optString("searchTerm", "Unknown")
                    Log.d("RadioReceiver", "Searching radio station with search term: $searchTerm")

                    // TODO: Implement logic to play the radio based on searchTerm
                }

                "SWITCH_BAND" -> {
                    val band = json.optString("band", "Unknown")
                    Log.d("RadioReceiver", "Switching band to: $band")

                    // TODO: Implement logic to switch band
                }

                "PLAY_FREQUENCY" -> {
                    // TODO: Implement for PLAY_FREQUENCY
                }

                else -> {
                    Log.w("RadioReceiver", "Unknown action received: ${intent.action}")
                }
            }
        }.onFailure {
            Log.e("RadioReceiver", "Failed to get intent extras: $it")
        }
    }
}
...

private fun sendCallback(functionName: String, status: String, message: String) {
    val response = JSONObject()
    try {
        response.put("functionName", functionName)
        response.put("status", status)
        response.put("message", message)
        response.put("data", data) // optional
    } catch (e: JSONException) {
        e.printStackTrace()
    }

    // Send response back to Gleo AI (e.g., via an API or broadcast)
}
```

- 라디오 앱은 위에서 언급한 각 function에 대한 앱 intent를 생성하고 앱 내에서 해당 처리 로직을 실행합니다.
  - 라디오 앱은 Gleo AI로부터 `SEARCH_STATION(searchTerm="SBS Power FM")`과 같은 intent를 수신합니다. 그런 후 LLM/Search SDK 또는 SQLite 데이터베이스를 사용하여 제공된 검색어를 기반으로 적절한 채널을 검색하고 해당 주파수를 재생합니다.

### 2. Callback 예시

- 라디오 앱에 대한 Callback 예시는 다음과 같습니다.

**SEARCH\_STATION - 성공**

```json
{
  "functionName": "SEARCH_STATION",
  "status": "success",
  "message": "The radio has started playing successfully.",
  "data": "[\n  {\n    \"frequency\": \"91.9MHz\",\n    \"frequencyBand\": \"FM\",\n    \"stationName\": \"MBC\",\n    \"channelName\": \"MBC FM4U\"\n  }\n]"
}
```

**SEARCH\_STATION - 실패**

```json
{
  "functionName": "SEARCH_STATION",
  "status": "fail",
  "message": "Failed to play the requested radio. Please check the station name or frequency."
}
```

**SEARCH\_STATION - 확인 요청 (다수 일치)**

```json
{
  "functionName": "SEARCH_STATION",
  "status": "asking",
  "message": "Multiple stations match your request. Please specify the frequency or channel name.",
  "data": "[\n  {\n    \"frequency\": \"91.9MHz\",\n    \"frequencyBand\": \"FM\",\n    \"stationName\": \"MBC\",\n    \"channelName\": \"MBC FM4U\"\n  },\n  {\n    \"frequency\": \"95.9MHz\",\n    \"frequencyBand\": \"FM\",\n    \"stationName\": \"MBC\",\n    \"channelName\": \"MBC Standard FM\"\n  }\n]"
}
```
