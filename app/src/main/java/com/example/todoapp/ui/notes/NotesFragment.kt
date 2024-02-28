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
    }

    private fun itemClicked(id: Long) {
        val note = adapter.currentList.find { it.id == id }
        note?.let {
            val action = NotesFragmentDirections.actionNavigationNotesToNotesDetailsFragment(note)
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

        setFragmentResultListener(FragmentNoteCreate.RESULT_KEY) {key, bundle ->
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                bundle.getParcelable(FragmentNoteCreate.NOTE_KEY, Notes::class.java)
            else
                bundle.getParcelable(FragmentNoteCreate.NOTE_KEY)

            val isEdit = bundle.getBoolean(FragmentNoteCreate.IS_EDIT_KEY, false)

            var data = mutableListOf<Notes>()
            data.addAll(adapter.currentList)

            if (isEdit) {
                data = data.map {
                    if (it.id == note?.id) {
                        note
                    } else {
                        it
                    }
                }.toMutableList()
            } else {
                data.add(note ?: return@setFragmentResultListener)
            }

            adapter.submitList(data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}