package com.example.armmanager.repository

import androidx.lifecycle.LiveData
import com.example.armmanager.AppExecutors
import com.example.armmanager.api.ArmService
import com.example.armmanager.database.RequestDAO
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
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

    suspend fun insertRequest() {
        //Отправка запроса на сервер, при положительном ответе вставка в БД. Либо вызов обновления всего списка с сервера
        requestDAO.insert(
            Request(
                0,
                1,
                "Test",
                "TestCustomer",
                "22.12.2022",
                "21.11.2022",
                "20.05.2023",
                1,
                "1"
            )
        )
    }
}