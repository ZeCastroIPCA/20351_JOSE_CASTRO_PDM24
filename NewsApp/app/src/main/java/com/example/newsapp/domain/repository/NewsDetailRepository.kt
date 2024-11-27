package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.NewsDetail

interface NewsDetailRepository {
    // Function to get news detail
    suspend fun getNewsDetail(newsId: String): NewsDetail
}