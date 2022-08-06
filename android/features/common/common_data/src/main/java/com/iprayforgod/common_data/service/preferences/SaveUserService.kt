package com.iprayforgod.common_data.service.preferences

import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.data.implementation.logger.utilities.KeysFeatureNames
import com.iprayforgod.core.data.repository.logger.LoggerRepository
import com.iprayforgod.core.data.repository.parser.ParserRepository
import com.iprayforgod.core.data.repository.preference.PreferenceRepository
import com.iprayforgod.core.domain.features.logger.LoggerFeature
import com.iprayforgod.core.domain.features.parser.ParserFeature
import com.iprayforgod.core.domain.features.preferences.PreferenceDatastore
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveUserService @Inject constructor(
    private val parserRepo: ParserFeature,
    private val prefRepo: PreferenceDatastore,
    private var log: LoggerFeature,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {

    fun saveUser(input: User): Flow<State<Unit>> {
        val resultDeferred = CompletableDeferred<State<Unit>>()
        try {
            CoroutineScope(dispatcher).launch {
                val userStr: String = convertUserObjectToString(resultDeferred, input)
                val savedResultState = prefRepo.saveCurrentUser(text = userStr)
                resultDeferred.complete(State.success(Unit))
            }
        } catch (ex: Exception) {
            resultDeferred.completeExceptionally(ex)
        }

        return flow {
            try {
                // emit(State.loading())
                emit(resultDeferred.await())
            } catch (e: Exception) {
                log.e(KeysFeatureNames.FEATURE_LOGIN, e.stackTrace.toString())
                resultDeferred.completeExceptionally(e)
            }
        }
    }

    private fun convertUserObjectToString(resultDeferred: CompletableDeferred<State<Unit>>, input: User): String {
        return parserRepo.convertUserObjectToJson(input)
    }
}
