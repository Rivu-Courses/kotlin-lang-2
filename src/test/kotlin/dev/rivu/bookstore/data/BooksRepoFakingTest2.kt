package dev.rivu.bookstore.data

import arrow.core.Either
import dev.rivu.bookstore.data.fakes.FakeBooksDS
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooksRepoFakingTest2 {

    lateinit var booksDS: FakeBooksDS
    lateinit var booksRepoSUT: BooksRepo

    @BeforeEach
    fun setUp() {
        booksDS = FakeBooksDS()

        booksRepoSUT = BooksRepo(
            localDS = booksDS,
            remoteDS = booksDS
        )
    }

    @Test
    fun addBook() {
        val dummyBook = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )

        booksDS.stubAddBookResult(true)

        val result = booksRepoSUT.addBook(dummyBook)
        assert(result)
    }

    @Test
    fun addBookFailureScenario() {
        val dummyBook = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )

        //Given
        booksDS.stubAddBookResult(false)

        //When
        val result = booksRepoSUT.addBook(dummyBook)

        //Then
        assertFalse(result)
    }

    @Test
    fun getBooksSuccessScenario() {
        val stubBooksList = (1..10).map {
            Book(
                id = "test123$it",
                publisher = "Some publisher",
                authors = emptyList(),
                title = "Test Book"
            )
        }
        booksDS.stubBookList(Either.Right(stubBooksList))

        val result = booksRepoSUT.getBooks()
        assert(result.isRight())
        assertEquals(stubBooksList, result.getOrNull())

    }

    @Test
    fun getBooksFailureScenario() {

        booksDS.stubBookList(Either.Left(RuntimeException()))

        val result = booksRepoSUT.getBooks()
        assert(result.isLeft())
    }

    @Test
    fun getBook() {
        assert(false)
    }

    @Test
    fun getBooksByAuthor() {
        assert(false)
    }
}