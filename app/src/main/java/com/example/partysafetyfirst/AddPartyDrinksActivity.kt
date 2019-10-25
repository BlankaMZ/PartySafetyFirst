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

    var dbHandler: DatabaseHandler? = null
    var allAlcohols = mutableListOf<Alcohol>()
    var partyName = ""

    private var drinks = mutableListOf(Alcohol("Red Wine", "Rojo", "half of glass", "13%"),
        Alcohol("Tequila", "Jimador","shot", "40%"),
        Alcohol("Whiskey", "Very smelly","glass", "38%"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_party_drinks)

        val partyName = intent.getStringExtra(EXTRA_MESSAGE)
        val partyNameString = partyName.toString()

        dbHandler = DatabaseHandler(this)
        allAlcohols = dbHandler!!.getAllAlcohols(partyNameString)

        // Capture the layout's TextView and set the string as its text
        findViewById<TextView>(R.id.textView).apply {
            text = partyName
        }

        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = MyAdapter(allAlcohols)
        }

    fun newDrink(view: View){
        //add new drink to database
        //drinks.add(Alcohol("Vodka", "Love", "shot", "42%"))
        //my_recycler_view.adapter = MyAdapter(drinks)

        dbHandler!!.addAlcohol(partyName, Alcohol("Vodka", "Love", "shot", "42%"))
        allAlcohols = dbHandler!!.getAllAlcohols(partyName)
        my_recycler_view.adapter = MyAdapter(allAlcohols)
       // val intent = Intent(this, NewDrinkActivity::class.java)
       // startActivity(intent)

    }

    }