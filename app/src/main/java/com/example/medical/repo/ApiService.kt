package com.example.medical.repo

import com.example.medical.data.login_data.LogInResponse
import com.example.medical.data.login_data.LoginInput
import com.example.medical.data.profile_data.Profle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginInput: LoginInput): Response<LogInResponse> // Assume a 200 response means success


    @FormUrlEncoded
    @POST("show-profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
        @Field("user_id") userId: Int
    ): Response<Profle>
}



