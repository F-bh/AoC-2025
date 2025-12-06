package days

import org.w3c.dom.ranges.Range

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
      !parsed.second.none { range -> (range.first..range.second).contains(item) }
    }
  }


  fun mergeRanges(input: List<LongRange>): List<LongRange> {
    val orderedRanges = input.sortedBy { it.first }
    val retRanges = mutableListOf<LongRange>()
    var compareRange = orderedRanges.first()

    for (range in orderedRanges) {
      when {
        compareRange.contains(range.first) -> compareRange = compareRange.first..maxOf(range.last, compareRange.last)
        compareRange.last + 1 == range.first -> compareRange = compareRange.first..range.last
        else -> {
          retRanges.add(compareRange)
          compareRange = range
        }
      }
    }

    if (!retRanges.contains(compareRange)) retRanges.add(compareRange)
    return retRanges
  }




  override fun solve2(input: String): Number {
    val ranges = parse(input).second.map { (it.first..it.second) }
    var last = mergeRanges(ranges)
    while (true){
      val next = mergeRanges(last)
      if (next == last) break
      last = next
    }

    return last.fold(0L) { acc, range ->
      acc + (range.last+1 - range.first)
    }
  }
}