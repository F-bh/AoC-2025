package days

open class Day2 : Day(2) {
    override fun solve1(input: String): Long {
        return input
            .split(",")
            .map {
                val split = it.split("-")
                Pair(split[0], split[1])
            }
            .fold(0, { acc, it ->
                var temp = 0L
                for (x in it.first.toLong()..it.second.toLong()) {
                    val str = x.toString()
                    val fh = str.take(str.length / 2)
                    val sh = str.substring(str.length / 2)
                    temp += when {
                        str.startsWith('0') -> x
                        fh == sh -> x
                        else -> 0
                    }
                }
                acc + temp
            })
    }

    override fun solve2(input: String): Long {
        return input
            .split(",")
            .map {
                val split = it.split("-")
                Pair(split[0], split[1])
            }
            .fold(0, { acc: Long, it: Pair<String, String> ->
                var temp = 0L
                for (x in it.first.toLong()..it.second.toLong()) {
                    val str = x.toString()
                    val fh = str.take(str.length / 2)
                    val sh = str.substring(str.length / 2)
                    if (fh == sh) {
                        temp += x
                        continue
                    }
                    assert(str.length != 1)
                    var isInvalid = false
                    window@for (y in 1..str.length / 2) {
                        val window = str.take(y)
                        if (str.length % window.length != 0) continue@window

                        for (windowIndex in 0..str.length step window.length) {
                            if ((windowIndex + window.length) > str.length){
                                when {
                                    isInvalid  -> {break@window}
                                    else -> break
                                }
                            }

                            val compare = str.substring(windowIndex, windowIndex + window.length)
                            isInvalid = when {
                                compare == window -> true
                                else -> false
                            }
                            if (!isInvalid) continue@window
                        }
                    }
                    if (isInvalid) temp += x
                }
                acc + temp
            }
        )
    }
}