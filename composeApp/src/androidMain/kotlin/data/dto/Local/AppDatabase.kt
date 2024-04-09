package data.dto.Local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import data.dto.Local.Entities.OrderedProductsDbEntity
import data.dto.Local.Entities.OrdersDbEntity
import data.dto.Local.Entities.ProductsDbEntity
import data.dto.Local.Entities.ProductsInStockDbEntity
import data.dto.Local.Entities.SuppliersDbEntity
import data.dto.Local.TypeConverters.DateConverter
import data.dto.Local.TypeConverters.InnConverter
import data.dto.Local.TypeConverters.ProductEanConverter


@Database(
    version = 1,
    entities = [
        OrderedProductsDbEntity::class,
        OrdersDbEntity::class,
        ProductsDbEntity::class,
        ProductsInStockDbEntity::class,
        SuppliersDbEntity::class
    ]
)
@TypeConverters(InnConverter::class, ProductEanConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

}
