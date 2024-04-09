package presentation.StorageFlow.ProductListScreen

import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.ProductSpecs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProductListScreen : IProductListScreen  {
    override val products = MutableStateFlow(
        listOf(
            ProductSpecs(ProductEAN("34242"), "Картошка", 5),
            ProductSpecs(ProductEAN("11111"), "Яблоки", 7),
            ProductSpecs(ProductEAN("34244"), "Бананы аргентинские", 3),
            ProductSpecs(ProductEAN("321312"), "Свекла", 2),
            ProductSpecs(ProductEAN("123123123"), "Орехи", 10),
        )
    )

    override val productLoadState: MutableStateFlow<ComponentState> = MutableStateFlow(ComponentState.Success)

    override val pendingProductsAmount = MutableStateFlow(0)

    override fun onProductClick(choosenEAN: ProductEAN) {
        TODO("Not yet implemented")
    }

    override fun recieveProducts() {
        TODO("Not yet implemented")
    }

}