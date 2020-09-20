package com.astro.mvvm_notes.data.db.repository

import com.astro.mvvm_notes.data.db.NotesDatabase
import com.astro.mvvm_notes.data.db.NotesItem

class NotesRepository(
private val db:NotesDatabase
) {
    suspend fun upsert(item:NotesItem) = db.getNotesDao().upsert(item)
    suspend fun delete(item: NotesItem) = db.getNotesDao().delete(item)

    fun getAllNotesItem() = db.getNotesDao().getAllNotesItem()
}