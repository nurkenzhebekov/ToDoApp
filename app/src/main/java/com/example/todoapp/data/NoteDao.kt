package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.models.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(notes: Notes)

    @Delete
    fun deleteNote(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getAll() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int) : LiveData<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateNote(note: Notes)

    @Update
    fun updateNote(note: Notes)
}