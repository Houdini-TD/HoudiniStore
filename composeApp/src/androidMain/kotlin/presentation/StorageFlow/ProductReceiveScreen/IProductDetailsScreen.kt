
package presentation.StorageFlow.ProductReceiveScreen

import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.StoredProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IProductDetailsScreen{
    val productList: StateFlow<MutableList<StoredProduct>>

    val productLoadState: StateFlow<ComponentState>

    fun onRefresh()

    fun loadProducts(productEan: ProductEAN)

    fun saveProduct(id: Int, newShelf: Int)
}