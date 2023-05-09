import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath(group = "org.postgresql", name = "postgresql", version = "42.6.0")
    }
}

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"

    id("nu.studer.jooq") version "8.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    val jooqVersion = "3.13.4"
    implementation("org.jooq:jooq:$jooqVersion")
    implementation("org.jooq:jooq-meta:$jooqVersion")
    implementation("org.jooq:jooq-codegen:$jooqVersion")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    val postgresVersion = "42.2.16"
    implementation("org.postgresql:postgresql:$postgresVersion")
    jooqGenerator("org.postgresql:postgresql:$postgresVersion")
    jooqGenerator("org.jooq:jooq-meta:$jooqVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("${buildDir}/generated-sources/jooq")
    }
}

jooq {
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

    configurations {
        create("database") {
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/postgres"
                    user = "postgres"
                    password = "password"
                }

                generator.apply {
                    name = "org.jooq.codegen.JavaGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        schemata.addAll(listOf(
                            org.jooq.meta.jaxb.SchemaMappingType().withInputSchema("public")
                        ))
                    }
                    strategy.name = "org.jooq.codegen.example.JPrefixGeneratorStrategy"
                    generate.apply {
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                        isSpatialTypes = false
                        isKeys = true
                    }
                    target.apply {
                        packageName = "com.example.learning.gen"
                        directory = "${buildDir}/generated-sources/jooq"
                        encoding = "UTF-8"
                    }
                }
            }
        }
    }
}
