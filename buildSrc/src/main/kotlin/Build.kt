import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.addRepos() {
    mavenLocal()
    mavenCentral()
    jcenter()
    google()
    gradlePluginPortal()
}