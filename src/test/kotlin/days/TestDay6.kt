package days

import kotlin.test.Test
import kotlin.test.assertEquals

class TestDay6: Day6() {
  val testInput = """
  123 328  51 64 
   45 64  387 23 
    6 98  215 314
  *   +   *   + 
  """.trimIndent()

  @Test
  fun `test parser`() {
    val got = this.parse(testInput)
    val expect = listOf(
      Problem(mutableListOf(123,45,6), Operation.Multiply),
      Problem(mutableListOf(328,64,98), Operation.Add),
      Problem(mutableListOf(51,387,215), Operation.Multiply),
      Problem(mutableListOf(64,23,314), Operation.Add),
    )

    assertEquals(expect, got)
  }

  @Test
  fun `test parser 2`() {
    val got = this.parse2(testInput)
    val expect = listOf(
      Problem(mutableListOf(4,431,623), Operation.Add),
      Problem(mutableListOf(175,581,32), Operation.Multiply),
      Problem(mutableListOf(8,248,369), Operation.Add),
      Problem(mutableListOf(356,24,1), Operation.Multiply),
    )

    assertEquals(expect, got)
  }

  @Test
  fun `test problem 1`(){
    val got = this.solve1(testInput)
    assertEquals(4277556, got)
  }
}