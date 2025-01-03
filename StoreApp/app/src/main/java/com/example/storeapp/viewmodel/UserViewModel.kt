package com.example.storeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.data.repository.UserRepository
import com.example.storeapp.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    init {
        getCurrentUser()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val email = userRepository.getCurrentUserEmail()
            val user = userRepository.getUserByEmail(email)
            Log.d("UserViewModel", "Retrieved user: $user")
            _currentUser.value = user
        }
    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onRegisterSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val user = mapOf(
                    "name" to name,
                    "email" to email,
                    "password" to password,
                )
                userRepository.registerUser(user)
                onRegisterSuccess()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error registering user: ${e.message}")
                onError(e.message ?: "An unknown error occurred.")
            }
        }
    }

    fun updateUser(
        id: String,
        name: String,
        email: String,
    ) {
        val user = User(
            id = id,
            name = name,
            email = email,
        )

        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(id: String) {
        viewModelScope.launch {
            userRepository.deleteUser(id)
        }
    }
}