package com.example.newsapp.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String
)

data class NewsDetail(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val source: String,
)