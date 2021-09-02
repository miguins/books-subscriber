package me.miguins.infra.subscriber

import io.micronaut.http.annotation.Body
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject
import me.miguins.core.mapper.BookConverter
import me.miguins.core.ports.BookServicePort
import me.miguins.infra.model.BookSubscriber
import java.util.*


@NatsListener
class BookListener(private val bookServicePort: BookServicePort) {

    @Subject("books.create")
    fun receiveNewBook(@Body bookSubscriber: BookSubscriber) {
        bookServicePort.create(BookConverter.bookSubscriberToBook(bookSubscriber))
    }

    @Subject("books.update")
    fun receiveUpdateBook(@Body book: Pair<UUID, BookSubscriber>) {
        with(book.first) {
            bookServicePort.findById(this)?.also {
                bookServicePort.update(this, BookConverter.bookSubscriberToBook(book.second))
            }
        }
    }

    @Subject("books.delete")
    fun receiveDeleteBook(@Body id: UUID) {
        bookServicePort.delete(id)
    }
}