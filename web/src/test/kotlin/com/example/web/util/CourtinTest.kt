package com.example.web.util

import kotlinx.coroutines.*

class CourtinTest {


}


fun main() {

    println("main1 ${Thread.currentThread().name}")

    val ints = mutableListOf<Int>(1, 2, 3, 4, 5)

    val job = CoroutineScope(Dispatchers.Default).launch {


        ints.map { prev ->
            async {
                asyncFunc()
            }
        }.awaitAll().forEach { msg-> println(msg) }

//        val result = async {
//            asyncFunc()
//        }
//
//        println(result.await())

    }

    println("main2 ${Thread.currentThread().name}")

    runBlocking {
        job.join()
    }

}


fun asyncFunc(): String {
    Thread.sleep(2000)
    println(Thread.currentThread().name)
    return "작업 끝"
}

