package com.iprayforgod.login_domain.usecases.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL_FORMAT
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PASSWORD
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_PASSWORD_LENGTH
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_PASSWORD
import com.droid.core_mock.core.modules.logger.repository.FakeLoggerRepository
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateLoginEntriesUseCaseTest {

    @Test
    fun `test when user enters valid email and password as input, then it succeeds`() {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = true

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters empty email and correct password as input, then it fails`() {
        // ARRANGE
        val emailInput = IN_VALID_EMAIL
        val passwordInput = VALID_PASSWORD
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters valid email and empty password as input, then it fails`() {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = IN_VALID_PASSWORD
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters invalid email format and valid password format as input, then it fails`() {
        // ARRANGE
        val emailInput = IN_VALID_EMAIL_FORMAT
        val passwordInput = VALID_PASSWORD
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters valid email and invalid password format as input, then it fails`() {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val passwordInput = IN_VALID_PASSWORD_LENGTH
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateLoginEntriesUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }
}
