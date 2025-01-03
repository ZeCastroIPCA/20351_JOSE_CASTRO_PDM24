package com.example.storeapp.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.storeapp.domain.model.User
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val usersCollection = db.collection("users")

    suspend fun getUserByEmail(email: String?): User? {
        return try {
            val querySnapshot = usersCollection
                .whereEqualTo("email", email)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val document = querySnapshot.documents.first()

                val user = document.toObject(User::class.java)?.copy(id = document.id)

                user
            } else {
                null
            }
        } catch (e: Exception) {
            println("Error fetching user: ${e.message}")
            null
        }
    }

    fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }

    suspend fun getUserById(id: String): User? {
        return try {
            val document = usersCollection.document(id).get().await()

            document.toObject(User::class.java)?.copy(id = document.id)
        } catch (e: Exception) {
            println("Error fetching user: ${e.message}")
            null
        }
    }

    suspend fun registerUser(user: Map<String, Any>) {
        try {
            val authResult = auth.createUserWithEmailAndPassword(user["email"].toString(), user["password"].toString()).await()
            val userDataForFirestore = mapOf(
                "name" to user["name"].toString(),
                "email" to user["email"].toString()
            )
            usersCollection.document(authResult.user?.uid ?: "").set(userDataForFirestore).await()

            Result.success(Unit)
        } catch (e: FirebaseAuthUserCollisionException) {
            throw Exception("Este email já pertence a uma conta. Por favor, insira um email diferente!")
        } catch (e: Exception) {
            throw Exception("Erro ao registrar o utilizador: ${e.message}")
        }
    }

    suspend fun updateUser(user: User) {
        try {
            usersCollection.document(user.id).set(user).await()
        } catch (e: Exception) {
            println("Error updating user: ${e.message}")
        }
    }

    suspend fun deleteUser(id: String) {
        try {
            usersCollection.document(id).delete().await()
        } catch (e: Exception) {
            println("Error deleting user: ${e.message}")
        }
    }
}