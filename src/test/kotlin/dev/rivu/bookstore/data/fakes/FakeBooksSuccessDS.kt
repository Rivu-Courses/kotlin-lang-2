package dev.rivu.bookstore.data.fakes

import arrow.core.Either
import arrow.core.raise.either
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksDS

class FakeBooksSuccessDS(

): BooksDS {

    private var booksStub: List<Book> = emptyList()
    private var bookStub: Book? = null
    private var searchBooksStub: List<Book>? = null


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
    fun stubSearchBook(book: List<Book>) {
        searchBooksStub = book
    }

    override fun getBook(id: String): Either<Throwable, Book> = bookStub?.let {
        Either.Right(bookStub!!)
    } ?: throw UnsupportedOperationException("book stub not created")

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = Either.Right(booksStub)

    override fun getBooksByPublisher(publisher: String): Either<Throwable, List<Book>> {
        TODO("Not yet implemented")
    }

    override fun searchBooks(query: String): Either<Throwable, List<Book>> = searchBooksStub?.let {
        Either.Right(searchBooksStub!!)
    } ?: throw UnsupportedOperationException("book stub not created")
}