package dev.rivu.bookstore.data

import arrow.core.computations.ResultEffect.bind
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooksLocalDSTest {

    lateinit var booksLocalDS: BooksLocalDS
    lateinit var listOfBooks: MutableList<Book>

    @BeforeEach
    fun setUp() {
        listOfBooks = mutableListOf()
        booksLocalDS = BooksLocalDS(listOfBooks)
    }

    @AfterEach
    fun cleanup() {
        listOfBooks.clear()
    }

    @Test
    fun addBook() {
        val dummyBook = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )
        val status = booksLocalDS.addBook(
            dummyBook
        )

        assert(status)
        assertEquals(1, listOfBooks.size)
        assertEquals(dummyBook, listOfBooks[0])
    }

    @Test
    fun addExistingBook() {
        val dummyBook = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )
        val status1 = booksLocalDS.addBook(
            dummyBook
        )
        val status2 = booksLocalDS.addBook(
            dummyBook
        )

        assert(status1)
        assertFalse(status2)
        assertEquals(1, listOfBooks.size)
        assertEquals(dummyBook, listOfBooks[0])
    }

    @Test
    fun getBooksEmpty() {
        val result = booksLocalDS.getBooks()
        assert(result.isLeft())
    }

    @Test
    fun getBooks3Items() {
        val dummyBook1 = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )
        booksLocalDS.addBook(
            dummyBook1
        )
        val dummyBook2 = Book(
            id = "test1234",
            publisher = "Some Other publisher",
            authors = emptyList(),
            title = "Test Book 2"
        )
        booksLocalDS.addBook(
            dummyBook2
        )
        val dummyBook3 = Book(
            id = "test12132",
            publisher = "Some Other publisher",
            authors = emptyList(),
            title = "Test Book 3"
        )
        booksLocalDS.addBook(
            dummyBook3
        )



        val result = booksLocalDS.getBooks()
        assert(result.isRight())
        assertEquals(listOf(dummyBook1, dummyBook2, dummyBook3), result.getOrNull())
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