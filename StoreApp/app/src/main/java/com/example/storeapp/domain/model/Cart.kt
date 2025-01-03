package com.example.storeapp.domain.model

data class Cart(
    val id: String = "",
    val shareId: String = "",
    val user: User = User(),
    val products: List<Product> = emptyList(),
)