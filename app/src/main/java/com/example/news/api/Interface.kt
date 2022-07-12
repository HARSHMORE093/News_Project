package com.example.news

import com.example.news.dataclass.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//https://newsapi.org/v2/top-headlines?country=in&apiKey=c624dfcc1d294aacb516f64dee519a2d&page=1
const val Base_url = "https://newsapi.org/"
const val ApiKey = "c624dfcc1d294aacb516f64dee519a2d"

interface NewsInterface {
    @GET("v2/top-headlines?country=in&apiKey=$ApiKey")
    fun getheadline (@Query("page") pageNumber:Int ): Call<News>

}
object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}