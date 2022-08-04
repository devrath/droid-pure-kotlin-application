package com.iprayforgod.login_domain.usecases.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.features.login.login_domain_mock.usecases.repository.FakeLoginRepository
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUserUseCaseTest {



    @Test
    fun `test when user enters valid email and password as input, then it succeeds`() {
        // ARRANGE
        /*val emailInput = "John.123@gmail.com"
        val passwordInput = "Hello!2345"
        val fakeResource = UseCaseUtilities.prepareLoginInput(
            email = emailInput, password = passwordInput
        )
        val fakeRepository = FakeLoggerRepository()
        val expectedOutput = true

        // ACT
        val useCase = LoginUserUseCase(fakeRepository)
        val result : Boolean = useCase.invoke(fakeResource).isSuccess

        // ASSERT
        useCase.invoke(fakeResource).onSuccess {
            Truth.assertThat(it.successful).isEqualTo(expectedOutput)
        }*/

        // ARRANGE
        val fakeRepository = FakeLoginRepository()

        // ACT
        val useCase = LoginUserUseCase(fakeRepository)
        //val result : Boolean = useCase.invoke(fakeResource).isSuccess


    }


}