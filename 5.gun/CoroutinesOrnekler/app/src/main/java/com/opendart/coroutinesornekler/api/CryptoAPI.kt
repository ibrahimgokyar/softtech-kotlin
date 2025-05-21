package com.opendart.coroutinesornekler.api


import com.opendart.coroutinesornekler.model.CryptoModel

import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("ibrahimgokyar/androidkitap/master/crypto.json")
    suspend fun getData(): Response<List<CryptoModel>>

}