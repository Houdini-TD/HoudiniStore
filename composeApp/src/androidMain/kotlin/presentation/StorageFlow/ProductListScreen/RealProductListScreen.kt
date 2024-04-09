package presentation.StorageFlow.ProductListScreen

import com.arkivanov.decompose.ComponentContext
import core.network.ComponentState
import core.network.NetworkComponentContext
import data.dto.IProductRepository
import domain.product.ProductEAN
import domain.product.ProductSpecs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import utils.componentCoroutineScope

class RealProductListScreen(
    componentContext: ComponentContext,
    private val productRepository: IProductRepository,
    val onProductChoosen: (ProductEAN) -> Unit,
    val startProductReceive: () -> Unit
) : NetworkComponentContext(componentContext), IProductListScreen{
    override val products = MutableStateFlow(emptyList<ProductSpecs>())

    override val productLoadState = MutableUIStateFlow()

    override val pendingProductsAmount = MutableStateFlow(0)

    private val coroutineScope = componentCoroutineScope()

    init {
        loadProducts()
    }

    private fun loadProducts(){
        coroutineScope.launch(Dispatchers.IO) {
            productLoadState.value = ComponentState.Loading
            productRepository.getProductsInStock().collectRequest(productLoadState, products)
        }
    }

    private fun loadPendingProductsAmount(){
        coroutineScope.launch {
            productRepository.getPendingProductsAmount().collectRequest(result = pendingProductsAmount)
        }
    }

    override fun onProductClick(choosenEAN: ProductEAN) {
        onProductChoosen(choosenEAN)
    }

    override fun recieveProducts() {
        startProductReceive()
    }

}