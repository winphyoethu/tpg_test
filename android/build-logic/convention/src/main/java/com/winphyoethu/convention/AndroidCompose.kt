package com.winphyoethu.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroidCompose(extension: CommonExtension<*, *, *, *, *, *>) {
    with(extension) {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.15"
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()

            implementationBom(bom)
            implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
            debugImplementation(libs.findLibrary("androidx-ui-tooling").get())
            androidTestImplementation(bom)
        }
    }
}