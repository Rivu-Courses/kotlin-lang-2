package dev.rivu.bookstore.presentation

import dev.rivu.bookstore.data.Book
import dev.rivu.bookstore.data.BooksRepo
import dev.rivu.bookstore.di.BooksAppScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@BooksAppScope
class BooksViewModel @Inject constructor(
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
        val books = booksRepo.getBooks()
        _booksState.update {
            if (books.isNotEmpty()) {
                BookStoreStates.BookList.Success(books)
            } else {
                BookStoreStates.BookList.Error
            }
        }
    }

    fun getBooksByAuthor(authorName: String) {
        BookStoreStates.BookList.Loading
        val books = booksRepo.getBooksByAuthor(authorName)
        _booksState.update {
            if (books.isNotEmpty()) {
                BookStoreStates.BookList.Success(books)
            } else {
                BookStoreStates.BookList.Error
            }
        }
    }
}

