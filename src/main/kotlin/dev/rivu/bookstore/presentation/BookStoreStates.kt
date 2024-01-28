package dev.rivu.bookstore.presentation

import dev.rivu.bookstore.data.Book


sealed class BookStoreStates {
    object Options : BookStoreStates() {
        val options: Set<String> = setOf(
            "Add Book", // action 0
            "Get All Books",
            "Get Books By Author"
        )
    }

    sealed class AddBook : BookStoreStates() {
        object Adding : AddBook()

        object Loading : AddBook()

        object AddSuccess : AddBook()

        object AddError : AddBook()
    }

    object AuthorInputScreen : BookStoreStates()

    sealed class BookList : BookStoreStates() {
        object Loading : BookList()
        data class Success(
            val books: List<Book>
        ) : BookList()

        data class Error(val errorDetails: String) : BookList()
    }
}