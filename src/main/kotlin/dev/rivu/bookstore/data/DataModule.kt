package dev.rivu.bookstore.data

import org.koin.core.qualifier.named
import org.koin.dsl.module

val DataModule = module {
    single {
        BooksRepo(get(named("localDS")), get(named("remoteDS")))
    }
    single<BooksDS>(named("localDS")) {
        BooksLocalDS()
    }
    single<BooksDS>(named("remoteDS")) {
        BooksLocalDS()
    }
}