import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details take a look at the Writing Custom Plugins chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.0.2/userguide/custom_plugins.html
 */

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    `maven-publish`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.31"
}

val gitlabProjectId: String by extra
val gitLabPrivateToken: String by extra
val gId: String by extra
val pluginVersion: String by extra

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    google()
    jcenter()
}

java {
    withSourcesJar()
}

afterEvaluate {
    publishing {
        repositories {
            maven {
//                url = uri("https://github.com/api/v4/projects/$gitlabProjectId/packages/maven")
                credentials(HttpHeaderCredentials::class.java) {
                    name = "Private-Token"
                    value = gitLabPrivateToken
                }
                authentication {
                    register<HttpHeaderAuthentication>("header")
                }
            }
        }
        publications {
            register<MavenPublication>("tinyhai") {
                from(components["java"])
                groupId = gId
                artifactId = project.name
                version = pluginVersion
            }
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    compileOnly("com.android.tools.build:gradle:7.0.2")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

gradlePlugin {
    plugins {
        register("init") {
            id = "tinyhai.init"
            implementationClass = "cn.tinyhai.android.plugin.TinyhaiInitPlugin"
        }
        register("module") {
            id = "tinyhai.module"
            implementationClass = "cn.tinyhai.android.plugin.TinyhaiPlugin"
        }
        register("root") {
            id = "tinyhai.root"
            implementationClass = "cn.tinyhai.android.plugin.TinyHaiRootPlugin"
        }
    }

    isAutomatedPublishing = false
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

/*
// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {
}

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}
*/
