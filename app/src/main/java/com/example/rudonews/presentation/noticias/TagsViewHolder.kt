package com.example.rudonews.presentation.noticias

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R

class TagsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.tagTextView)

}