package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay3: Day3() {
    val input = """
        987654321111111
        811111111111119
        234234234234278
        818181911112111
    """.trimIndent()

    @Test
    fun `test part 1`() {
        var got = this.solve1("987654321111111")
        assertEquals(98, got)

        got = this.solve1("811111111111119")
        assertEquals(89, got)

        got = this.solve1("234234234234278")
        assertEquals(78, got)

        got = this.solve1("818181911112111")
        assertEquals(92, got)

        got = this.solve1(input)
        assertEquals(357, got)
    }
}