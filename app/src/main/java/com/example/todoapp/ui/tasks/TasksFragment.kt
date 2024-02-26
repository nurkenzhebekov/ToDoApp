package com.example.todoapp.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasksBinding
import com.google.android.material.tabs.TabLayoutMediator

class TasksFragment : Fragment() {
    private lateinit var binding: FragmentTasksBinding
    private lateinit var adapter: CategoriesVPAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    private fun setup() {
        adapter = CategoriesVPAdapter(fm = childFragmentManager, lifecycle)
        binding.vpCategories.adapter = adapter

        TabLayoutMediator(binding.tblCategories, binding.vpCategories) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Work"
                else -> error("position not supported")
            }
        }.attach()
    }
}