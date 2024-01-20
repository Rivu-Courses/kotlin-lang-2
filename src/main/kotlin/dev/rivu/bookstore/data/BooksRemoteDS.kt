package dev.rivu.bookstore.data


class BooksRemoteDS private constructor() : BooksDS {

    override fun addBook(book: Book): Boolean {
        TODO()
    }

    override fun getBooks(): List<Book> = TODO()

    override fun getBook(id: String): Book? = TODO()

    override fun getBooksByAuthor(authorName: String): List<Book> = TODO()

    companion object Factory {
        private var instance: BooksRemoteDS? = null
        fun getInstance(): BooksRemoteDS {
            if (instance == null) {
                instance = BooksRemoteDS()
            }

            return instance!!
        }
    }
}
