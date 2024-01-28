package dev.rivu.bookstore.data

import dev.rivu.bookstore.di.BooksAppScope
import me.tatarka.inject.annotations.Inject


@BooksAppScope
@Inject
class BooksLocalDS : BooksDS {
    private val books = mutableListOf<Book>()

    override fun addBook(book: Book): Boolean {
        val existing = books.any {
            it.id.equals(book.id, ignoreCase = true)
        }

        if (existing) {
            return false
        }

        return books.add(book)
    }

    override fun getBooks(): List<Book> = books

    override fun getBook(id: String): Book? = books.firstOrNull {
        it.id.equals(id, ignoreCase = true)
    }

    override fun getBooksByAuthor(authorName: String): List<Book> = books.filter {
        it.authors.any { author ->
            author.name.equals(authorName, ignoreCase = true)
        }
    }
}

