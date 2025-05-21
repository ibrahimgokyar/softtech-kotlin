package com.opendart.mvvmlitedataornek

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    lateinit var txtTitle : TextView
    lateinit var imgDetail : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Geri"
        actionBar.setDisplayHomeAsUpEnabled(true)
        txtTitle = findViewById(R.id.txtDetail)
        imgDetail = findViewById(R.id.imgDetail)



        val extras = intent.extras
        txtTitle.setText(extras?.getString("title"))
        Glide.with(this).load(extras?.getString("imageurl")).placeholder(R.drawable.placeholder)
            .into(imgDetail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}