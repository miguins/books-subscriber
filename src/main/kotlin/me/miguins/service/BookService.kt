package me.miguins.service

import me.miguins.model.Book
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookService {

    fun create(book: Book)
    fun findById(id: UUID): Book?
    fun update(id: UUID, book: Book)
    fun delete(id: UUID)
}