package com.liangchengj.obfjstring.gradle.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradlePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def ext = project.extensions.getByType(BaseExtension)
        ext.registerTransform(new GradlePluginTransform())
    }
}
