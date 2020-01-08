package com.zlrx.example.springcoroutine.controller

import com.zlrx.example.springcoroutine.domain.Product
import com.zlrx.example.springcoroutine.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController @Autowired constructor(val productRepository: ProductRepository) {

    @GetMapping(path = ["/product/{id}"])
    suspend fun findOne(@PathVariable id: String): Product = productRepository.getProductById(id)


    @PostMapping(path = ["/product"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun insert(@RequestBody product: Product) {
        productRepository.addNewProduct(product)
    }

}