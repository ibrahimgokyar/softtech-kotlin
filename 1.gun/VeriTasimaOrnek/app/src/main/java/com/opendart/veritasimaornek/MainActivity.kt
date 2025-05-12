package com.opendart.veritasimaornek

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var btnGonder : Button
    lateinit var Name : EditText
    lateinit var Surname : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Button btnGonder = new Button();
        btnGonder = findViewById(R.id.btnGonder)
        Name = findViewById(R.id.etAd)
        Surname = findViewById(R.id.etSoyadi)
        btnGonder.setOnClickListener({
            //Intent class ından bir nesne oluşuturyoruz
            //Constructor ında 2 tane paramterre
            //1.bulunduğumuz aktivitiyi temsil edir
            //2. paramter gitmek istediğimiz aktiviteyi
            val intent = Intent(this,DetayActivity::class.java)
            intent.putExtra("isim",Name.text.toString())
            intent.putExtra("soyisim",Surname.text.toString())
            startActivity(intent)
        })

    }


}