import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.9.10"

    jacoco
}

group = "com.akikanellis.renovatetests"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
}

kotlin {
    jvmToolchain {
        // renovate: datasource=java-version depName=java
        val javaVersion = "17.0.8+101"
        val javaMajorVersion = javaVersion.split(".").first()

        vendor.set(JvmVendorSpec.ADOPTIUM)
        languageVersion.set(JavaLanguageVersion.of(javaMajorVersion))
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showExceptions = true
        showCauses = true
        exceptionFormat = TestExceptionFormat.FULL

        events = setOf(
            TestLogEvent.STARTED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.FAILED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR
        )
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(false)
    }
}

tasks.register("publish") {
    group = "publish"
    description =
        """
        Dummy task to pass the verification phase of the gradle-semantic-release-plugin.
        See: https://github.com/KengoTODA/gradle-semantic-release-plugin/issues/435
        """.trimIndent()
}