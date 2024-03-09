package dev.rivu.bookstore.data.fakes

import arrow.core.Either
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksDS

class FakeBooksDS(

): BooksDS {

    private lateinit var booksStub: Either<Throwable, List<Book>>
    private lateinit var searchBooksStub: Either<Throwable, List<Book>>
    private lateinit var bookStub: Either<Throwable, Book>
    private var addBookResult: Boolean = false

    fun stubBook(book: Either<Throwable, Book>) {
        bookStub = book
    }
    fun stubBookList(book: Either<Throwable, List<Book>>) {
        booksStub = book
    }
    fun stubSearchBook(book: Either<Throwable, List<Book>>) {
        searchBooksStub = book
    }

    fun stubAddBookResult(result: Boolean) {
        addBookResult = result
    }

    override fun addBook(book: Book): Boolean {
        return addBookResult
    }

    override fun getBooks(): Either<Throwable, List<Book>> = booksStub

    override fun getBook(id: String): Either<Throwable, Book> = bookStub

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = booksStub

    override fun getBooksByPublisher(publisher: String): Either<Throwable, List<Book>> {
        return booksStub
    }

    override fun searchBooks(query: String): Either<Throwable, List<Book>> {
        return searchBooksStub
    }
}