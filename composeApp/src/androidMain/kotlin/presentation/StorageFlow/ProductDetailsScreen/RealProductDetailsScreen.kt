package presentation.StorageFlow.ProductDetailsScreen

import com.arkivanov.decompose.ComponentContext
import core.network.ComponentState
import core.network.NetworkComponentContext
import data.dto.IProductRepository
import domain.product.ProductEAN
import domain.product.StoredProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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
        coroutineScope.launch(Dispatchers.IO) {
            productRepository.getProductsByEan(chosenEan).collectRequest(productLoadState, productList)
            if (productLoadState.value == ComponentState.Success)
                this@RealProductDetailsScreen.productList.value = productList.value.toMutableList()
        }
    }

    override fun updateProduct(updatedProduct: StoredProduct) {
        productList.value[productList.value.indexOfFirst {it.id == updatedProduct.id}] = updatedProduct
        coroutineScope.launch(Dispatchers.IO) {
            productRepository.updateStoredProduct(updatedProduct)
        }
    }

    override fun onProductDelete(id: Int) {
        val index = productList.value.indexOfFirst { it.id == id }
        productList.value = productList.value.apply {this.removeAt(index)}
        if (index != -1){
            coroutineScope.launch{
                productRepository.deleteProductById(id)
            }
        }
    }
}