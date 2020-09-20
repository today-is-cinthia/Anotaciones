package com.astro.mvvm_notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astro.mvvm_notes.R
import com.astro.mvvm_notes.data.db.NotesItem
import kotlinx.android.synthetic.main.notes_item_row.view.*

class NotesAdapter(
    var items:List<NotesItem>,
    val viewModel: NotesViewModel
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.notes_item_row,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int){

        val currentNotesItem = items[position]

        holder.itemView.apply {
            tvName_row.text = currentNotesItem.name
            checkBox_row.isChecked = currentNotesItem.isDone

            checkBox_row.setOnClickListener {

//                currentNotesItem.isDone = !checkBox_row.isChecked
                if(checkBox_row.isChecked){
                    currentNotesItem.isDone = true
                }else{
                    currentNotesItem.isDone = false
                }
                viewModel.upsert(currentNotesItem)

            }

            ivDel.setOnClickListener {
                viewModel.delete(currentNotesItem)
            }


        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}