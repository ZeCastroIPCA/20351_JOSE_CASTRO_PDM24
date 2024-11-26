package com.example.newsapp.data.remote.model

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsDetail

data class ApiResponse(
    val meta: Meta,
    val data: List<NewsDto>
)

data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)


data class NewsDto(
    val uuid: String,
    val title: String,
    val description: String,
    val snippet: String,
    val url: String,
    val image_url: String,
    val published_at: String,
    val source: String
) {
    fun toNews() : News {
        return News(
            id = uuid,
            title = title,
            description = description
        )
    }
    fun toNewsDetail() : NewsDetail {
        return NewsDetail(
            id = uuid,
            title = title,
            description = description,
            date = published_at,
            imageUrl = image_url,
            source = source
        )
    }
}