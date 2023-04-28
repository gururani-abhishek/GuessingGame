package com.example.guessinggame.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGiveTextBinding
import com.ncorti.slidetoact.SlideToActView

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

        applyShakyAnimation(binding.llPrompt)

        // this is a lib and it doesn't support binding, hence have to call findViewById
        val slideToActView :SlideToActView = binding.root.findViewById(R.id.start_slider)
        slideToActView.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener{
            override fun onSlideComplete(view: SlideToActView) {
                if (binding.etInputWord.text.isNotEmpty()) {
                    val inputWord = binding.etInputWord.text.toString()
                    val action =
                        GiveTextFragmentDirections.actionGiveTextFragmentToGameFragment(inputWord)
                    val navController = findNavController()
                    navController.navigate(action)
                } else slideToActView.resetSlider()

                binding.etInputWord.text = null
            }
        }

 }

    private fun applyShakyAnimation(view : ViewGroup) {
        for(viewId in 0 until view.childCount) {
            val childView = view.getChildAt(viewId)
            childView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shaky))
        }
    }
}