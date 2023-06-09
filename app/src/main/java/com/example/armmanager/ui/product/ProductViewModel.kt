package com.example.armmanager.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.armmanager.repository.ProductRepository
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Resource
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productsRepository: ProductRepository) :
    ViewModel() {
    private val _productLiveData = MutableLiveData<Resource<List<Product>>>()


    val product: LiveData<Resource<List<Product>>> = _productLiveData.switchMap {
        productsRepository.getProducts()
    }

    suspend fun deleteProduct(product: Product) {
        productsRepository.deleteProduct(product)
    }


    fun createProduct(productName: String): LiveData<Resource<Product>> {
        return productsRepository.createProduct(productName)
    }

    fun refresh() {
        _productLiveData.value.let {
            _productLiveData.value = it
        }
    }
}