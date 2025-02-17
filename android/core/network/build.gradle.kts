import java.util.Properties

plugins {
    id("tpg.android.library")
    id("tpg.android.hilt")
}

private val tpgProperties = Properties().apply {
    load(rootProject.file("tpg.properties").inputStream())
}

private val baseUrl = tpgProperties.getProperty("BASE_URL")

android {
    namespace = "com.winphyoethu.tpg.core.network"

    defaultConfig {
        buildConfigField("String", "BASE_URL", baseUrl)

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

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    api(libs.retrofit.core)
    api(libs.moshi)
    api(libs.moshi.adapters)
    api(libs.moshi.kotlin)
    api(libs.moshi.converter)
    implementation(libs.okhttp.logging)
    testImplementation(libs.junit)

}