package days

import org.jetbrains.kotlinx.multik.ndarray.data.last

open class Day5: Day(5) {
    fun parse(input: String): Pair<List<Long>, List<Pair<Long,Long>>>{
        val split = input.split("\n\n")
        val ranges = split[0]
            .lines()
            .map { it.split("-") }
            .map { it[0].toLong()to it[1].toLong() }

        val items = split[1].lines().map { it.toLong() }

        return items to ranges
    }

    override fun solve1(input: String): Number {
        val parsed = parse(input)

        return parsed.first.count { item ->
            !parsed.second.none { range -> (range.first ..range.second).contains(item) }
        }
    }

    override fun solve2(input: String): Number = parse(input).second.fold((0..0).asSequence()){ acc, range ->
        (acc + (range.first .. range.second).asSequence()) as Sequence<Int>
    }.count()

}