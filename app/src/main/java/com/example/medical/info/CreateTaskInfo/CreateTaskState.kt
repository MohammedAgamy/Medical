package com.example.medical.info.CreateTaskInfo

sealed class CreateTaskState {
    data object Idle : CreateTaskState()
    data object Loading : CreateTaskState()
    data class Success(val message: String) : CreateTaskState()
    data class Error(val error: String) : CreateTaskState()
}