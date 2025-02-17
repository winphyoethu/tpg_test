pluginManagement {
    includeBuild("../node_modules/@react-native/gradle-plugin")
    repositories {
        includeBuild("build-logic")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins { id("com.facebook.react.settings") }

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

extensions.configure<com.facebook.react.ReactSettingsExtension> { autolinkLibrariesFromCommand() }

rootProject.name = "android"
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:network")
//include(":core:data")
include(":core:database")
include(":features:character")
include(":core:model")
include(":domain")
include(":core:data:character")

includeBuild("../node_modules/@react-native/gradle-plugin")