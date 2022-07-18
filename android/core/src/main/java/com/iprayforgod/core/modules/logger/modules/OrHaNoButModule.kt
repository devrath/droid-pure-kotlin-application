package com.iprayforgod.core.modules.logger.modules

import android.content.Context
import com.iprayforgod.core.modules.logger.adapters.AndroidDiskLogAdapter
import com.iprayforgod.core.modules.logger.adapters.AndroidLogCatAdapter
import com.orhanobut.logger.Logger

class OrHaNoButModule {

    fun initialize(context : Context){
        // Log to be displayed in LogCat
        Logger.addLogAdapter(AndroidLogCatAdapter().initiate())
        // Log to be saved in the disk
        Logger.addLogAdapter(AndroidDiskLogAdapter().initiate(context))
    }

}