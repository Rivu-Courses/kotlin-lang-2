package dev.rivu.bookstore.data.fakes

import arrow.core.Either
import arrow.core.raise.either
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksDS

class FakeBooksSuccessDS(

): BooksDS {

    private var booksStub: List<Book> = emptyList()
    private var bookStub: Book? = null

    fun stubBook(book: Book) {
        bookStub = book
    }
    fun stubBookList(book: List<Book>) {
        booksStub = book
    }

    override fun addBook(book: Book): Boolean {
        return true
    }

    override fun getBooks(): Either<Throwable, List<Book>> = either {
        booksStub
    }

    override fun getBook(id: String): Either<Throwable, Book> = bookStub?.let {
        Either.Right(bookStub!!)
    } ?: Either.Left(UnsupportedOperationException("Book not found"))

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = Either.Right(booksStub)

    override fun getBooksByPublisher(publisher: String): Either<Throwable, List<Book>> {
        TODO("Not yet implemented")
    }
}