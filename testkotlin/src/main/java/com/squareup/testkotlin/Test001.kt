package com.squareup.testkotlin

annotation class JvmStatic
/**
 * Created by DongJunJie on 2017-9-26.
 */
class Test001()  {
    var aa: Int = 1

    init {
        aa = 2
    }
    constructor(a:Int):this(){
        aa=a
    }
    constructor(a:Int,b:Int):this(){
        aa=a
    }

    fun add(a: Int, b: Int) = a + b

    fun print(s: String) = println(s)

    fun add1(a: String?): Int {
        return a?.length ?: -1
    }

    fun maxOf(a: Int, b: Int) = if (a > b) a else b

}

fun main(args: Array<String>) {
//    val a = Test001(1)
//    var kk: Int = 11_23_4
//    var ba = a.aa + 1
//    var ll = { x: Int, y: Int -> x + y }
//    a.print(kk.toString())
//    var iTest: ITest = DX()
//    val ans = iTest.one(1)
//    var t1: String? = ""
//    t1 = null
//    a.print("" + a.add1(t1))


     var b2=Base()
     b2.test001()
    Base2.test001()
    Base3.k


}

