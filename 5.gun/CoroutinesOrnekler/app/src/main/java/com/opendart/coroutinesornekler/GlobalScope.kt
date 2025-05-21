package com.opendart.coroutinesornekler
import kotlinx.coroutines.delay

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


fun main()
{
    println("global scope start")
    GlobalScope.launch {
        delay(3000)
        println("global scope")
    }
    println("global scope end")
    //delay(2000)


}