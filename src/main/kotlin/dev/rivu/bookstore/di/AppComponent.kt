package dev.rivu.bookstore.di

import dev.rivu.bookstore.BookStoreApp
import dev.rivu.bookstore.data.di.DataComponent
import dev.rivu.bookstore.presentation.BooksViewModel
import me.tatarka.inject.annotations.Component

@BooksAppScope
@Component
abstract class AppComponent(@Component val dataComponent: DataComponent) {
    abstract val app: BookStoreApp
}