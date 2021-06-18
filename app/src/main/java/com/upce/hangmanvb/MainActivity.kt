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
    private val buttonReset by lazy { findViewById<ImageButton>(R.id.buttonReset) }
    private val buttonA by lazy { findViewById<Button>(R.id.buttonA) }
    private val buttonB by lazy { findViewById<Button>(R.id.buttonB) }
    private val buttonC by lazy { findViewById<Button>(R.id.buttonC) }
    private val buttonD by lazy { findViewById<Button>(R.id.buttonD) }
    private val buttonE by lazy { findViewById<Button>(R.id.buttonE) }
    private val buttonF by lazy { findViewById<Button>(R.id.buttonF) }
    private val buttonG by lazy { findViewById<Button>(R.id.buttonG) }
    private val buttonH by lazy { findViewById<Button>(R.id.buttonH) }
    private val buttonI by lazy { findViewById<Button>(R.id.buttonI) }
    private val buttonJ by lazy { findViewById<Button>(R.id.buttonJ) }
    private val buttonK by lazy { findViewById<Button>(R.id.buttonK) }
    private val buttonL by lazy { findViewById<Button>(R.id.buttonL) }
    private val buttonM by lazy { findViewById<Button>(R.id.buttonM) }
    private val buttonN by lazy { findViewById<Button>(R.id.buttonN) }
    private val buttonO by lazy { findViewById<Button>(R.id.buttonO) }
    private val buttonP by lazy { findViewById<Button>(R.id.buttonP) }
    private val buttonQ by lazy { findViewById<Button>(R.id.buttonQ) }
    private val buttonR by lazy { findViewById<Button>(R.id.buttonR) }
    private val buttonS by lazy { findViewById<Button>(R.id.buttonS) }
    private val buttonT by lazy { findViewById<Button>(R.id.buttonT) }
    private val buttonU by lazy { findViewById<Button>(R.id.buttonU) }
    private val buttonV by lazy { findViewById<Button>(R.id.buttonV) }
    private val buttonW by lazy { findViewById<Button>(R.id.buttonW) }
    private val buttonX by lazy { findViewById<Button>(R.id.buttonX) }
    private val buttonY by lazy { findViewById<Button>(R.id.buttonY) }
    private val buttonZ by lazy { findViewById<Button>(R.id.buttonZ) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonReset.setOnClickListener {
            reset()
        }
        buttonA.setOnClickListener {
            hangman.guess('a', 'á')
            btnHandler(buttonA)
        }
        buttonB.setOnClickListener {
            hangman.guess('b')
            btnHandler(buttonB)
        }
        buttonC.setOnClickListener {
            hangman.guess('c', 'č')
            btnHandler(buttonC)
        }
        buttonD.setOnClickListener {
            hangman.guess('d', 'ď')
            btnHandler(buttonD)
        }
        buttonE.setOnClickListener {
            hangman.guess('e', 'ě', 'é')
            btnHandler(buttonD)
        }
        buttonF.setOnClickListener {
            hangman.guess('f')
            btnHandler(buttonD)
        }
        buttonG.setOnClickListener {
            hangman.guess('g')
            btnHandler(buttonD)
        }
        buttonH.setOnClickListener {
            hangman.guess('h')
            btnHandler(buttonD)
        }
        buttonI.setOnClickListener {
            hangman.guess('i', 'í')
            btnHandler(buttonD)
        }
        buttonJ.setOnClickListener {
            hangman.guess('j')
            btnHandler(buttonD)
        }
        buttonK.setOnClickListener {
            hangman.guess('k')
            btnHandler(buttonD)
        }
        buttonL.setOnClickListener {
            hangman.guess('l')
            btnHandler(buttonD)
        }
        buttonM.setOnClickListener {
            hangman.guess('m')
            btnHandler(buttonD)
        }
        buttonN.setOnClickListener {
            hangman.guess('n', 'ň')
            btnHandler(buttonD)
        }
        buttonO.setOnClickListener {
            hangman.guess('o', 'ó')
            btnHandler(buttonD)
        }
        buttonP.setOnClickListener {
            hangman.guess('p')
            btnHandler(buttonD)
        }
        buttonQ.setOnClickListener {
            hangman.guess('q')
            btnHandler(buttonD)
        }
        buttonR.setOnClickListener {
            hangman.guess('r', 'ř')
            btnHandler(buttonD)
        }
        buttonS.setOnClickListener {
            hangman.guess('s','š')
            btnHandler(buttonD)
        }
        buttonT.setOnClickListener {
            hangman.guess('t','ť')
            btnHandler(buttonD)
        }
        buttonU.setOnClickListener {
            hangman.guess('u','ů','ú')
            btnHandler(buttonD)
        }
        buttonV.setOnClickListener {
            hangman.guess('v')
            btnHandler(buttonD)
        }
        buttonW.setOnClickListener {
            hangman.guess('w')
            btnHandler(buttonD)
        }
        buttonX.setOnClickListener {
            hangman.guess('X')
            btnHandler(buttonD)
        }
        buttonY.setOnClickListener {
            hangman.guess('y','ý')
            btnHandler(buttonD)
        }
        buttonZ.setOnClickListener {
            hangman.guess('z','ž')
            btnHandler(buttonD)
        }


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


}