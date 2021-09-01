package me.miguins.database.repository

import me.miguins.database.entity.BookEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookRepository {

    fun save(bookEntity: BookEntity)
    fun findById(id: UUID): BookEntity?
    fun update(id: UUID, bookEntity: BookEntity)
    fun deleteById(id: UUID)
}