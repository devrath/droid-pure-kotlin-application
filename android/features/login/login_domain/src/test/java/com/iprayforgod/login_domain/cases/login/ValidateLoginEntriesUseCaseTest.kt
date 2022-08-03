package com.iprayforgod.login_domain.cases.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.core_mock.modules.logger.repository.FakeLoggerRepository
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateLoginEntriesUseCaseTest {

    @Test
    fun `test when user enters valid email and password as input, then it succeeds`() {
        // ARRANGE
        val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = true

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when user enters empty email and correct password as input, then it fails`() {
        // ARRANGE
        val emailInput = ""
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when user enters valid email and empty password as input, then it fails`() {
        // ARRANGE
        val emailInput = "John.123@gmail.com"
        val passwordInput = ""
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters invalid email format and valid password format as input, then it fails`() {
        // ARRANGE
        val emailInput = "John.123gmail.com"
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }


    @Test
    fun `test when user enters valid email and invalid password format as input, then it fails`() {
        // ARRANGE
        val emailInput = "John.123@gmail.com"
        val passwordInput = "test"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

}