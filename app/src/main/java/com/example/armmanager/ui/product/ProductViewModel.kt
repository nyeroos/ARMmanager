package com.example.armmanager.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armmanager.repository.ProductRepository
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productsRepository: ProductRepository) :
    ViewModel() {

    val product: LiveData<Resource<List<Product>>> = productsRepository.getProducts()

    suspend fun deleteProduct(product: Product) {
        productsRepository.deleteProduct(product)
    }


    fun createProduct(productName: String): LiveData<Resource<Product>> {
        return productsRepository.createProduct(productName)
    }

    fun log1() = viewModelScope.launch {
        var a = productsRepository.getProductCount()
        Log.d("tt", "$a")
    }

    fun refresh() {
        productsRepository.getProducts()
    }
}