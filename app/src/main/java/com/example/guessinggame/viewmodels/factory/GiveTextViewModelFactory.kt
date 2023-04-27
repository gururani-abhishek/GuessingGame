package com.example.guessinggame.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.guessinggame.viewmodels.GiveTextViewModel
import com.example.guessinggame.viewmodels.ResultViewModel

class GiveTextViewModelFactory(private val guessWord : String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GiveTextViewModel::class.java))
            return GiveTextViewModel(guessWord) as T
        throw IllegalArgumentException("unknown ViewModel")
    }
}