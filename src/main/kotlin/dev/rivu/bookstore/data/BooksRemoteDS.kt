package dev.rivu.bookstore.data

import arrow.core.Either


class BooksRemoteDS private constructor() : BooksDS {

    override fun addBook(book: Book): Boolean {
        TODO()
    }

    override fun getBooks(): Either<Throwable, List<Book>> = Either.catch {
        TODO()
    }

    override fun getBook(id: String): Either<Throwable, Book> = Either.catch {
        TODO()
    }

    override fun getBooksByAuthor(authorName: String): Either<Throwable, List<Book>> = Either.catch {
        TODO()
    }

    override fun getBooksByPublisher(publisher: String): Either<Throwable, List<Book>> {
        TODO("Not yet implemented")
    }

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
