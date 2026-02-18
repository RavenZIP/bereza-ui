import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(projects.berezaApp.shared)

        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutinesSwing)
    }
}

compose.desktop {
    application {
        mainClass = "com.github.ravenzip.berezaUI.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "bereza-ui"
            packageVersion = "1.0.0"
        }
    }
}
