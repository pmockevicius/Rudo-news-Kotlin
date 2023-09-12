package com.example.rudonews.presentation.departamentos

import android.view.View

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Departament
import com.google.android.material.checkbox.MaterialCheckBox


class DepartamentoViewHolder(itemView: View, private val departamentos: List<Departament>) : RecyclerView.ViewHolder(itemView) {
    val departamentoNameTextView: TextView = itemView.findViewById(R.id.departamentoNameTextView)
    val checkBox: MaterialCheckBox = itemView.findViewById(R.id.dptCheckBox)

    init {
        checkBox.setOnCheckedChangeListener { _, isChecked ->

            departamentos[adapterPosition].isChecked = isChecked

            if (isChecked) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.SoftPink))
            } else {
//                println("${departamentoNameTextView.text.toString()} checked")
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.AppBackground))
            }
        }
    }
}

