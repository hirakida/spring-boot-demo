plugins {
    id("spring-boot-demo.kotlin-conventions")
    kotlin("plugin.spring")
    alias(libs.plugins.org.springframework.boot)
    alias(libs.plugins.io.spring.dependency.management)
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(libs.org.jetbrains.exposed.exposed.spring.boot.starter)
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
