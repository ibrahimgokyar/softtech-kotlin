package com.opendart.coroutinesornekler

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    println("run blocking basladi")
    //aradaki işlemler bitene bu ilgili alani blokluyor
    runBlocking {
        //coroutine baslatma
        launch {
            delay(5000)
            println("run blocking")
        }
    }
    //burası herhangi bir scope olmamasına rağmen blocking den sonra çalışıyor
    println("run blocking bitti")
}