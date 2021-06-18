package com.upce.hangmanvb

class Hangman(var word: String) {
    private val livesMax: Int = 6
    private var guessedArray = BooleanArray(word.length) { _ -> false }
    var lives: Int = livesMax

    /**
     * Resetuje atributy třídy a nahradí původníá slovo za nové
     */
    fun reset(word: String) {
        this.word = word
        lives = livesMax
        guessedArray = BooleanArray(word.length) { _ -> false }
    }

    /**
     * Hádání písmena/písmen ve slově
     */
    fun guess(vararg letter: Char) {
        var letterGuessed = false
        for (i in word.indices) {
            for (j in letter.indices) {
                if (word[i].equals(letter[j], ignoreCase = true)) {
                    guessedArray[i] = true
                    if (!letterGuessed) letterGuessed = true
                }
            }
        }
        if (!letterGuessed) lives--
    }

    /**
     * Vrací true, pokud je slovo kompletně uhodnuto, jinak false
     */
    fun wordCompleted(): Boolean {
        for (i in guessedArray.indices) {
            if (!guessedArray[i]) return false
        }
        return true
    }

    /**
     * Vrací formátovaný text pro zobrazení např. v TextView
     * Každé neuhodnuté písmeno je nahrazeno písmenem v parametru
     */
    fun getWordFormated(ch: Char): String {
        var text = ""
        for (i in guessedArray.indices) {
            text += if (guessedArray[i]) {
                word[i].toLowerCase() + " "
            } else {
                "$ch "
            }
        }
        return text
    }

}