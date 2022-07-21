package com.droid.home_presentation.vm

import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.platform.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private var  log: LoggerRepository,
) : BaseViewModel()  {

}