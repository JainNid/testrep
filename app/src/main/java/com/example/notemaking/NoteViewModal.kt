package com.example.notemaking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal(application: Application):AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init{
        val dao=NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addnote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}