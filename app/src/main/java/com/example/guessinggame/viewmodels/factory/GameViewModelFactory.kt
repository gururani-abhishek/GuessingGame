package com.example.guessinggame.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.guessinggame.viewmodels.GameViewModel

class GameViewModelFactory(private val guessWord: String) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(guessWord) as T
        throw IllegalArgumentException("unknown ViewModel")
    }
}