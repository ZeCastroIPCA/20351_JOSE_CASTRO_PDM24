package com.example.storeapp.domain.repository

import com.example.storeapp.domain.model.NewsDetail

interface NewsDetailRepository {
    // Function to get news detail
    suspend fun getNewsDetail(newsId: String): NewsDetail
}