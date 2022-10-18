package com.example.simpleblog.mvc.config.aop

import mu.KotlinLogging


object CurryingAopObject {

    private val log = KotlinLogging.logger {  }

    /**
     * 일급시민의 조건 (코틀린에서 함수도 일급시민)
     *
     * 1. 인자로 넘겨줄 수 있다.
     * 2. 리턴타입으로 정의할 수 있다
     * 3. 값에 할당할 수 있다.
     *
     * 고차함수를 통해서, AOP를 구현해볼게
     *
     */



    fun wrapTryCatchWithVoidFunc(func: ()->Unit) {
        try {
            func()
        }catch (e:Exception){
            log.error { e.stackTraceToString() }
            //throw e
        }
    }




}


