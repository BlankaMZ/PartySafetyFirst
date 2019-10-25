package com.example.partysafetyfirst

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_PARTY_TABLE)
        db?.execSQL(CREATE_ALCOHOL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Called when the database needs to be upgraded
    }

    //Inserting (Creating) data
    fun addParty(party: Party): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PARTY_NAME, party.name)
        val success = db.insert(PARTY_TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedParty", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    fun addAlcohol(partyName: String, alcohol: Alcohol) : Boolean {
        val partyId = findPartyId(partyName)
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PARTY_ID, partyId)
        values.put(ALCOHOL_TYPE, alcohol.type)
        values.put(ALCOHOL_NAME, alcohol.name)
        values.put(ALCOHOL_QUANTITY, alcohol.quantity)
        values.put(ALCOHOL_PERCENTAGE, alcohol.percentage)
        val success = db.insert(ALCOHOL_TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedAlcohol", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    private fun findPartyId(partyName: String) : Int {
        var partyId = 0
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $PARTY_TABLE_NAME WHERE $PARTY_NAME = $partyName"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    partyId = (cursor.getString(cursor.getColumnIndex(PARTY_ID)).toInt())
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return partyId
    }

    //get all users
    fun getAllParties(): MutableList<Party> {
        val allParties = mutableListOf<Party>()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $PARTY_TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val partyName = cursor.getString(cursor.getColumnIndex(PARTY_NAME))
                    allParties.add(Party(partyName))
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allParties
    }

    fun getAllAlcohols(partyName: String): MutableList<Alcohol> {
        val allAlcohols = mutableListOf<Alcohol>()
        val partyId = findPartyId(partyName)
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $ALCOHOL_TABLE_NAME WHERE $PARTY_ID = $partyId "
        val cursor = db.rawQuery(selectALLQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val alcoholType = cursor.getString(cursor.getColumnIndex(ALCOHOL_TYPE))
                    val alcoholName = cursor.getString(cursor.getColumnIndex(ALCOHOL_NAME))
                    val alcoholQuantity = cursor.getString(cursor.getColumnIndex(ALCOHOL_QUANTITY))
                    val alcoholPercentage = cursor.getString(cursor.getColumnIndex(
                        ALCOHOL_PERCENTAGE))
                    val alcohol = Alcohol(alcoholType, alcoholName, alcoholQuantity, alcoholPercentage)
                    allAlcohols.add(alcohol)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allAlcohols
    }

    companion object {
        private val DB_NAME = "PartyAlcoholDB"
        private val DB_VERSIOM = 1
        private val PARTY_TABLE_NAME = "users"
        private val ALCOHOL_TABLE_NAME = "alcohols"
        private val PARTY_ID = "partyId"
        private val PARTY_NAME = "PartyName"
        private val ALCOHOL_TYPE = "AlcoholType"
        private val ALCOHOL_NAME = "AlcoholName"
        private val ALCOHOL_QUANTITY = "AlcoholQuantity"
        private val ALCOHOL_PERCENTAGE = "AlcoholPercentage"
        val CREATE_PARTY_TABLE = "CREATE TABLE $PARTY_TABLE_NAME " +
                "($PARTY_ID Integer PRIMARY KEY AUTOINCREMENT, $PARTY_NAME TEXT)"
        val CREATE_ALCOHOL_TABLE = "CREATE TABLE $ALCOHOL_TABLE_NAME " +
                "($PARTY_ID Integer, $ALCOHOL_TYPE TEXT, $ALCOHOL_NAME TEXT, $ALCOHOL_QUANTITY TEXT, $ALCOHOL_PERCENTAGE TEXT)"
    }
}