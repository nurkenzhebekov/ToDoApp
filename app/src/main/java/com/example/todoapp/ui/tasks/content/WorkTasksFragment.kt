package com.example.todoapp.ui.tasks.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentWorkTasksBinding
import com.example.todoapp.ui.prefs.getMyPrefs

class WorkTasksFragment : Fragment() {

    private lateinit var binding: FragmentWorkTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        setListeners()
    }

    private fun setup() {
        binding.edtName.setText(getMyPrefs().getName())
    }

    private fun setListeners() {
        binding.btSave.setOnClickListener {
            getMyPrefs().saveName(binding.edtName.text.toString())
        }
    }
}