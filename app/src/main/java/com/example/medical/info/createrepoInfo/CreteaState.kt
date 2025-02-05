package com.example.medical.info.createrepoInfo


sealed class CreateState {
    data object Idle : CreateState()
    data object Loading : CreateState()
    data class Success(val message: String) : CreateState()
    data class Error(val error: String) : CreateState()
}