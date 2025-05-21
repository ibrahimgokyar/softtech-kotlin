package com.opendart.coroutinesornekler

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        delay(2000)
        println("run blocking")
        MyFunction()
    }
}
//Coroutine scope ancak suspend fonksiyonlardan cağrılabilir
suspend fun MyFunction() {
    coroutineScope {
        delay(4000)
        println("suspend function")

    }
}