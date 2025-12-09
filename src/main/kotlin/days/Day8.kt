package days

import kotlin.math.sqrt

open class Day8 : Day(8) {
  data class JunctionBox(val x: Long, val y: Long, val z: Long) {
    fun distanceTo(to: JunctionBox): Long {
      val xDiff = this.x - to.x
      val yDiff = this.y - to.y
      val zDiff = this.z - to.z

      return ((xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff))
    }
  }

  data class Connection(var parentIndex: Int, var index: Int, val distance: Long )

  fun parse(input: String): List<JunctionBox> = input.lines().map {
    val split = it.split(",")
    JunctionBox(split[0].toLong(), split[1].toLong(), split[2].toLong())
  }

   fun findParent(parents: Map<Int, Int>, toFind: Int): Int {
    if (parents[toFind] == toFind) return toFind

    parents[toFind] == findParent(parents, parents[toFind]!!)
    return parents[toFind]!!
  }

  fun union(parents: MutableMap<Int, Int>, a: Int, b: Int) {
    val aParent = parents[a]
    // make b's parent a's parent joining the two circuits
    parents[b] = aParent!!
  }

  override fun solve1(input: String): Number {
    val junctionBoxes = parse(input)
    val distances = mutableListOf<Connection>()
    val parents = junctionBoxes.indices.fold(mutableMapOf<Int, Int>()) { acc, it ->
      acc[it] = it
      acc
    }

    //calculate distances between all nodes
    for ((a, point) in junctionBoxes.withIndex()) {
      for ((b, compareTo) in junctionBoxes.withIndex()) {
        val distance = point.distanceTo(compareTo)
        distances.add(Connection(a, b, distance))
      }
    }

    val sorted = distances.sortedByDescending { it.distance }

    for (pair in 0..10) {
      val (a, b, _) = sorted[pair]
      if (findParent(parents, a) == findParent(parents, b)) continue

      //make sure a and b have the same root
      union(parents, a, b)
    }

    val sizes = mutableMapOf<Int, Int>()

    for (node in parents.values) {
      val root = findParent(parents, node)
      if (sizes[root] == null) {
        sizes[root] = 1
        continue
      }
      sizes[root] = sizes[root]!! + 1
    }

    return sizes.values.sorted().take(3).fold(1) { acc, i -> acc * i }
  }


  override fun solve2(input: String): Number {
    TODO("Not yet implemented")
  }

}