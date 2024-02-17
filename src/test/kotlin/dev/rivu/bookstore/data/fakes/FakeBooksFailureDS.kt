package dev.rivu.bookstore.data.fakes

import arrow.core.Either
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksDS

class FakeBooksFailureDS(
): BooksDS {
    override fun addBook(book: Book): Boolean {
        return false
    }

    override fun getBooks(): Either<Throwable, List<Book>> = Either.Left(UnsupportedOperationException("Book not found"))

    override fun getBook(id: String): Either<Throwable, Book> = Either.Left(UnsupportedOperationException("Book not found"))

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = Either.Left(UnsupportedOperationException("Book not found"))

    override fun getBooksByPublisher(publisher: String): Either<Throwable, List<Book>> {
        TODO("Not yet implemented")
    }
}