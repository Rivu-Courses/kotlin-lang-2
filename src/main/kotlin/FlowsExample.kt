import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val flow = flowOf(1, 2, 3, 4, 5)


    flow
        .map {
            println("map1 "+Thread.currentThread().name)
            it*2
        }
        .flowOn(Dispatchers.IO)
        .map {
            println("map2 "+Thread.currentThread().name)
            "Updated Value ${it*2}"
        }
        .flowOn(newSingleThreadContext("my-thread"))
        .collect {
            println(Thread.currentThread().name)
            println("Collected $it")
        }

    withContext(Dispatchers.IO) {
        flow.collect {
            println(Thread.currentThread().name)
            println(it)
        }
    }

    flow
        .flatMapMerge {
            getData(it)
        }
        .collect {
            println("Received $it")
        }
}

fun getData(count: Int) = flow<Int> {
    println("Creating for $count")
    repeat(count) {
        emit(it)
    }
}