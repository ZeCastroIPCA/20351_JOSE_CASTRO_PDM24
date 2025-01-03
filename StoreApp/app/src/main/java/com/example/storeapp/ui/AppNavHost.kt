package com.example.storeapp.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storeapp.navigation.Screen
import com.example.storeapp.ui.components.BottomNavigationBar
import com.example.storeapp.ui.screens.SettingsScreen
import com.example.storeapp.ui.screens.SignUpScreen
import com.example.storeapp.viewmodel.AuthViewModel
import com.example.storeapp.viewmodel.UserViewModel
import com.grupo1.lojasocial.ui.screens.HomeScreen
import com.grupo1.lojasocial.ui.screens.LoginScreen
import com.grupo1.lojasocial.ui.screens.SearchScreen

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
) {
    val navController = rememberNavController()

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    val startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route

    Scaffold(
        bottomBar = {
            if (isLoggedIn) {
                run {
                    BottomNavigationBar(
                        navController = navController,
                        items = listOf(
                            Screen.Home,
                            Screen.Search,
                            Screen.Cart,
                            Screen.Settings
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                val loginState by authViewModel.loginState.collectAsState()

                val loginError = if (loginState?.isFailure == true) {
                    loginState!!.exceptionOrNull()?.localizedMessage
                } else null

                LoginScreen(
                    navController,
                    onLoginClick = { email, password ->
                        authViewModel.login(
                            email.trim(),
                            password.trim()
                        )
                    },
                    loginError
                )

                if (loginState?.isSuccess == true) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo("login") { inclusive = true }
                    }
                    authViewModel.resetLoginState()
                }
            }

            composable(Screen.SignUp.route) {
                var signUpError by remember { mutableStateOf<String?>(null) }

                SignUpScreen(
                    navController,
                    onSignUpClick = { name, email, password ->
                        userViewModel.registerUser(
                            name = name.trim(),
                            email = email.trim(),
                            password = password.trim(),
                            onRegisterSuccess = {
                                authViewModel.login(email.trim(), password.trim())
                            },
                            onError = { errorMessage ->
                                signUpError = errorMessage
                            }
                        )
                    },
                    errorMessage = signUpError
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    navController,
                    userViewModel,
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    navController,
                    //searchViewModel,
                )
            }

            /*composable(Screen.Cart.route) {
                CartScreen(
                    navController,
                )
            }*/

            composable(Screen.Settings.route) {
                SettingsScreen(
                    navController,
                    authViewModel,
                    userViewModel,
                    onLogout = { navController.navigate(Screen.Login.route) }
                )
            }
        }
    }
}
