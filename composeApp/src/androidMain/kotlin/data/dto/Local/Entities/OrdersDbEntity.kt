package data.dto.Local.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import domain.product.Inn
import domain.product.OrderStatus
import domain.product.ProductEAN

@Entity(
    tableName = "Orders",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = ProductsDbEntity::class,
            parentColumns = ["ean"],
            childColumns = ["product_ean"]
        ),
        ForeignKey(
            entity = SuppliersDbEntity::class,
            parentColumns = ["inn"],
            childColumns = ["supplier_inn"]
        )
    ]
)
data class OrdersDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "supplier_inn") val supplierInn: Inn,
    @ColumnInfo(name = "product_ean") val productEan: ProductEAN,
    @ColumnInfo(name = "order_status") val orderStatus: OrderStatus
)