# \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧 \]
# iPrayForGod
[![Android Best practices](https://img.shields.io/badge/Android-best--practices-red)](https://www.android.com/intl/en_in/what-is-android/) [![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-brightgreen)](https://kotlinlang.org/) [![Coroutines](https://img.shields.io/badge/Coroutines-1.6.0-red)](https://kotlinlang.org/docs/reference/coroutines-overview.html) [![DaggerHilt](https://img.shields.io/badge/DaggerHilt-2.40-blue)](https://developer.android.com/training/dependency-injection/hilt-android)  
This repository contains a sample project that is developed using the clean architecture with compose and multi-module features

## `Skeletal Structure` 📑

![banner](https://github.com/devrath/iPrayForGod/blob/main/assets/skeletal_structure.png)

| `Data Layer` | `Domain Layer` | `Presentation Layer` |
| ------------ | -------------- | -------------------- |
| • It is the innermost layer <br> • The representations are such a way that they are easy to be consumed in the presentation layer.             | • It is the middle layer <br> • It contains the use case classes that contain logic for individual functions <br> • Models are something that hold data  | • It is the outermost layer <br> • It contains the platform specific classes like `activities`,`fragments` <br> • It also contains `view-models`, unlike earlier where they used to contain the business logic that resulted in a god like class, now the `view-model` communicates to each individual `use-cases` and finally map the result to the UI state |
