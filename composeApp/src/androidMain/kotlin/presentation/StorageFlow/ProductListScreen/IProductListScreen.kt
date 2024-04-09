
package presentation.StorageFlow.ProductListScreen

import androidx.compose.runtime.MutableState
import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.ProductSpecs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IProductListScreen{
    val products: StateFlow<List<ProductSpecs>>

    val productLoadState: StateFlow<ComponentState>

    val pendingProductsAmount: StateFlow<Int>

    fun onProductClick(choosenEAN: ProductEAN)

    fun recieveProducts()
}