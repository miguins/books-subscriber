package me.miguins.infra.model

import java.math.BigDecimal

data class BookSubscriber(
    val title: String? = null,
    val author: String? = null,
    val price: BigDecimal? = null
)