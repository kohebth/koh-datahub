import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Database
import org.jooq.meta.jaxb.Property

plugins {
    java
    id("nu.studer.jooq") version "7.1.1"
}

group = "koh.datahub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-reload4j:2.0.13")

    implementation("org.jooq:jooq:3.16.8")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")

    jooqGenerator("org.jooq:jooq-meta:3.16.8")
    jooqGenerator("org.jooq:jooq-codegen:3.16.8")
    jooqGenerator("org.jooq:jooq-meta-extensions:3.16.8")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
}

jooq {
    configurations.create("main") {
        generateSchemaSourceOnCompilation.set(false)
        jooqConfiguration.apply {
            generatorConfigDDL()
        }
    }
}

sourceSets.main {
    java.srcDir("src/generated")
    java.srcDir("src/main")
}

fun Configuration.generatorConfigDDL() {
    generator.apply {
        name = "org.jooq.codegen.DefaultGenerator"
        database.apply {
            databaseConfigDDL()
        }
        generate.apply {
            isRelations = true
            isDeprecated = false
            isDaos = false
            isFluentSetters = true
            isImmutablePojos = false
            isRecords = true
            isPojos = false
        }
        target.apply {
            packageName = "koh.db.hub"
            directory = "src/generated"
        }
        strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
    }
}

fun Database.databaseConfigDDL() {
    name = "org.jooq.meta.extensions.ddl.DDLDatabase"
    properties = listOf(
            Property().withKey("scripts").withValue("src/main/resources/init/*.sql"),
            Property().withKey("sort").withValue("alphanumeric"),
            Property().withKey("defaultNameCase").withValue("lower"),
            Property().withKey("deprecationOnUnknownTypes").withValue("false")
    )
}

gradle.projectsEvaluated {
    if (gradle.startParameter.taskNames.contains("wrapper")) {
        tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq").get().generate()
    }
}
