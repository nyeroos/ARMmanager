package com.example.armmanager.ui.request

import android.util.Log
import androidx.lifecycle.*
import com.example.armmanager.repository.RequestRepository
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestViewModel @Inject constructor(private val requestRepository: RequestRepository): ViewModel() {
    //private val repository: RequestRepository
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.



    val requests: LiveData<Resource<List<Request>>> = requestRepository.getRequests("")



    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    /*fun insert(request: Request) = viewModelScope.launch {
        repository.insert(request)
    }*/

    fun log()=viewModelScope.launch { requestRepository.insertRequest() }
}
