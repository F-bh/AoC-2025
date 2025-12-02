package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay2: Day2() {
    val input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

    @Test
    fun `part 1`() {
        val got = this.solve1(input)
        assertEquals(1227775554, got)
    }

    @Test
    fun `part 2`() {
        var got = this.solve1("11-22")
        assertEquals(33, got)

        got = this.solve2("95-115")
        assertEquals(210, got)

        got = this.solve2("998-1012")
        assertEquals(2009, got)

        got = this.solve2("1188511880-1188511890")
        assertEquals(1188511885, got)

        got = this.solve2("222220-222224")
        assertEquals(222222, got)

        got = this.solve2("1698522-1698528")
        assertEquals(0, got)

        got = this.solve2("446443-446449")
        assertEquals(446446, got)

        got = this.solve2("38593856-38593862")
        assertEquals(38593859, got)

        got = this.solve2("565653-565659")
        assertEquals(565656, got)

        got = this.solve2("824824821-824824827")
        assertEquals(824824824, got)

        got = this.solve2("2121212118-2121212124")
        assertEquals(2121212121, got)

        // putting it all together
        got = this.solve2(input)
        assertEquals(4174379265, got)
    }

}