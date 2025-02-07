package com.example.medical.repo.task_imp

import android.util.Log
import com.example.medical.client.RetrofitInstance.apiService
import com.example.medical.data.taks_data.Data

class TasksImpl {

    suspend fun getAllTasks(token: String): List<Data> {
        return try {
            val response = apiService.getAllTasks(token) // Returns AllReports
            Log.d("AllTasksImp", "API Response: ${response.status}") // Log the status
            Log.d("AllTasksImp", "API Data: ${response.data}") // Log the data
            response.data

        } catch (e: Exception) {
            Log.e("AllTasksImp", "Error fetching Tasks", e)
            emptyList() // Return empty list if there's an error
        }
    }
}