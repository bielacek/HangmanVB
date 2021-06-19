package com.upce.hangmanvb

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var hangman: Hangman
    private val textViewWord by lazy { findViewById<TextView>(R.id.textViewWord) }
    private val textViewEdit by lazy { findViewById<TextView>(R.id.editTextWord) }
    private val imageViewHangman by lazy { findViewById<ImageView>(R.id.imageViewHangman) }
    private val layoutKeyboard by lazy { findViewById<LinearLayout>(R.id.layoutKeyboard) }
    private lateinit var builder: AlertDialog.Builder

    private val context = this
    private var db = DatabaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonListeners()

        // Tlačítko na odeslání dat do db
        findViewById<Button>(R.id.buttonInsert).setOnClickListener {
            val text = textViewEdit.text.toString().trim().replace("\\s+".toRegex(), " ")
            if (text.isNotEmpty() && text.length < 35) {
                db.delete(text)
                db.insertData(text)
                textViewEdit.text = ""
            } else {
                Toast.makeText(context, "Slovo se nepodařilo přidat", Toast.LENGTH_SHORT).show()
            }
            showData()
        }

        //Tlačítko na smazání dat z db - pokud je textView prázdné, smaže se poslední vložený řádek
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            val text = textViewEdit.text.toString().trim().replace("\\s+".toRegex(), " ")
            if (text.isNotEmpty()) {
                val result = db.delete(text)
                if (result > 0) {
                    Toast.makeText(context, "Smazáno $result řádků", Toast.LENGTH_SHORT).show()
                    textViewEdit.text = ""
                } else {
                    Toast.makeText(context, "Data nenalezena", Toast.LENGTH_SHORT).show()
                }
            } else {
                db.deleteLast()
            }
            showData()
        }

        // Vložení slov do databáze, pokud je databáze prázdná
        //if(db.getRandom().isEmpty()){
        //    val filePath = "podstatna_jmena.txt"
        //    File(filePath).forEachLine { db.insertData(it) }
        //}


        //Inicializace třídy hangman
        val wordtemp = db.getRandom()
        hangman = if (wordtemp.isNotEmpty()) {
            Hangman(wordtemp)
        } else {
            Hangman("slovo")
        }

        // Změna barvy textu
        textViewWord.setTextColor(Color.parseColor("black"))

        // Nastavení dialogu při výhře/prohře
        builder = AlertDialog.Builder(this)
        with(builder) {
            setCancelable(false)
            val positiveButtonClick = { _: DialogInterface, _: Int ->
                reset()
            }
            setPositiveButton(
                "Nová hra",
                DialogInterface.OnClickListener(function = positiveButtonClick)
            )
        }
        render(imageViewHangman, textViewWord)
        showData()
    }

    private fun showData() {
        val data = db.readData()
        findViewById<TextView>(R.id.textViewResult).text = ""
        for (i in 0 until data.size) {
            findViewById<TextView>(R.id.textViewResult).append(data[i] + "\n")
        }
    }


    /**
     * Resetování hry a obnovení vypnutých tlačítek
     */
    private fun reset() {
        Toast.makeText(applicationContext, "Hra byla restartována", Toast.LENGTH_SHORT).show()
        val wordtemp = db.getRandom()
        if (wordtemp.isNotEmpty()) {
            hangman.reset(wordtemp)
            render(imageViewHangman, textViewWord)
            layoutKeyboard.forEach { child ->
                (child as LinearLayout).forEach { btn ->
                    btn.background.alpha = 255
                    btn.isEnabled = true
                }
            }
        } else {
            Toast.makeText(applicationContext, "Databáze slov je prázdná", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * Vykreslení obrázku dle počtu životů a textu dle již uhodnutých písmen
     */
    private fun render(imgView: ImageView, textView: TextView) {
        textView.text = hangman.getWordFormated('_')
        if (hangman.wordCompleted()) {
            builder.setTitle("Výhra")
            builder.setMessage("Správně bylo \"${hangman.word.toUpperCase(Locale.ROOT)}\"")
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
                    builder.setMessage("Správně bylo \"${hangman.word.toUpperCase(Locale.ROOT)}\"")
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
        setListener(findViewById(R.id.buttonA), 'a', 'á')
        setListener(findViewById(R.id.buttonB), 'b')
        setListener(findViewById(R.id.buttonC), 'c', 'č')
        setListener(findViewById(R.id.buttonD), 'd', 'ď')
        setListener(findViewById(R.id.buttonE), 'e', 'ě', 'é')
        setListener(findViewById(R.id.buttonF), 'f')
        setListener(findViewById(R.id.buttonG), 'g')
        setListener(findViewById(R.id.buttonH), 'h')
        setListener(findViewById(R.id.buttonI), 'i', 'í')
        setListener(findViewById(R.id.buttonJ), 'j')
        setListener(findViewById(R.id.buttonK), 'k')
        setListener(findViewById(R.id.buttonL), 'l')
        setListener(findViewById(R.id.buttonM), 'm')
        setListener(findViewById(R.id.buttonN), 'n', 'ň')
        setListener(findViewById(R.id.buttonO), 'o', 'ó')
        setListener(findViewById(R.id.buttonP), 'p')
        setListener(findViewById(R.id.buttonQ), 'q')
        setListener(findViewById(R.id.buttonR), 'r', 'ř')
        setListener(findViewById(R.id.buttonS), 's', 'š')
        setListener(findViewById(R.id.buttonT), 't', 'ť')
        setListener(findViewById(R.id.buttonU), 'u', 'ú', 'ů')
        setListener(findViewById(R.id.buttonV), 'v')
        setListener(findViewById(R.id.buttonW), 'w')
        setListener(findViewById(R.id.buttonX), 'x')
        setListener(findViewById(R.id.buttonY), 'y', 'ý')
        setListener(findViewById(R.id.buttonZ), 'z', 'ž')
    }

    private fun setListener(btn: Button, vararg letter: Char) {
        btn.setOnClickListener {
            hangman.guess(letter)
            btnHandler(btn)
        }
    }

    private fun btnHandler(btn: Button) {
        render(imageViewHangman, textViewWord)
        btn.isEnabled = false
        btn.background.alpha = 0
    }
}