package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay4: Day4() {
    val testInput = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent()

    @Test
    fun `task 1`() {
        val got = this.solve1(testInput)
        assertEquals(13, got)
    }
}