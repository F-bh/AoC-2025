package days

import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.and
import org.jetbrains.kotlinx.multik.ndarray.operations.count

open class Day4: Day(4) {
    fun toGrid(input: String) = mk.ndarray(input
        .lines()
        .map { line -> line.map { c -> if (c == '@') 1 else 0 } })

    override fun solve1(input: String): Number {
        val grid = toGrid(input)
        val mask = mk.ndarray(mk[listOf(1,1,1), listOf(1,0,1), listOf(1,1,1)])
        var res = 0

        for (x in 0..<grid.size) {
            for (y in 0..<grid[x].size) {
                when {
                    x == 0 && y == 0 -> {
                        val mask = mk.ndarray(mk[listOf(0,1,1), listOf(1,1,1)])
                        val sliced = grid[0..1, 0..2]
                        val maskRes  = sliced.and(mask)
                        res += if (maskRes.count { it == 1 } < 4) 1 else 0
                    }
                    else -> TODO("implement other cases")
                }
            }
        }

        return 0
    }


    override fun solve2(input: String): Number {
        return 1
    }
}