package dev.rivu.bookstore.data

data class Book(
    val id: String,
    val publisher: String,
    val title: String,
    val authors: List<Author>
)

data class Author(
    val name: String,
    val bio: String = "$name Author Bio goes here",
)
