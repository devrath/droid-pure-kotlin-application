apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(Coroutines.coroutineLib)
    // For injecting the mocks
    "testImplementation"(project(Modules.coreMockFactory))
}
