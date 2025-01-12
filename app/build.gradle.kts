plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.semesterproject"
    compileSdk = 34

    buildFeatures {
        viewBinding= true
    }

    defaultConfig {
        applicationId = "com.example.semesterproject"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.glide)
    annotationProcessor(libs.glideCompiler)
    implementation(libs.roundedimageview)
    implementation(libs.materialSearchBar)
    // Material Components for Android. Replace the version with the latest version of Material Components library.
    implementation (libs.materialComponents)

    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation (libs.circleIndicator)

    implementation (libs.imageCarousel)
}


