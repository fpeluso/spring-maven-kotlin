package it.peluso.ecommerce.repository

import it.peluso.ecommerce.dto.ProductDTO
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<ProductDTO?, String?> {
    fun findByName(name: String?): List<ProductDTO?>?
    fun findByDescription(description: String?): List<ProductDTO?>?
}
