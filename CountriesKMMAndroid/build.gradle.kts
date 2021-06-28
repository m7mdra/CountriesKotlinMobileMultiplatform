plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-extensions")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation ("com.google.android.gms:play-services-maps:17.0.1")

}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.m7mdra.countrieskmm.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}