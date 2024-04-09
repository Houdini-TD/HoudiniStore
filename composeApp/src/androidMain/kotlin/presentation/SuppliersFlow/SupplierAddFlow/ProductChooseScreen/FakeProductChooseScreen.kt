package presentation.SuppliersFlow.SupplierAddFlow.ProductChooseScreen

import core.network.ComponentState
import data.dto.FakeProductRepositoryImpl
import domain.product.ProductEAN
import kotlinx.coroutines.flow.MutableStateFlow

class FakeProductChooseScreen: IProductChooseScreen {
    override val productList = MutableStateFlow(
        FakeProductRepositoryImpl.products
    )
    override val productLoadState: MutableStateFlow<ComponentState> = MutableStateFlow(ComponentState.Idle)
    override val chosenProductEans: HashSet<ProductEAN> = emptySet<ProductEAN>().toHashSet()

    override fun onProductChosen(productEan: ProductEAN) {
        TODO("Not yet implemented")
    }

    override fun onAddingFinished() {
        TODO("Not yet implemented")
    }

    override fun onProductUnChosen(productEan: ProductEAN) {
        TODO("Not yet implemented")
    }

    override fun loadProducts() {
        TODO("Not yet implemented")
    }
}