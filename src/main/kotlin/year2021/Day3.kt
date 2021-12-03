package year2021

import kotlin.math.ceil
import kotlin.math.pow

class Day3 : Year2021(3) {

    private fun part1(): Int {
        val half = lines.size / 2
        val arr = IntArray(lines.first().length)
        lines.forEach { line ->
            line.forEachIndexed { index, c -> arr[index] += (c - '0') }
        }

        var n1 = ""
        var n2 = ""
        arr.forEach {
            if (it > half) {
                n1 += '1'; n2 += '0'
            } else {
                n1 += '0'; n2 += '1'
            }
        }

        return n1.toInt(2) * n2.toInt(2)
    }

    private fun part1ButFaster(): Int {
        val half = lines.size / 2
        val arr = IntArray(lines.first().length)
        lines.forEach { line ->
            line.forEachIndexed { index, c -> arr[index] += (c - '0') }
        }
        var n1 = 0
        var n2 = 0
        arr.forEach {
            if (it > half) {
                n1 = (n1 shl 1) or 1
                n2 = n2 shl 1
            } else {
                n1 = n1 shl 1
                n2 = (n2 shl 1) or 1
            }
        }
        return n1 * n2
    }

    private fun part2() = oxygenAndC02(true) * oxygenAndC02(false)

    fun oxygenAndC02(predicate: Boolean): Int {
        var currentBit = 0
        var currentLines = lines
        while (currentLines.size > 1) {
            val half = ceil(currentLines.size / 2.0)
            var bitCount = 0
            currentLines.forEach { bitCount += it[currentBit] - '0' }
            currentLines = if ((bitCount >= half) == predicate) {
                // 1 most common
                currentLines.filter { it[currentBit] == '1' }
            } else {
                currentLines.filter { it[currentBit] == '0' }
            }
            currentBit += 1
        }

        return currentLines.first().toInt(2)
    }

    fun part2WithTree(): Int {
        val array = buildTree(lines.first().length)
        return oxygenAndC02ButWithTree(array, false) * oxygenAndC02ButWithTree(array)
    }

    fun buildTree(size: Int): IntArray {
        val arr = IntArray(2.0.pow(size.toDouble() + 2).toInt())
        lines.forEach { line ->
            var curr = 0
            line.forEach { c ->
                if (c == '1') {
                    curr = curr.left()
                    arr[curr] += 1
                } else {
                    curr = curr.right()
                    arr[curr] += 1
                }
            }
        }
        return arr
    }

    fun oxygenAndC02ButWithTree(array: IntArray, predicate: Boolean = true): Int {
        var count = 0
        var curr = 0
        while (array[curr] != 1) {
            if (array[curr.left()] >= array[curr.right()] == predicate) {
                count = (count shl 1) or 1
                curr = curr.left()
            } else {
                count = (count shl 1)
                curr = curr.right()
            }
        }

        while (true) {
            if (array[curr.left()] != 0) {
                count = (count shl 1) or 1
                curr = curr.left()
                continue
            }
            if (array[curr.right()] != 0) {
                count = (count shl 1)
                curr = curr.right()
                continue
            }
            break
        }
        return count
    }

    fun run() {
        assert(part1() == 2498354)
        assert(part1ButFaster() == 2498354)
        assert(part2() == 3277956)
        assert(part2WithTree() == 3277956)

        println("Part 1 ${runPerformance(::part1)}")
        println("Part 1 faster: ${runPerformance(::part1ButFaster)}")

        println("Part 2 ${runPerformance(::part2)}")
        println("Part 2 but with a tree: ${runPerformance(::part2WithTree)}")
    }

    override fun runFast() = Pair(
        runPerformance(::part1ButFaster),
        runPerformance(::part2)
    )
}

private fun Int.left() = (2 * this ) + 1
private fun Int.right() = (2 * this) + 2
fun main() {
    Day3().run()
}