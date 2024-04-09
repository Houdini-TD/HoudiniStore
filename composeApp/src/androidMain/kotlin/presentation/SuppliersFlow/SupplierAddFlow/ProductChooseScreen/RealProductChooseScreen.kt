package presentation.SuppliersFlow.SupplierAddFlow.ProductChooseScreen

import com.arkivanov.decompose.ComponentContext
import core.network.ComponentState
import core.network.NetworkComponentContext
import data.dto.IProductRepository
import data.dto.ISuppliersRepository
import domain.product.ProductEAN
import domain.product.ProductSpecs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import utils.componentCoroutineScope

class RealProductChooseScreen(
    componentContext: ComponentContext,
    val productRepository: IProductRepository,
    val suppliersRepository: ISuppliersRepository,
    val onAddingFinished: () -> Unit
): NetworkComponentContext(componentContext), IProductChooseScreen {

    override val productList = MutableStateFlow(emptyList<ProductSpecs>())
    override val productLoadState = MutableUIStateFlow()

    override val chosenProductEans = HashSet<ProductEAN>()

    val coroutineScope = componentCoroutineScope()

    override fun onProductChosen(productEan: ProductEAN) {
        chosenProductEans.add(productEan)
    }

    override fun onAddingFinished() {
        coroutineScope.launch {
            suppliersRepository.addSuppliedProducts(chosenProductEans.toList())
        }
    }

    override fun onProductUnChosen(productEan: ProductEAN) {
        chosenProductEans.remove(productEan)
    }

    override fun loadProducts() {
        coroutineScope.launch {
            productLoadState.value = ComponentState.Loading
            productRepository.getProducts().collectRequest(productLoadState, productList)
            onAddingFinished()
        }
    }
}