package com.astro.mvvm_notes.ui

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.astro.mvvm_notes.R
import com.astro.mvvm_notes.activity_main
import com.astro.mvvm_notes.data.db.Anotacion
import kotlinx.android.synthetic.main.item_notas.view.*

//Adapter aloja el recycler view y lo vincula con sus metodos oncreate, onbind
class AnotacionAdapter(
    var tarea:List<Anotacion>,
    val viewModel: AnotacionViewModel
) : RecyclerView.Adapter<AnotacionAdapter.AnotacionViewHolder>() {

    inner class AnotacionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacionViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_notas,parent,false)
        return AnotacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnotacionViewHolder, position: Int){

        val currentNotesItem = tarea[position]

        holder.itemView.apply {
            tvTarea.text = currentNotesItem.tarea
            chkFinalizado.isChecked = currentNotesItem.finalizado

            chkFinalizado.setOnClickListener {

//                currentNotesItem.isDone = !checkBox_row.isChecked
                if(chkFinalizado.isChecked){
                    currentNotesItem.finalizado = true
                }else{
                    currentNotesItem.finalizado = false
                }
                viewModel.upsert(currentNotesItem)
            }


            ivDel.setOnClickListener {
              val builder = AlertDialog.Builder(it.context)
                    .setTitle("Desea eliminar?")
                    .setPositiveButton("Ok",{
                            _, _ ->
                        viewModel.delete(currentNotesItem)
                    })
                    .setNegativeButton("Cancelar", null)
                builder.create().show()
            }
        }

    }


    override fun getItemCount(): Int {
        return tarea.size
    }


}