package me.miguins.repository

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import me.miguins.model.Book
import java.util.*
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl(private val cqlSession: CqlSession) : BookRepository {

    override fun save(book: Book) {

        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO books.book (id, createdAt, title, author, price)" +
                            "VALUES (?,?,?,?,?)",
                    book.id,
                    book.createdAt,
                    book.title,
                    book.author,
                    book.price
                )
        )
    }

    override fun update(book: Book) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }
}