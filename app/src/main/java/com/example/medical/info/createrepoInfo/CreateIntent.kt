package com.example.medical.info.createrepoInfo

import com.example.medical.data.login_data.UserData
import com.example.medical.data.reports_data.CreateReportData
import com.example.medical.info.logininf.LoginIntent

sealed class CreateIntent {
    data class SubmitLogin(val loginInput: CreateReportData) : CreateIntent()
}