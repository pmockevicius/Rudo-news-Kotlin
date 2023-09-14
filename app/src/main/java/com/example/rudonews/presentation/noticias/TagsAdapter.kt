package com.example.rudonews.presentation.noticias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R


class TagsAdapter(private val dataList: List<String>) : RecyclerView.Adapter<TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.textView.text = dataList[position]
//        holder.imageView.setImageResource()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}