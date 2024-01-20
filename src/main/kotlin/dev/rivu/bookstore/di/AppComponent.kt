package dev.rivu.bookstore.di

import dagger.Component
import dev.rivu.bookstore.BookStoreApp
import dev.rivu.bookstore.data.di.DataModule

@BooksAppScope
@Component(modules = [DataModule::class])
abstract class AppComponent() {
    abstract fun bookStoreApp(): BookStoreApp
}