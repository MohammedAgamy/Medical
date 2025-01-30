package com.example.medical.info.profileinfo

sealed class ProfileIntent() {
    data class FetchProfile(val userId: Int) : ProfileIntent()
}
