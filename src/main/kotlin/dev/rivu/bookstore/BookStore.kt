package dev.rivu.bookstore

import dev.rivu.bookstore.data.Author
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.di.DataComponent
import dev.rivu.bookstore.data.di.create
import dev.rivu.bookstore.di.AppComponent
import dev.rivu.bookstore.di.BooksAppScope
import dev.rivu.bookstore.di.create
import dev.rivu.bookstore.presentation.BookStoreStates
import dev.rivu.bookstore.presentation.BookStoreStates.AddBook
import dev.rivu.bookstore.presentation.BooksViewModel
import java.util.*
import kotlinx.coroutines.runBlocking
import me.tatarka.inject.annotations.Inject
import kotlin.system.exitProcess

fun main() = runBlocking {
    AppComponent::class.create(DataComponent::class.create()).app.runApp()
}

@BooksAppScope
@Inject
class BookStoreApp(val viewModel: BooksViewModel) {

    suspend fun runApp() {
        viewModel.booksState.collect { latestState ->
            when (latestState) {
                is BookStoreStates.Options -> {
                    printOptions(latestState.options) { selection ->
                        when (selection) {
                            0 -> viewModel.addBookScreen()
                            1 -> viewModel.getBookList()
                            2 -> viewModel.authorInputScreen()
                            3 -> {
                                exitProcess(0)
                            }
                        }
                    }

                }

                is BookStoreStates.AddBook -> {
                    when (latestState) {
                        is AddBook.Adding -> addBook(viewModel)
                        is AddBook.Loading -> println("Loading")
                        is AddBook.AddSuccess -> {
                            println("Successfully added the book. Press any key to go back")
                            readLine()
                            viewModel.goBack()
                        }
                        is AddBook.AddError -> {
                            println("Error adding the book. Press any key to go back")
                            readLine()
                            viewModel.goBack()
                        }
                    }
                }

                is BookStoreStates.AuthorInputScreen -> {
                    println("TODO")
                }

                is BookStoreStates.BookList -> {
                    when(latestState) {
                        is BookStoreStates.BookList.Loading -> println("Loading")
                        is BookStoreStates.BookList.Success -> {
                            println("Success")
                            latestState.books.forEach {
                                println(it)
                            }
                            println("Press any key to go back")
                            readLine()
                            viewModel.goBack()
                        }
                        is BookStoreStates.BookList.Error -> {
                            println("Error")
                            println(latestState.errorDetails)
                            println("Press any key to go back")
                            readLine()
                            viewModel.goBack()
                        }
                    }
                }
            }
        }
    }

    private fun addBook(viewModel: BooksViewModel) {
        println("Enter Title")
        val title = readlnOrNull()
        println("Enter Publisher Name")
        val publisher = readlnOrNull()
        println("Enter Authors names, comma separated")
        val authors = readlnOrNull()?.split(",")

        if (title.isNullOrBlank() || publisher.isNullOrBlank() || authors.isNullOrEmpty()) {
            println("Please Try Again")
            viewModel.goBack()
            return
        }

        val book = Book(
            id = UUID.randomUUID().toString(),
            authors = authors.map {
                Author(name = it)
            },
            publisher = publisher,
            title = title
        )
        viewModel.addBook(book)
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
/*
BooksApp
//Functionalities
//Give below options to the user to select from
// add book
// get books list (all books stored)
// get book by author
*/


/*
* IoC => Inversion of Control -> D of SOLID -> Dependency Inversion
* IoC / Dependency Inversion -> DI-> Dependency Injection, SL -> Service Locator
*   SL ->
*       When a class needs / wants a dependency it asks for it
*       It's always runtime
*       Constructor injection is also a type of SL
*       Class knows where it's dependency is coming from
*
*   DI ->
*       Class doesn't necessarily has to ask for dependency, it's provided preemptively
*       It's mostly compile-time, mostly with annotation processing
*       Class doesn't care or know where the dependency is coming from
*
* Dependency Graph
*   - Relevant for both DI and SL
*
*
* ClassA -> ClassB
* */
