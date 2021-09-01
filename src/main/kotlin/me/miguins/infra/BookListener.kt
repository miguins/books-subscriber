package me.miguins.infra

import io.micronaut.http.annotation.Body
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject
import me.miguins.core.model.Book
import me.miguins.core.ports.BookServicePort
import java.util.*


@NatsListener
class BookListener(private val bookServicePort: BookServicePort) {

    @Subject("books.create")
    fun receiveNewBook(@Body book: Book) {
        bookServicePort.create(book)
    }

    @Subject("books.update")
    fun receiveUpdateBook(@Body book: Pair<UUID, Book>) {
        with(book.first) {
            bookServicePort.findById(this)?.also {
                bookServicePort.update(this, book.second)
            }
        }
    }

    @Subject("books.delete")
    fun receiveDeleteBook(@Body id: UUID) {
        bookServicePort.delete(id)
    }
}