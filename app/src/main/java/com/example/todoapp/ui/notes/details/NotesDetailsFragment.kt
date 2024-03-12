package com.example.todoapp.ui.notes.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.NotesManager
import com.example.todoapp.databinding.FragmentNotesDetailsBinding

class NotesDetailsFragment : Fragment() {

    private var _binding: FragmentNotesDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        setListener()
    }

    private fun setUp() {
        val noteId = requireArguments().getInt("noteId", -1)
        if (noteId != -1) {
            getNoteDetails(noteId)
        }
    }

    private fun getNoteDetails(noteId: Int) {
        NotesManager.dao.getNoteById(noteId).observe(viewLifecycleOwner) { note ->
            if (note != null) {
                binding.tvNoteTitle.text = note.title
                binding.tvNoteDescription.text = note.description
            }
        }
    }

    private fun setListener() {
        binding.btEditNote.setOnClickListener {
            val noteId = requireArguments().getInt("noteId", -1)
            if (noteId != -1) {
                val bundle = Bundle().apply {
                    putInt("noteId", noteId)
                }
                findNavController().navigate(R.id.action_notesDetailsFragment_to_fragmentNoteCreate, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}