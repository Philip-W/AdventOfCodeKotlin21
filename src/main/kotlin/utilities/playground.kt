package utilities

import java.lang.StringBuilder
import java.util.Collections.max
import kotlin.math.max

class playground {

    fun getSum(a: Int, b: Int): Int {
        val aString = Integer.toBinaryString(a).reversed()
        val bString = Integer.toBinaryString(b).reversed()
        val limit = max(aString.length, bString.length)
        val result = StringBuilder()
        var carry = 0

//        println(aString)
//        println(bString)

        for ( i in (0 until limit)) {
            val aValue = aString.getOrNull(i)?.toString()?.toInt() ?: 0
            val bValue = bString.getOrNull(i)?.toString()?.toInt() ?: 0
            val numberOf1s = listOf(aValue, bValue, carry).count { it == 1 }

            var add  = 0
            when (numberOf1s) {
                0 -> {add = 0; carry = 0}
                1 -> {add = 1; carry = 0}
                2 -> {add = 0; carry = 1}
                3 -> {add = 1; carry = 1}
            }

            result.append(add)
        }
        if (carry == 1 && result.length < 32) result.append(carry)

        return Integer.parseUnsignedInt(result.reverse().toString(), 2)
    }


    fun test(){
        //println((Integer.toBinaryString(-2), 2))
        println("Returned: ${getSum(1, 3)} - should be 4")
        println("Returned: ${getSum(-1, 1)} - should be 0")
        println("Returned: ${getSum(Int.MIN_VALUE, Int.MAX_VALUE)} - should be 0")
        println("Returned: ${getSum(1, 2)} - should be 3")
        println("Returned: ${getSum(-5, 3)} - should be -2")
    }
}
// 10000000000000000000000000000000
// 11111111111111111111111111111110
// 11111111111111111111111111111110
// 100000000000000000000000000000000

fun main(){
    playground().test()
}

//  1
// 11

