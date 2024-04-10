
package presentation.StorageFlow.ProductDetailsScreen

import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.StoredProduct
import kotlinx.coroutines.flow.StateFlow

interface IProductDetailsScreen{
    val productList: StateFlow<MutableList<StoredProduct>>

    val productLoadState: StateFlow<ComponentState>

    fun onRefresh()

    fun loadProducts(productEan: ProductEAN)

    fun updateProduct(newProduct: StoredProduct)

    fun onProductDelete(id: Int)
}