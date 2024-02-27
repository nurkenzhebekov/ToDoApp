package com.example.todoapp.ui.onboarding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityOnBoardingBinding
import com.example.todoapp.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isFirstRun = preferences.getBoolean("isFirstRun", true)

        if (!isFirstRun) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = OnBoardingAdapter(this)
        binding.vpOnboarding.adapter = pagerAdapter

        val editor = preferences.edit()
        editor.putBoolean("isFirstRun", false)
        editor.apply()
    }
}