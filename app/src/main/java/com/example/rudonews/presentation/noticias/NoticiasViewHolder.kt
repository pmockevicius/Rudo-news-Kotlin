package com.example.rudonews.presentation.noticias

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R

class NoticiasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tagText: TextView = itemView.findViewById(R.id.tagText)
    val title: TextView = itemView.findViewById(R.id.noticiaCardTitle)
    val imageView: ImageView = itemView.findViewById(R.id.noticiaImageView)
    val notciaDate: TextView = itemView.findViewById(R.id.noticiaPostedDate)
    val description: TextView = itemView.findViewById(R.id.noticiaDescription)
}