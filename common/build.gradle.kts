import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.1.0"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

group = "com.roudikk"
version = "1.0"

val currentVersion = "1.0.0-beta16"
val accompanistVersion = "0.23.1"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.material3)
                api(compose.material)
                api("cafe.adriel.voyager:voyager-navigator:$currentVersion")
                api("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$currentVersion")
                api("cafe.adriel.voyager:voyager-tab-navigator:$currentVersion")
                api("cafe.adriel.voyager:voyager-transitions:$currentVersion")
                api("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
                api("com.google.accompanist:accompanist-insets:$accompanistVersion")
                api("com.squareup.sqldelight:coroutines-extensions:1.5.3")
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.squareup.sqldelight:android-driver:1.5.3")
                api("androidx.appcompat:appcompat:1.4.1")
                api("androidx.core:core-ktx:1.7.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:sqlite-driver:1.5.3")
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    database(name = "YoutubeCreatorHelperDatabase") {
        packageName = "com.roudikk"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.animation.ExperimentalAnimationApi"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=org.jetbrains.compose.ExperimentalComposeLibrary"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
}