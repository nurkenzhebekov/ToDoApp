package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.NotesManager

class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        NotesManager.init(this)
    }
}