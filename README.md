# \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧 \]
# iPrayForGod
[![Android Best practices](https://img.shields.io/badge/Android-best--practices-red)](https://www.android.com/intl/en_in/what-is-android/) [![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-brightgreen)](https://kotlinlang.org/) [![Coroutines](https://img.shields.io/badge/Coroutines-1.6.0-red)](https://kotlinlang.org/docs/reference/coroutines-overview.html) [![DaggerHilt](https://img.shields.io/badge/DaggerHilt-2.40-blue)](https://developer.android.com/training/dependency-injection/hilt-android) [![Moshi](https://img.shields.io/badge/Moshi-1.13.0-orange)](https://github.com/square/moshi) [![Firebase](https://img.shields.io/badge/Firebase-30.2.0-blueviolet)](https://firebase.google.com/) [![Datastore](https://img.shields.io/badge/Datastore-1.0.0-brightgreen)](https://developer.android.com/topic/libraries/architecture/datastore) [![Timber](https://img.shields.io/badge/Timber-5.0.1-blue)](https://github.com/JakeWharton/timber) [![Orhanobut](https://img.shields.io/badge/orhanobut-2.2.0-lightgrey)](https://github.com/orhanobut/logger) [![Jetpack compose](https://img.shields.io/badge/Jetpack%20Compose-1.1.1-ff69b4)](https://developer.android.com/jetpack/compose/documentation) [![Coil](https://img.shields.io/badge/Coil-1.3.2-008080)](https://github.com/coil-kt/coil) [![Google Material](https://img.shields.io/badge/Google%20Material-1.4.0-3D3635)](https://material.io/develop/android/docs/getting-started) [![Compose Navigation](https://img.shields.io/badge/Compose%20Navigation-2.5.0-7D0541)](https://developer.android.com/guide/navigation/navigation-getting-started) [![Hilt navigation compose](https://img.shields.io/badge/Hilt%20navigation%20compose-1.0.0-DB7093)](https://androidx.tech/artifacts/hilt/hilt-navigation-compose/) [![Activity compose](https://img.shields.io/badge/Activity%20compose-1.5.0-5453A6)](https://androidx.tech/artifacts/activity/activity-compose/) [![Ui tooling preview](https://img.shields.io/badge/Ui%20tooling%20preview-1.1.1-C32148)](https://androidx.tech/artifacts/compose.ui/ui-tooling-preview/) [![Compose ui](https://img.shields.io/badge/Compose%20ui-1.1.1-F6BE00)](https://androidx.tech/artifacts/compose.ui/ui-tooling/) [![Kt lint](https://img.shields.io/badge/Kt%20lint-10.3.0-00FA9A)](https://github.com/pinterest/ktlint) [![Crashlytics](https://img.shields.io/badge/Crashlytics-2.9.1-3B9C9C)](https://firebase.google.com/docs/crashlytics)  






This repository contains a sample project that is developed using the clean architecture with compose and multi-module features

---

### `Why clean architecture is needed` 📑
<p align="center">
<a><img src="https://github.com/devrath/droid-compose-clean-architecture/blob/main/assets/compose_clean_arch.png"></a>
</p>

* **Scalability**: When new features are added, using the clean architecture we can easily add the new features and maintain old existing features without breaking them.
* **Testability**: For the new features, its essential we write a code that can be tested for all possible inputs so that once in production we can forecast all possible scenarios and handle them.
* **Understandable**: Scaling a product with keeping the code testable is good but its also should be in such a way that everyone should understand it and also should easily be modify it.

---

### `Advantages and Disadvantages of multi module architecture` 📑
#### `Advantages` 
| `Clear separation` | `Faster build time` |
| ------------ | -------------- |
| • Each of the modules is a library and it can depend on each other <br> • If there is a scenario where one library is not dependent on another, Then they can't access each other <br> • Thus, instead of adding terms of separate packages in the app module, we can enforce strict modularity by modularising it <br> • In a large project and big team we can enforce, who works on which project. Thus one person working on one won't affect other modules| • Using the multi-module we can decrease the build time. When in multi-module each module will use separate threads so build time will decrease since all modules will build independently <br> • Since Gradle needs to build the module that has been changed, it doesn't need to make the modules that have not been changed <br> • Point to note here is that, say there is a root module with few child modules, if the root module changes, all the child modules also need to rebuild <br> • Support for `instant apps` and `dynamic feature module` <br> • Using this we can make parts of our app reusable |

#### `Disadvantages` 
 • Lot of initial code <br>
 • Not knowing what problem it will cause
 

---

### `Skeletal Structure of project` 📑
<p align="center">
<a><img src="https://github.com/devrath/iPrayForGod/blob/main/assets/skeletal_structure.png"></a>
</p>


