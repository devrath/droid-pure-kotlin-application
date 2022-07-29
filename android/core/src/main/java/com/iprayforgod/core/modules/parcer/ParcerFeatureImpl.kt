package com.iprayforgod.core.modules.parcer

import com.iprayforgod.core.domain.features.parcer.ParcerFeature
import com.iprayforgod.core.domain.models.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


class ParcerFeatureImpl(
    private val moshi: Moshi
) : ParcerFeature {

    override fun convertUserObjectToJson(user: User): String {
        val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
        jsonAdapter.toJson(user)?.let { return it }?: run { return "" }
    }

}