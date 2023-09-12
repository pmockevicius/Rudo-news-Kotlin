package com.example.rudonews.presentation.departamentos

import android.view.View

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.google.android.material.checkbox.MaterialCheckBox


class DepartamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val departamentoNameTextView: TextView = itemView.findViewById(R.id.departamentoNameTextView)
    val checkBox: MaterialCheckBox = itemView.findViewById(R.id.dptCheckBox)

    init {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Update the checked state in the data model
//            departamentos[adapterPosition].isChecked = isChecked

            if (isChecked) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.SoftPink))

            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.AppBackground))
            }
        }
    }
}

