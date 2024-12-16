package com.example.storeapp.data.repository

import com.example.storeapp.data.remote.api.NewsApi
import com.example.storeapp.domain.model.News
import com.example.storeapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override suspend fun getNews(): List<News> {
        // Call the API and map the response to the domain model
        return api.getNews().data.map {
            it.toNews()
        }
    }
}