plugins {
    id("com.android.library") version "3.6.1"
    kotlin("multiplatform").version("1.3.72")
    id("maven-publish")
}
group = "de.datlag"
version = "1.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    google()
    gradlePluginPortal()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    maven {
        url = uri("https://dl.bintray.com/korlibs/korlibs")
    }
}

android {
    compileSdkVersion = 29.toString()
    buildToolsVersion = "29.0.3"

    defaultConfig {
        versionCode = 11
        versionName = "1.1.0"
        applicationId = "de.datlag.nanoid"
    }

    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
        }

        val release by getting {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {  }
        nodejs {  }
    }
    android {
        publishAllLibraryVariants()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("com.soywiz.korlibs.krypto:krypto:1.11.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting { }
        val nativeTest by getting { }
    }
}