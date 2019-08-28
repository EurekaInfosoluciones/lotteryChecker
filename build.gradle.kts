buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
    }
}

plugins {
    id("com.github.ben-manes.versions") version Versions.BEN_NAMES
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

apply(from = "gradle/scripts/ben-names.gradle")
