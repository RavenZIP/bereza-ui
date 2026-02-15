import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.androidMultiplatformLibrary)
}

version = "0.1.0"

kotlin {
    jvm()

    androidLibrary {
        namespace = "com.github.RavenZIP.bereza.ui.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("bereza-ui-core")
        browser { commonWebpackConfig { outputFileName = "bereza-ui-core.js" } }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            implementation(libs.ravenzip.kotlinReactiveForms.core)
            implementation(libs.ravenzip.krex.core)
        }

        androidMain.dependencies { implementation(libs.androidx.lifecycle.runtimeCompose) }

        commonTest.dependencies { implementation(libs.kotlin.test) }
    }
}
