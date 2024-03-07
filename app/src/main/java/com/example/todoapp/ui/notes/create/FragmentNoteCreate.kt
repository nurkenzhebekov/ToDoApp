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

    private var note: Notes? = null
    private var isEdit = false

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

        getTask()
        setListeners()
    }

    private fun getTask() {
        val args = arguments
        if (args != null && args.containsKey(NOTE_KEY)) {
            note = args.getParcelable(NOTE_KEY)
            binding.edtNoteTitle.setText(note?.title)
            binding.edtNoteDescription.setText(note?.description)
            isEdit = true
            binding.btNoteCreate.text = "Save"
        } else {
            isEdit = false
            binding.btNoteCreate.text = "Create"
        }
    }

    private fun setListeners() {
        binding.btNoteCreate.setOnClickListener {
            val note = if (note == null) {
                createNote()
            } else {
                updateNote()
            }
            saveNote(note)
        }
    }

    private fun saveNote(notes: Notes) {
        val isEdit = note?.id != null
        val bundle = bundleOf(
            NOTE_KEY to notes,
            IS_EDIT_KEY to isEdit
        )
        setFragmentResult(RESULT_KEY, bundle)
        Toast.makeText(requireContext()
            , "Note ${if (isEdit) "Updated" else "Created"}", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun updateNote() : Notes {
        return note!!.copy(
            title = binding.edtNoteTitle.text.toString(),
            description = binding.edtNoteDescription.text.toString()
        )
    }

    private fun createNote() : Notes {
        return Notes(
            title = binding.edtNoteTitle.text.toString(),
            description = binding.edtNoteDescription.text.toString(),
            id = System.currentTimeMillis()
        )
    }

    companion object {
        const val RESULT_KEY = "FRAGMENT_NOTE_CREATE_RESULT_KEY"
        const val NOTE_KEY = "NOTE_KEY"
        const val IS_EDIT_KEY = "IS_EDIT_KEY"
    }
}