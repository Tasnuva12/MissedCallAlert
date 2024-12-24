
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.5.31"



}


android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "com.example.missedcallalert"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.missedcallalert"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    //viewmodel dependencies
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)


    implementation(libs.androidx.storage)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    implementation (libs.androidx.navigation.compose)
    implementation (libs.material)
    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)

    // Retrofit
    implementation (libs.retrofit)

    // Retrofit Converter for JSON (Optional, if your API returns JSON)
    implementation (libs.converter.gson)

    // OkHttp for logging (Optional, for debugging network calls)
    implementation (libs.logging.interceptor)
    implementation(libs.androidx.material3.v140alpha02)
    //coroutine dependencies
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)


   //hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    //coroutine
    implementation(libs.kotlinx.coroutines.android.v139)
    //permissions
    implementation (libs.accompanist.permissions)


    //for using collectAsState
    implementation(libs.androidx.runtime.livedata)
    //navigation graph
    implementation (libs.androidx.navigation.compose.v260alpha01)
    implementation (libs.ui)
    implementation (libs.androidx.material3.v110)
    implementation(libs.kotlinx.serialization.json)

    //SharedPreferences
    implementation(libs.androidx.preference.ktx)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}