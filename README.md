# obfjstring
## Tell a joke: Dogs can't change to eat shit ...
## I hope we all send emails to the world regularly ...

******

## Android Library
```groovy
android.libraryVariants.configureEach { variant ->
    def variantJavaCompile = variant.getJavaCompileProvider().get()
    variantJavaCompile.doLast {
        javaexec {
            mainClass = "com.lcjuves.obfjstring.main.ObfuscateClassString"
            args = [
                    "obfjstring-1.0-SNAPSHOT.jar",
                    project.name,
                    variantJavaCompile.destinationDirectory
            ]
        }
    }
}
```

## Android Application
```groovy
android.applicationVariants.configureEach { variant ->
    def variantJavaCompile = variant.getJavaCompileProvider().get()
    variantJavaCompile.doLast {
        javaexec {
            mainClass = "com.lcjuves.obfjstring.main.ObfuscateClassString"
            args = [
                    "obfjstring-1.0-SNAPSHOT.jar",
                    project.name,
                    variantJavaCompile.destinationDirectory
            ]
        }
    }
}
```