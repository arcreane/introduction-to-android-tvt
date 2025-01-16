import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
}

fun getOpenWeatherApiKey(): String {
    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())
    return properties.getProperty("OPENWEATHER_API_KEY")
}

fun getUnsplashApiKey(): String {
    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())
    return properties.getProperty("UNSPLASH_API_KEY")
}

android {
    namespace = "com.tvt.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tvt.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "OPENWEATHER_API_KEY", "\"${getOpenWeatherApiKey()}\"")
        buildConfigField("String", "UNSPLASH_API_KEY", "\"${getUnsplashApiKey()}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger Hilt for dependency injection
    implementation(libs.dagger.hilt)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation)

    // Retrofit for network operations
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil for image loading in Compose
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Accompanist permissions for handling permissions
    implementation(libs.accompanist.permissions)

    // Navigation component for Compose
    implementation(libs.navigation.compose)

    // Kotlin Serialization for JSON parsing
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlin.reflect)

    implementation ("androidx.core:core-splashscreen:1.0.1")
}