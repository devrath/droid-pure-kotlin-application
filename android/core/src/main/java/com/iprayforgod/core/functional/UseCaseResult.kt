package com.iprayforgod.core.functional

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class UseCaseResult<out R> {

    data class Success<out T>(val value: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$value]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * [Success.value] if [UseCaseResult] is of type [Success]
 */
fun <T> UseCaseResult<T>.successOr(fallback: T): T {
    return (this as? UseCaseResult.Success<T>)?.value ?: fallback
}

val <T> UseCaseResult<T>.data: T?
    get() = (this as? UseCaseResult.Success)?.value

/**
 * Updates value of [liveData] if [UseCaseResult] is of type [Success]
 */
inline fun <reified T> UseCaseResult<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is UseCaseResult.Success) {
        liveData.value = value
    }
}

/**
 * Updates value of [stateFlow] if [UseCaseResult] is of type [Success]
 */
inline fun <reified T> UseCaseResult<T>.flowOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is UseCaseResult.Success) {
        stateFlow.value = value
    }
}