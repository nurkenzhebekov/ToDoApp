package com.example.todoapp.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.ui.tasks.content.HomeTasksFragment
import com.example.todoapp.ui.tasks.content.WorkTasksFragment

class CategoriesVPAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {

    private val content = listOf<Fragment>(
        HomeTasksFragment(),
        WorkTasksFragment()
    )

    override fun getItemCount(): Int {
        return content.size
    }

    override fun createFragment(position: Int): Fragment {
        return content[position]
    }
}