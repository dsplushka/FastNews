package com.example.fastnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val listener:OnNewsClick): RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val author: TextView = itemView.findViewById(R.id.author)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener{
            listener.onClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNews = items[position]
        holder.title.text = currentNews.title
        holder.author.text = currentNews.author

        Glide.with(holder.itemView.context).load(currentNews.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateData(newData:ArrayList<News>){
        items.clear()
        items.addAll(newData)

        notifyDataSetChanged()
    }
}

interface OnNewsClick {
    fun onClicked(news: News)
}