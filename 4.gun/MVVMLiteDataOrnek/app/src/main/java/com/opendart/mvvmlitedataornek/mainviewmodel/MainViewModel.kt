package com.opendart.mvvmlitedataornek.mainviewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opendart.mvvmlitedataornek.model.Hero

import com.opendart.mvvmlitedataornek.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections


class MainViewModel(private val repository: MainRepository) : ViewModel(){
    val movieList = MutableLiveData<List<Hero>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<Hero>> {
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                movieList.postValue(response?.body() ?: Collections.emptyList())
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}