package com.example.guessinggame.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding
import com.example.guessinggame.viewmodels.GameViewModel
import com.example.guessinggame.viewmodels.factory.GameViewModelFactory

class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding ?= null
    private val binding get() = _binding!!

    private lateinit var viewModel : GameViewModel
    private lateinit var viewModelFactory : GameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val guessWord = GameFragmentArgs.fromBundle(requireArguments()).guessWord
        viewModelFactory = GameViewModelFactory(guessWord)
        viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]

// "GameFragment is assigning it's instantiated viewModel to data binding variable gameViewModel"
        binding.gameViewModel = viewModel // assigning gameViewModel variable with instantiated viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        

        // observing these values and updating them using data binding.
//        // observing live data property associated with livesLeft,
//        viewModel.livesLeft.observe(viewLifecycleOwner, Observer { liveValueLivesLeft ->
//            binding.tvLivesLeft.text = getString(R.string.lives_left_stats, liveValueLivesLeft)
//        })
//
//        // observing live data property associated with incorrectGuesses,
//        viewModel.incorrectGuesses.observe(viewLifecycleOwner, Observer { liveValueIncorrectGuesses ->
//            binding.tvIncorrectGuesses.text = getString(R.string.incorrect_guess_stats, liveValueIncorrectGuesses)
//        })
//
//        // observing live data property associated with secretWordDisplay,
//        viewModel.secretWordDisplay.observe(viewLifecycleOwner, Observer { liveValueSecretWordDisplay ->
//            binding.tvWord.text = liveValueSecretWordDisplay
//        })

        // observing live data property associated with gameOver,
        viewModel.gameOver.observe(viewLifecycleOwner, Observer { liveValueGameOver ->
            if(liveValueGameOver) {
                val wonLostMsg = getString(R.string.won_lost_message, viewModel.wonLostMessage(), viewModel.secretWord)
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(wonLostMsg)
                val navController = findNavController()
                navController.navigate(action)
            }
        })

        // interaction with UI
        binding.btCheckGuess.setOnClickListener {
            viewModel.checkGuess(binding.etInputLetter.text.toString().uppercase())
            binding.etInputLetter.text = null
        }
    }

//    this function updates the UI manually and now after LiveData integration, manually updating isn't required anymore.
//    private fun updateStats() {
//        binding.tvWord.text = viewModel.secretWordDisplay
//        binding.tvIncorrectGuesses.text = getString(R.string.incorrect_guess_stats, viewModel.incorrectGuesses)
//        binding.tvLivesLeft.text = getString(R.string.lives_left_stats, viewModel.livesLeft)
//    }

// to avoid memory leaks it's important that you release the _binding resource
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}