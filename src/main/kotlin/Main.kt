import days.Day1
import days.Day2
import days.Day3

fun main() {
    val days = listOf(Day1(), Day2(), Day3())
    days.forEach {
        println(it.solve())
    }
}