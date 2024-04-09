package presentation.SuppliersFlow.SupplierAddFlow.SupplierListScreen

import core.network.ComponentState
import data.dto.FakeSupplierRepositoryImpl
import domain.product.Inn
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSupplierListScreen : ISupplierListScreen {
    override val supplierList = MutableStateFlow(FakeSupplierRepositoryImpl.suppliers)
    override val supplersLoadState: MutableStateFlow<ComponentState> = MutableStateFlow(ComponentState.Idle)


    override fun onSupplierClick(inn: Inn) {

    }

    override fun onNewSupplierClick() {

    }
}