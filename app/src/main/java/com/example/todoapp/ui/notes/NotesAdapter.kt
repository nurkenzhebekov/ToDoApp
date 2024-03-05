package com.example.todoapp.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemNotesBinding
import com.example.todoapp.ui.models.Notes

class NotesAdapter (
    private val itemClicked: (Long) -> Unit,
    ) : ListAdapter<Notes, NotesAdapter.NotesViewHolder>(DIFF_UTIL_CALL_BACK) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
            return NotesViewHolder(
                ItemNotesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
            holder.onBind(getItem(position))
        }

        inner class NotesViewHolder(private val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

            fun onBind(notes: Notes) {
                binding.tvTitle.text = notes.title
                binding.root.setOnClickListener {
                    itemClicked(notes.id)
                }
            }

        }

        companion object {
            private val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<Notes>() {
                override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }