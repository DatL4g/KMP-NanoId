pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }

}

rootProject.name = "NanoId"

enableFeaturePreview("GRADLE_METADATA")
