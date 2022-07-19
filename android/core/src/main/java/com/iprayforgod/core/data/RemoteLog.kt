package com.iprayforgod.core.data

data class RemoteLog(
    var priority: String,
    var tag: String?,
    var message: String,
    var throwable: String?,
    val time: String
)
