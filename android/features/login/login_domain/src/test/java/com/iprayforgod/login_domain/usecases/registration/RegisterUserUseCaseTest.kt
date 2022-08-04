package com.iprayforgod.login_domain.usecases.registration

import com.droid.core_mock.core.domain.models.UserMocks
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_CONFIRM_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_CONFIRM_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_PASSWORD
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeFailureMsgCheckLoginRepository
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeSuccessLoginRepository
import com.google.common.truth.Truth
import com.iprayforgod.core.platform.functional.State
import com.iprayforgod.login_domain.usecases.login.LoginUserUseCase
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RegisterUserUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters valid credentials as input, then it succeeds`() = runTest {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD
        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeSuccessLoginRepository() // we use a fake repository that returns success

        // ACT
        val useCase = RegisterUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        Truth.assertThat(result).isEqualTo(State.success(UserMocks.validUser()))
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository when user enters invalid email and password as input, then it fails`() = runTest {
        // ARRANGE
        val firstNameInput = IN_VALID_FIRST_NAME
        val lastNameInput = IN_VALID_LAST_NAME
        val emailInput = IN_VALID_EMAIL
        val passwordInput = IN_VALID_PASSWORD
        val confirmPasswordInput = IN_VALID_CONFIRM_PASSWORD
        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeFailureLoginRepository() // we use a fake repository that returns failure

        // ACT
        val useCase = RegisterUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        Truth.assertThat(result).isNotEqualTo(State.success(UserMocks.validUser()))
    }




    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository for registration failure message`() = runTest {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD
        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeFailureMsgCheckLoginRepository() // we use a fake repository that returns fake message

        // ACT
        val useCase = RegisterUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        Truth.assertThat(result)
            .isEqualTo(State.Failed<String>(message = FakeFailureMsgCheckLoginRepository.FAILURE_MESSAGE_FOR_REGISTRATION))
    }

}