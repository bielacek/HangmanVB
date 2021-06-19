package com.upce.hangmanvb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.core.database.getStringOrNull

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "WORDS"
val COL_WORD = "word"
val COL_ID = "id"


class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_WORD + " VARCHAR(265))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(word: String) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_WORD, word)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<String> {
        var list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var idTemp = result.getString(result.getColumnIndex(COL_ID))
                var wordTemp = result.getString(result.getColumnIndex(COL_WORD))
                list.add("$idTemp $wordTemp")
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun getRandom(): String {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY RANDOM() LIMIT 1"

        db.rawQuery(query, null).use {
            if (it.moveToFirst()) {
                val result = it.getString(it.getColumnIndex(COL_WORD))
                return result
            }

        }
        //var wordTemp = result.getString(result.getColumnIndex(COL_WORD))
        //var wordTemp = result.getString(result.getColumnIndex(COL_WORD))

        db.close()
        return ""
    }
    fun deleteLast(){
        val db = this.readableDatabase
        db.delete(TABLE_NAME, "$COL_ID = (SELECT MAX($COL_ID) FROM $TABLE_NAME)", null) > 0;
    }
}