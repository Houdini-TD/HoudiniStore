package data.dto.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import core.utils.Either
import data.dto.Local.Entities.ProductsDbEntity
import data.dto.Local.Entities.ProductsInStockDbEntity
import data.dto.Local.Tuples.StoredProductWithSpecs
import domain.product.ProductEAN
import domain.product.StoredProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT Products.* " +
            "FROM Products " +
            "INNER JOIN OrderedProducts ON Products.ean = OrderedProducts.product_ean " +
            "INNER JOIN ProductsInStock ON ProductsInStock.ordered_product_id = OrderedProducts.id")
    fun getProductsInStock(): List<ProductsDbEntity>


    @Query("SELECT Products.*," +
            " OrderedProducts.id as id," +
            " ProductsInStock.amount as amount," +
            " ProductsInStock.shelf_id as shelf_id," +
            " OrderedProducts.production_date as production_date" +
            " FROM ProductsInStock " +
            "INNER JOIN OrderedProducts ON ProductsInStock.ordered_product_id = OrderedProducts.id " +
            "INNER JOIN Products ON OrderedProducts.product_ean = Products.ean" +
            " WHERE Products.ean = :ean")
    fun getDetailedProductsByEan(ean: ProductEAN): List<StoredProductWithSpecs>

    @Update
    fun changeProductStorage(newProduct: ProductsInStockDbEntity)

//    @Query("DELETE FROM ")
//    fun deleteProductFromStockById(id: Int)

//    fun getPendingProductsAmount() : Flow<Either<String, Int>>


    @Query("SELECT COUNT(*) FROM Products")
    fun getProductsCount(): Int

    @Insert
    fun insert(product: ProductsDbEntity)
}