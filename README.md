<p align="center">

<img alt="Logo" src="images/logo.png" width="100%">

<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.2.21-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose Multiplatform-1.9.3-3b83f8">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
<a href="https://jitpack.io/#RavenZIP/kotlin-reactive-forms">
    <img alt="Static Badge" src="https://img.shields.io/badge/JitPack-0.1.0-39ad31">
</a>

</p>

> 🌐 **Languages:**  
> [Русский](README.md) | [English](docs/README-EN.md)

## 🔎 Что такое Bereza UI?

Скоро...

## 🌍 Поддерживаемые платформы

| Платформа | Статус              |
|-----------|---------------------|
| Windows   | ✅ Поддерживается    |
| Linux     | ❓ Неизвестно        |
| macOS     | ❓ Неизвестно        |
| Web       | ✅ Поддерживается    |
| Android   | ✅ Поддерживается    |
| iOS       | ❌ Не поддерживается |

Linux, macOS, iOS временно не поддерживаются, так как невозможно проверить
работоспособность библиотеки на данных платформах

## 🚀 Установка

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
    implementation("com.github.RavenZIP.bereza-ui:core:$version") 
}
```

Если вы используете libs.versions.toml

**libs.versions.toml**

```
[versions]
ravenzip-bereza-ui = "$version"

[libraries]
ravenzip-bereza-ui-core = { module = "com.github.RavenZIP.bereza-ui:core", version.ref = "ravenzip-bereza-ui" }
```

**build.gradle.kts**

```
dependencies {
    implementation(libs.ravenzip.bereza.ui.core)
}
```

## 🚬 Использование

Скоро...

## 📜 Лицензия

Эта библиотека распространяется по лицензии Apache 2.0. Подробности смотрите в файле [ЛИЦЕНЗИЯ](LICENSE).

## 👾 Разработчик

**Черных Александр**

- [Telegram](https://t.me/RavenZIP)
