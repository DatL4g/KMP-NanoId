buildscript {
    repositories {
        addRepos()
        maven(uri("https://dl.bintray.com/kotlin/kotlin-eap")) {
            metadataSources {
                gradleMetadata()
                mavenPom()
            }
        }
        maven(uri("https://dl.bintray.com/korlibs/korlibs")) {
            metadataSources {
                gradleMetadata()
                mavenPom()
            }
        }
    }

    dependencies {
        classpath(ClassPaths.AndroidGradleBuildTools)
        classpath(ClassPaths.KotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        addRepos()
        maven(uri("https://dl.bintray.com/kotlin/kotlin-eap")) {
            metadataSources {
                gradleMetadata()
                mavenPom()
            }
        }
        maven(uri("https://dl.bintray.com/korlibs/korlibs")) {
            metadataSources {
                gradleMetadata()
                mavenPom()
            }
        }
    }
}