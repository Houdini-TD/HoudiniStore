package core

import android.content.Context
import androidx.room.Room.databaseBuilder
import data.dto.IProductRepository
import data.dto.Local.AppDatabase
import data.dto.Local.Entities.ProductsDbEntity
import data.dto.Local.LocalProductRepository
import domain.product.ProductEAN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File


object Dependencies {
    private lateinit var applicationContext: Context
    private lateinit var appDatabase: AppDatabase

    fun init(context: Context){
        applicationContext = context
    }

    fun initDatabase() {
        appDatabase = databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database"
        ).createFromAsset("products.db")
            .build()
    }

    val productRepository: IProductRepository by lazy { LocalProductRepository(appDatabase.getProductDao()) }
}
