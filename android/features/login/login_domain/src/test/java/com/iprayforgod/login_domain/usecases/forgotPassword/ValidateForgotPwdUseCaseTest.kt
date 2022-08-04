package com.iprayforgod.login_domain.usecases.forgotPassword

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.InvalidCredentials.IN_VALID_EMAIL_FORMAT
import com.droid.core_mock.core.domain.models.UserMocks.MockCredentials.ValidCredentials.VALID_EMAIL
import com.droid.core_mock.core.modules.logger.repository.FakeLoggerRepository
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.login_domain.utils.UseCaseUtilities
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateForgotPwdUseCaseTest {

    @Test
    fun `test when user enters valid email input, then it succeeds`() {
        // ARRANGE
        val emailInput = VALID_EMAIL
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(emailInput)
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = true

        // ACT
        val useCase = ValidateForgotPwdUseCase(fakeRepository)
        val result: Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters empty value for email input, it fails`() {
        // ARRANGE
        val emailInput = IN_VALID_EMAIL
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(emailInput)
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateForgotPwdUseCase(fakeRepository)

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }

    @Test
    fun `test when user enters email as input having improper email structure, then it fails`() {
        // ARRANGE
        val emailInput = IN_VALID_EMAIL_FORMAT
        val fakeResource = UseCaseUtilities.prepareForgotPasswordInput(emailInput)
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = false

        // ACT
        val useCase = ValidateForgotPwdUseCase(fakeRepository)

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            assertThat(it.successful).isEqualTo(expectedOutput)
        }
    }
}
