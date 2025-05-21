package com.opendart.mvvmlitedataornek

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.opendart.mvvmlitedataornek.adapter.MainAdapter
import com.opendart.mvvmlitedataornek.databinding.ActivityMainBinding
import com.opendart.mvvmlitedataornek.factory.MyViewModelFactory
import com.opendart.mvvmlitedataornek.mainviewmodel.MainViewModel
import com.opendart.mvvmlitedataornek.network.RetrofitService
import com.opendart.mvvmlitedataornek.repository.MainRepository

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var  binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this, Observer {
            Log.d(TAG,"movieList: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG,"errorMessage: $it")
        })

        viewModel.getAllMovies()

    }
}