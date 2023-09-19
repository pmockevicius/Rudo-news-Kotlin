package com.example.rudonews.activities.DetailsActivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rudonews.databinding.ActivityDetailsBinding
import com.example.rudonews.domain.entity.Noticia


class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var navBarTextView: TextView
    private lateinit var currentNoticia: Noticia



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navBarTextView = binding.detailsNavBarTextView


        onView()
        bind()
    }

    private fun initNavBarBackListener(){
        val navBarBackArrow: ImageView = binding.DetailsNavBarBackArrow
        navBarBackArrow.setOnClickListener{
            finish()
        }
    }

    private fun onView() {
        receiveExtras()
        setNavBarText()
        initNavBarBackListener()
        initCommentsRecyclerView()
    }


    private fun setNavBarText() {
        navBarTextView.text = currentNoticia.title
    }

    fun receiveExtras(){
        val intent = intent
        currentNoticia = intent.getSerializableExtra("currentNoticia") as Noticia

    }

    fun bind(){
        binding.detailsDate.text = currentNoticia.date
        binding.detailsTitle.text = currentNoticia.title
        binding.detailsDescription.text = currentNoticia.description
        binding.detailsBody.text = currentNoticia.body

        Glide.with(this)
            .load(currentNoticia.image)
            .into(binding.detailsImageView)
    }

    private  fun initCommentsRecyclerView() {
        val recyclerView: RecyclerView = binding.commentsRecyclerView
        val comments = currentNoticia.comments
        val commensAdapter = ComentsAdapter(comments)

        recyclerView.adapter = commensAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }


}