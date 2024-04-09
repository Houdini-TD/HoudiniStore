package data.dto

import core.utils.Either
import core.utils.right
import domain.product.Inn
import domain.product.Order
import domain.product.OrderStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.sql.Date


class FakeOrdersRepositoryImpl : IOrdersRepository {

    companion object{
        val orders = listOf<Order>(
            Order(1, Inn("4324324324"), OrderStatus.EXECUTING, Date(2020, 9, 23)),
            Order(2, Inn("1234567890"), OrderStatus.EXECUTING, Date(2021, 5, 12)),
            Order(3, Inn("9876543210"), OrderStatus.EXECUTING, Date(2022, 3, 8)),
            Order(4, Inn("2468135790"), OrderStatus.DELIVERED, Date(2023, 7, 17)),
            Order(5, Inn("1357924680"), OrderStatus.DELIVERED, Date(2024, 1, 30)),
            Order(6, Inn("8642097513"), OrderStatus.EXECUTING, Date(2025, 11, 5)),
            Order(7, Inn("5793146820"), OrderStatus.DELIVERED, Date(2026, 6, 22))
        )
    }

    override suspend fun getOrders(): Flow<Either<String, List<Order>>> {
        return flowOf(orders.right())
    }
}