package com.example.guessinggame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding : FragmentResultBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater)
        val rootView = binding.root

        binding.tvResult.text = ResultFragmentArgs.fromBundle(requireArguments()).result
        binding.btnPlayAgain.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToGameFragment()
            val navController = rootView.findNavController()
            navController.navigate(action)
        }

        return rootView
    }
}