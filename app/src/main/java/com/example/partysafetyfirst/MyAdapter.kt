package com.example.partysafetyfirst

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val drinks: List<Alcohol>) :
    RecyclerView.Adapter<AlcoholViewHolder>() {

    override fun onBindViewHolder(holder: AlcoholViewHolder, position: Int) {
        val alcohol : Alcohol = drinks[position]
        holder.bind(alcohol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoholViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlcoholViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = drinks.size

}
