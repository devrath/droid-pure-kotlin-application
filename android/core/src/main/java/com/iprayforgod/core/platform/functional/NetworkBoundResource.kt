package com.iprayforgod.core.platform.functional

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * The way network bound resource works: ---> We need to pass different functions to it that does different jobs
 * NetworkBoundResource function: ---> Co-ordinates these different functions passed in the argument, It decides when each function has to be called
 * *****
 * RequestType : -> It will be list of news-article DTO
 */
inline fun <ResultType, RequestType> networkBoundResource(
    // Function that fetches data from database.
    crossinline query: () -> Flow<ResultType>,
    // Function that fetches data from the web
    crossinline fetch: suspend () -> RequestType,
    // Function that stores data in database
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    // Function that decides that whether this data is good to show or we need to fetch a new data
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline onFetchSuccess: () -> Unit = { },
    crossinline onFetchFailed: (Throwable) -> Unit = { }
) = channelFlow {
    // This function is same as Flow.collect(), Also we collect only one value from the channelFlow and stop collecting
    // One value means, One list of news articles
    val data = query().first()

    /**
     * shouldFetch is the function that takes data from database and checks its stale or not
     * *** *** *** *** *** *** *** ***
     * How it does is not up-to the network bound resource, because we need to keep it reusable
     */
    if (shouldFetch(data)) {
        /**
         *  Here we emit the loading state
         *  We are keeping the co-routine reference in a variable because later we might need to cancel it
         *  **
         *  This will launch a separate co-routine inside the channel flow
         */
        val loading = launch {
            // Here we launch a separate co-routine where loading is triggered
            // The launch will automatically use outer scope which is view model scope
            query().collect { send(Resource.Loading(it)) }
        }

        try {
            // Get the data from the rest API and store it in the database
            saveFetchResult(fetch())
            onFetchSuccess()
            // Cancel the loading updates
            loading.cancel()
            query().collect { send(Resource.Success(it)) }
        } catch (t: Throwable) {
            onFetchFailed(t)
            loading.cancel()
            query().collect { send(Resource.Error(t, it)) }
        }
    } else {
        // If data from database is ok and we don't need new update
        query().collect { send(Resource.Success(it)) }
    }
}