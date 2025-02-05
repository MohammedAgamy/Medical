package com.example.medical.repo.repo_impl

import com.example.medical.client.RetrofitInstance
import com.example.medical.data.login_data.LogInResponse
import com.example.medical.data.login_data.LoginInput
import com.example.medical.data.reports_data.CreateReport
import com.example.medical.data.reports_data.CreateReportData
import retrofit2.Response

class CreateRepoImp {
    suspend fun createReportImp( token :String,createRepo: CreateReportData): Response<CreateReportData> {
        return RetrofitInstance.apiService.createReport(token,createRepo)
    }
}