package me.miguins.database.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.database.entity.BookEntity
import me.miguins.database.repository.BookRepository
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@MicronautTest
class BookEntityServiceTest : AnnotationSpec() {

    private val repository = mockk<BookRepository>()
    private val target = BookEntityService(repository)

    private lateinit var bookEntity: BookEntity
    private var id: UUID = UUID.randomUUID()

    @BeforeEach
    internal fun setUp() {
        bookEntity = BookEntity(id, LocalDateTime.now().toString(), "Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `save book entity with success`() {
        every { repository.save(bookEntity) } answers {}
        val result = target.save(bookEntity)
        result shouldBe Unit
    }

    @Test
    fun `find book entity with success`() {
        every { repository.findById(id) } answers {bookEntity}
        val result = target.findById(id)
        result shouldBe bookEntity
    }

    @Test
    fun `update book entity with success`() {
        every { repository.update(id, any()) } answers { }
        val result = target.update(id, bookEntity)
        result shouldBe Unit
    }

    @Test
    fun `delete book entity with success`() {
        every { repository.deleteById(id) } answers {}
        val result = target.deleteById(id)
        result shouldBe Unit
    }
}
