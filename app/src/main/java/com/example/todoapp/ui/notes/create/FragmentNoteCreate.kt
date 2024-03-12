package com.example.todoapp.ui.notes.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.NotesManager
import com.example.todoapp.databinding.FragmentNoteCreateBinding
import com.example.todoapp.models.Notes

class FragmentNoteCreate : Fragment() {

    private var _binding: FragmentNoteCreateBinding? = null
    private val binding
        get() = _binding!!
    private var isUpdating = false
    private var noteId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteCreateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        setListeners()
    }

    private fun setUp() {
        noteId = requireArguments().getInt("noteId", -1)
        if (noteId != -1) {
            isUpdating = true
            val currentNote = NotesManager.dao.getNoteById(noteId).value
            if (currentNote != null) {
                binding.edtNoteTitle.setText(currentNote.title)
                binding.edtNoteDescription.setText(currentNote.description)
            }
        } else {
            Toast.makeText(requireContext(), "Invalid noteId", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        binding.btNoteCreate.setOnClickListener {
            if (isUpdating) {
                val updateNote = Notes(
                    id = noteId,
                    title = binding.edtNoteTitle.text.toString(),
                    description = binding.edtNoteDescription.text.toString()
                )
                NotesManager.dao.insertOrUpdateNote(updateNote)
            } else {
                val newNote = Notes(
                    title = binding.edtNoteTitle.text.toString(),
                    description = binding.edtNoteDescription.text.toString()
                )
                NotesManager.dao.insertOrUpdateNote(newNote)
            }
            findNavController().popBackStack()
        }
    }
}