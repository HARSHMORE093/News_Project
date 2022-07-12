package com.example.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.dataclass.Articles
import com.example.news.R
import com.example.news.activity.WebView

class Myadapter(val context: Context,val article:List<Articles>) :RecyclerView.Adapter<Myadapter.Viewholder>(){
    class Viewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val image =itemView.findViewById<ImageView>(R.id.Image)
        val title=itemView.findViewById<TextView>(R.id.Title)
        val description=itemView.findViewById<TextView>(R.id.Description)
        val published=itemView.findViewById<TextView>(R.id.PublishedAt)
        val author=itemView.findViewById<TextView>(R.id.Author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=LayoutInflater.from(context).inflate(R.layout.items_list,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val articles=article[position]
        holder.title.text=articles.title
        holder.description.text=articles.description
        holder.published.text=articles.publishedAt
        holder.author.text=articles.author
        Glide.with(context).load(articles.urlToImage).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent=Intent(context, WebView::class.java)
            intent.putExtra("URL",articles.url)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return article.size
    }
}