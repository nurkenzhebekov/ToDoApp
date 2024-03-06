package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todoapp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_notes, R.id.navigation_tasks, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        //hide the bottom navigation on splash screen
        navController.addOnDestinationChangedListener {_, destination, _ ->
            if (destination.id == R.id.splashScreenFragment ||
                destination.id == R.id.onBoardingFragment) {
                binding.botNavView.visibility = View.GONE
            } else {
                binding.botNavView.visibility = View.VISIBLE
            }
        }
        binding.botNavView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.default_toolbar_menu, menu)

        val item = menu?.findItem(R.id.close_app)
        item?.setOnMenuItemClickListener {
            finish()
            true
        }
        return true
    }
}