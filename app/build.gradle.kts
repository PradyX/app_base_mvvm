/*
 * *
 *  * Created by prady on 14/02/26, 2:28 pm, $today.time
 *  * Copyright (c) 2026 . All rights reserved.
 *  * Last modified 14/02/26, 2:28 pm, $file.lastModified.time
 *
 *
 */

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "com.prady.app.base"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.prady.app.base"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://bankingmitra.org/api/\"")
        buildConfigField("String", "IMG_BASE_URL", "\"https://bankingmitra.org/\"")
        buildConfigField("String", "FIREBASE_URL", "\"https://appchecks-44916-default-rtdb.asia-southeast1.firebasedatabase.app/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.kodein.di.generic.jvm)
    implementation(libs.kodein.di.framework.android.x)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.gson)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    implementation(libs.coil)
    implementation(libs.viewpager2)
    implementation(libs.circularImageView)
    implementation(libs.shimmer)
    implementation(libs.lottie)
    implementation(libs.pinview)
    implementation(libs.kenburnsview)
    implementation(libs.imagepicker)

    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseUiAuth)
    implementation(libs.firebaseDatabaseKtx)
    implementation(libs.firebaseAuthKtx)
    implementation(libs.firebaseAnalyticsKtx)
    implementation(libs.firebaseMessagingKtx)
    implementation(libs.firebaseDynamicLinksKtx)
}