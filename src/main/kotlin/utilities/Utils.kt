package utilities

import java.io.File
import java.nio.file.Paths


val path = Paths.get("").toAbsolutePath().toString() + "/src/main/kotlin/"

fun getFileLines(fileName: String) = File(fileName).readLines()

fun getYearDayInput(year: String, day: Int) = getFileLines(path + "year$year/inputs/day$day.txt")