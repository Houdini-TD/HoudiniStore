package presentation.StorageFlow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import data.dto.FakeProductRepositoryImpl
import data.dto.IProductRepository
import data.dto.Local.LocalProductRepository
import domain.product.ProductEAN
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.PolymorphicSerializer
import utils.componentCoroutineScope
import kotlinx.serialization.Serializable
import presentation.StorageFlow.ProductDetailsScreen.RealProductDetailsScreen
import presentation.StorageFlow.ProductListScreen.RealProductListScreen
import utils.toStateFlow


class RealStorageFlow(
    componentContext: ComponentContext,
    val productRepository: IProductRepository
) : ComponentContext by componentContext, IStorageFlow {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, IStorageFlow.Child>> = childStack(
        source = navigation,
        serializer = PolymorphicSerializer(ChildConfig::class),
        initialConfiguration = ChildConfig.ProductList,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): IStorageFlow.Child = when (config) {

        is ChildConfig.ProductList -> {
            IStorageFlow.Child.ProductList(
                RealProductListScreen(
                    componentContext,
                    productRepository,
                    onProductChoosen = {
                        navigation.push(ChildConfig.ProductDetails(it))
                    },
                    {}
                )
            )
        }

        is ChildConfig.ProductDetails -> {
            IStorageFlow.Child.ProductDetails(
                RealProductDetailsScreen(
                    componentContext,
                    productRepository,
                    config.productEAN
                )
            )
        }
    }

    private val coroutineScope = componentCoroutineScope()

    private sealed interface ChildConfig {
        @Serializable
        object ProductList : ChildConfig

        @Serializable
        data class ProductDetails(val productEAN: ProductEAN) : ChildConfig
    }
}