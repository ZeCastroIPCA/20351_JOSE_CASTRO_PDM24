package com.example.storeapp.data.repository

import com.example.storeapp.domain.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductRepository {
    private val db = FirebaseFirestore.getInstance()

    private val productsCollection = db.collection("products")

    suspend fun getProducts(): List<Product> {
        return try {
            val querySnapshot = productsCollection.orderBy("createdAt").get().await()
            val products = mutableListOf<Product>()
            for (document in querySnapshot.documents) {
                val product = document.toObject(Product::class.java)?.copy(id = document.id)
                if (product != null) {
                    products.add(product)
                }
            }
            products.toList()
        } catch (e: Exception) {
            println("Error fetching products: ${e.message}")
            emptyList()
        }
    }

    suspend fun addProduct(product: Product) {
        try {
            productsCollection.document(product.id ?: "").set(product).await()
        } catch (e: Exception) {
            println("Error adding product: ${e.message}")
        }
    }

    suspend fun updateProduct(product: Product) {
        try {
            productsCollection.document(product.id ?: "").set(product).await()
        } catch (e: Exception) {
            println("Error updating product: ${e.message}")
        }
    }

    suspend fun deleteProduct(product: Product) {
        try {
            productsCollection.document(product.id ?: "").delete().await()
        } catch (e: Exception) {
            println("Error deleting product: ${e.message}")
        }
    }
}