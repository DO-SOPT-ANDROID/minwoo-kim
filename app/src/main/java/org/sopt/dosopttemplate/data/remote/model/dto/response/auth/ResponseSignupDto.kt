package org.sopt.dosopttemplate.data.remote.model.dto.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignupDto(
    @SerialName("message")
    val msg: String,
)