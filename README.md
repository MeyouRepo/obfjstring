# obfuscate-java-string
## Android Library
```groovy
android.libraryVariants.all { variant ->
    def variantJavaCompile = variant.getJavaCompileProvider().get()
    variantJavaCompile.doLast {
        javaexec {
            main = "-jar";
            args = [
                    "obfuscate-java-string-1.0-SNAPSHOT.jar",
                    project.name,
                    variantJavaCompile.destinationDir
            ]
        }
    }
}
```

## Android Application
```groovy
android.applicationVariants.all { variant ->
    def variantJavaCompile = variant.getJavaCompileProvider().get()
    variantJavaCompile.doLast {
        javaexec {
            main = "-jar";
            args = [
                    "obfuscate-java-string-1.0-SNAPSHOT.jar",
                    project.name,
                    variantJavaCompile.destinationDir
            ]
        }
    }
}
```