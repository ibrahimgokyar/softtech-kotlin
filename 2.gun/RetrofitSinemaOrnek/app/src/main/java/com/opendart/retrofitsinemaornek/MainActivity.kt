package com.opendart.retrofitsinemaornek

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opendart.retrofitsinemaornek.adapter.HeroesAdapter
import com.opendart.retrofitsinemaornek.dto.Hero
import com.opendart.retrofitsinemaornek.network.RetrofitClient
import com.opendart.retrofitsinemaornek.services.HeroService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import java.util.Collections
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //https://www.simplifiedcoding.net/demos/marvel/

        val recyclerView: RecyclerView = findViewById(R.id.heroesRescView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.getClient().create(HeroService::class.java).getHeroes()
            .enqueue(object : retrofit2.Callback<List<Hero>> {
                override fun onResponse(call: Call<List<Hero>>?, response: Response<List<Hero>>?) {

                        val heroList = ArrayList(response?.body() ?: Collections.emptyList())
                        recyclerView.adapter = HeroesAdapter(heroList)


                }

                override fun onFailure(call: Call<List<Hero>>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity,"Bir hata oluştu"+t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
    }
}