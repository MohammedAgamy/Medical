package com.example.medical.info.CreateTaskInfo

import com.example.medical.data.taks_data.CreateTaskData

sealed class CreateTaskIntent {
    data class SubmitTask(val createTask: CreateTaskData) : CreateTaskIntent()
}