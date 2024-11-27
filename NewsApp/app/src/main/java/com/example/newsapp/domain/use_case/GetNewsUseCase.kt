package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    // Get news
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}