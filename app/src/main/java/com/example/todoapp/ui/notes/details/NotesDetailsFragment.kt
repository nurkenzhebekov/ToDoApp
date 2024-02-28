package com.example.todoapp.ui.notes.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentNotesDetailsBinding
import com.example.todoapp.ui.models.Notes
import com.example.todoapp.ui.notes.create.FragmentNoteCreate

class NotesDetailsFragment : Fragment() {

    private var _binding: FragmentNotesDetailsBinding? = null
    private val binding
        get() = _binding!!
    private var note: Notes? = null

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

        displayNoteDetails()
    }

    private fun displayNoteDetails() {
        val args = arguments ?: return
        note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        args.getParcelable(FragmentNoteCreate.TASK_KEY, Notes::class.java) ?: return
        else
        args.getParcelable(FragmentNoteCreate.TASK_KEY) ?: return

        binding.tvNoteDetails.text = note?.title.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}