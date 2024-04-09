
package presentation.SuppliersFlow.SupplierAddFlow.SupplierListScreen

import core.network.ComponentState
import domain.product.Inn
import domain.product.Supplier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ISupplierListScreen{
    val supplierList: StateFlow<List<Supplier>>
    val supplersLoadState: StateFlow<ComponentState>

    fun onSupplierClick(inn: Inn)
    fun onNewSupplierClick()
}