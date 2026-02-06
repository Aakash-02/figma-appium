# Android Project Configuration for Cursor AI

> **Use these exact versions and configurations to avoid build failures**

## 📋 Quick Reference

### Gradle & Plugin Versions

#### `gradle/wrapper/gradle-wrapper.properties`
```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

#### `build.gradle.kts` (Root Level)
```kotlin
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}
```

#### `app/build.gradle.kts`
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.yourapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.yourapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    buildFeatures {
        viewBinding = true
        dataBinding = false  // Explicitly disable to avoid conflicts
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

## 🔑 Key Configuration Points

### ✅ Versions That Work
- **Gradle**: `8.2` (not 9.x)
- **Android Gradle Plugin (AGP)**: `8.1.4` (not 9.0)
- **Kotlin**: `1.9.20` (not 2.0.x)
- **Java Compatibility**: `1.8` (not 11 or higher)
- **Compile SDK**: `34`
- **Target SDK**: `34`
- **Min SDK**: `24` (Android 7.0+)

### ❌ Versions to Avoid
- **Gradle 9.x** → Causes "module() method deprecated" errors
- **AGP 9.0.x** → Incompatible with Gradle 8.x
- **Kotlin 2.0.x** → Causes "Cannot add extension with name 'kotlin'" error
- **Java 11+** → Can cause compatibility issues with older dependencies

## 🛠️ Environment Setup

### JAVA_HOME Configuration
```bash
export JAVA_HOME=/Applications/Android\ Studio.app/Contents/jbr/Contents/Home
```

### Build Commands
```bash
# Clean build
./gradlew clean assembleDebug

# Clean Gradle cache (if needed)
rm -rf ~/.gradle/caches
rm -rf .gradle
rm -rf app/build
```

## 🎨 Required Resource Files

### `app/src/main/res/values/colors.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Your app colors -->
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    <color name="primary">#FEA903</color>
    
    <!-- Legacy colors for Material compatibility -->
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
</resources>
```

### `app/src/main/res/values/themes.xml`
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Base.Theme.YourApp" parent="Theme.Material3.Light.NoActionBar">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="android:statusBarColor">@color/white</item>
        <item name="android:windowLightStatusBar">true</item>
    </style>

    <style name="Theme.YourApp" parent="Base.Theme.YourApp" />
</resources>
```

### `app/src/main/res/values-night/themes.xml`
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Base.Theme.YourApp" parent="Theme.Material3.Dark.NoActionBar">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="android:statusBarColor">@color/black</item>
    </style>

    <style name="Theme.YourApp" parent="Base.Theme.YourApp" />
</resources>
```

## 🚨 Common Build Errors & Solutions

### Error 1: "Cannot add extension with name 'kotlin'"
**Cause**: Kotlin 2.0+ causes plugin conflicts  
**Solution**: Use Kotlin 1.9.20
```kotlin
id("org.jetbrains.kotlin.android") version "1.9.20"
```

### Error 2: "Cannot mutate dependencies after configuration"
**Cause**: Data binding conflicts with view binding  
**Solution**: Explicitly disable data binding
```kotlin
buildFeatures {
    viewBinding = true
    dataBinding = false
}
```

### Error 3: "'module()' method is deprecated"
**Cause**: Gradle 9.x incompatibility with AGP 8.x  
**Solution**: Use Gradle 8.2
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
```

### Error 4: "Resource color/purple_200 not found"
**Cause**: Missing legacy Material colors  
**Solution**: Add legacy colors to `colors.xml`

### Error 5: "No Java compiler found"
**Cause**: Wrong JDK or JAVA_HOME not set  
**Solution**: Set JAVA_HOME to Android Studio's JDK
```bash
export JAVA_HOME=/Applications/Android\ Studio.app/Contents/jbr/Contents/Home
```

## 📦 Essential Dependencies

### Core AndroidX
```kotlin
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.11.0")
```

### UI Components
```kotlin
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("androidx.cardview:cardview:1.0.0")
```

### Image Loading
```kotlin
implementation("com.github.bumptech.glide:glide:4.16.0")
```

## 🎯 Best Practices

1. **Always use direct plugin IDs** instead of version catalog aliases for main plugins
2. **Disable data binding** if you only need view binding
3. **Use Java 1.8** for better compatibility
4. **Include legacy colors** for Material theme compatibility
5. **Set explicit namespace** in app build.gradle
6. **Clear Gradle cache** if switching between major versions
7. **Use Android Studio's bundled JDK** for JAVA_HOME

## 📝 Prompt Template for Cursor

```
Create an Android app with the following specifications:

IMPORTANT: Use these exact configurations to avoid build failures:
- Gradle: 8.2
- AGP: 8.1.4
- Kotlin: 1.9.20
- Java: 1.8
- Compile SDK: 34
- Min SDK: 24
- Enable viewBinding, disable dataBinding
- Include legacy Material colors (purple_200, teal_200, etc.)

[Your app requirements here...]
```

## 🔗 Reference Files

- This configuration is based on a working e-commerce app project
- All settings have been tested and verified to build successfully
- Last updated: February 2026

---

**📌 Save this file in your project root and reference it when working with Cursor AI on Android projects!**
