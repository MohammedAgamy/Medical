package com.example.medical.data.reports_data

data class AllReports(
    val `data`: List<Data>,
    val message: String,
    val status: Int
)