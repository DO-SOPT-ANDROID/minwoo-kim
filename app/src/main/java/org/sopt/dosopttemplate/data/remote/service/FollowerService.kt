package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.FollowerRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    fun getFollowerList(
        @Query("page") page: Int
    ): Call<FollowerRes>
}