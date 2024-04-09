package data.dto

import core.utils.Either
import domain.product.Order
import domain.product.ProductEAN
import domain.product.Supplier
import kotlinx.coroutines.flow.Flow

interface IOrdersRepository {
    suspend fun getOrders() : Flow<Either<String, List<Order>>>
}