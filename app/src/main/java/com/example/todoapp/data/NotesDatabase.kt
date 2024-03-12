package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.models.Notes

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}