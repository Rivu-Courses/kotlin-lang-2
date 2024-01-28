package dev.rivu.bookstore.data

import dev.rivu.bookstore.di.BooksAppScope
import me.tatarka.inject.annotations.Inject


class BooksRepo(
    private val localDS: BooksDS,
    private val remoteDS: BooksDS,
) {


    fun addBook(book: Book): Boolean {
        return localDS.addBook(book)
    }

    fun getBooks(): List<Book> = localDS.getBooks()

    fun getBook(id: String): Book? = localDS.getBook(id)

    fun getBooksByAuthor(authorName: String): List<Book> = localDS.getBooksByAuthor(authorName)
}