package me.miguins.service

import me.miguins.model.Book
import java.util.*
import javax.inject.Singleton

@Singleton
interface BookService {

    fun create(book: Book)
    fun update(id: String, book: Book)
    fun delete(id: UUID)
}