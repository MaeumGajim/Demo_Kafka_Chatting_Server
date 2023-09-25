import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.16-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.info"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // db
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    runtimeOnly("com.mysql:mysql-connector-j")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // socket
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")

    // bad word filter
    implementation("io.github.vaneproject:badwordfiltering:1.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.data.redis.core.RedisHash")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.data.redis.core.RedisHash")
}
