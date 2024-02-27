package com.example.todoapp.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.ui.onboarding.fragments.OnBoardingFragment
import com.example.todoapp.ui.onboarding.fragments.OnBoardingFragment1
import com.example.todoapp.ui.onboarding.fragments.OnBoardingFragment2
import com.example.todoapp.ui.onboarding.fragments.OnBoardingFragment3

class OnBoardingAdapter(
    fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardingFragment.newInstance(position)
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}