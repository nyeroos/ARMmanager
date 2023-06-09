package com.example.armmanager.repository

import androidx.lifecycle.LiveData
import com.example.armmanager.AppExecutors
import com.example.armmanager.api.ArmService
import com.example.armmanager.database.RequestDAO
import com.example.armmanager.util.AbsentLiveData
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Resource
import com.example.armmanager.vo.dto.AddRequestDto
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

    fun createRequest(
        requestNumber: Int, requestName: String, customer: String, expectedDate: String,
        creationDate: String, actualDate: String, user: Int, status: String
    ): LiveData<Resource<Request>> {
        return object : NetworkBoundResource<Request, Unit>(appExecutors) {
            override fun saveCallResult(item: Unit) {

            }

            override fun shouldFetch(data: Request?) = true

            override fun loadFromDb() = AbsentLiveData.create<Request>()

            override fun createCall() = armService.addRequest(
                AddRequestDto(
                    requestNumber, requestName, customer,
                    expectedDate, creationDate, actualDate,  status
                )
            )
        }.asLiveData()
    }

    fun updateRequest(request: Request): LiveData<Resource<Request>> {
        return object : NetworkBoundResource<Request, Request>(appExecutors) {
            override fun saveCallResult(item: Request) {

            }

            override fun shouldFetch(data: Request?) = true

            override fun loadFromDb() = AbsentLiveData.create<Request>()

            override fun createCall() = armService.updateRequest(request.id, request)
        }.asLiveData()
    }

    fun deleteRequest(request: Request): LiveData<Resource<Unit>> {
        return object : NetworkBoundResource<Unit, Unit>(appExecutors) {
            override fun saveCallResult(item: Unit) {

            }

            override fun shouldFetch(data: Unit?) = true

            override fun loadFromDb() = AbsentLiveData.create<Unit>()

            override fun createCall() = armService.deleteRequest(request.id)
        }.asLiveData()
    }

}