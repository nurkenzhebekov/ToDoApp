package com.example.todoapp.ui.notes.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.NotesManager
import com.example.todoapp.databinding.FragmentNoteCreateBinding
import com.example.todoapp.models.Notes

class FragmentNoteCreate : Fragment() {

    private var _binding: FragmentNoteCreateBinding? = null
    private val binding
        get() = _binding!!

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

        setListeners()
    }

    private fun setListeners() {
        binding.btNoteCreate.setOnClickListener {
            NotesManager.dao.insertNote(
                Notes(
                    title = binding.edtNoteTitle.text.toString(),
                    description = binding.edtNoteDescription.text.toString()
                )
            )
            findNavController().navigateUp()
        }
    }
}