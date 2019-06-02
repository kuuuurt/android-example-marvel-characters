# Marvel App

This project is a sample app created to demonstrate simple modularization on an Android project.

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

This project is built using Clean Architecture and is structured in the following way

**data** -  contains data models and repositories for getting data

**domain** - contains use cases/interactors for the presentation layer to access data from the **data** layer

**ui** - contains Activities/Fragments and their corresponding ViewModels and Adapters for the presentation layer


## Marvel API

The API keys should be in a **secrets.properties** in the root folder and will be loaded into the app as **BuildConfig** fields.

Create your own API keys on [Marvel's developer site] (https://developer.marvel.com/documentation/getting_started)

The public api key is in **app.marvel\_public\_api\_key** and the hash is in **app.marvel\_hash**. I hashed my keys before this so I can do away with hashing inside the Android App. Read more about their API Authorization [here](https://developer.marvel.com/documentation/authorization).

## Resources

If you wanna read more about modularization, here are some articles I found helpful:

- [Modularization Android Applications](https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0)
- [Intro to App Modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e)
- [Multiple application modules in one project](https://medium.com/mindorks/multiple-application-modules-in-one-android-project-36e86ceb8a9)
- [Patchwork Plaid - A modularization store](https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e)

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
