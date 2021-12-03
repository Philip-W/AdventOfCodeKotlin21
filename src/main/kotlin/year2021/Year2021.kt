package year2021

import utilities.getYearDayInput
import java.sql.Time
import kotlin.system.measureNanoTime

data class TimeDetails(
    val mean: Double? = null,
    val median: Double? = null,
    val slowest: Double? = null,
    val fastest: Double? = null
) {
    override fun toString() = "Mean: $mean μs"
}

open class Year2021(val day: Int) {

    val lines = getYearDayInput("2021", day)

    fun asInts() = lines.map { it.toInt() }

    open fun runFast(): Pair<TimeDetails, TimeDetails>? = null

    fun runPerformance(lambda: () -> Any): TimeDetails {
        val times = mutableListOf<Double>()
        repeat(5000) {
            times.add(measureNanoTime { lambda.invoke() } / 1_000.0)
        }
        return processTimes(times)
    }

    private fun processTimes(times: List<Double>): TimeDetails{
        val sorted = times.sorted()
        return TimeDetails(
            mean = (sorted.sum() / times.size).toLong().toDouble(),
            median = (times[times.size / 2]).toLong().toDouble(),
            slowest = (times.last()).toLong().toDouble(),
            fastest = (times.first()).toLong().toDouble()
        )
    }

}
