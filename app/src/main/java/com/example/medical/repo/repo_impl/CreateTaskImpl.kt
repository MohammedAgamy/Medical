package com.example.medical.repo.repo_impl

import com.example.medical.client.RetrofitInstance
import com.example.medical.data.reports_data.CreateReportData
import com.example.medical.data.taks_data.CreateTaskData
import retrofit2.Response

class CreateTaskImpl {
    suspend fun createTaskImp(
        token: String,
        createTaskData: CreateTaskData
    ): Response<CreateTaskData> {
        return RetrofitInstance.apiService.createTask(token, createTaskData)

    }
}