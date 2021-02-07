package it.peluso.ecommerce.service

import org.springframework.beans.factory.annotation.Autowired
import it.peluso.ecommerce.repository.ProductRepository
import org.springframework.http.ResponseEntity
import it.peluso.ecommerce.dto.ProductDTO
import it.peluso.ecommerce.exception.DataValidationException
import org.springframework.http.HttpStatus
import it.peluso.ecommerce.util.ProductUtil
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.ArrayList

@Service
class ProductService {
    @Autowired
    var productRepository: ProductRepository? = null

    fun getAllProducts(name: String?): ResponseEntity<List<ProductDTO?>?> {
        return try {
            val products: MutableList<ProductDTO?> = ArrayList()
            if (name == null) products.addAll(productRepository!!.findAll()) else products.addAll(productRepository!!.findByName(name)!!)
            if (products.isEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else ResponseEntity(products, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun getProductById(id: String): ResponseEntity<ProductDTO> {
        val productData = productRepository!!.findById(id)
        return productData.map { productDTO: ProductDTO? -> ResponseEntity(productDTO, HttpStatus.OK) }.orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun createProduct(product: ProductDTO): ResponseEntity<ProductDTO> {
        return try {
            val newProduct: ProductDTO
            if (ProductUtil.isProductDataValid(product)) {
                newProduct = if (ProductUtil.productHasQuantity(product)) {
                    ProductDTO(product.name, product.description, product.price, product.quantity)
                } else {
                    ProductDTO(product.name, product.description, product.price)
                }
                val _product = productRepository!!.save(newProduct)
                ResponseEntity(_product, HttpStatus.CREATED)
            } else {
                throw DataValidationException("Invalid data")
            }
        } catch (dve: DataValidationException) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun updateProduct(id: String, product: ProductDTO): ResponseEntity<ProductDTO> {
        val productData = productRepository!!.findById(id)
        //TODO: validate data
        return if (productData.isPresent) {
            val _product = productData.get()
            _product.name = product.name
            _product.description = product.description
            _product.price = product.price
            if(_product.quantity != null) _product.quantity = product.quantity
            ResponseEntity(productRepository!!.save(_product), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    fun deleteProduct(id: String): ResponseEntity<HttpStatus> {
        return try {
            productRepository!!.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
