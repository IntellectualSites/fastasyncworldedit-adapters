plugins {
    java
}

subprojects {
    apply(plugin = "java")
    group = "com.sk89q.worldedit.adapters"
    version = "1.0"

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(16))
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://ci.athion.net/plugin/repository/tools/") }
        maven { url = uri("https://mvn.intellectualsites.com/content/groups/public/") }
        maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    }

    dependencies {
        compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit:1.17-287")
        compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core:1.17-287")
        compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
        compileOnly("io.papermc:paperlib:1.0.6") {
            because("Shading is done in FAWE")
        }
    }

    configurations.all {
        attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 16)
    }

    tasks.compileJava.configure {
        options.release.set(11)
    }

}

// Paper 1.16 and below has a different classpath
mapOf(
        "spigot_v1_15_R2" to "1_15_r1",
        "spigot_v1_16_R3" to "1_16_r3"
).forEach { (projectName, dep) ->
    project(":$projectName") {
        dependencies.compileOnly("com.destroystokyo.paperv$dep:paperv$dep:$dep")
        dependencies.compileOnly("org.spigotmcv$dep:spigotmcv$dep:$dep")
    }
}

mapOf(
        "spigot_v1_17_R1" to "1_17_r1",
        "spigot_v1_17_R1_2" to "1_17_r1_2",
).forEach { (projectName, dep) ->
    project(":$projectName") {
        dependencies.compileOnly("io.papermc.paperv$dep:paperv$dep:$dep")
        dependencies.compileOnly("org.spigotmcv$dep:spigotmcv$dep:$dep")
    }
}

tasks.jar {
    from(subprojects.map {
        it.sourceSets["main"].output
    })
}
