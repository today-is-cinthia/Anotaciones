package com.astro.mvvm_notes.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

//Dao hace conexion con la funcionalidad de las queries y el modelo
@Dao
interface AnotacionDAO {

    //Si se da algun conflicto cuando se ejecuta la query que lo ignore
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(tarea: Anotacion)

    @Delete
    suspend fun delete(tarea: Anotacion)

    @Update
    suspend fun update(tarea: Anotacion)

    @Query("SELECT * FROM Anotacion")
    //LiveData para ver fluidez en vivo de los datos de la lista
    fun getAllTareas() : LiveData<List<Anotacion>>
}