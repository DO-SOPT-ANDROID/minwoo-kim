package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.remote.model.request.auth.RequestLoginDto
import org.sopt.dosopttemplate.data.model.remote.model.request.auth.RequestSignupDto
import org.sopt.dosopttemplate.data.model.remote.model.response.auth.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun postSignup(
        @Body request: RequestSignupDto
    )

    @POST("api/v1/members/sign-in")
    suspend fun postLogin(
        @Body request: RequestLoginDto,
    ): ResponseLoginDto
}