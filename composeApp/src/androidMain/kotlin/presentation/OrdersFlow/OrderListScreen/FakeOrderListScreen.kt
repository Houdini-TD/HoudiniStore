package presentation.StorageFlow.ProductDetailsScreen

import core.network.ComponentState
import data.dto.FakeOrdersRepositoryImpl
import data.dto.FakeSupplierRepositoryImpl
import domain.product.Inn
import kotlinx.coroutines.flow.MutableStateFlow

class FakeOrderListScreen : IOrderListScreen {
    override val orderList = MutableStateFlow(FakeOrdersRepositoryImpl.orders)
    override val ordersLoadState: MutableStateFlow<ComponentState> = MutableStateFlow(ComponentState.Idle)

    override fun onOrderClick(id: Int) {

    }

    override fun onNewOrderClick() {

    }
}