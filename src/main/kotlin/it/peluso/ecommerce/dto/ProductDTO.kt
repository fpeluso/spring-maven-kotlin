package it.peluso.ecommerce.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "catalogue")
class ProductDTO() {
    @Id
    var id: String? = null
    var name = ""
    var description = ""
    var price = 0.0
    var quantity = 0

    constructor(name: String, description: String, price: Double) : this() {
        this.name = name
        this.description = description
        this.price = price
    }

    constructor(name: String, description: String, price: Double, quantity: Int) : this(name, description, price) {
        this.quantity = quantity
    }

    override fun toString(): String {
        return "$name: $description ($quantity, €$price cad.)"
    }
}
