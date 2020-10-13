package com.example.news.api2

import com.example.news.model1.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//3
class ApiClient {

    val BASE_URL ="https://newsapi.org/v2/"

    val newApiInterface:NewApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newApiInterface=retrofit.create(NewApiInterface::class.java)


    }

    fun getTopHeadlines(country:String,category: String):Call<News>
    {
        return newApiInterface.getTopHeadlines(country,category)
    }

    //for search
    fun  searchNews(query:String):Call<News>{
        return searchNews(query)
    }


}