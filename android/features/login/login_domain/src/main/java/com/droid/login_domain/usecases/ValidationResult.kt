package com.droid.login_domain.usecases

import com.iprayforgod.core.ui.uiEvent.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText.StringResource? = null
)
