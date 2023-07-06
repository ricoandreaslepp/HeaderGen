plugins {
    id("java")
}

version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("net.portswigger.burp.extensions:montoya-api:2023.3")
}
