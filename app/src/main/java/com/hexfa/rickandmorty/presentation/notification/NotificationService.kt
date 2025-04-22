package com.hexfa.rickandmorty.presentation.notification

import android.annotation.SuppressLint
import android.widget.Toast
import com.hexfa.rickandmorty.CharactersApp

@SuppressLint("StaticFieldLeak")
object NotificationService {

    private val context = CharactersApp.context

    fun showInformation(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showError(message: String) {
        showInformation(message)
    }
}