package dev.rivu.bookstore.data.di

import dagger.Module
import dagger.Provides
import dev.rivu.bookstore.data.BooksDS
import dev.rivu.bookstore.data.BooksLocalDS
import dev.rivu.bookstore.data.BooksRemoteDS
import javax.inject.Named

@Module
class DataModule {
    @Provides
    @Named("localDS")
    fun getLocalDS(): BooksDS = BooksLocalDS()

    @Provides
    @Named("remoteDS")
    fun getRemoteDS(): BooksDS = BooksRemoteDS.getInstance()
}