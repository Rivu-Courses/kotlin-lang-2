import kotlin.random.Random

fun main() {
    sequenceOf(1,2,3,4,5)
        .forEach {
            println("Seq 1 "+it)
        }

    val random = Random(10)

    val sequence = sequence {
        println("Yielding 1st value")
        yield(10)
        println("Yielding 2nd value")
        yield(11)
    }

    val firstValue = sequence.first()
    println("First Value $firstValue")
}