package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsDetail
import com.example.newsapp.domain.repository.NewsDetailRepository
import com.example.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override suspend fun getNews(): List<News> {
        return api.getNews().data.map {
            it.toNews()
        }
    }
}

class NewsDetailRepositoryImpl(private val api: NewsApi) : NewsDetailRepository {
    override suspend fun getNewsDetail(newsId: String): NewsDetail {
        return api.getNewsDetail(newsId).toNewsDetail()
    }
}