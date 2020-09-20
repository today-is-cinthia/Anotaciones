package com.astro.mvvm_notes.ui

import androidx.lifecycle.ViewModel
import com.astro.mvvm_notes.data.db.NotesItem
import com.astro.mvvm_notes.data.db.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
):ViewModel() {

    fun upsert(item: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: NotesItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllNotesItem() = repository.getAllNotesItem()
}