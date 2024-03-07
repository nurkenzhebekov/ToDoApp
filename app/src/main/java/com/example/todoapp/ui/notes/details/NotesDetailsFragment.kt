package com.example.todoapp.ui.notes.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNotesDetailsBinding
import com.example.todoapp.ui.models.Notes
import com.example.todoapp.ui.notes.create.FragmentNoteCreate

class NotesDetailsFragment : Fragment() {

    private var _binding: FragmentNotesDetailsBinding? = null
    private val binding
        get() = _binding!!
    private val args: NotesDetailsFragmentArgs by navArgs()

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

        setArgs()
        setListener()
    }

    private fun setListener() {
        binding.btEditNote.setOnClickListener {
            val bundle = bundleOf(
                FragmentNoteCreate.NOTE_KEY to args.note,
                FragmentNoteCreate.IS_EDIT_KEY to true
            )
            findNavController().navigate(
                R.id.action_notesDetailsFragment_to_fragmentNoteCreate,
                bundle
            )
        }
    }

    private fun setArgs() {
        val note = args.note
        binding.tvNoteTitle.text = note?.title
        binding.tvNoteDescription.text = note?.description

        val isEdit = args.isEdit
        if (isEdit) {
            val updatedNote = getUpdatedNote()
            binding.tvNoteTitle.text = updatedNote.title
            binding.tvNoteDescription.text = updatedNote.description
        }
    }

    private fun getUpdatedNote(): Notes {
        val updatedNote = findNavController().currentBackStackEntry
            ?.savedStateHandle?.get<Notes>(FragmentNoteCreate.NOTE_KEY)
        return updatedNote ?: args.note ?: Notes("", "", 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}