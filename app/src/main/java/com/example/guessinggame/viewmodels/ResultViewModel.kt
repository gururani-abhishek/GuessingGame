package com.example.guessinggame.viewmodels

import androidx.lifecycle.ViewModel

class ResultViewModel(result : String) : ViewModel() {
    val finalResult = result
}