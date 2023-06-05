package com.example.armmanager.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.armmanager.AppExecutors
import com.example.armmanager.database.ArmRoomDatabase
import com.example.armmanager.database.RequestDAO
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
import android.content.Context
import com.example.armmanager.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val requestDAO: RequestDAO,
    private val armService: ArmService
) {

    fun getRequests(): LiveData<Resource<List<Request>>> {
        return object : NetworkBoundResource<List<Request>, List<Request>>(appExecutors) {
            override fun saveCallResult(items: List<Request>) {
                requestDAO.deleteAll()
                requestDAO.insertAll(items)
            }

            override fun shouldFetch(data: List<Request>?) = true

            override fun loadFromDb() = requestDAO.getRequests()

            override fun createCall() = armService.getAllRequest()
        }.asLiveData()
    }

    fun getCompleteRequest(): LiveData<Resource<List<Request>>> {
        return object : NetworkBoundResource<List<Request>, List<Request>>(appExecutors) {
            override fun saveCallResult(items: List<Request>) {
                requestDAO.deleteAll()
                requestDAO.insertAll(items)
            }

            override fun shouldFetch(data: List<Request>?) = true

            override fun loadFromDb() = requestDAO.getCompletedRequests()

            override fun createCall() = armService.getAllRequest()
        }.asLiveData()
    }

    suspend fun insertRequest(request: Request) {
        //Отправка запроса на сервер, при положительном ответе вставка в БД. Либо вызов обновления всего списка с сервера

        requestDAO.insert(request)
    }

    suspend fun updateRequest(request: Request) {
        //Отправка запроса на сервер, при положительном ответе обновление записи в БД. Либо вызов обновления всего списка с сервера

        requestDAO.update(request)

    }

    suspend fun getRequestCount(): Int {
        return requestDAO.getRequestCount()
    }

    suspend fun deleteRequest(request: Request){
        return requestDAO.deleteRequest(request)
    }

    suspend fun deleteAll() {
        requestDAO.deleteAll()
    }
}