package com.opendart.mvvmlitedataornek.network


import com.opendart.mvvmlitedataornek.model.Hero
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("marvel")
    fun getHeroes(): Call<List<Hero>>
    companion object {
        var retrofitService : RetrofitService? = null

        fun getInstance() : RetrofitService {
            if(retrofitService == null)
            {
                //https://www.simplifiedcoding.net/demos/
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.simplifiedcoding.net/demos/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)

            }
            return retrofitService!!
        }
    }
}