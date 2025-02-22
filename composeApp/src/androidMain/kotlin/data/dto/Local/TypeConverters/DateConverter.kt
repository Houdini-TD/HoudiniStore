package data.dto.Local.TypeConverters

import androidx.room.TypeConverter
import java.sql.Date

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun toDate(timestamp: Long): Date{
        return Date(timestamp)
    }
}