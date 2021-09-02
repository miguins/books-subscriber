package me.miguins.core.mapper

import me.miguins.core.model.Book
import me.miguins.database.entity.BookEntity
import me.miguins.infra.model.BookSubscriber
import javax.inject.Singleton

@Singleton
class BookConverter {

    companion object {
        fun bookToBookEntity(book: Book) =
            BookEntity(title = book.title, author = book.author, price = book.price)

        fun bookEntityToBook(bookEntity: BookEntity) =
            Book(bookEntity.title, bookEntity.author, bookEntity.price)

        fun bookSubscriberToBook(bookSubscriber: BookSubscriber) =
            Book(bookSubscriber.title, bookSubscriber.author, bookSubscriber.price)
    }

}