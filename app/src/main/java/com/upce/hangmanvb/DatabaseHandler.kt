package com.upce.hangmanvb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.core.database.getStringOrNull

const val DATABASE_NAME = "MyDB"
const val TABLE_NAME = "WORDS"
const val COL_WORD = "word"
const val COL_ID = "id"


class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_WORD + " VARCHAR(265))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(word: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_WORD, word)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<String> {
        val list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val idTemp = result.getString(result.getColumnIndex(COL_ID))
                val wordTemp = result.getString(result.getColumnIndex(COL_WORD))
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
                return it.getString(it.getColumnIndex(COL_WORD))
            }

        }

        db.close()
        return ""
    }

    fun deleteLast() {
        val db = this.readableDatabase
        db.delete(TABLE_NAME, "$COL_ID = (SELECT MAX($COL_ID) FROM $TABLE_NAME)", null) > 0
    }

    fun delete(s: String): Int {
        val db = this.readableDatabase
        return try {
            val result = db.delete(TABLE_NAME, "$COL_WORD LIKE '$s'", null)
            if (result < 1) {
                db.delete(TABLE_NAME, "$COL_ID=$s", null)
            } else {
                result
            }
        } catch (e: SQLiteException) {
            -1
        }
    }
}