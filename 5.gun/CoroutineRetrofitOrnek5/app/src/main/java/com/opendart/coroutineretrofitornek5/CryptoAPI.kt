package com.opendart.coroutineretrofitornek5

import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("ibrahimgokyar/androidkitap/master/crypto.json")
    suspend fun getData(): Response<List<CryptoModel>>

}