/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
        maven { url = uri("https://mvn.intellectualsites.com/content/repositories/releases/") }
        maven { url = uri("https://ci.athion.net/plugin/repository/tools/") }
        maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    }

    dependencies {
        implementation("com.intellectualsites.fawe:FAWE-Bukkit:1.16-725")
        compileOnly("io.papermc:paperlib:1.0.6")
    }

}

mapOf(
    "spigot_v1_15_R2" to "1_15_r1",
    "spigot_v1_16_R3" to "1_16_r3",
    "spigot_v1_17_R1" to "1_17_r1"
).forEach { (projectName, dep) ->
    project(":$projectName") {
        dependencies.compileOnly("com.destroystokyo.paperv$dep:paperv$dep:$dep")
        dependencies.compileOnly("org.spigotmcv$dep:spigotmcv$dep:$dep")
    }
}

tasks.jar {
    from(subprojects.map {
        it.sourceSets["main"].output
    })
}
