package com.example.medical.repo.repo_impl

import android.util.Log
import com.example.medical.client.RetrofitInstance.apiService
import com.example.medical.data.reports_data.Data


class AllReportImp() {

    suspend fun getAllReport(token: String): List<Data> {
        return try {
            val response = apiService.getAllReports(token) // Returns AllReports
            Log.d("AllReportImp", "API Response: ${response.status}") // Log the status
            Log.d("AllReportImp", "API Data: ${response.data}") // Log the data
            response.data // Extract and return the List<Data>
        } catch (e: Exception) {
            Log.e("AllReportImp", "Error fetching reports", e)
            emptyList() // Return empty list if there's an error
        }
    }

}