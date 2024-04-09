
package presentation.SuppliersFlow.SupplierAddFlow.NewSupplierScreen

import kotlinx.coroutines.flow.StateFlow

interface INewSupplierScreen{
    val name: StateFlow<String>
    val inn: StateFlow<String>
    val address: StateFlow<String>
    val phone: StateFlow<String>

    fun onNameChanged(name: String)
    fun onInnChanged(inn: String)
    fun onAddressChanged(address: String)
    fun onPhoneChanged(phone: String)
    fun addSuppliedProducts()
    fun save()
}