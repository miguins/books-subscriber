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

    override fun update(id: String, book: Book) {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}