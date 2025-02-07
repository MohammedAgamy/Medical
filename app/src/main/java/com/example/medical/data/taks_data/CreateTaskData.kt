package com.example.medical.data.taks_data

import android.net.Uri

data class CreateTaskData(val user_id: Int, val task_name: String, val image: Uri , val description:String)