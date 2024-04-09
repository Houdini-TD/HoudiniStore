package data.dto.Local.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import domain.product.ProductEAN

@Entity(
    tableName = "ProductsInStock",
    foreignKeys = [
        ForeignKey(
            entity = OrderedProductsDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["ordered_product_id"]
        )
    ]
)
data class ProductsInStockDbEntity(
    @PrimaryKey @ColumnInfo(name = "ordered_product_id") val orderedProductId: Int,
    @ColumnInfo(name = "shelf_id") val shelfId: Int,
    val amount: Int
)