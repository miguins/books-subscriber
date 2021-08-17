package me.miguins.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Book(
    val id: UUID? = UUID.randomUUID(),
    val createdAt: String? = LocalDateTime.now().toString(),
    val title: String? = null,
    val author: String? = null,
    val price: BigDecimal? = null
)