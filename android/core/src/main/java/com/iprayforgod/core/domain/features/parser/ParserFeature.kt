package com.iprayforgod.core.domain.features.parser

import com.iprayforgod.core.domain.models.User

interface ParserFeature {
    fun convertUserObjectToJson(user: User): String
}
