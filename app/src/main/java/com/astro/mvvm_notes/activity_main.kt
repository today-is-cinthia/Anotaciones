package com.astro.mvvm_notes

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.mvvm_notes.data.db.AnotacionRoomDatabase
import com.astro.mvvm_notes.data.db.Anotacion
import com.astro.mvvm_notes.data.db.repository.AnotacionRepository
import com.astro.mvvm_notes.ui.AnotacionAdapter
import com.astro.mvvm_notes.ui.AnotacionViewModel
import com.astro.mvvm_notes.ui.AnotacionViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_notas.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class activity_main : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AnotacionRoomDatabase(this)
        val repository = AnotacionRepository(database)
        val factory = AnotacionViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(AnotacionViewModel::class.java)

        val adapter = AnotacionAdapter(listOf(),viewModel)
        val adapterF = AnotacionAdapter(listOf(),viewModel)

        rvTareasPendientes.layoutManager = LinearLayoutManager(this)
        rvTareasPendientes.adapter = adapter

        rvTareasFinalizadas.layoutManager = LinearLayoutManager(this)
        rvTareasFinalizadas.adapter = adapterF

        //llenando listas
        lifecycleScope.launch {
            viewModel.getAllTareas(true).collect {
                adapterF.tarea = it
                adapterF.notifyDataSetChanged()
            }
        }

        //llenando listas pendientes
        lifecycleScope.launch {
            viewModel.getAllTareas(false).collect {
                adapter.tarea = it
                adapter.notifyDataSetChanged()
            }
        }



        btnAgregar.setOnClickListener {
            if(tvDescripcionTarea.text.toString().isNotBlank())
            {
                val nName = tvDescripcionTarea.text.toString()
                viewModel.upsert(Anotacion(nName,false))
                tvDescripcionTarea.text?.clear()
            }else
            {
                tvDescripcionTarea.error = getString(R.string.strValidacionError)
            }

        }


    }


}

