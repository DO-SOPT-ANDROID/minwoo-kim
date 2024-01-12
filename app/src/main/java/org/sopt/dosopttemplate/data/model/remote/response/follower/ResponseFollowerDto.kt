package org.sopt.dosopttemplate.data.model.remote.response.follower

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.Follower

@Serializable
data class ResponseFollowerDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val followerData: List<FollowerData>,
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
        val firstName: String,
        @SerialName("last_name")
        val lastName: String
    )

    @Serializable
    data class Support(
        @SerialName("text")
        val text: String,
        @SerialName("url")
        val url: String
    )

    fun toFollowerData(): List<Follower> = followerData.map {
        Follower(
            id = it.id,
            avatar = it.avatar,
            email = it.email,
            firstName = it.firstName,
            lastName = it.firstName,
        )
    }
}