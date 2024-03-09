package dev.rivu.bookstore.data.factory

import dev.rivu.bookstore.data.Author
import dev.rivu.bookstore.data.Book
import io.kotest.property.Arb
import io.kotest.property.arbitrary.UUIDVersion.ANY
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.kotest.property.arbitrary.string
import io.kotest.property.arbitrary.uuid
import io.kotest.property.arbs.name

object BooksFactory {

    val nameGenerator = Arb.name()
    val publisherGenerator = Arb.string(minSize = 10, maxSize = 12)
    val idGenerator = Arb.uuid(ANY)

    val authorGenerator = arbitrary {
        val name = nameGenerator.next()
        Author(
            name = name.toString()
        )
    }

    val authorsGenerator = Arb.list<Author>(authorGenerator)

    val bookGenerator = arbitrary {
        val id = idGenerator.next()
        Book(
            id = idGenerator.next().toString(),
            publisher = publisherGenerator.next(),
            authors = authorsGenerator.next(),
            title = nameGenerator.next().first.name
        )
    }
    val booksGenerator = Arb.list<Book>(bookGenerator)

    val commonTitleBooksGeneratorForSearch = arbitrary<List<Book>> {
        val publisher = publisherGenerator.next()
        val titlePrefix = nameGenerator.next().first.name
        listOf(10).map {
            Book(
                id = idGenerator.next().toString(),
                publisher = publisher,
                authors = authorsGenerator.next(),
                title = titlePrefix + " " + Arb.string().next()
            )
        }
    }

    val commonAuthorBooksGeneratorForSearch = arbitrary<List<Book>> {
        val publisher = publisherGenerator.next()
        val authors = authorsGenerator.next()
        listOf(10).map {
            Book(
                id = idGenerator.next().toString(),
                publisher = publisher,
                authors = authors,
                title = nameGenerator.next().first.name
            )
        }
    }
}