package com.iprayforgod.core.data.implementation.logger.utilities.modules

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.iprayforgod.core.BuildConfig
import com.iprayforgod.core.domain.models.DeviceDetails
import com.iprayforgod.core.domain.models.RemoteLog
import com.orhanobut.logger.Logger
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimberRemoteTree(private val deviceDetails: DeviceDetails) : Timber.DebugTree() {

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS a zzz", Locale.getDefault())
    private val date = dateFormat.format(Date(System.currentTimeMillis()))

    private val logRef = FirebaseDatabase.getInstance().getReference("logs/$date/${deviceDetails.deviceId}")

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (BuildConfig.DEBUG) {
            // --> OrHaNoButModule logging
            Logger.log(priority, tag, message, t)

            val timestamp = System.currentTimeMillis()
            val time = timeFormat.format(Date(timestamp))
            val remoteLog = RemoteLog(priorityAsString(priority), tag, message, t.toString(), time)

            with(logRef) {
                updateChildren(mapOf(Pair("-DeviceDetails", deviceDetails)))
                child(timestamp.toString()).setValue(remoteLog)
            }
        } else super.log(priority, tag, message, t)
    }

    private fun priorityAsString(priority: Int): String = when (priority) {
        Log.VERBOSE -> "VERBOSE"
        Log.DEBUG -> "DEBUG"
        Log.INFO -> "INFO"
        Log.WARN -> "WARN"
        Log.ERROR -> "ERROR"
        Log.ASSERT -> "ASSERT"
        else -> priority.toString()
    }
}
