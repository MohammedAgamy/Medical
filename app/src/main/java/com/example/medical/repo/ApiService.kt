package com.example.medical.repo

import com.example.medical.data.LogInResponse
import com.example.medical.data.LoginInput
import com.example.medical.data.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body loginInput: LoginInput): Response<LogInResponse> // Assume a 200 response means success
}