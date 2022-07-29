package com.iprayforgod.core.domain.features.parcer

import com.iprayforgod.core.domain.models.User

interface ParcerFeature {
    fun convertUserObjectToJson(user: User) : String
}
