package dev.rivu.bookstore.data


class BooksLocalDS {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book): Boolean {
        val existing = books.any {
            it.id.equals(book.id, ignoreCase = true)
        }

        if (existing) {
            return false
        }

        return books.add(book)
    }

    fun getBooks(): List<Book> = books

    fun getBook(id: String): Book? = books.firstOrNull {
        it.id.equals(id, ignoreCase = true)
    }

    fun getBooksByAuthor(authorName: String): List<Book> = books.filter {
        it.authors.any { author ->
            author.name.equals(authorName, ignoreCase = true)
        }
    }
}