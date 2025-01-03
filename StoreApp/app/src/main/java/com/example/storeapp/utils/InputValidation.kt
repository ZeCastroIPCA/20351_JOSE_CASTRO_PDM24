package com.example.storeapp.utils

fun nameValidation(name: String): Boolean {
    val namePattern = "[a-zA-Z]+"
    return name.matches(namePattern.toRegex())
}

fun emailValidation(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}