package dev.rivu.bookstore.presentation

import arrow.core.Either
import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksRepo
import dev.rivu.bookstore.di.BooksAppScope
import dev.rivu.bookstore.presentation.BookStoreStates.Options
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@BooksAppScope
@Inject
class BooksViewModel(
    private val booksRepo: BooksRepo
) {
    private val _booksState: MutableStateFlow<BookStoreStates> = MutableStateFlow(BookStoreStates.Options)
    val booksState: StateFlow<BookStoreStates> = _booksState.asStateFlow()

    fun addBookScreen(
    ) {
        _booksState.update {
            BookStoreStates.AddBook.Adding
        }
    }

    fun authorInputScreen(
    ) {
        _booksState.update {
            BookStoreStates.AuthorInputScreen
        }
    }

    fun addBook(
        book: Book
    ) {
        val isSuccess = booksRepo.addBook(book)

        _booksState.update {
            if (isSuccess) {
                BookStoreStates.AddBook.AddSuccess
            } else {
                BookStoreStates.AddBook.AddError
            }
        }
    }

    fun backToOptions() {
        _booksState.update {
            BookStoreStates.Options
        }
    }

    fun getBookList() {
        BookStoreStates.BookList.Loading
        val booksResult = booksRepo.getBooks()
        _booksState.update {
            if (booksResult.isRight()) {
                BookStoreStates.BookList.Success((booksResult as Either.Right).value)
            } else {
                BookStoreStates.BookList.Error(booksResult.leftOrNull()?.stackTraceToString() ?: "Unknown Error")
            }
        }
    }

    fun getBooksByAuthor(authorName: String) {
        BookStoreStates.BookList.Loading
        val booksResult = booksRepo.getBooksByAuthor(authorName)
        _booksState.update {

            if (booksResult.isRight()) {
                BookStoreStates.BookList.Success((booksResult as Either.Right).value)
            } else {
                BookStoreStates.BookList.Error(booksResult.leftOrNull()?.stackTraceToString() ?: "Unknown Error")
            }
        }
    }

    fun goBack() {
        _booksState.update {
            Options
        }
    }
}

