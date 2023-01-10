plugins {
    kotlin("jvm") version "1.8.0"

    jacoco
}

group = "com.akikanellis.gradletest"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

kotlin {
    jvmToolchain {
        // renovate: datasource=adoptium-java depName=java
        val javaVersion = "16.0.2+7"
        val javaMajorVersion = javaVersion.split(".").first()

        vendor.set(JvmVendorSpec.ADOPTIUM)
        languageVersion.set(JavaLanguageVersion.of(javaMajorVersion))
    }
}

tasks.test {
    useJUnitPlatform()
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
