package com.example.guessinggame.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.example.guessinggame.R

class GameViewModel() : ViewModel() {
    val words = listOf("Android", "Activity", "Fragment", "Binding", "Safeargs")
    var secretWord = words.random().uppercase()
    var secretWordDisplay = "" // word to be displayed
    var correctGuesses = "" // will hold correct letters in the form of string
    var incorrectGuesses = ""
    var livesLeft = 8

    fun deriveSecretWordDisplay() : String {
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
                incorrectGuesses += guess
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
            Resources.getSystem().getString(R.string.won_message, secretWord)
        } else {
            Resources.getSystem().getString(R.string.lost_message, secretWord)
        }
    }

}