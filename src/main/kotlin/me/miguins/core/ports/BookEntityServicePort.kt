package me.miguins.core.ports

import me.miguins.database.entity.BookEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookEntityServicePort {

    fun save(bookEntity: BookEntity)
    fun findById(id: UUID): BookEntity?
    fun update(id: UUID, bookEntity: BookEntity)
    fun deleteById(id: UUID)
}