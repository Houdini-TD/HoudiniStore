package domain.product

import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
@JvmInline
value class ProductEAN(val value: String)

data class ProductSpecs(
    val ean: ProductEAN,
    val name: String,
    val storageTime: Int
)

data class StoredProduct(
    val id: Int,
    val specs: ProductSpecs,
    val amount: Int,
    val shelf: Int,
    val productionDate: Date
){
    fun copyWith(
        id: Int = this.id,
        specs: ProductSpecs = this.specs,
        amount: Int = this.amount,
        shelf: Int = this.shelf,
        productionDate: Date = this.productionDate
    ): StoredProduct {
        return StoredProduct(id, specs, amount, shelf, productionDate)
    }
}