
package presentation.OrdersFlow.OrderDetailsScreen

import core.network.ComponentState
import domain.product.Order
import domain.product.ProductSpecs
import kotlinx.coroutines.flow.StateFlow

interface IOrderDetailsScreen{
    val order: StateFlow<Order>
    val orderedProducts: StateFlow<List<ProductSpecs>>
    val ordersLoadState: StateFlow<ComponentState>

    fun onRefresh()

    fun loadOrderedProducts(orderId: Int)

    fun loadOrderDetails(orderId: Int)
}