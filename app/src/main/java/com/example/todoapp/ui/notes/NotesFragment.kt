package com.example.todoapp.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.NotesManager
import com.example.todoapp.databinding.FragmentNotesBinding
import com.example.todoapp.models.Notes

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = NotesAdapter(itemClicked = this::itemClicked)

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
        setListeners()
        setupData()
    }

    private fun setupData() {
        NotesManager.dao.getAll().observe(viewLifecycleOwner) {notes ->
            adapter.submitList(notes)
        }
    }

    private fun itemClicked(item: Notes) {
        findNavController().navigate(R.id.action_navigation_notes_to_fragmentNoteCreate,
            bundleOf("noteKey" to item))
    }

    private fun setup() {
        binding.rvNotes.adapter = adapter
    }

    private fun setListeners() {
        binding.btAddNewTask.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notes_to_fragmentNoteCreate)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}