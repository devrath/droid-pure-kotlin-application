package com.iprayforgod.core.modules.logger.models

data class RemoteLog(
    var priority: String,
    var tag: String?,
    var message: String,
    var throwable: String?,
    val time : String
)
