buildscript {
    repositories {
        addRepos()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
        maven {
            url = uri("https://dl.bintray.com/korlibs/korlibs")
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
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
        maven {
            url = uri("https://dl.bintray.com/korlibs/korlibs")
        }
    }
}