package com.droid.login_domain.usecases.cases.forgotPassword

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.droid.login_domain.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class ValidateForgotPwdUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


}