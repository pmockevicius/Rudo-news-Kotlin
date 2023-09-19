package com.example.rudonews.activities.DetailsActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Comment
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.Tag


class ComentsAdapter(private val comments: List<Comment>?) : RecyclerView.Adapter<CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        comments?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return comments?.size ?: 0
    }
}