package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.RequestLoginDto
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.RequestSignupDto
import org.sopt.dosopttemplate.data.remote.model.dto.response.auth.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun postSignup(
        @Body request: RequestSignupDto
    )

    @POST("api/v1/members/sign-in")
    fun postLogin(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}