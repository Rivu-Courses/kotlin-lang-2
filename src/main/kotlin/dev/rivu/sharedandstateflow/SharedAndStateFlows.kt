package dev.rivu.sharedandstateflow

import dev.rivu.sharedandstateflow.BroadCaster._state
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    val viewModel = ViewModel()


    runBlocking {
        GlobalScope.launch {
            viewModel.countSeconds()
        }

        val job = GlobalScope.launch {
            viewModel.state.collect {
                println("Collected State $it")
            }
        }

        delay(10001)
        val job2 = GlobalScope.launch {
            viewModel.state.collect {
                println("Collected State 2 $it")
            }
        }




        GlobalScope.launch {
            BroadCaster.countSeconds()
        }

        val job3 = GlobalScope.launch {
            BroadCaster.state.collect {
                println("Collected BroadCaster $it")
            }
        }

        delay(9001)
        val job4 = GlobalScope.launch {
            BroadCaster.state.collect {
                println("Collected BroadCaster 2 $it")
            }
        }

        job.join()
        job2.join()
        job3.join()
        job4.join()
    }

}

class ViewModel {
    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> = _state

    suspend fun countSeconds() {
        repeat(10) {
            _state.update { oldValue ->
                oldValue+1
            }
            delay(1000)
        }
    }
}

object BroadCaster {
    val _state: MutableSharedFlow<Int> = MutableSharedFlow()
    val state: SharedFlow<Int> = _state.asSharedFlow()

    suspend fun countSeconds() {
        var count = 1
        repeat(10) {
            _state.emit(count++)
            delay(1000)
        }
    }
}
object Something {

    fun countSeconds(): Flow<Int> {
        return flow<Int> {
            emit(1)
        }
    }
}