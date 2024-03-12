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
    private var note: Notes? = null

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

        loadDataFromNotesFragment()
        initListeners()
    }

    private fun initListeners() {
        binding.btNoteCreate.setOnClickListener {
            if (note == null) saveNote()
            if (note != null && binding.edtNoteTitle.text.isNotEmpty()) updateNote()
            findNavController().popBackStack()
        }
    }

    private fun updateNote() {
        val data = note?.copy(
            title = binding.edtNoteTitle.text.toString(),
            description = binding.edtNoteDescription.text.toString()
        )
        if (data != null) NotesManager.dao.updateNote(data)
    }

    private fun saveNote() {
        val data = Notes(
            title = binding.edtNoteTitle.text.toString(),
            description = binding.edtNoteDescription.text.toString()
        )
        NotesManager.dao.insertNote(data)
    }

    private fun loadDataFromNotesFragment() {
        note = arguments?.getSerializable("noteKey") as? Notes
        if (note != null) {
            binding.edtNoteTitle.setText(note?.title)
            binding.edtNoteDescription.setText(note?.description)
            binding.btNoteCreate.text = "Update"
        }
    }
}