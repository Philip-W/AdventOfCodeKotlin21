package year2021

import org.junit.Test

class TestDays {

    val days = listOf(Day1(), Day2(), Day3())

    @Test
    fun `See how fast it all is` () {
        days.forEach {
            val time = it.runFast()
            if (time != null){
                println("Day ${it.day}:")
                println("\t Part 1 ${time.first}")
                println("\t Part 2 ${time.second}")
            }

        }
    }

}