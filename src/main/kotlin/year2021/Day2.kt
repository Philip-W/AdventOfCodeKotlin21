package year2021

class Day2 : Year2021(2) {

    fun part1(): Int {
        var depth = 0
        var horizontal = 0

        lines.forEach {
            val (direction, amount) = it.split(" ")
            when(direction){
                "forward" -> horizontal += amount.toInt()
                "down" -> depth += amount.toInt()
                "up"-> depth -= amount.toInt()
            }
        }
        return depth * horizontal
    }

    fun part2(): Int {
        var (depth, horizontal, aim) = listOf(0,0,0)

        lines.forEach {
            val (direction, amount) = it.split(" ")
            when(direction){
                "forward" -> {horizontal += amount.toInt(); depth += aim * amount.toInt()}
                "down" ->  aim += amount.toInt()
                "up"-> aim -= amount.toInt()
            }
        }
        return depth * horizontal
    }

    fun part1Faster(): Int {
        var depth = 0
        var horizontal = 0

        lines.forEach {
            when(it.first()){
                'f' -> horizontal += it[8].toInt()
                'd' -> depth += it[5].toInt()
                'u'-> depth -= it[3].toInt()
            }
        }
        return depth * horizontal
    }

    fun part2Faster(): Int {
        var depth =0
        var horizontal =0
        var aim = 0

        lines.forEach {
            when(it.first()){
                'f' -> {horizontal += it[8].toInt(); depth += aim * it[8].toInt()}
                'd' ->  aim += it[5].toInt()
                'u'-> aim -= it[3].toInt()
            }
        }
        return depth * horizontal
    }

    fun run() {
        assert(part1() == 2027977)
        assert(part1Faster() == 2027977)
        assert(part2() == 1903644897)
        assert(part2Faster() == 1903644897)

        println("Part 1: ${runPerformance(::part1)}")
        println("Part 1 faster: ${runPerformance(::part1Faster)}")

        println("Part 2: ${runPerformance(::part2)}")
        println("Part 2  Faster: ${runPerformance(::part2Faster)}")
    }
}

fun main() {
    Day2().run()
}
