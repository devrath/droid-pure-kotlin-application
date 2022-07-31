apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.featuresOnboardingOnboardingDomain))

    // --> Pagers for on-boarding
    "implementation"(Accompanist.accompanistPager)
    "implementation"(Accompanist.accompanistPagerIndicators)
}
