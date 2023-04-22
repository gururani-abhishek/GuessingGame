package com.example.guessinggame.viewmodels

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessinggame.R

// what are the properties that are affecting my UI or are triggering some method calls in UI controller?
// : "secretWordDisplay", "incorrectGuesses", "livesLeft"

// to the properties that are being used in Fragment, I want them to be read-only.
// for that I can use backing property code structure.
class GameViewModel() : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment", "Binding", "Safeargs")
    private var _secretWord = words.random().uppercase()
    val secretWord get() = _secretWord

    private var correctGuesses = "" // will hold correct letters in the form of string

    private val _secretWordDisplay = MutableLiveData<String>() // word to be displayed
    val secretWordDisplay : LiveData<String> get()= _secretWordDisplay

    private val _incorrectGuesses = MutableLiveData("")
    val incorrectGuesses : LiveData<String> get() = _incorrectGuesses

    private val _livesLeft = MutableLiveData(8)
    val livesLeft : LiveData<Int> get() = _livesLeft

    private val _gameOver = MutableLiveData(false)
    val gameOver : LiveData<Boolean> get() = _gameOver

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }
    private fun deriveSecretWordDisplay() : String {
        var display = ""
        _secretWord.forEach {
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
            if(_secretWord.contains(guess)) {
                correctGuesses += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            }else {
                _livesLeft.value = livesLeft.value?.minus(1)
                _incorrectGuesses.value = incorrectGuesses.value?.plus("$guess ")
            }

            _gameOver.value = (isWon() || isLost())
        }
    }

    private fun isWon() : Boolean {
        return _secretWord.equals(_secretWordDisplay)
    }

    private fun isLost() : Boolean {
        return (_livesLeft.value ?: 0) <= 0
    }

    fun wonLostMessage() : String {
        return if(isWon()) {
            "Won"
        } else {
            "Lost"
        }
    }
}