package data.dto.Local.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import data.dto.Local.TypeConverters.DateConverter
import domain.product.ProductEAN
import java.sql.Date

@Entity(
    tableName = "OrderedProducts",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = OrdersDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["order_id"]
        ),
        ForeignKey(
            entity = ProductsDbEntity::class,
            parentColumns = ["ean"],
            childColumns = ["product_ean"]
        )
    ]
)
data class OrderedProductsDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "order_id") val orderId: Int,
    @ColumnInfo(name = "product_ean") val productEan: ProductEAN,
    @ColumnInfo(name = "production_date") val productionDate: Date,
    val amount: Int
)
