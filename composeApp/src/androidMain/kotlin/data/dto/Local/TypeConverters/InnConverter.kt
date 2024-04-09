package data.dto.Local.TypeConverters

import androidx.room.TypeConverter
import domain.product.Inn

class InnConverter {
    @TypeConverter
    fun fromInn(inn: Inn): String{
        return inn.value
    }

    @TypeConverter
    fun toInn(value: String): Inn{
        return Inn(value)
    }
}