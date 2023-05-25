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
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val requestDAO: RequestDAO,
    private val armService: ArmService
) {

    fun getRequests(login: String): LiveData<Resource<List<Request>>> {
        return object : NetworkBoundResource<List<Request>, List<Request>>(appExecutors) {
            override fun saveCallResult(items: List<Request>) {
                //requestDAO.insert(item)
            }

            override fun shouldFetch(data: List<Request>?) = data == null

            override fun loadFromDb() = requestDAO.getRequests()

            override fun createCall() = armService.getRequest("")
        }.asLiveData()
    }

    suspend fun insertRequest(request: Request) {
        //Отправка запроса на сервер, при положительном ответе вставка в БД. Либо вызов обновления всего списка с сервера

        requestDAO.insert(request)
    }

    suspend fun updateRequest(request: Request) {
        //Отправка запроса на сервер, при положительном ответе обновление записи в БД. Либо вызов обновления всего списка с сервера

        // Отправляем запрос на сервер для обновления записи
        val response = armService.updateRequest(request.id, request)

        // Если запрос успешен, обновляем запись в БД
        if (response.isSuccessful) {
            requestDAO.update(request)
        } else {
            // Если запрос неуспешен, мы можем обработать ошибку соответствующим образом
            // ...
        }

//        // Обрабатываем ответ сервера
//        when (val apiResponse = ApiResponse.create(response)) {
//            is ApiSuccessResponse -> {
//                // Если запрос успешен, обновляем запись в БД
//                appExecutors.diskIO().execute {
//                    requestDAO.update(request)
//                }
//            }
//            is ApiEmptyResponse -> {
//                // Если сервер вернул пустой ответ, мы можем обработать это соответствующим образом
//                // ...
//            }
//            is ApiErrorResponse -> {
//                // Если запрос неуспешен, мы можем обработать ошибку соответствующим образом
//                // ...
//            }
//        }
    }

    suspend fun getRequestCount(): Int {
        return requestDAO.getRequestCount()
    }

    suspend fun deleteAll() {
        requestDAO.deleteAll()
    }
}