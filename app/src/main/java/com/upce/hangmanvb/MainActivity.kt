package com.upce.hangmanvb

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.forEach

class MainActivity : AppCompatActivity() {

    //TODO Napojit na databázi (přidat do databáze slova z .txt)
    var hangman: Hangman = Hangman("Analfabet")
    private val textViewWord by lazy { findViewById<TextView>(R.id.textViewWord) }
    private val imageViewHangman by lazy { findViewById<ImageView>(R.id.imageViewHangman) }
    private val layoutKeyboard by lazy { findViewById<LinearLayout>(R.id.layoutKeyboard) }
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonListeners()
        textViewWord.setTextColor(Color.parseColor("black"));
        builder = AlertDialog.Builder(this)
        with(builder) {
            setMessage("Slovo bylo \"${hangman.word.toUpperCase()}\"")
            setCancelable(false)
            val positiveButtonClick = { _: DialogInterface, _: Int ->
                reset()
            }
            setPositiveButton(
                "Nová hra",
                DialogInterface.OnClickListener(function = positiveButtonClick)
            )
        }
    }

    private fun btnHandler(btn: Button) {
        render(imageViewHangman, textViewWord)
        btn.isEnabled = false
        btn.background.alpha = 0
    }

    /**
     * Resetování hry a obnovení vypnutých tlačítek
     */
    private fun reset() {
        Toast.makeText(applicationContext, "Hra byla restartována", Toast.LENGTH_SHORT).show()
        //TODO Změnit na náhodné slovo z databáze
        hangman.reset("tkanička")
        render(imageViewHangman, textViewWord)
        layoutKeyboard.forEach { child ->
            (child as LinearLayout).forEach { btn ->
                btn.background.alpha = 255
                btn.isEnabled = true
            }
        }
    }

    /**
     * Vykreslení obrázku dle počtu životů a textu dle již uhodnutých písmen
     */
    private fun render(imgView: ImageView, textView: TextView) {
        textView.text = hangman.getWordFormated('_')
        if (hangman.wordCompleted()) {
            builder.setTitle("Výhra")
            builder.show()
        } else {
            when (hangman.lives) {
                6 -> imgView.setBackgroundResource(R.drawable.lives6)
                5 -> imgView.setBackgroundResource(R.drawable.lives5)
                4 -> imgView.setBackgroundResource(R.drawable.lives4)
                3 -> imgView.setBackgroundResource(R.drawable.lives3)
                2 -> imgView.setBackgroundResource(R.drawable.lives2)
                1 -> imgView.setBackgroundResource(R.drawable.lives1)
                0 -> {
                    imgView.setBackgroundResource(R.drawable.lives0)
                    builder.setTitle("Prohra")
                    builder.show()
                }
            }
        }
    }

    /**
     * Nastavení funkcí tlačítek
     */
    private fun setButtonListeners() {
        findViewById<ImageButton>(R.id.buttonReset).setOnClickListener {
            reset()
        }
        findViewById<Button>(R.id.buttonA).setOnClickListener {
            hangman.guess('a', 'á')
            btnHandler(findViewById(R.id.buttonA))
        }
        findViewById<Button>(R.id.buttonB).setOnClickListener {
            hangman.guess('b')
            btnHandler(findViewById(R.id.buttonB))
        }
        findViewById<Button>(R.id.buttonC).setOnClickListener {
            hangman.guess('c', 'č')
            btnHandler(findViewById(R.id.buttonC))
        }
        findViewById<Button>(R.id.buttonD).setOnClickListener {
            hangman.guess('d', 'ď')
            btnHandler(findViewById(R.id.buttonD))
        }
        findViewById<Button>(R.id.buttonE).setOnClickListener {
            hangman.guess('e', 'ě', 'é')
            btnHandler(findViewById(R.id.buttonE))
        }
        findViewById<Button>(R.id.buttonF).setOnClickListener {
            hangman.guess('f')
            btnHandler(findViewById(R.id.buttonF))
        }
        findViewById<Button>(R.id.buttonG).setOnClickListener {
            hangman.guess('g')
            btnHandler(findViewById(R.id.buttonG))
        }
        findViewById<Button>(R.id.buttonH).setOnClickListener {
            hangman.guess('h')
            btnHandler(findViewById(R.id.buttonH))
        }
        findViewById<Button>(R.id.buttonI).setOnClickListener {
            hangman.guess('i', 'í')
            btnHandler(findViewById(R.id.buttonI))
        }
        findViewById<Button>(R.id.buttonJ).setOnClickListener {
            hangman.guess('j')
            btnHandler(findViewById(R.id.buttonJ))
        }
        findViewById<Button>(R.id.buttonK).setOnClickListener {
            hangman.guess('k')
            btnHandler(findViewById(R.id.buttonK))
        }
        findViewById<Button>(R.id.buttonL).setOnClickListener {
            hangman.guess('l')
            btnHandler(findViewById(R.id.buttonL))
        }
        findViewById<Button>(R.id.buttonM).setOnClickListener {
            hangman.guess('m')
            btnHandler(findViewById(R.id.buttonM))
        }
        findViewById<Button>(R.id.buttonN).setOnClickListener {
            hangman.guess('n', 'ň')
            btnHandler(findViewById(R.id.buttonN))
        }
        findViewById<Button>(R.id.buttonO).setOnClickListener {
            hangman.guess('o', 'ó')
            btnHandler(findViewById(R.id.buttonO))
        }
        findViewById<Button>(R.id.buttonP).setOnClickListener {
            hangman.guess('p')
            btnHandler(findViewById(R.id.buttonP))
        }
        findViewById<Button>(R.id.buttonQ).setOnClickListener {
            hangman.guess('q')
            btnHandler(findViewById(R.id.buttonQ))
        }
        findViewById<Button>(R.id.buttonR).setOnClickListener {
            hangman.guess('r', 'ř')
            btnHandler(findViewById(R.id.buttonR))
        }
        findViewById<Button>(R.id.buttonS).setOnClickListener {
            hangman.guess('s', 'š')
            btnHandler(findViewById(R.id.buttonS))
        }
        findViewById<Button>(R.id.buttonT).setOnClickListener {
            hangman.guess('t', 'ť')
            btnHandler(findViewById(R.id.buttonT))
        }
        findViewById<Button>(R.id.buttonU).setOnClickListener {
            hangman.guess('u', 'ů', 'ú')
            btnHandler(findViewById(R.id.buttonU))
        }
        findViewById<Button>(R.id.buttonV).setOnClickListener {
            hangman.guess('v')
            btnHandler(findViewById(R.id.buttonV))
        }
        findViewById<Button>(R.id.buttonW).setOnClickListener {
            hangman.guess('w')
            btnHandler(findViewById(R.id.buttonW))
        }
        findViewById<Button>(R.id.buttonX).setOnClickListener {
            hangman.guess('X')
            btnHandler(findViewById(R.id.buttonX))
        }
        findViewById<Button>(R.id.buttonY).setOnClickListener {
            hangman.guess('y', 'ý')
            btnHandler(findViewById(R.id.buttonY))
        }
        findViewById<Button>(R.id.buttonZ).setOnClickListener {
            hangman.guess('z', 'ž')
            btnHandler(findViewById(R.id.buttonZ))
        }
    }
}