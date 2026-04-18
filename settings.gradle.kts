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

/** Библиотечные модули, доступные публично */
include("bereza-core", "bereza-extensions", "bereza-reactive")

/** Библиотечные модули, недоступные публично */
include("berezaApp:shared", "berezaApp:desktopApp", "berezaApp:androidApp", "berezaApp:webApp")
