package com.example.partysafetyfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.Toast

class NewPartyActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_party)

        //init db
        dbHandler = DatabaseHandler(this)
    }

    fun newPartyDrinks(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()

        if(message.isNotEmpty()) {

            val party = Party(message)
            var success = false

            success = dbHandler!!.addParty(party)

            if (success){
                Toast.makeText(this,"Saved Successfully", Toast.LENGTH_LONG).show()
            }

            val intent = Intent(this, AddPartyDrinksActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Please! Enter name of the party!", Toast.LENGTH_SHORT).show()
        }

    }
}
