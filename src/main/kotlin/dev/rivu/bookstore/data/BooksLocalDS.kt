package dev.rivu.bookstore.data

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
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
            println("existing $books")
            return false
        }


        val status = books.add(book)
        println("added $books")
        return status
    }

    override fun getBooks(): Either<Throwable, List<Book>> = either {
        ensure(books.isNotEmpty()) {
            println("Empty List $books")
            RuntimeException("No books Available")
        }
        println("List $books")
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

