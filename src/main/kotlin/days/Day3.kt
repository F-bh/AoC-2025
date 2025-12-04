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

    fun maxJoltage(input: String): Long {
        val windowSize = 11
        val ixMappedInput = input.mapIndexed { index, c -> Pair(index, c) }

        var lastPossibleIndex = input.length-windowSize
        var retval = ""
        var index = 0

        while (index < lastPossibleIndex && retval.length < 12) {
            val slice = ixMappedInput
                .slice(index until lastPossibleIndex)
            slice
                .maxBy {  it.second }
                .also { retval += it.second }
                .also { index = it.first + 1 }
                .also { lastPossibleIndex = input.length - (windowSize - retval.length)  }
        }

        return retval.toLong()
    }


    override fun solve2(input: String): Long  =  input.lines().fold(0L) { acc, line -> acc + maxJoltage(line) }

}