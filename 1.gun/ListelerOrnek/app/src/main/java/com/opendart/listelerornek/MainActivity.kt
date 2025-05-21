package com.opendart.listelerornek

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var ulkeListesi: ListView;
    var ulkeArray = arrayOf("Türkiye","Almanya","Fransa","İtalya",
        "Portekiz","Türkiye","Almanya","Fransa","İtalya","Portekiz","Türkiye","Almanya","Fransa","İtalya",
        "Portekiz","Türkiye","Almanya","Fransa","İtalya","Portekiz")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //ana layout taki listview da nesne oluşturduk
        //veri kümesi ile bizm listview ımı verilerle birleştirecek adapter bir nesne oluşturoyuz
        //listview.setAdapter(adapte)
        ulkeListesi = findViewById(R.id.liste)
        val adapter = ArrayAdapter(this,R.layout.listview_item,ulkeArray)
       // val adapter2 = ArrayAdapter(this,R.layout.listview_item,ulkeArray)
        ulkeListesi.setAdapter(adapter)
        //ulkeListesi.onItemClickListener = object : AdapterView.OnItemClickListener {
       // ulkeListesi.onItemClickListener(new AdapterView.OnItemClick())
        ulkeListesi.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val ulkeSatir  = ulkeListesi.getItemAtPosition(p2) as String
               Toast.makeText(applicationContext,"Tıklanan Ulke Adı : "+ulkeSatir,Toast.LENGTH_SHORT).show()

            }

        }


    }
}