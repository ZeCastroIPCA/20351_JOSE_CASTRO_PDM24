package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.model.ApiResponse
import com.example.newsapp.data.remote.model.NewsDto
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

private const val API_KEY = "Xe2u3J36aZCcoIic3nU0oWwRVWZuRCI54Uwb0UsY"
private const val BASE_URL = "https://api.thenewsapi.com/v1/news/"

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

object RetrofitInstance {
    private val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<List<NewsDto>>() {}.type, NewsResponseDeserializer())
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}

interface NewsApi {
    @GET("top")
    suspend fun getNews(
        @Query("api_token") apiKey: String = API_KEY,
        @Query("locale") locale: String = "us",
        @Query("limit") limit: Int = 10
    ): ApiResponse

    @GET("uuid/{id}")
    suspend fun getNewsDetail(
        @Path("id") newsId: String,
        @Query("api_token") apiKey: String = API_KEY
    ): NewsDto
}
