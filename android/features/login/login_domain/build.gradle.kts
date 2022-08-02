apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "testImplementation"(project(Modules.mockFactory))
    "implementation"(Coroutines.coroutineLib)
}
