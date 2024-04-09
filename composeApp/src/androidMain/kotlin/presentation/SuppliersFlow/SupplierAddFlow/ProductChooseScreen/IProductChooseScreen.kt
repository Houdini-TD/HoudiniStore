package presentation.SuppliersFlow.SupplierAddFlow.ProductChooseScreen

import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.ProductSpecs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IProductChooseScreen {
    val productList: StateFlow<List<ProductSpecs>>

    val productLoadState: StateFlow<ComponentState>

    val chosenProductEans: HashSet<ProductEAN>

    fun onProductChosen(productEan: ProductEAN)

    fun onAddingFinished()

    fun onProductUnChosen(productEan: ProductEAN)

    fun loadProducts()
}