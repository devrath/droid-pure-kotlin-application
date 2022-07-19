package com.iprayforgod.core.domain

interface LoggerFeature {
    fun d(msg: String?)
    fun e(msg: String?)
    fun w(msg: String?)
    fun v(msg: String?)
    fun i(msg: String?)
}
