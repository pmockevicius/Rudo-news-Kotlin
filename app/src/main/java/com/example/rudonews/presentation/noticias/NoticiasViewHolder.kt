package com.example.rudonews.presentation.noticias

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rudonews.activities.DetailsActivity.DetailsActivity
import com.example.rudonews.activities.MainActivity
import com.example.rudonews.R
import com.example.rudonews.domain.entity.News


class NoticiasViewHolder(itemView: View, private val mainActivity: MainActivity) : RecyclerView.ViewHolder(itemView) {
    val tagText: TextView = itemView.findViewById(R.id.tagText)
    val title: TextView = itemView.findViewById(R.id.noticiaCardTitle)
    val imageView: ImageView = itemView.findViewById(R.id.noticiaImageView)
    val notciaDate: TextView = itemView.findViewById(R.id.noticiaPostedDate)
    val description: TextView = itemView.findViewById(R.id.noticiaDescription)
    val favoritesImageView: ImageView = itemView.findViewById(R.id.favoriteImageView)
    val shareImageView : ImageView = itemView.findViewById(R.id.shareImageView)
    val noticiaCardView: CardView = itemView.findViewById(R.id.noticiaCardView)
    val titleOverImage : TextView = itemView.findViewById(R.id.noticiaTitleOverImage)


    fun bind(currentNoticia:News) {

        title.text = currentNoticia.title
        notciaDate.text = currentNoticia.date
        description.text = currentNoticia.description
        tagText.text = currentNoticia.tag
        titleOverImage.text = currentNoticia.title

        if (currentNoticia.isFavorite){
            favoritesImageView.setImageResource(R.drawable.icon_favorite_selected)
        } else {
            favoritesImageView.setImageResource(R.drawable.icon_favorite)
        }

        Glide.with(itemView)
            .load(currentNoticia.image)
            .into(imageView)



        favoritesImageView.setOnClickListener {


            val imageResource = if (currentNoticia.isFavorite) {
                R.drawable.icon_favorite
            } else {
                R.drawable.icon_favorite_selected
            }

            favoritesImageView.setImageResource(imageResource)
            currentNoticia.isFavorite = !currentNoticia.isFavorite

            println("is favorite? ${currentNoticia.isFavorite}")

        }


        shareImageView.setOnClickListener{
            val shareData = Intent(Intent.ACTION_SEND)
            shareData.type = "text/plain"
            val dataToShare = currentNoticia.body
            shareData.putExtra(Intent.EXTRA_SUBJECT, "Subject from my application")
            shareData.putExtra(Intent.EXTRA_TEXT, dataToShare)

            val context = itemView.context
            context.startActivity(Intent.createChooser(shareData, "Share Via"))
        }


        noticiaCardView.setOnClickListener {
//            mainActivity.navigateToDetailsActivity(currentNoticia)

            val context = itemView.context
            val intent = Intent(context, DetailsActivity::class.java) // Replace NoticiasActivity with the actual name of your target activity
            intent.putExtra("currentNoticia", currentNoticia)
            context.startActivity(intent)
        }

        }


    }
