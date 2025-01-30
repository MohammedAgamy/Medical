package com.example.medical.data.profile_data


data class Data(
    val access_token: String,
    val address: String,
    val birthday: String,
    val created_at: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val id: Int,
    val last_name: String,
    val mobile: String,
    val specialist: String,
    val status: String,
    val token_type: String,
    val type: String,
    val verified: Boolean
)

/*

data class ProfileResponse(
    val first_name: String,
    val last_name: String,
    val gender: String,
    val status: String,
    val birthday: String,
    val address: String,
    val maritalStatus: String,
    val email: String,
    val mobile: String,
    val specialist: String,
    val profileImage: String?
)*/
