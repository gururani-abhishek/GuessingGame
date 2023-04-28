package com.example.guessinggame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.databinding.FragmentGiveTextBinding

class GiveTextFragment : Fragment() {
    private var _binding : FragmentGiveTextBinding ?= null
    private val binding get() = _binding!!

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
                    val action = GiveTextFragmentDirections.actionGiveTextFragmentToGameFragment(inputWord)
                    val navController = findNavController()
                    navController.navigate(action)
                } else {
                    Toast.makeText(activity, "enter a guess word, please!!", Toast.LENGTH_SHORT).show()
                }
            binding.etInputWord.text = null

        }
    }
}