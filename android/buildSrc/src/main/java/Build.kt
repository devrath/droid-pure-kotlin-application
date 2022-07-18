object Build {
    private const val androidBuildToolsVersion = "7.0.4"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.42"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    private const val googleServicesVersion = "4.3.13"
    const val googleServicesGradlePlugin = "com.google.gms:google-services:$googleServicesVersion"

    object BuildPlugins {
        const val androidLibrary = "com.android.library"
        const val androidApplication = "com.android.application"
        const val daggerHiltAndroidPlugin = "dagger.hilt.android.plugin"
        const val kotlinKapt = "kotlin-kapt"
        const val googleServices = "com.google.gms.google-services"

        const val kotlinAndroid = "kotlin-android"
        const val kotlinAndroidExtensions = "kotlin-android-extensions"
    }

    object BuildModule {
        const val kotlinAndroid = "android"
    }
}