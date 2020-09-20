package com.astro.mvvm_notes.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_notes")
class NotesItem(
    @ColumnInfo(name="item_name")
    var name:String,
    @ColumnInfo(name = "item_isDone")
    var isDone :Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

}