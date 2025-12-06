package days

import java.util.InputMismatchException


open class Day6 : Day(6) {
  enum class Operation() {
    Multiply {
      override fun apply(operands: List<Long>) = operands.drop(1).fold(operands.first()) { acc, it -> acc * it }
    },
    Add {
      override fun apply(operands: List<Long>) = operands.fold(0L) { acc, it -> acc + it }
    };

    abstract fun apply(operands: List<Long>): Long
  }

  data class Problem(val operands: MutableList<Long>, var operation: Operation?) {
    fun solve() = this.operation!!.apply(this.operands)
  }

  fun parse(input: String): List<Problem> {
    val ret = mutableListOf<Problem>()
    for (line in input.lines()) {
      val split = line.split(" ").filter { !it.isBlank() }.filter { !it.isEmpty() }
      for ((index, text) in split.withIndex()) {
        if (ret.size <= index) ret.add(Problem(mutableListOf(text.toLong()), null))
        else {
          when (text) {
            "*" -> ret[index].operation = Operation.Multiply
            "+" -> ret[index].operation = Operation.Add
            else -> ret[index].operands.add(text.toLong())
          }
        }
      }
    }

    return ret
  }

  fun parse2(input: String): List<Problem> {
    val operations = input.lines().last().filter { it != ' ' }.map {
      when (it){
        '*' -> Operation.Multiply
        '+' -> Operation.Add
        else -> throw InputMismatchException("invalid operation: $it")
    }}

    val lines = input.lines().dropLast(1)
    val transposedLines = Array(lines[0].length){" "}
    for (line in lines){
      for ((index, char) in line.withIndex()){
       transposedLines[index] = transposedLines[index] + char
      }
    }

    var lastOp = mutableListOf<String>()
    val ops = mutableListOf<MutableList<Long>>()
    for (line in transposedLines){
      if (line.isBlank()){
        val operands = lastOp.map {it.trim().toLong()}.toMutableList()
        ops.add(operands)
        lastOp = mutableListOf()
        continue
      }
      lastOp.add(line)
    }
    val operands = lastOp.map {it.trim().toLong()}.toMutableList()
    ops.add(operands)

    val ret = operations.foldIndexed(mutableListOf<Problem>()){ ix, acc, it ->
      acc.add(Problem(ops[ix].asReversed(),it ))
      acc
    }


    return ret.reversed()
  }

  override fun solve1(input: String) = parse(input).fold(0L) { acc, op ->
    acc + op.solve()
  }


  override fun solve2(input: String) = parse2(input).fold(0L) { acc, op ->
    acc + op.solve()
  }
}