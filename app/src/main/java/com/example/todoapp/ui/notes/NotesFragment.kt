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
    private val adapter = NotesAdapter(this::itemClicked)

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
        observeNoteResult()
    }

    private fun observeNoteResult() {
        setFragmentResultListener(FragmentNoteCreate.RESULT_KEY) { _, bundle ->
            val updatedNote = bundle.getParcelable<Notes>(FragmentNoteCreate.NOTE_KEY)
            val isEdit = bundle.getBoolean(FragmentNoteCreate.IS_EDIT_KEY, false)

            updatedNote?.let {
                val data = adapter.currentList.toMutableList()
                if (isEdit) {
                    val existingNoteIndex = data.indexOfFirst { it.id == updatedNote.id }
                    if (existingNoteIndex != -1) {
                        data[existingNoteIndex] = updatedNote
                    }
                } else {
                    data.add(updatedNote)
                }
                adapter.submitList(data)
            }
        }
    }

    private fun itemClicked(id: Long) {
        val note = adapter.currentList.find { it.id == id }
        note?.let {
            val action = NotesFragmentDirections.actionNavigationNotesToNotesDetailsFragment(note, false)
            findNavController().navigate(action)
        }
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