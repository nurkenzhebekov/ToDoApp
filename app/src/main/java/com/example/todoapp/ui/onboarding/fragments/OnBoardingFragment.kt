package com.example.todoapp.ui.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentOnBoardingBinding
import com.example.todoapp.ui.onboarding.adapter.ViewPagerAdapter
import com.example.todoapp.ui.onboarding.fragments.screens.OnBoardingScreenFragment1
import com.example.todoapp.ui.onboarding.fragments.screens.OnBoardingScreenFragment2
import com.example.todoapp.ui.onboarding.fragments.screens.OnBoardingScreenFragment3

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingScreenFragment1(),
            OnBoardingScreenFragment2(),
            OnBoardingScreenFragment3(),
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.vpOnboarding

        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)
    }
}