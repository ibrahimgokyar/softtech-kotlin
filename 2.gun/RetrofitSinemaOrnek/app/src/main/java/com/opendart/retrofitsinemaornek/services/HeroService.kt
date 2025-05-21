package com.opendart.retrofitsinemaornek.services

import com.opendart.retrofitsinemaornek.dto.Hero
import retrofit2.http.GET
import retrofit2.Call

interface HeroService {

    @GET("marvel")
    fun getHeroes(): Call<List<Hero>>

    //POST("login")
}