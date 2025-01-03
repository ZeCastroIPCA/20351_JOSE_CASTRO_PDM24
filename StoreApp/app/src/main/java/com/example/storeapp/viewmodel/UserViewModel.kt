package com.example.storeapp.viewmodel

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

    private fun getCurrentUser() {
        viewModelScope.launch {
            val email = userRepository.getCurrentUserEmail()
            val user = userRepository.getUserByEmail(email)
            _currentUser.value = user
        }
    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
    ) {
        val user = mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
        )
        viewModelScope.launch {
            userRepository.registerUser(user)
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