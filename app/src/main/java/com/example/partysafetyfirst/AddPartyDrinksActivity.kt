package com.example.partysafetyfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_party_drinks.*


class AddPartyDrinksActivity : AppCompatActivity() {

    private var drinks = mutableListOf(Alcohol("Red Wine", "Rojo", "half of glass", "13%"),
        Alcohol("Tequila", "Jimador","shot", "40%"),
        Alcohol("Whiskey", "Very smelly","glass", "38%"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_party_drinks)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        findViewById<TextView>(R.id.textView).apply {
            text = message
        }

        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = MyAdapter(drinks)
        }

    fun newDrink(view: View){
        //add new drink to database
        drinks.add(Alcohol("Vodka", "Love", "shot", "42%"))
        my_recycler_view.adapter = MyAdapter(drinks)

        val intent = Intent(this, NewDrinkActivity::class.java)
        startActivity(intent)

    }

    }