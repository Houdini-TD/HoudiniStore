
package presentation.OrdersFlow.NewOrderScreen

import domain.product.Inn
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.Supplier
import kotlinx.coroutines.flow.StateFlow

interface INewOrderScreen{
    val choosenSupplier: StateFlow<Supplier>
    val products: StateFlow<List<ProductSpecs>>
    val productsToOrder: StateFlow<MutableMap<ProductEAN, Int>>


    fun loadSuppliers()
    fun loadSuppliedProducts(inn: Inn)
    fun onSupplierChanged(inn: Inn)
    fun onProductAmountChanged(productEan: ProductEAN, newAmount: Int)
    fun createOrder()
}