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

    override fun findById(id: UUID): Book? {
        val selectResult = cqlSession.execute(
            (SimpleStatement.newInstance("SELECT * FROM books.book WHERE id = ?", id))
        )

        return selectResult.map {
            Book(
                it.getUuid("id"),
                it.getString("createdAt"),
                it.getString("title"),
                it.getString("author"),
                it.getBigDecimal("price")
            )
        }.one()
    }

    override fun update(id: UUID, book: Book) {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "UPDATE books.book SET title = ?, author = ?, price = ? " +
                            "WHERE id = ?",
                    book.title,
                    book.author,
                    book.price,
                    id
                )
        )
    }

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }
}