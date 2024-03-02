package dev.rivu.bookstore.data

import arrow.core.Either
import dev.rivu.bookstore.data.factory.BooksFactory
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.property.arbitrary.next

class BooksLocalDSBehaviourSpecTest : BehaviorSpec({
    lateinit var booksLocalDS: BooksLocalDS
    val listOfBooks = mutableListOf<Book>()

    val dummyBook1 = BooksFactory.bookGenerator.next()
    val dummyBook2 = dummyBook1.copy(title = BooksFactory.nameGenerator.next().toString(), authors = BooksFactory.authorsGenerator.next())
    val dummyBook3 = BooksFactory.bookGenerator.next()

    given("books list has dummyBook1, dummyBook2 and dummyBook3") {
        listOfBooks.add(dummyBook1)
        listOfBooks.add(dummyBook2)
        listOfBooks.add(dummyBook3)

        and("booksLocalDS refers to books list as local storage") {
            booksLocalDS = BooksLocalDS(listOfBooks)

            var result: Either<Throwable, List<Book>>? = null

            When("call getBooksByPublisher with an existing publisher") {
                result = booksLocalDS.getBooksByPublisher(dummyBook1.publisher)

                then("should return the books of publisher") {
                    result?.shouldBeRight(listOf(dummyBook1, dummyBook2))
                }
            }

            When("call getBooksByPublisher with a non-existing publisher") {
                result = booksLocalDS.getBooksByPublisher("hjdbchjdscbhjc")

                then("should return failure result") {
                    result?.shouldBeLeft(RuntimeException("No books found with the author hjdbchjdscbhjc"))
                }
            }
        }
    }
})