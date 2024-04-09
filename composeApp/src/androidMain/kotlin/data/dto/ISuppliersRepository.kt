package data.dto

import core.utils.Either
import domain.product.ProductEAN
import domain.product.Supplier
import kotlinx.coroutines.flow.Flow

interface ISuppliersRepository {
    suspend fun addSuppliedProducts(products: List<ProductEAN>)
    suspend fun getSuppliers(newSupplier: Supplier) : Flow<Either<String, List<Supplier>>>
}