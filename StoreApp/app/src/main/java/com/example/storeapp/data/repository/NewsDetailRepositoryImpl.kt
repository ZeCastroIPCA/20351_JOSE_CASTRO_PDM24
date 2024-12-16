package com.example.storeapp.data.repository

import com.example.storeapp.data.remote.api.NewsApi
import com.example.storeapp.domain.model.NewsDetail
import com.example.storeapp.domain.repository.NewsDetailRepository

class NewsDetailRepositoryImpl(private val api: NewsApi) : NewsDetailRepository {
    override suspend fun getNewsDetail(newsId: String): NewsDetail {
        // Call the API and convert the response to NewsDetail
        return api.getNewsDetail(newsId).toNewsDetail()
    }
}