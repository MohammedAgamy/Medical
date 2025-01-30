package com.example.medical.info.logininf

import com.example.medical.data.login_data.UserData

sealed class LoginIntent {
    data class SubmitLogin(val loginInput: UserData) : LoginIntent()

}