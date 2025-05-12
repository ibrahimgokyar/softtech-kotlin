package com.opendart.veritasimaornek

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetayActivity : AppCompatActivity() {

    lateinit var textName : TextView
    lateinit var textSurName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detay)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textName = findViewById(R.id.txtName)
        textSurName = findViewById((R.id.txtSurName))
       //bana bir önceki actiity den gelen veri var mı
        val extras  = intent.extras
        textName.setText(extras?.getString(("isim")))
        textSurName.setText(extras?.getString(("soyisim")))
    }
}