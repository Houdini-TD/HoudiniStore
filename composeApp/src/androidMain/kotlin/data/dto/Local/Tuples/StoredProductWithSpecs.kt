package data.dto.Local.Tuples

import androidx.room.ColumnInfo
import androidx.room.Embedded
import data.dto.Local.Entities.ProductsDbEntity
import domain.product.ProductSpecs
import java.sql.Date

data class StoredProductWithSpecs(
    val id: Int,
    @Embedded
    val specs: ProductsDbEntity,
    val amount: Int,
    @ColumnInfo(name = "shelf_id") val shelfId: Int,
    @ColumnInfo(name = "production_date") val productionDate: Date
)