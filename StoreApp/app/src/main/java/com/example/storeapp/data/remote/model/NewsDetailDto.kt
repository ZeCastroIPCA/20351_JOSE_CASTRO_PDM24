package com.example.storeapp.data.remote.model

import com.example.storeapp.domain.model.NewsDetail

// Data transfer object for news detail
data class NewsDetailDto(
    val uuid: String,
    val title: String,
    val description: String,
    val snippet: String,
    val url: String,
    val image_url: String,
    val published_at: String,
    val source: String
) {
    // Convert to domain model
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