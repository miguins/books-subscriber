package me.miguins.repository

import me.miguins.model.Book
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookRepository {

    fun save(book: Book)
    fun update(book: Book)
    fun deleteById(id: UUID)
}