package com.iprayforgod.login_domain.usecases.cases.registration

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.core_mock.modules.logger.repository.FakeLoggerRepository
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateRegistrationEntriesUseCaseTest {

    @Test
    fun `test when all the inputs are valid, then it succeeds`() {
        // ARRANGE
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = ""
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = ""
        val lastNameInput = ""
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = ""
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123@gmail.com"
        val passwordInput = ""
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = ""

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123@gmail.com"
        val passwordInput = "H"
        val confirmPasswordInput = "Hello!2345"

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
        val firstNameInput = "John"
        val lastNameInput = "McCain"
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val confirmPasswordInput = "Hello!2346"

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