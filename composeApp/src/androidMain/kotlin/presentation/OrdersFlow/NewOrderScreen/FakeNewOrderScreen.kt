package presentation.OrdersFlow.NewOrderScreen

import data.dto.FakeProductRepositoryImpl
import data.dto.FakeSupplierRepositoryImpl
import domain.product.Inn
import domain.product.ProductEAN
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNewOrderScreen : INewOrderScreen {
    override val choosenSupplier = MutableStateFlow(FakeSupplierRepositoryImpl.suppliers[0])
    override val products = MutableStateFlow(FakeProductRepositoryImpl.products)
    override val productsToOrder = MutableStateFlow(mutableMapOf<ProductEAN, Int>())

    init {
        productsToOrder.value[ProductEAN("342421")] = 12
    }

    override fun loadSuppliers() {

    }

    override fun loadSuppliedProducts(inn: Inn) {

    }

    override fun onSupplierChanged(inn: Inn) {

    }

    override fun onProductAmountChanged(productEan: ProductEAN, newAmount: Int) {

    }

    override fun createOrder() {

    }

}