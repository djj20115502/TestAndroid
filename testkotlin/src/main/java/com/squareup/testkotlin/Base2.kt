package com.squareup.testkotlin

/**
 * Created by DongJunJie on 2017-10-24.
 */
object Base2 : Base() {

    override var s1 = 21

    override var v1 = 22
    override fun test001() {
        println("s1:" + s1)
        println("v1:" + v1)
    }
}