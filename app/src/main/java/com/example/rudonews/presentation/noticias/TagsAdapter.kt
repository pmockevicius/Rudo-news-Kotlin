package com.example.rudonews.presentation.noticias

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Tag


class TagsAdapter(private val tagsList: List<Tag>, private val tagPressed: ((tag: String) -> Unit)) : RecyclerView.Adapter<TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.bind(tagsList[position], tagsList, tagPressed)
    }

    override fun getItemCount(): Int {
        return tagsList.size
    }
}