plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.mvvmrecipeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mvvmrecipeapp"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding {
            enable = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.gif.animation)
    implementation(libs.retrofit.android)
    implementation(libs.gson.android)
    implementation(libs.glide.compiler)
    implementation(libs.glide.android)

    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.coordinatorlayout)
}
//implementation 'com.github.bumptech.glide:glide:4.12.0'
//annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
//#implementation("com.squareup.retrofit2:retrofit:2.9.0")
//#implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
