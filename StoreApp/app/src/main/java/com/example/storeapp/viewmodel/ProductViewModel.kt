package com.example.storeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.data.repository.ProductRepository
import com.example.storeapp.domain.model.Product
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ProductViewModel(private val productRepository: ProductRepository = ProductRepository()) : ViewModel() {

    private var _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun getProducts() {
        viewModelScope.launch {
            productRepository.getProducts().let {
                _products.value = it
            }
        }
    }

    fun addProduct(name: String, description: String, price: String, onProductAdded: () -> Unit) {
        viewModelScope.launch {
            val productId  = UUID.randomUUID().toString()
            val product = Product(productId, name, description, price.toDouble(), Timestamp.now())
            productRepository.addProduct(product)
            onProductAdded()
        }
    }
}
