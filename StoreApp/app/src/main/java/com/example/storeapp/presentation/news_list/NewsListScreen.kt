package com.example.storeapp.presentation.news_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.storeapp.domain.model.News
import com.example.storeapp.presentation.news_detail.CustomLoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    newsListViewModel: NewsListViewModel, // ViewModel
    onNewsItemClick: (String) -> Unit // Click for each news item
) {

    // Collect the news and isLoading state from the ViewModel
    val news by newsListViewModel.news.collectAsState()
    val isLoading by newsListViewModel.isLoading.collectAsState()

    // Fetch news when the screen is launched
    LaunchedEffect(Unit) {
        newsListViewModel.fetchNews()
    }

    // Display the news list
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Today's News") })
        }
    ) { paddingValues ->
        // Display loading screen if the news is still loading
        if (isLoading) {
            LoadingScreen()
        } else {
            // Display the news list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(news) { newsItem ->
                    NewsItemCard(
                        news = newsItem,
                        onClick = { onNewsItemClick(newsItem.id) }
                    )
                }
            }
        }
    }
}

// Display a loading screen while fetching news
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomLoadingWheel()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Loading news...")
        }
    }
}

// Display each news item in a card
@Composable
fun NewsItemCard(
    news: News,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}