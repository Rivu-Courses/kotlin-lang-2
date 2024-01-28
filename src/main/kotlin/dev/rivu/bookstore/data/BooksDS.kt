package dev.rivu.bookstore.data

import arrow.core.Either

interface BooksDS {
    fun addBook(book: Book): Boolean

    fun getBooks(): Either<Throwable, List<Book>>

    fun getBook(id: String): Either<Throwable, Book>

    fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>>
}