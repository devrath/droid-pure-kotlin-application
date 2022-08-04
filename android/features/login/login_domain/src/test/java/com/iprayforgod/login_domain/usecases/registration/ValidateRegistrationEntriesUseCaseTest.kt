package com.iprayforgod.login_domain.usecases.registration

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_CONFIRM_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL_FORMAT
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PASSWORD_LENGTH
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_CONFIRM_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_FIRST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_LAST_NAME
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_PASSWORD
import com.droid.core_mock.core.modules.logger.repository.FakeLoggerRepository
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateRegistrationEntriesUseCaseTest {

    @Test
    fun `test when all the inputs are valid, then it succeeds`() {
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
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = true

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when first name field is empty, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = IN_VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when last name field is empty, then it fails`() {
        // ARRANGE
        val firstNameInput = IN_VALID_FIRST_NAME
        val lastNameInput = IN_VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when email is empty, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = IN_VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when password is empty, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = IN_VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when confirm password is empty, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = IN_VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when all the inputs are valid but email is not of proper format, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = IN_VALID_EMAIL_FORMAT
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when all the inputs are valid but password length is not correct, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = IN_VALID_PASSWORD_LENGTH
        val confirmPasswordInput = VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            Truth.assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when all the inputs are valid but password and confirm password does not match, then it fails`() {
        // ARRANGE
        val firstNameInput = VALID_FIRST_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val confirmPasswordInput = IN_VALID_CONFIRM_PASSWORD

        val fakeResource = UseCaseUtilities.prepareRegistrationInput(
            firstName = firstNameInput, lastName = lastNameInput, email = emailInput,
            password = passwordInput, confirmPassword = confirmPasswordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateRegistrationEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            Truth.assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

}