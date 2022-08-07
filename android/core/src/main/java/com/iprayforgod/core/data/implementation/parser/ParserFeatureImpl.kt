package com.iprayforgod.core.data.implementation.parser

import com.iprayforgod.core.domain.features.parser.ParserFeature
import com.iprayforgod.core.domain.models.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class ParserFeatureImpl(
    private val moshi: Moshi
) : ParserFeature {

    override fun convertUserObjectToJson(user: User): String {
        val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
        jsonAdapter.toJson(user)?.let { return it } ?: run { return "" }
    }
}
