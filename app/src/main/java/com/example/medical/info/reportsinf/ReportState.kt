package com.example.medical.info.reportsinf

import com.example.medical.data.reports_data.AllReports
import com.example.medical.data.reports_data.Data

sealed class ReportState {
    object Idle : ReportState()
    object Loading : ReportState()
    data class Success(val reports: List<Data>) : ReportState()
    data class Error(val message: String) : ReportState()
}