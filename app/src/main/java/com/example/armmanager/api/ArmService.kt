package com.example.armmanager.api

import androidx.lifecycle.LiveData
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.dto.AddProductDto
import com.example.armmanager.vo.dto.AddRequestDto
import com.example.armmanager.vo.dto.LoginDto
import com.example.armmanager.vo.dto.RegisterDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ArmService {
//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

//    @GET("users/{login}/repos")
//    fun getRequest(@Path("login") login: String): LiveData<ApiResponse<List<Request>>>

    @POST("")
    fun createProduct(name: String): LiveData<ApiResponse<Product>>

    @POST("users/login")
    fun login(@Body request: LoginDto):Call<Unit>

    @POST("users/register")
    fun register(@Body request: RegisterDto):Call<Unit>

    @PUT("request/{id}")
    fun updateRequest(@Path("id") id: Int, @Body request:Request): LiveData<ApiResponse<Request>>

    @GET("request/all")
    fun getAllRequest():LiveData<ApiResponse<List<Request>>>
    @POST("request/add")
    fun addRequest(@Body request: AddRequestDto): LiveData<ApiResponse<Unit>>
    @DELETE("request/{id}")
    fun deleteRequest(@Path("id") id: Int): LiveData<ApiResponse<Unit>>
    @POST("products/add")
    fun addProduct(@Body productName: AddProductDto): LiveData<ApiResponse<Unit>>
    @GET("products/all")
    fun getAllProducts():LiveData<ApiResponse<List<Product>>>
}