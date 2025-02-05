package com.example.medical.client

import com.example.medical.data.login_data.LogInResponse
import com.example.medical.data.login_data.LoginInput
import com.example.medical.data.profile_data.Profle
import com.example.medical.data.reports_data.AllReports
import com.example.medical.data.reports_data.CreateReport
import com.example.medical.data.reports_data.CreateReportData
import com.example.medical.data.reports_data.Data
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginInput: LoginInput): Response<LogInResponse> // Assume a 200 response means success


    @FormUrlEncoded
    @POST("show-profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
        @Field("user_id") userId: Int
    ): Response<Profle>


    @GET("reports")
    suspend fun getAllReports(
        @Header("Authorization") token: String,
    ):AllReports



    @POST("reports")
    suspend fun createReport(
        @Header("Authorization") token: String,
        @Body createReport:CreateReportData
    ):Response<CreateReportData>
}



