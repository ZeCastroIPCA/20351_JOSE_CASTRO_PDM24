package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.news_detail.NewsDetailScreen
import com.example.newsapp.presentation.news_detail.NewsDetailViewModel
import com.example.newsapp.presentation.news_list.NewsListScreen
import com.example.newsapp.presentation.news_list.NewsListViewModel
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedNewsId by remember { mutableStateOf<String?>(null) }

    if (selectedNewsId == null) {
        val newsListViewModel = NewsListViewModel()
        NewsListScreen(newsListViewModel) { newsId ->
            selectedNewsId = newsId
        }
    } else {
        val newsDetailViewModel = NewsDetailViewModel()
        NewsDetailScreen(newsDetailViewModel, selectedNewsId!!) {
            selectedNewsId = null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NewsAppTheme {
        MainScreen()
    }
}