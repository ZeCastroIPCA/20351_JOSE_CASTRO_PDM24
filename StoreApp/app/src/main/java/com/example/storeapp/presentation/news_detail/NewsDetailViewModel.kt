package com.example.storeapp.presentation.news_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.data.remote.api.RetrofitInstance
import com.example.storeapp.data.repository.NewsDetailRepositoryImpl
import com.example.storeapp.domain.model.NewsDetail
import com.example.storeapp.domain.use_case.GetNewsDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = NewsDetailRepositoryImpl(api)
    private val getNewsDetailUseCase = GetNewsDetailUseCase(repository)

    // As a stateflow, news is mutable and can be updated
    var news = MutableStateFlow<NewsDetail?>(null)

    // Fetch individual news
    fun fetchIndividualNews (newsId: String) {
        viewModelScope.launch {
            try {
                news.value = getNewsDetailUseCase(newsId)
            } catch (e: Exception) {
                Log.e("Error: ", e.toString())
            }
        }
    }
}