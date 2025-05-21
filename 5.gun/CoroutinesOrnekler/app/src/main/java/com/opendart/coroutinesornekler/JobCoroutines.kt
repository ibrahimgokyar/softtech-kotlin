package com.opendart.coroutinesornekler


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()
{
    runBlocking{
        val myJob = launch {
            delay(2000)
            println("job çalışıyor")
            val secondJob = launch {
                println("job 2")
            }

        }

        myJob.invokeOnCompletion {
            println("my job bitti")
        }

        // myJob.cancel()
    }

}