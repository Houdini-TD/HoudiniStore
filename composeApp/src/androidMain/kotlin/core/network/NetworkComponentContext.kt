package core.network

import com.arkivanov.decompose.ComponentContext
import core.utils.Either
import domain.product.StoredProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import utils.componentCoroutineScope

abstract class NetworkComponentContext(context: ComponentContext) : ComponentContext by context {

    protected fun MutableUIStateFlow() = MutableStateFlow<ComponentState>(ComponentState.Idle)

    protected suspend fun <T> Flow<Either<String, T>>.collectRequest(
        state: MutableStateFlow<ComponentState>? = null,
        result: MutableStateFlow<T>
    ) {
        val coroutineScope = componentCoroutineScope()

        coroutineScope.launch(Dispatchers.IO) {
            state?.value = ComponentState.Loading
            this@collectRequest.collect {
                when (it) {
                    is Either.Left -> state?.value = ComponentState.Error(it.a)
                    is Either.Right -> {
                        state?.value = ComponentState.Success
                        result.value = it.b
                    }
                }
            }
        }
    }
}