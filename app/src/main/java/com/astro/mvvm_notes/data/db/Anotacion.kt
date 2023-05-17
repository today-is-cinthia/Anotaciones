package com.astro.mvvm_notes.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//Creamos la entidad(modelo)Anotacion
@Entity(tableName = "Anotacion")
class Anotacion(
    @ColumnInfo(name="tarea")
    var tarea:String = "",
    @ColumnInfo(name = "Finalizado")
    var finalizado :Boolean = false
) {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

}