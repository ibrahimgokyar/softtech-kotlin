package com.opendart.coroutinesornekler

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        launch {
            delay(5000)
            println("run blocking")
        }
//
        coroutineScope {
            launch {
                delay(3000)
                println("coroutine scope")
            }
        }
    }
}