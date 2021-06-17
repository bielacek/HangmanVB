package com.upce.hangmanvb

import android.widget.ImageView
import android.widget.TextView

class Hangman(private val word: String) {
    var lives: Int = 6
    var guessedArray = BooleanArray(word.length) { _ -> false }

    fun render(imgView: ImageView?, textView: TextView) {
        //TODO nakopírovat obrázky a nastavit metodu aby vykreslila příslušný dle počtu životů
        //Vygenerovat obrázek
        //imgView.setImageResource()
        //Vygenerovat uhádnutá písmena
        textView.setText(getTextForTextView() + " - ${lives}")
    }

    fun guess(vararg letter: Char) {
        var letterGuessed = false
        for (i in word.indices) {
            for(j in letter.indices){
                if (word[i].toLowerCase() == letter[j].toLowerCase()) {
                    guessedArray[i] = true
                    if (!letterGuessed) letterGuessed = true
                }
            }
        }
        if (!letterGuessed) lives--
    }

    private fun getTextForTextView(): String {
        var text = ""
        for (i in 0..guessedArray.size-1) {
            if (guessedArray[i]) {
                text += word[i]
            } else {
                text += "_ "
            }
        }
        return text
    }
}