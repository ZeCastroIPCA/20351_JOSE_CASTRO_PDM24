package com.grupo1.lojasocial.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    //searchViewModel: SearchViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var searchQuery by remember { mutableStateOf("") }
    //val searchResults by searchViewModel.searchResultsBeneficiaries.collectAsState()

    LaunchedEffect(searchQuery) {
        if (searchQuery.length >= 3) {
            //searchViewModel.searchBeneficiaries(searchQuery)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            /*SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                //onCancelClick = { searchQuery = "" }
            )

            when {
                searchQuery.length >= 3 && searchResults?.isEmpty() == true -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                searchQuery.length >= 3 -> {
                    searchResults?.let {
                        ProfileList(
                            type = "enter_profile",
                            profiles = it,
                            onClick = { profile ->
                                if (currentRoute === Screen.People.route) {
                                    localHistoryViewModel.insertBeneficiaryHistory(profile)
                                    navController.navigate("${Screen.ProfileBeneficiary.route}/${profile.id}")
                                } else {
                                    navController.navigate("${Screen.RegisterSession.route}/${profile.id}")
                                }
                            },
                            onRemoveClick = {}
                        )
                    }
                }

                searchQuery.isNotEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Digite pelo menos 3 letras para pesquisar",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                }
            }*/
        }
    }
}