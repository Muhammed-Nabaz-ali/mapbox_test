pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = "sk.eyJ1Ijoib3JiaXRhbC1jZWUiLCJhIjoiY2wxZG0zdWdiMGlwZTNwcDNzbzB5OHgyOSJ9.8vSNQoEyDshU0_eySbuXhQ"
            }
        }
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Cee Radar"
include(":app")
