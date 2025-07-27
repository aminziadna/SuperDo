plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(project(":shared"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                
                // Koin for DI
                implementation("io.insert-koin:koin-core:${rootProject.ext["koin_version"]}")
            }
        }
    }
}