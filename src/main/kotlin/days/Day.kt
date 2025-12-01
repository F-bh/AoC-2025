package days

import java.util.MissingResourceException

abstract class Day(val id: Int) {
    val problem: String = Day::class.java.getResource("day${id}.problem")?.readText()
        ?: throw MissingResourceException(
            "failed to find input for day $id task 1",
            this::class.simpleName,
            "day$id.problem"
        )

    protected abstract fun solve1(input: String): Int
    protected abstract fun solve2(input: String): Int
    fun solve(): String {
        return "The result for day ${id}\ntask one is: ${solve1(problem)}\ntask two is: ${solve2(problem)}"
    }

}