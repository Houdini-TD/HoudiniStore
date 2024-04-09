package data.dto.Local

import androidx.compose.runtime.collectAsState
import core.utils.Either
import data.dto.IProductRepository
import data.dto.Local.Entities.ProductsDbEntity
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.StoredProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class LocalProductRepository(private val productDao: ProductDao) : IProductRepository{

    override suspend fun getProductsInStock(): Flow<Either<String, List<ProductSpecs>>> {
        return withContext(Dispatchers.IO){
            return@withContext flow {
                emit(Either.Left("Waiting"))
                try {
                   emit(Either.Right(productDao.getProductsInStock().toProductSpecs()))
                } catch (e: Exception){
                    emit(Either.Left(e.toString()))
                }
            }
        }
    }

    override suspend fun getProducts(): Flow<Either<String, List<ProductSpecs>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByEan(ean: ProductEAN): Flow<Either<String, List<StoredProduct>>> {
        TODO("Not yet implemented")
    }

    override suspend fun changeProductById(id: Int, newProduct: StoredProduct) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getPendingProductsAmount(): Flow<Either<String, Int>> {
        TODO("Not yet implemented")
    }

    fun ProductsDbEntity.toProductSpecs(){

    }
}