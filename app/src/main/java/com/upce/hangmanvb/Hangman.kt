package com.upce.hangmanvb

import java.util.*

class Hangman(var word: String) {
    private val livesMax: Int = 6
    private var guessedArray = BooleanArray(word.length) { _ -> false }
    var lives: Int = livesMax

    init {
        word = word.toUpperCase(Locale.ROOT).trim().replace("\\s+".toRegex(), " ")
        revealWhiteSpace()
    }

    /**
     * Resetuje atributy třídy a nahradí původníá slovo za nové
     */
    fun reset(word: String) {
        this.word = word.toUpperCase(Locale.ROOT).trim().replace("\\s+".toRegex(), " ")
        lives = livesMax
        guessedArray = BooleanArray(word.length) { _ -> false }
        revealWhiteSpace()
    }

    /**
     * Hádání písmena/písmen ve slově
     */
    fun guess(letter: CharArray) {
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
                word[i].toUpperCase() + " "
            } else {
                "$ch "
            }
        }
        return text
    }

    private fun revealWhiteSpace() {
        for (i in word.indices) {
            if (word[i].isWhitespace()) {
                guessedArray[i] = true
            }
        }
    }
}