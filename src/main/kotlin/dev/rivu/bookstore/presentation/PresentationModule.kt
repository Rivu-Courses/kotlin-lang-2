package dev.rivu.bookstore.presentation

import dev.rivu.bookstore.data.BooksRepo
import org.koin.dsl.module

val PresentationModule = module {
    single {
        BooksViewModel(get<BooksRepo>())
    }
}