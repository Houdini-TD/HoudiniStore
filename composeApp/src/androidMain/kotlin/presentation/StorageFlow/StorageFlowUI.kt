package presentation.StorageFlow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import presentation.StorageFlow.ProductDetailsScreen.ProductDetailsScreenUI
import presentation.StorageFlow.ProductListScreen.ProductListScreenUI


@Composable
fun StorageFlowUI(component: IStorageFlow) {
    val childStack by component.childStack.collectAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide())
    ) { child ->
        when (val instance = child.instance){
            is IStorageFlow.Child.ProductDetails -> ProductDetailsScreenUI(component = instance.component)
            is IStorageFlow.Child.ProductList -> ProductListScreenUI(component = instance.component)
        }
    }
}


