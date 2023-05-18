package com.astro.mvvm_notes.ui

import androidx.lifecycle.ViewModel
import com.astro.mvvm_notes.data.db.Anotacion
import com.astro.mvvm_notes.data.db.repository.AnotacionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Un ViewModel actúa como un centro de comunicación entre el repositorio y la IU
// contiene los datos de la IU de tu app de una manera optimizada para los ciclos de vida
// que sobrevive a los cambios de configuración


//ViewModel convertirá los datos del repositorio de flujo en LiveData
// y le mostrará a la IU la lista de palabras como LiveData.
//Actualizacion automatica de los datos cuando se modifiquen
class AnotacionViewModel(
    private val repository: AnotacionRepository
):ViewModel() {

    //CoroutineScope trabaja con alcance de corrutinas(suspender o reanudar ejecuciones)
    //launch inicia este proceso
    fun upsert(tarea: Anotacion) = CoroutineScope(Dispatchers.Main).launch {
        //accediendo al repositorio
        repository.upsert(tarea)
    }

    fun delete(tarea: Anotacion) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(tarea)
    }

    fun getAllTareas(finalizado : Boolean) : Flow<List<Anotacion>> = repository.getAllTareas(finalizado)
}