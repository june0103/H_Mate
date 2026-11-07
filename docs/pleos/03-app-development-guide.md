# 03. 앱 개발 가이드

오디오 포커스·주행 규제·CCP·내비/음악 앱·TTS·IME·ADB·FAQ 등 개발 가이드 원문 정리.

## 목차

- [Pleos Connect 앱 개발 가이드](#pleos-connect-앱-개발-가이드)
- [오디오 포커스 개발 가이드](#오디오-포커스-개발-가이드)
- [Suspend 모드 - 오디오 포커스 개발 가이드](#suspend-모드---오디오-포커스-개발-가이드)
- [주행 규제 개발 가이드](#주행-규제-개발-가이드)
- [CCP 개발 가이드](#ccp-개발-가이드)
- [Maps/Navigation 앱 개발 가이드](#mapsnavigation-앱-개발-가이드)
- [Music/Audio 앱 개발 가이드](#musicaudio-앱-개발-가이드)
- [TTS / 음성 안내 선행 묵음 적용 가이드](#tts--음성-안내-선행-묵음-적용-가이드)
- [IME 동작에 따른 window resize 처리 가이드](#ime-동작에-따른-window-resize-처리-가이드)
- [FAQ - 디버깅을 위한 주요 ADB Command 활용법](#faq---디버깅을-위한-주요-adb-command-활용법)
- [FAQ - 앱 실행 환경 및 플랫폼 리소스](#faq---앱-실행-환경-및-플랫폼-리소스)


---

## Pleos Connect 앱 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/intro

이 문서는 Pleos Connect 앱 개발 시 반드시 준수해야 할 정책과 그 구현 방법을 안내합니다.

- 오디오 포커스 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/audio-focus-development) — 차량 환경에서 오디오 포커스 정책과 구현 시 고려해야 할 핵심 가이드를 제공합니다.

- Suspend 모드 - 오디오 포커스 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/suspend-mode-audio-focus) — Suspend 모드 진입 및 Resume 시점에서 오디오 포커스를 안전하게 처리하는 원칙과 구현 가이드를 제공합니다.

- 주행 규제 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/driving-restriction-development-guide) — 운전 중 UX 제한을 올바르게 적용할 수 있도록 CarUxRestrictions 연동 방법을 안내합니다.

- CCP 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/ccp-rotary-controller-development) — 앱 분류별 CCP 지원 정책, Pre-installed 앱의 CCP 필수 요건·예외 처리, CCP 심사 기준, Pleos Connect CCP UX 가이드라인 다운로드를 제공합니다.

- Maps/Navigation 앱 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/navigation-app-development-guide) — Maps/Navigation 앱의 안전 중심 개발 정책과 앱 심사 기준을 안내합니다.

- Music/Audio 앱 개발 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/music-audio-app-developement-guide) — Music/Audio 앱의 카테고리 정책, 미디어 연동 구현, 배포 및 심사 기준을 안내합니다.

- TTS / 음성 안내 선행 묵음 적용 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/voice-guidance-preroll-silence-guide) — TTS 및 음성 안내 재생 시 초기 음절 손실을 줄이기 위한 선행 묵음 적용 방법을 안내합니다.

- IME 동작에 따른 window resize 처리 가이드 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/ime-window-resize-handling-guide) — IME 표시 시 window resize 동작 기준과 insets 예외 설정 방법을 안내합니다.

- FAQ - 앱 실행 환경 및 플랫폼 리소스 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/vehicle-app-faq) — 앱 실행 환경 및 플랫폼 리소스를 확인하는 방법을 안내합니다.

- FAQ - 디버깅을 위한 주요 ADB Command 활용법 (https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/adb-command-reference-guide) — ADB 설치, 기기 연결, 화면 캡처·녹화, 앱 설치·실행·삭제, 주행 규제 제어 등 차량 앱 디버깅에 필요한 주요 명령어를 안내합니다.


---

## 오디오 포커스 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/audio-focus-development

차량 안에서는 음악, 내비, 통화, 안전·긴급 안내 등 여러 사운드가 동시에 또는 경쟁적으로 재생될 수 있습니다. 어떤 소리를 재생·중단·거부할지는 아래 정책에 따라 설계하고 구현해야 하며, [AAOS 오디오 포커스](https://source.android.com/docs/automotive/audio/audio-focus?hl=ko)를 바탕으로 하되 Pleos Connect 오디오 포커스 정책을 반드시 따릅니다.

> [INFO] AAOS 오디오 포커스 기본 개념
>
> 다음은 AAOS 오디오 포커스의 기본 개념 및 용어 설명입니다. 실제 설계·구현 시에는 이어지는 Pleos Connect 오디오 포커스 정책을 따릅니다.
>
> #### 포커스 기본 원칙
>
> AAOS는 들어오는 요청의 CarAudioContext와 현재 포커스 보유자(들)의 CarAudioContext 사이의 사전 정의된 Interaction으로 요청을 처리합니다.
>
> #### 포커스 상호작용
>
> - R(Reject): 거부 상호작용. 새 요청은 거부되어 포커스를 얻지 못합니다.
> - E(Exclusive): 배타적 상호작용. 새 요청이 포커스를 획득하고 기존 보유자는 포커스를 상실합니다.
> - C(Concurrent): 동시 상호작용. 조건을 만족하면 기존 보유자와 새 요청이 동시에 포커스를 유지합니다. (일반적으로 **duck** 기반)
>
>
> 중요: 포커스는 오디오 하위 시스템 제어의 유일 수단이 아니라, 충돌 방지/간접 제어 수단입니다.
>
> #### C(Concurrent) 성립 조건
>
> 동시 재생을 의도한다면, 들어오는 요청은 `AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK`을 사용해야 하며(대표), 기존 보유자 설정(예: duck 시 pause 여부)에 따라 C가 깨질 수 있습니다.
>
> #### 포커스 보유자가 여러 명일 때 최종 결론 규칙
>
> C로 인해 **현재 보유자**가 여러 명일 수 있습니다. 이때 새 요청은 각 보유자와의 결과를 비교해 가장 보수적인 결과를 적용합니다.
>
> - 우선순위: R > E > C
>
> #### 통화 중 내비 처리 사용자 설정 (Optional)
>
> AAOS에는 통화 중 내비를 C → R로 바꾸는 사용자 설정이 존재할 수 있으며, 구현 시 플래그로 지원 가능합니다.
>
> #### 지연 가능한 포커스 (Optional)
>
> 비일시적(non-transient) 요청이 상호작용상 R로 거부될 상황이면, AAOS는 “지연 요청”을 지원할 수 있습니다. (요청자가 delayed를 수용하도록 빌드)

### Pleos Connect 오디오 포커스 정책

Pleos Connect 오디오 포커스 정책은 다음 Pleos Connect Interaction Matrix를 따릅니다.

[이미지: Pleos Connect Interaction Matrix v1.0 (2026-01-28)]

[이미지 설명 필요: 행(현재 포커스 보유자 컨텍스트) × 열(새 요청 컨텍스트) 12×12 매트릭스의 각 셀 값(E/C/R)이 이미지로만 제공됨. 원본: https://document.pleos.ai/assets/images/connect-audiofocus-interaction-matrix-ec92c7e71b5963697419f6cca4b75b61.png]

*Pleos Connect Interaction Matrix v1.0 (2026-01-28)*

> [INFO] 범례
>
> - 행(Row): 현재 포커스 보유자의 컨텍스트 (Current Focus Holder’s Context)
> - 열(Column): 새로 들어오는 요청의 컨텍스트 (Incoming Request’s CarAudioContext)
>   - MUSIC, NAVIGATION, VOICE\_COMMAND, CALL\_RING, CALL, ALARM, NOTIFICATION, SYSTEM\_SOUND, EMERGENCY, SAFETY, VEHICLE\_STATUS, ANNOUNCEMENT
> - 셀 값: E/C/R

Suspend 모드 진입 및 Resume 시 오디오 포커스 처리 방법은 [Suspend 모드 오디오 포커스 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/suspend-mode-audio-focus)를 참조하세요.

### 개발사가 구현해야 할 동작 규칙

#### 포커스 요청 규칙 (요청 API 사용)

1. 각 오디오 스트림은 재생 시작 전 `AudioAttributes` + `AudioFocusRequest`로 포커스를 요청해야 합니다.
2. C를 기대하는 컨텍스트(예: NAVIGATION이 MUSIC과 동시)라면 요청은 원칙적으로 `AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK`을 사용해야 합니다.
3. E가 필요한 컨텍스트(예: CALL, EMERGENCY 등)는 목적에 맞는 gain을 사용하되, 포커스로 모든 것을 강제 제어하지 않아야 합니다.

#### 포커스 변경 이벤트 처리 (필수)

포커스 상실/획득 이벤트를 반드시 처리해야 합니다. 포커스 변경 이벤트 처리는 AAOS에서 필수 구현사항은 아니지만 자동차 UX/정책 준수를 위해 구현해야 합니다.

##### 권장 매핑 예시 (앱/서비스 공통)

- `AUDIOFOCUS_GAIN` → 정상 재생
- `AUDIOFOCUS_LOSS` → 즉시 중단/정지
- `AUDIOFOCUS_LOSS_TRANSIENT` → 일시정지 후 복귀 가능
- `AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK` → duck(볼륨 감쇄) 적용

#### 게임/엔터 앱 실행 전환 시 처리 정책

- 게임 앱 실행으로 인한 오디오 포커스 상실 시 처리 정책 및 서비스 생명주기 구현 가이드는 [Music/Audio 앱 개발 가이드](https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/music-audio-app-developement-guide#%EC%98%A4%EB%94%94%EC%98%A4-%ED%8F%AC%EC%BB%A4%EC%8A%A4-%EC%83%81%EC%8B%A4-%EB%B0%8F-%EC%84%B8%EC%85%98-%EC%A0%95%EB%A6%AC-%EA%B0%80%EC%9D%B4%EB%93%9C)를 참조하세요.

#### Duck(감쇄) 구현 규칙

1. C는 보통 **duck 기반 공존**을 의미합니다.
2. Duck 대상(예: MUSIC)은 `LOSS_TRANSIENT_CAN_DUCK` 수신 시 다음 내용을 적용합니다.
   - 볼륨 감쇄(예: -X dB) 적용
   - duck 해제 조건(다른 스트림 종료/포커스 반환)에서 원복
3. Duck 적용 중 pause하도록 구성하면 C가 깨질 수 있으므로, 정책 의도에 맞게 설정/구현합니다.

#### 다중 홀더 상황 처리 (중요)

C로 인해 포커스 보유자가 여러 명일 때, 새 요청의 최종 결과는 R > E > C 우선순위로 **가장 보수적 결과**를 적용합니다.
다음의 내용을 구현해야 합니다.

- result = C로 시작
- 모든 currentHolder에 대해 Pleos Connect Interaction Matrix를 여러 현재 포커스 보유자에게 적용합니다. 다음은 개념적 의사 코드입니다.

  ```text
  cell = MATRIX[currentHolder][incoming]
  result = min_by_severity(result, cell) (R가 가장 강함)
  ```
- result에 따라 grant / steal / fail 처리

### 컨텍스트별 구현 가이드

#### EMERGENCY

EMERGENCY 상황에서 다음의 내용을 구현합니다.

- EMERGENCY가 current일 때 대부분 incoming은 R(긴급음 보호)로 보장됩니다.
- Incoming EMERGENCY는 대부분 current를 E로 선점합니다.
- EMERGENCY-EMERGENCY는 C(표 대각)로 허용 → 테스트에서 **긴급 중 음악/알림 요청이 실패**하는지 반드시 확인합니다.

#### SAFETY

SAFETY 상황에서 다음의 내용을 구현합니다.

- SAFETY는 MUSIC/NAVIGATION/알림 등과는 대체로 C/E로 보장됩니다.
- EMERGENCY가 current면 SAFETY incoming은 R → **Safety는 Emergency 아래**라는 정책 의도가 명확하게 구현되어야 합니다.

#### CALL / CALL\_RING

- CALL은 MUSIC에 대해 incoming이 R(통화 중 음악 금지)이며, NAVIGATION에 대해 incoming이 C(통화 중 내비 안내 허용)로 구현되어야 합니다.
- 단, 통화 중 내비 거부 옵션이 요구되면, 플래그로 NAVIGATION-in-CALL을 C→R로 변경 가능합니다.

#### NAVIGATION ↔ MUSIC

- 두 앱은 C로 공존을 기본으로 합니다.
- 실제 공존을 위해 NAVIGATION은 MAY\_DUCK 포커스 요청을 사용해야 합니다.

#### VOICE\_COMMAND(Gleo) 동시 호출 처리

- Gleo 음성 명령은 일반 오디오보다 우선 처리하되, EMERGENCY, CALL 등 매트릭스에서 Reject로 정의된 컨텍스트에는 우선하지 않습니다.
- Gleo가 호출되어 앱이 `AUDIOFOCUS_LOSS_TRANSIENT`를 수신하면, 즉시 재생을 중단하고 필요 시 오디오 포커스를 반납(abandon)해야 합니다.

### 설정/데이터 형태 권장

#### 매트릭스의 코드/설정화

Pleos Connect 오디오 포커스 정책 변경 대응을 위해 하드코딩 금지를 권장합니다.

- 권장: interaction\_matrix.json 또는 csv로 탑재 후 로딩
- 키: `current_context`, `incoming_context` → 값: E|C|R

#### 로그/디버그 필수 항목

- `incoming context`, `gain type`, `client id`
- current holders 목록
- holder별 matrix 결과(E/C/R)
- 최종 결과(R/E/C) 및 처리(grant/steal/reject)
- duck 적용/해제 이벤트(대상 스트림, 감쇄 값)

### 검증 체크리스트

오디오 포커스 구현 후 다음 테스트 시나리오를 필수적으로 수행해야 합니다.

1. MUSIC 재생 중 NAVIGATION 요청 → C (뮤직 duck + 내비 재생)
2. CALL 중 MUSIC 요청 → R (요청 실패)
3. MUSIC 중 CALL 요청 → (현재 MUSIC row, CALL col = E) → 통화가 뮤직 선점
4. EMERGENCY 재생 중 어떤 요청(MUSIC/NOTIFICATION 등) → R
5. C로 동시 홀더 2명(MUSIC+NAVIGATION) 상태에서 VOICE\_COMMAND 요청
   - 각각의 결과를 비교하여 최종이 보수적으로 적용되는지 확인합니다. (R/E 우선)

### 오디오 컨텍스트

AAOS 오디오 구성을 단순화하기 위해 유사한 용도가 `CarAudioContexts`로 그룹화되었습니다. 이러한 오디오 컨텍스트는 `CarAudioService` 전체에서 라우팅, 볼륨 그룹, 오디오 포커스, 볼륨 낮추기 관리를 정의하는데 사용됩니다. AAOS의 정적 오디오 컨텍스트는 아래에 나열되어 있습니다.

이 표에서는 오디오 컨텍스트와 용도 간의 매핑을 설명합니다. 새 시스템 용도에는 강조 표시된 행이 제공됩니다.

(원문에서 강조 표시된 행: EMERGENCY, SAFETY, VEHICLE\_STATUS, ANNOUNCEMENT)

| CarAudioContext | AttributeUsages |
| --- | --- |
| MUSIC | `MUSIC`, `UNKNOWN`, `GAME`, `MEDIA` |
| NAVIGATION | `NAVIGATION`, `ASSISTANCE_NAVIGATION_GUIDANCE` |
| VOICE\_COMMAND | `VOICE_COMMAND`, `ASSISTANT` |
| CALL\_RING | `CALL_RING`, `NOTIFICATION_RINGTONE` |
| CALL | `CALL`, `VOICE_COMMUNICATION`, `VOICE_COMMUNICATION_SIGNALING` |
| ALARM | `ALARM` |
| NOTIFICATION | `NOTIFICATION`, `NOTIFICATION_*` |
| SYSTEM\_SOUND | `SYSTEM_SOUND`, `ASSISTANCE_SONIFICATION`, `ASSISTANCE_ACCESSIBILITY` |
| EMERGENCY | `EMERGENCY` |
| SAFETY | `SAFETY` |
| VEHICLE\_STATUS | `VEHICLE_STATUS` |
| ANNOUNCEMENT | `ANNOUNCEMENT` |

> [INFO]
>
> - 시스템 컨텍스트는 Android 11 이상에서만 사용할 수 있습니다.
> - 상세 설명은 [자동차 오디오 구성](https://source.android.com/docs/automotive/audio/audio-policy-configuration?hl=ko) 문서를 참조합니다.


---

## Suspend 모드 - 오디오 포커스 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/audio-focus-development-guide/suspend-mode-audio-focus

### Suspend 모드 정의

Suspend 모드는 차량의 시동(IGN)이 OFF된 이후, 시스템이 완전히 종료되지 않고 저전력 상태로 진입하는 운영 상태를 의미합니다.
이 상태에서 대부분의 사용자 기능은 중단되지만, 시스템은 빠른 Resume을 위해 메모리와 일부 핵심 기능을 유지합니다.

Suspend 모드의 주요 특징은 다음과 같습니다.

- 사용자 인터랙션이 없는 비활성 상태
- 디스플레이 및 대부분의 UI 기능 비활성화
- 오디오, 미디어 등 일반 서비스는 중단
- 일부 시스템 작업(Garage mode 등)은 제한적으로 수행 가능
- Resume 시 빠른 복구를 위한 상태 유지

Suspend 모드는 완전 종료(shutdown)가 아닌 저전력 유지 상태이며, 앱 관점에서는 '언제든 중단되고 이후 다시 시작될 수 있는 상태'로 간주해야 합니다.

### Suspend 모드에 따른 오디오 포커스 처리

#### 기본 원칙

> [DANGER]
>
> Suspend 모드 진입 시 모든 일반 오디오 세션은 사용자에게 출력되지 않아야 하며 각 앱은 보유 중인 오디오 포커스를 반드시 반납해야 합니다.

핵심 원칙은 다음과 같습니다.

- 오디오 출력은 Suspend 모드 진입 전에 중단되어야 합니다.
- 오디오 포커스를 유지하지 말고, 반드시 명시적으로 반납(abandon)해야 합니다.
- Resume 이후에는 자동으로 포커스를 복원하지 않습니다.
- 오디오 상태는 저장하고 필요 시 재요청 합니다.

#### Suspend 모드 진입 시 처리

Suspend 모드 진입이 감지되면 각 앱은 다음 순서로 처리해야 합니다.

1. 진행 중인 오디오 재생 즉시 중단
2. 오디오 포커스 반납(abandon) 수행
3. 플레이백 상태 저장 (재생 여부, 위치 등)
4. 오디오 관련 리소스 해제 (player, track 등)

> [DANGER]
>
> 이 과정에서 새로운 오디오 재생 또는 오디오 포커스 요청은 허용되지 않습니다.

#### 예외 정책

다음 기능은 시스템 정책에 따라 제한적으로 예외가 허용될 수 있습니다.

- 전화 통화 및 긴급 통신
- 안전 관련 경고음
- 시스템 필수 음성 안내

단, 예외 기능은 Suspend 상태에서 사용자 오디오를 지속적으로 제공하기 위한 목적으로 허용되어서는 안 되며, 예외 적용 범위는 최소한으로 제한되어야 합니다.

#### Resume 시 처리

> [DANGER]
>
> Suspend 이후 Resume 시에는 이전 오디오 상태를 그대로 복원하지 않습니다.

- 오디오 포커스는 자동으로 복구되지 않음
- 앱은 현재 상태를 재평가 후 필요 시 포커스를 재요청
- 자동 재생 여부는 OEM 또는 사용자 정책에 따름

#### 준수 사항

각 앱은 다음 사항을 준수해야 합니다.

- Suspend 모드 진입 시 오디오 포커스를 유지하지 않습니다.
- 백그라운드에서 오디오를 계속 재생하지 않습니다.
- Resume을 위해 오디오 포커스를 선점하거나 유지하지 않습니다.

### 예제 (Kotlin)

Suspend 진입 및 Resume 처리를 보여주는 예제 코드입니다.

```kotlin
import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PlayerActivity : AppCompatActivity() {

    private lateinit var audioManager: AudioManager

    // 예시용 플레이어 상태
    private var isPlaying: Boolean = false
    private var lastPlaybackPositionMs: Long = 0L
    private var hasAudioFocus: Boolean = false

    private val focusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                pausePlayback()
                abandonAudioFocus()
            }
        }
    }

    private val audioFocusRequest: AudioFocusRequest by lazy {
        AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
            .setOnAudioFocusChangeListener(focusChangeListener)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    fun startPlayback() {
        if (!requestAudioFocus()) return

        // TODO: 실제 플레이어 start 호출
        isPlaying = true
    }

    fun onSuspendEntering() {
        // 1) 새 작업 시작 금지
        // 2) 진행 중 작업 중단
        pausePlayback()

        // 3) 상태 저장
        savePlaybackState()

        // 4) 오디오 포커스 반납
        abandonAudioFocus()

        // 5) 필요 시 네트워크/타이머/센서 리소스 정리
        releaseTransientResources()
    }

    fun onResumeFromSuspend() {
        // Resume 후 자동 재생을 바로 하지 않고,
        // 현재 시스템 상태/사용자 조건을 다시 확인한 다음 재개 여부 판단
        restorePlaybackState()

        val shouldAutoResume = false // OEM/앱 정책에 따라 결정
        if (shouldAutoResume) {
            startPlayback()
        }
    }

    private fun requestAudioFocus(): Boolean {
        val result = audioManager.requestAudioFocus(audioFocusRequest)
        hasAudioFocus = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        return hasAudioFocus
    }

    private fun abandonAudioFocus() {
        if (!hasAudioFocus) return
        audioManager.abandonAudioFocusRequest(audioFocusRequest)
        hasAudioFocus = false
    }

    private fun pausePlayback() {
        if (!isPlaying) return

        // TODO: 실제 플레이어 현재 위치 조회
        lastPlaybackPositionMs = getCurrentPlaybackPosition()

        // TODO: 실제 플레이어 pause/stop 호출
        isPlaying = false
    }

    private fun savePlaybackState() {
        // TODO: SharedPreferences/DB/ViewModel 등에 저장
        // 예: 재생 위치, 현재 콘텐츠 ID, 재생 여부
    }

    private fun restorePlaybackState() {
        // TODO: 저장된 상태 복원
    }

    private fun releaseTransientResources() {
        // TODO: 타이머 취소, 센서/리스너 해제, 임시 wake lock 정리 등
    }

    private fun getCurrentPlaybackPosition(): Long {
        // TODO: 실제 플레이어 position 반환
        return lastPlaybackPositionMs
    }
}
```


---

## 주행 규제 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/driving-restriction-development-guide

Pleos Connect 앱은 플랫폼이 제공하는 `CarUxRestrictions` 상태를 기준으로 주행 중 UX 제한을 적용해야 합니다. 이 문서에서는 `CarUxRestrictions`를 활용한 주행 규제 연동 방법과 앱 구현 시 유의 사항을 설명합니다. 지역·기능별 제한 범위는 [지역별 주행 규제 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy)을, 운전 중 UX 설계 원칙은 [주행 안전을 고려한 설계](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driver-distraction-guideline)를 참조하세요.

### CarUxRestrictions 연동

앱은 AAOS [CarUxRestrictionsManager](https://developer.android.com/reference/android/car/drivingstate/CarUxRestrictionsManager)를 통해 [CarUxRestrictions](https://developer.android.com/reference/android/car/drivingstate/CarUxRestrictions) 값을 얻어 주행 규제 상태를 확인하고, 사양에 맞게 동작하도록 구현합니다.

`CarUxRestrictionsManager`에 `registerListener()`를 등록하면 `onUxRestrictionsChanged()` 콜백으로 변경된 `CarUxRestrictions`를 수신합니다.

DO 미선언 앱의 상황별 사용 가능 여부는 [지역별 주행 규제 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy)을 참조하세요. DO를 선언한 앱도 주행 규제가 활성화되면 `CarUxRestrictions`와 `ActiveRestrictions`를 확인하여 기능별 UX 제한을 직접 적용해야 합니다.

#### 샘플 코드

동영상 재생 제한

```java
if (mCurrentCarUxRestrictions.isRequiresDistractionOptimization()
        && (mCurrentCarUxRestrictions.getActiveRestrictions()
            & CarUxRestrictions.UX_RESTRICTIONS_NO_VIDEO) != 0) {
    stopPlayingVideo();
}
```

#### UX 규제 항목과 CarUxRestrictions 매핑

기능별 `CarUxRestrictions` 플래그는 onUxRestrictionsChanged() 콜백으로 수신한 [CarUxRestrictions](https://developer.android.com/reference/android/car/drivingstate/CarUxRestrictions)의 `ActiveRestrictions`를 기준으로 적용합니다.

- 예: 동영상(DMB)은 `CarUxRestrictions.UX_RESTRICTIONS_NO_VIDEO`로 주행 중 재생 제한 여부를 판단합니다.
- UX 규제 피그마 사양서는 Pleos Playground에 공개하지 않습니다.

플랫폼이 지역별 규제 사양에 맞는 `ActiveRestrictions`를 내려주므로, 앱은 notify로 수신한 `CarUxRestrictions`만 반영하면 됩니다.
키보드·검색 등 기능별 지역 허용·잠금에 대한 정보는 [지역별 주행 규제 정책](https://document.pleos.ai/docs/pleos-only/vehicle-app-planning-guide/driving-restiction-policy)을 참조합니다.


---

## CCP 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/ccp-rotary-controller-development

CCP(Center Control Panel)는 차량 내 물리 버튼 및 하드키를 통해 시스템 기능을 제어하는 입력 장치를 말합니다. CCP 입력 장치에는 Rotary Controller, 다이얼, 물리 버튼 등이 포함됩니다. CCP Back 하드키는 현재 화면의 이전 단계로 이동하거나, 더 이상 이전 화면이 없는 경우 앱 또는 시스템 레벨의 Back 동작을 수행하는 물리 버튼입니다.

### CCP 지원 정책

Pleos Connect 앱은 아래와 같이 분류되며, 분류별로 CCP 지원 요건이 다르게 적용됩니다.

- User-installed 앱 (사용자 설치 앱): Pleos App Market을 통해 배포되며 사용자가 차량에 설치하는 앱입니다.
- Pre-installed 앱 (기본 탑재 앱): 차량 출시 시 기본으로 탑재되어 있는 앱입니다.
- System 앱 (시스템 앱): 플랫폼 또는 OEM이 시스템 이미지에 포함하여 제공하며, 사용자가 설치·삭제할 수 없는 앱입니다.

#### User-installed 앱

User-installed 앱의 경우 CCP 지원 여부는 개발사가 선택할 수 있으며, CCP 미지원 앱도 Pleos App Market 등록 및 배포에 제한이 없습니다. 다만 CCP 미지원 앱은 필수 조작을 터치만으로 완결할 수 있어야 하며, 이는 CCP 미지원 환경에서 사용 불가 상태가 발생하지 않도록 하기 위한 요건입니다.

#### Pre-installed 앱

Pre-installed 앱은 필수적으로 CCP를 지원해야 합니다. Pleos Connect CCP UX 가이드라인에 따라 CCP 연동을 구현합니다.

> [TIP] Pre-installed 앱 CCP 적용 정책
>
> #### Pre-installed 앱 CCP 정책
>
> - Pre-installed 앱 계약서에 "CCP 지원"을 의무 조건으로 명시합니다.
> - 앱은 초기 릴리즈 시점부터 CCP 기반 조작(회전, 클릭, 방향 이동 등)을 지원해야 합니다.
> - CCP 입력과 화면 포커스 이동은 Pleos Connect CCP UX 가이드라인을 준수해야 합니다.
> - 향후 업데이트에서 CCP 기능이 누락되거나 비활성화되는 것을 허용하지 않습니다.
> - CCP 미지원 또는 불완전 지원 시 Pre-installed 앱 승인 불가 처리됩니다.
>
> #### Pre-installed 앱 CCP 적용 예외 정책
>
> Pre-installed 앱이 다음 조건 중 하나 이상을 충족하는 경우 CCP 지원을 필수로 요구하지 않습니다. 이 경우 해당 내용은 계약서에 명시되어야 합니다.
>
> 1. 앱의 기능이 정적 콘텐츠 중심으로 되어 있어 CCP 입력이 불필요한 경우
>    - 화면 이동이나 포커스 기반 조작 요구가 없음
>    - 단순 정보 표시형 앱(예: 고정 정보 패널, 단일 화면 구조 등)
> 2. 브라우저 앱 또는 특정 미디어 앱처럼 CCP 구현이 기술적으로 제한적인 경우
>    - WebView 기반 포커스 조작 제약
>    - Pleos Connect CCP UX 가이드라인에서 CCP 지원을 권고 수준으로 규정한 영역
> 3. 게임처럼 CCP 사용이 UX나 안전성 측면에서 적합하지 않은 경우

#### 게임 앱 CCP 지원 정책

Pre-installed 앱은 원칙적으로 CCP 지원이 필수이나, 게임 앱은 특성상 CCP 지원이 의미 없거나 불가능한 경우 예외로 인정할 수 있습니다.

#### 브라우저 앱 CCP 지원 정책

##### 사용성 보장 요건

Pre-installed 브라우저 앱은 CCP가 있는 차량에서 사용성을 보장하기 위해 다음을 준수해야 합니다.

- CCP 회전 → 스크롤(상/하)
- CCP 클릭 → 링크/버튼 선택
- CCP 좌/우 이동 → 포커스 이동 (적용 범위: 주소창, 북마크/탭/홈, 뒤로/앞으로, 설정, 기본 메뉴)

##### CCP 지원 영역

Pre-installed 브라우저 앱은 CCP 지원을 브라우저 UI 영역에 한정하며, 웹페이지 내부 콘텐츠의 CCP 포커스는 전면 보장을 요구하지 않습니다. HTML 구조에 따라 포커스가 맞지 않거나 복잡한 웹페이지에서 예측 불가 동작이 발생할 수 있기 때문입니다.

#### CCP 미지원 선언 방법

CCP를 지원하지 않는 앱은 `<application>` 요소에 다음 메타데이터를 추가하여 CCP 미지원 앱임을 선언합니다.

AndroidManifest.xml

```xml
  <application
      ...>
      <meta-data
          android:name="com.hmg.rotary.ROTARY_SUPPORTED"
          android:value="false" />
  </application>
```

### CCP Back 하드키 입력 시 앱 숨김 처리 정책 (필수)

앱 내 최상단 포커스(더 이상 이전 화면으로 이동할 수 없는 상태)에서 CCP Back 하드키가 입력되면 앱을 종료하지 않고 숨김(Hide) 처리해야 합니다. 숨김 이후에도 미디어 사운드 출력 등 백그라운드에서 유지되어야 하는 기능은 계속 동작해야 합니다. 백그라운드 동작이 허용된 기능은 해당 기능의 서비스 생명주기와 오디오 포커스 정책에 따라 계속 동작할 수 있습니다.

앱 내 하위 화면 또는 팝업이 있는 경우에는 기존 Back 정책에 따라 이전 화면 이동 또는 팝업 종료를 수행합니다.

#### 앱 숨김 처리 구현 방법

CCP Back 하드키 입력을 처리하기 전에 다음 플랫폼 동작을 확인해야 합니다.

- CCP Back 하드키 입력이 Android 표준 Back 이벤트로 전달되는지 확인합니다.
- 표준 Back 이벤트로 전달되는 경우 `OnBackInvokedDispatcher` 또는 플랫폼에서 지원하는 기존 Back API 중 적절한 API를 사용합니다.
- 팝업, 하위 화면, 최상위 화면을 구분하여 하위 화면에서는 이전 화면 이동 또는 팝업 종료를 수행하고, 최상위 화면에서만 앱을 숨깁니다.

확인 결과 CCP Back 하드키 입력이 Android 표준 Back 이벤트로 전달된다면, 다음과 같이 앱을 숨김 처리합니다

- 앱 숨김 처리는 Android Framework에서 제공하는 [`moveTaskToBack(boolean)`](https://developer.android.com/reference/android/app/Activity#moveTaskToBack(boolean)) API를 사용하여 구현합니다.
- 앱 내 최상단 포커스 상태에서 CCP Back 하드키 입력 시 `moveTaskToBack(true)`를 호출하여 Task를 백그라운드로 이동합니다.
- `finish()` 또는 `finishAffinity()`를 통한 Activity 종료는 수행하지 않습니다.
- Task를 백그라운드로 이동한 경우에도 앱 프로세스가 종료될 수 있으므로 필요한 상태는 별도로 저장하고 복원해야 합니다.

최상단 포커스에서 CCP Back 하드키 입력 시 앱이 종료되거나 `moveTaskToBack(true)` 기반 숨김이 구현되지 않은 경우 심사 반려 대상이 될 수 있습니다.

### CCP 심사 정책

CCP 지원이 필수인 Pre-installed 앱은 Pleos App Market 등록 전 CCP 심사를 거칩니다. 아래는 심사 시 확인하는 항목 예시와 결과 기준입니다.

#### 심사 항목 예시

심사 시 다음 항목을 확인합니다.

- 포커스 이동이 모든 주요 UI 구성요소에서 정상 동작하는지
- CCP 회전 시 리스트/메뉴 스크롤이 자연스러운지
- CCP 클릭 시 선택/확인 입력이 정상 수행되는지
- 역방향 입력(뒤로, 취소)이 정상 처리되는지
- 터치 전용 UI만 있어 CCP 조작이 불가한 영역이 없는지
- CCP 입력 시 안전 관련 동작(운전 중 차단 요소 등)이 준수되는지

#### 심사 결과 기준

- CCP 테스트에 합격하면 Pre-installed 앱으로 등록할 수 있습니다.
- CCP 테스트에 불합격한 경우 수정 요청 항목을 반영한 뒤 재심사를 요청합니다.

### Pleos Connect CCP UX 가이드라인

[이미지: Download][Pleos Connect CCP UX Guideline](https://document.pleos.ai/img/policy/Pleos-Connect-CCP-UX-guideline-20260220.fig)

> [TIP] CCP 정책 준수 유의사항
>
> - CCP 지원 정책은 AAOS 버전, 차량 플랫폼, 차종별 사양에 따라 업데이트될 수 있습니다.
> - CCP는 차량 안전성과 직결되므로 HMI/안전 기준 미준수 시 배포가 제한되거나 기본 탑재 앱에서 제외될 수 있습니다.
> - Pleos Connect CCP UX 가이드라인이 업데이트 될 경우, Pre-installed 앱 개발사에게 앱 업데이트를 요청할 수 있습니다.


---

## Maps/Navigation 앱 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/navigation-app-development-guide

### Maps/Navigation 앱 기본 정책

Pleos Connect의 Maps/Navigation 앱을 개발할 때 반영해야 할 기본 정책은 다음과 같습니다.

- 운전자 안전 최우선
- 단일 경로 · 단일 내비게이션 원칙
- 주행 중 조작 최소화
- 카테고리 기반 기능 제한
- Pleos Connect 플랫폼 API와 AAOS 표준 API를 사용하고 UX 가이드를 준수

### Maps/Navigation 앱 카테고리 선언

모든 앱은 개발 및 등록 시 앱 카테고리를 필수적으로 선언해야 합니다.

#### 카테고리 선언

내비컨테이너(내비앱 고정 표시영역)를 사용하려면 Home screen에서 실행 중인 앱이 Navigation 앱인지 알 수 있어야 하며, 앱 카테고리 정보로 이를 확인합니다. 따라서 앱 개발자는 매니페스트 파일에 인텐트 필터로 앱 카테고리를 선언합니다.

AndroidManifest.xml

```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.APP_MAPS" />
</intent-filter>
```

#### 앱 등록 시 카테고리 지정

매니페스트 파일에 선언된 앱 카테고리를 Pleos App Market 등록 시에도 반영하여야 합니다.

#### 카테고리 불일치 정책

- 선언된 카테고리와 실제 기능 불일치 시 심사에서 즉시 반려됩니다.
- 만약 앱 심사 중 기능이 변경된다면 반드시 재심사를 요청해야 합니다.

### Pleos Connect 경로 정책

#### 단일 경로 정책 (Route Exclusivity Policy)

- AAOS 환경에서는 하나의 Navigation 앱만 Active Route 상태를 가질 수 있기 때문에 복수 Navigation 앱의 음성 안내·경로 출력은 허용하지 않습니다.

  | 상태 | 설명 | 허용 범위 |
  | --- | --- | --- |
  | Active Route | 경로 안내 | 음성·TBT 허용 |
  | Inactive Route | 지도 탐색 상태 | 안내 금지 |
  | Background | 백그라운드 | 모든 안내 금지 |

  > [DANGER]
  >
  > Inactive Route / Background 상태에서 경로 안내 제공 시 정책 위반에 해당합니다. 단, OEM Navigation 앱의 경우 안전규제 대응을 위해서 안전 운행 정보가 제공될 수 있습니다.
- 이러한 경로 배타 정책은 Navigation 앱 간에도 적용되지만 Navigation 앱과 Phone Projection 간에도 적용됩니다.
- 단일 경로 정책을 준수하려면 AAOS의 앱 포커스·경로 소유권을 다루는 다음 API를 사용합니다.

  - android.car 참조를 통한 직접 CarAppFocusManager 핸들링
    - [requestAppFocus @ CarAppFocusManager | API reference | Android Developers](https://developer.android.com/reference/android/car/CarAppFocusManager#requestAppFocus(int,%20android.car.CarAppFocusManager.OnAppFocusOwnershipCallback))
    - [CarAppFocusManager.OnAppFocusChangedListener | API reference | Android Developers](https://developer.android.com/reference/android/car/CarAppFocusManager.OnAppFocusChangedListener)

#### 경로 전환 정책

Pleos Connect에서는 다음 두 가지 경우에만 Navigation 앱의 경로 전환을 허용합니다.

- 사용자의 명시적 선택에 의한 전환
- 기존 Navigation 앱 오류 또는 종료에 의한 전환

Navigation 앱은 경로 전환 시 다음을 반드시 구현해야 합니다.

- 경로 전환 시 사용자가 인지할 수 있는 안내를 제공합니다.
- 사용자 동의 없이 자동으로 전환하지 않습니다.

### 주행 중 조작 최소화 구현

주행 중 Navigation 앱에서 조작 금지 처리해야 하거나 허용 가능한 항목은 다음과 같습니다.

| 주행 중 조작 금지 항목 | 주행 중 허용 항목 |
| --- | --- |
| 복잡한 메뉴 탐색 (3 depth 초과)<br>긴 리스트 스크롤<br>영상, 애니메이션, 광고 노출 | 음성 입력<br>단일 터치 기반 주요 기능<br>즐겨찾기 목적지 선택 |

### UX 심사 가이드

Navigation 앱은 Pleos App Market 등록 시 다음 UX 요건을 충족해야 합니다.

- Pleos Connect 플랫폼 및 AAOS UX 가이드 준수
- 터치 영역 최소 크기 충족
- 주행 핵심 정보 상시 노출
- 주행 중 전체 화면 팝업 금지

### 시스템 연동 및 권한 정책

#### 시스템 연동

- Pleos Connect 및 AAOS 공식 API 사용을 권장합니다.
- 비공식 센서/API 사용은 금지되어 있습니다.

#### 권한 정책

Navigation 앱은 필요한 최소 권한만 요청해야 하며, 다음 정책을 준수합니다.

- 위치 권한: 필수
- 마이크: 음성 입력 시에만 허용
- 불필요한 권한 요청 시 심사에서 반려됩니다.

### 성능·안정성 심사 기준

Navigation 앱은 다음 성능·안정성 요건을 충족해야 합니다.

- 앱 실행·전환 시 지연을 최소화해야 합니다.
- 장시간 주행 시에도 안정적으로 동작해야 합니다.
- Crash, freeze 발생 시 심사에서 반려됩니다.

### 보안 및 개인정보 정책

Navigation 앱은 다음 보안·개인정보 정책을 준수해야 합니다.

- 위치 데이터는 암호화하여 처리해야 합니다.
- HTTPS 통신을 사용해야 합니다.
- 개인정보 처리방침을 반드시 제공해야 합니다.
- 불필요한 위치 데이터 저장은 금지됩니다.

### 금지 정책 (Immediate Reject)

다음에 해당하면 심사에서 즉시 반려됩니다.

- 주행 중 광고 노출
- 영상·게임 요소 포함
- 복수 내비 동시 경로 안내
- 카테고리 위반 기능 제공
- OEM/AAOS 정책 위반


---

## Music/Audio 앱 개발 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/music-audio-app-developement-guide

Pleos Connect에서 동작할 Music/Audio 카테고리 앱을 개발할 때 반영해야 할 사항을 가이드합니다.
Music/Audio 카테고리 앱의 분류·세분화 기준과 미디어 라이브러리 연동 시 사용자 경험 저하를 방지하는 정책을 가이드하며, 이 가이드는 Pleos App Market 등록 시 심사 기준으로 적용됩니다.

### Music/Audio 앱 카테고리

#### 카테고리 정의

Music/Audio 카테고리는 다음과 같은 오디오 중심 서비스를 제공하는 앱을 포함합니다.

- 음악 스트리밍 및 재생
- 팟캐스트, 오디오북, 라디오 콘텐츠 제공
- 차량 내 미디어 재생을 목적으로 하는 오디오 플랫폼

> [INFO]
>
> 영상이 주 기능인 앱은 본 카테고리에 포함되지 않습니다.

#### 카테고리 등록 기준

Pleos App Market Music/Audio 카테고리에 앱을 등록하려면 다음 요건을 충족해야 합니다.

- 오디오 콘텐츠 재생이 앱의 주 기능일 것
- 차량 주행 환경에 적합한 UI/UX를 제공할 것
- 백그라운드 재생 및 차량 미디어 컨트롤 연동을 지원할 것

#### 카테고리 불일치 정책

- 음악 외 부가 기능(커뮤니티, 쇼핑 등)이 포함된 경우에는 오디오 재생이 핵심 기능인지를 기준으로 카테고리 적합성을 판단합니다.
- 단순 보조 기능으로 음악을 제공하는 앱은 심사에서 즉시 반려됩니다.

### 미디어 앱 라이브러리 연동

> [TIP]
>
> 미디어 센터를 사용하는 앱은 차량의 미디어 앱 라이브러리에 자동 등록될 수 있습니다.
> 미디어 센터를 사용하지 않는 앱은 미디어 앱 라이브러리에 표시되지 않습니다.

#### 미디어 앱 라이브러리 연동을 위한 카테고리 세분화 정책

차량 환경에서 미디어 앱 라이브러리 노출은 핵심 사용자 경험 요소입니다. 미디어 앱 라이브러리는 시스템 미디어 브라우저에서 사용자에게 표시되는 앱 및 콘텐츠 목록을 의미합니다. Music/Audio 앱은 콘텐츠 유형에 따라 하위 카테고리를 정의해야 하며, 이렇게 분류된 정보가 미디어 앱 라이브러리와 연동되어 미디어 브라우저, 음성 명령, 스티어링 휠 컨트롤 기반 사용 흐름과 연결됩니다.

| 하위 카테고리 | 구분 원칙 |
| --- | --- |
| 음악(Music) | 아티스트, 앨범, 트랙 기반의 음악 콘텐츠 제공<br>예: 음악 스트리밍 서비스, 로컬 음악 플레이어 |
| 팟캐스트(Podcast) | 에피소드 단위의 음성 콘텐츠 제공<br>구독, 최신 회차, 에피소드 목록 구조를 포함 |
| 오디오북(Audiobook) | 도서 기반 장시간 음성 콘텐츠<br>챕터 단위 탐색 및 재생 위치 저장 기능 포함 |
| 라디오(Radio) | 실시간 스트리밍 또는 방송 기반 오디오 콘텐츠<br>채널/방송국 중심 탐색 구조 |

> [DANGER] 복합 콘텐츠 앱의 처리
>
> - 하위 카테고리 구분이 불명확한 경우 심사에서 반려 사유가 될 수 있습니다.

#### 미디어 앱 라이브러리 연동 정책 (필수)

Music/Audio 카테고리 앱은 다음 정책을 준수해야 합니다.

- 콘텐츠 유형별로 명확한 브라우징 구조 제공
- 시스템 미디어 앱 라이브러리와 연동 시 하위 카테고리(음악/팟캐스트/오디오북/라디오) 정보 전달 필수
- 단일 리스트로 모든 콘텐츠를 혼합 노출하는 방식은 제한될 수 있음

#### 미디어 앱 라이브러리 연동 구현

##### 앱 카테고리 설정

매니페스트 파일에 카테고리 정보를 추가합니다. `<application>` 요소의 `android:appCategory` 속성을 `"audio"`로 설정합니다. 자세한 정보는 [미디어 앱에 Android Automotive OS 지원 추가](https://developer.android.com/training/cars/media/automotive-os?hl=ko)를 참조합니다.

AndroidManifest.xml

```xml
<application
  ...
  android:appCategory="audio" />
```

##### 런처 노출 메타데이터 설정

앱 런처 노출을 위해 다음 메타데이터를 추가합니다.

AndroidManifest.xml

```xml
<application
  ...>
  <meta-data
    android:name="androidx.car.app.launchable"
    android:value="true" />
</application>
```

### Music/Audio 앱 개발 정책

차량에 탑재되는 모든 Music/Audio 앱은 차량 시스템과의 표준 미디어 연동을 위해 MBS(`MediaBrowserService`)를 반드시 사용해야 합니다.

#### 필수 요구사항

- 앱은 Android `MediaBrowserService`(MBS)를 구현해야 합니다.
- 차량 시스템은 MBS를 통해 앱의 콘텐츠 탐색, 재생 상태 확인, 미디어 제어를 수행합니다.
- 앱은 차량 시스템이 제공하는 표준 미디어 인터페이스와 호환되어야 합니다.
- 앱은 Android `MediaSession`을 통해 재생 상태와 제어 기능을 제공해야 합니다.

> [DANGER] 배포 제외 기준
>
> MBS를 구현하지 않은 오디오 앱은 차량 탑재 및 배포 대상에서 제외될 수 있습니다.

#### 미디어 센터 사용 권장 정책

미디어 센터는 MBS 기반의 개발 템플릿입니다. Music/Audio 앱 개발에 미디어 센터 사용을 권장합니다.
미디어 센터를 사용하면 차량 미디어 기능 연동이 용이하며, 미디어 앱 라이브러리 자동 등록, Tune Knob, Media 버튼 연동 등 차량 통합 기능을 활용할 수 있습니다. 미디어 센터를 사용하지 않는 경우에는 동일 기능을 앱에서 별도로 구현하거나 일부 기능을 사용할 수 없습니다.

| 기능 항목 | 미디어 센터 사용 | 미디어 센터 미사용 |
| --- | --- | --- |
| 스티어링 휠 키 지원 | 기본 제공 | 개발 가능하나 별도 구현 필요 (미구현 시 심사 반려 가능) |
| 미디어 앱 라이브러리 등록 | 지원 | 미지원 (라이브러리 미노출) |
| Tune Knob 지원 (다음/이전 곡) | 지원 | 미지원 |
| 차량 Media 버튼 연동 | 지원 | 미지원 |
| 차량 Mode 버튼 연동 | 미지원 | 미지원 |
| 차량 미디어 생태계와의 통합성 | 높음 | 제한적 |
| 추가 개발 부담 | 적음 | 기능별 별도 개발 필요 |

### 차량 미디어 앱 스티어링 휠 키 지원 정책

차량에서 실행되는 모든 미디어 앱은 스티어링 휠 키를 통한 기본 재생 제어를 지원해야 합니다.
기본 재생 제어는 미디어 센터 연동을 권장하며, 다음 두 가지 구현 방식 중 하나를 적용해야 합니다.

1. 미디어 센터 연동
2. Android MediaBrowserService를 사용한 구현

#### 미디어 센터 연동

차량의 미디어 센터를 통해 재생, 일시정지, 정지, 이전 트랙, 다음 트랙 등 기본 제어를 구현해야 합니다.

#### Android MediaBrowserService를 사용한 구현

미디어 센터를 사용하지 않을 경우, 앱은 Android `MediaBrowserService`를 기반으로 스티어링 휠 키를 통한 기본 재생 제어를 지원해야 합니다.

- `MediaSessionCompat`을 등록하고 `MediaSessionCompat.Callback`을 구현해야 합니다.
- 최소한 다음 기능을 지원해야 합니다.
  - 재생, 일시정지, 정지, 미디어 ID 기반 재생, 검색 기반 재생
- 콘텐츠 특성상 가능한 경우 이전 트랙 및 다음 트랙 이동도 지원해야 합니다.
- 앱 연결 시 자동 재생은 금지하며, 사용자의 명시적 동작 또는 시스템 재개 요청에 따라 재생해야 합니다. 자세한 내용은 [Android Developers 가이드](https://developer.android.com/training/cars/media?hl=ko)를 참조하세요.

> [DANGER] 배포 제외 기준
>
> 스티어링 휠 키 동작이 지원되지 않거나 Google 가이드에 맞는 미디어 세션 처리가 구현되지 않은 미디어 앱은 차량 탑재 또는 배포 대상에서 제외될 수 있습니다.

### 개발사가 구현해야 할 동작 규칙

#### 자동 재생 제한 정책

- 프로필이 변경된 경우에도 음원을 자동 재생하면 안 되며, 사용자의 명시적 동작 또는 시스템 재개 요청에 따라 재생해야 합니다.

#### 오디오 포커스 상실 및 세션 정리

- 게임 앱 실행 등으로 `AUDIOFOCUS_LOSS`를 수신한 경우, `MediaSession`이 active 상태로 유지되지 않도록 `stop` 처리 또는 session 비활성화 로직을 적용해야 합니다.
- 샘플 앱 구현 예시는 다음과 같습니다.

```kotlin
private val audioFocusListener = AudioManager.OnAudioFocusChangeListener { focus ->
    when (focus) {
        AudioManager.AUDIOFOCUS_LOSS -> {
            stopPlayback()
            audioManager.abandonAudioFocusRequest(audioFocusRequest)
        }
        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> pausePlayback()
        AudioManager.AUDIOFOCUS_GAIN -> resumePlayback()
    }
}
```

- media 앱이 `bind()`/`unbind()`만 호출하는 경우 `onTaskRemoved()`를 수신하지 못할 수 있으므로, `onCreate()`에서 `startService()`를 호출해 서비스가 Started 상태가 되도록 구성해야 합니다.
- `onStartCommand()` 및 재생 시점에는 `startForeground()`를 호출해야 합니다.
- `onTaskRemoved()`에서는 `stopMedia()` → `stopSelf()` → `super.onTaskRemoved()` 순서로 실행해야 하며, `super.onTaskRemoved()` 호출 이전에 내부 정지 로직을 먼저 수행해야 합니다.
- 쓰리핑거 동작 시 시스템에서 `MediaSessionCompat.Callback.onStop()`이 호출될 수 있으며, 앱은 `onStop()` 수신 시 `ExoPlayer.stop()`을 수행해야 합니다.

### Gleo AI 기능 연동 정책

Pleos Connect에서 동작하는 Music/Audio 앱은 Gleo AI 기능을 사용할 수 있습니다.

Gleo AI는 사용자의 이동 여정을 함께 하며 소통하고 도와주는 AI 에이전트입니다. LLM 기반의 인공지능 기술과 음성 기술을 활용하여 사용자의 음성 명령의 맥락과 대화를 이해하고 필요한 응답과 실행을 제공하여 사용자에게 편리하고 안전한 이동 경험을 제공합니다.

Gleo AI와 연동할 경우 다음 기능을 사용할 수 있습니다.

- “멜론 앱에서 아이유 노래 틀어줘”와 같은 곡 검색 및 재생 음성 기능을 제공할 수 있습니다.
- 곡 검색 외에도 앱의 핵심 기능 중 약 3~5개의 추가 음성 기능을 선택적으로 제공할 수 있습니다.
- 앱 심사 통과 후 Pleos App Market에 출시되면, 사용자는 앱 설치 후 해당 음성 기능을 바로 사용할 수 있습니다.

앱에서 Gleo AI 음성 검색 및 제어 기능을 사용하려면 다음 두 가지 방법 중 하나를 구현해야 합니다.

1. Gleo Interface 구현
2. Android 표준 미디어 API 기반 구현

#### Gleo Interface 구현

Gleo Interface는 Gleo AI와 타사 앱이 차량 내에서 사용자의 음성 명령을 처리하기 위해 서로 상호작용하는 방식을 정의하는 인터페이스입니다.
Gleo Interface를 사용하려면 Gleo AI가 호출할 수 있는 function을 앱에서 구현해야 하며, Gleo AI와 function 간의 호출 방식을 정의한 schema를 등록해야 합니다.

> [INFO] Gleo Interface 구현 가이드
>
> Gleo Interface 구현 방법에 대한 자세한 설명은 [Gleo Interface 구현 가이드](https://document.pleos.ai/docs/pleos-only/app-integration-guide/guide/core-concepts)를 참조하시기 바랍니다.

#### Android 표준 미디어 API 기반 구현

Gleo AI가 MediaAgent를 통해 음악 앱을 제어하는 방식입니다. MediaAgent는 Android 표준 Media3 기반으로 미디어 앱을 제어합니다.
AndroidX Media3 및 AAOS 표준 미디어 API를 준수하여 개발된 음악 앱의 경우, 별도의 Schema를 구현하지 않더라도 음성 기능을 사용할 수 있도록 향후 업데이트를 준비 중에 있습니다.

##### 표준 미디어 정책 준수의 최소 조건

Android 미디어 컨트롤 표준 라이브러리인 Media3를 사용하려면 최소한 다음 사항을 구현해야 합니다.

- `MediaSession` / `MediaLibraryService` 연동
- 재생 / 일시정지 / 다음 곡 / 이전 곡 등 기본 미디어 제어 명령 제공
- (`MediaLibraryService` 연동 시) 검색 관련 명령 제공

기존의 `MediaBrowserService`도 호환 가능하지만, 최신 표준인 Media3 사용을 권장합니다.

##### 곡 검색 기반 음성 기능 지원 조건

곡 검색과 같은 음성 기능을 사용하려면 **Media3 기준 `MediaLibraryService`의 검색 기능**을 반드시 구현해야 합니다. `MediaSession`만 제공되는 경우 기본 재생 제어는 가능하지만 검색 기반 음성 기능은 제한됩니다.

### 심사 및 운영 기준

#### 심사 기준

Pleos App Market Music/Audio 카테고리 앱 등록 시 심사 기준은 다음과 같습니다.

- 카테고리 및 하위 카테고리 정의가 명확한지 확인합니다.
- 미디어 앱 라이브러리 연동 구조가 적합한지 확인합니다.
- 차량 환경에서의 사용성 및 안전성을 평가합니다.

#### 정책 위반 시 조치

정책 위반 시 다음과 같이 조치할 수 있습니다.

- 카테고리 불일치: 수정 요청 또는 심사 반려
- 하위 카테고리 미정의: 기능 보완 요청
- 위의 사항을 반복 위반 시: 앱 노출 제한 또는 등록 중단


---

## TTS / 음성 안내 선행 묵음 적용 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/voice-guidance-preroll-silence-guide

TTS 및 음성 안내 재생 시 발생할 수 있는 초기 음절 손실, 재생 시작부 클리핑(clipping), 디바이스 출력 경로 초기화 지연 문제를 방지하기 위해 재생 시작 전 선행 묵음(pre-roll silence)을 적용하는 방법을 설명합니다.

> [INFO] 적용 범위
>
> - TTS 기반 음성 안내
> - 녹음 음원 기반 안내 멘트
> - 내비게이션, 알림, 상태 안내 등 모든 음성 출력 경로

### 기본 정책

#### 선행 묵음 적용

모든 음성 안내 재생 시작 전 선행 묵음 150 ms ~ 200 ms 적용을 권장합니다.

- 최소 권장값: 150 ms
- 기본 권장 구간: 150 ms ~ 200 ms
- 150 ms 미만은 디바이스 및 재생 환경에 따라 첫 음절 손실 가능성이 있음
- 200 ms 초과 적용은 체감 지연을 유발할 수 있으므로 필요 시에만 예외적으로 사용

#### 적용 원칙

- 음성 안내는 묵음 없이 즉시 재생하지 않습니다.
- 선행 묵음은 일관된 기준으로 적용합니다.
- 서비스/플랫폼/디바이스별로 편차가 있더라도 기본 정책은 동일하게 유지합니다.

#### 적용 위치

선행 묵음은 가능한 한 오디오 데이터 레벨에서 처리합니다.
권장 우선 순위는 다음과 같습니다.

1. PCM/WAV 등 오디오 데이터 앞단에 사일런스 패딩(silence padding) 삽입
2. TTS 결과물 생성 후 후처리로 묵음 추가
3. 플레이어 재생 지연 방식
   - 재생 지연만으로 처리하는 방식은 스케줄링 편차가 커서 권장하지 않습니다.

#### 연속 재생 처리

- 단일 안내 시작 시에는 선행 묵음을 적용합니다.
- 하나의 자연스러운 발화 단위로 이어지는 연속 음성에는 불필요한 추가 묵음을 삽입하지 않습니다.
- 스트림 재생이 끊기거나 새 세션으로 시작되는 경우에는 다시 적용합니다.

### 구현 시 유의사항

선행 묵음 적용과 별도로 아래 조건을 함께 확인해야 합니다.

- 오디오 출력 경로가 사전에 초기화되어 있을 것
- 오디오 포커스 또는 오디오 세션 확보가 완료되어 있을 것
- 디코더/플레이어/출력 디바이스가 재생 가능한 상태일 것

> [DANGER]
>
> 선행 묵음은 초기 클리핑(clipping) 완화 수단이며, 출력 경로 초기화 문제 자체를 대체하지는 않습니다.


---

## IME 동작에 따른 window resize 처리 가이드

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/ime-window-resize-handling-guide

Android Framework는 IME가 표시되면 IME(Input Method Editor) insets 영역만큼 view 크기를 조정하는 기본 동작을 수행합니다.
이 동작은 Pleos Connect 플랫폼 정책이 아니라 AOSP 기본 동작입니다.
이 문서는 앱 유형별로 window resize를 허용할지 제외할지 판단하는 기준과 설정 방법을 제시합니다.

### Pleos Connect 사양

IME 표시 시 Pleos Connect 화면 동작 사양입니다.

| 구분 | 사양 |
| --- | --- |
| EditText 또는 TextField가 포함된 window | IME 표시 시 view가 IME에 의해 위로 밀려 올라가는 것이 사양입니다. |
| EditText 또는 TextField가 없는 window | IME 표시 시 view가 앵커링되지 않아야 합니다. 아래 가이드를 참고해 앱에서 수정이 필요합니다. |

### IME Insets 제외 설정 가이드

Dialog를 표시할 때 insets 적용 대상을 `statusBars`와 `navigationBars`로 한정하면, IME insets의 영향을 받지 않습니다.

#### System Dialog

```kotlin
val params = WindowManager.LayoutParams(...)
val typesToFit = WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
params.setFitInsetsTypes(typesToFit)
windowManager.addView(overlay, params)
```

#### App Dialog

```kotlin
val dialog = Dialog(this)
val window = dialog.window
window?.let { win ->
    val params = win.attributes
    val typesToFit = WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
    params.setFitInsetsTypes(typesToFit)
    window.attributes = params
}
dialog.setContentView(R.layout...)
dialog.show()
```

#### WindowInsetsListener 적용

IME 외 insets(SystemBars)에 대해 view 업데이트가 필요한 경우 `WindowInsetsListener`에서 개별 처리해야 합니다.

- [View.OnApplyWindowInsetsListener](https://developer.android.com/reference/android/view/View.OnApplyWindowInsetsListener)

> [INFO] Activity 유형 처리
>
> Activity 유형은 아래 Google 가이드를 기준으로 별도 처리 검토가 필요합니다.
> [Handle input method visibility](https://developer.android.com/develop/ui/views/touch-and-input/keyboard-input/visibility#Respond)


---

## FAQ - 디버깅을 위한 주요 ADB Command 활용법

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/adb-command-reference-guide

### ADB 설치 방법

설치 방법 펼쳐보기

#### Mac

Homebrew를 사용하여 adb를 설치합니다. 직접 설치할 경우 환경변수 셋업을 직접 해줘야 합니다.

1. 먼저 Homebrew를 설치합니다.

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

2. Homebrew를 사용하여 adb를 설치합니다.

```bash
brew install android-platform-tools
```

3. 설치가 잘 되었는지 확인합니다.

```bash
adb version
```

만약 `adb` 명령어가 실행할 수 있는 프로그램으로 인식되지 않을 경우, 환경변수 `PATH`에 adb 경로가 반영되어 있는지 확인합니다.

#### Windows

1. Chocolatey나 Winget을 사용하여 adb를 설치합니다.

Chocolatey 사용

```powershell
choco install adb
```

Winget 사용

```powershell
winget install Google.AndroidSDKPlatformTools
```

2. 설치가 잘 되었는지 확인합니다.

```powershell
adb version
```

#### 리눅스

##### Ubuntu / Debian 계열

1. apt 패키지 매니저를 사용하여 adb를 설치합니다.

```bash
sudo apt update
sudo apt install android-tools-adb android-tools-fastboot
```

2. 설치가 잘 되었는지 확인합니다.

```bash
adb version
```

### ADB 연결 및 기기 확인

연결된 기기 정보를 조회하는 명령어입니다. 식별번호가 나타나지 않는다면, 연결을 재시도 하시기 바랍니다.

명령어 입력

```sh
adb devices
```

결과 예시

```sh
List of devices attached
emulator-5554	device
```

### CCS 연결

기기에서 서비스에 연결하는 명령어입니다. 이 명령어를 활용하여 블루링크, 제네시스 커넥티드 카서비스 등에 연결합니다.

> [CAUTION] CCS 차량 전용 명령어
>
> 아래 명령어는 CCS 차량에서 서비스 연결을 위해 필요한 명령어입니다.
> Devbox와 Pleos Connect Emulator에서는 지원되지 않으므로, 해당 환경에서는 실행하거나 CCS 연결 테스트에 사용하지 마시기 바랍니다.

```sh
adb shell settings put secure hmg_is_ccs_account_linked 1
adb shell settings put global hmg_provisioning_status 4
```

### 화면 캡처

기기에 연결하여 스크립샷을 캡처하는 명령어입니다. 캡처된 스크린샷은 `[screen_현재시간.png]` 이름으로 바탕화면에 저장됩니다.

```sh
adb exec-out screencap -p > ~/Desktop/screen_$(date +"%Y%m%d_%H%M%S").png
```

### 화면 영상 촬영

기기에 연결된 상태에서 화면의 영상을 촬영하는 명령어입니다.

1. 영상 포맷 프로그램 설치

Mac 예시

```sh
brew install ffmpeg
```

2. 촬영 시작

```sh
adb exec-out screenrecord --output-format=h264 - > ~/Desktop/screen.h264
```

3. 촬영 종료
   녹화 프로세스가 진행 중인 동일 터미널에서 `Ctrl+C`를 눌러 촬영을 종료합니다.
4. 포맷
   영상 파일 포맷을 변환합니다. 여기서는 h.264 파일을 mp4 컨테이너로 재패키징 합니다.
   여기까지 하면 PC에서 캡처한 영상을 볼 수 있습니다.

Mac/Linux 예시

```sh
ffmpeg -framerate 60 -i ~/Desktop/screen.h264 -c copy ~/Desktop/screen_$(date +"%Y%m%d_%H%M%S").mp4
```

### 앱 설치 / 실행 / 종료 / 삭제

#### 앱 설치

앱을 설치하는 명령어입니다.

```sh
adb install -d -r [apk파일주소]
# adb install -d -r app-my-signed.apk
```

> 옵션 설명
> -d : 다운그레이드 허용
> -r : 로컬 정보 제거

위 방법이 실패한 경우 다음 명령어로 앱 설치를 시도할 수 있습니다.

```sh
adb push [apk파일주소] /data/local/tmp/
adb shell pm install -d -r /data/local/tmp/[apk파일이름]

# adb push app-my-signed.apk /data/local/tmp/
# adb shell pm install -d -r /data/local/tmp/app-my-signed.apk
```

#### 앱 실행

앱 패키지를 찾아서 앱을 실행시키는 방법을 설명합니다.

패키지 찾기

```sh
adb shell pm list packages | grep [패키지명 중 일부내용]
# adb shell pm list packages | grep radioline
```

앱 실행: 1) 런처 실행

```sh
adb shell monkey -p [패키지이름] -c android.intent.category.LAUNCHER 1
# adb shell monkey -p ai.umos.appmarket -c android.intent.category.LAUNCHER 1
```

- Global Navigation Bar(GNB)에서 앱을 선택하여 실행하는 것과 유사한 동작으로 실행됩니다.

앱 실행: 2) 액티비티 직접 실행

```sh
adb shell am start -n [패키지이름]/[Activity 경로]
# adb shell am start -n com.radioline.android.radioline/.MainActivity
```

#### 앱 삭제

앱을 삭제하는 명령어입니다. 앱을 삭제한 후에 패키지를 비워줍니다.

앱 삭제

```sh
adb uninstall [패키지이름]
# adb uninstall ai.umos.appmarket
```

패키지 비우기

```sh
adb shell pm clear [패키지이름]
# adb shell pm clear ai.umos.appmarket
```

#### 앱 종료

앱을 강제 종료하는 명령어입니다.

앱 강제 종료

```sh
adb shell am force-stop [패키지이름]
# adb shell am force-stop ai.umos.appmarket
```

### UI 텍스트 입력

에뮬레이터 화면에 텍스트를 입력하는 명령어입니다.

텍스트 입력

```sh
adb shell input text "[텍스트]"
# adb shell input text "test"
```

### 기어 상태 변경

Pleos Connect Virtual Console에서 기어 모드를 변경합니다.

자세한 내용은 [Pleos Connect Virtual Console 사용 가이드](https://document.pleos.ai/docs/pleos-only/app-test-guide/virtual-console-guide)를 참조합니다.

### 주행 규제 활성화/해제

주행 규제를 활성화하는 단계별 명령어와 활성화한 주행 규제를 해제하는 명령어입니다.

모든 차종에서 공통으로 사용할 수 있는 명령어입니다.

- 주행 규제 활성화 (ON)

```sh
adb shell cmd hmg_car_service --service drivingstate driving_regulation on
```

- 주행 규제 활성화 해제 (OFF)

```sh
adb shell cmd hmg_car_service --service drivingstate driving_regulation off
```

### 모드 변경

유틸리티 모드로 변경하는 명령어입니다.

유틸리티 모드 명령어는 (차속 0 / Parking Break - On / Gear - P )를 먼저 수행한 후에 입력합니다.

```sh
adb shell echo -e -n "\x83\x50\x10\x00\x00\x02\xAA\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00" > /dev/vcs_simulator_rx
```


---

## FAQ - 앱 실행 환경 및 플랫폼 리소스

> 원문: https://document.pleos.ai/docs/pleos-only/vehicle-app-developer-guide/vehicle-app-faq

### 실행 플랫폼 확인

앱이 어떤 기기 또는 환경에서 실행 중인지 확인하려면 디바이스 식별 값을 읽어야 합니다.

`android.os.Build.DEVICE`를 사용하여 `ro.product.device`에 해당하는 값을 확인합니다.

이 값은 실차에서만 유효하게 제공되므로, 에뮬레이터에서는 플랫폼을 구분하는 용도로 사용할 수 없습니다.

플랫폼별 반환 값은 다음과 같습니다.

- Connect-S: `CONNECT_S`
- Connect-L: `CONNECT_L`
- Connect-W: `CONNECT_W`

### 지역 코드 확인

앱은 `product.sw.target_region`으로 지역을 구분하고, 지역별 UX 정책을 적용해야 합니다. 지역별 값은 다음과 같습니다.

- 한국 지역: `KOR`
- 유럽 지역: `EUR`
- 북미 지역: `USA`, `CAN` (미국, 캐나다)

### 내비게이션 탑재 여부 확인

차량의 내비게이션 탑재 여부를 확인하는 방법은 현재 퍼블릭 오픈하지 않고 있습니다. 자세한 사항은 [partnership@pleos.ai](mailto:partnership@pleos.ai)로 문의하시기 바랍니다.

### 클러스터 속도 조회 방법

Pleos Connect 클러스터에 보이는 속도와 앱에 표시하는 속도는 데이터 출처가 다르면 달라질 수 있습니다. 예를 들어 앱이 GPS로 속도를 계산하면, 클러스터가 쓰는 차량 신호 기반 속도와 어긋날 수 있습니다.

Pleos Connect 클러스터는 구글 `PERF_VEHICLE_SPEED` 프로퍼티로 차량 속도를 표시하므로, 클러스터와 동일한 속도를 쓰려면 `CarPropertyManager`로 `PERF_VEHICLE_SPEED`를 읽습니다.

앱에서 클러스터와 동일한 차량 속도를 사용하려면 AAOS의 `CarPropertyManager`를 통해 `PERF_VEHICLE_SPEED` 프로퍼티를 조회합니다. 필요한 권한을 선언한 후 `CarPropertyManager`를 획득하고, 현재 값 조회 또는 변경 이벤트 구독 API를 사용합니다.

API 사용 방법과 버전별 상세 내용은 구글의 [CarPropertyManager 공식 문서](https://developer.android.com/reference/android/car/hardware/property/CarPropertyManager)와 [`PERF_VEHICLE_SPEED` 프로퍼티 문서](https://developer.android.com/reference/android/car/VehiclePropertyIds#PERF_VEHICLE_SPEED)를 참조합니다.

### 배터리 정보 확인 방법

차량의 배터리 충전 상태를 확인하려면 Vehicle SDK의 [`getEvBatteryStateOfCharge` API](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/Vehicle/vehicle/ai.pleos.playground.vehicle.api/ev-battery/get-ev-battery-state-of-charge) 문서를 참조합니다.

### 미디어 코덱 지원 정보

실차 환경에서 지원되는 미디어 코덱은 플랫폼에 따라 다르므로, 자세한 내용은 이 섹션의 플랫폼별 정보를 확인하시기 바랍니다.
Pleos Connect Emulator에서 지원되는 미디어 코덱은 실차 환경과 다를 수 있으므로, 에뮬레이터의 지원 결과를 실차 환경에 그대로 적용하지 마시기 바랍니다.

#### 플랫폼별 미디어 코덱 정보

##### Connect-S/L 코덱 정보

| 미디어 유형 | 지원 코덱 |
| --- | --- |
| 오디오 | FLAC, AC3+, ALAC, APE, PCM, TTA, AIFF, WMA, OGG, DSD, DFF/DSF, DTS, WAV, MP2, MP3, BSAC, ADPCM, HE-AAC, HE-AAC+, AAC, EAC3, EAC3+JOC, OPUS, AMR 등 |
| 비디오 | MPEG-1, MPEG-2, VC-1, WMV, H.263, MP42, MP43, MPEG4 Part2, Xvid, VP6, VP7, VP8, VP9, H.264, HEVC(H.265) – main 10 profile(기타 포함), x265, AV1 등 |

#### Connect-W 코덱 정보

| 미디어 유형 | 지원 코덱 |
| --- | --- |
| 오디오 | PCM, AAC, MPEG, AMR, WMA, Opus, Vorbis, FLAC, ALAC, APE, Dolby, DTS, ADPCM, AIFF, TTA, BSAC |
| 비디오 | MPEG-1, MPEG-2, MPEG-4 Part 2, H.263, H.264 (AVC), H.265 (HEVC), AV1, VP8, VP9 |

#### Pleos Connect Emulator 코덱 정보

| 미디어 유형 | 지원 코덱 |
| --- | --- |
| 오디오 디코더 | AMR-NB: c2.android.amrnb.decoder<br>AMR-WB: c2.android.amrwb.decoder<br>FLAC: c2.android.flac.decoder<br>G.711 A-law: c2.android.g711.alaw.decoder<br>G.711 μ-law: c2.android.g711.mlaw.decoder<br>AAC: c2.android.aac.decoder<br>MP3: c2.android.mp3.decoder<br>Opus: c2.android.opus.decoder<br>PCM Raw: c2.android.raw.decoder<br>Vorbis: c2.android.vorbis.decoder |
| 비디오 디코더 | H.263: c2.android.h263.decoder<br>AV1: c2.android.av1.decoder<br>AVC/H.264: c2.android.avc.decoder<br>HEVC/H.265: c2.android.hevc.decoder<br>MPEG-4 Part 2: c2.android.mpeg4.decoder<br>VP8: c2.android.vp8.decoder<br>VP9: c2.android.vp9.decoder |
| 오디오 인코더 | AMR-NB: c2.android.amrnb.encoder<br>AMR-WB: c2.android.amrwb.encoder<br>FLAC: c2.android.flac.encoder<br>AAC: c2.android.aac.encoder<br>Opus: c2.android.opus.encoder |
| 비디오 인코더 | H.263: c2.android.h263.encoder<br>AV1: c2.android.av1.encoder<br>AVC/H.264: c2.android.avc.encoder<br>HEVC/H.265: c2.android.hevc.encoder<br>MPEG-4 Part 2: c2.android.mpeg4.encoder<br>VP8: c2.android.vp8.encoder<br>VP9: c2.android.vp9.encoder |
