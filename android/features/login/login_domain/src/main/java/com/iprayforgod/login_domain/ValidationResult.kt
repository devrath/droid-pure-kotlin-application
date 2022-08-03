package com.iprayforgod.login_domain

import com.iprayforgod.core.platform.ui.uiEvent.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText.StringResource? = null
)
