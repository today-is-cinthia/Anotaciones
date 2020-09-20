package com.astro.mvvm_notes

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.mvvm_notes.data.db.NotesDatabase
import com.astro.mvvm_notes.data.db.NotesItem
import com.astro.mvvm_notes.data.db.repository.NotesRepository
import com.astro.mvvm_notes.ui.NotesAdapter
import com.astro.mvvm_notes.ui.NotesViewModel
import com.astro.mvvm_notes.ui.NotesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notes_item_row.*
import kotlinx.android.synthetic.main.popup_add_new_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = NotesDatabase(this)
        val repository = NotesRepository(database)
        val factory = NotesViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(NotesViewModel::class.java)

        val adapter = NotesAdapter(listOf(),viewModel)

        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter


        viewModel.getAllNotesItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab_add.setOnClickListener {
            val popup = Dialog(this)
            popup.setContentView(R.layout.popup_add_new_item)
            popup.show()

            popup.btnSave_popup.setOnClickListener {
                val nName = popup.etName_popup.text.toString()

                viewModel.upsert(NotesItem(nName,false))
                popup.dismiss()
            }


        }



    }
}