package com.example.rudonews.activities.DetailsActivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rudonews.R
import com.example.rudonews.databinding.ActivityDetailsBinding
import com.example.rudonews.domain.entity.News


class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var navBarTextView: TextView
    private lateinit var currentNoticia: News
    private lateinit var commentInput: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navBarTextView = binding.detailsNavBarTextView
        commentInput = binding.detailsCommentEditText


        onView()
        bind()
    }

    private fun initNavBarBackListener() {
        val navBarBackArrow: ImageView = binding.DetailsNavBarBackArrow
        navBarBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun onView() {
        receiveExtras()
        setNavBarText()
        initNavBarBackListener()
        initCommentsRecyclerView()
        initShareClickListenerInDetails()
        initFavoritesClickListenerInDetails()
    }


    private fun setNavBarText() {
        navBarTextView.text = currentNoticia.title
    }

    fun receiveExtras() {
        val intent = intent
        currentNoticia = intent.getSerializableExtra("currentNoticia") as News

    }

    fun bind() {
        binding.detailsDate.text = currentNoticia.date
        binding.detailsTitle.text = currentNoticia.title
        binding.detailsDescription.text = currentNoticia.description
        binding.detailsBody.text = currentNoticia.body

        Glide.with(this)
            .load(currentNoticia.image)
            .into(binding.detailsImageView)

        val imageResource = if (currentNoticia.isFavorite) {
            R.drawable.icon_favorite_selected
        } else {
            R.drawable.icon_favorite
        }
        binding.favoriteInNavbar.setImageResource(imageResource)
    }



    private  fun initCommentsRecyclerView() {
        val recyclerView: RecyclerView = binding.commentsRecyclerView
        val comments = currentNoticia.comments
        val commensAdapter = ComentsAdapter(comments)

        recyclerView.adapter = commensAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initShareClickListenerInDetails(){
        binding.shareInNavbar.setOnClickListener{
            val shareData = Intent(Intent.ACTION_SEND)
            shareData.type = "text/plain"
            val dataToShare = currentNoticia.body
            shareData.putExtra(Intent.EXTRA_SUBJECT, "Subject from my application")
            shareData.putExtra(Intent.EXTRA_TEXT, dataToShare)

            val context = this
            context.startActivity(Intent.createChooser(shareData, "Share Via"))
        }
    }


    private fun initFavoritesClickListenerInDetails(){
        binding.favoriteInNavbar.setOnClickListener {
            currentNoticia.isFavorite = !currentNoticia.isFavorite

            val imageResource = if(currentNoticia.isFavorite){
                R.drawable.icon_favorite_selected
            } else {
                R.drawable.icon_favorite
            }

            binding.favoriteInNavbar.setImageResource(imageResource)
        }

    }

    private fun initCommentOnTextChangeListener(){

        commentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val drawableColorResource = if (s.isNullOrEmpty()){
                    R.color.Grey
                }else {
                    R.color.Grey
                }

                if (s.isNullOrEmpty()){
                commentInput.
                )
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

    }
}
