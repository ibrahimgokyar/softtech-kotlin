package com.android.roomornek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.roomornek.adapter.NoteAdapter
import com.android.roomornek.database.AppDatabase
import com.android.roomornek.entities.Note
import androidx.room.Room
import com.android.roomornek.activities.CreateNoteActivity

class MainActivity : AppCompatActivity() {
    lateinit var fab : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Create a notes in list
        val notes:List<Note>

        val db:AppDatabase= Room.databaseBuilder(this,AppDatabase::class.java,"notes")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        //Get a notes
        notes=db.notedao().getAllNotes()


        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NoteAdapter(notes,this@MainActivity)
        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent= Intent(this,CreateNoteActivity::class.java)
            startActivity(intent)
        }
    }
}