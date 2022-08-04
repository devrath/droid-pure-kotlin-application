package com.iprayforgod.login_domain.usecases.forgotPassword

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureMsgCheckLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeSuccessLoginRepository
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForgotPwdUseCaseTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters valid credential as input, then it succeeds`() = runTest {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(email = emailInput)
        val fakeRepository = FakeSuccessLoginRepository() // we use a fake repository that returns success
        val expectedResult = true

        // ACT
        val useCase = ForgotPwdUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.success(expectedResult))
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters invalid email as input, then it fails`() = runTest {
        // ARRANGE
        val emailInput = IN_VALID_EMAIL
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(email = emailInput)
        val fakeRepository = FakeFailureLoginRepository() // we use a fake repository that returns failure
        val expectedResult = false

        // ACT
        val useCase = ForgotPwdUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.success(expectedResult))
    }




    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository for forgot password failure message`() = runTest {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(email = emailInput)
        val fakeRepository = FakeFailureMsgCheckLoginRepository() // we use a fake repository that returns fake message

        // ACT
        val useCase = ForgotPwdUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result)
            .isEqualTo(State.Failed<String>(message = FakeFailureMsgCheckLoginRepository.FAILURE_MESSAGE_FOR_FORGOT_PWD))
    }


}