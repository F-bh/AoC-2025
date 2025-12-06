import days.Day1
import days.Day2
import days.Day3
import days.Day4
import days.Day5

fun main() {
    val days = listOf(Day5())
    days.forEach {
        println(it.solve())
    }
}