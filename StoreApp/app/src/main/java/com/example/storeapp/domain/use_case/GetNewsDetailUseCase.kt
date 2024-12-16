package com.example.storeapp.domain.use_case

import com.example.storeapp.domain.model.NewsDetail
import com.example.storeapp.domain.repository.NewsDetailRepository

class GetNewsDetailUseCase(private val repository: NewsDetailRepository) {
    // Get news detail by newsId
    suspend operator fun invoke(newsId: String): NewsDetail {
        return repository.getNewsDetail(newsId)
    }
}