package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News

interface NewsRepository {
    // Function to get all the available news
    suspend fun getNews(): List<News>
}