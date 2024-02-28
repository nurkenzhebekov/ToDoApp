package com.example.todoapp.ui.onboarding.fragments.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentOnBoardingScreen1Binding

class OnBoardingScreenFragment1 : Fragment() {

    private lateinit var binding: FragmentOnBoardingScreen1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingScreen1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextPage()
    }

    private fun nextPage() {
        val btNext = binding.btNextPage1
        val viewPager = activity?.findViewById<ViewPager2>(R.id.vp_onboarding)

        btNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }
}