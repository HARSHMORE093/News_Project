package com.example.news.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.news.R

class WebView : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val web=findViewById<WebView>(R.id.webView)
        val progressBar=findViewById<ProgressBar>(R.id.progress_circular)
        val str=intent.getStringExtra("URL")
        if(str != null){
            web.settings.javaScriptEnabled=true
            web.webViewClient=object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility= View.GONE
                    web.visibility=View.VISIBLE
                }
            }
            web.loadUrl(str)
        }
    }
}