package days

open class Day3: Day(3) {
    override fun solve1(input: String) =
        input
        .lines()
        .fold(0) { acc, line ->
            acc + line.foldIndexed("") {index, acc2, char ->
                when {
                    acc2.isEmpty() -> char
                    index < (line.length -1 ) && (char > acc2[0]) -> char
                    acc2.length < 2 -> acc2 + char
                    acc2[1] < char -> "${acc2[0]}$char"
                    else -> acc2
                }.toString()
            }.toInt()
        }

    fun find(input: String): String {
        var lastPossibleIndex = input.length-13
        for ((index, value) in input.slice(1..lastPossibleIndex).withIndex()) {
            var indices = mutableListOf(0)
            var char = input[0]
            when {
                value > char -> {
                    char = value
                    indices = mutableListOf(index)
                }
                value == char -> indices.add(index)
                else -> continue
            }
        }

        return "ind"
    }


    override fun solve2(input: String): Int {
        return 0
    }
}