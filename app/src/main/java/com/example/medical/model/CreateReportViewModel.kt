package com.example.medical.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medical.data.reports_data.CreateReportData
import com.example.medical.info.createrepoInfo.CreateState
import com.example.medical.info.logininf.LoginState
import com.example.medical.repo.repo_impl.CreateRepoImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateReportViewModel(val createRepoImp: CreateRepoImp) : ViewModel() {

    private val _state = MutableStateFlow<CreateState>(CreateState.Idle)
    val state: StateFlow<CreateState> get() = _state


    fun createReport(createRepo:String , description :String) {

        val token =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWM3Y2Y1OTYxZDM2YzQyNmE0MjhkYmExMjdhYTc1ZTQwZTA2MjRiNmMzZDBhYTkzY2Y5NDEyY2I2Yjc0YTY4ZWIwNDNhNWU0ZDYyMTc0NjkiLCJpYXQiOjE3MzgwNTA3MDEuODM1NjMzLCJuYmYiOjE3MzgwNTA3MDEuODM1NjM1LCJleHAiOjE3Njk1ODY3MDEuODM0MzcyLCJzdWIiOiI2MjciLCJzY29wZXMiOltdfQ.refzVelhSILjyfYEKS-LMF8D9rMbyl-WcJf_-SyZ51TL8o6pvrMQoML61IYvQXOPepzhDS7JzvR8MC17c0qoqYbrc5WmlFJlsvuZlukDBfLgtZcNH8OXN1Gu9aW0wPeAEZ-F54ltvXiPyvg4_sYgqWDoCMY4Bybp8AkPFw2ZvBAL8IjIWorrznYINBj8bcwSz6imyw--HmYUGRf3BCv1gqk0JuKHckab0iesjW_Gq8_zGcRFu3r05E-4_VXCzM5CJh_pd0yd8GvJgpO1a_jHAwUB-ZxALqfMDKbGqnOGPfCTLc4TnWYOBPGX_YS9ZRXmfUameugy0Dq7I38lL8w9PccRKTbqm60GwSGpn6CEX9qD2EM9pXLhriuJFw735MmFGw7hgcZqWL3eanUqKh20JK4nEc898mwNQ1F_1o2ePNinL7SWfL6-0exezTngFL-QD-39S-HnQHwSB0-41QmgIeVoj2Qw57r7kCY4qruNNvBSyatj54BehmaGn3c_gOMOEwPrcINfpco-1na6AWwNd8javs-sfYZ8DCPuFKIKOu4hs20SmtGLxMUTwP9JCZ1OPicWMjNer6EccWy1pxcKwYMUu9ICQU8FPuyyKpFot6TwG6IZqv0-rlH2SmCuiRQ8EHZ6LEXU-XpFzKuuyYTe9R3UoWk0Oe9QfBmHiUuVLsI"

        viewModelScope.launch {
            _state.value = CreateState.Loading
            try {
                val response =
                    createRepoImp.createReportImp(token, CreateReportData(createRepo, description))
                if (response.isSuccessful && response.body() != null) {
                    val createResponse = response.body()
                    _state.value = CreateState.Success("Welcome, ${createResponse!!.report_name}!")
                    Log.d("TAG", _state.value.toString())

                } else {
                    _state.value = CreateState.Error("Login failed: ${response.message()}")
                    Log.d("TAG", _state.value.toString())
                }
            } catch (e: Exception) {
                _state.value = CreateState.Error("An error occurred: ${e.message}")
                Log.d("TAG", _state.value.toString())
            }
        }
    }


}