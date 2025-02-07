package com.example.medical.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medical.info.reportsinf.ReportState
import com.example.medical.info.reportsinf.ReportsIntent
import com.example.medical.info.tasks_info.TasksIntent
import com.example.medical.info.tasks_info.TasksState
import com.example.medical.repo.task_imp.TasksImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TasksViewModel(private val repoImpl: TasksImpl) : ViewModel() {

    private val _state = MutableStateFlow<TasksState>(TasksState.Idle)
    val state: StateFlow<TasksState> get() = _state


    fun handleIntent(intent: TasksIntent) {
        when (intent) {
            is TasksIntent.LoadTasks -> {
                fetchReports()
                Log.d("fetchTasks", "Fetched Tasks: ${_state.value}") // Log the full response

            }
        }
    }

    private fun fetchReports() {
        val token =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWM3Y2Y1OTYxZDM2YzQyNmE0MjhkYmExMjdhYTc1ZTQwZTA2MjRiNmMzZDBhYTkzY2Y5NDEyY2I2Yjc0YTY4ZWIwNDNhNWU0ZDYyMTc0NjkiLCJpYXQiOjE3MzgwNTA3MDEuODM1NjMzLCJuYmYiOjE3MzgwNTA3MDEuODM1NjM1LCJleHAiOjE3Njk1ODY3MDEuODM0MzcyLCJzdWIiOiI2MjciLCJzY29wZXMiOltdfQ.refzVelhSILjyfYEKS-LMF8D9rMbyl-WcJf_-SyZ51TL8o6pvrMQoML61IYvQXOPepzhDS7JzvR8MC17c0qoqYbrc5WmlFJlsvuZlukDBfLgtZcNH8OXN1Gu9aW0wPeAEZ-F54ltvXiPyvg4_sYgqWDoCMY4Bybp8AkPFw2ZvBAL8IjIWorrznYINBj8bcwSz6imyw--HmYUGRf3BCv1gqk0JuKHckab0iesjW_Gq8_zGcRFu3r05E-4_VXCzM5CJh_pd0yd8GvJgpO1a_jHAwUB-ZxALqfMDKbGqnOGPfCTLc4TnWYOBPGX_YS9ZRXmfUameugy0Dq7I38lL8w9PccRKTbqm60GwSGpn6CEX9qD2EM9pXLhriuJFw735MmFGw7hgcZqWL3eanUqKh20JK4nEc898mwNQ1F_1o2ePNinL7SWfL6-0exezTngFL-QD-39S-HnQHwSB0-41QmgIeVoj2Qw57r7kCY4qruNNvBSyatj54BehmaGn3c_gOMOEwPrcINfpco-1na6AWwNd8javs-sfYZ8DCPuFKIKOu4hs20SmtGLxMUTwP9JCZ1OPicWMjNer6EccWy1pxcKwYMUu9ICQU8FPuyyKpFot6TwG6IZqv0-rlH2SmCuiRQ8EHZ6LEXU-XpFzKuuyYTe9R3UoWk0Oe9QfBmHiUuVLsI"
        viewModelScope.launch {
            _state.value = TasksState.Loading
            Log.d("TasksViewModel out", "State set to Loading")
            Log.d("TasksViewModel out ", "State set to Loading ${_state.value}")
            try {
                val reports = repoImpl.getAllTasks(token) // Returns List<Data>
                Log.d(
                    "TasksViewModel",
                    "Fetched reports: ${reports.size} items"
                ) // Log the size of the response
                Log.d("TasksViewModel", "Fetched reports: $reports") // Log the full response
                _state.value = TasksState.Success(reports) // Pass the List<Data> to ReportState.Su
                Log.d("TasksViewModel", "Fetched reports: ${_state.value}") // Log the full response

            } catch (e: Exception) {
                _state.value = TasksState.Error(e.message ?: "Unknown Error")
                Log.e("TasksViewModel", "Error fetching tasks", e)
            }
        }
    }

}