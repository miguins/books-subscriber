package me.miguins.service

import me.miguins.model.Book
import me.miguins.repository.BookRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class BookServiceImpl(private val bookRepository: BookRepository) : BookService {

    override fun create(book: Book) {
        bookRepository.save(book)
    }

    override fun findById(id: UUID): Book? {
        return bookRepository.findById(id)
    }

    override fun update(id: UUID,book: Book) {
        bookRepository.update(id, book)
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}