package com.astro.mvvm_notes.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:NotesItem)

    @Delete
    suspend fun delete(item:NotesItem)

    @Query("SELECT * FROM tbl_notes")
    fun getAllNotesItem() : LiveData<List<NotesItem>>
}