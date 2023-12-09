import java.lang.RuntimeException
import java.util.concurrent.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

//Coroutines
// Lightweighted Threads
// Green Threads

//RxSchedulers

suspend fun doSomething(number: Int) {
    println("Doing Something $number")
    for (i in 1..3) {
        println(i)
    }
    println("Waiting $number")
    delay(100)
    println("Done $number")
}


@OptIn(ExperimentalCoroutinesApi::class)
suspend fun suspensionTest(scope: CoroutineScope, number: Int) {
    println("suspensionTest $number")
    val singleThreadedDispatcher = newSingleThreadContext("Test")
    val job1 = scope.launch(singleThreadedDispatcher) {
        doSomething(1)
    }
    val job2 = scope.launch {
        withContext(singleThreadedDispatcher) {
            doSomething(2)
        }
    }
    val job3 = scope.launch(singleThreadedDispatcher) {
        doSomething(3)
    }

    job1.join()
    job2.join()
    job3.join()


    println("suspensionTest Completed $number")
}

fun doSomethingWithThreads() {
    println("Doing Something")
    for (i in 1..3) {
        println(i)
    }
    println("Waiting")
    Thread.sleep(100)
    println("Done")
}

suspend fun getSomething() : Int{
    delay(100)

    return 10
}

fun main() = runBlocking {

    val job = SupervisorJob()

    val myScope = CoroutineScope(Dispatchers.IO + job)

    myScope.launch {
        suspensionTest(myScope, 1)
        throw RuntimeException("something broke")
    }
    myScope.launch(Dispatchers.IO) {
        suspensionTest(myScope,2)
    }

    val something = myScope.async {
        getSomething()
    }

    val valueOfSomething = something.await()
    println(valueOfSomething)

    job.join()
}