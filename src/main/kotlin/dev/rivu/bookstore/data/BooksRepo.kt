package dev.rivu.bookstore.data

import dev.rivu.bookstore.di.BooksAppScope
import me.tatarka.inject.annotations.Inject


@BooksAppScope
@Inject
class BooksRepo(
    private val dsMap: Map<String, BooksDS>
) {

    private val localDS: BooksDS
    private val remoteDS: BooksDS

    init {
        localDS = dsMap["local"] ?: throw RuntimeException("Unable to get localDS, check dependency graph")
        remoteDS = dsMap["remote"] ?: throw RuntimeException("Unable to get remoteDS, check dependency graph")
    }

    fun addBook(book: Book): Boolean {
        return localDS.addBook(book)
    }

    fun getBooks(): List<Book> = localDS.getBooks()

    fun getBook(id: String): Book? = localDS.getBook(id)

    fun getBooksByAuthor(authorName: String): List<Book> = localDS.getBooksByAuthor(authorName)
}