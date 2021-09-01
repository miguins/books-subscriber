package me.miguins.database.service

import me.miguins.core.ports.BookEntityServicePort
import me.miguins.database.entity.BookEntity
import me.miguins.database.repository.BookRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class BookEntityService(private val bookRepository: BookRepository): BookEntityServicePort {

    override fun save(bookEntity: BookEntity) {
        bookRepository.save(bookEntity)
    }

    override fun findById(id: UUID): BookEntity? {
        return bookRepository.findById(id)
    }

    override fun update(id: UUID, bookEntity: BookEntity) {
        bookRepository.update(id, bookEntity)
    }

    override fun deleteById(id: UUID) {
        bookRepository.deleteById(id)
    }
}