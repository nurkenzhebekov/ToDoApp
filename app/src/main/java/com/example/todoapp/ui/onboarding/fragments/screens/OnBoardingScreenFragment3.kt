package com.example.todoapp.ui.onboarding.fragments.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentOnBoardingScreen3Binding

class OnBoardingScreenFragment3 : Fragment() {

    private lateinit var binding: FragmentOnBoardingScreen3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingScreen3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishPage()
    }

    private fun finishPage() {
        val btNext = binding.btFinishPage
        val viewPager = activity?.findViewById<ViewPager2>(R.id.vp_onboarding)

        btNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_navigation_home)
            onBoardingIsFinished()
        }
    }

    private fun onBoardingIsFinished() {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }
}