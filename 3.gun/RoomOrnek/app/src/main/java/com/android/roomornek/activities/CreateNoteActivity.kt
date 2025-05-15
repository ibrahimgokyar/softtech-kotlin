package com.android.roomornek.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.android.roomornek.MainActivity
import com.android.roomornek.R
import com.android.roomornek.database.AppDatabase
import com.android.roomornek.entities.Note

class CreateNoteActivity : AppCompatActivity() {
    private var btn_Add: Button? = null
    private var txt_Title: EditText? = null
    private var txt_Subject: EditText? = null
    private var txt_description: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_note_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       // supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val db: AppDatabase =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration(true)
                .build()
        btn_Add = findViewById(R.id.btn_Add)
        txt_Title = findViewById(R.id.txt_Title)
        txt_Subject = findViewById(R.id._txt_Subject)
        txt_description = findViewById(R.id.txt_description)

        btn_Add?.setOnClickListener {
            // Use safe calls and elvis operator to get text, defaulting to empty string if null
            val title = txt_Title?.text?.toString().orEmpty()
            val subject = txt_Subject?.text?.toString().orEmpty()
            val description = txt_description?.text?.toString().orEmpty()

            if (title.isEmpty() || subject.isEmpty() || description.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                val note = Note(title, subject, description)
                db.notedao().insertAll(note)
                // Consider launching MainActivity with flags to clear back stack if appropriate
                val intent = Intent(this@CreateNoteActivity, MainActivity::class.java)
                // Example: intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}