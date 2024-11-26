package com.example.newsapp.presentation.news_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.api.RetrofitInstance
import com.example.newsapp.data.repository.NewsDetailRepositoryImpl
import com.example.newsapp.domain.model.NewsDetail
import com.example.newsapp.domain.use_case.GetNewsDetailUseCase
import kotlinx.coroutines.launch

class NewsDetailViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val repository = NewsDetailRepositoryImpl(api)
    private val getNewsDetailUseCase = GetNewsDetailUseCase(repository)

    var news : NewsDetail = NewsDetail(
        id = "",
        title = "",
        description = "",
        date = "",
        imageUrl = "",
        source = ""
    )

    fun fetchIndividualNews () {
        viewModelScope.launch {
            try {
                news = getNewsDetailUseCase()
            } catch (e: Exception) {
                Log.e("Error: ", e.toString())
            }
        }
    }
}