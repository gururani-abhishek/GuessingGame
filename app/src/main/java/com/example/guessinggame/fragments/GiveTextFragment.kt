package com.example.guessinggame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding
import com.example.guessinggame.databinding.FragmentGiveTextBinding
import com.example.guessinggame.viewmodels.GiveTextViewModel
import com.example.guessinggame.viewmodels.factory.GiveTextViewModelFactory

class GiveTextFragment : Fragment() {
    private var _binding : FragmentGiveTextBinding ?= null
    private val binding get() = _binding!!

    private lateinit var viewModel : GiveTextViewModel
    private lateinit var viewModelFactory : GiveTextViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGiveTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartGame.setOnClickListener {
            if(binding.etInputWord.text.isNotEmpty()) {
                val inputWord = binding.etInputWord.text.toString()

                viewModelFactory = GiveTextViewModelFactory(inputWord)
                viewModel = ViewModelProvider(this, viewModelFactory)[GiveTextViewModel(inputWord)::class.java]
                val guessWord = viewModel.wordToBeGuessed
                val action = GiveTextFragmentDirections.actionGiveTextFragmentToGameFragment(guessWord)
                val navController = findNavController()
                navController.navigate(action)
            }

            binding.etInputWord.text = null

        }
    }
}