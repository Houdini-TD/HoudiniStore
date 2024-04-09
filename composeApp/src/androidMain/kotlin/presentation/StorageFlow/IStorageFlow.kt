
package presentation.StorageFlow

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import presentation.StorageFlow.ProductDetailsScreen.IProductDetailsScreen
import presentation.StorageFlow.ProductListScreen.IProductListScreen


interface IStorageFlow{
    val childStack: StateFlow<ChildStack<*, Child>>


    sealed interface Child{
        class ProductList(val component: IProductListScreen) : Child
        class ProductDetails(val component: IProductDetailsScreen) : Child
    }
}