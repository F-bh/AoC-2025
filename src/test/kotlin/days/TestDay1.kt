package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay1: Day1() {
    @Test
    fun `Part one`() {
        val input = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """.trimMargin().trimIndent()
        val actual = this.solve1(input)
        assertEquals(3, actual)
    }

    @Test
    fun `Part two`() {
        val input = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """.trimMargin().trimIndent()
        val actual = this.solve2(input)
        assertEquals(6, actual)
    }
}
