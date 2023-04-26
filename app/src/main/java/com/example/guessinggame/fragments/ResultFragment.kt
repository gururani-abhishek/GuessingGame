package com.example.guessinggame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.guessinggame.databinding.FragmentResultBinding
import com.example.guessinggame.viewmodels.ResultViewModel
import com.example.guessinggame.viewmodels.factory.ResultViewModelFactory

class ResultFragment : Fragment() {
    private var _binding : FragmentResultBinding ?= null
    private val binding get() = _binding!!

    lateinit var viewModel : ResultViewModel
    lateinit var viewModelFactory : ResultViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// this should move to view model ->
//        binding.tvResult.text = ResultFragmentArgs.fromBundle(requireArguments()).result

        val result = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactory(result)
        viewModel = ViewModelProvider(this, viewModelFactory)[ResultViewModel::class.java]

        binding.tvResult.text = viewModel.finalResult
        binding.btnPlayAgain.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToGameFragment()
            val navController = binding.root.findNavController()
            navController.navigate(action)
        }

    }
}