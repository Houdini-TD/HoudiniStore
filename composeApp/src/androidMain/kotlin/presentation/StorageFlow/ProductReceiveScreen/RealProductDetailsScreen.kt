package presentation.StorageFlow.ProductReceiveScreen

import com.arkivanov.decompose.ComponentContext
import core.network.ComponentState
import core.network.NetworkComponentContext
import data.dto.IProductRepository
import domain.product.ProductEAN
import domain.product.StoredProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import utils.componentCoroutineScope

class RealProductDetailsScreen(
    componentContext: ComponentContext,
    private val productRepository: IProductRepository,
    private val chosenEan: ProductEAN
) : NetworkComponentContext(componentContext), IProductDetailsScreen {

    override val productLoadState = MutableUIStateFlow()

    override val productList = MutableStateFlow(
        emptyList<StoredProduct>().toMutableList()
    )
    private val coroutineScope = componentCoroutineScope()

    init {
        loadProducts(chosenEan)
    }

    override fun onRefresh() {
        loadProducts(chosenEan)
    }

    override fun loadProducts(productEan: ProductEAN) {
        val productList = MutableStateFlow<List<StoredProduct>>(emptyList())
        coroutineScope.launch {
            productRepository.getProductsByEan(chosenEan).collectRequest(productLoadState, productList)
            if (productLoadState.value == ComponentState.Success)
                this@RealProductDetailsScreen.productList.value = productList.value.toMutableList()
        }
    }

    override fun saveProduct(id: Int, newAmount: Int) {
        val index = productList.value.indexOfFirst { it.id == id }
        if (index != -1){
            val oldProduct = productList.value[index]
            coroutineScope.launch {

            }
        }
    }
}