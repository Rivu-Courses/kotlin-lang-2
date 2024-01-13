package dev.rivu.bookstore.data

import org.koin.dsl.module

val DataModule = module {
    single {
        BooksRepo(get())
    }
    single {
        BooksLocalDS()
    }
}