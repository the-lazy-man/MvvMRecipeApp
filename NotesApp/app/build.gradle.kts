import com.android.build.api.dsl.DataBinding

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)  // Use Kotlin DSL for kapt
}

android {
    namespace = "com.example.notesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.notesapp"
        minSdk = 24
        targetSdk = 35
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
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding  = true
        dataBinding  = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
dependencies {
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx) // Room runtime
    kapt(libs.androidx.room.compiler) // Room compiler for Kotlin
    implementation(libs.androidx.room.ktx) // Room KTX
    implementation(libs.androidx.lifecycle.livedata.ktx) // LiveData KTX
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel KTX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
//
//dependencies {
//    implementation(libs.androidx.room.runtime) // Room runtime
//    kapt(libs.androidx.room.compiler) // Room compiler for Kotlin
//    implementation(libs.androidx.room.ktx)
//    implementation(libs.androidx.lifecycle.livedata.ktx) // LiveData KTX
//    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel KTX
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//}