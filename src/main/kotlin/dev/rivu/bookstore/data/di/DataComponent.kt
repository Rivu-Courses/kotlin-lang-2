package dev.rivu.bookstore.data.di

import dev.rivu.bookstore.data.BooksDS
import dev.rivu.bookstore.data.BooksLocalDS
import dev.rivu.bookstore.data.BooksRemoteDS
import dev.rivu.bookstore.di.BooksAppScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides

@Component
abstract class DataComponent {
    abstract val dsMap: Map<String, BooksDS>

    @IntoMap
    @Provides
    protected fun provideLocalDS(): Pair<String, BooksDS> = "local" to BooksLocalDS()

    @IntoMap
    @Provides
    protected fun provideRemoteDS(): Pair<String, BooksDS> = "remote" to BooksRemoteDS.getInstance()
}