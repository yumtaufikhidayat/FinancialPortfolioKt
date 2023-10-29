plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.taufik.financialportofolio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.taufik.financialportofolio"
        minSdk = 31
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // UI
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val navigationVersion = "2.7.4"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion")

    // Coroutine
    val coroutineVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // Android KTX
    val activityKtxVersion = "1.8.0"
    val fragmentKtxVersion = "1.6.1"
    implementation("androidx.activity:activity-ktx:$activityKtxVersion")
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    // OkHttp
    val loggingInterceptorVersion = "4.11.0"
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion")

    // Android Chart
    val chartVersion = "3.1.0"
    implementation("com.github.PhilJay:MPAndroidChart:v$chartVersion")

    // Splash Screen
    val splashScreenVersion = "1.0.1"
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")
}