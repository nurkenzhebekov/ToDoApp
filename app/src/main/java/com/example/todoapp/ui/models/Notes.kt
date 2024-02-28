package com.example.todoapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notes(
    val title: String,
    val description: String,
    val id: Long
) : Parcelable
