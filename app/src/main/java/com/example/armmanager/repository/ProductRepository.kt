package com.example.armmanager.repository

import androidx.lifecycle.LiveData
import com.example.armmanager.AppExecutors
import com.example.armmanager.api.ArmService
import com.example.armmanager.database.ProductDAO
import com.example.armmanager.util.AbsentLiveData
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val productDAO: ProductDAO,
    private val armService: ArmService
) {

    fun getProducts(): LiveData<Resource<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, List<Product>>(appExecutors) {
            override fun saveCallResult(items: List<Product>) {
                productDAO.deleteAll()
                items.forEach { el -> run { productDAO.insert(el) } }
            }

            override fun shouldFetch(data: List<Product>?) = data == null

            override fun loadFromDb() = productDAO.getProducts()

            override fun createCall() = armService.getAllProducts()
        }.asLiveData()
    }


    suspend fun insertProduct(product: Product) {
        //Отправка запроса на сервер, при положительном ответе вставка в БД. Либо вызов обновления всего списка с сервера

        productDAO.insert(product)
    }

    fun createProduct(name: String): LiveData<Resource<Product>> {
        return object : NetworkBoundResource<Product, Product>(appExecutors) {
            override fun saveCallResult(item: Product) {
                //requestDAO.insert(item)
            }

            override fun shouldFetch(data: Product?) = true

            override fun loadFromDb() = AbsentLiveData.create<Product>()

            override fun createCall() = armService.createProduct(name)
        }.asLiveData()
    }

    suspend fun updateProduct(product: Product) {
        productDAO.update(product)
    }

    suspend fun getProductCount(): Int {
        return productDAO.getProductsCount()
    }

    suspend fun deleteProduct(product: Product) {
        return productDAO.deleteProduct(product)
    }

    suspend fun deleteAll() {
        productDAO.deleteAll()
    }
}