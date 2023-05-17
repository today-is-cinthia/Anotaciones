package com.astro.mvvm_notes.data.db.repository

import com.astro.mvvm_notes.data.db.AnotacionRoomDatabase
import com.astro.mvvm_notes.data.db.Anotacion

//Un repositorio administra las consultas y te permite usar varios backends
// El repositorio implementa la lógica para decidir si debe recuperar datos de una red o usar
// resultados almacenados en caché de una base de datos local


//Creamos el repositorio que recibe nuestro Room
class AnotacionRepository(
private val db:AnotacionRoomDatabase
) {
    //Queries que administra
    suspend fun upsert(tarea: Anotacion) = db.getAnotacionDao().upsert(tarea)
    suspend fun delete(tarea: Anotacion) = db.getAnotacionDao().delete(tarea)
    suspend fun update(tarea: Anotacion) = db.getAnotacionDao().update(tarea)

    //Select * FROM Anotacion
    fun getAllTareas() = db.getAnotacionDao().getAllTareas()
}