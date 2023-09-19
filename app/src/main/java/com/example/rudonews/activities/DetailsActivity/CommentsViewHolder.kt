package com.example.rudonews.activities.DetailsActivity

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Comment
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.Tag
import com.google.android.material.card.MaterialCardView


class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



//    private val body: TextView = itemView.findViewById(R.id.commentBody)



    fun bind(comment: Comment) {

//        body.text = comment.body
    }
}