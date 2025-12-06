package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay5: Day5() {
    val testInput = """
    3-5
    10-14
    16-20
    12-18
    
    1
    5
    8
    11
    17
    32
    """.trimIndent()

    @Test
    fun `task 1`() {
        val got = this.solve1(testInput)
        assertEquals(3, got)
    }

    @Test
    fun `task 2`() {
        val got1 = this.mergeRanges(listOf(0L..10L, 10L..10L))
        assertEquals(1, got1.size)
        val got = this.solve2(testInput)
        assertEquals(14L, got)
    }
}