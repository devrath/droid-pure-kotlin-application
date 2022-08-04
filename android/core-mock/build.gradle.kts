apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(AndroidX.coreKtx)
    "implementation"(project(Modules.core))

    "implementation"(project(Modules.featureLoginDomain))

}