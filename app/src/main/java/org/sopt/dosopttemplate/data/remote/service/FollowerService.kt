package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.ResponseFollowerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    suspend fun getFollowerList(
        @Query("page") page: Int
    ): ResponseFollowerDto
}