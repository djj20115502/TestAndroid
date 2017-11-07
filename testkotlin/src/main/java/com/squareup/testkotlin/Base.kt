package com.squareup.testkotlin

/**
 * Created by DongJunJie on 2017-10-24.
 */
open class Base {
    open val s1 = 1
    open var v1 = 2
    open fun test001() {
        println("s1:" + s1)
        println("v1:" + v1)
    }
}