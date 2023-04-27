package com.example.guessinggame.viewmodels

import androidx.lifecycle.ViewModel

class GiveTextViewModel(guessWord : String) : ViewModel() {
    private val _wordToBeGuessed = guessWord
    val wordToBeGuessed get() = _wordToBeGuessed
}