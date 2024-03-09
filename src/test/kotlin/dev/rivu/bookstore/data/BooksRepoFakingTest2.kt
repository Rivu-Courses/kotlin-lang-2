package dev.rivu.bookstore.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.rivu.bookstore.data.factory.BooksFactory
import dev.rivu.bookstore.data.fakes.FakeBooksDS
import dev.rivu.bookstore.data.fakes.FakeBooksSuccessDS
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.property.arbitrary.next
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooksRepoFakingTest2 {

    lateinit var localBooksDS: FakeBooksDS
    lateinit var remoteBooksDS: FakeBooksSuccessDS
    lateinit var booksRepoSUT: BooksRepo

    @BeforeEach
    fun setUp() {
        localBooksDS = FakeBooksDS()
        remoteBooksDS = FakeBooksSuccessDS()

        booksRepoSUT = BooksRepo(
            localDS = localBooksDS,
            remoteDS = remoteBooksDS
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

        localBooksDS.stubAddBookResult(true)

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
        localBooksDS.stubAddBookResult(false)

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
        localBooksDS.stubBookList(Either.Right(stubBooksList))

        val result = booksRepoSUT.getBooks()
        assert(result.isRight())
        assertEquals(stubBooksList, result.getOrNull())

    }

    @Test
    fun getBooksFailureScenario() {

        localBooksDS.stubBookList(Either.Left(RuntimeException()))

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

    @Test
    fun searchBookSuccessScenario() {
        val commonBooksList = BooksFactory.commonTitleBooksGeneratorForSearch.next()

        localBooksDS.stubSearchBook(Either.Right(commonBooksList))

        val query = commonBooksList[0].title.substringBefore(" ")

        val result = booksRepoSUT.searchBooks(query)

        assertTrue(result.isRight())
        assertEquals(commonBooksList, result.getOrNull())
    }

    @Test
    fun searchBookFailureScenario() {
        val commonBooksList = BooksFactory.commonTitleBooksGeneratorForSearch.next()
        val randomBooksList = BooksFactory.booksGenerator.next()

        val totalBooksList = commonBooksList + randomBooksList
        localBooksDS.stubSearchBook(Either.Left(RuntimeException()))

        val result = booksRepoSUT.searchBooks("hsbchjsbd")

        assertTrue(result.isLeft())
    }

    @Test
    fun searchBookRemoteDSSuccessScenario() {
        val commonBooksList = BooksFactory.commonTitleBooksGeneratorForSearch.next()

        localBooksDS.stubSearchBook(Either.Left(RuntimeException()))
        remoteBooksDS.stubSearchBook(commonBooksList)

        val query = commonBooksList[0].title.substringBefore(" ")

        val result = booksRepoSUT.searchBooks(query)

        result.shouldBeRight()

        assertTrue(result.isRight())
        assertEquals(commonBooksList, result.getOrNull())
    }
}