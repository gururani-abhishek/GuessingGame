package com.example.guessinggame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding ?= null
    private val binding get() = _binding!!

    private val words = listOf("Android", "Activity", "Fragment", "Binding", "Safeargs")
    private var secretWord = words.random().uppercase()
    private var secretWordDisplay = "" // word to be displayed
    private var correctGuesses = "" // will hold correct letters in the form of string
    private var incorrectGuesses = ""
    private var livesLeft = 8

    private val SECRET_WORD_KEY = "SWK"
    private val SECRET_WORD_DISPLAY_KEY = "SWDK"
    private val CORRECT_GUESSES_KEY = "CGK"
    private val INCORRECT_GUESSES_KEY = "IGK"
    private val LIVES_LEFT_KEY = "LLK"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if(savedInstanceState != null) {
            secretWord = savedInstanceState.getString(SECRET_WORD_KEY).toString()
            secretWordDisplay = savedInstanceState.getString(SECRET_WORD_DISPLAY_KEY).toString()
            correctGuesses = savedInstanceState.getString(CORRECT_GUESSES_KEY).toString()
            incorrectGuesses = savedInstanceState.getString(INCORRECT_GUESSES_KEY).toString()
            livesLeft = savedInstanceState.getInt(LIVES_LEFT_KEY)
        }

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val rootView = binding.root
        secretWordDisplay = deriveSecretWordDisplay()
        updateStats()

        binding.btCheckGuess.setOnClickListener {
            checkGuess(binding.etInputLetter.text.toString().uppercase())
            binding.etInputLetter.text = null

            updateStats()

            if(isWon() || isLost()) {
                // move to the next fragment, make sure to pass the information
                // of word and winning/losing
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(wonLostMessage())
                val navController = rootView.findNavController()
                navController.navigate(action)
            }
        }

        return rootView
    }

    private fun updateStats() {
        binding.tvWord.text = secretWordDisplay
        binding.tvIncorrectGuesses.text = getString(R.string.incorrect_guess_stats, incorrectGuesses)
        binding.tvLivesLeft.text = getString(R.string.lives_left_stats, livesLeft)
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

    private fun checkGuess(guess : String) {
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

    private fun isWon() : Boolean {
        return secretWordDisplay == secretWord
    }

    private fun isLost() : Boolean {
        return livesLeft == 0
    }

    private fun wonLostMessage() : String {
        return if(isWon()) {
            getString(R.string.won_message, secretWord)
        } else {
            getString(R.string.lost_message, secretWord)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putString(SECRET_WORD_KEY, secretWord)
        savedInstanceState.putString(SECRET_WORD_DISPLAY_KEY, secretWordDisplay)
        savedInstanceState.putString(CORRECT_GUESSES_KEY, correctGuesses)
        savedInstanceState.putString(INCORRECT_GUESSES_KEY, incorrectGuesses)
        savedInstanceState.putInt(LIVES_LEFT_KEY, livesLeft)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}