# 실측 검증 예제 코드 (sdkcheck)

[11-sdk-verification.md](../11-sdk-verification.md)의 결과를 낸 코드입니다. 2026-11-05에 Pleos Connect Emulator v2.1.0에서 **실제로 컴파일되고 동작한** SDK 호출 예시라서, 원문 문서 예제보다 이 코드를 먼저 참고하세요.

| 파일 | 내용 |
|---|---|
| [sdkcheck/build.gradle.kts](sdkcheck/build.gradle.kts) | SDK 5종 의존성 좌표 |
| [sdkcheck/src/main/AndroidManifest.xml](sdkcheck/src/main/AndroidManifest.xml) | `android.hardware.type.automotive` feature, `pleos.car.permission.*` 권한 선언 |
| [sdkcheck/src/main/java/com/mist/sdkcheck/SdkCheckActivity.kt](sdkcheck/src/main/java/com/mist/sdkcheck/SdkCheckActivity.kt) | Vehicle·NaviHelper·TTS·STT·FusedLocation 초기화 → 호출 → 콜백 → 해제. logcat 태그 `PLEOSCHECK` |

`SdkCheckActivity.kt`에서 볼 만한 곳:
- Vehicle: Capability / Get / Set / Callback 각각의 호출 형태, `registerDoorPosition(area, listener)`(원문 예제의 이름 붙은 인자는 컴파일 안 됨), `onFailed`에서 `VehicleException` 대신 일반 `Exception`을 다루는 법, 문서에 없는 `getGear()`·EV·주행가능거리 API
- NaviHelper: `initialize(onConnected, onFailed)`, `NaviHelperEventListener`의 `open fun` 오버라이드
- TTS: `initialize(listener)` → `onSuccess` 안에서 `addEventListener`/`speak` (순서를 바꾸면 예외)
- `onDestroy`에서 SDK 5종 모두 `release()`

## 새 프로젝트에서 돌려 보려면

1. 폴더 `sdkcheck/`를 프로젝트 루트로 복사하고 `settings.gradle.kts`에 `include(":sdkcheck")`를 넣습니다.
2. `settings.gradle.kts`의 `dependencyResolutionManagement.repositories`에 Pleos Maven 저장소를 넣습니다.
   ```kotlin
   maven { url = uri("https://nexus-playground.pleos.ai/repository/maven-releases/") }
   ```
3. `build.gradle.kts`는 version catalog의 `libs.plugins.android.application`을 씁니다. 카탈로그가 없으면 `id("com.android.application")`로 바꿉니다. 작성 당시 AGP 9.3.3(Kotlin 내장, `kotlin-android` 플러그인 불필요), compileSdk 37 기준입니다. 패키지 이름 `com.mist.sdkcheck`는 원하는 이름으로 바꿔도 됩니다.
4. 에뮬레이터를 Cold boot로 켜고 아래 순서로 실행합니다. 권한은 `-g`로 한꺼번에 부여됩니다.

```bash
./gradlew :sdkcheck:assembleDebug
```

```bash
adb install -r -g sdkcheck/build/outputs/apk/debug/sdkcheck-debug.apk
```

```bash
adb shell am start -n com.mist.sdkcheck/.SdkCheckActivity
```

```bash
adb logcat -s PLEOSCHECK:I
```

빌드가 `jvm.cfg` 없음 같은 JDK 오류로 실패하면 [README의 빌드 환경 함정](../README.md#빌드-환경-함정)을 보세요.
