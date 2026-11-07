// docs/pleos 문서에 적힌 대로 Pleos Connect SDK 5종을 불러와 에뮬레이터에서 동작을 확인하는 검증용 모듈.
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mist.sdkcheck"
    compileSdk {
        version = release(37)
    }
    defaultConfig {
        applicationId = "com.mist.sdkcheck"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // 각 SDK Importing 페이지에 적힌 좌표 그대로
    implementation("ai.pleos.playground:Vehicle:2.3.2")
    implementation("ai.pleos.playground:NaviHelper:3.0.5")
    implementation("ai.pleos.playground:TextToSpeech:3.3.1")
    implementation("ai.pleos.playground:SpeechToText:3.3.1")
    implementation("ai.pleos.playground:FusedLocation:1.1.3")
}
