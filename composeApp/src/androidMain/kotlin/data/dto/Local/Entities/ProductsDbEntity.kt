package data.dto.Local.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import domain.product.ProductEAN

@Entity(
    tableName = "Products",
)
data class ProductsDbEntity(
    @PrimaryKey val ean: ProductEAN,
    val name: String,
    @ColumnInfo(name = "storage_time") val storageTime: Int
)
