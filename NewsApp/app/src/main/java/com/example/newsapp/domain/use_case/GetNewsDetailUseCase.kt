package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.model.NewsDetail
import com.example.newsapp.domain.repository.NewsDetailRepository

class GetNewsDetailUseCase(private val repository: NewsDetailRepository) {
    // Get news detail by newsId
    suspend operator fun invoke(newsId: String): NewsDetail {
        return repository.getNewsDetail(newsId)
    }
}