package data.dto

import core.utils.Either
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.StoredProduct
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProductsInStock(): Flow<Either<String, List<ProductSpecs>>>
    suspend fun getProducts(): Flow<Either<String, List<ProductSpecs>>>
    suspend fun getProductsByEan(ean: ProductEAN): Flow<Either<String, List<StoredProduct>>>
    suspend fun changeProductById(id: Int, newProduct: StoredProduct)
    suspend fun deleteProductById(id: Int)
    suspend fun getPendingProductsAmount() : Flow<Either<String, Int>>
}


