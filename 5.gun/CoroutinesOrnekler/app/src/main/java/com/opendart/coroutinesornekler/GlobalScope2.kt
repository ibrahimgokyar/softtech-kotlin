package com.opendart.coroutinesornekler


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


fun main() {
    //Bütün Uygulama içerisinde çalıştırabilmemizi sağlar
    println("global scope basladi")
    GlobalScope.launch {
        delay(5000)
        //bu kısım arka planda çalışmaya devam ediyor
        //5 saniye geçtikten sonra global scope eknana gelecek
        println("global scope")
    }
    //burada bloklanlanmadan end yazdırılmış oldu
    print("global scope bitti")
}