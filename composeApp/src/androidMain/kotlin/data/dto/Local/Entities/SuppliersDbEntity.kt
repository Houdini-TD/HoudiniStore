package data.dto.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import domain.product.Inn

@Entity(
    tableName = "Suppliers"
)
data class SuppliersDbEntity(
    @PrimaryKey val inn: Inn,
    val name: String,
    val address: String,
    val phone: String
)