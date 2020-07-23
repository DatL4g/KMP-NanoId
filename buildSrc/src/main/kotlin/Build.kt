import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.addRepos() {
    mavenLocal {
        metadataSources {
            gradleMetadata()
            mavenPom()
        }
    }
    mavenCentral {
        metadataSources {
            gradleMetadata()
            mavenPom()
        }
    }
    jcenter {
        metadataSources {
            gradleMetadata()
            mavenPom()
        }
    }
    google {
        metadataSources {
            gradleMetadata()
            mavenPom()
        }
    }
    gradlePluginPortal()
}