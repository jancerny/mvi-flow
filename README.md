# MVI-observable
MVI-observable is a simple MVI library for Kotlin + Coroutines inspired by Redux-observable

# Architecture
![Architecture Layers diagram](/diagram.svg)

# Installation
Add jitpack to your project `build.gradle` 
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and then add **mvi-observable** to the list of dependencies
```groovy
dependencies {
    implementation 'com.github.jancerny:mvi-observable:1.0.0'
}
```
