rootProject.name = "bereza-ui"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

include("bereza-core")

include("berezaApp:shared", "berezaApp:desktopApp", "berezaApp:androidApp", "berezaApp:webApp")
