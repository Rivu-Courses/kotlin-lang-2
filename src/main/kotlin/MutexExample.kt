import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object MutexExample {
    private val mutex: Mutex = Mutex()
    var counter: Int = 0
        private set

    suspend fun increment(): Int {
        return mutex.withLock {
            ++counter
        }
    }
}

fun main() = runBlocking {

    val myScope = CoroutineScope(Dispatchers.IO)

    val job1 = myScope.launch {
        repeat(10) {
            delay(100)
            println("Counter Value from Coroutine a =  ${MutexExample.increment()}")
        }
    }
    val job2 = myScope.launch {
        repeat(10) {
            delay(100)
            println("Counter Value from Coroutine b =  ${MutexExample.increment()}")
        }
    }
    val job3 = myScope.launch {
        repeat(10) {
            delay(200)
            println("Counter Value from Coroutine b =  ${MutexExample.increment()}")
        }
    }


    job1.join()
    job2.join()
    job3.join()
    delay(500)
    println("Counter Value at the end =  ${MutexExample.counter}")
}