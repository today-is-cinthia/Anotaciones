package com.astro.mvvm_notes

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


        viewModel.getAllTareas().observe(this, Observer {
            adapter.tarea = it
            adapter.notifyDataSetChanged()
        })

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

private fun addAnotacion(anotacion: Anotacion)
{
    if(anotacion.finalizado)
    {

    }
}

