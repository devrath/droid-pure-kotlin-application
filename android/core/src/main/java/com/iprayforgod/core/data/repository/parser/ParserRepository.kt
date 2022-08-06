package com.iprayforgod.core.data.repository.parser

import com.iprayforgod.core.domain.features.parser.ParserFeature
import com.iprayforgod.core.domain.models.User

class ParserRepository(
    private val parserFeature: ParserFeature
) {

    fun convertUserObjectToJson(user: User): String {
        return parserFeature.convertUserObjectToJson(user)
    }
}
