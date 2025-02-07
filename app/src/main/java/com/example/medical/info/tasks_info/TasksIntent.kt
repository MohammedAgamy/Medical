package com.example.medical.info.tasks_info


sealed class TasksIntent {
    //data object LoadReports : ReportsIntent()
    object LoadTasks : TasksIntent()
}