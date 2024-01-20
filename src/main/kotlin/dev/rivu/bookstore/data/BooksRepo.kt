package dev.rivu.bookstore.data

import dev.rivu.bookstore.di.BooksAppScope
import javax.inject.Inject
import javax.inject.Named


@BooksAppScope
class BooksRepo @Inject constructor(
    @Named("localDS") private val localDS: BooksDS,
    @Named("remoteDS") private val remoteDS: BooksDS,
) {

    fun addBook(book: Book): Boolean {
        return localDS.addBook(book)
    }

    fun getBooks(): List<Book> = localDS.getBooks()

    fun getBook(id: String): Book? = localDS.getBook(id)

    fun getBooksByAuthor(authorName: String): List<Book> = localDS.getBooksByAuthor(authorName)
}