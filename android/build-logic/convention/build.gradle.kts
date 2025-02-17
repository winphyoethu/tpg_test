plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationCompose") {
            id = "tpg.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("AndroidApplication") {
            id = "tpg.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("AndroidFeature") {
            id = "tpg.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("AndroidHilt") {
            id = "tpg.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("AndroidLibraryCompose") {
            id = "tpg.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("AndroidLibrary") {
            id = "tpg.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("JvmLibrary") {
            id = "tpg.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidLint") {
            id = "tpg.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("androidRoom") {
            id = "tpg.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}