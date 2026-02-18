import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    target { compilerOptions { jvmTarget.set(JvmTarget.JVM_17) } }

    OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.berezaApp.shared)
        implementation(libs.androidx.activity.compose)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.androidx.core.ktx)
    }
}

android {
    namespace = "com.github.ravenzip.berezaUI"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.github.ravenzip.berezaUI"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }

    buildTypes { getByName("release") { isMinifyEnabled = false } }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures { compose = true }
}
