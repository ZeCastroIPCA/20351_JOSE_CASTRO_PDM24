package com.example.storeapp.data.remote.api

import com.example.storeapp.data.remote.model.ApiResponse
import com.example.storeapp.data.remote.model.NewsDetailDto
import com.example.storeapp.data.remote.model.NewsDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

// Api key (in a real app, this should be stored in a secure place)
private const val API_KEY = "Xe2u3J36aZCcoIic3nU0oWwRVWZuRCI54Uwb0UsY"

// Base url of the API
private const val BASE_URL = "https://api.thenewsapi.com/v1/news/"

// Custom deserializer for the news response
class NewsResponseDeserializer : JsonDeserializer<List<NewsDto>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<NewsDto> {
        return json.asJsonObject.getAsJsonArray("data")
            .map { context.deserialize(it, NewsDto::class.java) }
    }
}

// Retrofit instance
object RetrofitInstance {
    // Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // News API instance
    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}

interface NewsApi {
    // Get top news
    @GET("top")
    suspend fun getNews(
        @Query("api_token") apiKey: String = API_KEY,
        @Query("locale") locale: String = "us",
        @Query("limit") limit: Int = 10
    ): ApiResponse

    // Get each news by id
    @GET("uuid/{id}")
    suspend fun getNewsDetail(
        @Path("id") newsId: String,
        @Query("api_token") apiKey: String = API_KEY
    ): NewsDetailDto
}
