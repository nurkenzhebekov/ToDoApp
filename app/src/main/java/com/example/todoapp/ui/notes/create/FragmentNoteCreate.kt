package com.example.todoapp.ui.notes.create

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.databinding.FragmentNoteCreateBinding
import com.example.todoapp.ui.models.Notes
import com.example.todoapp.ui.notes.NotesAdapter

class FragmentNoteCreate : Fragment() {

    private var _binding: FragmentNoteCreateBinding? = null
    private val binding
        get() = _binding!!

    private val arguments: FragmentNoteCreateArgs by navArgs()

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

        setArgs()
    }

    private fun setArgs() {

        val note = arguments.notes
        binding.edtNoteTitle.setText(note.title)
        binding.edtNoteDescription.setText(note.description)

        binding.btNoteCreate.setOnClickListener {
            val title = binding.edtNoteTitle.text.toString()
            val description = binding.edtNoteDescription.text.toString()
            val newNote = Notes(title, description)

            val action = FragmentNoteCreateDirections
                .actionFragmentNoteCreateToNavigationNotes(newNote)
            findNavController().navigate(action)
        }
    }
}