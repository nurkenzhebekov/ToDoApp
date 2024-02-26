package com.example.todoapp.ui.prefs

import android.content.Context
import androidx.fragment.app.Fragment

class MySharedPreferences(
    context: Context
) {

    private val prefs = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

    fun saveName(name: String) {
        prefs.edit()
            .putString("user-name", name)
            .apply()
    }

    fun getName() : String? {
        return prefs.getString("user-name", null)
    }
}

fun Fragment.getMyPrefs() = MySharedPreferences(requireContext())