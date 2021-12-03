package year2021

import kotlin.system.measureNanoTime

class Day1 : Year2021(1) {


//    private fun part1(values: List<Int> = asInts()) = values.mapIndexed { index, i ->  if (i > values.getOrElse(index -1) {i}) 1 else 0}.sum()
//    private fun part2() = part1(asInts().windowed(3).map{ it.sum()})
//


//         private fun part1(values: List<Int> = asInts()) =
//            values
//                .windowed(2)
//                .map { if (it.first() < it.last()) 1 else 0 }
//                .sum()
//
//        private fun part2() = part1(asInts().windowed(3).map { it.sum() })


    private fun part1() = solve(asInts().toIntArray(), 1)
    private fun part2() = solve(asInts().toIntArray(), 3)

    private fun solve(values: IntArray, distance: Int): Int {
        var count = 0
        for (i in (distance until values.size)) {
            if (values[i] > values[i - distance]) count++
        }
        return count
    }

    fun run() {
        repeat(5000) {
            part1()
            part2()
        }
        assert(part1() == 1676)
        assert(part2() == 1706)

        val time1 = measureNanoTime { part1() } / 1_000.0
        val time2 = measureNanoTime { part2() } / 1_000.0

        println("Time 1: $time1 μs")
        println("Time 2: $time2 μs")
        mapOf(1 to 2).getOrDefault(1, 2)
    }
}

fun main() {
    Day1().run()
}
