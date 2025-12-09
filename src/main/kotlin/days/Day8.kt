package days

import kotlin.math.sqrt

open class Day8 : Day(8) {
  data class JunctionBox(val x: Long, val y: Long, val z: Long) {
    fun distanceTo(to: JunctionBox): Double {
      val xDiff = this.x - to.x
      val yDiff = this.y - to.y
      val zDiff = this.z - to.z

      return sqrt(((xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff)).toDouble())
    }
  }

  fun parse(input: String): List<JunctionBox> = input.lines().map {
    val split = it.split(",")
    JunctionBox(split[0].toLong(), split[1].toLong(), split[2].toLong())
  }

  override fun solve1(input: String): Number {
    val junctionBoxes = parse(input)
    val shortestConnections = mutableListOf<Pair<JunctionBox, JunctionBox>>()

    for (point in junctionBoxes) {
      var closest: Pair<JunctionBox, Double>? = null
      for (compareTo in junctionBoxes) {
        val distance = point.distanceTo(compareTo)
        when {
          distance < 1 -> continue
          closest == null -> closest = compareTo to point.distanceTo(compareTo)
          distance < closest.second -> closest = compareTo to distance
        }
      }
      shortestConnections.add(point to closest!!.first)
    }
    val sorted = shortestConnections.sortedBy { it.first.distanceTo(it.second) }

    val filteredDuplicates = mutableListOf<Pair<JunctionBox, JunctionBox>>()

    for (connection in sorted) {
      if (filteredDuplicates.contains(connection.second to connection.first)) {
        continue
      }
      filteredDuplicates.add(connection)
    }

    val tenShortest = filteredDuplicates.sortedBy { it.first.distanceTo(it.second) }.take(10)

    val circuits = mutableListOf<MutableList<JunctionBox>>()
    val alreadyAdded = mutableListOf<Pair<JunctionBox, JunctionBox>>()

    x@for (connection in tenShortest) {
      if (circuits.isEmpty()) {
        alreadyAdded.add(connection)
        circuits.add(mutableListOf(connection.first, connection.second))
        continue
      }

      var circuitsSize = circuits.size
      var index = 0
      while (index < circuitsSize) {
        val circuit = circuits[index]
        when {
          alreadyAdded.contains(connection) || alreadyAdded.contains(connection.second to connection.first) -> continue@x

          circuit.contains(connection.first) -> {
            circuit.add(connection.second)
            alreadyAdded.add(connection)
          }

          circuit.contains(connection.second) -> {
            circuit.add(connection.first)
            alreadyAdded.add(connection)
          }

          else -> {
            alreadyAdded.add(connection)
            circuits.add(mutableListOf(connection.first, connection.second))
          }
        }

        circuitsSize = circuits.size
        index++
      }
    }

    return circuits.take(3).fold(1) { acc, it -> acc * it.size }
  }

  override fun solve2(input: String): Number {
    TODO("Not yet implemented")
  }

}