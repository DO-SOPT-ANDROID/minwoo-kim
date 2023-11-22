package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.LoginReq
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.SignupReq
import org.sopt.dosopttemplate.data.remote.model.dto.response.auth.LoginRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun postSignup(
        @Body request: SignupReq,
    ): Call<Unit>

    @POST("api/v1/members/sign-in")
    fun postLogin(
        @Body request: LoginReq,
    ): Call<LoginRes>
}