package com.example.medical.data.reports_data

import android.net.Uri

data class CreateReportData(
    val report_name:String,
    val description:String ,
    val image:Uri
)
