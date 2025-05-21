plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.opendart.coroutineretrofitornek5"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.opendart.coroutineretrofitornek5"
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
    viewBinding {
        enable = true
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
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")
    //noinspection GradleCompatible
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation (libs.retrofit)
    //implementation (libs.rxjava2)
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/adapter-rxjava3
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
}