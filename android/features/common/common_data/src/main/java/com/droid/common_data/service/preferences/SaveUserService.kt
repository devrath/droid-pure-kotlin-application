package com.droid.common_data.service.preferences

import com.iprayforgod.core.di.qualifiers.IoDispatcher
import com.iprayforgod.core.domain.models.User
import com.iprayforgod.core.modules.keys.KeysFeatureNames
import com.iprayforgod.core.modules.logger.repository.LoggerRepository
import com.iprayforgod.core.modules.parser.repository.ParserRepository
import com.iprayforgod.core.modules.preference.repository.PreferenceRepository
import com.iprayforgod.core.platform.functional.State
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveUserService @Inject constructor(
    private val parserRepo: ParserRepository,
    private val prefRepo: PreferenceRepository,
    private var  log: LoggerRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {

    fun saveUser(input: User): Flow<State<Unit>> {
        val resultDeferred = CompletableDeferred<State<Unit>>()
        try {
            CoroutineScope(dispatcher).launch {
                val userStr : String = convertUserObjectToString(resultDeferred,input)
                val savedResultState = prefRepo.saveUserState(user = userStr)
                resultDeferred.complete(State.success(Unit))
            }
        } catch (ex : Exception) {
            resultDeferred.completeExceptionally(ex)
        }

        return flow {
            try {
                //emit(State.loading())
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