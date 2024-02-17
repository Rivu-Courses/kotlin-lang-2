package dev.rivu.bookstore.data

import dev.rivu.bookstore.data.fakes.FakeBooksFailureDS
import dev.rivu.bookstore.data.fakes.FakeBooksSuccessDS
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooksRepoFakingTest {

    lateinit var booksSuccessDS: FakeBooksSuccessDS
    lateinit var booksFailureDS: FakeBooksFailureDS
    lateinit var booksRepoSuccessSUT: BooksRepo
    lateinit var booksRepoFailureSUT: BooksRepo

    @BeforeEach
    fun setUp() {
        booksSuccessDS = FakeBooksSuccessDS()
        booksFailureDS = FakeBooksFailureDS()

        booksRepoSuccessSUT = BooksRepo(
            localDS = booksSuccessDS,
            remoteDS = booksSuccessDS
        )

        booksRepoFailureSUT = BooksRepo(
            localDS = booksFailureDS,
            remoteDS = booksFailureDS
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

        val result = booksRepoSuccessSUT.addBook(dummyBook)
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

        val result = booksRepoFailureSUT.addBook(dummyBook)
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
        booksSuccessDS.stubBookList(stubBooksList)

        val result = booksRepoSuccessSUT.getBooks()
        assert(result.isRight())
        assertEquals(stubBooksList, result.getOrNull())

    }

    @Test
    fun getBooksFailureScenario() {

        val result = booksRepoFailureSUT.getBooks()
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