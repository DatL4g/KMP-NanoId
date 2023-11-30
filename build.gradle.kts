import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library") version "8.1.4"
    kotlin("multiplatform") version "1.9.21"
    `maven-publish`
    signing
    id("com.vanniktech.maven.publish") version "0.25.3"
}

val libName = "nanoid"
val libVersion = "1.0.1"
val artifact = "dev.datlag.nanoid"
group = artifact
version = libVersion

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

android {
    compileSdk = 34
    namespace = artifact

    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            isShrinkResources = false
        }

        val release by getting {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvm()
    androidTarget {
        publishAllLibraryVariants()
    }
    js(IR) {
        browser {
            testTask {
                useMocha()
            }
        }
        nodejs()
        binaries.executable()
    }
    macosX64()
    macosArm64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    linuxArm64()
    mingwX64()
    watchosArm64()
    watchosSimulatorArm64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toIntOrNull() ?: (JavaVersion.VERSION_17.ordinal + 1))
    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.soywiz.korlibs.krypto:krypto:4.0.10")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

mavenPublishing {
    publishToMavenCentral(host = SonatypeHost.S01, automaticRelease = true)
    signAllPublications()

    coordinates(
        groupId = artifact,
        artifactId = libName,
        version = libVersion
    )

    pom {
        name.set(libName)
        description.set("A unique string ID generator for Javascript, JVM and Native written in Kotlin.")
        url.set("https://github.com/DatL4g/KMP-NanoId")

        licenses {
            license {
                name.set("MIT")
                url.set("https://github.com/DatL4g/KMP-NanoId/blob/master/LICENSE")
            }
        }

        scm {
            url.set("https://github.com/DatL4g/KMP-NanoId")
            connection.set("scm:git:git://github.com/DatL4g/KMP-NanoId.git")
        }

        developers {
            developer {
                id.set("DatL4g")
                name.set("Jeff Retz (DatLag)")
                url.set("https://github.com/DatL4g")
            }
        }
    }
}
