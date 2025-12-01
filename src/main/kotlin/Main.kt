import days.Day1

fun main() {
    val days = listOf(Day1())
    days.forEach {
        println(it.solve())
    }
}