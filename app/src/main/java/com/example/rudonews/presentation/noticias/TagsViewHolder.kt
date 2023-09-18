package com.example.rudonews.presentation.noticias

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Tag
import com.google.android.material.card.MaterialCardView


class TagsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tagTextView: TextView = itemView.findViewById(R.id.tagTextView)
    val tagCardView: MaterialCardView = itemView.findViewById(R.id.cardTag)
    val tagTextViewLayout: LinearLayout = itemView.findViewById(R.id.tagTextViewLayout)
    val tagCheckImage: ImageView = itemView.findViewById(R.id.tagCheckImage)


    fun bind(tag: Tag, datalist: List<Tag>, tagPressed: ((tag: String) -> Unit)) {
        // Bind data to views here


        tagTextView.text = tag.tag
        tagCheckImage.visibility =  View.GONE



        tagCardView.setOnClickListener {
            tag.isClicked = !tag.isClicked

            println("1 ${datalist.any { it.isClicked }}")
            tagPressed(tag.tag)

            if (tag.isClicked){
                tagTextViewLayout.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.fuciaTag))
                tagCheckImage.visibility =  View.VISIBLE
                tagCardView.strokeColor = ContextCompat.getColor(itemView.context, R.color.fucia)


            }else {
                val resolvedColor = ContextCompat.getColor(itemView.context, R.color.AppBackground)
                tagTextViewLayout.setBackgroundColor(resolvedColor)
                tagCheckImage.visibility =  View.GONE
                tagCardView.strokeColor = ContextCompat.getColor(itemView.context, R.color.Grey)

            }

        }
    }
}