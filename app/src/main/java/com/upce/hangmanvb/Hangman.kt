package com.upce.hangmanvb

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Hangman(private var word: String) {
    val livesMax: Int = 6
    var lives: Int = livesMax
    var guessedArray = BooleanArray(word.length) { _ -> false }

    fun render(imgView: ImageView?, textView: TextView) {
        //TODO nakopírovat obrázky a nastavit metodu aby vykreslila příslušný dle počtu životů
        //Vygenerovat obrázek
        //imgView.setImageResource()
        //Vygenerovat uhádnutá písmena
        textView.setText(getTextForTextView() + " - ${lives}")
        when (lives) {
            6 -> imgView?.setBackgroundResource(R.drawable.lives6)
            5 -> imgView?.setBackgroundResource(R.drawable.lives5)
            4 -> imgView?.setBackgroundResource(R.drawable.lives4)
            3 -> imgView?.setBackgroundResource(R.drawable.lives3)
            2 -> imgView?.setBackgroundResource(R.drawable.lives2)
            1 -> imgView?.setBackgroundResource(R.drawable.lives1)
            0 -> imgView?.setBackgroundResource(R.drawable.lives0)

        }
    }

    fun newWord(word: String) {
        this.word = word
        lives = livesMax
        guessedArray = BooleanArray(word.length) { _ -> false }

    }

    fun guess(vararg letter: Char) {
        var letterGuessed = false
        for (i in word.indices) {
            for (j in letter.indices) {
                if (word[i].toLowerCase() == letter[j].toLowerCase()) {
                    guessedArray[i] = true
                    if (!letterGuessed) letterGuessed = true
                }
            }
        }
        if (!letterGuessed) lives--
        if (lives == 0) {
            // game over
        }
    }

    private fun getTextForTextView(): String {
        var text = ""
        for (i in 0..guessedArray.size - 1) {
            if (guessedArray[i]) {
                text += word[i] + " "
            } else {
                text += "_ "
            }
        }
        return text
    }

}