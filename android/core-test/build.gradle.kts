apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(Coroutines.coroutineLib)

    "testImplementation"(Testing.junit4)
    "testImplementation"(Testing.junitAndroidExt)
    "testImplementation"(Testing.truth)
    "testImplementation"(Testing.coroutines)
    "testImplementation"(Testing.turbine)
    "testImplementation"(Testing.composeUiTest)
    "testImplementation"(Testing.mockk)
    "testImplementation"(Testing.mockWebServer)
    "testImplementation"(Testing.unitCoreTesting)
    "testImplementation"(Testing.unitRoomTesting)
    "testImplementation"(Testing.unitCore)
    "testImplementation"(Testing.unitMockitoKotlin)
    "testImplementation"(Testing.mockitoInline)
    "testImplementation"(Testing.mockitoCore)
    "testImplementation"(Testing.orgJson)
    "testImplementation"(Testing.roboElectric)

    "androidTestImplementation"(Testing.junit4)
    "androidTestImplementation"(Testing.junitAndroidExt)
    "androidTestImplementation"(Testing.truth)
    "androidTestImplementation"(Testing.coroutines)
    "androidTestImplementation"(Testing.turbine)
    "androidTestImplementation"(Testing.composeUiTest)
    "androidTestImplementation"(Testing.mockk)
    "androidTestImplementation"(Testing.mockWebServer)
    "androidTestImplementation"(Testing.hiltTesting)

    "kaptAndroidTest"(DaggerHilt.hiltCompiler)
    "androidTestImplementation"(Testing.testRunner)
}
