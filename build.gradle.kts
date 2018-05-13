import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "cn.net.nikai.kotlint"
version = "1.0-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.10"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }

}

apply {
    plugin("kotlin")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    //    compile(kotlinModule("stdlib-jdk8", kotlin_version))
//    compile
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

fun createDir(path: String) {
    val dir = File(path)
    if (!dir.exists())
        dir.mkdirs()

}

//自定义任务
task("makeJavaDir", {
    doFirst {
        println("make java dirs")
        val paths = arrayListOf<String>("src/main/java", "src/main/resources", "src/test/java", "src/test/resources")
        for (path in paths)
            createDir(path)
    }
})

//自定义任务依赖
task("makeWebDir", {
    doLast {
        println("make web dirs")
        val paths = arrayListOf<String>("src/main/webapp")
        paths.forEach {
            createDir(it)
        }
    }
}).dependsOn("makeJavaDir")

//多个任务的合集就是任务集
tasks {
    "opendoor"{

    }

    "putelephont"{

    }.dependsOn("opendoor")

    "closedoor"{

    }.dependsOn("putelephont")
}

//Task的默认属性
task("println") {
    project.properties.forEach { t, any ->
        println("$t:$any")

    }
}
