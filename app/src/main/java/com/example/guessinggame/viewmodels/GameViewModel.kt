package com.example.guessinggame.viewmodels

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.guessinggame.R

class GameViewModel() : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment", "Binding", "Safeargs")
    var secretWord = words.random().uppercase()
    var secretWordDisplay = "" // word to be displayed
    var correctGuesses = "" // will hold correct letters in the form of string
    var incorrectGuesses = ""
    var livesLeft = 8

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }
    private fun deriveSecretWordDisplay() : String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    private fun checkLetter(letter : String) : String {
        return when(correctGuesses.contains(letter)) {
            true -> letter
            false -> "_"
        }
    }

    fun checkGuess(guess : String) {
        if(guess.length == 1) {
            if(secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            }else {
                livesLeft--
                incorrectGuesses += "$guess "
            }
        }
    }

    fun isWon() : Boolean {
        return secretWordDisplay == secretWord
    }

    fun isLost() : Boolean {
        return livesLeft == 0
    }

    fun wonLostMessage() : String {
        return if(isWon()) {
            // this is wrong!!
//            Resources.getSystem().getString(R.string.won_message, secretWord)
            "Won"
        } else {
//            Resources.getSystem().getString(R.string.lost_message, secretWord)
            "Lost"
        }
    }
}