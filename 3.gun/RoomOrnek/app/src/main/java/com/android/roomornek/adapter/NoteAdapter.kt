package com.android.roomornek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.roomornek.R
import com.android.roomornek.entities.Note
import com.android.roomornek.viewholder.NoteViewHolder

class NoteAdapter (private val notes : List<Note>, val context: Context) : RecyclerView.Adapter<NoteViewHolder>() {


    var noteList = notes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent) // görselin ayarlandığı kısım
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    //gizli bir for döngüsü
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindTo(noteList[position])
    }
}