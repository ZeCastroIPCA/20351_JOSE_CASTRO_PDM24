package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.NewsDetail
import com.example.newsapp.domain.repository.NewsDetailRepository

class NewsDetailRepositoryImpl(private val api: NewsApi) : NewsDetailRepository {
    override suspend fun getNewsDetail(newsId: String): NewsDetail {
        // Call the API and convert the response to NewsDetail
        return api.getNewsDetail(newsId).toNewsDetail()
    }
}