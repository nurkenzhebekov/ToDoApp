package com.example.todoapp.ui.notes

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNotesBinding
import com.example.todoapp.ui.models.Notes
import com.example.todoapp.ui.notes.create.FragmentNoteCreate

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = NotesAdapter()
    private val arguments: NotesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        setListener()

        val newNote = arguments.notes
        newNote?.let {
            adapter.addNote(it)
        }
    }

    private fun setup() {
        binding.rvNotes.adapter = adapter
    }

    private fun setListener() {
        binding.btAddNewTask.setOnClickListener {
            val action = NotesFragmentDirections
                .actionNavigationNotesToFragmentNoteCreate()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}