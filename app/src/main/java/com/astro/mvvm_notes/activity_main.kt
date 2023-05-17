package com.astro.mvvm_notes

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.mvvm_notes.data.db.AnotacionRoomDatabase
import com.astro.mvvm_notes.data.db.Anotacion
import com.astro.mvvm_notes.data.db.repository.AnotacionRepository
import com.astro.mvvm_notes.ui.AnotacionAdapter
import com.astro.mvvm_notes.ui.AnotacionViewModel
import com.astro.mvvm_notes.ui.AnotacionViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_add_new_item.*

class activity_main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AnotacionRoomDatabase(this)
        val repository = AnotacionRepository(database)
        val factory = AnotacionViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(AnotacionViewModel::class.java)

        val adapter = AnotacionAdapter(listOf(),viewModel)

        rvTareasPendientes.layoutManager = LinearLayoutManager(this)
        rvTareasPendientes.adapter = adapter


        viewModel.getAllTareas().observe(this, Observer {
            adapter.tarea = it
            adapter.notifyDataSetChanged()
        })

        btnAgregar.setOnClickListener {
            val popup = Dialog(this)
            popup.setContentView(R.layout.popup_add_new_item)
            popup.show()

            popup.btnSave_popup.setOnClickListener {
                val nName = popup.etName_popup.text.toString()

                viewModel.upsert(Anotacion(nName,false))
                popup.dismiss()
            }


        }



    }
}