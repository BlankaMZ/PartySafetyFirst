package com.example.partysafetyfirst

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AlcoholViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.drink_layout, parent, false)) {
    private var mTypeView: TextView? = null
    private var mNameView: TextView? = null


    init {
        mTypeView = itemView.findViewById(R.id.list_title)
        mNameView = itemView.findViewById(R.id.list_description)
    }

    fun bind(alcohol: Alcohol) {
        mTypeView?.text = alcohol.quantity + " of " + alcohol.type
        mNameView?.text = alcohol.name + " (" + alcohol.percentage + ")"
    }

}