package dev.rivu.bookstore.data

interface BooksDS {
    fun addBook(book: Book): Boolean

    fun getBooks(): List<Book>

    fun getBook(id: String): Book?

    fun getBooksByAuthor(authorName: String): List<Book>
}