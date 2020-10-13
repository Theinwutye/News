package com.example.news.api2

import com.example.news.model1.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

//2
interface NewApiInterface {

    @Headers("X-Api-Key:85182792e77a43559e62423b56386407")
    @GET("top-headlines")
    fun getTopHeadlines(

        @Query("country") country:String,
        @Query("category") category:String

    ):Call<News>



//for search everything
    @Headers("X-Api-Key:85182792e77a43559e62423b56386407")
    @GET("everything")
    fun searchNews(

        @Query("q") query:String

    ):Call<News>

}