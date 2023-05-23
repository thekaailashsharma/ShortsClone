@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "task.clone.shorts"
    compileSdk = 33

    defaultConfig {
        applicationId = "task.clone.shorts"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Compose Bom
    implementation(platform(libs.compose.bom))

    //Compose
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // Lottie Animation
    implementation(libs.lottie)

    //Coil
    implementation(libs.coilx)

    //Coroutines
    implementation(libs.kotlin.coroutines)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    //Paging
    implementation(libs.paging)
    implementation(libs.paging.compose)

    //Exoplayer
    implementation(libs.exoplayer)
    implementation(libs.exoplayer.ui)
    implementation(libs.google.exoplayer.core)
    implementation(libs.google.exoplayer.dash)
    implementation(libs.google.exoplayer.ui)

    //Material Extended Icons
    implementation(libs.material.icons.extended)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.kapt)
    implementation(libs.dagger.hilt.navigation)

    //Navigation Compose
    implementation(libs.navigation.compose)

    //MaterialCompose
    implementation(libs.compose.material)

    //Moshi
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)

    //Pager
    implementation(libs.pager)


    //Testing Android
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}