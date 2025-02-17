import com.android.build.api.dsl.LibraryExtension
import com.winphyoethu.convention.androidTestImplementation
import com.winphyoethu.convention.configureKotlinAndroid
import com.winphyoethu.convention.implementation
import com.winphyoethu.convention.libs
import com.winphyoethu.convention.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("tpg.android.lint")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                buildTypes {
                    release {
                        isMinifyEnabled = true
                    }
                }
            }

            dependencies {
                implementation(libs.findLibrary("kotlinx-serialization-json").get())

                add("androidTestImplementation", kotlin("test"))
                add("implementation", kotlin("test"))
                testImplementation(libs.findLibrary("junit").get())
                testImplementation(libs.findLibrary("mockito-core").get())
                testImplementation(libs.findLibrary("mockito-kotlin").get())
                testImplementation(libs.findLibrary("coroutine-test").get())
                androidTestImplementation(libs.findLibrary("androidx-junit").get())
            }
        }
    }

}