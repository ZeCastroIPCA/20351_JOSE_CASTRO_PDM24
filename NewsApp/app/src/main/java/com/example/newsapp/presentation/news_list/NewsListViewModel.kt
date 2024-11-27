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

    val news = MutableStateFlow<List<News>>(emptyList())
    val isLoading = MutableStateFlow(false)

    fun fetchNews() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                news.value = getNewsUseCase()
            } catch (e: Exception) {
                news.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }
}