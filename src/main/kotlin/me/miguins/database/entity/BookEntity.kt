package me.miguins.database.entity

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class BookEntity(
    val id: UUID? = UUID.randomUUID(),
    val createdAt: String? = LocalDateTime.now().toString(),
    val title: String? = null,
    val author: String? = null,
    val price: BigDecimal? = null
)