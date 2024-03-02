package dev.rivu.bookstore.data

import arrow.core.Either
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BooksLocalDSDescribeSpecTest : DescribeSpec({
    lateinit var booksLocalDS: BooksLocalDS
    lateinit var listOfBooks: MutableList<Book>

    describe("getBooksByPublisher scenarios") {
        val dummyBook1 = Book(
            id = "test123",
            publisher = "Some publisher",
            authors = emptyList(),
            title = "Test Book"
        )
        val dummyBook3 = Book(
            id = "test12132",
            publisher = "Some Other publisher",
            authors = emptyList(),
            title = "Test Book 3"
        )
        val dummyBook2 = Book(
            id = "test1234",
            publisher = "Some Other publisher",
            authors = emptyList(),
            title = "Test Book 2"
        )

        var result: Either<Throwable, List<Book>>? = null

        beforeTest {
            listOfBooks = mutableListOf()
            booksLocalDS = BooksLocalDS(listOfBooks)

            listOfBooks.add(dummyBook1)

            listOfBooks.add(dummyBook2)
            listOfBooks.add(dummyBook3)
        }
        describe("success scenario") {


            result = booksLocalDS.getBooksByPublisher("Some Other publisher")

            it("should return the books of publisher") {
                val expected = Either.Right(listOf(dummyBook2, dummyBook3))
                result shouldBe expected
            }
        }
        describe("failure scenario") {


            result = booksLocalDS.getBooksByPublisher("jhbchjasdbc")

            it("should return failure result") {

                result?.isLeft() shouldBe true
            }

            it("Result should not have null value for Throwable") {
                result?.leftOrNull() shouldNotBe null
            }

            it("Throwable should have a proper message") {
                result?.leftOrNull()!! shouldHaveMessage "No books found with the author jhbchjasdbc"
            }
        }
    }
})