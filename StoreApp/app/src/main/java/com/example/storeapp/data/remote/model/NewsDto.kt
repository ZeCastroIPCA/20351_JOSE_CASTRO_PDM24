package com.example.storeapp.data.remote.model

import com.example.storeapp.domain.model.News

// Whole object structure of the API response
data class ApiResponse(
    val meta: Meta,
    val data: List<NewsDto>
)

// Meta object structure of the API response
data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)

// News object structure of the API response
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
    // Convert the NewsDto object to a News object
    fun toNews() : News {
        return News(
            id = uuid,
            title = title,
            description = description
        )
    }
}