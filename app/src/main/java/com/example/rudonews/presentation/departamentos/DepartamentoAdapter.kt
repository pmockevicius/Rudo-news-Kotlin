package com.example.rudonews.presentation.departamentos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Departament


class DepartamentoAdapter(private val departamentos: List<Departament>) : RecyclerView.Adapter<DepartamentoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartamentoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_departamento, parent, false)
        return DepartamentoViewHolder(itemView, departamentos)
    }

    override fun onBindViewHolder(holder: DepartamentoViewHolder, position: Int) {
        val departamento = departamentos[position]
        holder.departamentoNameTextView.text = departamento.deptName
        holder.checkBox.isChecked = departamento.isChecked
    }

    override fun getItemCount(): Int {
        return departamentos.size
    }
}

