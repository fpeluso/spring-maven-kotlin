package it.peluso.ecommerce.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import it.peluso.ecommerce.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import it.peluso.ecommerce.dto.ProductDTO
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.HttpStatus

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api/v1/products")
class ProductController {
    @Autowired
    var productService: ProductService? = null
    @GetMapping
    fun getAllProducts(@RequestParam(required = false) name: String?): ResponseEntity<List<ProductDTO?>?> {
        return productService!!.getAllProducts(name)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable("id") id: String): ResponseEntity<ProductDTO> {
        return productService!!.getProductById(id)
    }

    @PostMapping
    fun createProduct(@RequestBody product: ProductDTO): ResponseEntity<ProductDTO> {
        return productService!!.createProduct(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable("id") id: String, @RequestBody product: ProductDTO): ResponseEntity<ProductDTO> {
        return productService!!.updateProduct(id, product)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id: String): ResponseEntity<HttpStatus> {
        return productService!!.deleteProduct(id)
    }
}
