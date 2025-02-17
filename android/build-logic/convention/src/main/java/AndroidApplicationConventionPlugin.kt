import com.android.build.api.dsl.ApplicationExtension
import com.winphyoethu.convention.TARGET_SDK
import com.winphyoethu.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.application")
                apply("tpg.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = TARGET_SDK
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                buildTypes {
                    release {
                        isMinifyEnabled = true
                    }
                }
            }
        }
    }

}