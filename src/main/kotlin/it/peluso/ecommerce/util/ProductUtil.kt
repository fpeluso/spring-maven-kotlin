package it.peluso.ecommerce.util

import it.peluso.ecommerce.dto.ProductDTO
import org.springframework.stereotype.Component

@Component
object ProductUtil {
    fun isProductDataValid(product: ProductDTO): Boolean {
        return product.name != null && product.description != null && product.price != null
    }

    fun productHasQuantity(product: ProductDTO): Boolean {
        return product.quantity != null && product.quantity > 0
    }
}
