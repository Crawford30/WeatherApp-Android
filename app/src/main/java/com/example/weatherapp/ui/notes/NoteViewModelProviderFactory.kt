package com.example.weatherapp.ui.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.repository.NoteRepository


class NoteViewModelProviderFactory(
    val app: Application,
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(app, noteRepository) as T
    }
}