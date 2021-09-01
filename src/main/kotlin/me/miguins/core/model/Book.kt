package me.miguins.core.model

import java.math.BigDecimal

data class Book(
    val title: String? = null,
    val author: String? = null,
    val price: BigDecimal? = null
)