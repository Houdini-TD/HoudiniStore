package data.dto.Local.TypeConverters

import androidx.room.TypeConverter
import domain.product.ProductEAN

class ProductEanConverter {
    @TypeConverter
    fun fromProductEan(ean: ProductEAN): String{
        return ean.value
    }

    @TypeConverter
    fun toProductEan(value: String): ProductEAN{
        return ProductEAN(value)
    }
}