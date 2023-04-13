import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.cinema.classic"
    compileSdkVersion(33)

    defaultConfig {
        applicationId = "com.cinema.classic"
        minSdkVersion(23)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "GOOGLE_API_KEY", getApiKey("GOOGLE_API_KEY"))
        buildConfigField("String", "NAVER_CLIENT_KEY", getApiKey("NAVER_CLIENT_KEY"))
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.material:material:1.4.1")
    implementation("androidx.compose.material:material-icons-extended:1.4.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")
    implementation("androidx.fragment:fragment-ktx:1.5.6")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    implementation("androidx.compose.ui:ui-viewbinding:1.4.1")
    implementation("androidx.compose.material3:material3:1.1.0-beta02")

    implementation("androidx.compose.runtime:runtime-livedata:1.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    kapt("androidx.room:room-compiler:2.5.1")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}