package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override suspend fun getNews(): List<News> {
        // Call the API and map the response to the domain model
        return api.getNews().data.map {
            it.toNews()
        }
    }
}