package com.example.newsapp.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.api.RetrofitInstance
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = NewsRepositoryImpl(api)
    private val getNewsUseCase = GetNewsUseCase(repository)

    // As a state flow, news and isLoading can be observed by the UI to update the UI when the data changes
    val news = MutableStateFlow<List<News>>(emptyList())
    val isLoading = MutableStateFlow(false)

    // Fetch news from the API
    fun fetchNews() {
        viewModelScope.launch {
            // Set isLoading to true before fetching the news
            isLoading.value = true
            try {
                // Fetch the news from the API
                news.value = getNewsUseCase()
            } catch (e: Exception) {
                // If an error occurs, set news to an empty list
                news.value = emptyList()
            } finally {
                // Set isLoading to false after fetching the news
                isLoading.value = false
            }
        }
    }
}