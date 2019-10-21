package com.example.partysafetyfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.Toast

class NewPartyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_party)
    }

    fun newPartyDrinks(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()

        if(message.isNotEmpty()) {
            val intent = Intent(this, AddPartyDrinksActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Please! Enter name!", Toast.LENGTH_SHORT).show()
        }

    }
}
