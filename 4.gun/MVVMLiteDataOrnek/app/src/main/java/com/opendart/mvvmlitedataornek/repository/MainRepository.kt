package com.opendart.mvvmlitedataornek.repository

import com.opendart.mvvmlitedataornek.network.RetrofitService

class MainRepository  constructor(private val retrofitService: RetrofitService){
    fun getAllMovies() = retrofitService.getHeroes()
}