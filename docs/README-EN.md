<p align="center">
<img alt="Logo" src="images/logo.png" width="100%">
</p>
<p align="center">
<strong>Additional UI components for Compose Multiplatform</strong><br>
Bereza UI extends the standard Material 3 component set with tools for
building modern, complex interfaces.
</p>
<p align="center">
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.4.20-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose%20Multiplatform-1.12.0-3b83f8">
<img alt="API" src="https://img.shields.io/badge/API-24%2B-39ad31">
<a href="https://jitpack.io/#RavenZIP/bereza-ui">
<img alt="JitPack" src="https://jitpack.io/v/RavenZIP/bereza-ui.svg">
</a>
</p>
<p align="center">
<a href="../README.md">Русский</a> · <a href="README-EN.md">English</a>
</p>

## ✨ About

**Bereza UI** is a UI component library for Compose Multiplatform that
extends the standard Material 3 library with components that may be
missing when building an application.

## 🧩 Components

Below are some of the most notable component groups. For the complete
list, see the project source code. More detailed documentation will be
added in the future.

- **Selection from a predefined list** — `Select`, `MultiSelect`.
- **Autocomplete and dynamic suggestions** — `AutocompleteTextField`,
  `MultiAutocomplete`.
- **Toggle groups** — `CheckBoxGroup`, `RadioGroup`, `SwitchGroup`.
- **Containers** — `ExpandableCard`.

## 🌍 Supported platforms

| Platform | Status           |
|----------|------------------|
| Windows  | ✅ Supported     |
| Linux    | ❓ Unknown       |
| macOS    | ❓ Unknown       |
| Web      | ✅ Supported     |
| Android  | ✅ Supported     |
| iOS      | ❌ Not supported |

macOS and iOS are currently not supported because the library cannot be
tested on these platforms.

## 🚀 Installation

### 1. Add the JitPack repository

In `settings.gradle.kts`, add JitPack to
`dependencyResolutionManagement.repositories`:

``` kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Other repositories
        maven("https://jitpack.io")
    }
}
```

### 2. Add the dependency

Replace `$version` with the version you want to use from
[JitPack](https://jitpack.io/#RavenZIP/bereza-ui).

In your module’s `build.gradle.kts`:

``` kotlin
dependencies {
    implementation("com.github.RavenZIP.bereza-ui:bereza-core:$version")
}
```

If you use `libs.versions.toml`, add the dependency to your version
catalog:

``` toml
[versions]
ravenzip-bereza-ui = "$version"

[libraries]
ravenzip-bereza-ui-core = { module = "com.github.RavenZIP.bereza-ui:bereza-core", version.ref = "ravenzip-bereza-ui" }
```

Then use it in `build.gradle.kts`:

``` kotlin
dependencies {
    implementation(libs.ravenzip.bereza.ui.core)
}
```

## 🔗 Integrations

[Kotlin Reactive
Forms](https://github.com/RavenZIP/kotlin-reactive-forms) — a library
for reactive form state management that provides UI component
integration through `FormControl`.

## 🗂️ Project structure

- **`bereza-core`** — the main library module containing UI components.
- **`berezaApp`** — a demo application.
- **`docs`** — project documentation.
- **`images`** — images used in the README and documentation.

## 📜 License

This library is distributed under the Apache License 2.0. See the
[LICENSE](../LICENSE) file for details.

## 👤 Author

**Alexander Chernykh**

- [Telegram](https://t.me/RavenZIP)
