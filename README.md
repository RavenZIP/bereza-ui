<p align="center">

<img alt="Logo" src="images/logo.png" width="100%">

<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.10-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose Multiplatform-1.10.1-3b83f8">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
<a href="https://jitpack.io/#RavenZIP/bereza-ui">
  <img src="https://jitpack.io/v/RavenZIP/bereza-ui.svg">
</a>
</p>

> 🌐 **Languages:**  
> [Русский](README.md) | [English](docs/README-EN.md)

## 🔎 Что такое Bereza UI?

Bereza UI - Compose Multiplatform библиотека кастомных Material UI элементов. 
Проект разрабатывается как набор переиспользуемых компонентов и инструментов для создания современных приложений под несколько платформ с единым API.
Библиотека основана на Material Design и по возможности и необходимости версия Material актуализируется

## 🌍 Поддерживаемые платформы

| Платформа | Статус              |
|-----------|---------------------|
| Windows   | ✅ Поддерживается    |
| Linux     | ❓ Неизвестно        |
| macOS     | ❓ Неизвестно        |
| Web       | ✅ Поддерживается    |
| Android   | ✅ Поддерживается    |
| iOS       | ❌ Не поддерживается |

macOS, iOS временно не поддерживаются, так как невозможно проверить работоспособность библиотеки на данных платформах.

## 🌳 Структура проекта

### bereza-core

Основной модуль библиотеки. Содержит все базовые компоненты и их полный API на Compose.


### bereza-extensions

Упрощённые обёртки над компонентами `bereza-core`. Вместо Compose-функций в качестве параметров используются обычные параметры (text, textStyle и др.), что делает API более лаконичным и удобным для большинства типовых сценариев.


### bereza-reactive

Интеграция с [`kotlin-reactive-forms`](https://github.com/RavenZIP/kotlin-reactive-forms).

Позволяет использовать компоненты Bereza UI совместно с `FormControl` для построения сложных форм с валидацией и управлением состоянием.


### docs

Документация проекта.


### images

Изображения и ресурсы, используемые в README и документации.

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
    implementation("com.github.RavenZIP.bereza-ui:bereza-core:$version") 
}
```

Если вы используете libs.versions.toml

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

## 🚬 Использование

Скоро...

## 📜 Лицензия

Эта библиотека распространяется по лицензии Apache 2.0. Подробности смотрите в файле [ЛИЦЕНЗИЯ](LICENSE).

## 👾 Разработчик

**Черных Александр**

- [Telegram](https://t.me/RavenZIP)
