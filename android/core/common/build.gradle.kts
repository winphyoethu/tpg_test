plugins {
    id("tpg.android.library")
    id("tpg.android.hilt")
}

android {
    namespace = "com.winphyoethu.tpg.core.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    api(libs.kotlinx.collections.immutable)

}