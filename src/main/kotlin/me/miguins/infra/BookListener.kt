package me.miguins.infra

import io.micronaut.http.annotation.Body
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject
import me.miguins.model.Book
import me.miguins.service.BookService
import java.util.UUID


@NatsListener
class BookListener(private val bookService: BookService) {

    @Subject("books.create")
    fun receiveNewBook(@Body book: Book) {
        bookService.create(book)
    }

    @Subject("books.update")
    fun receiveUpdateBook(@Body book: Pair<String, Book>) {
        with(UUID.fromString(book.first)) {
            bookService.findById(this)?.also {
                bookService.update(this, book.second)
            }
        }
    }

    @Subject("books.delete")
    fun receiveDeleteBook(@Body id: String) {
        println("delete $id")
    }
}