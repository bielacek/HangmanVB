package com.upce.hangmanvb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    //TODO Reset hry - ikona hotova
    //TODO Skóre?
    //TODO Uživatel může přidat slovo do databáze
    //TODO Napojit na databázi (přidat do databáze slova z .txt)
    var hangman: Hangman = Hangman("Analfabet")

    //TODO Nakopírovat pro ostatní buttony + změnit písmena v guess
    private val textViewWord by lazy { findViewById<TextView>(R.id.textViewWord) }
    private val imageViewHangman by lazy { findViewById<ImageView>(R.id.imageViewHangman) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonsListeners()
        reset()
    }

    private fun btnHandler(btn: Button) {
        hangman.render(imageViewHangman, textViewWord)
        btn.isEnabled = false
        btn.background.alpha = 0
    }
    // nechal jsem to tady, predpokldáám že na databázi to bude napojený tady

    fun reset() {
        Toast.makeText(applicationContext, "reset", Toast.LENGTH_SHORT).show()
        hangman.newWord("tkanička")

    }

    private fun setButtonsListeners(){
        findViewById<ImageButton>(R.id.buttonReset).setOnClickListener {
            reset()
        }
        findViewById<Button>(R.id.buttonA).setOnClickListener {
            hangman.guess('a', 'á')
            btnHandler(findViewById<Button>(R.id.buttonA))
        }
        findViewById<Button>(R.id.buttonB).setOnClickListener {
            hangman.guess('b')
            btnHandler(findViewById<Button>(R.id.buttonB))
        }
        findViewById<Button>(R.id.buttonC).setOnClickListener {
            hangman.guess('c', 'č')
            btnHandler(findViewById<Button>(R.id.buttonC))
        }
        findViewById<Button>(R.id.buttonD).setOnClickListener {
            hangman.guess('d', 'ď')
            btnHandler(findViewById<Button>(R.id.buttonD))
        }
        findViewById<Button>(R.id.buttonE).setOnClickListener {
            hangman.guess('e', 'ě', 'é')
            btnHandler(findViewById<Button>(R.id.buttonE))
        }
        findViewById<Button>(R.id.buttonF).setOnClickListener {
            hangman.guess('f')
            btnHandler(findViewById<Button>(R.id.buttonF))
        }
        findViewById<Button>(R.id.buttonG).setOnClickListener {
            hangman.guess('g')
            btnHandler(findViewById<Button>(R.id.buttonG))
        }
        findViewById<Button>(R.id.buttonH).setOnClickListener {
            hangman.guess('h')
            btnHandler(findViewById<Button>(R.id.buttonH))
        }
        findViewById<Button>(R.id.buttonI).setOnClickListener {
            hangman.guess('i', 'í')
            btnHandler(findViewById<Button>(R.id.buttonI))
        }
        findViewById<Button>(R.id.buttonJ).setOnClickListener {
            hangman.guess('j')
            btnHandler(findViewById<Button>(R.id.buttonJ))
        }
        findViewById<Button>(R.id.buttonK).setOnClickListener {
            hangman.guess('k')
            btnHandler(findViewById<Button>(R.id.buttonK))
        }
        findViewById<Button>(R.id.buttonL).setOnClickListener {
            hangman.guess('l')
            btnHandler(findViewById<Button>(R.id.buttonL))
        }
        findViewById<Button>(R.id.buttonM).setOnClickListener {
            hangman.guess('m')
            btnHandler(findViewById<Button>(R.id.buttonM))
        }
        findViewById<Button>(R.id.buttonN).setOnClickListener {
            hangman.guess('n', 'ň')
            btnHandler(findViewById<Button>(R.id.buttonN))
        }
        findViewById<Button>(R.id.buttonO).setOnClickListener {
            hangman.guess('o', 'ó')
            btnHandler(findViewById<Button>(R.id.buttonO))
        }
        findViewById<Button>(R.id.buttonP).setOnClickListener {
            hangman.guess('p')
            btnHandler(findViewById<Button>(R.id.buttonP))
        }
        findViewById<Button>(R.id.buttonQ).setOnClickListener {
            hangman.guess('q')
            btnHandler(findViewById<Button>(R.id.buttonQ))
        }
        findViewById<Button>(R.id.buttonR).setOnClickListener {
            hangman.guess('r', 'ř')
            btnHandler(findViewById<Button>(R.id.buttonR))
        }
        findViewById<Button>(R.id.buttonS).setOnClickListener {
            hangman.guess('s','š')
            btnHandler(findViewById<Button>(R.id.buttonS))
        }
        findViewById<Button>(R.id.buttonT).setOnClickListener {
            hangman.guess('t','ť')
            btnHandler(findViewById<Button>(R.id.buttonT))
        }
        findViewById<Button>(R.id.buttonU).setOnClickListener {
            hangman.guess('u','ů','ú')
            btnHandler(findViewById<Button>(R.id.buttonU))
        }
        findViewById<Button>(R.id.buttonV).setOnClickListener {
            hangman.guess('v')
            btnHandler(findViewById<Button>(R.id.buttonV))
        }
        findViewById<Button>(R.id.buttonW).setOnClickListener {
            hangman.guess('w')
            btnHandler(findViewById<Button>(R.id.buttonW))
        }
        findViewById<Button>(R.id.buttonX).setOnClickListener {
            hangman.guess('X')
            btnHandler(findViewById<Button>(R.id.buttonX))
        }
        findViewById<Button>(R.id.buttonY).setOnClickListener {
            hangman.guess('y','ý')
            btnHandler(findViewById<Button>(R.id.buttonY))
        }
        findViewById<Button>(R.id.buttonZ).setOnClickListener {
            hangman.guess('z','ž')
            btnHandler(findViewById<Button>(R.id.buttonZ))
        }
    }
}