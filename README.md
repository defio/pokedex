# Pokédex (work-in-progress)

Pokédex is a **work-in-progress** Android app, which connects to
[graphql-pokemon](https://github.com/lucasbento/graphql-pokemon). It is still in its early stages of development.

## Android development

Pokédex is an app which attempts to use the latest cutting edge libraries and tools, and try using design patterns typical of the web world (like [redux](http://redux.js.org/)) in android. As a summary:

 * Entirely written in [Kotlin](https://kotlinlang.org/)
 * Uses [RxJava](https://github.com/ReactiveX/RxJava) 2
 * Uses all of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room, LiveData and Lifecycle-components
 * Uses [dagger-android](https://google.github.io/dagger/android.html) for dependency injection
 * Uses [apollo-android](https://github.com/apollographql/apollo-android) as GraphQL client 

## Development setup

First off, you require the latest Android Studio 3.0 (or newer) to be able to build the app.

### Code style

This project uses [ktlint](https://github.com/shyiko/ktlint), provided via
the [spotless](https://github.com/diffplug/spotless) gradle plugin, and the bundled project IntelliJ codestyle.

If you find that one of your pull reviews does not pass the CI server check due to a code style conflict, you can
easily fix it by running: `./gradlew spotlessApply`, or running IntelliJ/Android Studio's code formatter.


## Contributions

If you've found an error in this sample, please file an issue.

Patches are encouraged, and may be submitted by forking this project and
submitting a pull request. Since this project is still in its very early stages,
if your change is substantial, please raise an issue first to discuss it.

## License

```
Copyright 2017 Nicola De Fiorenze

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
         http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```