package com.example.simpleblog.util.value

class Dog(

) {

    init {
        println("dog!!!! => $this")
    }

    val age = 10
}


class Cat(
    val dog: Dog
){



}