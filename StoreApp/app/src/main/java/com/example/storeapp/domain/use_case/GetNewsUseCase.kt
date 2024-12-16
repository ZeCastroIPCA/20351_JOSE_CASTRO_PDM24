package com.example.storeapp.domain.use_case

import com.example.storeapp.domain.model.News
import com.example.storeapp.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    // Get news
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}