package org.sopt.dosopttemplate.data.remote.model.dto.response.follower

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowerRes(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<FollowerData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class FollowerData(
        @SerialName("id")
        val id: Int,
        @SerialName("avatar")
        val avatar: String,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String
    )

    @Serializable
    data class Support(
        @SerialName("text")
        val text: String,
        @SerialName("url")
        val url: String
    )
}