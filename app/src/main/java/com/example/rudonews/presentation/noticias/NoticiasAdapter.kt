package com.example.rudonews.presentation.noticias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R


class NoticiasAdapter(private val dataList: List<String>) : RecyclerView.Adapter<NoticiasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        holder.textView.text = dataList[position]
//        holder.imageView.setImageResource()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}