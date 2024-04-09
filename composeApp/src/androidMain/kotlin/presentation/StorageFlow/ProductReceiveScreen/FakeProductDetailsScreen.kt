package presentation.StorageFlow.ProductReceiveScreen

import core.network.ComponentState
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.StoredProduct
import kotlinx.coroutines.flow.MutableStateFlow
import java.sql.Date

class FakeProductDetailsScreen : IProductDetailsScreen  {
    override val productList: MutableStateFlow<MutableList<StoredProduct>> =
        MutableStateFlow(
            mutableListOf<StoredProduct>().apply {
                val specs = ProductSpecs(ProductEAN("12345"), "Картошка", 5)
                add(StoredProduct(1, specs, 20, 12, Date(2024, 9, 23)))
                add(StoredProduct(2, specs, 9, 12, Date(2020, 9, 23)))
                add(StoredProduct(232, specs, 202, 23, Date(2024, 2, 23)))
            }
        )


    override val productLoadState: MutableStateFlow<ComponentState> = MutableStateFlow(ComponentState.Success)

    override fun onRefresh() = Unit

    override fun loadProducts(productEan: ProductEAN) = Unit

    override fun saveProduct(id: Int, newShelf: Int) = Unit
}