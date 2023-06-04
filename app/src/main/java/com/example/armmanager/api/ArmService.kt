package com.example.armmanager.api

import androidx.lifecycle.LiveData
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ArmService {
//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRequest(@Path("login") login: String): LiveData<ApiResponse<List<Request>>>

    @POST("")
    fun createProduct(name: String): LiveData<ApiResponse<Product>>

    @GET("users/{login}/repos")
    fun getProduct(@Path("login") login: String): LiveData<ApiResponse<List<Product>>>

    @PUT("requests/{id}")
    suspend fun updateRequest(@Path("id") id: Int, @Body request:Request): Response<Request>

    @GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<Request>>

//    @GET("repos/{owner}/{name}/contributors")
//    fun getContributors(
//        @Path("owner") owner: String,
//        @Path("name") name: String
//    ): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RequestProductResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RequestProductResponse>


    @GET("products/all")
    fun getAllProducts():LiveData<ApiResponse<List<Product>>>
}