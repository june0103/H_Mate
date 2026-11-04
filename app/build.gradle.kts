plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mist.carapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.mist.carapp"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    // 차곡 데이터 화면: 주행 규제 상태(CarUxRestrictionsManager)
    useLibrary("android.car")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.pleos.vehicle)
    implementation(libs.pleos.navihelper)
    implementation(libs.kotlinx.serialization.json)  // 내비 데이터(@Serializable)를 JSON으로 화면에 넘길 때
    implementation(libs.androidx.webkit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}