package com.iprayforgod.core.platform.functional

/**
 * By making a class a sealed class, We will be telling the compiler that only the sub-classes possible are the ones that we can put in here
 * ***
 * On comparing when with sealed class: -> When condition will have a else case but a sealed class already knows the number of conditions it has so there is no scenario of else condition
 */
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}