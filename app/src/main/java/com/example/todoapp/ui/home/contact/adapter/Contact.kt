package com.example.todoapp.ui.home.contact.adapter

data class Contact(
    val name: String,
    val type: ContactType,
    val numbers: List<String>
)

enum class ContactType {
    WORK, MOBILE
}
