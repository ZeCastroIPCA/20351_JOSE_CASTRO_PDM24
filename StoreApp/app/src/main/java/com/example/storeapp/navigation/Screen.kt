package com.example.storeapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    /* MAIN SCREENS */
    object Login : Screen("login", "Login", Icons.Default.Lock)
    object SignUp : Screen("signup", "SignUp", Icons.Default.Lock)
    object Home : Screen("home", "Início", Icons.Default.Home)
    object Search : Screen("search", "Pessoas", Icons.Default.Search)
    object Cart : Screen("cart", "Carrinho", Icons.Default.ShoppingCart)
    object Settings : Screen("settings", "Configurações", Icons.Default.Settings)
}
