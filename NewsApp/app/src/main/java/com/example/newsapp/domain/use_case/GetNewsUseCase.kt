package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsDetail
import com.example.newsapp.domain.repository.NewsDetailRepository
import com.example.newsapp.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}

class GetNewsDetailUseCase(private val repository: NewsDetailRepository) {
    suspend operator fun invoke(newsId: String): NewsDetail {
        return repository.getNewsDetail(newsId)
    }
}