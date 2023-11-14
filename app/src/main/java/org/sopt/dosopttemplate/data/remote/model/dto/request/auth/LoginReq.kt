package org.sopt.dosopttemplate.data.remote.model.dto.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginReq(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)