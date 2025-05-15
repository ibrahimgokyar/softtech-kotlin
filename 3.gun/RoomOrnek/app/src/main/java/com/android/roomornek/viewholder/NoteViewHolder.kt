package com.android.roomornek.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.android.roomornek.R
import com.android.roomornek.activities.CreateNoteActivity
import com.android.roomornek.entities.Note

class NoteViewHolder (viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item,viewGroup,false))

{

    private val rc_txt_title by lazy { itemView.findViewById<TextView>(R.id.rc_txt_title) }
    private val rc_txt_subject by lazy { itemView.findViewById<TextView>(R.id.rc_txt_subject) }
    private val rc_txt_description by lazy { itemView.findViewById<TextView>(R.id.rc_txt_description) }

    fun bindTo(note: Note) {
        rc_txt_title.text = note.title
        rc_txt_subject.text = note.subject
        rc_txt_description.text = note.description


         itemView.setOnClickListener {
             val intent = Intent(itemView.context, CreateNoteActivity::class.java)
             intent.putExtra("title",note.title)
             intent.putExtra("subject",note.subject)
             intent.putExtra("description",note.description)
             itemView.context.startActivity(intent)
         }
    }
}
