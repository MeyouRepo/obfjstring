# obfjstring
## Android Library
```groovy
android.libraryVariants.all { variant ->
    def variantJavaCompile = variant.getJavaCompileProvider().get()
    variantJavaCompile.doLast {
        javaexec {
            main = "-jar";
            args = [
                    "obfjstring-1.0-SNAPSHOT.jar",
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
                    "obfjstring-1.0-SNAPSHOT.jar",
                    project.name,
                    variantJavaCompile.destinationDir
            ]
        }
    }
}
```