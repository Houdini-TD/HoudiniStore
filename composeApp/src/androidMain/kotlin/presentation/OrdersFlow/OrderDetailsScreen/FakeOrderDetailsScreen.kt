package presentation.OrdersFlow.OrderDetailsScreen

import core.network.ComponentState
import data.dto.FakeOrdersRepositoryImpl
import data.dto.FakeProductRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow

class FakeOrderDetailsScreen : IOrderDetailsScreen  {
    override val order = MutableStateFlow(FakeOrdersRepositoryImpl.orders[0])

    override val orderedProducts = MutableStateFlow(FakeProductRepositoryImpl.products)

    override val ordersLoadState = MutableStateFlow(ComponentState.Idle)

    override fun onRefresh() = Unit
    override fun loadOrderedProducts(orderId: Int) {
    }

    override fun loadOrderDetails(orderId: Int) {
    }

}