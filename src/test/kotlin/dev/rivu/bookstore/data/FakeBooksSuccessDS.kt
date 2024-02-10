package dev.rivu.bookstore.data

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

class FakeBooksSuccessDS(
    private val books: MutableList<Book>
): BooksDS {
    override fun addBook(book: Book): Boolean {
        books.add(book)
        return true
    }

    override fun getBooks(): Either<Throwable, List<Book>> = either {
        books
    }

    override fun getBook(id: String): Either<Throwable, Book> = Either.catch {
        books.first {
            it.id.equals(id, ignoreCase = true)
        }
    }

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = either {
        val authorBooks = books.filter {
            it.authors.any { author ->
                author.name.equals(authorName, ignoreCase = true)
            }
        }

        ensure(authorBooks.isNotEmpty()) {
            RuntimeException("No books found with the author $authorName")
        }

        authorBooks
    }
}