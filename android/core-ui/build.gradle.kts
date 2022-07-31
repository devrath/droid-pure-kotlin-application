apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Compose.compiler)
    "implementation"(Compose.ui)
    "implementation"(Compose.uiToolingPreview)
    "implementation"(Compose.hiltNavigationCompose)
    "implementation"(Compose.material)
    "implementation"(Compose.runtime)
    "implementation"(Compose.navigation)
    "implementation"(Compose.viewModelCompose)
    "implementation"(Compose.activityCompose)

    "implementation"(Google.material)

    "implementation"(SplashScreen.splashAndroidApi)
}
