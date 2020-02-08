# Jotter
Jotter, a simple library for log all of your Activity and Fragment event. Very easy to use this Android library.

## Support 
Support from Android Jelly Bean / Minimum API 16

## Permission 
No needed special permission in AndroidManifest

## Installing 
Add repository in root build.gradle

```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

And add dependency to module build.gradle:

```gradle
dependencies {
    implementation 'com.github.pahlevikun:jotter:1.0.0'
}
```

## How to Use 
You need to initialize Jotter first inside your Application class and you need to set the JotterListener into the Singleton. Then the only way you needed is just startListening and voila! All of your activity or fragment event will listened inside JotterListener.
Here's some sample code :

**Java**

```Java
new Jotter
    .Builder(this)
    .setLogEnable(true)
    .setActivityEventFilter(Arrays.asList(ActivityEvent.CREATE, ActivityEvent.DESTROY))
    .setFragmentEventFilter(Arrays.asList(FragmentEvent.VIEW_CREATE, FragmentEvent.PAUSE))
    .setJotterListener(new JotterListener())
    .build()
    .startListening();
```

**Kotlin**

```Kotlin
Jotter
    .Builder(this)
    .setLogEnable(true)
    .setActivityEventFilter(listOf(ActivityEvent.CREATE, ActivityEvent.DESTROY))
    .setFragmentEventFilter(listOf(FragmentEvent.VIEW_CREATE, FragmentEvent.PAUSE))
    .setJotterListener(JotterListener)
    .build()
    .startListening()
```

## Changelog
```changelog
Version 1.0.0
- Initial release
```

## License
```license
   Copyright 2020 Farhan Yuda Pahlevi

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