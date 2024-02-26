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
import com.example.todoapp.databinding.FragmentNoteCreateBinding
import com.example.todoapp.ui.models.Notes

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
        _binding = FragmentNoteCreateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTask()
        setListeners()
    }

    private fun getTask() {
        val args = arguments ?: return
        note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            args.getParcelable(TASK_KEY, Notes::class.java) ?: return
        else
            args.getParcelable(TASK_KEY) ?: return

        binding.edtTaskTitle.setText(note?.title.toString())
        isEdit = true
        binding.btTaskCreate.text = "Save"
    }

    private fun setListeners() {
        binding.btTaskCreate.setOnClickListener {
            val task = if (note == null) {
                createTask()
            } else {
                updateTask()
            }
            saveTask(task)
        }
    }

    private fun updateTask(): Notes {
        return note!!.copy(
            title = binding.edtTaskTitle.text.toString()
        )
    }

    private fun saveTask(tasks: Notes) {
        setFragmentResult(RESULT_KEY, bundleOf(
            TASK_KEY to tasks,
            IS_EDIT_KEY to isEdit
        )
        )
        Toast.makeText(requireContext(), "Task Created", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun createTask() : Notes {
        return Notes(
            title = binding.edtTaskTitle.text.toString(),
            id = System.currentTimeMillis()
        )
    }

    companion object {
        const val RESULT_KEY = "FRAGMENT_TASK_CREATE_RESULT_KEY"
        const val TASK_KEY = "TASK_KEY"
        const val IS_EDIT_KEY = "IS_EDIT_KEY"
    }
}