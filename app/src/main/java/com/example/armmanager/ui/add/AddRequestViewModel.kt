package com.example.armmanager.ui.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armmanager.repository.RequestRepository
import com.example.armmanager.vo.Request
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddRequestViewModel @Inject constructor(private val requestRepository: RequestRepository): ViewModel() {

    suspend fun insertRequest(request: Request) {
        requestRepository.insertRequest(request)
    }

    suspend fun updateRequest(request: Request) {
        requestRepository.updateRequest(request)
    }

}