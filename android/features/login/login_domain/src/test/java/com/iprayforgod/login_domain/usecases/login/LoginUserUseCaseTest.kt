package com.iprayforgod.login_domain.usecases.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureMsgCheckLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureMsgCheckLoginRepository.Companion.FAILURE_MESSAGE_FOR_LOGIN
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeSuccessLoginRepository
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUserUseCaseTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters valid email and password as input, then it succeeds`() = runTest {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeSuccessLoginRepository() // we use a fake repository that returns success

        // ACT
        val useCase = LoginUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.success(UserMocks.validUser()))
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters invalid email and password as input, then it fails`() = runTest {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeFailureLoginRepository() // we use a fake repository that returns failure

        // ACT
        val useCase = LoginUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isNotEqualTo(State.success(UserMocks.validUser()))
    }




    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository for login failure message`() = runTest {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeFailureMsgCheckLoginRepository() // we use a fake repository that returns fake message

        // ACT
        val useCase = LoginUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.Failed<String>(message = FAILURE_MESSAGE_FOR_LOGIN))
    }


}