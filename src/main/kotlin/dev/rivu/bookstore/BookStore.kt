package dev.rivu.bookstore

import dev.rivu.bookstore.data.DataModule
import dev.rivu.bookstore.presentation.BookStoreStates
import dev.rivu.bookstore.presentation.BooksViewModel
import dev.rivu.bookstore.presentation.PresentationModule
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.system.exitProcess

fun main() = runBlocking {

    startKoin {
        modules(
            PresentationModule, DataModule
        )
    }


    BookStoreApp().runApp()

    stopKoin()
}

class BookStoreApp : KoinComponent {
    val viewModel: BooksViewModel by inject<BooksViewModel>()


    suspend fun runApp() {
        viewModel.booksState.collect { latestState ->
            when(latestState) {
                is BookStoreStates.Options -> {
                    printOptions(latestState.options) { selection ->
                        when (selection) {
                            0 -> viewModel.addBookScreen()
                            1 -> viewModel.getBookList()
                            2 -> viewModel.authorInputScreen()
                            3 -> {
                                stopKoin()
                                exitProcess(0)
                            }
                        }
                    }

                }
                is BookStoreStates.AddBook -> {
                    println("TODO")
                }
                is BookStoreStates.AuthorInputScreen -> {
                    println("TODO")
                }
                is BookStoreStates.BookList -> {
                    println("TODO")
                }
            }
        }
    }

    fun printOptions(
        options: Set<String>,
        onSelected: (Int) -> Unit
    ) {
        println("Select an option from here")
        options.forEachIndexed { index, option ->
            println("$index -> $option")
        }
        println("3 -> Exit")
        val input = readLine()?.toIntOrNull()

        if (input == null) {
            printOptions(options, onSelected)
        } else {
            onSelected(input)
        }
    }
}

//Functionalities
//Give below options to the user to select from
// add book
// get books list (all books stored)
// get book by author