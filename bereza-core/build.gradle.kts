import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    jvm()

    androidLibrary {
        namespace = "com.github.RavenZIP.bereza.ui.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
    }

    js(IR) { browser() }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("bereza-ui-core")
        browser { commonWebpackConfig { outputFileName = "bereza-ui-core.js" } }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.ravenzip.kotlinReactiveForms.core)
            implementation(libs.ravenzip.krex.core)
        }

        commonTest.dependencies { implementation(libs.kotlin.test) }
    }
}
