package me.miguins.core.service

import me.miguins.core.mapper.BookConverter
import me.miguins.core.model.Book
import me.miguins.core.ports.BookEntityServicePort
import me.miguins.core.ports.BookServicePort
import java.util.*
import javax.inject.Singleton

@Singleton
class BookService(private val bookEntityServicePort: BookEntityServicePort) : BookServicePort {

    override fun create(book: Book) {
        bookEntityServicePort.save(BookConverter.bookToBookEntity(book))
    }

    override fun findById(id: UUID): Book? {
        return bookEntityServicePort.findById(id)?.let { BookConverter.bookEntityToBook(it) }
    }

    override fun update(id: UUID, book: Book) {
        bookEntityServicePort.update(id, BookConverter.bookToBookEntity(book))
    }

    override fun delete(id: UUID) {
        bookEntityServicePort.deleteById(id)
    }
}