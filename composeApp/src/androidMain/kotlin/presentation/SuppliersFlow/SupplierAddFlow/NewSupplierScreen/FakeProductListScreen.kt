package presentation.SuppliersFlow.SupplierAddFlow.NewSupplierScreen

import kotlinx.coroutines.flow.MutableStateFlow

class FakeProductListScreen : INewSupplierScreen {
    override val name = MutableStateFlow("")

    override val inn = MutableStateFlow("")

    override val address = MutableStateFlow("")

    override val phone = MutableStateFlow("")

    override fun onNameChanged(name: String) {

    }

    override fun onInnChanged(inn: String) {

    }

    override fun onAddressChanged(address: String) {

    }

    override fun onPhoneChanged(phone: String) {

    }

    override fun addSuppliedProducts() {

    }

    override fun save() {

    }

}