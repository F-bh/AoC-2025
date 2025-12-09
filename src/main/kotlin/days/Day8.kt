package days

import jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent

open class Day8 : Day(8) {
  data class JunctionBox( val id: Int, val x: Long, val y: Long, val z: Long, var parent: JunctionBox? = null) {
    fun distanceTo(to: JunctionBox): Long {
      val xDiff = this.x - to.x
      val yDiff = this.y - to.y
      val zDiff = this.z - to.z

      return ((xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff))
    }
  }

  tailrec fun getRoot(input: JunctionBox): JunctionBox {
    if (input.parent == null) return input
    return getRoot(input.parent!!)
  }

  fun parse(input: String): List<JunctionBox> = input.lines().withIndex().map {
    val split = it.value.split(",")
    JunctionBox(it.index,split[0].toLong(), split[1].toLong(), split[2].toLong())
  }

  fun merge(a: JunctionBox, b: JunctionBox) {
    a.parent = getRoot(b)
  }

  fun createConnections(input: List<JunctionBox>): List<JunctionBox>{
    val connections = mutableListOf<JunctionBox>()
    x@for (a in input) {
      var shortestParent: JunctionBox? = null
      for (b in input) {
        if (b === a) continue

        //check for duplicate connections i.e A->B; B->A
        for (connection in connections){
          if (connection.parent != null){
            if (connection.parent!!.x == a.x && connection.parent!!.y == a.y && connection.parent!!.z == a.z){
              continue@x
            }
          }
        }

        if (shortestParent == null){
          shortestParent = b
          continue
        }

        if (a.distanceTo(b) < a.distanceTo(shortestParent)) shortestParent = b

      }
      a.parent = shortestParent
      connections.add(a)
    }

    return connections
  }

  override fun solve1(input: String): Number {
    val junctionBoxes = parse(input)
    val connectedBoxes = createConnections(junctionBoxes)

    val sortedByConnectionLength = connectedBoxes.sortedBy { it.distanceTo(it.parent!!) }
    val topTen = sortedByConnectionLength.take(10)
    val groupd = topTen.groupBy { getRoot(it) }

    //for (connection in connectedBoxes){
    //  merge(connection, connection.parent!!)
    //}

    return 0
  }


  override fun solve2(input: String): Number {
    TODO("Not yet implemented")
  }

}