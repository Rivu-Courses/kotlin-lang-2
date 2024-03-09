package dev.rivu.bookstore.data

import arrow.core.Either
import arrow.core.catch
import arrow.core.recover
import dev.rivu.bookstore.di.BooksAppScope
import me.tatarka.inject.annotations.Inject


class BooksRepo(
    private val localDS: BooksDS,
    private val remoteDS: BooksDS,
) {


    fun addBook(book: Book): Boolean {
        return localDS.addBook(book)
    }

    fun getBooks(): Either<Throwable, List<Book>> = localDS.getBooks().recover {
        remoteDS.getBooks().bind()
    }

    fun getBook(id: String): Either<Throwable, Book> = localDS.getBook(id).recover {
        remoteDS.getBook(id).bind()
    }

    fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = localDS.getBooksByAuthor(authorName).recover {
        remoteDS.getBooksByAuthor(authorName).bind()
    }

    fun searchBooks(query: String): Either<Throwable, List<Book>> = localDS.searchBooks(query).recover {
        remoteDS.searchBooks(query).bind()
    }
}