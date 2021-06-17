package com.upce.hangmanvb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //TODO Reset hry
    //TODO Skóre?
    //TODO Uživatel může přidat slovo do databáze
    //TODO Napojit na databázi (přidat do databáze slova z .txt)
    val hangman:Hangman = Hangman("Analfabet")

    //TODO Nakopírovat pro ostatní buttony + změnit písmena v guess
    private val buttonA by lazy { findViewById<Button>(R.id.buttonA) }
    private val textViewWord by lazy { findViewById<TextView>(R.id.textViewWord) }
    private val imageViewHangman by lazy { findViewById<TextView>(R.id.imageViewHangman) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonA.setOnClickListener {
            hangman.guess('a','á')
            hangman.render(null,textViewWord)
            buttonA.isEnabled = false
            buttonA.background.alpha = 0
        }
    }

}