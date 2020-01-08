package com.zlrx.example.springcoroutine.domain

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: Long)