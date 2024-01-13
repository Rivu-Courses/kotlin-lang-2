package dev.rivu.bookstore.data

class BooksRepo(
    private val localDS: BooksLocalDS
) {
    fun addBook(book: Book): Boolean {
        return localDS.addBook(book)
    }

    fun getBooks(): List<Book> = localDS.getBooks()

    fun getBook(id: String): Book? = localDS.getBook(id)

    fun getBooksByAuthor(authorName: String): List<Book> = localDS.getBooksByAuthor(authorName)
}