package me.miguins.core.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.core.model.Book
import me.miguins.core.ports.BookEntityServicePort
import me.miguins.database.entity.BookEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@MicronautTest
class BookServiceTest : AnnotationSpec() {

    private val service = mockk<BookEntityServicePort>()
    private val target = BookService(service)

    private lateinit var book: Book
    private lateinit var bookEntity: BookEntity

    private var id: UUID = UUID.randomUUID()

    @BeforeEach
    internal fun setUp() {
        book = Book("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
        bookEntity = BookEntity(id, LocalDateTime.now().toString(), "Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `send to entity service with success`()  {
        every { service.save(any()) } answers {}
        val result = target.create(book)
        result shouldBe Unit
    }

    @Test
    fun `find a valid book with success`() {
        every { service.findById(id) } answers { bookEntity }
        val result = target.findById(id)
        result shouldBe book
    }

    @Test
    fun `send updated book to entity service with success`() {
        every { service.update(id, any()) } answers {}
        val result = target.update(id, book)
        result shouldBe Unit
    }

    @Test
    fun `send id to be deleted to entity service with success`() {
        every { service.deleteById(id) } answers {}
        val result = target.delete(id)
        result shouldBe Unit
    }
}
