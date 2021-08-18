package me.miguins.repository

import me.miguins.model.Book
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookRepository {

    fun save(book: Book)
    fun findById(id: UUID): Book?
    fun update(id: UUID, book: Book)
    fun deleteById(id: UUID)
}