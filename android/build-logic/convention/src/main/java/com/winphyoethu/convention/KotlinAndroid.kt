package com.winphyoethu.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun Project.configureKotlinAndroid(extension: CommonExtension<*, *, *, *, *, *>) {
    with(extension) {
        compileSdk = COMPILE_SDK

        defaultConfig {
            minSdk = MIN_SDK
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}