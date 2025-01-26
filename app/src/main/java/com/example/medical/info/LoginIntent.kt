package com.example.medical.info

import com.example.medical.data.UserData

sealed class LoginIntent {
    data class SubmitLogin(val loginInput: UserData) : LoginIntent()

}