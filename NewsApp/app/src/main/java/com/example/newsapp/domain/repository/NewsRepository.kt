package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsDetail

interface NewsRepository {
    suspend fun getNews(): List<News>
}

interface NewsDetailRepository {
    suspend fun getNewsDetail(newsId: String): NewsDetail
}