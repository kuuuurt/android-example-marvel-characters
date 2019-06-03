# Marvel App

This project is a sample app created to experiment around Android Development

## Libraries and Tools Used

- Kotlin
- AndroidX
    - App Compat
    - RecyclerView
- Material Components
- Android Architecture Components
    - Lifecycle and ViewModel
    - Paging
- Retrofit
- Moshi
- Kotlin Coroutines
- Glide
- Dagger 2

## Project Structure

This project is built using Clean Architecture and is structured in the following way:

**presentation** - contains Activities/Fragments and their corresponding ViewModels and Adapters for the presentation layer

**domain** - contains entities and use cases for the presentation layer to access data from the **data** layer

**data** -  contains data models and repositories for getting data

**framework** - contains implementation details for network and db layer (Retrofit/Room/SQLDelight/Realm)

**di** - contains classes for dependency injection. In this case, Dagger's components and modules.


## Marvel API

The API keys should be in a **secrets.properties** in the root folder and will be loaded into the app as **BuildConfig** fields.

You can create your own API keys on [Marvel's developer site] (https://developer.marvel.com/documentation/getting_started)

The public api key is in **app.marvel\_public\_api\_key** and the hash is in **app.marvel\_hash**. I hashed my keys before this so I can do away with hashing inside the Android App. Read more about their API Authorization [here](https://developer.marvel.com/documentation/authorization).

## Resources

Here are the articles I found that helped me come up with this app:

### Clean Architecture:
- [Clean architecture for Android with Kotlin: a pragmatic approach for starters](https://antonioleiva.com/clean-architecture-android)
- [Kotlin Clean Architecture](https://proandroiddev.com/kotlin-clean-architecture-1ad42fcd97fa)
- [Architecting Android...Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)

### Modularization:
- [Modularization Android Applications](https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0)
- [Intro to App Modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e)
- [Multiple application modules in one project](https://medium.com/mindorks/multiple-application-modules-in-one-android-project-36e86ceb8a9)
- [Patchwork Plaid - A modularization store](https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e)

### Dagger:
- [Dependency injection in a multi module project](https://medium.com/androiddevelopers/dependency-injection-in-a-multi-module-project-1a09511c14b7)
- [That Missing Guide: How to use Dagger2](https://medium.com/@Zhuinden/that-missing-guide-how-to-use-dagger2-ef116fbea97)
- [Dagger 2 for Android Beginners — Introduction](https://medium.com/@harivigneshjayapalan/dagger-2-for-android-beginners-introduction-be6580cb3edb)

## Authors

- Kurt Renzo Acosta - [kurt.r.acosta@gmail.com](mailto:kurt.r.acosta@gmail.com)

## License


    Copyright 2019 Kurt Renzo Acosta

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at


        http://www.apache.org/licenses/LICENSE-2.0


    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an 
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and
    limitations under the License.
