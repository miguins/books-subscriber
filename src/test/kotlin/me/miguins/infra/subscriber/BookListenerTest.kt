package me.miguins.infra.subscriber

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.core.model.Book
import me.miguins.core.ports.BookServicePort
import me.miguins.infra.model.BookSubscriber
import java.math.BigDecimal
import java.util.*

@MicronautTest
class BookListenerTest : AnnotationSpec() {

    val service = mockk<BookServicePort>()
    val target = BookListener(service)

    private lateinit var bookSubscriber: BookSubscriber
    private lateinit var book: Book
    private var id: UUID = UUID.randomUUID()

    @BeforeEach
    internal fun setUp() {
        bookSubscriber = BookSubscriber("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
        book = Book("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `send new book to service with success`() {
        every { service.create(book) } answers {}
        val result = target.receiveNewBook(bookSubscriber)
        result shouldBe Unit
    }
    @Test
    fun `send updated book to service with success`() {
        every { service.update(id, any()) } answers {}
        every { service.findById(id) } answers { book }
        val result = target.receiveUpdateBook(Pair(id, bookSubscriber))
        result shouldBe Unit
    }

    @Test
    fun `send delete book to service with success`() {
        every { service.delete(id) } answers {}
        val result = target.receiveDeleteBook(id)
        result shouldBe Unit
    }

}