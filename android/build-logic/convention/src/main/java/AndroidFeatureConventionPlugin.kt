import com.winphyoethu.convention.implementation
import com.winphyoethu.convention.implementationProject
import com.winphyoethu.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("tpg.android.library")
                apply("tpg.android.hilt")
            }

            dependencies {
                implementationProject(":core:designsystem")

                implementation(libs.findLibrary("androidx-hilt-navigation-compose").get())
                implementation(libs.findLibrary("androidx-navigation-compose").get())
                implementation(libs.findLibrary("androidx-lifecycle-viewModelCompose").get())
            }
        }
    }

}