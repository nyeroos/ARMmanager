package com.example.armmanager.ui.request

import android.util.Log
import androidx.lifecycle.*
import com.example.armmanager.repository.RequestRepository
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestViewModel @Inject constructor(private val requestRepository: RequestRepository): ViewModel() {

    val requests: LiveData<Resource<List<Request>>> = requestRepository.getRequests("")

    val completerequests: LiveData<Resource<List<Request>>> = requestRepository.getCompleteRequest("")

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    /*fun insert(request: Request) = viewModelScope.launch {
        repository.insert(request)
    }*/

//    fun log() = viewModelScope.launch { requestRepository.insertRequest() }
    fun log2() = viewModelScope.launch { requestRepository.deleteAll() }
    fun log1() = viewModelScope.launch {
        var a = requestRepository.getRequestCount()
        Log.d("gg","$a")
    }
}
