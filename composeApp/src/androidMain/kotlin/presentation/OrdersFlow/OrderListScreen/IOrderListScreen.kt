
package presentation.StorageFlow.ProductDetailsScreen

import core.network.ComponentState
import domain.product.Inn
import domain.product.Order
import domain.product.Supplier
import kotlinx.coroutines.flow.StateFlow

interface IOrderListScreen{
    val orderList: StateFlow<List<Order>>
    val ordersLoadState: StateFlow<ComponentState>

    fun onOrderClick(id: Int)
    fun onNewOrderClick()
}