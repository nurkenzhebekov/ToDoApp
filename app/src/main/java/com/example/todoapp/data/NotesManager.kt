package com.example.todoapp.data

import android.content.Context
import androidx.room.Room

object NotesManager {

    private var _dao: NotesDao? = null
    val dao get() = _dao!!

    fun init(context: Context) {

        val database = Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notesDatabase"
        )
            .allowMainThreadQueries()
            .build()

        _dao = database.notesDao()

    }
}