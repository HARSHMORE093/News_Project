package com.example.news.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.NewsService
import com.example.news.R
import com.example.news.adapter.Myadapter
import com.example.news.dataclass.News


class MainActivity : AppCompatActivity() {
    lateinit var adapter: Myadapter
    var pageNum=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val mainRecycler = findViewById<RecyclerView>(R.id.Recyclerview)
        val news = NewsService.newsInstance.getheadline(pageNum)
        news.enqueue(object : Callback<News>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val responsebody=response.body()
                if(responsebody != null) {
                    Log.e("Harsh", responsebody.toString())
                    adapter= Myadapter(this@MainActivity,responsebody.articles)
                    mainRecycler.adapter=adapter
                    mainRecycler.layoutManager=LinearLayoutManager(this@MainActivity)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("Harsh","api not fetch",t)
            }
        })
    }
}



