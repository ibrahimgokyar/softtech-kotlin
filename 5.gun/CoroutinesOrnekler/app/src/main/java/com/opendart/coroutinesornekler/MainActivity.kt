package com.opendart.coroutinesornekler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.opendart.coroutinesornekler.adapter.RecyclerViewAdapter
import com.opendart.coroutinesornekler.api.CryptoAPI
import com.opendart.coroutinesornekler.databinding.ActivityMainBinding
import com.opendart.coroutinesornekler.model.CryptoModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var recylerViewAdapter: RecyclerViewAdapter
    private var cryptoModels: ArrayList<CryptoModel> = arrayListOf()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Hata : ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels.clear()
                        cryptoModels.addAll(it)
                        recylerViewAdapter = RecyclerViewAdapter(cryptoModels, this@MainActivity)
                        binding.recyclerView.adapter = recylerViewAdapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Veri alınamadı!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Crypto Fiyatı : ${cryptoModel.price}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

}