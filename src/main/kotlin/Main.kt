import days.Day1
import days.Day2

fun main() {
    val days = listOf(Day1(), Day2())
    days.forEach {
        println(it.solve())
    }
}