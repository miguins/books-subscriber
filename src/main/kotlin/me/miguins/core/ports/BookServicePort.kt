package me.miguins.core.ports

import me.miguins.core.model.Book
import me.miguins.database.entity.BookEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookServicePort {

    fun create(book: Book)
    fun findById(id: UUID): BookEntity?
    fun update(id: UUID, book: Book)
    fun delete(id: UUID)
}