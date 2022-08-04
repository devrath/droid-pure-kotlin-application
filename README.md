# droid-pure-kotlin-application
[![Android Best practices](https://img.shields.io/badge/Android-best--practices-red)](https://www.android.com/intl/en_in/what-is-android/) [![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-brightgreen)](https://kotlinlang.org/) [![Coroutines](https://img.shields.io/badge/Coroutines-1.6.0-red)](https://kotlinlang.org/docs/reference/coroutines-overview.html) [![DaggerHilt](https://img.shields.io/badge/DaggerHilt-2.40-blue)](https://developer.android.com/training/dependency-injection/hilt-android) [![Moshi](https://img.shields.io/badge/Moshi-1.13.0-orange)](https://github.com/square/moshi) [![Firebase](https://img.shields.io/badge/Firebase-30.2.0-blueviolet)](https://firebase.google.com/) [![Datastore](https://img.shields.io/badge/Datastore-1.0.0-brightgreen)](https://developer.android.com/topic/libraries/architecture/datastore) [![Timber](https://img.shields.io/badge/Timber-5.0.1-blue)](https://github.com/JakeWharton/timber) [![Orhanobut](https://img.shields.io/badge/orhanobut-2.2.0-lightgrey)](https://github.com/orhanobut/logger) [![Jetpack compose](https://img.shields.io/badge/Jetpack%20Compose-1.1.1-ff69b4)](https://developer.android.com/jetpack/compose/documentation) [![Coil](https://img.shields.io/badge/Coil-1.3.2-008080)](https://github.com/coil-kt/coil) [![Google Material](https://img.shields.io/badge/Google%20Material-1.4.0-3D3635)](https://material.io/develop/android/docs/getting-started) [![Compose Navigation](https://img.shields.io/badge/Compose%20Navigation-2.5.0-7D0541)](https://developer.android.com/guide/navigation/navigation-getting-started) [![Hilt navigation compose](https://img.shields.io/badge/Hilt%20navigation%20compose-1.0.0-DB7093)](https://androidx.tech/artifacts/hilt/hilt-navigation-compose/) [![Activity compose](https://img.shields.io/badge/Activity%20compose-1.5.0-5453A6)](https://androidx.tech/artifacts/activity/activity-compose/) [![Ui tooling preview](https://img.shields.io/badge/Ui%20tooling%20preview-1.1.1-C32148)](https://androidx.tech/artifacts/compose.ui/ui-tooling-preview/) [![Compose ui](https://img.shields.io/badge/Compose%20ui-1.1.1-F6BE00)](https://androidx.tech/artifacts/compose.ui/ui-tooling/) [![Kt lint](https://img.shields.io/badge/Kt%20lint-10.3.0-00FA9A)](https://github.com/pinterest/ktlint) [![Crashlytics](https://img.shields.io/badge/Crashlytics-2.9.1-3B9C9C)](https://firebase.google.com/docs/crashlytics)  






This repository contains a sample project that is developed using the clean architecture with compose and multi-module features

---

### **`𝙿𝚞𝚛𝚎 𝚔𝚘𝚝𝚕𝚒𝚗`** :card_index_dividers:
<img align="left" height="150" width="260" src="https://github.com/devrath/droid-compose-clean-architecture/blob/main/assets/KotlinAndroidStudio.png"  alt="dev_logo"/>

:label: Programming language: :heavy_minus_sign: By migrating away from Java and towards kotlin, we can leverage the features like better readability, null saftey, less code, benifits of improved syntax and others. </br>
:label: Kotlin DSL: :heavy_minus_sign: Earlier we used to use gradle for organizing dependencies. Gradle is written in a groovy programming language. Using the kotlin-DSL, we can organize the dependencies in our project cleaner and in an efficient way. Type safety. This allows better autocompletion which was missing from grovey till now. Code navigation between files becomes easy in Kotlin DSL. Refactoring is much easier. </br>
:label: Compose: :heavy_minus_sign: Earlier we had xml for building the UI and thus there was one more language hooked to android project. But with the addition of jetpack compose, Even the UI will be built in kotlin language.

---

### **`𝚆𝚑𝚢 𝚌𝚕𝚎𝚊𝚗 𝚊𝚛𝚌𝚑𝚒𝚝𝚎𝚌𝚝𝚞𝚛𝚎 𝚒𝚜 𝚗𝚎𝚎𝚍𝚎𝚍`** :card_index_dividers:
<img align="right" height="190" width="410" src="https://github.com/devrath/droid-compose-clean-architecture/blob/main/assets/compose_clean_arch.png"  alt="dev_logo"/>
:label: Scalability: :heavy_minus_sign: When new features are added, using the clean architecture we can easily add the new features and maintain old existing features without breaking them. </br>
:label: Testability: :heavy_minus_sign: For the new features, its essential we write a code that can be tested for all possible inputs so that once in production we can forecast all possible scenarios and handle them. </br>
:label: Understandable: :heavy_minus_sign:  Scaling a product with keeping the code testable is good but its also should be in such a way that everyone should understand it and also should easily be modify it.

---

### **`𝚆𝚑𝚢 𝚖𝚞𝚕𝚝𝚒 𝚖𝚘𝚍𝚞𝚕𝚎 𝚒𝚜 𝚞𝚜𝚎𝚍 𝚒𝚗 𝚙𝚛𝚘𝚓𝚎𝚌𝚝`** :card_index_dividers:
:label: A multi module project helps us to segrigate between the layers of code in the project and enforcing strict seperation of concerns. </br>
:label: When working on a large project this will keep the team to work in layer wise and also feature wise. </br>
:label: A change in one feature will not affect other features in the project. </br>

---

### **`𝙳𝚎𝚙𝚎𝚗𝚍𝚎𝚗𝚌𝚢 𝙸𝚗𝚓𝚎𝚌𝚝𝚒𝚘𝚗`** :card_index_dividers:
<img align="right" height="150" width="160" src="https://github.com/devrath/droid-compose-clean-architecture/blob/main/assets/hilt_img_new.png"  alt="dev_logo"/>
:label: A Depencency Injection results in a loosely coupled programs. It helps to achieve inversion of control. Inversion of control means a object or function recieves another object or function as a dependency. </br>
:label: We use hilt that is built on top of dagger tool that generates code on behalf of user and helps to attain the inversion control easily. This is very essential because all the extra code that we need to generate, hilt will do it for us. </br>
:label: The inversion control becomes even more crucial part of the structure in case of multi module project. </br>
:label: This also helps in unit testing thte code because, since object is not created and instead provided to a function, we can easily mock it during unit testing. </br>

---

### **`𝙲𝚘𝚖𝚖𝚞𝚗𝚒𝚌𝚊𝚝𝚒𝚘𝚗 𝚋𝚎𝚝𝚠𝚎𝚎𝚗 𝚝𝚑𝚎 𝚕𝚊𝚢𝚎𝚛𝚜`** :card_index_dividers:
<img align="left" height="250" width="250" src="https://github.com/devrath/iPrayForGod/blob/main/assets/Clean-architecture-layered-interaction.png"  alt="dev_logo"/>
:label: Presentation Layer: :heavy_minus_sign: This layer is the UI layer of the application, It contains composables and view-models. The presentation layer communicates with the domain layer, But the presentation layer is not aware of the data layer. </br> 
:label: Domain Layer: :heavy_minus_sign: This layer contains use cases that contain business logic that perform one functionality each. It also contains the interfaces that communicates with repository which is present in the data layer. </br>
:label: Data Layer: :heavy_minus_sign: The data layer contains the repositries, The repository can be a preference repository, remote API, local-filesystem etc. The data layer communicates back with the domain layer after fetching the data. </br>


---

### **`𝙸𝚖𝚙𝚕𝚎𝚖𝚎𝚗𝚝𝚊𝚝𝚒𝚘𝚗 𝚘𝚏 𝚂𝙾𝙻𝙸𝙳 𝚙𝚛𝚒𝚗𝚌𝚒𝚙𝚕𝚎𝚜`** :card_index_dividers:
*This applicaion is developed keeping in mind of clean architecture principles*
| SINGLE RESPONSIBILITY | OPEN CLOSED PRINCIPLE | LISKOV SUBSTITUTION PRINCIPLE | INTERFACE SEGREGATION PRINCIPLE | DEPENDENCY INVERSION PRINCIPLE |
| --------------------- | --------------------- | ----------------------------- | ------------------------------- | ------------------------------ |
| Each class having one responsibility. If there is a change in it, it must be because of only one reason only. If there are two reasons for a class to change, then it violates single responsibility principle. | This indicates that the class is open for extension but closed for modification | When we have a class that is inheriting the parent, the parent class functionality must be replaceable using the child class without modifying the functionality of parent class | Here we should not force a class to do something that it does not want to do, Say a class is implementing interface then it overrides all the functions in it. Now if we don’t want a function of interface to be implemented then we give the default behaviour for the interface and so that it will not force the child class to override that method | This indicates that, a class must depend on abstraction instead of concrete implementation. Say we want a implementation to be used for a class thence need to inject a abstraction for it instead of concrete implementation so that if its implementation changes in future , it must not affect where the abstract version of it is implemented |

---

### **`𝙰𝚍𝚟𝚊𝚗𝚝𝚊𝚐𝚎𝚜 𝚊𝚗𝚍 𝙳𝚒𝚜𝚊𝚍𝚟𝚊𝚗𝚝𝚊𝚐𝚎𝚜 𝚘𝚏 𝚖𝚞𝚕𝚝𝚒 𝚖𝚘𝚍𝚞𝚕𝚎 𝚊𝚛𝚌𝚑𝚒𝚝𝚎𝚌𝚝𝚞𝚛𝚎`** :card_index_dividers:
#### **`𝙰𝚍𝚟𝚊𝚗𝚝𝚊𝚐𝚎𝚜`**
| `Clear separation` | `Faster build time` |
| ------------ | -------------- |
| • Each of the modules is a library and it can depend on each other <br> • If there is a scenario where one library is not dependent on another, Then they can't access each other <br> • Thus, instead of adding terms of separate packages in the app module, we can enforce strict modularity by modularising it <br> • In a large project and big team we can enforce, who works on which project. Thus one person working on one won't affect other modules| • Using the multi-module we can decrease the build time. When in multi-module each module will use separate threads so build time will decrease since all modules will build independently <br> • Since Gradle needs to build the module that has been changed, it doesn't need to make the modules that have not been changed <br> • Point to note here is that, say there is a root module with few child modules, if the root module changes, all the child modules also need to rebuild <br> • Support for `instant apps` and `dynamic feature module` <br> • Using this we can make parts of our app reusable |

#### **`𝙳𝚒𝚜𝚊𝚍𝚟𝚊𝚗𝚝𝚊𝚐𝚎𝚜`**
 • Lot of initial code <br>
 • Not knowing what problem it will cause
 
 ---
 

#### **`𝙼𝚅𝚅𝙼 𝚜𝚝𝚛𝚞𝚌𝚝𝚞𝚛𝚎 𝚞𝚜𝚎𝚍 𝚒𝚗 𝚖𝚞𝚕𝚝𝚒-𝚖𝚘𝚍𝚞𝚕𝚎 𝚏𝚘𝚛 𝚌𝚕𝚎𝚊𝚗 𝚊𝚛𝚌𝚑𝚒𝚝𝚎𝚌𝚝𝚞𝚛𝚎`**


<p align="right" >
<img align="left" height="300" width="300" src="https://github.com/devrath/iPrayForGod/blob/main/assets/mvvm_structure.png" alt="mvvm"/>
<img align="left" height="300" width="400" src="https://github.com/devrath/iPrayForGod/blob/main/assets/skeletal_structure_new.png" alt="mvvm-improved"/>
</p>

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
 
 :label: Implementations of repositories sits in the data layer.<br/>
 :label: The Data layer has reference to domain layer, So it can access all the classes from the data layer.<br/>
 :label: Presentation layer has reference to the domain layer, so it can access all the classes from the domain layer and not data layer.<br/>
 :label: We alwaays inject abstractions to any class instead of concrete implementation.<br/>
 :label: Data flows from presentation layer to data layer if data is saved locally or sent to server. In other case the data is retrieved from data layer to presentation layer in case when data is retrieved from server to UI displayed or from data saved locally to UI displayed<br/> 
 
 ---
 
 ## **`𝚃𝚎𝚜𝚝𝚒𝚗𝚐 𝚝𝚑𝚎 𝚌𝚘𝚍𝚎`** :test_tube:
 
 :label: We test the `features` of the code, Each of the `features` contain three layers namely `data`, `domain`, `presentation`. <br/>
 :label: In the `core` layer we can write tests for the `implementation` and `repository` for each of modules. 

 ---
 
## **`𝙲𝚘𝚍𝚎 𝚚𝚞𝚊𝚕𝚒𝚝𝚢`**
  
##### **`Run Kt lint`**
:label: It is static code analysis tool that is used to analyze the Kotlin code for you. It will check if you have written the code by following the Kotlin code guidelines or not.

```gradle
// To check the list of errors
./gradlew ktlintCheck  
// Try to auto auto format errors
./gradlew ktlintFormat
```
 
 ---
 
## **`𝙷𝚘𝚠 𝚝𝚘 𝚛𝚞𝚗 𝚝𝚑𝚎 𝚜𝚊𝚖𝚙𝚕𝚎 𝚊𝚙𝚙𝚕𝚒𝚌𝚊𝚝𝚒𝚘𝚗`** :card_index_dividers:
:label: Basically You just follow steps to create a firebase account and eanable authentication module, I have summarized some steps below.<br/>
:one: Before running the sample application, Please create a account in firebase console.<br/>
:two: Now enable the authentication in settings for firebase(This is used during the authentication of login).<br/>
:three: Add the google-services.json in the app level of the project that is obtained when creating a project in firebase.<br/> 
:four: Last but not least add your application SHA in your firebase settings.<br/>

## **`𝙲𝚞𝚛𝚛𝚎𝚗𝚝𝚕𝚢 𝚏𝚎𝚊𝚝𝚞𝚛𝚎𝚜 𝚍𝚎𝚟𝚎𝚕𝚘𝚙𝚎𝚍`** :card_index_dividers:
:label: Currently the registration, login, forgot password flow is implemented 

## **`𝙿𝚊𝚌𝚔𝚊𝚐𝚎 𝚂𝚝𝚛𝚞𝚌𝚝𝚞𝚛𝚎`** :package:

    Project Folder                                    # Root Package
    .
    ├── app                                           # It is the starting point for the application
    │   ├── activity                                  # Entire application has just one activity
    │   ├── view-model                                # This will be the view-model for the single activity     
    |   ├── navigation                                # It contains the navigation compose and the routes used in the application
    │   └── application                               # Singleton used at application level
    |
    ├── buildSrc                                      # It contains all project dependencies & its version references that can be modified from one place
    |
    ├── core                                          # Module with code that can be re-used in all the modules. This also helps us to manage them easily
    │   ├── res                                       # String resources used in the project
    │   └── java
    │       ├── di                                    # Hilt dependency injection
    │       ├── domain                                # Domain layer for all the reusable third party features in project
    │       │   ├── features                          # Contains interface for all the reusable third party features in project
    │       │   └── models                            # data modules used thorught the project
    │       │
    │       ├── modules                               # It can hold multiple third party features     
    │       │   └── feature                           # A feature
    │       │        ├── implementation-of-feature    # Implementation of the third party feature
    │       │        └── repository-for-feature       # Repository for the third party feature
    │       │
    │       └── platform                              # Can old other miscellaneous things needed in project, some are listed below
    │           ├── base                              # Can hold base classes that are used in different features in project
    │           ├── extensions                        # kotlin extensions that can be kept in one place and handeled
    │           └── functional                        # Other util functional elements in teh project
    │      
    │ 
    ├── core-ui                                       # Module contains all the UI related things in the project
    │   ├── res                                       
    │   │   ├── drawable                              # Contains all the drawables needed for the project
    │   │   └── font                                  # Place we can store the font files in the project
    │   │
    │   └── java
    │       ├── composables                           # Here we store the reusable composables used for the project 
    │       └── theme                                 # Contains the theme file used in project
    │ 
    └── features                                      # The module contains all the features used in project
        │
        ├── common                                    # sub-module that holds the logic that is re-used in multiple features
        │   │
        │   │
        │   ├── common_data                           # sub-module for data
        │   │   ├── di                                # di for current module
        │   │   ├── repository                        # repository used for injection
        │   │   └── service                           # service used in repository
        │   │   
        │   │
        │   └── common_domain                         # sub-module for domain
        │       ├── di                                # di used for having use-case data object instance
        │       ├── repository                        # interface of the repository
        │       └── usecases                          # individual logic for domain data
        │         
        └── login                                     # sub-module that contains one feature
            │
            │
            ├── login_data                            # sub-module for data
            │   ├── di                                # di used to build the module and create instances of service and repository of current module
            │   ├── repository                        # implementation of the repository
            │   └── service                           # service used in repository
            │   
            │
            ├── login_domain                          # sub-module for domain
            │   ├── di                                # di used for having use-case data object instance
            │   ├── repository                        # interface of the repository
            │   └── usecases                          # individual logic for domain data
            │   
            │
            └── login_presentation                    # sub-module for presentation
                ├── states                            # states of the data displayed and view-model
                ├── view-composable                   # composable representing a screen
                └── viewmodel                         # view model for the composable

## **`𝙱𝚞𝚒𝚕𝚝 𝚆𝚒𝚝𝚑`** 🛠
- [Kotlin](https://kotlinlang.org/) official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI.

## **`𝙱𝚞𝚒𝚕𝚍-𝚝𝚘𝚘𝚕`** 🧰
You need to have [android studio](https://developer.android.com/studio/features) to build this project.

## **`𝙵𝚒𝚗𝚍 𝚝𝚑𝚒𝚜 𝚙𝚛𝚘𝚓𝚎𝚌𝚝 𝚞𝚜𝚎𝚏𝚞𝚕`** ? ❤️
Support it by clicking the ⭐ button on the upper right of this page. ✌️


## **`𝙻𝚒𝚌𝚎𝚗𝚜𝚎`** :credit_card:

<details><summary>Click to view License</summary>
<p>

    Copyright 2022 Devrath

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


</p>
</details>


<p align="center">
<a><img src="https://forthebadge.com/images/badges/built-for-android.svg"></a>
</p>
