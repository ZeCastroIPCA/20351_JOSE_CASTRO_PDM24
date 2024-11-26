package com.example.newsapp.presentation.news_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NewsDetailScreen(
    newsDetailViewModel: NewsDetailViewModel
) {
    val news = newsDetailViewModel.news

    LaunchedEffect(Unit) {
        newsDetailViewModel.fetchIndividualNews()
    }

    news.let { newsDetail ->
        Column {
            AsyncImage(
                model = newsDetail.imageUrl,
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(text = "Title: ${newsDetail.title}")
            Text(text = "Description: ${newsDetail.description}")
            Text(text = "Date: ${newsDetail.date}")
            Text(text = "Source: ${newsDetail.source}")
            Spacer(modifier = Modifier.height(16.dp))
        }
    } ?: run {
        Text(text = "Loading...")
    }
}
