package com.iprayforgod.common_domain.usecases.module

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.core_mock.core.domain.models.UserMocks.validUser
import com.droid.core_mock.features.common.common_domain.repository.FakeFailureMsgCheckCommonRepository
import com.droid.core_mock.features.common.common_domain.repository.FakeFailureMsgCheckCommonRepository.Companion.FAILURE_MSG_SAVE_USER
import com.droid.core_mock.features.common.common_domain.repository.FakeSuccessCommonRepository
import com.google.common.truth.Truth.assertThat
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SaveUserUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository with valid user credential as input, then it succeeds`() = runTest {
        // ARRANGE
        val fakeResource = validUser()
        val fakeRepository = FakeSuccessCommonRepository() // we use a fake repository that returns success
        val expectedResult = Unit

        // ACT
        val useCase = SaveUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.success(expectedResult))
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test repository for save user failure message`() = runTest {
        // ARRANGE
        val fakeResource = validUser()
        val fakeRepository = FakeFailureMsgCheckCommonRepository() // we use a fake repository that returns success

        // ACT
        val useCase = SaveUserUseCase(fakeRepository)
        val result  = useCase.invoke(fakeResource).first()

        // ASSERT
        assertThat(result).isEqualTo(State.Failed<String>(message = FAILURE_MSG_SAVE_USER))
    }
}