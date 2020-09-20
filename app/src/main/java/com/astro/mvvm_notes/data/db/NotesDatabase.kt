package com.astro.mvvm_notes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesItem::class],version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun getNotesDao() : NotesDAO

    companion object{

        private var instance:NotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            NotesDatabase::class.java,
            "notes_db").build()
    }
}