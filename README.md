# Callback Pattern 
[![](https://img.shields.io/badge/language-ES-blue.svg)](./README.es)

## In this project we visualize how use callbacks

### By Romell Domínguez
[![](https://raw.githubusercontent.com/romellfudi/assets/master/favicon.ico#favico)](https://www.romellfudi.com/)

Examples:
```java
// java
service.needImagesForSomethig(id idTrack, String hashSecurity, new ServiceCallback(){
    public void successful(int code,Array<Bitmap> maps){

    }

    public void throwProblem(int code, ServiceException exception){

    }
} );
```
```kotlin
// kotlin
service.needImagesForSomethig(idTrack: Int , hashSecurity: String , object : ServiceCallback(){
    override fun  successful(code : Int,maps: Array<Bitmap> ){

    }

    override fun throwProblem(code: Int,  exception: ServiceException){

    }
} ) 
```

### License
```
Copyright 2018 Romell D.Z.

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