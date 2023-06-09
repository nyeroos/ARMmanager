package com.example.armmanager.ui.request

import android.util.Log
import androidx.lifecycle.*
import com.example.armmanager.api.ApiResponse
import com.example.armmanager.repository.RequestRepository
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestViewModel @Inject constructor(private val requestRepository: RequestRepository): ViewModel() {

    private val _requestLiveData = MutableLiveData<Resource<List<Request>>>()

    val requests: LiveData<Resource<List<Request>>> = _requestLiveData.switchMap {
        requestRepository.getRequests()
    }

    val completerequests: LiveData<Resource<List<Request>>> = requestRepository.getCompleteRequest()

    fun deleteRequest(request: Request) {
        requestRepository.deleteRequest(request)
    }
    fun updateRequest(request: Request): LiveData<Resource<Request>> {
        return requestRepository.updateRequest(request)
    }
    fun createRequest(requestNumber: Int, requestName: String, customer: String,expectedDate: String,
                      creationDate: String, actualDate: String, user: Int, status: String): LiveData<Resource<Request>> {
        return requestRepository.createRequest(requestNumber, requestName, customer,
            expectedDate, creationDate, actualDate, user, status)
    }

    fun refresh() {
        _requestLiveData.value.let {
            _requestLiveData.value = it
        }
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    /*fun insert(request: Request) = viewModelScope.launch {
        repository.insert(request)
    }*/

  //  fun log() = viewModelScope.launch { requestRepository.insertRequest() }
    //fun log2() = viewModelScope.launch { requestRepository.deleteAll() }
    //fun log1() = viewModelScope.launch {
//        var a = requestRepository.getRequestCount()
//        Log.d("gg","$a")
//    }
}
