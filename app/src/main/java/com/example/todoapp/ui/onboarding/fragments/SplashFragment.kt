package com.example.todoapp.ui.onboarding.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({

            if (onBoardingIsFinished()) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_authFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
            }
        }, 4000)

        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root

    }

    private fun onBoardingIsFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        return sharedPreferences.getBoolean("finished", false)
    }
}