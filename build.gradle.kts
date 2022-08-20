plugins {
    java
}

subprojects {
    apply(plugin = "java")
    group = "com.sk89q.worldedit.adapters"
    version = "2.0.0"

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://ci.athion.net/plugin/repository/tools/") }
        maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
    }

    dependencies {
        implementation(platform("com.intellectualsites.bom:bom-1.16.x:1.13"))
        compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit")
        compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
        compileOnly("com.destroystokyo.paper:paper-api")
        compileOnly("io.papermc:paperlib") {
            because("Shading is done in FAWE")
        }
    }

    configurations.all {
        attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 17)
    }

    tasks.compileJava.configure {
        options.release.set(17)
    }

}

// Paper 1.16 and below has a different classpath
mapOf(
        "spigot_v1_16_R3" to "1_16_r3"
).forEach { (projectName, dep) ->
    project(":$projectName") {
        dependencies.compileOnly("com.destroystokyo.paperv$dep:paperv$dep:$dep")
        dependencies.compileOnly("org.spigotmcv$dep:spigotmcv$dep:$dep")
    }
}

mapOf(
        "spigot_v1_17_R1" to "1_17_r1",
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
