package com.example.medical.repo

import com.example.medical.data.reports_data.AllReports
import com.example.medical.data.reports_data.Data
import retrofit2.Response

interface AllReportRepository {
    suspend fun getAllReport(token:String):List<Data>
}