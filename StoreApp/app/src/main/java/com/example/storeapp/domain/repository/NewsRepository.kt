package com.example.storeapp.domain.repository

import com.example.storeapp.domain.model.News

interface NewsRepository {
    // Function to get all the available news
    suspend fun getNews(): List<News>
}