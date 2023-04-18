package com.example.guessinggame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding
import com.example.guessinggame.viewmodels.GameViewModel

class GameFragment : Fragment() {
    private var _binding : FragmentGameBinding ?= null
    private val binding get() = _binding!!

    lateinit var viewModel : GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        // UI initialisation
        val rootView = binding.root
        viewModel.secretWordDisplay = viewModel.deriveSecretWordDisplay()
        updateStats()

        // interaction with UI
        binding.btCheckGuess.setOnClickListener {
            viewModel.checkGuess(binding.etInputLetter.text.toString().uppercase())
            binding.etInputLetter.text = null

            updateStats()

            if(viewModel.isWon() || viewModel.isLost()) {
                // move to the next fragment, make sure to pass the information
                // of word and winning/losing
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                val navController = rootView.findNavController()
                navController.navigate(action)
            }
        }

        return rootView
    }

    // this function updates the UI
    private fun updateStats() {
        binding.tvWord.text = viewModel.secretWordDisplay
        binding.tvIncorrectGuesses.text = getString(R.string.incorrect_guess_stats, viewModel.incorrectGuesses)
        binding.tvLivesLeft.text = getString(R.string.lives_left_stats, viewModel.livesLeft)
    }

    // to avoid memory leaks it's important that you release the _binding resource
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}