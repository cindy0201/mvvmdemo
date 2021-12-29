package com.hxt.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class TinaPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def demo = project.extensions.create("custom", ExtensionDemo)
        def demo2 = project.extensions.create("custom2", ExtensionDemo2)
        project.afterEvaluate {
            println("${demo.name}")
            println("${demo.version}")

            println("${demo2.name}")
            println("${demo2.version}")
        }
    }
}