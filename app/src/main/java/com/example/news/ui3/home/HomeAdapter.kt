package com.example.news.ui3.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.model1.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

//6,2
class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var articleList: List<ArticlesItem> =ArrayList()

    class HomeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(articlesItem: ArticlesItem){

            Picasso.get().load(articlesItem.urlToImage)
                .into(itemView.newsImage)
            itemView.author.text=articlesItem.author
            itemView.description.text=articlesItem.description

        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)

        return HomeViewHolder(view)


    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int =articleList.size

    fun updateArticleList(articlelist: List<ArticlesItem>){
        this.articleList=articlelist
        notifyDataSetChanged()
    }

}