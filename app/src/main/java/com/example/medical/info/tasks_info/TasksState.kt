package com.example.medical.info.tasks_info

import com.example.medical.data.taks_data.Data


sealed class TasksState {

    object Idle : TasksState()
    object Loading : TasksState()
    data class Success(val reports: List<Data>) : TasksState()
    data class Error(val message: String) : TasksState()
}