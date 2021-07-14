# Asiabill Android SDK

Welcome to Asiabill's Android SDK. This library will help you accept card and alternative payments in your Android app.

 1. application需要实现抽象BaseApplication一边sdk初始化（参照AsiabillApplication）

 2. 使用主要参照清单文件、build.gradle（app和主公程都需要关注一下）、gradle.properties、proguard-rules.pro（混淆文件记得添加）等文件
 //第三方依赖
 dependencies {
     implementation fileTree(dir: 'libs', include: ['*.jar'])
     api fileTree(include: ['*.aar'], dir: 'libs')
     implementation "com.google.android.gms:play-services-wallet:$google_wallet_version"
     api "androidx.appcompat:appcompat:$appcompat_version"
     api "androidx.multidex:multidex:$multidex_version"
     api "androidx.constraintlayout:constraintlayout:$constraintlayout_version"
     api "com.google.android.material:material:$material_version"
     api "androidx.recyclerview:recyclerview:$recyclerview_version"

     api "com.squareup.retrofit2:retrofit:$retrofit2_version"
     api "com.squareup.retrofit2:converter-gson:$retrofit2_version"
     api "com.squareup.retrofit2:converter-scalars:$retrofit2_version"
     api "com.squareup.retrofit2:adapter-rxjava2:$retrofit2_version"

     api "io.reactivex.rxjava2:rxjava:$rxjava2_rxjava_version"
     api "io.reactivex.rxjava2:rxandroid:$rxjava2_rxandroid_version"
     api "io.reactivex.rxjava2:rxandroid:$rxjava2_rxandroid_version"


     api "com.squareup.okhttp3:logging-interceptor:$okhttp3_version"
     api "com.squareup.okhttp3:okhttp:$okhttp3_version"

     api "com.trello.rxlifecycle2:rxlifecycle:$rxlifecycle2_version"
     api "com.trello.rxlifecycle2:rxlifecycle-android:$rxlifecycle2_version"
     api "com.trello.rxlifecycle2:rxlifecycle-components:$rxlifecycle2_version"

     api "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
     api "com.google.code.gson:gson:$gson_version"
     api "org.apache.commons:commons-lang3:$commons_lang3_version"
     api "commons-beanutils:commons-beanutils:$commons_beanutils_version"

     api "com.github.bumptech.glide:glide:$glide_version"

     api "com.tencent.sonic:sdk:$sonic_version"
 }


 ----------------------------------
 // SDK版本号和第三方版本号控制位于build.gradle
        // SDK
        ext.min_sdk_version = 19
        ext.target_sdk_version = 29
        ext.compile_sdk_version = 29

        // App version
        ext.version_code = 2
        ext.version_name = "1.2"

        //common library
        ext.appcompat_version = "1.2.0"
        ext.multidex_version = "2.0.1"
        ext.constraintlayout_version = '2.0.4'
        ext.material_version = "1.3.0"
        ext.recyclerview_version = "1.1.0"
        ext.retrofit2_version = '2.9.0'
        ext.rxjava2_rxjava_version = "2.2.9"
        ext.rxjava2_rxandroid_version = "2.1.0"
        ext.okhttp3_version = '4.9.0'
        ext.rxlifecycle2_version = '2.2.2'
        ext.lifecycle_version = "2.2.0"
        ext.gson_version = "2.8.5"
        ext.commons_lang3_version = "3.11"
        ext.commons_beanutils_version = "1.9.4"
        ext.glide_version = "3.8.0"
        ext.sonic_version = "3.1.0"
        ext.room_version = "2.2.6"

        //asiabill library
        ext.google_wallet_version = "18.1.2"
        ext.cmake_version = "3.10.2"

================================================

----------------------------------------end--------------------------------------