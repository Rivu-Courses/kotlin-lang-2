package dev.rivu.bookstore.presentation

import org.koin.dsl.module

val PresentationModule = module {
    single {
        BooksViewModel(get())
    }
}