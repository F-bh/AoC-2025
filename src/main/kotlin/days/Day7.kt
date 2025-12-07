package days

import kotlin.math.max
import kotlin.streams.toList

open class Day7: Day(7) {
  sealed interface Node {
    val x: Int
    val y: Int
  }
  data class Emitter(override val x: Int, override val y: Int): Node
  data class Splitter(override val x: Int, override val y: Int): Node
  data class Beam(val pos: Position, var ways: Long)
  data class Position(var x: Int, var y: Int)

  fun parse(input: String): MutableList<Node>{
    val nodes = mutableListOf<Node>()
    for ((yIx, line) in input.lines().withIndex()){
      for ((xIx, ch) in line.withIndex()){
        nodes.add(when (ch){
          'S' -> Emitter(xIx, yIx)
          '^' -> Splitter(xIx, yIx)
          else -> continue
        })
      }
    }
    return nodes
  }


  override fun solve1(input: String): Number {
    val nodes = parse(input)
    val maxY = nodes.maxOf { it.y + 1}
    var split = 0
    var beams = listOf(Beam(Position(nodes.first().x, nodes.first().y+1), 0))

    while (true) {
      val newBeams = mutableListOf<Beam>()
      for (beam in beams) {
        if (nodes.any { it.x == beam.pos.x && it.y == beam.pos.y }){
          split++
          newBeams.add(Beam(Position(beam.pos.x-1, beam.pos.y+1), 0))
          beam.pos.x++
        }
        beam.pos.y++
        newBeams.add(beam)
      }

      beams = newBeams.distinct()

      if (beams.all { it.pos.y >= maxY }) break
    }

    return split
  }

  override fun solve2(input: String): Number {
    val nodes = parse(input)
    val maxY = nodes.maxOf { it.y + 1}
    var beams = listOf(Beam(Position(nodes.first().x, nodes.first().y+1), 1))
    var possibleBeams: Long

    while (true) {
      val newBeams = mutableListOf<Beam>()
      for (beam in beams) {
        if (nodes.any { it.x == beam.pos.x && it.y == beam.pos.y }){
          newBeams.add(Beam(Position(beam.pos.x-1, beam.pos.y+1), beam.ways))
          beam.pos.x++
        }
        beam.pos.y++
        newBeams.add(beam)
      }

      val grouped = newBeams.groupBy { it.pos }
      val temp = mutableListOf<Beam>()
      for (key in grouped.keys){
        val values = grouped[key]
        val temp2 = values!!.first().copy(ways = 0)
        for (x in values){
          temp2.ways += x.ways
        }
        temp.add(temp2)
      }

      beams = temp

      possibleBeams = temp.fold(0) {acc, beam -> acc + beam.ways}
      if (beams.all { it.pos.y >= maxY }) {
        break
      }
    }

    return possibleBeams
  }
}