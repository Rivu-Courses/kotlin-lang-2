package dev.rivu.bookstore.data

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooksRepoTest {

    lateinit var booksLocalDS: BooksDS
    lateinit var booksRemoteDS: BooksDS
    lateinit var booksRepo: BooksRepo

    @BeforeEach
    fun setUp() {
        booksLocalDS = mockk()
        booksRemoteDS = mockk()

        booksRepo = BooksRepo(
            localDS = booksLocalDS,
            remoteDS = booksRemoteDS
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

        every {
            booksLocalDS.addBook(
                any()
            )
        } returns false

        every {
            booksLocalDS.addBook(
                dummyBook
            )
        } returns true

        val result = booksRepo.addBook(dummyBook)
        assert(result)
    }

    @Test
    fun addBookBehaviour() {//Should not rely on behaviour test
        val dummyBook = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )

        every {
            booksLocalDS.addBook(
                any()
            )
        } returns true

        val result = booksRepo.addBook(dummyBook)
        assert(result)
        verify(
            exactly = 1
        ) {
            booksLocalDS.addBook(dummyBook)
        }
    }

    @Test
    fun getBooks() {
        assert(false)
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