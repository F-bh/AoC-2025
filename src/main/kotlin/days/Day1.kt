package days

import java.util.InputMismatchException

open class Day1: Day(1) {
    override fun solve1(input: String): Int {
        var ret = 0
        var pointer = 50
        input.split("\n").forEach { step ->
            when {
                step.startsWith('L') -> {
                    val toWalk = step.substringAfter('L').toInt() % 100
                    val res = pointer - toWalk
                    pointer = when {
                        res >= 0 -> res
                        else -> 100 + res
                    }
                }
                step.startsWith('R') -> {
                    val toWalk = step.substringAfter('R').toInt() % 100
                    val res = pointer + toWalk
                    pointer = when {
                        res < 100 -> res
                        else -> res - 100
                    }
                }
                else -> throw InputMismatchException("Input for day1 task 1 is invalid")
            }
            assert(pointer >= 0 )
            assert(pointer < 100)
            if (pointer == 0){
                ret++
            }
        }
        return ret
    }

    override fun solve2(input: String): Int {
        data class Node(val id: Int, val next: Int, val prev: Int)
        val list: Array<Node?> = (0..99).fold(arrayOfNulls(100)) {
            acc, id ->
                acc[id] = when {
                    id == 99 -> Node(id, 0, 98)
                    id == 0 -> Node(id, 1, 99)
                    else -> Node(id, id + 1, id - 1)
                }
            acc
        }

        var res = 0
        var current = list[50]
        input.split("\n").forEach { step ->
            when {
                step.startsWith('L') -> {
                    val toWalk = step.substringAfter('L').toInt()
                    (1..toWalk).forEach { _ ->
                        current = list[current!!.prev]
                        if (current!!.id == 0){
                            res++
                        }
                    }
                }
                step.startsWith('R') -> {
                    val toWalk = step.substringAfter('R').toInt()
                        (1..toWalk).forEach { _ ->
                            current = list[current!!.next]
                            if (current!!.id == 0){
                                res++
                            }
                        }
                    }
                else -> throw InputMismatchException("Input for day1 task 1 is invalid")
            }
        }
        return res
    }
}