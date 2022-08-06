package com.iprayforgod.login_domain.service

import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.entities.inputs.LoginInput
import kotlinx.coroutines.flow.Flow

interface LoginService {
    fun loginUser(input: LoginInput): Flow<State<User>>
}