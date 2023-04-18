package com.example.guessinggame.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guessinggame.R
import com.example.guessinggame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}