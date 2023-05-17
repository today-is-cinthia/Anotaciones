package com.astro.mvvm_notes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Room usa el dao para enviar consultas a la base de datos
// Es una capa que hace la funcion de SQLiteOpenHelper


//Se especifica la entidad Anotacion como una database
@Database(entities = [Anotacion::class],version = 1)

//Extiende RoomDatabase
abstract class AnotacionRoomDatabase : RoomDatabase(){

    //Obtiene lo que contiene el DAO
    abstract fun getAnotacionDao() : AnotacionDAO

    companion object{

        //Variable que contiene las instancias hechas de la base de datos
        private var instance:AnotacionRoomDatabase? = null

        //Enllava el objeto que se este sincronizando
        //Esto es requerido para invocar una instancia de un metodo sincronizado
        private val LOCK = Any()

        //Verifica si hubo una instancia y llama a la funcion que crea a la base de datos
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        //Funcion para crear la base de datps
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            AnotacionRoomDatabase::class.java,
            "Anotacion").build()
    }
}