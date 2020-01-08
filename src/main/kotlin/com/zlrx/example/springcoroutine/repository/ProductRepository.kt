package com.zlrx.example.springcoroutine.repository

import com.zlrx.example.springcoroutine.domain.Product
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(private val client: DatabaseClient) {

    suspend fun getProductById(id: String): Product = client
        .execute("select * from product where id= $1")
        .bind(0, id)
        .`as`(Product::class.java)
        .fetch()
        .one()
        .awaitFirst()

    suspend fun addNewProduct(product: Product): Void? {
        val (id, name, price) = product
        return client
            .execute("Insert into product (id,name,price) values($1,$2,$3)")
            .bind(0, id)
            .bind(1, name)
            .bind(2, price)
            .then()
            .awaitFirstOrNull()
    }

}

