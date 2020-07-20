plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("maven-publish")
}
group = "de.datlag"
version = Versions.releaseVersion

android {
    compileSdkVersion = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        versionCode = Versions.releaseVersionCode
        versionName = Versions.releaseVersion
    }

    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
        }

        val release by getting {
            isMinifyEnabled = false
            isDebuggable = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = Versions.java.toString()
        }
    }
    js {
        browser()
        nodejs()
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
                implementation("com.soywiz.korlibs.krypto:krypto:${Versions.krypto}")
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