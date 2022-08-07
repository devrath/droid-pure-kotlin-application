package com.iprayforgod.core.domain.models

data class RemoteLog(
    var priority: String,
    var tag: String?,
    var message: String,
    var throwable: String?,
    val time: String
)
