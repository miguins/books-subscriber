package me.miguins.database.repository

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import me.miguins.database.entity.BookEntity
import java.util.*
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl(private val cqlSession: CqlSession) : BookRepository {

    override fun save(bookEntity: BookEntity) {

        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO books.book (id, createdAt, title, author, price)" +
                            "VALUES (?,?,?,?,?)",
                    bookEntity.id,
                    bookEntity.createdAt,
                    bookEntity.title,
                    bookEntity.author,
                    bookEntity.price
                )
        )
    }

    override fun findById(id: UUID): BookEntity? {
        val selectResult = cqlSession.execute(
            (SimpleStatement.newInstance("SELECT * FROM books.book WHERE id = ?", id))
        )

        return selectResult.map {
            BookEntity(
                it.getUuid("id"),
                it.getString("createdAt"),
                it.getString("title"),
                it.getString("author"),
                it.getBigDecimal("price")
            )
        }.one()
    }

    override fun update(id: UUID, bookEntity: BookEntity) {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "UPDATE books.book SET title = ?, author = ?, price = ? " +
                            "WHERE id = ?",
                    bookEntity.title,
                    bookEntity.author,
                    bookEntity.price,
                    id
                )
        )
    }

    override fun deleteById(id: UUID) {
        cqlSession.execute(SimpleStatement.newInstance("DELETE FROM books.book WHERE id = ?", id))
    }
}