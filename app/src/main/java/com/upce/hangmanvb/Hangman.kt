package com.upce.hangmanvb

import android.widget.ImageView
import android.widget.TextView

class Hangman(private val word: String) {
    var lives: Int = 7
    var guessedArray = BooleanArray(word.length) { _ -> false }

    fun render(imgView: ImageView, textView: TextView) {
        //Vygenerovat obrázek

        //Vygenerovat uhádnutá písmena
        textView.setText(getTextForTextView())
    }

    fun guess(vararg letter: Char) {
        var letterGuessed = false
        for (i in word.indices) {
            if (word[i].equals(letter)) {
                guessedArray[i] = true
                if (!letterGuessed) letterGuessed = true
            }
        }
        if (!letterGuessed) lives--
    }

    private fun getTextForTextView(): String {
        var text = ""
        for (i in 0..guessedArray.size) {
            if (guessedArray[i]) {
                text += word[i]
            } else {
                text += "_"
            }
        }
        return text
    }
}