package com.example.medical.info.profileinfo

import com.example.medical.data.profile_data.Data
import com.example.medical.data.profile_data.Profle

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(val profile: Profle) : ProfileState()
    data class Error(val message: String) : ProfileState()
}