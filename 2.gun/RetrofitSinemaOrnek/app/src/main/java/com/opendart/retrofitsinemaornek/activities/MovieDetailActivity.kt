package com.opendart.retrofitsinemaornek.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.opendart.retrofitsinemaornek.R
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieDetailActivity : AppCompatActivity() {

    lateinit var txtDetail: TextView
    lateinit var imgDetail: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val actionbar = supportActionBar
        actionbar!!.title = "Geri"
        actionbar.setDisplayHomeAsUpEnabled(true)

        txtDetail = findViewById(R.id.txtDetail)
        imgDetail = findViewById(R.id.imgDetail)

        val extras = intent.extras //bir önceki ekrandan gelen data varmı?
        txtDetail.setText(extras?.getString("bio"))

        Glide.with(this).load(extras?.getString("imageurl"))
            .thumbnail(
                Glide.with(this)
                    .load(androidx.appcompat.R.drawable.abc_ic_go_search_api_material)
            )
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgDetail)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}