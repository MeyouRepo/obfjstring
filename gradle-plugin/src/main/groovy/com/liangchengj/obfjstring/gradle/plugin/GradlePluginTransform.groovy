package com.liangchengj.obfjstring.gradle.plugin


import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.google.common.collect.ImmutableSet
import com.liangchengj.obfjstring.JavaStringObfuscator
import com.liangchengj.obfjstring.io.Stream
import com.liangchengj.obfjstring.main.ObfuscateClassString
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils

class GradlePluginTransform extends Transform {
    @Override
    String getName() {
        return 'ObfuscateJavaString'
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return ImmutableSet.<QualifiedContent.Scope> of(
                QualifiedContent.Scope.PROJECT
        )
    }

    @Override
    boolean isIncremental() {
        return true
    }


    @Override
    void transform(TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        def lineS = "=============================="
        println("Start classes obfuscate ${lineS}")
        transformInvocation.inputs.each { input ->
            input.directoryInputs.each { directoryInput ->
                directoryInput.file.eachFileRecurse { file ->
                    if (file.name.endsWith(JavaStringObfuscator.JAVA_CLASS_FILE_EXT)) {
                        def fAbsolutePath = file.absolutePath
                        ObfuscateClassString.processClassFile(fAbsolutePath)
                        println("\tObfuscated class file: ${fAbsolutePath}")
                    }
                }

                println("End classes obfuscate ${lineS}")
                println()

                def directoryInputFileAbsolutePath = directoryInput.file.absolutePath
                if (directoryInputFileAbsolutePath.contains('intermediates')
                        && directoryInputFileAbsolutePath.contains('javac')) {
                    println("Start write dep classes ${lineS}")
                    ObfuscateClassString.writeDepClassToVariant(directoryInputFileAbsolutePath)
                    println("End write dep classes ${lineS}")
                }

                copyDir(directoryInput, transformInvocation.outputProvider)
            }
            input.jarInputs.each { jarInput ->
                copyJar(jarInput, transformInvocation.outputProvider)
            }
        }
        println("Obfuscate java string completed !!!")
    }

    private void copyDir(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        def dst = outputProvider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)

        FileUtils.copyDirectory(directoryInput.file, dst)
    }

    private void copyJar(JarInput jarInput, TransformOutputProvider outputProvider) {
        def jarName = jarInput.name
        def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
        if (jarName.endsWith(".jar")) {
            jarName = jarName.substring(0, jarName.length() - 4)
        }
        def dst = outputProvider.getContentLocation("${jarName}${md5Name}",
                jarInput.contentTypes, jarInput.scopes, Format.JAR)
        def fis = new FileInputStream(jarInput.file)
        def fos = new FileOutputStream(dst)
        Stream.readAndWrite(fis, fos)
    }

}
