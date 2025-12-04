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

    @Test
    fun `test part 2`() {
        var got = this.solve2("987654321111111")
        assertEquals(987654321111, got)

        got = this.solve2("811111111111119")
        assertEquals(811111111119, got)

        got = this.solve2("234234234234278")
        assertEquals(434234234278, got)

        got = this.solve2("818181911112111")
        assertEquals(888911112111, got)

        got = this.solve2("1225256234342253412143421223222225222313522223223162233422625222264342131333132242562353732432472452")
        assertEquals(732432472452, got)

        got = this.solve2("7634742762152335335232537437247636722272554232217132324732223325535421252751556444525557936354356543")
        assertEquals(936354356543, got)

        got = this.solve2(input)
        assertEquals(3121910778619, got)
    }
}