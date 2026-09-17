<p align="center">

<img alt="Logo" src="../images/logo.png" width="100%">

<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.4.20-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose Multiplatform-1.12.0-3b83f8">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
<a href="https://jitpack.io/#RavenZIP/bereza-ui">
  <img src="https://jitpack.io/v/RavenZIP/bereza-ui.svg">
</a>
</p>

> 🌐 **Languages:**  
> [English](README-EN.md) | [Русский](../README.md)

## 🔎 What is Bereza UI?

Bereza UI is a component library for Compose Multiplatform that extends the Material3 library,
providing additional UI components and tools not found in the Material library.

## 🌍 Supported platforms

Coming soon...

## 🌳 Project Structure

### - bereza-core

The library's core module.

### - berezaApp

A demo application for the library. It allows you to launch Bereza UI, explore available components, and test their
functionality in real-world scenarios.

### - docs

Project documentation.

### - images

Images used in the README and documentation.

## 🚀 Installation

**settings.gradle.kts**

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven ("https://jitpack.io")
    }
}
```

**build.gradle.kts**

```
dependencies {
    implementation("com.github.RavenZIP.bereza-ui:bereza-core:$version") 
}
```

If you are using libs.versions.toml

**libs.versions.toml**

```
[versions]
ravenzip-bereza-ui = "$version"

[libraries]
ravenzip-bereza-ui-core = { module = "com.github.RavenZIP.bereza-ui:bereza-core", version.ref = "ravenzip-bereza-ui" }
```

**build.gradle.kts**

```
dependencies {
    implementation(libs.ravenzip.bereza.ui.core)
}
```

## 🚬 Using

Coming Soon... See berezaApp module

## 📜 License

This library is licensed under the Apache 2.0 License. See the [LICENSE](../LICENSE) file for details.

## 👾 Developer

**Alexander Chernykh**

- [Telegram](https://t.me/RavenZIP)