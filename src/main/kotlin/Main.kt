fun main(args: Array<String>) {
    println("Hello World!")


    //X.doSoemthingElse()()

    /*val instance = GenericClass<Int>(
        listOf(
            19,
            1,
            5,
            3,
            9,
            10,
            13,
            2,
            4,
        )
    )

    instance.print()
    val sortedList = instance.sort()
    instance.print()

    println("Sorted List Print $sortedList")


    val map = mapOf(
        1 to "Something",
        3 to "Something Else",
        4 to "Something 4",
        9 to "Something 9",
        9 to "Something bchjbjhsbjhs",
    )

    println("Map $map")
    println("Map 3${map[3]}")
    println("Map 9${map[9]}")

    val map2: MutableMap<Int?, List<String>> = mutableMapOf(
        1 to listOf("Something"),
        3 to listOf("Something Else"),
        4 to listOf("Something 4"),
        9 to listOf("Something 9"),
        9 to listOf("Something bchjbjhsbjhs"),
    )

    map2[9] = map2[9]!! + "Something 9 runtime"
    println("Map2 9 ${map2[9]}")

    map2[null] = listOf("Something asbxjsax 9 runtime")
    println("Map2 null ${map2[null]}")

    map2[null] = listOf("dskcbsjkdcbhjcdb nscjnc")
    println("Map2 null ${map2[null]}")


    val set = setOf(
        1,1,3,9,1,2,3,4,5,9,5,6
    )

    println("Set $set")

    val array = intArrayOf(
        1,1,3,9,1,2,3,4,5,9,5,6
    )
    val arrayToSet = array.toSet()
    val uniqueArray = arrayToSet.toIntArray()
    println("Duplicates Removed")
    uniqueArray.forEach {
        println(it)
    }

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}


class GenericClass<T>(val array: List<T>) where T : Comparable<T>, T: Number  {

    fun print(value: T) {
        println("$value")
    }

    fun sort(): List<T> {
        println("sorted")
        return array.sortedBy {
            it
        }

    }

    fun print() {
        array.forEach {
            println(it)
        }
    }

}

class X {
    fun doSomething() {

    }

    companion object {
        fun doSoemthingElse(): ()->Unit {
            return {
                println("Lambda is called")
            }
        }
    }

     */

}

data class SomeData(
    val value: Int,
)