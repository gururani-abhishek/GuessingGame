package com.example.guessinggame.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.guessinggame.viewmodels.ResultViewModel

//this class will have a constructor that resembles the one your want for your custom view model
class ResultViewModelFactory(private val result : String) : ViewModelProvider.Factory {
    // this function checks if the ViewModel that is being created using this ViewModelFactory
    // is of correct type, otherwise throws an exception
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(result) as T
        throw IllegalArgumentException("unknown ViewModel")
    }
}