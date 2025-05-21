package com.opendart.customlistviewornek

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val languaArray  =  arrayOf<String>("Java",".Net Core","Php","Python","Ruby")
    val languageDescArray = arrayOf<String>("Java Programlama Dili",".Net Core Programlama Dili",
        "Php programlama dili","Python Programlama Dili","Ruby Proglama Dili")
    val imageArray = arrayOf<Int>(R.drawable.java,R.drawable.net,R.drawable.php,R.drawable.python,R.drawable.ruby)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.languageList)
        val myListAdapter = OzelAdapter(this,languaArray,languageDescArray,imageArray)
        listView.adapter = myListAdapter
    }
}