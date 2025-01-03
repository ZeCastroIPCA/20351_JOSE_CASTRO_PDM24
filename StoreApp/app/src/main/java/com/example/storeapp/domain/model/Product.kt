package com.example.storeapp.domain.model

import com.google.firebase.Timestamp

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val createdAt: Timestamp? = null
)