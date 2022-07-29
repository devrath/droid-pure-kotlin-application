package com.iprayforgod.core.modules.logger.implementation

import com.iprayforgod.core.domain.features.logger.LoggerFeature
import timber.log.Timber

class LoggerFeatureImpl() : LoggerFeature {
    override fun d(featureName: String?, msg: String?) {
        if (featureName != null && msg != null){ Timber.tag(featureName).d(msg) }
    }
    override fun e(featureName: String?, msg: String?) {
        if (featureName != null && msg != null){ Timber.tag(featureName).e(msg) }
    }
    override fun w(featureName: String?, msg: String?) {
        if (featureName != null && msg != null){ Timber.tag(featureName).w(msg) }
    }
    override fun v(featureName: String?, msg: String?) {
        if (featureName != null && msg != null){ Timber.tag(featureName).v(msg) }
    }
    override fun i(featureName: String?, msg: String?) {
        if (featureName != null && msg != null){ Timber.tag(featureName).i(msg) }
    }
}
